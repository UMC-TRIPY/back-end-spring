package com.example.tripy.global.common.response.code.status;

import com.example.tripy.global.common.response.code.BaseErrorCode;
import com.example.tripy.global.common.response.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    //일반 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    //멤버 관련
    _EMPTY_MEMBER(HttpStatus.CONFLICT, "MEMBER_001", "존재하지 않는 사용자입니다."),

    //인증 관련
    _EMPTY_JWT(HttpStatus.UNAUTHORIZED, "AUTH_001", "JWT가 존재하지 않습니다."),
    _INVALID_JWT(HttpStatus.UNAUTHORIZED, "AUTH_002", "유효하지 않은 JWT입니다."),

    //통화 관련
    _EMPTY_CURRENCY(HttpStatus.CONFLICT, "CUR_001", "환율정보가 존재하지 않습니다."),

    //나라 관련
    _EMPTY_COUNTRY(HttpStatus.CONFLICT, "CTR_001", "국가정보가 존재하지 않습니다."),

    //도시 관련
    _EMPTY_CITY(HttpStatus.NOT_FOUND, "CTY_001", "도시정보가 존재하지 않습니다."),

    // TravelPlan 관련
    _EMPTY_TRAVEL_PLAN(HttpStatus.NOT_FOUND, "TRAVEL_PLAN_001", "존재하지 않는 여행 계획입니다."),
    _ALREADY_TRAVEL_PLAN_BAG_EXISTS(HttpStatus.BAD_REQUEST, "TRAVEL_PLAN_002",
        "이미 가방이 존재하는 여행 계획입니다."),
    _FAULT_TRAVEL_PLAN_BAG_EXISTS(HttpStatus.BAD_REQUEST,"TRAVEL_PLAN_003",
		"여행 목록에 해당하는 가방 목록이 생성되지 않았습니다."),

    //S3 관련
    _FAULT_S3_KEY(HttpStatus.NOT_FOUND, "S3_001", "잘못된 S3 정보입니다."),

    //파일 업로드 관련
    _FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_001", "파일 업로드에 실패했습니다."),

    //태그 관련
    _EMPTY_TAG(HttpStatus.CONFLICT, "TAG_001", "태그가 존재하지 않습니다."),

    //게시글 관련
    _EMPTY_POST(HttpStatus.CONFLICT, "POST_001", "게시글이 존재하지 않습니다."),

    //가방 관련
    _EMPTY_BAG(HttpStatus.NOT_FOUND,"BAG_001","존재하지 않는 가방입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
            .message(message)
            .code(code)
            .isSuccess(false)
            .httpStatus(httpStatus)
            .build();
    }
}