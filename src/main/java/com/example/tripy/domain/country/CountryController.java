package com.example.tripy.domain.country;

import com.example.tripy.domain.country.dto.CountryResponseDto.CountryFilterResult;
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
@Tag(name = "국가 API")
@RequestMapping("/api")
@RestController
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "국가 목록 조회", description = "국가 목록을 조회합니다. 필터링 검색에 사용합니다.")
    @Parameter(name = "continentName", description = "대륙 이름, query string 입니다. null 이면 전체 국가를 조회합니다.")
    @Parameter(name = "countryName", description = "국가 이름, query string 입니다. continentName 입력 시 해당 대륙의 전체 국가를 조회,  countryName 입력 시 해당 국가를 조회")
    @GetMapping("/countries")
    public ApiResponse<List<CountryFilterResult>> getCountries(
        @RequestParam(value = "continentName", required = false) String continentName,
        @RequestParam(value = "countryName", required = false) String countryName) {
        return ApiResponse.onSuccess(countryService.getCountries(countryName, continentName));
    }


}
