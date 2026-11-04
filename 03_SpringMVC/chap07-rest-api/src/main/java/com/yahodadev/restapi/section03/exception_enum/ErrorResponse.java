package com.yahodadev.restapi.section03.exception_enum;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ErrorResponse {
    private int status;
    private String code;
    private String message;
    private String detail;

    // Factory Pattern
    public static ErrorResponse from(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return new ErrorResponse(
                errorCode.getStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                ex.getMessage());
    }
}
