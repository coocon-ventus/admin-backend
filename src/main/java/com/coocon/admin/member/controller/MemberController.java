package com.coocon.admin.member.controller;


import com.coocon.admin.member.dto.MemberDto;
import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.service.MemberRoleService;
import com.coocon.admin.member.service.MemberService;
import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.dto.SortedMenu;
import com.coocon.admin.product.service.ProductService;
import com.coocon.admin.security.service.SpringSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRoleService memberRoleService;
    private final SpringSecurityService springSecurityService;
    private final ProductService productService;

    @GetMapping("/me")
    public MemberDto.info getMemberInfo(){
        MemberDto.info memberInfo = memberService.createMemberInfoByMember(springSecurityService.getMemberFromAuthentication());
        log.info("getMemberInfo result = [{}]", memberInfo.toString());
        return memberInfo;
    }

    @GetMapping("/menu/list")
    public List<SortedMenu> getMemberMenu(){
        Member member = springSecurityService.getMemberFromAuthentication();
        List<Long> memberRoleList =  memberService.getMemberRoleList(member.getId())
                .stream().map(memberRole -> memberRole.getId()).collect(Collectors.toList());

        return productService.getMenuListByRootMenu(memberRoleList);
    }


}
