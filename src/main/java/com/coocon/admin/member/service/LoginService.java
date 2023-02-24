package com.coocon.admin.member.service;

import com.coocon.admin.member.dto.LoginDto;
import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.repository.MemberRepository;
import com.coocon.admin.security.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final MemberRepository memberRepository;
    private final MemberRoleService memberRoleService;
    private final JwtProvider jwtProvider;

    public LoginDto.response login(LoginDto.reqeust loginRequestDto){
        String email = loginRequestDto.getEmail();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        if(!member.getPassword().equals(loginRequestDto.getPassword())){
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }

        String accessToken =  jwtProvider.createToken(member.getId()
                ,memberRoleService.getMemberAuthorities(member.getId()));
        log.debug("ISSUE SUCCESS! token = [{}]", accessToken);

        return new LoginDto.response(accessToken);
    }
}
