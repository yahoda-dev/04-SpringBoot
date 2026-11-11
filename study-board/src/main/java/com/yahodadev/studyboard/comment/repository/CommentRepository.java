package com.yahodadev.studyboard.comment.repository;

import com.yahodadev.studyboard.comment.dto.CommentRespDTO;
import com.yahodadev.studyboard.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = """
                select new com.yahodadev.studyboard.comment.dto.CommentRespDTO(
               post.title, comment.body
                )
                from Comment comment
                join comment.studyPost post
                where comment.studyPost.id = :postId
            """)
    Page<CommentRespDTO> findCommentPage(Long postId, Pageable pageRequest);
}
