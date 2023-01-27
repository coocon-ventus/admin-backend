package com.coocon.admin.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Deprecated
 */
@Deprecated
@Component
public class OAuth2TokenFilter extends OncePerRequestFilter {

    private final static String TOKEN_HEADER = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO 토큰 값 추출 등 진행하는 util 작성 필요
        String headerTokenValue = request.getHeader(TOKEN_HEADER);
//        AuthToken token = tokenProvider.convertAuthToken(tokenStr);
        if(headerTokenValue != null && !headerTokenValue.isEmpty()){

        /*
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
         */
        }
        filterChain.doFilter(request, response);
    }
}
