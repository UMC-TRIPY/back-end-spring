package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "여행 계획 API")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;
    @PostMapping("/api/travelPlans")
    public ApiResponse<String> createTravelPlan(@RequestBody CreateTravelPlanRequest createTravelPlanRequest, @CurrentUser Member member){
        travelPlanService.addTravelPlan(member, createTravelPlanRequest);
         return ApiResponse.onSuccess("여행 계획 생성 성공");
    }
}
