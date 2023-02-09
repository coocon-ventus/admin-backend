package com.coocon.admin.member.controller;


import com.coocon.admin.member.dto.MemberDto;
import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.service.MemberService;
import com.coocon.admin.security.service.SpringSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final SpringSecurityService springSecurityService;

    @GetMapping("/me")
    public MemberDto.info getMemberInfo(){
        Member member = springSecurityService.getMemberFromAuthentication();
        log.info("getMemberInfo result = [{}]", member.toString());
        return MemberDto.info.builder().name(member.getName())
                .id(member.getId()).build();
    }
}
