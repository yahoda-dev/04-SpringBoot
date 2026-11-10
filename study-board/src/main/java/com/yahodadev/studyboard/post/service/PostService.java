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
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
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
        Member member = memberRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_USER_NUMBER)
        );

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_CATEGORY_NUMBER)
        );

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
}
