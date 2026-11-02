package com.yahodadev.exceptionhandler.controller;

import com.yahodadev.exceptionhandler.exception.MemberRegistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException excpetion) { // Exception 객체 받아오기
        System.out.println("Global 레벨의 Exception 처리!");
        return "error/nullPointer"; // NPE 발생 시 이동시킬 View
    }

    @ExceptionHandler(MemberRegistException.class)
    public String registExceptionHandler(Model model, MemberRegistException exception) {
        System.out.println("Member Regist Exception 처리!");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex) {

        return "error/default";
    }
}
