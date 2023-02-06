package com.coocon.admin.auth.jwt;

import com.coocon.admin.member.service.MemberService;
import com.coocon.admin.security.util.JwtProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class JwtProviderTest {

    @Mock
    private static MemberService memberService;

    private static JwtProvider jwtProvider = new JwtProvider( memberService);
    private static Map<String, Object> attributes = new HashMap<String,Object>();
    private static Collection<? extends GrantedAuthority> authorities;

    @BeforeAll
    public static void beforeAll(){
        attributes.put("id","user");
        authorities= Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /*
    @Test
    void create_jwt_token_by_auth_user_success(){
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(authorities,attributes,"id");
        System.out.println(customOAuth2User.getName());

        String jwtToken = jwtProvider.createTokenByOAuth2User(customOAuth2User);
        System.out.println(jwtToken);
    }
*/
    @Test
    void valid_jwt_token_success(){

    }

    @Test
    void valid_jwt_token_failed(){

    }




}