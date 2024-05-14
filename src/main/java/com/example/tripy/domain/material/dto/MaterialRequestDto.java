package com.example.tripy.domain.material.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MaterialRequestDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateMaterialRequest {

		private String materialName;
	}

}
