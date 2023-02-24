package com.coocon.admin.security.filter;

import com.coocon.admin.member.service.MemberRoleService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final static String AUTH_HEADER = "Authorization";
    private final MemberRoleService memberRoleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTH_HEADER);
        log.info("JwtTokenFilter uri = [{}]", request.getRequestURL().toString());

        if(token != null && !token.isEmpty()) {
            Authentication authentication = memberRoleService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else {
            log.info("token is null or empty");
         }

        filterChain.doFilter(request, response);
    }

}
