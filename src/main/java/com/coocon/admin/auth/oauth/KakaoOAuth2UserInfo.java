package com.coocon.admin.auth.oauth;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    public KakaoOAuth2UserInfo(Map<String,Object> attributes){
        super(attributes);
        kakaoAttributes = (Map<String, Object>) attributes.get("properties");
    }
    private final Map<String,Object> kakaoAttributes;


    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {
        return (String) kakaoAttributes.get("nickname");
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getImageUrl() {
        return (String) kakaoAttributes.get("profile_image");
    }
}
