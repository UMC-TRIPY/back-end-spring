package com.example.tripy.domain.conversation;

import com.example.tripy.domain.conversation.dto.ConversationResponseDto.ConversationDetailResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;

    public List<ConversationDetailResponse> findConversationListByCountry(Long countryId) {
        return conversationRepository.findAllByCountryId(countryId)
            .stream()
            .map(ConversationDetailResponse::toDTO)
            .toList();
    }
}
