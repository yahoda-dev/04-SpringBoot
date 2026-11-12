package com.yahodadev.springai.section01;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01. Spring AI 시작하기") // 태그 앞에 있는 숫자를 기준으로 Swagger에 정렬이 된다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/section01/")
public class HelloAiController {
    private final ChatClient chatClient;

    @GetMapping("/hello")
    public String hello() {
        return chatClient
                .prompt()               // 프롬프트 요청을 작성하는 함수
                .system("답변 끝에 용이라고 붙여서 답변")
                .user("안녕")       // 유저 프롬프트를 작성하는 함수
                .call()                 // LLM에게 전송 후 답변을 기다리는 함수(call = 동기 방식이기 때문에)
                .content();             // 답변에서 응답 텍스트만 추출
    }
}
