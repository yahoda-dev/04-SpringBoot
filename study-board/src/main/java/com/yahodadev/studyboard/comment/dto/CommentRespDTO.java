package com.yahodadev.studyboard.comment.dto;


import com.yahodadev.studyboard.comment.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentRespDTO {
    private String postTitle;
    private String body;

    public static CommentRespDTO from(Comment comment) {
        return new CommentRespDTO(
                comment.getStudyPost().getTitle(),
                comment.getBody()
        );
    }
}
