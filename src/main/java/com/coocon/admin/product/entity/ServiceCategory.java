package com.coocon.admin.product.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="MEMBER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    private String description;

    @Builder
    ServiceCategory(String name, String code, String description){
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
