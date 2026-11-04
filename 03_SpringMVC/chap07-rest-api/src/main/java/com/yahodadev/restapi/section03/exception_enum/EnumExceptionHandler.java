package com.yahodadev.restapi.section03.exception_enum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.yahodadev.restapi.section03.exception_enum")
public class EnumExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse resp = ErrorResponse.from(ex);


        return ResponseEntity.status(errorCode.getStatus()).body(resp);
    }

}
