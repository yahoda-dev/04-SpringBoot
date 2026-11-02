package com.yahodadev.exceptionhandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null") // 테스트 URL로 이동되게 하기 위함
    public String nullPointerException() {
        String str = null;
        System.out.println("NPE 발생을 위해 NULL이 저장된 문자열에 charAt시도 --> " + str.charAt(0));
        return "/"; // Main View로 이동 시키기 위함
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException excpetion) { // Exception 객체 받아오기
        System.out.println("Controller 레벨의 Exception 처리!");
        return "error/nullPointer"; // NPE 발생 시 이동시킬 View
    }

}
