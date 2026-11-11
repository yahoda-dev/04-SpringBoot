package com.yahodadev.studyboard.comment.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDTO {
    private Long authorId;
    private String body;
}
