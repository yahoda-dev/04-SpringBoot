package com.yahodadev.studyboard.member.repository;


import com.yahodadev.studyboard.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Query Method 방식
    boolean existsByEmail(String email);

    Member saveAndFlush(Member member);

    Optional<Member> findByEmail(String email);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            update Member member
            set member.withdrawnAt = :now
            where member.id = :memberId 
            """)
    void withdraw(Long memberId, LocalDateTime now);
}