package com.example.tripy.domain.planfriend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PlanFriendRequestDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreatePlanFriendRequest {

        private Long memberId;

    }

}
