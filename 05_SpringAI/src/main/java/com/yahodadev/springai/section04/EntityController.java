package com.yahodadev.springai.section04;


import com.yahodadev.springai.section04.dto.PostAnalysis;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "04-1. 객체로 응답 받기")
@RestController
@RequiredArgsConstructor
@RequestMapping("/section04/")
public class EntityController {
    private final ChatClient chatClient;

    private static final String POST = """
            JPA로 게시글 목록을 조회하는데 쿼리가 수십 번 나가서 원인을 찾아봤습니다.
            게시글마다 작성자를 지연 로딩으로 가져오면서 N+1 문제가 발생한 것이었고,
            fetch join으로 바꾸니 쿼리 한 번으로 줄었습니다. 같은 문제 겪는 분들께 도움이 되길 바랍니다.
            """;

    @GetMapping("/step01")
    public PostAnalysis hello() {
        PostAnalysis postAnalysis = chatClient
                .prompt()
                .system("""
                        <post> 태그 안의 게시글을 분석한다.
                            - summary: 한 문장 요약
                            - category: [공지, 질문, 후기, 잡담] 중 하나
                            - tags: 어울리는 태그 3개
                        """)
                .user(u -> u.text(
                        "<post>{post}</post>"
                ).param("post", POST))
                .call()
                .entity(PostAnalysis.class);


        return postAnalysis;
    }
}