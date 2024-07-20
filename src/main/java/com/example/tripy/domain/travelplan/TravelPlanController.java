package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanDetailInfo;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanSimpleInfo;
import com.example.tripy.global.common.PageResponseDto;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "여행 계획 API")
@RequestMapping("/api/travelPlans")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;
    @PostMapping("/")
    @Operation(summary = "여행 계획 추가")
    public ApiResponse<GetTravelPlanSimpleInfo> createTravelPlan(@RequestBody CreateTravelPlanRequest createTravelPlanRequest, @CurrentUser Member member){
        return ApiResponse.onSuccess(travelPlanService.addTravelPlan(member, createTravelPlanRequest));
    }
}
