package com.coocon.admin.auth.token;


import com.coocon.admin.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="REFRESH_TOKEN")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", referencedColumnName = "id")
    private Member member;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(Member member, String value) {
        this.member = member;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}
