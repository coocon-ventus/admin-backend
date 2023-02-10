package com.coocon.admin.product.entity;

import com.coocon.admin.company.entity.Company;
import com.coocon.admin.security.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ServiceMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="serviceCategory_id", referencedColumnName = "id")
    private ServiceCategory serviceCategory;

    private String name;

    private Integer depth;

    private Long parent_menu;

    private String url;

    private String description;

    @Builder
    public ServiceMenu(ServiceCategory serviceCategory, String name,
                       Integer depth, Long parent_menu,
                       String url, String description){

        this.serviceCategory = serviceCategory;
        this.name= name;
        this.depth = depth;
        this.parent_menu = parent_menu;
        this.url = url;
        this.description = description;
    }

}
