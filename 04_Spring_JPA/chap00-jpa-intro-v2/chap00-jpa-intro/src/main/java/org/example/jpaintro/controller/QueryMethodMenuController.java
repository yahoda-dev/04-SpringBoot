package org.example.jpaintro.controller;


import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.MenuRequestDTO;
import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus/query-method")
@RequiredArgsConstructor // 생성 시점에 초기화가 필요한 필드들에 대한 생성자를 자동으로 생성해준다.
public class QueryMethodMenuController {
    private final MenuService menuService;


    /// 1. 요청한 주문 가능 상태와 일치하는 메뉴를 조회
    /// 조건: 가격이 낮은 메뉴부터 반환, 가격이 같을 경우 메뉴 코드 순으로 반환
    @GetMapping("/orderable")
    public List<MenuResponseDTO> findByOrderableStatus(
            @RequestParam String status
    ) {
        return menuService.findByOrderableStatus(status);
    }

    /// 2. 요청 가격 이상인 메뉴 조회
    /// 가격이 낮은 메뉴부터 반환하고, 가격이 같으면 메뉴 코드 순으로 반환
    @GetMapping("/price")
    public List<MenuResponseDTO> findByMinimumPrice(
            @RequestParam Integer price
    ) {
        return menuService.findByMinimumPrice(price);
    }

    /// 다중 조건 결합 실습
    /// 3. 주문 가능 상태가 요청 상태와 같고 요청 가격 이하인 메뉴를 조회
    /// 두 조건을 만족하는 메뉴만 가격 오름차순으로 정렬
    @GetMapping("/search")
    public List<MenuResponseDTO> findByStatusAndMaximumPrice(
            @RequestParam Integer price,
            @RequestParam String status
    ) {
        return menuService.findByStatusAndMaximumPrice(price, status);
    }


    /// 4. 요청한 카테고리에 속한 메뉴를 조회
    /// 조회 결과는 메뉴 코드 오름차순
    @GetMapping("/categories/{categoryCode}")
    public List<MenuResponseDTO> findByCategory(
            @PathVariable Integer categoryCode
    ) {
        return menuService.findByCategoryQueryMethod(categoryCode);
    }

    /// 5. 요청한 카테고리에 속한 메뉴를 조회
    /// 조회 결과는 메뉴 코드 오름차순
    @GetMapping("/category-name")
    public List<MenuResponseDTO> findByCategory_name(
            @RequestParam String categoryName
    ) {
        return menuService.findByCategoryNameQueryMethod(categoryName);
    }

    /// (실습) 메뉴 이름에 요청한 검색어가 포함된 메뉴를 조회
    /// (힌트) Containing 함수를 사용
    @GetMapping("/menu-name")
    public List<MenuResponseDTO> findByMenuName(
            @RequestParam String menuName
    ) {
        return menuService.findByMenuName(menuName);
    }

    @PutMapping("/{menuCode}")
    public MenuResponseDTO update(@PathVariable Integer menuCode, @RequestBody MenuRequestDTO menuRequestDTO) {

        return menuService.update(menuCode,menuRequestDTO);
    }

    // menu 저장
    @PostMapping
    public MenuResponseDTO create(@RequestBody MenuRequestDTO menuRequestDTO) {

        return menuService.create(menuRequestDTO);
    }
}
