package com.bear.studyboard.monitoring.section02.counter;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCategoryCounter {
    private final MeterRegistry meterRegistry;

    public void increase(String categoryName) {
        Counter counter = Counter.builder("study.post.creation.by.category")
                .description("분류 별 등록에 성공한 게시글 구")
                .tag("category", categoryName)
                .register(meterRegistry);

        counter.increment();
    }

}
