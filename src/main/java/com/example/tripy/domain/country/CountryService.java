package com.example.tripy.domain.country;

import com.example.tripy.domain.country.dto.CountryResponseDto.CountryFilterResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryFilterResult> getCountries(String countryName, String continentName) {
        return countryRepository.findAll().stream()
            .filter(country -> countryName == null || country.getName().equals(countryName))
            .filter(country -> continentName == null || country.getContinent().getName()
                .equals(continentName))
            .map(CountryFilterResult::toDTO)
            .collect(Collectors.toList());
    }

}
