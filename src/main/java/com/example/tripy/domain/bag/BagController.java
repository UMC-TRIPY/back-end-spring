package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagRequestDto.CreateBagRequest;
import com.example.tripy.domain.bag.dto.BagRequestDto.UpdateBagContent;
import com.example.tripy.domain.bag.dto.BagResponseDto.BagListSimpleInfo;
import com.example.tripy.domain.bag.dto.BagResponseDto.BagListWithMaterialInfo;
import com.example.tripy.domain.bag.dto.BagResponseDto.GetBagDetailInfo;
import com.example.tripy.domain.bag.dto.BagResponseDto.GetBagListDetailInfo;
import com.example.tripy.domain.bag.dto.BagResponseDto.GetBagSimpleInfo;
import com.example.tripy.domain.countrymaterial.CountryMaterialService;
import com.example.tripy.domain.material.dto.MaterialRequestDto.CreateMaterialRequest;
import com.example.tripy.domain.material.dto.MaterialResponseDto.MaterialListByCountry;
import com.example.tripy.domain.member.Member;
import com.example.tripy.global.common.PageResponseDto;
import com.example.tripy.global.response.ApiResponse;
import com.example.tripy.global.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	@GetMapping("/members/bags")
	public ApiResponse<PageResponseDto<List<BagListSimpleInfo>>> getBagsList(
		@CurrentUser Member member, @RequestParam(value = "page") int page,
		@RequestParam(value = "size") int size) {
		return ApiResponse.onSuccess(bagService.getTravelBagExistsList(page, size, member));
	}

	/**
	 * [POST] 내 여행 일정 목록에서 해당하는 가방 목록 생성하기
	 */
	@Operation(summary = "내 여행 일정 목록에서 해당하는 가방 목록 생성하기", description = "여행 일정 목록에서 가방을 생성하기 위해 해당하는 가방 목록을 생성합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@PostMapping("/members/bags/{travelPlanId}")
	public ApiResponse<String> updateBagExists(@CurrentUser Member member,
		@PathVariable(value = "travelPlanId") Long travelPlanId) {
		return ApiResponse.onSuccess(bagService.updateBagExists(travelPlanId, member));
	}

	/**
	 * [GET] 여행지별 준비물 불러오기
	 */
	@Operation(summary = "여행지별 준비물 불러오기", description = "나라별 존재하는 추천 준비물을 불러옵니다.")
	@Parameter(name = "countryName", description = "나라 이름, query string 입니다.")
	@GetMapping("/material-name")
	public ApiResponse<MaterialListByCountry> getCountryMaterials(
		@RequestParam(value = "countryId") Long countryId) {
		return ApiResponse.onSuccess(countryMaterialService.getCountryMaterials(countryId));
	}

	/**
	 * [POST] 여행 일정에 해당하는 개별 가방 생성(추가)하기
	 */
	@Operation(summary = "여행 일정에 해당하는 개별 가방 생성(추가)하기", description = "여행 일정에 해당하는 캐리어, 크로스백과 같은 가방을 생성합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, query string 입니다.")
	@PostMapping("/members/bags")
	public ApiResponse<String> createBag(@RequestBody CreateBagRequest createBagRequest,
		@RequestParam Long travelPlanId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(bagService.addBag(createBagRequest, travelPlanId, member));
	}

	/**
	 * [GET] 여행 가방 리스트와 가방 내 준비물 불러오기
	 */
	@Operation(summary = "내 여행 가방 리스트와 가방 내 준비물 불러오기", description = "여행 가방 리스트와 그 가방에 존재하는 준비물을 불러오고, isChecked로 챙겼는지 확인합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@GetMapping("/members/materials/{travelPlanId}")
	public ApiResponse<List<BagListWithMaterialInfo>> getBagsListAndMaterialsByTravelPlan(
		@PathVariable(value = "travelPlanId") Long travelPlanId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(bagService.getBagsListAndMaterialsByTravelPlan(travelPlanId, member));
	}

	/**
	 * [PATCH] 여행 가방 메모 작성하기
	 */
	@Operation(summary = "가방 내부에 메모 작성하기", description = "가방 내부에 메모를 작성합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@PatchMapping("/members/bags/{travelPlanId}/{bagId}/memo")
	public ApiResponse<GetBagSimpleInfo> updateMemo(@RequestBody UpdateBagContent updateBagContent,
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(bagService.updateMemo(updateBagContent, travelPlanId, bagId, member));
	}

	/**
	 * [POST] 여행 가방에 준비물 추가하기
	 */
	@Operation(summary = "가방 내부에 준비물 추가하기", description = "가방 내부에 준비물을 추가합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@PostMapping("/members/bags/{travelPlanId}/{bagId}/materials")
	public ApiResponse<BagListWithMaterialInfo> addBagMaterial(
		@RequestBody CreateMaterialRequest createMaterialRequest,
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(
			bagService.addBagMaterial(createMaterialRequest, travelPlanId, bagId, member));
	}


	/**
	 * [PATCH] 여행 가방 준비물 이름 수정
	 */
	@Operation(summary = "여행 가방 준비물 이름 수정하기", description = "가방의 준비물 이름을 수정합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@Parameter(name = "materialId", description = "준비물 Id, query string 입니다.")
	@PatchMapping("/members/bags/{travelPlanId}/{bagId}/materials")
	public ApiResponse<BagListWithMaterialInfo> updateBagMaterialName(
		@RequestBody CreateMaterialRequest updateMaterialRequest,
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @RequestParam Long materialId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(
			bagService.updateBagMaterialName(updateMaterialRequest, travelPlanId, bagId,
				materialId, member));
	}

	/**
	 * [DELETE] 여행 가방 준비물 삭제
	 */
	@Operation(summary = "여행 가방 준비물 삭제하기", description = "가방의 준비물을 삭제합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@Parameter(name = "materialId", description = "준비물 Id, query string 입니다.")
	@DeleteMapping("/members/bags/{travelPlanId}/{bagId}/materials")
	public ApiResponse<BagListWithMaterialInfo> deleteBagMaterial(
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @RequestParam Long materialId , @CurrentUser Member member) {
		return ApiResponse.onSuccess(
			bagService.deleteBagMaterial(travelPlanId, bagId, materialId, member));
	}

	/**
	 * [PATCH] 여행 가방 준비물 체크박스 수정
	 */
	@Operation(summary = "여행 가방 준비물 체크박스 업데이트", description = "가방의 준비물 체크 상태를 수정합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@Parameter(name = "materialId", description = "준비물 Id, query string 입니다.")
	@PatchMapping("/members/bags/{travelPlanId}/{bagId}/materials/check")
	public ApiResponse<Boolean> updateBagMaterialIsChecked(
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @RequestParam Long materialId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(
			bagService.updateBagMaterialIsChecked(travelPlanId, bagId, materialId, member));
	}

	@Operation(summary = "여행 가방 개별 상세조회", description = "가방을 상세 조회합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@Parameter(name = "bagId", description = "가방 Id, Path Variable 입니다.")
	@GetMapping("/members/bags/{travelPlanId}/{bagId}/detail)")
	public ApiResponse<GetBagDetailInfo> getBagDetail(
		@PathVariable(value = "travelPlanId") Long travelPlanId,
		@PathVariable(value = "bagId") Long bagId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(bagService.getBagDetail(travelPlanId, bagId, member));
	}

	@Operation(summary = "내 가방 목록 상세 조회", description = "내 여행 계획에 해당하는 가방 목록을 상세 조회합니다.")
	@Parameter(name = "travelPlanId", description = "여행 계획 Id, Path Variable 입니다.")
	@GetMapping("/members/bags/{travelPlanId}")
	public ApiResponse<GetBagListDetailInfo> getBagDetailListByTravelPlan(
		@PathVariable(value = "travelPlanId") Long travelPlanId, @CurrentUser Member member) {
		return ApiResponse.onSuccess(bagService.getBagDetailListByTravelPlan(travelPlanId, member));
	}

}
