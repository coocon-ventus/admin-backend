package com.coocon.admin.security.service;

import com.coocon.admin.member.service.MemberAuthService;
import com.coocon.admin.security.repository.CustomOAuth2UserFactory;
import com.coocon.admin.security.exception.OAuthProviderMissMatchException;
import com.coocon.admin.security.entity.Provider;
import com.coocon.admin.member.entity.Member;
import com.coocon.admin.member.service.MemberService;
import com.coocon.admin.security.entity.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService  extends DefaultOAuth2UserService {
    private final MemberAuthService memberAuthService;
    private final MemberService memberService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        printOAuthUserInfo(userRequest, oauth2User);

        try{
            return this.process(userRequest,oauth2User);
        } catch(AuthenticationException e){
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(),e.getCause());
        }
    }

    private void printOAuthUserInfo(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        log.debug("######## OAuth User Info ########");
        log.debug("access-token =[{}]", userRequest.getAccessToken().getTokenValue());
        log.debug("getName = [{}]", oauth2User.getName());
        log.debug("######## Authorities START ########");
        oauth2User.getAuthorities().stream().forEach(grantedAuthority -> {
            log.debug( grantedAuthority.getAuthority());
        });

        log.debug("######## Authorities END ########");
        log.debug("######## Attributes START ########");
        oauth2User.getAttributes().forEach((key, value) ->{
            log.debug("key = [{}] value = [{}]",key,value);
        });
        log.debug("######## Attributes END ########");


    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user){
        Provider provider = Provider.valueOf(userRequest.getClientRegistration()
                .getRegistrationId().toUpperCase());

        CustomOAuth2User customOAuth2User = CustomOAuth2UserFactory.getOAuth2UserInfo(provider,user);

        Optional<Member> savedMember = memberService.getMemberByEmail(customOAuth2User.getName());
        Member member;
        if(savedMember.isPresent()){
            member = savedMember.get();
            /*
            if(provider != member.getProvider()){
                throw new OAuthProviderMissMatchException("Request provider type is ["+ provider
                        + "] use [" +member.getProvider() + "]");
            }
             */

            updateMember(member,customOAuth2User);
        }else{
            member = memberAuthService.createMemberByOAuthUser(customOAuth2User,provider);
        }
        customOAuth2User.setId(member.getId());
        return customOAuth2User;
    }

    private Member updateMember(Member member, CustomOAuth2User customOAuth2User){
        /*
        바뀔수 있는 정보에 대한 업데이트
         */

        return member;
    }
}
