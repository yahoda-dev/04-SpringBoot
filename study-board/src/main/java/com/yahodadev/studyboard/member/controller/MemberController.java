package com.yahodadev.studyboard.member.controller;

import com.yahodadev.studyboard.common.dto.ApiResponse;
import com.yahodadev.studyboard.member.dto.MemberAccountResponseDTO;
import com.yahodadev.studyboard.member.dto.RegistMemberRequestDTO;
import com.yahodadev.studyboard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    /// 회원 가입
    @PostMapping
    public ResponseEntity<ApiResponse<MemberAccountResponseDTO>> regist(
            @RequestBody RegistMemberRequestDTO request
    ) {
        MemberAccountResponseDTO resp = memberService.regist(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of(HttpStatus.CREATED, "회원 가입 성공", resp));
    }

    /// 로그인

}
