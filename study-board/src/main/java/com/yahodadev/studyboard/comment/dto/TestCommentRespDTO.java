package com.yahodadev.studyboard.comment.dto;


import com.yahodadev.studyboard.comment.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestCommentRespDTO {
    private String postTitle;
    private String parentComment;
    private String body;

    public static TestCommentRespDTO from(Comment comment) {
        return new TestCommentRespDTO(
                comment.getStudyPost().getTitle(),
                comment.getParent().getBody(),
                comment.getBody()
        );
    }
}
