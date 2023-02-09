package com.coocon.admin.security.filter;

import com.coocon.admin.member.service.MemberAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final static String AUTH_HEADER = "Authorization";
    private final MemberAuthService memberAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTH_HEADER);


        log.info("JwtTokenFilter uri = [{}]", request.getRequestURL().toString());
        if(token != null && !token.isEmpty()){
            try{
                Authentication authentication = memberAuthService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (SecurityException | MalformedJwtException e) {
                log.info("토큰 만료");

                ObjectMapper mapper = new ObjectMapper();

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                ResponseStatusException responseStatusException = new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "invalid Token.");

                mapper.writeValue(response.getWriter(), responseStatusException);

                //request.setAttribute("exception", ExceptionCode.WRONG_TYPE_TOKEN.getCode());
            } catch (ExpiredJwtException e) {
                log.info("토큰 만료");

                ObjectMapper mapper = new ObjectMapper();

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                ResponseStatusException responseStatusException = new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

                mapper.writeValue(response.getWriter(), responseStatusException);
            } catch (UnsupportedJwtException e) {
            } catch (IllegalArgumentException e) {
            } catch (Exception e) {
                log.error("================================================");
                log.error("JwtFilter - doFilterInternal() 오류발생");
                log.error("token : {}", token);
                log.error("Exception Message : {}", e.getMessage());
                log.error("Exception StackTrace : {");
                e.printStackTrace();
                log.error("}");
                log.error("================================================");
                request.setAttribute("exception", "unknown Exception");
            }
        }
        else {
            log.info("token is null or emtpy");
        }

        filterChain.doFilter(request, response);
    }
}
