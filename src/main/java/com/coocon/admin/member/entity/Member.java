package com.coocon.admin.member.entity;

import com.coocon.admin.company.entity.Company;
import com.coocon.admin.security.entity.Provider;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name ="MEMBER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @NotNull
    private String email;
    private String password;

    private LocalDateTime passwordChangedAt;
    private String nickname;

    private int loginFailCount;
    //계정만료 false = 만료

    private boolean accountNonLocked;
    private boolean enabled;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    private LocalDateTime lastLoginAt;

    public Member(String email, String password, boolean accountNonLocked
                ) {
        Assert.isTrue(email != null && !"".equals(email) && password != null,
                "Cannot pass null or empty values to constructor");
        this.email = email;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
    }

    @Builder
    Member(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.loginFailCount = 0;
        this.accountNonLocked = true;
        this.passwordChangedAt = LocalDateTime.now();
        this.lastLoginAt = LocalDateTime.now();
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /*
        아래 함수들은 사용하지 않음
     */

    /**
     * @deprecated use MemberService.getMemberAuthorities
     */
    @Override
    @Deprecated
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
