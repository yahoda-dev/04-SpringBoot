package com.yahodadev.restapi.section03.exception_enum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enum-errors/*")
public class EnumExceptionController {


    private final Map<Integer, String> users = Map.of(
            1, "홍길동",
            2, "유관순"
    );


    @GetMapping("/users/{userNo}")
    public ResponseEntity<Map<String, Object>> findUser(
            @PathVariable int userNo
    ) {
        if (userNo < 1) {
            throw new BusinessException(ErrorCode.INVALID_USER_NUMBER);
        }

        return ResponseEntity.ok().build();

    }
}
