package com.example.tripy.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreateCommentRequest {
        private String content;
    }
}
