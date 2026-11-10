package com.yahodadev.studyboard.member.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistMemberRequestDTO {

    private String email;

    @ToString.Exclude
    private String password;

    private String nickname;

}
