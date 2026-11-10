package com.yahodadev.studyboard.common.error;

import com.yahodadev.studyboard.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 공통 응답 모듈(ApiResponse)과 결합할 것임
 * */

@RestControllerAdvice(basePackages = "com.yahodadev.studyboard")
public class ApiExceptionHandler {


    // base package에 지정된 경로 내에서 BusinessException이 발생할 경우
    // 아래 핸들러 메소드가 호출되고 private으로 만든 errorResponse 함수를 호출하여 응답을 생성 후 반환한다.
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<ErrorResponseDTO>> handleBusinessException(BusinessException ex) {

        return errorResponse(ex.getErrorCode(), ex.getMessage());
        /* 현재는 실습을 위해 getMessage를 클라이언트 응답에 첨부하지만
        *  서버의 내부 정보를 클라이언트에게 전달하는 행위이기에 실무에서는
        *  Null을 보내거나 다른 메시지를 채우는 것이 바람직하다.
        *  */
    }

    // BusinessException을 제외한 모든 예외가 발생할 경우 처리하기 위한 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponseDTO>> handleException(Exception ex) {

        return errorResponse(ErrorCode.INTERNAL_ERROR, ex.getMessage());
    }

    // 에러 코드도 공통 응답 형식인 ApiResponse에 담아서 보내기 위한 응답 생성 메서드
    private ResponseEntity<ApiResponse<ErrorResponseDTO>> errorResponse(ErrorCode errorCode, String detail) {
        HttpStatus status = errorCode.getStatus();
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.of(errorCode, detail);
        return ResponseEntity.status(status)
                .body(ApiResponse.of(status, errorCode.getMessage(), errorResponseDTO));
    }
}
