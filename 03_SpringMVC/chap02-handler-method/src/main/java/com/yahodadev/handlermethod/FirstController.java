package com.yahodadev.handlermethod;


import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/first/*")
public class FirstController {


    @GetMapping("regist")
    public String regist() {

        return "first/regist";
    }

    @PostMapping("regist")
    public String registMen(Model model, WebRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        System.out.println("categoryCode = " + categoryCode);

        model.addAttribute("message", name + "을 신규메뉴목록의 " + categoryCode + " 번, 가격은 " + price + "원으로 추가했습니다.");
        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify() {

    }

    // @RequestParama 사용 연습
    // 요청 파라미터를 핸들러 메소드의 매개변수에 바인딩
    @PostMapping("modify")
    public String modifyMenu(
            Model model,
            @RequestParam(required = false) String modifyName,
            @RequestParam String modifyPrice) {
        String msg = modifyName + "을 " + modifyPrice + "원으로 수정했습니다.";
        model.addAttribute("message", msg);
        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search(){}

    @PostMapping("search")
    public String searchMenu(
            Model model,
            @ModelAttribute("menu") MenuDTO menuDTO // "menu": Model에 담기 위한 이름
            ) {
        return "first/searchResult";
    }

    @GetMapping("login")
    public void login(){}

    @PostMapping("login")
    public String loginSession(
            Model model,
            @RequestParam String id, @RequestParam String pwd,
            HttpSession session
    ) {
        session.setAttribute("id", id);
        session.setAttribute("pwd", pwd);
        return "first/loginResult";
    }

    @GetMapping("session-check")
    @ResponseBody
    public String sessionCheck(HttpSession session) {
        return session.getId() + " : " + session.getAttribute("id");
    }

    @GetMapping("logout")
    public String logoutTest(HttpSession session) {
        session.invalidate();
        return "first/loginResult";
    }

    @GetMapping("body")
    public void body() {}

    // Request Body 실습
    @PostMapping("body")
    public void bodyTest(
            @RequestBody String body,
            @RequestHeader("content-type") String contentType
    ) {
        System.out.println("body = " + URLDecoder.decode(body, StandardCharsets.UTF_8));
        System.out.println("contentType = " + contentType);
    }
}
