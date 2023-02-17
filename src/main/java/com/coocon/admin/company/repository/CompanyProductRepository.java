package com.coocon.admin.company.repository;

import com.coocon.admin.company.entity.CompanyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CompanyProductRepository extends JpaRepository<CompanyProduct,Long> {
    List<CompanyProduct> findByCompany_id(Long company_id);
    List<CompanyProduct> findByProduct_id(Long product_id);
}
