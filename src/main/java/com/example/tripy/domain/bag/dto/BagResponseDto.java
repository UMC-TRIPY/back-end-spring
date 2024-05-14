package com.example.tripy.domain.bag.dto;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.bagmaterials.dto.BagMaterialsResponseDto.BagMaterialInfo;
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

		private String bagName;
		private List<BagMaterialInfo> bagMaterials;
		private Long travelPlanId;

	}

	public static BagListWithMaterialInfo toBagListWithMaterialInfoDto(String bagName,
		List<BagMaterialInfo> bagMaterials, Long travelPlanId) {
		return BagListWithMaterialInfo.builder()
			.bagName(bagName)
			.bagMaterials(bagMaterials)
			.travelPlanId(travelPlanId)
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

}
