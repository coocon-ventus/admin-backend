package com.coocon.admin.member.service;

import com.coocon.admin.member.entity.Member;
import com.coocon.admin.security.util.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberAuthService {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public Authentication getAuthentication(String token){
        Claims claims = jwtProvider.parserJwtToken(token);
        log.debug("Claims = [{}]",claims.toString());
        Member member = memberService.getMemberById(Long.valueOf(claims.getId()));
        log.debug("JwtFilter member = [{}]",member);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(new String[]{claims.get("scope").toString()})
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(member,token,authorities);
    }

    public String createToken(Long id) {
        return jwtProvider.createToken(id, memberService.getMemberAuthorities(id));
    }

    public String createTokenByRefreshToken(String refreshToken){

        Claims claims = jwtProvider.parserJwtToken(refreshToken);
        Member member =memberService.getMemberById(Long.valueOf(claims.getId()));

        return createToken(member.getId());
    }
}
