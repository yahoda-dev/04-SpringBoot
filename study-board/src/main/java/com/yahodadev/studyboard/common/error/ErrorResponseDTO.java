package com.yahodadev.studyboard.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ErrorResponseDTO {
    private String code;
    private String detail;


    // Factory Pattern
    static ErrorResponseDTO of(ErrorCode error, String detail) {
        return new ErrorResponseDTO(error.name(), detail);
    }
}
