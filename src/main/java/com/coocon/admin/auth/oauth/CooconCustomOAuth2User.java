package com.coocon.admin.auth.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class CooconCustomOAuth2User extends CustomOAuth2User {

    public CooconCustomOAuth2User(OAuth2User oauth2User){
        super(oauth2User, "id");
    }

    @Override
    public String getUserId() {
        return (String) getAttributes().get("user_id");
    }

    @Override
    public String getName() {
        return (String) getAttributes().get("name");
    }

    @Override
    public String getEmail() {
        return (String) getAttributes().get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) getAttributes().get("profile_image");
    }

}
