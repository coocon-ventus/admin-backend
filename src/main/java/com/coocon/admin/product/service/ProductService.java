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
    private final ProductMenuRepository ProductMenuRepository;
    private final MenuRoleRepository menuRoleRepository;



    public List<SortedMenu> getMenuListByRootMenu(List<Long> roleIdList){
        List<Long> rootMenuList = menuRoleRepository.findByProductRole_IdIn(roleIdList).stream()
                .map(menuRole -> menuRole.getProductMenu().getId())
                .collect(Collectors.toList());
        List<SortedMenu> menuList =  ProductMenuRepository.findRecursiveByMenuIdList(rootMenuList);
        return menuList;
    }

}
