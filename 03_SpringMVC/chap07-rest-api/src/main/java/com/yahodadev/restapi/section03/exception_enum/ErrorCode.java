package com.yahodadev.restapi.section03.exception_enum;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "회원을 찾을 수 없습니다."),
    INVALID_USER_NUMBER(HttpStatus.BAD_REQUEST, "INVALID_USER_NUMBER", "유효하지 않은 유저입니다."),
    DUPLICATE_USER_NAME(HttpStatus.CONFLICT, "DUPLICATE_USER_NAME", "이미 사용 중인 회원 이름입니다.");

    private final HttpStatus status;
    final String code;
    final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
