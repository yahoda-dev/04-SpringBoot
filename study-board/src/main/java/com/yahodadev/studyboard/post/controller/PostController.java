package com.yahodadev.studyboard.post.controller;


import com.yahodadev.studyboard.common.dto.ApiResponse;
import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.post.dto.PostListItemResponseDTO;
import com.yahodadev.studyboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/*")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDTO<PostListItemResponseDTO>>> findPosts(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        PageResponseDTO<PostListItemResponseDTO> postPage = postService.findPosts(pageable);
        return ResponseEntity.ok(ApiResponse.of(
                HttpStatus.OK,
                "게시물 목록 조회 완료",
                postPage
        ));
    }
}
