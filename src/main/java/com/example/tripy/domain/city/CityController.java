package com.example.tripy.domain.city;

import com.example.tripy.domain.city.dto.CityResponseDto.CityFilterResult;
import com.example.tripy.domain.city.dto.CityResponseDto.SearchCityResponse;
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
@Tag(name = "도시 API")
@RequestMapping("/api/")
@RestController
public class CityController {

    private final CityService cityService;

    @Operation(summary = "도시 검색", description = "도시를 검색합니다.")
    @Parameter(name = "cityName", description = "검색할 도시 이름, query string 입니다.")
    @GetMapping("/search/cities")
    public ApiResponse<SearchCityResponse> searchCity(@RequestParam(value = "cityName") String cityName) {
        return ApiResponse.onSuccess(cityService.searchCity(cityName));
    }

    @Operation(summary = "도시 목록 조회", description = "도시 목록을 조회합니다. 필터링 검색에 사용합니다.")
    @Parameter(name = "countryName", description = "국가 이름, query string 입니다. null 이면 전체 도시를 조회합니다.")
    @Parameter(name = "cityName", description = "도시 이름, query string 입니다. countryName 입력 시 해당 국가의 전체 도시를 조회,  cityName 입력 시 해당 도시를 조회")
    @GetMapping("/cities")
    public ApiResponse<List<CityFilterResult>> getCities(
        @RequestParam(value = "countryName", required = false) String countryName,
        @RequestParam(value = "cityName", required = false) String cityName) {
        return ApiResponse.onSuccess(cityService.getCities(cityName, countryName));
    }

}
