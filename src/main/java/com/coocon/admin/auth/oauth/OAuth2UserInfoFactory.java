package com.coocon.admin.auth.oauth;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(Provider provider, Map<String,Object> attributes){
        switch(provider){
            case GOOGLE:
                throw new IllegalArgumentException("Google OAuth provider is not support!");
            case COOCON:
                return new CooconOAuth2UserInfo(attributes);
            case KAKAO:
                return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
