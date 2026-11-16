package com.bear.studyboard.monitoring.section02.summary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    private static final long MAX_SLEEP_MS = 5000;

    @GetMapping("/sleep")
    public String sleep(@RequestParam(defaultValue = "10") long ms) throws InterruptedException {
        long sleepMs = Math.max(0, Math.min(ms, MAX_SLEEP_MS));

        Thread.sleep(sleepMs);

        return sleepMs + "ms 동안 기다린 뒤 응답했습니다.";
    }
}