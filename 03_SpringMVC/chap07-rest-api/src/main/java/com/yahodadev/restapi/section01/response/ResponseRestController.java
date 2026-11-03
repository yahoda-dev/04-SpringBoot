package com.yahodadev.restapi.section01.response;


import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/response/*")
public class ResponseRestController {

    @GetMapping("hello")
    public String hello() {
        return "Hello, World";
    }

    @GetMapping("random")
    public int random() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("message")
    public Message getMessage() {
        return new Message(
                404, "Message입니당."
        );
    }

    @GetMapping("list")
    public List<String> getList() {
        return List.of(
                new String[]{"사과", "바나나", "수박"}
        );
    }

    @GetMapping("map")
    public Map<Integer, String> getMap() {
        List<Message> msgList = new ArrayList<>();
        msgList.add(new Message(200, "정상 응답"));
        msgList.add(new Message(404, "Page Not Found"));
        msgList.add(new Message(500, "개발자 잘못"));

        return msgList.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/static/images.png").readAllBytes();
    }

    @GetMapping("entity")
    public ResponseEntity<Message> getEntity() {
        //return ResponseEntity.ok(new Message(200, "응답 성공"));

        return ResponseEntity.status(404).body(new Message(200, "응답 성공"));
    }

}
