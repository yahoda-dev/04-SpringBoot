package com.yahodadev.exceptionhandler.controller;

import com.yahodadev.exceptionhandler.exception.MemberRegistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/controller-user")
    // 복습: 메서드에서 throw를 하면 해당 메서드를 호출한 곳에서 처리해야 한다
    public String userException() throws MemberRegistException{
        boolean check = true;

        if(check) {
            throw new MemberRegistException("당신은 우리 서비스의 회원이 될 수 없습니다.");
        }

        return "/";
    }



    @ExceptionHandler(MemberRegistException.class)
    public String registExceptionHandler(Model model, MemberRegistException exception) {
        System.out.println("Member Regist Exception 처리!");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }

}
