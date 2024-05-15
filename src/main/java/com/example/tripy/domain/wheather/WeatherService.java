package com.example.tripy.domain.wheather;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.city.CityRepository;
import com.example.tripy.domain.wheather.dto.WheatherResponseDto.WhetherResponseInfo;
import com.example.tripy.domain.wheather.dto.WheatherResponseDto.WhetherResponseSimpleInfo;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.plaf.IconUIResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    public WhetherResponseInfo getWeatherList(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new GeneralException(
            ErrorStatus._EMPTY_CITY));

        List<Object[]> weatherList = weatherRepository.findAllByCityNameAndByDateTime(city.getName());

        List<WhetherResponseSimpleInfo> whetherResponseSimpleInfoList
            = weatherList.stream()
            .map(WhetherResponseSimpleInfo::toDto)
            .sorted(Comparator.comparing(WhetherResponseSimpleInfo::getWeatherDate)) // datetime 기준으로 오름차순 정렬
            .collect(Collectors.toList());

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        double temp = weatherRepository.findClosestTemperature(
            city.getName(), nowDate, nowTime);

        return WhetherResponseInfo.toDto(temp, whetherResponseSimpleInfoList);
    }
}
