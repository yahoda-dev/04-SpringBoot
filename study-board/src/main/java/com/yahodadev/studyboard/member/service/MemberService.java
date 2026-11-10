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

    /// 비밀번호 일치 여부 확인 후 Boolean을 반환하는 함수
    private boolean matchesPassword(String inputPassword, String passwordHash) {
        System.out.println("inputPassword = " + inputPassword);
        if(!passwordEncoder.matches(inputPassword, passwordHash)) {
            return false;
        }
        return true;
    }


    public MemberAccountResponseDTO regist(RegistMemberRequestDTO request) {
        String email = request.getEmail();

        // 회원 가입 시 중복 가입을 허용하지 않는다.
        if (memberRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER_EMAIL);
        }

        if (!email.contains("@")) {
            throw new BusinessException(ErrorCode.INVALID_EMAIL);
        }


        String passwordHash = encodeNewPassword(request.getPassword());
        System.out.println("passwordHash = " + passwordHash); // TODO: 지워야 함
        Member member = new Member(
                email.toLowerCase(),
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

        if(member.getWithdrawnAt() != null) {
            throw new BusinessException(ErrorCode.MEMBER_WITHDRAWN);
        }

        if(!matchesPassword(request.getPassword(), member.passwordHash())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        return MemberAccountResponseDTO.from(member);
    }

    public MemberAccountResponseDTO update(Long memberId, RegistMemberRequestDTO request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_USER_NUMBER));

        if(!matchesPassword(request.getPassword(), member.passwordHash())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        member.updateProfile(request.getEmail(), request.getNickname());

        return MemberAccountResponseDTO.from(member);
    }

    public MemberAccountResponseDTO withdraw(Long memberId, String password) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_USER_NUMBER));

        if(!matchesPassword(password.replace("\"", ""), member.passwordHash())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        memberRepository.withdraw(memberId, LocalDateTime.now());
        Member withdrawMember =  memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_USER_NUMBER));

        return MemberAccountResponseDTO.from(withdrawMember);
    }
}
