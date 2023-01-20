package com.coocon.admin.auth.oauth;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Coocon OAuth controller
 * Create Authorization code when using ' * Create Authorization code when using ' mode
 * Issue token
 * Check token
 */
@RestController
@Slf4j
public class CooconOAuthController {

    @GetMapping("/oauth/authorize")
    public ResponseEntity<?> checkCooconAuth (@RequestParam(name ="client_id")  String clientId
            , @RequestParam(name ="client_secret", required = false) String clientSecret
            , @RequestParam(name ="redirect_uri") String redirectUri){

        checkClientInfo(clientId, clientSecret);

        // TODO 인가 코드요청 시 react 화면으로 일부 파라미터와 redirect 해서 쿠콘 로그인 진행 로직 추가 필요

        // TODO 인가 코드 발급
        HttpHeaders headers= new HttpHeaders();
        headers.setLocation(URI.create(redirectUri));
        //ResponseEntity<T> responseEntity = new ResponseEntity<>(headers, HttpStatus.OK);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/oauth/token")
    public OAuth2AccessToken issueToken(){
        Set<String> scopes = new HashSet<String>();
        scopes.add("email");
        scopes.add("email");

        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                "tokenValue", Instant.now(),Instant.now().plus(1000, ChronoUnit.MILLIS),scopes);
        return oAuth2AccessToken;
    }


    private void checkClientInfo(String clientId, String clientSecret) {
        // TODO 클라이언트 인증 로직 필요
        log.debug("client-id = [{}] client-secret = []", clientId);
        //if(!clientId.equals("coocon-admin") || !clientSecret.equals("1234567890!@")){
        if(!clientId.equals("coocon-admin")){
            log.debug("BadCredentialsException");
            throw new BadCredentialsException("client id is invalid");
        }
    }
}
