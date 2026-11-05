package com.bear.section02.enum_type;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity(name="member_section02")
@Table(name="tbl_member_section02")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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


}