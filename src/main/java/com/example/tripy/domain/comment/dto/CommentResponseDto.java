package com.example.tripy.domain.comment.dto;

import com.example.tripy.domain.comment.Comment;
import com.example.tripy.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

public class CommentResponseDto {

    @Getter
    @Builder
    public static class GetCommentInfo {

        private String nickname;
        private String profileImageUrl;
        private String content;
        private String createdAt;

        public static GetCommentInfo toDto(Comment comment, Member member, String createdAt) {
            return GetCommentInfo.builder()
                .nickname(member.getNickName())
                .profileImageUrl(member.getProfileImgUrl())
                .content(comment.getContent())
                .createdAt(createdAt)
                .build();

        }
    }
}
