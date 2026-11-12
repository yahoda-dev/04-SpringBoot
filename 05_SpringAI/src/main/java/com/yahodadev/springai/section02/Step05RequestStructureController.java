package com.yahodadev.springai.section02;



import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "02-5. Request Structure")
@RestController
@RequestMapping("/section02")
public class Step05RequestStructureController {
    private final ChatModel chatModel;

    public Step05RequestStructureController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/step05")
    public String step05(
            @RequestParam String question
    ) {
        // 1. 역할 별 메시지 객체 생성
        SystemMessage systemMessage = new SystemMessage("너는 신입 개발자를 가르치는 강사이다. 답변은 2문장 이하로 한다.");
        UserMessage userMessage = new UserMessage(question);

        // 2. Message 목록을 하나의 Prompt
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        ChatResponse resp = chatModel.call(prompt);

        System.out.println("resp.getResult() = " + resp.getResult());
        System.out.println("resp.getResult().getOutput() = " + resp.getResult().getOutput());

        String answer = resp.getResult().getOutput().getText();


        return answer;
    }
}
