package com.coocon.admin.product.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MenuRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id", referencedColumnName = "id")
    private ProductMenu productMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private ProductRole productRole;
}
