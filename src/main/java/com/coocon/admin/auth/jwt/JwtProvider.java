package com.coocon.admin.auth.jwt;

import com.coocon.admin.auth.oauth.CustomOAuth2User;
import com.coocon.admin.member.Member;
import com.coocon.admin.member.MemberService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * JsonWebToken Util
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private final String secretKey = "12345678901234567890123456789012";
    private final MemberService memberService;


    /**
     * 헤더(heaer)-내용(payload)-서명(Signature)
     * JSON -> base64 encoding -> split by '.' character
     * 헤더 : 토큰 타입(JWT), 알고리즘(HS256-HmacSHA256)
     * 내용 : claims - 발급자, 발급시간, 만료시간, 토큰 제목
     * 서명 : BASE64(HmacSHA256(헤더.내용))
     * @param id
     * @param authorities
     * @return
     */
    public String createToken(Long id, Collection<? extends GrantedAuthority> authorities){

        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofMinutes(30).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setIssuer("COOCON")
                .setId(String.valueOf(id))
                .claim("scope",authorities)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public String createTokenByOAuth2User(CustomOAuth2User oauth2User){
        log.debug("oauth2User.getName = [{}], getId=  [{}]",oauth2User.getName(), oauth2User.getId());

        return createToken(oauth2User.getId(), oauth2User.getAuthorities());
    }


    public String createTokenByRefreshToken(String refreshToken){

        Claims claims = parserJwtToken(refreshToken);
        Member member =memberService.getMemberById(Long.valueOf(claims.getId()));

        return createToken(member.getId(), memberService.getMemberAuthorities(member.getId()));
    }

    /**
     * 중요 : parse 시 expiration Date 까지 검증
     * @param token
     * @return
     */
    public Claims parserJwtToken(String token){
        token = BearerRemove(token);
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token){
        Claims claims = parserJwtToken(token);
        log.debug("Claims = [{}]",claims.toString());
        Member member = memberService.getMemberById(Long.valueOf(claims.getId()));
        log.debug("JwtFilter member = [{}]",member);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(new String[]{claims.get("scope").toString()})
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(member,token,authorities);
    }

    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}
