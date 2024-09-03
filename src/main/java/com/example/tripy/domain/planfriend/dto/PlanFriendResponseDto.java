package com.example.tripy.domain.planfriend.dto;

import com.example.tripy.domain.planfriend.PlanFriend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PlanFriendResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPlanFriendInfo {

        private Long planFriendId;
        private Long memberId;
        private String memberName;

        public static GetPlanFriendInfo toDTO(PlanFriend planFriend) {
            return GetPlanFriendInfo.builder()
                .planFriendId(planFriend.getId())
                .memberId(planFriend.getMember().getId())
                .memberName(planFriend.getMember().getNickName())
                .build();
        }

    }

}
