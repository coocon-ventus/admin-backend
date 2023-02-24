package com.coocon.admin.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;
    @NotNull
    private String name;

    private String baseUrl;

    @Builder
    Product(String name, String code){
        this.name = name;
        this.code = code;
    }

}
