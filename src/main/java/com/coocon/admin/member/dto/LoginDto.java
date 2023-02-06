package com.coocon.admin.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reqeust {
        private String email;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response {

        private String accessToken;

    }

}
