package com.yahodadev.studyboard.post.controller;


import com.yahodadev.studyboard.common.dto.ApiResponse;
import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.post.dto.PostListItemResponseDTO;
import com.yahodadev.studyboard.post.dto.PostRequestDTO;
import com.yahodadev.studyboard.post.dto.PostRespDTO;
import com.yahodadev.studyboard.post.entity.Post;
import com.yahodadev.studyboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
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

    @PostMapping
    public ResponseEntity<ApiResponse<PostRespDTO>> submit(
            @RequestBody PostRequestDTO request
    ) {
        PostRespDTO resp = postService.submit(request);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.CREATED,
                        "포스트 등록이 완료되었습니다.",
                        resp
                )
        );
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostRespDTO>> getById(
            @PathVariable Long postId
    ) {
        PostRespDTO resp = postService.getById(postId);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.CREATED,
                        "포스트 조회가 완료되었습니다.",
                        resp
                )
        );
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostRespDTO>> modify(
            @PathVariable Long postId,
            @RequestBody PostRequestDTO request
    ) {
        PostRespDTO resp = postService.modfiy(postId, request);
        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.CREATED,
                        "포스트 수정이 완료되었습니다.",
                        resp
                )
        );
    }

}
