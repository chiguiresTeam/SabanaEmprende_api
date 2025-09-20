package com.hackaton.SabanaEmprende_api.Events.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TweetsApiResponseDto {
    private List<TweetData> data;  // la lista de tweets
    private Meta meta;             // info de paginación (opcional)

    @Getter
    @Setter
    public static class TweetData {
        private String id;
        private String text;
        private List<String> edit_history_tweet_ids;
    }

    @Getter
    @Setter
    public static class Meta {
        private int result_count;
        private String next_token;
    }
}
