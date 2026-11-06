package com.yahodadev.jpaintro.service;

import com.yahodadev.jpaintro.dto.MenuReqDTO;
import com.yahodadev.jpaintro.dto.MenuRespDTO;
import com.yahodadev.jpaintro.entity.Category;
import com.yahodadev.jpaintro.entity.Menu;
import com.yahodadev.jpaintro.repository.CategoryRepository;
import com.yahodadev.jpaintro.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<MenuRespDTO> findAllUsers() {
        // DB에서 조회하여 리스트에 저장(JpaRepository 상속하면 자동으로 findAll 함수 제공)
        List<Menu> menus = menuRepository.findAll();

        for (Menu menu : menus) {
            System.out.println("menu = " + menu);
        }

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
                getCategoryById(request.getCategoryCode()),
                request.getOrderableStatus()
        );

        // save: DB에 저장
        Menu savedMenu = menuRepository.save(menu);

        return MenuRespDTO.from(savedMenu);
    }

    @Transactional
    public MenuRespDTO update(Integer menucode, MenuRespDTO request) {
        // findById는 Optional을 반환하기 때문에 대응 함수까지 추가해야 함
        Menu findMenu = menuRepository.findById(menucode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."));

        // JPA에서 가장 중요한 영속성 컨텍스트
        findMenu.change(
                request.getMenuName(),
                request.getMenuPrice(),
                getCategoryById(request.getCategoryCode()),
                request.getOrderableStatus()
        );

        return MenuRespDTO.from(findMenu);
    }

    private Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. id=" + id));
    }
}