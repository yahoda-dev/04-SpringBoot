package com.yahodadev.studyboard.post.entity;


import com.yahodadev.studyboard.category.entity.Category;
import com.yahodadev.studyboard.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title",
            nullable = false,
            length = 200)
    private String title;

    @Column(name = "body",
            nullable = false,
            columnDefinition = "TEXT")
    private String body;

    @Column(name = "reference_url",
            length = 2048)
    private String referenceUrl;

    @Column(name = "created_at",
            nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            nullable = false)
    private LocalDateTime updatedAt;

    public Post(Member author, Category category, String title, String body, String referenceUrl, LocalDateTime createdAt) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.body = body;
        this.referenceUrl = referenceUrl;
        this.createdAt = createdAt;
    }
}
