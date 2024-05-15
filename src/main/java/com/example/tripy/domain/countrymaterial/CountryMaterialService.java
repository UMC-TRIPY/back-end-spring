package com.example.tripy.domain.countrymaterial;

import static com.example.tripy.domain.material.dto.MaterialResponseDto.toMaterialListByCountryDto;

import com.example.tripy.domain.material.Material;
import com.example.tripy.domain.material.dto.MaterialResponseDto.CountryMaterialInfo;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountryMaterialService {

	private final CountryMaterialRepository countryMaterialRepository;

	public MaterialListByCountry getCountryMaterials(Long countryId) {
		List<Material> materials = countryMaterialRepository.findMaterialsByCountry(countryId);

		return toMaterialListByCountryDto(materials.stream()
			.map(material -> new CountryMaterialInfo(material.getId(), material.getName()))
			.collect(Collectors.toList()));
	}

}
