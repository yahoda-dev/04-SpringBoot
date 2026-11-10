package com.yahodadev.studyboard.common.error;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    /// Error Code만 전달
    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /// Error Code와 Message 함께 전달
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
