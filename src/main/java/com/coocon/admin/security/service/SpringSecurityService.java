package com.coocon.admin.security.service;

import com.coocon.admin.member.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringSecurityService {

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Member getMemberFromAuthentication(){
        return (Member) getAuthentication().getPrincipal();
    }

    public List<GrantedAuthority> getGranteedAuthorites(){
        return (List<GrantedAuthority>) getAuthentication().getAuthorities();
    }
    public String getIdFromAuthentication(){
        return getAuthentication().getName();
    }
}
