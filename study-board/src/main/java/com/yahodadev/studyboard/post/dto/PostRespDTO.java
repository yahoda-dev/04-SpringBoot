package com.yahodadev.studyboard.post.dto;


import com.yahodadev.studyboard.post.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRespDTO {

    private String author;
    private String category;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    public static PostRespDTO from(Post post) {
        return new PostRespDTO(
                post.getAuthor().getNickName(),
                post.getCategory().getName(),
                post.getTitle(),
                post.getBody(),
                post.getCreatedAt()
        );
    }
}
