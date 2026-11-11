package com.yahodadev.studyboard.comment.entity;

import com.yahodadev.studyboard.member.entity.Member;
import com.yahodadev.studyboard.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "study_post_id",
            nullable = false)
    private Post studyPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id",
            nullable = false)
    private Member author;

    @Column(name = "body",
            nullable = false,
            length = 1000)
    private String body;

    @Column(name = "created_at",
            nullable = false,
            updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            nullable = false)
    private LocalDateTime updatedAt;

    public Comment(Post studyPost, String body) {
        this.studyPost = studyPost;
        this.body = body;
    }

    public Comment(Post studyPost, Member author, String body, LocalDateTime createdAt) {
        this.studyPost = studyPost;
        this.author = author;
        this.body = body;
        this.createdAt = createdAt;
    }
}
