package com.coocon.admin.product.repository;

import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.entity.MenuRole;
import com.coocon.admin.product.entity.ProductMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRoleRepository  extends JpaRepository<MenuRole,Long> {

    List<MenuRole> findByProductRole_IdIn(List<Long> roldIdList);
}
