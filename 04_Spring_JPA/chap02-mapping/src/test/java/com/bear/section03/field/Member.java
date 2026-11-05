package com.bear.section03.field;

import com.bear.section02.enum_type.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity(name="member_section03")
@Table(name="tbl_member_section03")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// JPA가 엔티티의 값을 어디를 통해 쓸지
// FIELD가 아닌 PROPERTY를 부여할 경우 GETTER로 접근한다는 의미
@Access(AccessType.FIELD)
public class Member {

    @Id
    @Column(name="member_no")
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    @Column(name="member_pwd")
    private String memberPwd;

    @Column(name="nickname")
    private String nickname;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="enroll_date")
    private Date enrollDate;

    @Column(name="member_role")
    @Enumerated(EnumType.STRING)
    private RoleType memberRole;

    @Column(name="status")
    private String status;


    public String getMemberId() {
        System.out.println("Gtter-getMemberId");
        return memberId;
    }

    public String getMemberPwd() {
        System.out.println("Gtter-getMemberPwd");
        return memberPwd;
    }

    public String getNickname() {
        System.out.println("Gtter-getNickname");
        return nickname;
    }
}