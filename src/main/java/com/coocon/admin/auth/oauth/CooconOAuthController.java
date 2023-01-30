package com.coocon.admin.auth.oauth;

import com.coocon.admin.auth.token.IssueTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Coocon OAuth controller
 * Create Authorization code when using ' * Create Authorization code when using ' mode
 * Issue token
 * Check token
 */
@RestController
@Slf4j
public class CooconOAuthController {

    /**
     * 인가코드 발급
     * @param clientId :
     * @param redirectUri :
     * @param redirectUri
     * @return ResponseEntity<?>
     */
    @PostMapping("/oauth/authorize")
    public ResponseEntity<?> checkCooconAuth (
            @RequestParam(name ="client_id")  String clientId
            , @RequestParam(name ="redirect_uri") String redirectUri
            , @RequestParam(name ="state") String state){

        // TODO 클라이언트 인증 및 사용자 검증 로직 필요
        isValidClientInfo(clientId);
        //check(clientId);
        // TODO 인가 코드요청 시 react 화면으로 일부 파라미터와 redirect 해서 쿠콘 로그인 진행 로직 추가 필요
        HttpHeaders headers= new HttpHeaders();

        URI uri = UriComponentsBuilder.fromPath(redirectUri)
                .queryParam("code","")
                .queryParam("state",state).build().toUri();
        headers.setLocation(uri);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    private void isValidClientInfo(String clientId) {

        log.debug("client-id = [{}]", clientId);
        if(!clientId.equals("coocon-admin")){
            log.debug("BadCredentialsException");
            throw new BadCredentialsException("client id is invalid");
        }
    }

    /**
     * Token 발급 API
     * @return IssueTokenResponse
     */
    @GetMapping("/oauth/token")
    public IssueTokenResponse issueToken(){

        // TODO authrorization server 의 역할 더 상세히 구현 필요 spring security 쓸지 참조 필요
        // https://spring.io/projects/spring-authorization-server#support
        Set<String> scopes = new HashSet<String>();
        scopes.add("USER");
        scopes.add("dashboard");

        //OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
        //         String.valueOf(System.nanoTime()), Instant.now(),Instant.now().plus(1000, ChronoUnit.SECONDS),scopes);
        // TODO 사용자 auth 정보 확인하여 전달필요

        IssueTokenResponse accessToken = IssueTokenResponse.builder().accessToken(UUID.randomUUID().toString())
                .tokenType("BEARER")
                .refreshToken("refresh_token")
                .scopes(scopes)
                .expiresIn(600).build();
        return accessToken;
    }
}
