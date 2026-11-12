package com.yahodadev.springai.section02;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "02-2. Prompt")
@RestController
@RequestMapping("/section02/")
public class Step3SystemDefaultController {
    private final ChatClient chatClient;

    public Step3SystemDefaultController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultSystem("너는 스터디 게시판의 도우미이다. 항상 한국어로 대답하고, 문장 구성은 최대 2문장이다.")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chap03")
    public String step03(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/chap03/override")
    public String step03_1(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .system("Answer in English, in one short sentence.")
                .user(question)
                .call()
                .content();
    }
}
