package com.coocon.admin.member;

import com.coocon.admin.auth.oauth.Provider;
import com.coocon.admin.auth.oauth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name ="MEMBER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member implements UserDetails {

    @JsonIgnore
    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    private String userId;

    private String name;

    //@Column(unique = true)
    private String email;

    private String password;

    private String profileImage;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    //계정만료 false = 만료
    private boolean accountNonExpired = true;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public Member(String userId, String password, boolean enabled,
                boolean credentialsNonExpired, boolean accountNonLocked
                ) {
        Assert.isTrue(userId != null && !"".equals(userId) && password != null,
                "Cannot pass null or empty values to constructor");
        this.userId = userId;
        this.password = password;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    @Builder
    Member(String userId, String password, String email, String name, Role role
            , Provider provider, String profileImage, boolean accountNonLocked, boolean credentialsNonExpired,
           boolean enabled){
        this.userId = userId;
        this.password = password;
        this.profileImage = profileImage;
        this.name = name;
        this.role = role;
        this.email = email;
        this.provider = provider;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
