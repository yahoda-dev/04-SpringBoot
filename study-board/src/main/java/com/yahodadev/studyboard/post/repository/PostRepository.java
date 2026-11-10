package com.yahodadev.studyboard.post.repository;

import com.yahodadev.studyboard.post.dto.PostListItemResponseDTO;
import com.yahodadev.studyboard.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {


    // Repository의 마지막 인자로 Pageable 객체를 넘기면 SpringData가 Paging 처리를 해준다.
    @Query(value = """
            select new com.yahodadev.studyboard.post.dto.PostListItemResponseDTO(
                        post.title, author.nickName, post.createdAt
            )
            from Post post
            join post.author author
            """)
    Page<PostListItemResponseDTO> findPostPage(Pageable pageRequest);
}
