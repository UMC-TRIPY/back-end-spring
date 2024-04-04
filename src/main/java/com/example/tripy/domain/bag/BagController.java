package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagRequestDto.CreateBagRequest;
import com.example.tripy.domain.bag.dto.BagResponseDto.BagListSimpleInfo;
import com.example.tripy.domain.bag.dto.BagResponseDto.BagListWithMaterialInfo;
import com.example.tripy.domain.countrymaterial.CountryMaterialService;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "여행 가방 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/travel-bag")
public class BagController {

	private final BagService bagService;
	private final CountryMaterialService countryMaterialService;

	/**
	 * [GET] 내 여행 가방 모두 불러오기
	 */
	@Operation(summary = "내 여행 가방 모두 불러오기", description = "여행 가방이 존재하는 목록을 불러옵니다.")
	@Parameter(name = "page", description = "내 여행 가방 목록 페이지 번호, query string 입니다.")
	@Parameter(name = "size", description = "내 여행 가방 목록 페이지 번호, query string 입니다.")
	@GetMapping("/members/bags")
	public ApiResponse<PageResponseDto<List<BagListSimpleInfo>>> getBagsList(
		@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return ApiResponse.onSuccess(bagService.getTravelBagExistsList(page, size));
	}

	/**
	 * [POST] 내 여행 일정 목록에서 해당하는 가방 목록 생성하기
	 */
	@Operation(summary = "내 여행 일정 목록에서 해당하는 가방 목록 생성하기", description = "여행 일정 목록에서 가방을 생성하기 위해 해당하는 가방 목록을 생성합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@PostMapping("/members/bags/{travelPlanId}")
	public ApiResponse<String> updateBagExists(
		@PathVariable(value = "travelPlanId") Long travelPlanId) {
		return ApiResponse.onSuccess(bagService.updateBagExists(travelPlanId));
	}

	/**
	 * [GET] 여행지별 준비물 불러오기
	 */
	@Operation(summary = "여행지별 준비물 불러오기", description = "나라별 존재하는 추천 준비물을 불러옵니다.")
	@Parameter(name = "countryName", description = "나라 이름, query string 입니다.")
	@GetMapping("/material-name")
	public ApiResponse<MaterialListByCountry> getCountryMaterials(
		@RequestParam(value = "countryName") String countryName) {
		return ApiResponse.onSuccess(countryMaterialService.getCountryMaterials(countryName));
	}

	/**
	 * [POST] 여행 일정에 해당하는 개별 가방 생성(추가)하기
	 */
	@Operation(summary = "여행 일정에 해당하는 개별 가방 생성(추가)하기", description = "여행 일정에 해당하는 캐리어, 크로스백과 같은 가방을 생성합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, query string 입니다.")
	@PostMapping("/members/bags")
	public ApiResponse<String> createBag(@RequestBody CreateBagRequest createBagRequest,
		@RequestParam Long travelPlanId) {
		return ApiResponse.onSuccess(bagService.addBag(createBagRequest, travelPlanId));
	}

	/**
	 * [GET] 여행 가방 리스트와 가방 내 준비물 불러오기
	 */
	@Operation(summary = "내 여행 가방 리스트와 가방 내 준비물 불러오기", description = "여행 가방 리스트와 그 가방에 존재하는 준비물을 불러오고, isChecked로 챙겼는지 확인합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@GetMapping("/members/materials/{travelPlanId}")
	public ApiResponse<List<BagListWithMaterialInfo>> getBagsListAndMaterialsByTravelPlan(
		@PathVariable(value = "travelPlanId") Long travelPlanId) {
		return ApiResponse.onSuccess(bagService.getBagsListAndMaterialsByTravelPlan(travelPlanId));
	}

}
