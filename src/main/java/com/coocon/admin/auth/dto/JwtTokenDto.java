package com.coocon.admin.auth.dto;

import com.coocon.admin.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {

    private String accessToken;

    public static JwtTokenDto of(String token){
        return new JwtTokenDto(token);
    }

}
