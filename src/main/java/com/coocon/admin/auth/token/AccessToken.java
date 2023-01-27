package com.coocon.admin.auth.token;

import com.coocon.admin.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessToken {
    // TODO Token.class 륾 만들어서 상속하는 것에 대한 검토 필요

    private String accessToken;
    private String tokenType;
    private Set<String> scopes;

    private LocalDateTime issuedAt;

    private long expiresIn;

    @Builder
    public AccessToken(String accessToken,String tokenType,
                       Set<String> scopes,long expiresIn){
        this.accessToken =  accessToken;
        this.tokenType = tokenType;
        this.scopes = scopes;
        this.expiresIn = expiresIn;
        this.issuedAt = LocalDateTime.now();
    }
}
