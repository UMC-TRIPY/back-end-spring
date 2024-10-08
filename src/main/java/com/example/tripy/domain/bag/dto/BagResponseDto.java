package com.example.tripy.domain.bag.dto;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.bagmaterials.dto.BagMaterialsResponseDto.BagMaterialInfo;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import com.example.tripy.domain.travelplan.TravelPlan;
import java.time.LocalDate;
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

		private LocalDate departureDate;
		private LocalDate arrivalDate;
		private List<String> cities;
		private Long travelPlanId;

		@Builder
		public BagListSimpleInfo(List<String> cities, TravelPlan travelPlan) {
			this.departureDate = travelPlan.getDepartureDate();
			this.arrivalDate = travelPlan.getArrivalDate();
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

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetBagListDetailInfo {

		private LocalDate departure;
		private LocalDate arrival;
		private List<String> cities;
		private List<GetBagSimpleListInfo> bags;

		public static GetBagListDetailInfo toDto(List<GetBagSimpleListInfo> bags,
			List<String> cities, TravelPlan travelPlan) {
			return GetBagListDetailInfo.builder()
				.departure(travelPlan.getDepartureDate())
				.arrival(travelPlan.getArrivalDate())
				.cities(cities)
				.bags(bags)
				.build();
		}

	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetBagSimpleListInfo {

		private Long bagId;
		private String bagName;

		public static GetBagSimpleListInfo toDto(Bag bag) {
			return GetBagSimpleListInfo.builder()
				.bagId(bag.getId())
				.bagName(bag.getBagName())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class BagAdditionInfo {

		private Long bagId;


		public static BagAdditionInfo toDto(Bag bag) {
			return BagAdditionInfo.builder()
				.bagId(bag.getId())
				.build();
		}
	}

}
