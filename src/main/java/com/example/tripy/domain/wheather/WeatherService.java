package com.example.tripy.domain.wheather;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.city.CityRepository;
import com.example.tripy.domain.wheather.dto.WheatherResponseDto;
import com.example.tripy.domain.wheather.dto.WheatherResponseDto.WhetherResponseSimpleInfo;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    public List<WhetherResponseSimpleInfo> getWeatherList(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new GeneralException(
            ErrorStatus._EMPTY_CITY));

        List<Object[]> weatherList = weatherRepository.findAllByCityNameAndByDateTime(city.getName());

        List<WhetherResponseSimpleInfo> result
            = weatherList.stream()
            .map(WhetherResponseSimpleInfo::toDto)
            .collect(Collectors.toList());

        return result;
    }
}
