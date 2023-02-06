package com.coocon.admin.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebFilter
@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("###### HTTP REQUEST INPUT - {}) ######",req.getRequestURI());
        
        printHeaders(req);
        printParameters(req);

   //     printInputBody(req);

        chain.doFilter(request, response);
        log.info("###### HTTP RESPONSE OUTPUT status {} ######", res.getStatus());
    }

    private void printInputBody(HttpServletRequest req) throws IOException {
//        InputStream inputStream = req.getInputStream();
//        String bodyJson = "";
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader br = null;
//        String line = "";
//
//        if(inputStream != null){
//            br = new BufferedReader(new InputStreamReader(inputStream));
//
//            //더 읽을 라인이 없을때까지 계속
//            br.lines().collect(Collectors.joining(System.lineSeparator()));
//            while ((line = br.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            log.debug("INPUT BODY= [{}]",stringBuilder.toString());
//        }
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
