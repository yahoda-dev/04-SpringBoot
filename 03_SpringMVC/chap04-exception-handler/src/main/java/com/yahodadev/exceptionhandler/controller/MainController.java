package com.yahodadev.exceptionhandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = {"/", "/main"}) // / 혹은 main URL로 요청이 올 경우 처리하기 위함
    public String main() {
        return "main";
    }
}
