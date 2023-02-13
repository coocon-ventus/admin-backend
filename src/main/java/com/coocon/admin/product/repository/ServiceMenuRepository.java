package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.ServiceMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceMenuRepository extends JpaRepository<ServiceMenu,Long> {
    Optional<ServiceMenu> findByServiceCategory_id(long id);
}
