package com.yahodadev.studyboard.comment.controller;


import com.yahodadev.studyboard.comment.dto.CommentReqDTO;
import com.yahodadev.studyboard.comment.dto.CommentRespDTO;
import com.yahodadev.studyboard.comment.service.CommentService;
import com.yahodadev.studyboard.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/*")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<?>> submit(
            @PathVariable Long postId,
            @RequestBody CommentReqDTO request
    ) {
        CommentRespDTO resp = CommentRespDTO.from(commentService.submit(postId, request));

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "댓글이 등록되었습니다.",
                        resp
                )
        );
    }


}
