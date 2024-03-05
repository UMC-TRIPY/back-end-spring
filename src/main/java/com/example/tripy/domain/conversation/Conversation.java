package com.example.tripy.domain.conversation;

import com.example.tripy.domain.conversation.dto.ConversationRequestDto.ConversationCreateRequest;
import com.example.tripy.domain.country.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String korean;

    @NotNull
    private String translation;

    @NotNull
    private String pronunciation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public static Conversation toEntity(ConversationCreateRequest requestDto) {
        return Conversation.builder()
            .korean(requestDto.getKorean())
            .translation(requestDto.getTranslation())
            .pronunciation(requestDto.getPronunciation())
            .country(requestDto.getCountry())
            .build();
    }

}