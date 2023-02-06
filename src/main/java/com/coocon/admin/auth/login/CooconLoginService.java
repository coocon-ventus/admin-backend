package com.coocon.admin.auth.login;

import com.coocon.admin.auth.dto.LoginRequestDto;
import com.coocon.admin.auth.dto.JwtTokenDto;
import com.coocon.admin.auth.jwt.JwtProvider;
import com.coocon.admin.member.Member;
import com.coocon.admin.member.MemberRepository;
import com.coocon.admin.member.MemberRole;
import com.coocon.admin.member.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CooconLoginService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public JwtTokenDto login(LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        long userSeq =memberRepository.findByEmail(email).get().getId();

        String accessToken =  jwtProvider.createToken(userSeq, memberRoleRepository.findByMember_Id(userSeq)
                    .stream()
                    .map(data -> new SimpleGrantedAuthority(data.getRole().getAuthority()))
                    .collect(Collectors.toList())
        );
        log.debug("ACCESSTOKEN???");
        log.debug(accessToken);
        return JwtTokenDto.of(accessToken);
    }


    public List<GrantedAuthority> getMemberAuthorities(Long id){
        List<MemberRole> memberRoleList = memberRoleRepository.findByMember_Id(id);
        return memberRoleList.stream().map(memberRole-> new SimpleGrantedAuthority(memberRole.getRole().getAuthority()))
                .collect(Collectors.toList());
    }
}
