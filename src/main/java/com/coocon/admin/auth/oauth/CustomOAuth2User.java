package com.coocon.admin.auth.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class CustomOAuth2User extends DefaultOAuth2User {

    public CustomOAuth2User(Map<String,Object> attributes,
                            Collection<GrantedAuthority> authorities,
                            String nameKey){
        super(authorities,attributes, nameKey);
    }

    private String id;
    private String email;
    private String imageUrl;

    public CustomOAuth2User(OAuth2User oAuth2User, String nameAttributeKey){
        super(oAuth2User.getAuthorities(),oAuth2User.getAttributes(), nameAttributeKey);

    }
}
