package com.example.tripy.domain.conversation.dto;

import com.example.tripy.domain.conversation.Conversation;
import com.example.tripy.domain.language.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ConversationResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationDetailResponse {

        private String koreanSentences;
        private String translatedSentences;
        private Language language;
        private Long countryId;
        private String pronunciation;

        public static ConversationDetailResponse toDTO(Conversation conversation) {
            return ConversationDetailResponse.builder()
                .koreanSentences(conversation.getKorean())
                .translatedSentences(conversation.getTranslation())
                .language(conversation.getCountry().getLanguage())
                .countryId(conversation.getCountry().getId())
                .pronunciation(conversation.getPronunciation())
                .build();
        }
    }
}
