package com.coocon.admin.auth.oauth;

import com.coocon.admin.member.Member;
import com.coocon.admin.member.MemberRepository;
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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService  extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

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

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,user.getAttributes());
        Optional<Member> savedMember = memberRepository.findByUserId(userInfo.getId());
        Member member;
        if(savedMember.isPresent()){
            member = savedMember.get();
            if(provider != member.getProvider()){
                throw new OAuthProviderMissMatchException("Request provider type is ["+ provider
                        + "] use [" +member.getProvider() + "]");
            }

            updateMember(member,userInfo);
        }else{
            member = createMember(userInfo,provider);
        }

        return MemberPrincipal.create(member,user.getAttributes() );
    }

    private Member createMember(OAuth2UserInfo userInfo, Provider provider ){
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .userId(userInfo.getId())
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .provider(provider)
                .profileImage(userInfo.getImageUrl())
                .role(Role.USER)
                .build();

        return memberRepository.saveAndFlush(member);
    }

    private Member updateMember(Member member, OAuth2UserInfo userInfo){
        /*
        바뀔수 있는 정보에 대한 업데이트
         */

        return member;
    }
}
