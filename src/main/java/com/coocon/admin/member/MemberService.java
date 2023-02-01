package com.coocon.admin.member;

import com.coocon.admin.auth.oauth.CustomOAuth2User;
import com.coocon.admin.auth.oauth.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final MemberRoleRepository memberRoleRepository;
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
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
    public Member findByUserIdNonNull(String userId){
        return memberRepository.findByUserId(userId).orElseThrow();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id).orElseThrow();
    }

    public List<GrantedAuthority> getMemberAuthorities(Long id){
        List<MemberRole> memberRoleList = memberRoleRepository.findByMember_Id(id);
        return memberRoleList.stream().map(memberRole-> new SimpleGrantedAuthority(memberRole.getRole().getAuthority()))
                .collect(Collectors.toList());
    }



}

