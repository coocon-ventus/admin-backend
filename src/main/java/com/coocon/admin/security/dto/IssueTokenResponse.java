package com.coocon.admin.security.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for OAuth controller issue token response
 */
@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IssueTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Set<String> scopes;

    private LocalDateTime issuedAt;

    private long expiresIn;

    @Builder
    public IssueTokenResponse(String accessToken,String refreshToken, String tokenType,
                              Set<String> scopes, long expiresIn){
        this.accessToken =  accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.scopes = scopes;
        this.expiresIn = expiresIn;
        this.issuedAt = LocalDateTime.now();
    }
}
