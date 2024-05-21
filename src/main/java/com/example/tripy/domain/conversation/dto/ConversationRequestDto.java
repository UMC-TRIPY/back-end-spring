package com.example.tripy.domain.conversation.dto;

import com.example.tripy.domain.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ConversationRequestDto {
    @Getter
    @AllArgsConstructor
    public static class ConversationCreateRequest {
        private String korean;

        private String translation;

        private String pronunciation;

        private Country country;
    }
}
