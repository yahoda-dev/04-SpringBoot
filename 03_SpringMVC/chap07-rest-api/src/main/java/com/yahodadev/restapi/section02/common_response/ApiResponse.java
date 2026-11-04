package com.yahodadev.restapi.section02.common_response;


import lombok.*;

/// 모든 API에서 공통으로 사용할 응답 형식
/// @param <T> 응답 본문에 담을 데이터 타입

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    // 정적 팩토리 메서드를 구현할 것
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> failure(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }
}
