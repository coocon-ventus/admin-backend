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

    private String name;

    private Integer depth;

    private Long parentMenu;

    private String url;
    private String description;

    @Column(name="menu_order")
    private int order;
    private String type;

    @Builder
    public ProductMenu(String name,
                       Integer depth, Long parentMenu,
                       String url, String description, int order, String type){
        this.name= name;
        this.depth = depth;
        this.parentMenu = parentMenu;
        this.url = url;
        this.description = description;
        this.order = order;
        this.type = type;
    }
}
