package com.yahodadev.springai.section02;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "02-1. User Prompt 공부")
@RestController
@RequiredArgsConstructor
@RequestMapping("/section02/")
public class Step01UserPromptController {
    private final ChatClient chatClient;

    @GetMapping("/step01")
    public String step01(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/step02")
    public String step02(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .system("너는 초등학생을 가르치는 선생님이야. 쉬운 단어만 사용해서 3문장 이내로 답해.")
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/step02/expert")
    public String step02_expert(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .system("너는 10년차 백엔드 개발자야. 전문 용어를 사용해서 핵심만 3문장 이내로 답해")
                .user(question)
                .call()
                .content();
    }
}
