package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanSimpleInfo;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "여행 계획 API")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;
    @PostMapping("/api/travelPlans")
    public ApiResponse<GetTravelPlanSimpleInfo> createTravelPlan(@RequestBody CreateTravelPlanRequest createTravelPlanRequest, @CurrentUser Member member){
        return ApiResponse.onSuccess(travelPlanService.addTravelPlan(member, createTravelPlanRequest));
    }
}
