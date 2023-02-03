package com.coocon.admin.auth.oauth;

import com.coocon.admin.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        String accessToken = jwtProvider.createTokenByOAuth2User(oAuth2User);
        log.debug("[OAuth2SuccessHandler] token = [{}]",accessToken);

        String targetUrl = makeRedirectUrl(accessToken);
        log.debug("[OAuth2SuccessHandler] targetUrl = [{}]",targetUrl);
        getRedirectStrategy().sendRedirect(request,response,targetUrl);
    }
    // TODO .yml 에서 읽어올 수 있도록 수정 필요
    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/auth/redirect")
                .queryParam("token", token)
                .build().toUriString();
    }
    /*
    OAuth2SuccessHandler(String defaultTargetUrl){
        setDefaultTargetUrl(defaultTargetUrl);
    }
     */
}

