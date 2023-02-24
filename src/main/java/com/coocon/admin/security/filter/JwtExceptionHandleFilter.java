package com.coocon.admin.security.filter;

import com.coocon.admin.common.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtExceptionHandleFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.error("JwtExceptionHandleFilter");
        try{
            filterChain.doFilter(request, response);
        }catch (SecurityException | MalformedJwtException e) {
            log.error("토큰 오류");
            setJwtErrorResponse(response, "Invalid Token");
        } catch (ExpiredJwtException e) {
            log.error("만료 토큰");
            setJwtErrorResponse(response, "Token expired");
        } catch (UnsupportedJwtException e) {
        }
        /*catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException");
            setJwtErrorResponse(response, "IllegalArgumentException");
            //setJwtErrorResponse(response, "IllegalArgumentException");
        }
        */
    }

    private void setJwtErrorResponse(HttpServletResponse response, String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = new ErrorResponse("TOKEN", message);
        mapper.writeValue(response.getWriter(), errorResponse);
    }
}
