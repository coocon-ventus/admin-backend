package com.coocon.admin.product.repository;

import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.dto.SortedMenu;
import com.coocon.admin.product.entity.ProductMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductMenuRepository extends JpaRepository<ProductMenu,Long> {
    Optional<ProductMenu> findByProduct_id(long productId);
    List<ProductMenu> findByProductIdIn(List<Long> productIds);


    @Query(value = "with recursive menus (id,name,depth_no,parent_menu,url,order_no,sorted_order,description) as (\n" +
            "select\n" +
            "id,name,depth_no,parent_menu,url, order_no,order_no || id as sorted_order,description \n" +
            "from product_menu\n" +
            "where id in (:idList) and depth_no = 0\n" +
            "union all\n" +
            "select\n" +
            "b.id, b.name,b.depth_no ,b.parent_menu,b.url, b.order_no" +
            ",a.sorted_order || '-' ||b.order_no || b.id as sorted_order,b.description\n" +
            "from menus a\n" +
            "inner join product_menu b on\n" +
            "a.id = b.parent_menu\n" +
            "where\n" +
            "b.depth_no = a.depth_no + 1) \n" +
            "select id, name, depth_no as depthNo, url" +
            ", sorted_order as sortedOrder, description" +
            " from menus order by sortedOrder", nativeQuery = true)
    List<SortedMenu> findRecursiveByMenuIdList(@Param("idList") List<Long> idList);

}

