package com.yahodadev.studyboard.comment.controller;


import com.yahodadev.studyboard.comment.dto.CommentReqDTO;
import com.yahodadev.studyboard.comment.dto.CommentRespDTO;
import com.yahodadev.studyboard.comment.dto.TestCommentRespDTO;
import com.yahodadev.studyboard.comment.service.CommentService;
import com.yahodadev.studyboard.common.dto.ApiResponse;
import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        CommentRespDTO resp = commentService.submit(postId, request);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "댓글이 등록되었습니다.",
                        resp
                )
        );
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/test")
    public ResponseEntity<ApiResponse<?>> testSubmit(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentReqDTO request
    ) {
        TestCommentRespDTO resp = commentService.testSubmit(postId, commentId, request);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "댓글이 등록되었습니다.",
                        resp
                )
        );
    }


    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<PageResponseDTO<?>>> findComments(
            @PathVariable Long postId,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        PageResponseDTO<CommentRespDTO> commentPage = commentService.findComments(postId, pageable);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "댓글 조회 완료.",
                        commentPage
                )
        );
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long commentId
    ) {
        HttpStatus status;

        if (!commentService.delete(commentId)) {
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.REQUEST_TIMEOUT;
        }


        return ResponseEntity.status(status).build();
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<?>> modify(
            @PathVariable Long commentId,
            @RequestBody CommentReqDTO request
    ) {
        CommentRespDTO resp = commentService.modify(commentId, request);

        return ResponseEntity.ok().body(
                ApiResponse.of(
                        HttpStatus.OK,
                        "댓글 수정이 완료되었습니다.",
                        resp
                )
        );
    }


}
