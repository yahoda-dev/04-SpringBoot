package com.bear.studyboard.monitoring.section02.counter;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PostCreatedCounter {
    private final Counter counter;

    public PostCreatedCounter(MeterRegistry meterRegistry) {
        this.counter = Counter.builder("study.post.creation")
                .description("등록에 성공한 게시글 수")
                .register(meterRegistry);
    }

    public void increase() {
        counter.increment();
    }
}
