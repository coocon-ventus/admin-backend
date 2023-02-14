package com.coocon.admin.product.entity;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    private String authority;
    private String description;
}
