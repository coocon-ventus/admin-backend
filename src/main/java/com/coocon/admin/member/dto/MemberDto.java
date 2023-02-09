package com.coocon.admin.member.dto;

import com.coocon.admin.security.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberDto {

    @Getter
    @Builder
    public static class info{
        private Long id;
        private String name;
        private String profileImage;
        private String email;
        private List<Role> role;
    }
}
