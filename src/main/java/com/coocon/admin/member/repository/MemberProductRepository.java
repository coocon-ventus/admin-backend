package com.coocon.admin.member.repository;


import com.coocon.admin.member.entity.MemberProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberProductRepository extends JpaRepository<MemberProduct,Long> {
    List<MemberProduct> findByMember_id(long id);
}
