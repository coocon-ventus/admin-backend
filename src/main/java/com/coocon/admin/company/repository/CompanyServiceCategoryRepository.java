package com.coocon.admin.company.repository;

import com.coocon.admin.company.entity.CompanyServiceCategory;
import com.coocon.admin.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyServiceCategoryRepository extends JpaRepository<CompanyServiceCategory,Long> {
    List<CompanyServiceCategory> findBycompany_id(Long company_id);
    List<CompanyServiceCategory> findByservice_id(Long service_id);
}
