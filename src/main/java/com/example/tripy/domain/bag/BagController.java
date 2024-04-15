package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagResponseDto.BagListSimpleInfo;
import com.example.tripy.global.common.PageResponseDto;
import com.example.tripy.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/travel-bag")
public class BagController {

    private final BagService bagService;


    /**
     * [GET] 내 여행 가방 모두 불러오기
     */
    @GetMapping("/member/bag/{memberId}")
    public ApiResponse<PageResponseDto<List<BagListSimpleInfo>>> getBagsList(
        @PathVariable(value = "memberId") Long memberId, @RequestParam(value = "page") int page,
        @RequestParam(value = "size") int size) {
        return ApiResponse.onSuccess(bagService.getTravelBagExistsList(page, size, memberId));
    }

    /**
     * [POST] 내 여행 일정 목록에서 해당하는 가방 목록 생성하기
     */
    @PostMapping("/member/bag/{memberId}/{travelPlanId}")
    public ApiResponse<String> updateBagExists(@PathVariable Long memberId,
        @PathVariable(value = "travelPlanId") Long travelPlanId) {
        return ApiResponse.onSuccess(bagService.updateBagExists(memberId, travelPlanId));
    }

}
