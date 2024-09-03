package com.example.tripy.domain.city.dto;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.continent.Continent;
import com.example.tripy.domain.country.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CityResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCityResponse {

        private Long cityId;
        private String cityName;
        private String countryName;
        private String continentName;

        public static SearchCityResponse toDTO(City city) {
            return SearchCityResponse.builder()
                .cityId(city.getId())
                .cityName(city.getName())
                .countryName(city.getCountry().getName())
                .continentName(city.getCountry().getContinent().getName())
                .build();
        }
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CityFilterResult {

        private Long cityId;
        private String cityName;

        public static CityFilterResult toDTO(City city) {
            return CityFilterResult.builder()
                .cityId(city.getId())
                .cityName(city.getName())
                .build();
        }
    }
}
