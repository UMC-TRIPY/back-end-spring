package com.example.tripy.domain.city;

import com.example.tripy.domain.city.dto.CityResponseDto.CityFilterResult;
import com.example.tripy.domain.city.dto.CityResponseDto.SearchCityResponse;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;

    public SearchCityResponse searchCity(String cityName) {
        return SearchCityResponse.toDTO(cityRepository.findByName(cityName)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_CITY)));
    }

    public List<CityFilterResult> getCities(String cityName, String countryName) {
        return cityRepository.findAll().stream()
            .filter(city -> cityName == null || city.getName().equals(cityName))
            .filter(city -> countryName == null || city.getCountry().getName().equals(countryName))
            .map(CityFilterResult::toDTO)
            .collect(Collectors.toList());
    }

}
