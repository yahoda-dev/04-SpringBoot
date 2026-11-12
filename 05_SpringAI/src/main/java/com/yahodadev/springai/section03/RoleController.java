package com.yahodadev.springai.section03;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "03-1. Role Set")
@RestController
@RequiredArgsConstructor
@RequestMapping("/section03/")
public class RoleController {
    private final ChatClient chatClient;

    @GetMapping("/step01/before")
    public String step01Before(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/step01/after")
    public String step01After(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                // 역할과 맥락을 system message에 전달
                .system("""
                        너는 백엔드 부트캠프 수업의 멘토야.
                        질문자는 JPA를 막 학습했고, 게시판 프로젝트를 만들고 있어.
                        게시판과 댓글로 예시를 들어서 설명해.
                        """)
                .user(question)
                .call()
                .content();
    }
}
