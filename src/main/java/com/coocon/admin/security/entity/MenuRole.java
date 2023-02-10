package com.coocon.admin.security.entity;


import com.coocon.admin.company.entity.Company;
import com.coocon.admin.product.entity.ServiceCategory;
import com.coocon.admin.product.entity.ServiceMenu;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MenuRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id", referencedColumnName = "id")
    private ServiceMenu serviceMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role role;

    @Builder
    public MenuRole(ServiceMenu serviceMenu, Role role){
        this.role = role;
        this.serviceMenu = serviceMenu;
    }

}
