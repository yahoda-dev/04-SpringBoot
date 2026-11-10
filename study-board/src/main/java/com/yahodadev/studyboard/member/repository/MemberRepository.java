package com.yahodadev.studyboard.member.repository;


import com.yahodadev.studyboard.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Query Method 방식
    boolean existsByEmail(String email);

    Member saveAndFlush(Member member);

    Optional<Member> findByEmail(String email);
}