package com.coocon.admin.member.controller;

import com.coocon.admin.member.dto.LoginDto;
import com.coocon.admin.member.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Coocon OAuth controller
 * Create Authorization code when using ' * Create Authorization code when using ' mode
 * Issue token
 * Check token
 */

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginDto.response> login (@RequestBody LoginDto.reqeust loginRequest){

        log.debug("##Login Process Start##");
        log.debug(loginRequest.getEmail());
        log.debug(loginRequest.getPassword());

        return ResponseEntity.ok(loginService.login(loginRequest));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> login (@RequestBody Map<String,Object> logoutRequest){

        return ResponseEntity.ok("test");
    }
}
