package com.yahodadev.studyboard.post.service;

import com.yahodadev.studyboard.category.entity.Category;
import com.yahodadev.studyboard.category.repository.CategoryRepository;
import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.common.error.BusinessException;
import com.yahodadev.studyboard.common.error.ErrorCode;
import com.yahodadev.studyboard.member.entity.Member;
import com.yahodadev.studyboard.member.repository.MemberRepository;
import com.yahodadev.studyboard.post.dto.PostListItemResponseDTO;
import com.yahodadev.studyboard.post.dto.PostRequestDTO;
import com.yahodadev.studyboard.post.dto.PostRespDTO;
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
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;


    private Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_USER_NUMBER));
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_CATEGORY_NUMBER));
    }


    public PageResponseDTO<PostListItemResponseDTO> findPosts(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt", "id"));

        // Page로 래핑된 DTO Projection으로 받을 것
        Page<PostListItemResponseDTO> posts = postRepository.findPostPage(pageRequest);
        return PageResponseDTO.from(posts);
    }


    public Post submit(PostRequestDTO request) {
        Member member = getMemberById(request.getAuthorId());
        Category category = getCategoryById(request.getCategoryId());


        Post post = new Post(
                member,
                category,
                request.getTitle(),
                request.getBody(),
                request.getReferenceUrl(),
                LocalDateTime.now()
        );

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostRespDTO getById(Long postId) {
        Post post = getPostById(postId);
        return PostRespDTO.from(post);
    }

    public PostRespDTO modfiy(Long postId, PostRequestDTO request) {
        Post post = getPostById(postId);
        Category category = getCategoryById(request.getCategoryId());

        postRepository.modify(
                postId,
                request.getTitle(),
                request.getBody(),
                category,
                LocalDateTime.now());

        Post modifyPost = getPostById(postId);

        return PostRespDTO.from(modifyPost);
    }
}
