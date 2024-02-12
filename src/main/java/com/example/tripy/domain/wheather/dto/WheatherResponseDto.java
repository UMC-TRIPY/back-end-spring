package com.example.tripy.domain.wheather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class WheatherResponseDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class WhetherResponseSimpleInfo{

        private String cityName;

        private Number tempMax;

        private Number tempMin;

        private String weatherDate;

        public static WhetherResponseSimpleInfo toDto(Object[] object) {
            return WhetherResponseSimpleInfo.builder()
                .cityName(object[0].toString())
                .tempMax(Double.valueOf(object[1].toString()))
                .tempMin(Double.valueOf(object[1].toString()))
                .weatherDate(object[3].toString())
                .build();
        }
    }


}
