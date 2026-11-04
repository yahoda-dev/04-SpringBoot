package com.yahodadev.restapi.section03.valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// 해당 패키지의 예외만 잡기 위해 경로 지정
@RestControllerAdvice(basePackages = "com.yahodadev.restapi.section03.valid")
public class ValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {
        // 특정 필드에서 발생한 Valid Error 정보를 담은 객체
        FieldError error = ex.getBindingResult().getFieldError();
        Map<String, String> resp = Map.of(
                "field", error.getField(),
                "message", error.getDefaultMessage()
        );

        return ResponseEntity.badRequest().body(resp);
    }
}
