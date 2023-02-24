package com.coocon.admin.member.repository;

import com.coocon.admin.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByEmail(String email);
    @Query("select o from Member o join fetch o.company where o.id = :id")
    Optional<Member> findById(Long id);
    boolean existsByEmail(String email);
}
