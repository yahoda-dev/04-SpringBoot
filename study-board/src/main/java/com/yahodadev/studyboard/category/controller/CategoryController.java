package com.yahodadev.studyboard.category.controller;


import com.yahodadev.studyboard.category.dto.CategoryRespDTO;
import com.yahodadev.studyboard.category.entity.Category;
import com.yahodadev.studyboard.category.service.CategoryService;
import com.yahodadev.studyboard.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryRespDTO>>> getCategoryList() {
        List<Category> categories = categoryService.getCategoryList();
        List<CategoryRespDTO> resp = new ArrayList<>();

        for (Category category : categories) {
            resp.add(CategoryRespDTO.from(category));
        }


        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "카테고리 항목을 조회합니다.",
                        resp
                )
        );
    }
}
