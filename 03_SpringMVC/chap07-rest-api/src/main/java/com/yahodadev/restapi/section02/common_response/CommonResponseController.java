package com.yahodadev.restapi.section02.common_response;


import com.yahodadev.restapi.section02.responseEntity.ResponseMessage;
import com.yahodadev.restapi.section02.responseEntity.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/common-response/*")
public class CommonResponseController {
    // DB가 없기 때문에 실습용 List로 대체함
    private List<UserDTO> users;

    public CommonResponseController() {
        this.users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    // 유저 목록 전체 조회
    @GetMapping("/users")
    // UserDTO 객체를 List에 담아서 ApiResponse로 만들어서 ResponseEntitiy로 감싸서 반환하겠다.
    public ResponseEntity<ApiResponse<List<UserDTO>>> findAllUsers() {

        ApiResponse<List<UserDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "조회 성공",
                users
        );
        return ResponseEntity.ok(response);
    }

    // 특정 유저 상세 조회
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ApiResponse<UserDTO>> findUserByNo(@PathVariable int userNo) {
        UserDTO foundUser = new UserDTO();

        // DB를 사용할 땐 쿼리문으로 처리하게 될 내용이다.
        for (UserDTO user : users) {
            if (user.getNo() == userNo) {
                foundUser = user;
                break;
            }
        }

        ApiResponse<UserDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "조회 성공",
                foundUser
        );

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> healthCheck() {
        ApiResponse<Void> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "서버 동작함",
                null
        );

        return ResponseEntity.ok(response);
    }

}
