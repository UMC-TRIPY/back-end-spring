package com.example.tripy.domain.post.dto;

import com.example.tripy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPostSimpleInfo {

        Long postId;
        String cityName;
        String title;
        int recommendationCount;
        Long viewCount;

        public static GetPostSimpleInfo toDto(Post post) {
            return GetPostSimpleInfo.builder()
                .postId(post.getId())
                .cityName(post.getCity().getName())
                .title(post.getTitle())
                .recommendationCount(post.getRecommendationCount())
                .viewCount(post.getView())
                .build();
        }
    }
}
