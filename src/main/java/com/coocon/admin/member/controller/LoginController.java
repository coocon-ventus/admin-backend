package com.coocon.admin.member.controller;

import com.coocon.admin.common.error.ErrorResponse;
import com.coocon.admin.member.dto.LoginDto;
import com.coocon.admin.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Coocon OAuth controller
 * Create Authorization code when using ' * Create Authorization code when using ' mode
 * Issue token
 * Check token
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalLoginInfo(IllegalArgumentException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("LOGIN_ERROR").errorMessage(e.getMessage()).build();

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDto.response> login (@RequestBody LoginDto.reqeust loginRequest){

        log.debug("##Login Process Start##");
        log.debug(loginRequest.getEmail());
        log.debug(loginRequest.getPassword());

        return ResponseEntity.ok(loginService.login(loginRequest));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout (@RequestBody Map<String,Object> logoutRequest){
        return ResponseEntity.ok("test");
    }

}
