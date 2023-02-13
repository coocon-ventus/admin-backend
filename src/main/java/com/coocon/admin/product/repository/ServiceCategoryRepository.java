package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory,Long> {
    Optional<ServiceCategory> findByCode(String code);
    Optional<ServiceCategory> findByName(String name);
}
