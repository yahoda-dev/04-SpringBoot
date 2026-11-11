package com.yahodadev.studyboard.common.error;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    INVALID_USER_NUMBER(HttpStatus.BAD_REQUEST, "유효하지 않은 유저입니다."),
    DUPLICATE_USER_NAME(HttpStatus.CONFLICT, "이미 사용 중인 회원 이름입니다."),
    DUPLICATE_USER_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일입니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 요청을 처리하지 못 했습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "요청 입력을 확인해 주세요."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "로그인 실패."),
    INVALID_CATEGORY_NUMBER(HttpStatus.BAD_REQUEST, "카테고리 조회 실패."),
    MEMBER_WITHDRAWN(HttpStatus.CONFLICT, "탈퇴한 회원입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "포스트를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");

    private final HttpStatus status;
    final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
