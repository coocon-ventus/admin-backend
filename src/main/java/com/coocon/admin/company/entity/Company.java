package com.coocon.admin.company.entity;

import com.coocon.admin.security.entity.Provider;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    private String type;

    @CreatedDate
    private LocalDateTime createdAt;
    @Builder
    Company(String name, String code, String type){
        this.name = name;
        this.code = code;
        this.type = type;
    }
}
