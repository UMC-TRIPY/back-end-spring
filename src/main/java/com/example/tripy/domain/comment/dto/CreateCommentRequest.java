package com.example.tripy.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateCommentRequest {

    @AllArgsConstructor
    @Getter
    public static class CrateCommentRequest {
        private String content;
    }
}
