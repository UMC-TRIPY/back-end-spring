package com.example.tripy.domain.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class AuthResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginSimpleInfo{
        private String accessToken;
        private String refreshToken;

        public static LoginSimpleInfo toDTO(String accessToken, String refreshToken) {
            return LoginSimpleInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        }

    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoAccessTokenResponse(
        String idToken
    ) {
        public static KakaoAccessTokenResponse of(
            final String idToken
        ) {
            return new KakaoAccessTokenResponse(
                idToken
            );
        }


    }


}
