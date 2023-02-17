package com.coocon.admin.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.result.Output;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@WebFilter
@Slf4j

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("###### HTTP REQUEST INPUT - {}) ######",req.getRequestURI());

        printHeaders(req);
        printParameters(req);
        printBody(true,req.getInputStream());
        chain.doFilter(request, response);
        //ResponseWrapper res = (ResponseWrapper) response;
       // printBody(false,res.getContentInputStream());
        //log.info("###### HTTP RESPONSE OUTPUT status {} ######", response.getStatus());
    }

    private void printBody(boolean isInput, InputStream inputstream) throws IOException {
        byte[] content = StreamUtils.copyToByteArray(inputstream);

        String type = isInput? "REQUEST" : "RESPONSE" ;

        if (content.length > 0) {
            String contentString = new String(content);
            log.info("{} BODY= [{}]", type,contentString);
        }
    }

    private void printHeaders(HttpServletRequest req) {
        req.getHeaderNames().asIterator().forEachRemaining(
                header ->  log.debug("HTTP Header [{} : {}]",header, req.getHeader(header))
        );
    }

    private void printParameters(HttpServletRequest req) {
        req.getParameterMap().forEach((key, value) ->{
            log.debug("PARAMETER = [{} : {}]",key, value);
        });
    }
}
