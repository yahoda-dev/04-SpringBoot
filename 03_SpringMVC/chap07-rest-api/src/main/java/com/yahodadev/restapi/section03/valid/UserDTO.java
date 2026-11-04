package com.yahodadev.restapi.section03.valid;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int no;
    @NotNull(message = "아이디는 반드시 입력해야합니다.")
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 8, max=20, message = "아이디는 8자이상, 20자 이하로 입력해야합니다.")
    // 정규표현식(regexp)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "아디는 영문자, 숫자, 밑줄(_)만 허용됩니다.")
    private String id;

    // 비밀번호는 8자 이상 30자 이하로 입력해야합니다.
    // 비밀번호는 대문자,소문자, 숫자, 특수문자가 각각 하나 이상 포함되어야 한다
    @Size(min = 8, max=30, message = "비밀번호는 8자이상, 30자 이하로 입력해야합니다.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^()_+\\-={}\\[\\]:;\"'<>,.?/\\\\|]).+$",
            message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 각각 최소 1개 이상 포함해야 합니다."
    )
    private String pwd;

    // 이름은 반드시 입력해야한다.
    // 2글자 이상 5글자 이하
    @NotNull(message = "이름은 반드시 입력해야합니다.")
    @Size(min = 2, max=5, message = "이름은 2자이상, 5자 이하로 입력해야합니다.")
    private String name;
    @Past(message = "가입일은 과거 날짜만 입력할 수 있습니다.")
//    @PastOrPresent(message = "과거 또는 현재")
//    @Future(message = "현재보다 미래")
//    @FutureOrPresent(message = "현재 또는 미래")
    private Date enrollAt;

    public UserDTO(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}


