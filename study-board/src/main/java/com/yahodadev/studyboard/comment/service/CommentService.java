package com.yahodadev.studyboard.comment.service;


import com.yahodadev.studyboard.comment.dto.CommentReqDTO;
import com.yahodadev.studyboard.comment.dto.CommentRespDTO;
import com.yahodadev.studyboard.comment.dto.TestCommentRespDTO;
import com.yahodadev.studyboard.comment.entity.Comment;
import com.yahodadev.studyboard.comment.repository.CommentRepository;
import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.common.error.BusinessException;
import com.yahodadev.studyboard.common.error.ErrorCode;
import com.yahodadev.studyboard.member.entity.Member;
import com.yahodadev.studyboard.member.repository.MemberRepository;
import com.yahodadev.studyboard.post.entity.Post;
import com.yahodadev.studyboard.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /* 이미 PostService에 존재하는 아래 두 함수를 중복 코드로 작성하는 것이 좋을지
     *  PostService에 있는 두 함수를 static으로 선언하여 외부 접근이 가능하게 하되 결합도를 올리는 게 맞을지 고민임
     *  */

    private Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_USER_NUMBER));
    }

    private Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }


    public CommentRespDTO submit(Long postId, CommentReqDTO request) {
        Post post = getPostById(postId);
        Member member = getMemberById(request.getAuthorId());

        Comment comment = new Comment(
                post,
                member,
                request.getBody(),
                LocalDateTime.now()
        );

        return CommentRespDTO.from(commentRepository.save(comment));
    }

    public TestCommentRespDTO testSubmit(Long postId, Long commentId, CommentReqDTO request) {
        Post post = getPostById(postId);
        Member member = getMemberById(request.getAuthorId());
        Comment parentComment = getCommentById(commentId);

        Comment comment = new Comment(
                post,
                member,
                request.getBody(),
                LocalDateTime.now(),
                parentComment
        );

        return TestCommentRespDTO.from(commentRepository.save(comment));
    }

    public boolean delete(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
        commentRepository.flush();

        return commentRepository.existsById(commentId);
    }

    public CommentRespDTO modify(Long commentId, CommentReqDTO request) {
        Comment comment = getCommentById(commentId);
        commentRepository.modify(commentId, request.getBody(), LocalDateTime.now());

        return CommentRespDTO.from(getCommentById(commentId));
    }

    public PageResponseDTO<CommentRespDTO> findComments(Long postId, Pageable pageable) {
        Post post = getPostById(postId);

        Pageable pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt", "id")
        );

        Page<CommentRespDTO> comments = commentRepository.findCommentPage(postId, pageRequest);

        return PageResponseDTO.from(comments);
    }
}