package com.example.tripy.domain.bag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BagRequestDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateBagRequest {

		private String bagName;
	}


}
