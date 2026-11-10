package com.yahodadev.studyboard.member.service;

import com.yahodadev.studyboard.common.error.BusinessException;
import com.yahodadev.studyboard.common.error.ErrorCode;
import com.yahodadev.studyboard.member.dto.LoginMemberRequestDTO;
import com.yahodadev.studyboard.member.dto.MemberAccountResponseDTO;
import com.yahodadev.studyboard.member.dto.RegistMemberRequestDTO;
import com.yahodadev.studyboard.member.entity.Member;
import com.yahodadev.studyboard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /* utils */
    private String encodeNewPassword(String password) {
        try {
            return passwordEncoder.encode(password);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("비밀번호는 UTF-8 기준 72바이트 이하여야 합니다.", ErrorCode.INVALID_INPUT);
        }
    }





    public MemberAccountResponseDTO regist(RegistMemberRequestDTO request) {
        // 회원 가입 시 중복 가입을 허용하지 않는다.
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER_EMAIL);
        }

        String passwordHash = encodeNewPassword(request.getPassword());
        System.out.println("passwordHash = " + passwordHash); // TODO: 지워야 함
        Member member = new Member(
                request.getEmail(),
                passwordHash,
                request.getNickname(),
                LocalDateTime.now()
        );
        Member savedMember = memberRepository.saveAndFlush(member);
        return MemberAccountResponseDTO.from(savedMember);
    }

    public MemberAccountResponseDTO login(LoginMemberRequestDTO request) {
        String email = request.getEmail();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.LOGIN_FAILED));

        // 탈퇴한 회원이라면
        if (member.getWithdrawnAt() != null) {
            throw new BusinessException(ErrorCode.MEMBER_WITHDRAWN);
        }

        if (passwordEncoder.matches(request.getPassword(), member.passwordHash())) {
            return MemberAccountResponseDTO.from(member);
        } else {
            throw new BusinessException("비밀번호가 일치하지 않습니다.", ErrorCode.LOGIN_FAILED);
        }
    }
}
