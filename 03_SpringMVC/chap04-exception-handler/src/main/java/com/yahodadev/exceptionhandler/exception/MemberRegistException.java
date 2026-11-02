package com.yahodadev.exceptionhandler.exception;

public class MemberRegistException extends Exception {

    // 복습: 부모로 예외 메시지를 저장 가능한 생성자 오버라이딩
    public MemberRegistException(String message) {
        super(message);
    }
}
