package com.coocon.admin.auth.token;

import com.coocon.admin.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RefreshToken {

    @Id
    private String refreshToken;
    private String tokenType;

    @CreationTimestamp
    private LocalDateTime issuedAt;

    private long expiresIn;

    @ManyToOne
    @JoinColumn(name="seq")
    private Member member;


}
