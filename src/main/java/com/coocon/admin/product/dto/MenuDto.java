package com.coocon.admin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MenuDto {

    @Builder
    @Getter
    @ToString
    @AllArgsConstructor
    public static class sortedMenu{
        private Long id;
        private String name;
        private int depthNo;
        private String url;
        private String description;
        private String sortedOrder;
    }
}
