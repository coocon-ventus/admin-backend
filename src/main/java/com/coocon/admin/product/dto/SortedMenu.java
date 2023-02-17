package com.coocon.admin.product.dto;

import lombok.ToString;

/**
 * mapper for ProductMenuRepository
 */
public interface SortedMenu {
    Long getId();
    String getTitle();
    int getDepthNo();
    String getUrl();
    String getSortedOrder();
    String getDescription();
    String getType();
}
