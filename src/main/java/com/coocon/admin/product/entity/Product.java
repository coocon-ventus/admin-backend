package com.coocon.admin.product.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
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

    @Builder
    Product(String name, String code){
        this.name = name;
        this.code = code;
    }
}
