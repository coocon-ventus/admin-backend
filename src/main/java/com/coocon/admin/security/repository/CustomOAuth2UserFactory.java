package com.coocon.admin.security.repository;

import com.coocon.admin.security.entity.CooconCustomOAuth2User;
import com.coocon.admin.security.entity.CustomOAuth2User;
import com.coocon.admin.security.entity.KakaoCustomOAuth2User;
import com.coocon.admin.security.entity.Provider;
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
