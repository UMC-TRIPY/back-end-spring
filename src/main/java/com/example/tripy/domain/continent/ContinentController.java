package com.example.tripy.domain.continent;

import com.example.tripy.domain.continent.dto.ContinentResponseDto.ContinentFilterResult;
import com.example.tripy.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "대륙 API")
@RequestMapping("/api/")
@RestController
public class ContinentController {

    private final ContinentService continentService;

    @Operation(summary = "대륙 목록 조회", description = "대륙 목록을 조회합니다. 필터링 검색에 사용합니다.")
    @Parameter(name = "continentName", description = "대륙 이름, query string 입니다. null 이면 전체 대륙을 조회합니다.")
    @GetMapping("/continents")
    public ApiResponse<List<ContinentFilterResult>> getContinents(
        @RequestParam(value = "continentName", required = false) String continentName) {
        return ApiResponse.onSuccess(continentService.getContinents(continentName));
    }
}
