package com.example.tripy.global.common.response.exception;

import com.example.tripy.global.common.response.code.BaseErrorCode;
import com.example.tripy.global.common.response.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
