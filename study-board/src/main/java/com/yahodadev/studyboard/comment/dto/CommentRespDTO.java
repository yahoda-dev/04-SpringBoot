package com.yahodadev.studyboard.comment.dto;


import com.yahodadev.studyboard.comment.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentRespDTO {
    private String postTitle;
    private String body;
    List<String> testComments;

    public static CommentRespDTO from(Comment comment) {
        return new CommentRespDTO(
                comment.getStudyPost().getTitle(),
                comment.getBody(),
                null
        );
    }

    public CommentRespDTO(String postTitle, String body) {
        this.postTitle = postTitle;
        this.body = body;
    }
}
