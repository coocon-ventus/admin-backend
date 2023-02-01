package com.coocon.admin.member;

import com.coocon.admin.auth.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberDto {

    @Getter
    @Builder
    public static class info{
        private Long id;
        private String name;
        private String userId;
        private String profileImage;
        private String email;
        private List<Role> role;
    }
}
