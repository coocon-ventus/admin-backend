package com.coocon.admin.member.service;

import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.repository.MemberRepository;
import com.coocon.admin.security.entity.CustomOAuth2User;
import com.coocon.admin.security.entity.Provider;
import com.coocon.admin.security.util.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberAuthService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
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
    public Member createMemberByOAuthUser(CustomOAuth2User oauth2User, Provider provider){
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .userId(oauth2User.getUserId())
                .name(oauth2User.getName())
                .email(oauth2User.getEmail())
                .provider(provider)
                .profileImage(oauth2User.getImageUrl())
                .build();
        return memberRepository.saveAndFlush(member);
    }
    public String createToken(Long id) {
        return jwtProvider.createToken(id, getMemberAuthorities(id));
    }

    public String createTokenByRefreshToken(String refreshToken){

        Claims claims = jwtProvider.parserJwtToken(refreshToken);
        Member member =memberService.getMemberById(Long.valueOf(claims.getId()));

        return createToken(member.getId());
    }

    public List<GrantedAuthority> getMemberAuthorities(Long id){
        return memberService.getMemberRoleList(id).stream().map(memberRole-> new SimpleGrantedAuthority(memberRole.getRole().getAuthority()))
                .collect(Collectors.toList());
    }


}
