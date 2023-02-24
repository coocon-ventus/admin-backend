package com.coocon.admin.product.dto;

import com.coocon.admin.product.entity.ProductMenu;
import lombok.*;

public class MenuDto {

    @Builder
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class item{
        private Long id;
        private String title;
        private int depthNo;
        private String url;
        private String description;
        private Long parentMenu;
        private String icon;
        private String type;
        private int order;
        private Long productId;

        public item(ProductMenu productMenu){
            this.id = productMenu.getId();
            this.title = productMenu.getTitle();
            this.depthNo = productMenu.getDepthNo();
            this.url = productMenu.getUrl();
            this.description = productMenu.getDescription();
            this.parentMenu = productMenu.getParentMenu();
            this.icon = productMenu.getIcon();
            this.type = productMenu.getType();
            this.order = productMenu.getOrderNo();
            this.productId = productMenu.getProduct().getId();
        }
    }

}
