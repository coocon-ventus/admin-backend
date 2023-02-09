package com.coocon.admin.security.service;

import com.coocon.admin.member.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityService {

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Member getMemberFromAuthentication(){
        return (Member) getAuthentication().getPrincipal();
    }

    public String getIdFromAuthentication(){
        return getAuthentication().getName();
    }
}
