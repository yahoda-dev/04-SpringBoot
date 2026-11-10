package com.yahodadev.studyboard.category.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정된 컬럼에 부여하는 어노테이션
    private Long id;

    @Column(name = "name",
            nullable = false,
            length = 50)
    private String name;
}
