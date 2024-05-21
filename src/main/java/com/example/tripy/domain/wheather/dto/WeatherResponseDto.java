package com.example.tripy.domain.wheather.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class WeatherResponseDto {
    private static final int CITY_NAME = 0;
    private static final int TEMP_MAX = 1;
    private static final int TEMP_MIN = 2;
    private static final int WEATHER_DATE = 3;


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
                .cityName(object[CITY_NAME].toString())
                .tempMax(Double.valueOf(object[TEMP_MAX].toString()))
                .tempMin(Double.valueOf(object[TEMP_MIN].toString()))
                .weatherDate(object[WEATHER_DATE].toString())
                .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class WhetherResponseInfo {
        Number currentTemp;
        String weatherMain;
        List<WhetherResponseSimpleInfo> whetherResponseSimpleInfoList;
        public static WhetherResponseInfo toDto(Float temp, String weatherMain, List<WhetherResponseSimpleInfo> whetherResponseSimpleInfoList) {
            return WhetherResponseInfo.builder()
                .currentTemp(temp)
                .weatherMain(weatherMain)
                .whetherResponseSimpleInfoList(whetherResponseSimpleInfoList)
                .build();
        }
    }
}
