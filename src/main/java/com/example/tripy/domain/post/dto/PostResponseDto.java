package com.example.tripy.domain.post.dto;

import com.example.tripy.domain.post.Post;
import java.util.List;
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPostDetailInfo {

        private Long postId;
        private String title;
        private String nickname;
        private String content;
        private Long travelPlanId;
        private List<String> imgUrls;
        private List<String> fileUrls;
        private List<String> postTags;
        private Long viewCount;
        private String createdAt;


        public static GetPostDetailInfo toDto(Post post, List<String> imgUrls, List<String> fileUrls, List<String> postTags) {
            return GetPostDetailInfo.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .nickname(post.getMember().getNickName())
                .travelPlanId(post.getTravelPlan().getId())
                .content(post.getContent())
                .imgUrls(imgUrls)
                .fileUrls(fileUrls)
                .postTags(postTags)
                .viewCount(post.getView())
                .createdAt(String.valueOf(post.getCreatedAt()))
                .build();
        }
    }
}
