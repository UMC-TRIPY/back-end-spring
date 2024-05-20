package com.example.tripy.domain.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
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
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OIDCPublicKeysResponse(List<OIDCPublicKey> keys) {
    }
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OIDCPublicKey(
        String kid,
        String kty,
        String alg,
        String use,
        String n,
        String e
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OIDCDecodePayload(
        String issuer,
        String audience,
        String subject,
        String email,
        String nickName,
        String picture

    ){
    }





}
