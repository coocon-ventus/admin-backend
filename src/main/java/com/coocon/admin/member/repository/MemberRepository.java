package com.coocon.admin.member.repository;

import com.coocon.admin.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
