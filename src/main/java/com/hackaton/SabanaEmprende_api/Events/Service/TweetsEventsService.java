package com.hackaton.SabanaEmprende_api.Events.Service;

import com.hackaton.SabanaEmprende_api.Events.dto.TweetsApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetsEventsService {

    @Value("${twitter.token}")
    private String TWITTER_BEARER_TOKEN;

    @Value("${deepseek.api_key}")
    private String DEEPSEEK_API_KEY;

    private static final String urlTweets =
            "https://api.twitter.com/2/users/557962165/tweets?tweet.fields=created_at,text";

    private static final String deepSeekUrl =
            "https://api.deepseek.com/v1/chat/completions"; // 🔹 Endpoint estándar de DeepSeek

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Llama a DeepSeek para analizar los textos obtenidos de Twitter
     */
    public String deepSeekResponse(List<String> textos) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(DEEPSEEK_API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construir payload estilo OpenAI/DeepSeek
        Map<String, Object> body = Map.of(
                "model", "deepseek-chat",   // ⚠️ Ajusta si usas otro modelo
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "Eres un analista que clasifica tweets en relevantes o no para emprendimiento/comercio."),
                        Map.of("role", "user", "content", String.join("\n\n", textos))
                )
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                deepSeekUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }

    /**
     * Se ejecuta cada 30 segundos → obtiene tweets y manda textos a la IA
     */
    @Scheduled(fixedRate = 60000 * 3)
    public void searchTweets() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(TWITTER_BEARER_TOKEN);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<TweetsApiResponseDto> response = restTemplate.exchange(
                    urlTweets,
                    HttpMethod.GET,
                    entity,
                    TweetsApiResponseDto.class
            );

            TweetsApiResponseDto twitterResponse = response.getBody();

            if (twitterResponse != null && twitterResponse.getData() != null) {
                List<String> textos = twitterResponse.getData()
                        .stream()
                        .map(TweetsApiResponseDto.TweetData::getText)
                        .collect(Collectors.toList());

                System.out.println("📌 Textos obtenidos: " + textos);

                String deepSeekResult = deepSeekResponse(textos);
                System.out.println("🤖 Respuesta de IA: " + deepSeekResult);
            }

        } catch (Exception e) {
            System.err.println("Error consultando tweets: " + e.getMessage());
        }
    }
}
