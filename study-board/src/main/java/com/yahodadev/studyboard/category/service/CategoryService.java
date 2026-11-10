package com.yahodadev.studyboard.category.service;


import com.yahodadev.studyboard.category.dto.CategoryRespDTO;
import com.yahodadev.studyboard.category.entity.Category;
import com.yahodadev.studyboard.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        return categoryRepository.findAll().stream().toList();
    }
}
