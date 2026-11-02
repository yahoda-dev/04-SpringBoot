package com.yahodadev.exceptionhandler.controller;

import com.yahodadev.exceptionhandler.exception.MemberRegistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("/other-controller-null")
    public String otherControllerNullException() {
        String str = null;
        System.out.println(str.charAt(0));
        return "/";
    }

    @GetMapping("/other-controller-user")
    // 복습: 메서드에서 throw를 하면 해당 메서드를 호출한 곳에서 처리해야 한다
    public String userException2() throws MemberRegistException {
        boolean check = true;

        if(check) {
            throw new MemberRegistException("Other Controller User Exception 테스트입니다.");
        }

        return "/";
    }
}
