package com.example.tripy.domain.currency;

import com.example.tripy.domain.country.Country;
import com.example.tripy.domain.country.CountryRepository;
import com.example.tripy.domain.currency.dto.CurrencyResponseDto;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrencyService {


    private final CurrencyRepository currencyRepository;
    private final CountryRepository countryRepository;

    public List<CurrencyResponseDto> getCurrencyList() {
        List<Currency> currencyList = currencyRepository.findAll();

        return currencyList.stream()
            .map(CurrencyResponseDto::toDTO)
            .collect(Collectors.toList());
    }

    public CurrencyResponseDto getCurrencyByCountryId(Long countryId) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_COUNTRY));

        String countryName = country.getName();

        if(countryName.equals("스페인") || countryName.equals("프랑스")){
            countryName = "유로";
        }

        Currency currency = currencyRepository.findByCountryName(countryName)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_CURRENCY));

        return CurrencyResponseDto.euroToDto(currency, countryName);
    }
}
