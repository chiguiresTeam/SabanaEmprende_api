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
    private String twitterBearerToken;


    /** Endpoint de Twitter para obtener los últimos tweets del usuario */
    private static final String URL_TWEETS =
            "https://api.twitter.com/2/users/557962165/tweets?tweet.fields=created_at,text";

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Se ejecuta cada 3 minutos: obtiene tweets y manda textos a la IA.
     */
    @Scheduled(fixedRate = 3 * 60 * 1000)
    public void searchTweets() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(twitterBearerToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<TweetsApiResponseDto> response = restTemplate.exchange(
                    URL_TWEETS,
                    HttpMethod.GET,
                    entity,
                    TweetsApiResponseDto.class
            );

            TweetsApiResponseDto twitterResponse = response.getBody();

            if (twitterResponse != null && twitterResponse.getData() != null) {
                List<String> textos = twitterResponse.getData().stream()
                        .map(TweetsApiResponseDto.TweetData::getText)
                        .collect(Collectors.toList());

                System.out.println("📌 Textos obtenidos: " + textos);

            } else {
                System.out.println("⚠️ No se recibieron tweets nuevos.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error consultando tweets o DeepSeek: " + e.getMessage());
        }
    }
}
