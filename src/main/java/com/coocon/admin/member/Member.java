package com.coocon.admin.member;

import com.coocon.admin.auth.oauth.Provider;
import com.coocon.admin.auth.oauth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="MEMBER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

    @JsonIgnore
    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    private String userId;

    private String name;

    private String oauth2Id;

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


    @Builder
    Member(String userId, String password,String email,  String name, Role role
            ,Provider provider, String profileImage){
        this.userId = userId;
        this.password = password;
        this.profileImage = profileImage;
        this.name = name;
        this.role = role;
        this.email = email;
        this.provider = provider;
    }
}
