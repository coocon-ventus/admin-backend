package com.coocon.admin.product.repository;

import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.dto.SortedMenu;
import com.coocon.admin.product.entity.ProductMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ProductMenuRepositoryTest {
    @Autowired
    ProductMenuRepository productMenuRepository;

    @Test
    void get_menu_list(){
        System.out.println("menu size " +  productMenuRepository.findAll().size());

    }

    @Test
    void success_findRecursiveByMenuIdList(){
        List<Long> idList = Arrays.asList(1L,2L,5L);

        List<SortedMenu> menuList =  productMenuRepository.findRecursiveByMenuIdList(idList);
        menuList.stream().forEach(
                menu-> System.out.println("id = " +menu.getId() + " sorted order = " + menu.getSortedOrder()));
    }
}