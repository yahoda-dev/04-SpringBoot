package com.yahodadev.studyboard.comment.repository;

import com.yahodadev.studyboard.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            UPDATE Comment  comment
            SET comment.body = :body, comment.updatedAt = :updatedAt
            WHERE comment.id = :commentId
            """)
    void modify(Long commentId, String body, LocalDateTime updatedAt);
}
