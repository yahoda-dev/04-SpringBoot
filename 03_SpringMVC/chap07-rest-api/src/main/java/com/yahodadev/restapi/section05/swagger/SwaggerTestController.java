package com.yahodadev.restapi.section05.swagger;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

@Tag(name = "SWAGGER 테스트 컨트롤러",
        description = "Section05의 스웨거 테스트입니다.")
@RestController
@RequestMapping("/entity2/*")
public class SwaggerTestController {

    // DB가 없기 때문에 실습용 List로 대체함
    private List<UserDTO> users;

    public SwaggerTestController() {
        this.users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    /// 전체 유저 조회
    @Operation(summary = "모든 사용자 조회", description = "모든 사용자를 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "500",
                    description = "조회 실패",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        Map<String, Object> respMap = new HashMap<>(); // 1. Map 생성
        respMap.put("users", users); // 2. List에 있는 전체 데이터 저장
        ResponseMessage respMsg = new ResponseMessage(200, "조회 성공", respMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(respMsg, /*헤더 정보*/headers, /*상태 코드*/ HttpStatus.OK);
    }

    /// 특정 유저 상세 조회
    @GetMapping("/users/{userNo}") // 한 명만 조회할 거니까 Path Variable을 사용한다
    public ResponseEntity<ResponseMessage> findUserByNo(
            @PathVariable int userNo
    ) { // 사용할 PathVariable 명시
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = new UserDTO();

        // DB를 사용할 땐 쿼리문으로 처리하게 될 내용이다.
        for (UserDTO user : users) {
            if (user.getNo() == userNo) {
                foundUser = user;
                break;
            }
        }

        Map<String, Object> respMap = new HashMap<>();
        respMap.put("user", foundUser);

        // chaining(or builder) 패턴으로 구현하는 연습
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공", respMap));
    }

    /// 신규 유저 등록 후 해당 유저 정보 조회하는 API로 연결까지
    @PostMapping("/users")
    public ResponseEntity<?> registUser(
            @RequestBody UserDTO newUser
    ) {
        System.out.println("newUser = " + newUser);

        // 마지막 회원의 회원 번호
        int lastUserNo = users.get(users.size() - 1).getNo();
        int newUserNo = lastUserNo + 1;

        newUser.setNo(newUserNo); // 신규 회원 no 지정
        newUser.setEnrollAt(new Date()); // 신규 회원 가입 날짜 지정

        users.add(newUser); // 신규 회원 저장

        return ResponseEntity.created(URI.create("/entity/users/" + newUserNo)).build();
    }

    /// 기존 유저 정보 수정
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> updateUser(
            @PathVariable int userNo,
            @RequestBody UserDTO modifyUser
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        modifyUser.setNo(foundUser.getNo());
        modifyUser.setEnrollAt(foundUser.getEnrollAt());

        int index = users.indexOf(foundUser);
        users.set(index, modifyUser);

        Map<String, Object> respMap = new HashMap<>();
        respMap.put("users", users);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공", respMap));
    }

    /// 기존 유저 정보 삭제
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(
            @PathVariable int userNo
    ) {
        // 복습을 위한 Stream을 통한 유저 찾기
        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .toList()
                .get(0);

        users.remove(foundUser);

        return ResponseEntity.noContent().build();
    }

}
