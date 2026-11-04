package com.yahodadev.restapi.section04;

import com.yahodadev.restapi.section04.UserDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/hateoas/*")
public class HateoasTestController {
    private List<UserDTO> users;
    private final DtoModelAssembler assembler;

    public HateoasTestController(DtoModelAssembler assembler) {
        this.assembler = assembler;
        this.users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        // 사용자 목록을 EntityModel로 반환하기 위해 Assembler의 toModel 메소드 사용
        List<EntityModel<UserDTO>> usersWithRel = users.stream()
                .map(assembler::toModel)
                .toList();

        Map<String, Object> respMap = new HashMap<>();

        respMap.put("users", usersWithRel); // 2. List에 있는 전체 데이터 저장
        ResponseMessage respMsg = new ResponseMessage(200, "조회 성공", respMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(respMsg, /*헤더 정보*/headers, /*상태 코드*/ HttpStatus.OK);
    }


    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByUserNo(
            @PathVariable int userNo
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", StandardCharsets.UTF_8)
        );

        UserDTO foundUser = new UserDTO();

        // 일치하는 회원 찾기
        for (UserDTO user : users) {
            if (user.getNo() == userNo) {
                foundUser = user;
                break;
            }
        }

        // 응답 데이터
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        // 메서드 체이닝 + 빌더 패턴
        return ResponseEntity
                .ok() // 200 상태코드
                .headers(headers)
                .body(new ResponseMessage(200, "조회성공", responseMap));
    }
}
