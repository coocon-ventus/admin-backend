package com.coocon.admin.member.controller;


import com.coocon.admin.member.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/me")
    public MemberDto.info getMemberInfo(){
        return MemberDto.info.builder().build();
    }

}
