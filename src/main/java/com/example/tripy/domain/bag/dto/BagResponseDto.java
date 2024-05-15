package com.example.tripy.domain.bag.dto;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.bagmaterials.dto.BagMaterialsResponseDto.BagMaterialInfo;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BagResponseDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BagListSimpleInfo {

		private Date departure;
		private Date arrival;
		private List<String> cities;
		private Long travelPlanId;

		@Builder
		public BagListSimpleInfo(List<String> cities, TravelPlan travelPlan) {
			this.departure = travelPlan.getDeparture();
			this.arrival = travelPlan.getArrival();
			this.cities = cities;
			this.travelPlanId = travelPlan.getId();
		}

	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class BagListWithMaterialInfo {

		private Long bagId;
		private String bagName;
		private Long travelPlanId;
		private List<BagMaterialInfo> bagMaterials;

	}

	public static BagListWithMaterialInfo toBagListWithMaterialInfoDto(Bag bag,
		List<BagMaterialInfo> bagMaterials) {
		return BagListWithMaterialInfo.builder()
			.bagId(bag.getId())
			.bagName(bag.getBagName())
			.travelPlanId(bag.getTravelPlan().getId())
			.bagMaterials(bagMaterials)
			.build();
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetBagSimpleInfo {

		private String bagName;
		private Long travelPlanId;
		private Long bagId;
		private String bagContent;


		public static GetBagSimpleInfo toDto(Bag bag) {
			return GetBagSimpleInfo.builder()
				.bagName(bag.getBagName())
				.bagId(bag.getId())
				.travelPlanId(bag.getTravelPlan().getId())
				.bagContent(bag.getContent())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetBagDetailInfo {

		private Long countryId;
		private String bagContent; //메모
		private BagListWithMaterialInfo bagListWithMaterialInfo; //가방 물건들
		private MaterialListByCountry materialListByCountry; //여행지별 추천 준비물

		public static GetBagDetailInfo toDto(Bag bag, Long countryId,
			BagListWithMaterialInfo bagListWithMaterialInfo,
			MaterialListByCountry materialListByCountry) {
			return GetBagDetailInfo.builder()
				.countryId(countryId)
				.bagContent(bag.getContent())
				.bagListWithMaterialInfo(bagListWithMaterialInfo)
				.materialListByCountry(materialListByCountry)
				.build();
		}
	}

}
