package com.yahodadev.jpaintro.controller;


import com.yahodadev.jpaintro.dto.MenuReqDTO;
import com.yahodadev.jpaintro.dto.MenuRespDTO;
import com.yahodadev.jpaintro.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus") // URI는 복수형!
// 초기화가 필요한 필드만 생성자로 만들어주는 어노테이션
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    // 전체 조회
    @GetMapping("/users")
    public List<MenuRespDTO> findAllUsers() {
        return menuService.findAllUsers();
    }

    // 회원 가입
    @PostMapping("/users")
    public MenuRespDTO createUser(@RequestBody MenuReqDTO request) {
        return menuService.createUser(request);
    }
}
