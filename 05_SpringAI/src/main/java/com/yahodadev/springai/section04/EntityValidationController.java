package com.yahodadev.springai.section04;


import com.yahodadev.springai.section04.dto.PostAnalysis;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "04-2. Entity Validation")
@RestController
@RequiredArgsConstructor
@RequestMapping("/section04/")
public class EntityValidationController {
    private final ChatClient chatClient;

    private static final String POST = """
            JPA로 게시글 목록을 조회하는데 쿼리가 수십 번 나가서 원인을 찾아봤습니다.
            게시글마다 작성자를 지연 로딩으로 가져오면서 N+1 문제가 발생한 것이었고,
            fetch join으로 바꾸니 쿼리 한 번으로 줄었습니다. 같은 문제 겪는 분들께 도움이 되길 바랍니다.
            """;

    @GetMapping("/step02")
    public PostAnalysis hello() {
        PostAnalysis postAnalysis = chatClient
                .prompt()
                .user("""
                        스프링 AI의 구조화 응답 기능을 분석하여 요약, 카테고리 ,태그 3개를 만들어줘.
                        """)
                .call()
                .entity(PostAnalysis.class, spec -> spec.validateSchema());
        return postAnalysis;
    }
}