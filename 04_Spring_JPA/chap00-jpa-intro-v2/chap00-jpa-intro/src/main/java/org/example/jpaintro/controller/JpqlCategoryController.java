package org.example.jpaintro.controller;


import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.CategoryMenuCountProjection;
import org.example.jpaintro.dto.CategoryMenuStatisticsDTO;
import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories/*")
@RequiredArgsConstructor
public class JpqlCategoryController {

    private final MenuService menuService;

    /// 1. 카테고리 별 메뉴 개수와 평균 가격을 조회
    /// -> 메뉴 개수와 평균 가격은 엔티티 필드에 없기 때문에 새로 만들어 줄 것이다.
    // 전체조회
    @GetMapping("/menu-statistics")
    public List<CategoryMenuStatisticsDTO> findMenuStatistics()
    {
        return menuService.findCategoryStatistics();
    }

    /// 2. 카테고리 코드, 이름, 메뉴 개수를 조회
    @GetMapping("/menu-count")
    public List<CategoryMenuCountProjection> findMenuCounts()
    {
        return menuService.findCategoryMenuCounts();
    }
}
