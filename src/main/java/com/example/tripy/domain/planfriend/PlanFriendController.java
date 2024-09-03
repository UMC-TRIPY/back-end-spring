package com.example.tripy.domain.planfriend;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.planfriend.dto.PlanFriendResponseDto.GetPlanFriendInfo;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "친구 계획 API")
@RequestMapping("/api/plan-friends")
@RestController
public class PlanFriendController {

    private final PlanFriendService planFriendService;

    @GetMapping("/{travelPlanId}")
    public ApiResponse<List<GetPlanFriendInfo>> getPlanFriends(@CurrentUser Member member,
        @PathVariable(value = "travelPlanId") Long travelPlanId) {
        return ApiResponse.onSuccess(planFriendService.getPlanFriends(member, travelPlanId));
    }

}
