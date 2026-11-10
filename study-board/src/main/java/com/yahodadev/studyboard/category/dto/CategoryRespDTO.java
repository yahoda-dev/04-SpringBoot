package com.yahodadev.studyboard.category.dto;

import com.yahodadev.studyboard.category.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryRespDTO {
    @ToString.Exclude
    private Long id;
    private String name;

    public CategoryRespDTO(String name) {
        this.name = name;
    }

    public static CategoryRespDTO from(Category category) {
        return new CategoryRespDTO(category.getName());
    }
}
