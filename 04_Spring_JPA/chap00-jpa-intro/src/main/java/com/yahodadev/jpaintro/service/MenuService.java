package com.yahodadev.jpaintro.service;

import com.yahodadev.jpaintro.dto.MenuReqDTO;
import com.yahodadev.jpaintro.dto.MenuRespDTO;
import com.yahodadev.jpaintro.entity.Menu;
import com.yahodadev.jpaintro.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<MenuRespDTO> findAllUsers() {
        // DB에서 조회하여 리스트에 저장(JpaRepository 상속하면 자동으로 findAll 함수 제공)
        List<Menu> menus = menuRepository.findAll();

        List<MenuRespDTO> result = menus.stream().map(
                menu -> MenuRespDTO.from(menu)
        ).toList();

        return result;
    }

    @Transactional // SQL 트랜잭션 개념과 동일. 여러 작업을 하나의 단위로 수행하는 것
    public MenuRespDTO createUser(MenuReqDTO request) {
        Menu menu = new Menu(
                request.getMenuName(),
                request.getMenuPrice(),
                request.getCategoryCode(),
                request.getOrderableStatus()
        );

        // save: DB에 저장
        Menu savedMenu = menuRepository.save(menu);

        return MenuRespDTO.from(savedMenu);
    }
}