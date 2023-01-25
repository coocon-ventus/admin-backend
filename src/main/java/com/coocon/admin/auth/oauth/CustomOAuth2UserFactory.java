package com.coocon.admin.auth.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2UserFactory {
    public static CustomOAuth2User getOAuth2UserInfo(Provider provider, OAuth2User oAuth2User){
        switch(provider){
            case GOOGLE:
                throw new IllegalArgumentException("Google OAuth provider is not support!");
            case COOCON:
                return new CooconCustomOAuth2User(oAuth2User);
            case KAKAO:
                return new KakaoCustomOAuth2User(oAuth2User);
            default: throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
