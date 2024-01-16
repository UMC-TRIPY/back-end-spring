package com.example.tripy.domain.currency.dto;

import com.example.tripy.domain.currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponseDto {
    private Long id;

    private String currencyUnit;

    private String currencyName;

    private float deal;

    private String countryName;

    public static CurrencyResponseDto toDTO(Currency entity){
        return CurrencyResponseDto.builder()
            .id(entity.getId())
            .currencyUnit(entity.getCurrencyUnit())
            .currencyName(entity.getCurrencyName())
            .deal(entity.getDeal())
            .countryName(entity.getCountryName())
            .build();
    }
}
