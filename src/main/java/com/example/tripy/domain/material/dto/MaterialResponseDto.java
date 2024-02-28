package com.example.tripy.domain.material.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MaterialResponseDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class MaterialListByCountry {

		private Long countryId;
		private List<String> materials;

	}

	public static MaterialListByCountry toMaterialListByCountryDto(Long countryId,
		List<String> materials) {
		return MaterialListByCountry.builder()
			.countryId(countryId)
			.materials(materials)
			.build();
	}

}
