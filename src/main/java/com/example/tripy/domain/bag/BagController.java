package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagResponseDto.BagSimpleInfo;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BagController {

    private final BagService bagService;

    /**
     * [GET] 내 여행 가방 모두 불러오기
     */
    @GetMapping("travel-bag/member/bag/{memberId}")
    public ApiResponse<PageResponseDto<List<BagSimpleInfo>>> getBagsList(
        @PathVariable(value = "memberId") Long memberId, @RequestParam(value = "page") int page,
        @RequestParam(value = "size") int size) {
        return ApiResponse.onSuccess(bagService.getBagsList(page, size, memberId));
    }

}
