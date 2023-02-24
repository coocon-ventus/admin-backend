package com.coocon.admin.product.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ProductMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    private String title;

    private Integer depthNo;

    private Long parentMenu;

    private String url;
    private String description;

    private int orderNo;
    private String type;
    private String icon;
    @Builder
    public ProductMenu(String title,
                       Integer depthNo, Long parentMenu,
                       String url, String description, int orderNo, String type){
        this.title= title;
        this.depthNo = depthNo;
        this.parentMenu = parentMenu;
        this.url = url;
        this.description = description;
        this.orderNo = orderNo;
        this.type = type;
    }
}
