package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.ProductRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRoleRepository extends JpaRepository<ProductRole,Long> {


}
