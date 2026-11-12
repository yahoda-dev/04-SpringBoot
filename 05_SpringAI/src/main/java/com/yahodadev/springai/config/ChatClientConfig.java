package com.yahodadev.springai.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Prompt를 만들고, LLM 모델을 호출하고, 응답을 받는 클래스
// 해당 AI 모델의 프로바이더 서버에 HTTP 요청을 보내고 응답을 받아온다.

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) { // Builder 패턴을 주입 받아서 객체를 반환한다.
        return builder.defaultAdvisors(new SimpleLoggerAdvisor()) // LLM에게 전송되는 값을 확인하기 위해 추가
                .build();
    }


}
