package com.example.tripy.domain.bagmaterials.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BagMaterialsResponseDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BagMaterialInfo {

		private String materialName;
		private Boolean isChecked;

	}

}
