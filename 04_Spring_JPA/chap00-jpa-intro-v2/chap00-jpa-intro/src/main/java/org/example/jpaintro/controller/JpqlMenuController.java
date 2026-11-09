package org.example.jpaintro.controller;


import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.dto.PriceIncreaseRequestDTO;
import org.example.jpaintro.dto.PriceUpdateResponseDTO;
import org.example.jpaintro.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus/*")
@RequiredArgsConstructor
public class JpqlMenuController {

    private final MenuService menuService;

    // 전체조회
    @GetMapping
    public List<MenuResponseDTO> findAll() {
        return menuService.findAll();
    }

    @GetMapping("/categories/{categoryCode}")
    public List<MenuResponseDTO> findByCategory(
            @PathVariable Integer categoryCode
    ) {
        return menuService.findByCategory(categoryCode);
    }

    /// 가격 범위와 주문 가능 상태 검색
    /// 지정한 가격 범위에 포함되고 주문 가능 상태가 일치하는 메뉴 조회
    @GetMapping("/serach")
    public List<MenuResponseDTO> searchByPriceRange(
            @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice,
            @RequestParam String orderableStatus
    ) {
        return menuService.searchByPriceRange(minPrice, maxPrice, orderableStatus);
    }

    /// 메뉴명에 요청한 검색어가 포함된 메뉴를 조회한다.
    /// 조건 : JPQL로 작성
    @GetMapping("/search/name")
    public List<MenuResponseDTO> searchByMenuKeyword(
            @RequestParam String menuKeyword
    ) {
        return menuService.searchByMenuKeyword(menuKeyword);
    }


    /// JPQL (상관)서브 쿼리 실습
    /// 각 메뉴를 자신이 속한 카테고리의 평균 가격과 비교
    /// 해당 평균보다 비싼 메뉴만 카테고리와 함께 조회
    @GetMapping("/above-category-average")
    public List<MenuResponseDTO> findMenusAboveCategoryAverage(
    ) {
        return menuService.findMenusAboveCategoryAverage();
    }


    /// 카테고리 별 메뉴 가격 일괄 인상
    /// PATCH 메소드를 사용한다
    /// 지정한 카테고리의 모든 메뉴 가격을 요청 비율만큼 한 번에 인상시킨 후 변경된 갯수를 반환
    @PatchMapping("categories/{categoryCode}/price")
    public PriceUpdateResponseDTO increasesPriceByCategory(
            @PathVariable Integer categoryCode,
            @RequestBody PriceIncreaseRequestDTO request
    ) {
        return menuService.increasesPriceByCategory(categoryCode, request.getIncreaseRate());
    }
}
