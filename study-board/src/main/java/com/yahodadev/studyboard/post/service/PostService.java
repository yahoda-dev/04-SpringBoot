package com.yahodadev.studyboard.post.service;

import com.yahodadev.studyboard.common.dto.PageResponseDTO;
import com.yahodadev.studyboard.post.dto.PostListItemResponseDTO;
import com.yahodadev.studyboard.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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
}
