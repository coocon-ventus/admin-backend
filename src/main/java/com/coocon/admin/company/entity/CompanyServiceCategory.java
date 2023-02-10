package com.coocon.admin.company.entity;

import com.coocon.admin.product.entity.ServiceCategory;
import com.coocon.admin.security.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="serviceCategory_id", referencedColumnName = "id")
    private ServiceCategory serviceCategory;

    @Builder
    public CompanyServiceCategory(Company company, ServiceCategory serviceCategory){
        this.company = company;
        this.serviceCategory = serviceCategory;
    }

}
