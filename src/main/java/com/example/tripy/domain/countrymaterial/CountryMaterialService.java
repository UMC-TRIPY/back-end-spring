package com.example.tripy.domain.countrymaterial;

import static com.example.tripy.domain.material.dto.MaterialResponseDto.toMaterialListByCountryDto;

import com.example.tripy.domain.country.Country;
import com.example.tripy.domain.country.CountryRepository;
import com.example.tripy.domain.material.Material;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountryMaterialService {

	private final CountryMaterialRepository countryMaterialRepository;
	private final CountryRepository countryRepository;

	public MaterialListByCountry getCountryMaterials(String countryName) {
		Country country = countryRepository.findCountryByName(countryName)
			.orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_COUNTRY));
		List<Material> materials = countryMaterialRepository.findMaterialsByCountry(countryName);

		return toMaterialListByCountryDto(country.getId(),
			materials.stream().map(Material::getName).collect(Collectors.toList()));
	}
}
