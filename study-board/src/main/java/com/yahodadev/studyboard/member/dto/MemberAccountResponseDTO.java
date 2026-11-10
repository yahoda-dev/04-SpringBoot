package com.yahodadev.studyboard.member.dto;


import com.yahodadev.studyboard.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
/// 회원 가입 후 응답에 사용할 DTO
public class MemberAccountResponseDTO {
    private long id;
    private String email;
    private String nickname;
    private LocalDateTime joinedAt;
    private LocalDateTime withdrawnAt;

    public static MemberAccountResponseDTO from(Member member) {
        return new MemberAccountResponseDTO(
                member.getId(),
                member.getEmail(),
                member.getNickName(),
                member.getJoinedAt(),
                member.getWithdrawnAt()
        );
    }

}
