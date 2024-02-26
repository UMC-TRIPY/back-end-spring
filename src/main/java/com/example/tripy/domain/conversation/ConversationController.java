package com.example.tripy.domain.conversation;

import com.example.tripy.domain.conversation.dto.ConversationResponseDto.ConversationDetailResponse;
import com.example.tripy.global.s3.S3Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ConversationController {

    private final ConversationService conversationService;
    private final S3Service s3Service;

    @GetMapping("/api/conversation/country/{country-id}")
    public ResponseEntity<List<ConversationDetailResponse>> findConversationListByCountry(
        @PathVariable("country-id") Long countryId) {
        return ResponseEntity.ok(conversationService.findConversationListByCountry(countryId));
    }
    
}
