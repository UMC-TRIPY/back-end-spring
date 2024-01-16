package com.example.tripy.domain.currency;

import com.example.tripy.domain.currency.dto.CurrencyResponseDto;
import com.example.tripy.domain.post.dto.PostResponseDto;
import com.example.tripy.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;
    /**
     * [GET] 전체 환율 조회
     */
    @GetMapping
    public ApiResponse<List<CurrencyResponseDto>> getCurrencyList(){
        return ApiResponse.onSuccess(currencyService.getCurrencyList());
    }

    /**
     * [GET] 나라별 환율 조회
     */
    @GetMapping("/{countryId}")
    public ApiResponse<CurrencyResponseDto> getCurrencyByCountry(@PathVariable Long countryId){
        return ApiResponse.onSuccess(currencyService.getCurrencyByCountryId(countryId));
    }
}
