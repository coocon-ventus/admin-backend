package com.coocon.admin.member.repository;

import com.coocon.admin.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole,Long> {

    List<MemberRole> findByMember_Id(Long id);
}