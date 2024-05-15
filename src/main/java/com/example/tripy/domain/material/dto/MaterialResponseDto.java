package com.example.tripy.domain.material.dto;

import com.example.tripy.domain.material.Material;
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

		private List<CountryMaterialInfo> materials;

	}

	public static MaterialListByCountry toMaterialListByCountryDto(
		List<CountryMaterialInfo> materials) {
		return MaterialListByCountry.builder()
			.materials(materials)
			.build();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CountryMaterialInfo {

		private Long materialId;
		private String materialName;

	}

}
