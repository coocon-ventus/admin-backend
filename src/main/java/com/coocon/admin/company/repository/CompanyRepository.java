package com.coocon.admin.company.repository;

import com.coocon.admin.company.entity.Company;
import com.coocon.admin.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
