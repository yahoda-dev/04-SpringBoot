package com.yahodadev.studyboard.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// DTO Projection으로 SELECT 결과를 반환 받기 위한 DTO 클래스
public class PostListItemResponseDTO {

    private String authorName;
    private String title;
    private LocalDateTime createdAt;

}
