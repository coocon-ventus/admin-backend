package com.coocon.admin.company.entity;

import com.coocon.admin.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @Builder
    public CompanyProduct(Company company, Product product){
        this.company = company;
        this.product = product;
    }

}
