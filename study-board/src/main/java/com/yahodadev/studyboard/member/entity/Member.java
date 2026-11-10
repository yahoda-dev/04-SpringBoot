package com.yahodadev.studyboard.member.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",
            unique = true, // 중복 불가 정책
            length = 255
    )
    private String email;

    @Column(name = "password_hash",
            nullable = false,
            length = 255
    )
    @Getter(AccessLevel.NONE) // Getter로 접근 차단
    private String passwordHash;

    @Column(name = "nickname",
            nullable = false,
            length = 50
    )
    private String nickName;

    @Column(name = "joined_at",
            nullable = false,
            updatable = false)
    private LocalDateTime joinedAt;

    @Column(name = "withdrawn_at")
    private LocalDateTime withdrawnAt;


    public void updateProfile(String email, String nickName) {
        if (email != null) this.email = email;
        if (nickName != null) this.nickName = nickName;
    }

    public String passwordHash() {
        return passwordHash;
    }

    // 첫 회원가입 시 필요한 필드로 구성된 생성자
    public Member(String email, String passwordHash, String nickName, LocalDateTime joinedAt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickName = nickName;
        this.joinedAt = joinedAt;
    }
}
