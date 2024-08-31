package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanListSimpleInfo;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanSimpleInfo;
import com.example.tripy.domain.traveltimeplan.dto.TravelTimePlanResponseDto.GetTravelTimePlanDetailInfo;
import com.example.tripy.global.common.PageResponseDto;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "여행 계획 API")
@RequestMapping("/api/travel-plans")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    @PostMapping("/")
    @Operation(summary = "여행 계획 추가")
    public ApiResponse<GetTravelPlanSimpleInfo> createTravelPlan(
        @RequestBody CreateTravelPlanRequest createTravelPlanRequest, @CurrentUser Member member) {
        return ApiResponse.onSuccess(
            travelPlanService.addTravelPlan(member, createTravelPlanRequest));
    }


    @Operation(summary = "내 여행 목록 조회하기", description = "내가 속한 여행 목록을 조회힙니다. (일정에서 bag_exists 값이 false 값 조회)")
    @Parameter(name = "page", description = "페이징 page, query string 입니다.")
    @Parameter(name = "size", description = "페이징 size, query string 입니다.")
    @GetMapping("/")
    public ApiResponse<PageResponseDto<List<GetTravelPlanListSimpleInfo>>> getTravelPlanList(
        @CurrentUser Member member, @RequestParam(value = "page") int page,
        @RequestParam(value = "size") int size) {
        return ApiResponse.onSuccess(travelPlanService.getTravelPlanExistsList(page, size, member));
    }


    //TODO : Date 값 이상하게 들어가는 현상 해결 필요
    @Operation(summary = "여행 일정 상세 조회하기", description = "여행 목록을 상세 조회힙니다.")
    @GetMapping("/{travelPlanId}")
    public ApiResponse<List<GetTravelTimePlanDetailInfo>> getTravelPlanDetail(
        @CurrentUser Member member, @PathVariable(value = "travelPlanId") Long travelPlanId) {
        return ApiResponse.onSuccess(travelPlanService.getTravelPlanDetail(member, travelPlanId));

    }

}
