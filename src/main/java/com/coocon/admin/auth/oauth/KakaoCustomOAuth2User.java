package com.coocon.admin.auth.oauth;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Setter
@Getter
public class KakaoCustomOAuth2User extends CustomOAuth2User {

    private final Map<String,Object> kakaoAttributes;
    private String nickname;

    public KakaoCustomOAuth2User(OAuth2User oauth2User) {
        super(oauth2User, "id");
        this.kakaoAttributes = oauth2User.getAttribute("properties");

        if(kakaoAttributes != null){
            this.nickname = (String) kakaoAttributes.get("nickname");
            // TODO 테스트 계정에서는 이메일을 받을 수 없음
            setEmail( (String) kakaoAttributes.get("nickname"));
            setImageUrl((String)kakaoAttributes.get("profile_image"));
        }
        setId(String.valueOf(getAttributes().get("id")));
    }

}
