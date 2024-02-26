package com.example.tripy.domain.post.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequestDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreatePostRequest {

        private String title;

        private String content;

        private List<String> imageUrls;

        private List<String> fileUrls;

        private List<Long> tagIds;
    }
}