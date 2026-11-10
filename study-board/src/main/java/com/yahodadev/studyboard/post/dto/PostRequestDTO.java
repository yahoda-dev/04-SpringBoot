package com.yahodadev.studyboard.post.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequestDTO {
    private String title;
    private String body;
    // TODO: 추후 UI에서 사용자가 선택한 카테고리를 ID로 변환하여 보내게 할 것
    private long categoryId;
    // TODO: 추후 로그인된 사용자의 ID를 전달하도록 수정할 것
    private long authorId;
    private String referenceUrl;

    public PostRequestDTO(String title, String body, long categoryId, long authorId) {
        this.title = title;
        this.body = body;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }
}
