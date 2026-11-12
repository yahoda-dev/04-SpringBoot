package com.yahodadev.springai.section02;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "02-4. Prompt Template")
@RestController
@RequiredArgsConstructor
@RequestMapping("/section02")
public class Step04PromptTemplateController {
    private final ChatClient chatClient;

    @GetMapping("/step04")
    public String step04(
            @RequestParam String topic,
            @RequestParam String level
    ) {
        return chatClient
                .prompt()
                .system(
                        s -> s.text("너는 {level}에게 개발 용어를 설명하는 강사야. 3문장 이내로 설명해.")
                        .param("level", level)
                )
                .user(
                        s -> s.text("{topic}이(가) 무엇인지 설명해줘.")
                                .param("topic", topic)
                )
                .call()
                .content();
    }
}
