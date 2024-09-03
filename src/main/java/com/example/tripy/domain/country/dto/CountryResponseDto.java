package com.example.tripy.domain.country.dto;

import com.example.tripy.domain.country.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CountryResponseDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CountryFilterResult {

        private Long countryId;
        private String countryName;

        public static CountryFilterResult toDTO(Country country) {
            return CountryFilterResult.builder()
                .countryId(country.getId())
                .countryName(country.getName())
                .build();
        }
    }

}
