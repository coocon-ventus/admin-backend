package com.coocon.admin.product.service;


import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.dto.SortedMenu;
import com.coocon.admin.product.entity.MenuRole;
import com.coocon.admin.product.entity.ProductMenu;
import com.coocon.admin.product.repository.MenuRoleRepository;
import com.coocon.admin.product.repository.ProductMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMenuRepository productMenuRepository;
    private final MenuRoleRepository menuRoleRepository;



    public List<Long> getMenuIdListByRoleIdList(List<Long> roleIdList){
        return menuRoleRepository.findByProductRole_IdIn(roleIdList).stream()
                .map(menuRole -> menuRole.getProductMenu().getId())
                .collect(Collectors.toList());
    }

    public List<SortedMenu> getSortedMenuListByRoleIdList(List<Long> roleIdList){
        List<Long> authedMenuList = getMenuIdListByRoleIdList(roleIdList);
        List<SortedMenu> menuList =  productMenuRepository.findRecursiveByMenuIdList(authedMenuList);
        return menuList;
    }

    public List<MenuDto.item> getMenuList(List<Long> roleIdList){

        List<Long> authedMenuList = getMenuIdListByRoleIdList(roleIdList);
        List<ProductMenu> productMenuList = productMenuRepository.findByIdInOrderByOrderNo(authedMenuList);
        return productMenuList.stream().map(MenuDto.item::new).collect(Collectors.toList());
    }

}
