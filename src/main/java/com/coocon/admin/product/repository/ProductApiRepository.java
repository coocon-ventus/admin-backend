package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.MenuRole;
import com.coocon.admin.product.entity.ProductApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductApiRepository extends JpaRepository<ProductApi,Long> {

}
