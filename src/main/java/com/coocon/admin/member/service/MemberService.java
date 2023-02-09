package com.coocon.admin.member.service;

import com.coocon.admin.member.dto.LoginDto;
import com.coocon.admin.member.repository.MemberRoleRepository;
import com.coocon.admin.security.entity.CustomOAuth2User;
import com.coocon.admin.security.entity.Provider;
import com.coocon.admin.member.entity.MemberRole;

import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }
    private final MemberRoleRepository memberRoleRepository;
    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    public Member findByUserIdNonNull(String userId){
        return memberRepository.findByUserId(userId).orElseThrow();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id).orElseThrow();
    }



    public List<MemberRole> getMemberRoleList(Long id){
        return memberRoleRepository.findByMember_Id(id);
    }

}

