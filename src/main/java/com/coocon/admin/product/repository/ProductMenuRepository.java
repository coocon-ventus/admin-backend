package com.coocon.admin.product.repository;

import com.coocon.admin.product.dto.MenuDto;
import com.coocon.admin.product.dto.SortedMenu;
import com.coocon.admin.product.entity.ProductMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductMenuRepository extends JpaRepository<ProductMenu,Long> {
    Optional<ProductMenu> findByProduct_id(long productId);
    List<ProductMenu> findByProductIdIn(List<Long> productIds);

    // using query for prevent N+1 problem
    @Query("select t from ProductMenu t join fetch t.product where t.id in (:idList) order by t.orderNo")
    List<ProductMenu> findByIdInOrderByOrderNo(List<Long> idList);

    /**
     * @deprecated replaced by findByIdInOrderByOrderNo
     * Select authed menu list by cte native query
     * @see #findByIdInOrderByOrderNo
     * @Since 0.1.0
     */
    @Deprecated
    @Transactional(readOnly = true)
    @Query(value = "with recursive " +
 //           "authed_menu (id,name,depth_no,parent_menu,url,order_no,description) as\n" +
 //           "(select id,name,depth_no,parent_menu,url,order_no,description \n" +
 //           "from product_menu where id in (:idList)),\n" +
            "menus (id,title,depth_no,parent_menu,url,order_no,sorted_order,type,description) as \n" +
            "(select id,title,depth_no,parent_menu,url, order_no,order_no || id as sorted_order,type,description \n" +
            "from product_menu where depth_no = 0 \n" +
            "union all \n" +
            "select b.id, b.title,b.depth_no ,b.parent_menu,b.url, b.order_no\n" +
            ",a.sorted_order || '-' ||b.order_no || b.id as sorted_order,b.type,  b.description \n" +
            "from menus a \n" +
            "inner join product_menu b \n" +
            "on a.id = b.parent_menu \n" +
            "where b.depth_no = a.depth_no + 1) \n" +
            "select id, title, depth_no as depthNo, url, sorted_order as sortedOrder, type,description \n" +
            "from menus where id in (:idList)\n" +
            "order by sortedOrder", nativeQuery = true)
    List<SortedMenu> findRecursiveByMenuIdList(@Param("idList") List<Long> idList);

}

