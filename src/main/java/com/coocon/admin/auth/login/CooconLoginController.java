package com.coocon.admin.auth.login;

import com.coocon.admin.auth.dto.LoginRequestDto;
import com.coocon.admin.auth.dto.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Coocon OAuth controller
 * Create Authorization code when using ' * Create Authorization code when using ' mode
 * Issue token
 * Check token
 */

@Slf4j
@RestController
public class CooconLoginController {

    @Autowired
    private CooconLoginService cooconLoginService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login (@RequestBody LoginRequestDto loginRequestDto){

        log.debug("##Login Process Start##");
        log.debug(loginRequestDto.getEmail());
        log.debug(loginRequestDto.getPassword());

        return ResponseEntity.ok(cooconLoginService.login(loginRequestDto));
    }
}
