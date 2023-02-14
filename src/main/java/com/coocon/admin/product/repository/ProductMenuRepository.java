package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.ProductMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductMenuRepository extends JpaRepository<ProductMenu,Long> {
    Optional<ProductMenu> findByProduct_id(long productId);
}
