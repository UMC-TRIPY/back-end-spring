package com.example.tripy.domain.bagmaterials.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BagMaterialsResponseDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BagMaterialInfo {

		private Long materialId;
		private String materialName;
		private Boolean isChecked;

	}

}
