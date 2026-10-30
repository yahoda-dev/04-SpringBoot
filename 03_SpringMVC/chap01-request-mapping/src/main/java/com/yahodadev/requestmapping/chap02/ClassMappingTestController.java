package com.yahodadev.requestmapping.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String registOrder(Model model) {
        model.addAttribute("message", "/order/regist 요청 결과!");
        return "mappingResult";
    }


    @RequestMapping(value = {"modify", "delete"}, method = {RequestMethod.POST})
    public String modifyAndDelete(Model model) {
        model.addAttribute("message", "/order/modify와 /order/delete 요청 결과!");
        return "mappingResult";
    }

    // Path Variable 실습
    @GetMapping("/detail/{orderNumber}")
    // public String selectOrderDetail(Model model, @PathVariable int orderNumber)
    public String selectOrderDetail(Model model, @PathVariable("orderNumber") int orderNumber) {
        model.addAttribute("message", orderNumber + "번의 주문을 상세 조회합니다.");
        return "mappingResult";
    }

    // 요청에 대한 예외 처리도 가능
    @RequestMapping
    public String anyRequest(Model model) {
        model.addAttribute("message", "그 외 모든 요청을 여기서 처리합니다.");
        return "mappingResult";
    }

}
