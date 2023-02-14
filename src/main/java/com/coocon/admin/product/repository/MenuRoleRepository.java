package com.coocon.admin.product.repository;

import com.coocon.admin.product.entity.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRoleRepository  extends JpaRepository<MenuRole,Long> {
}
