package com.yahodadev.requestmapping.chap01;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // Dispatcher Servlet이 처리를 위임하는 컨트롤러 클래스라는 의미
public class MethodMappingTestController {


    // Handler Method 작성
    @RequestMapping("/menu/regist")
    public String registMenu(Model model) {
        model.addAttribute("message", "신규 등록된 핸들러 메소드 호출!");

        return "mappingResult";
    }

    //@RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    @GetMapping(value = "/menu/modify")
    public String modifyMenu(Model model) {
        model.addAttribute("message", "GET 방식의 핸들러 메소드 호출!");

        return "mappingResult";
    }

}
