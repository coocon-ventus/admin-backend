package com.coocon.admin.member.service;

import com.coocon.admin.member.entity.MemberProduct;
import com.coocon.admin.member.entity.MemberRole;
import com.coocon.admin.member.repository.MemberProductRepository;
import com.coocon.admin.member.repository.MemberRoleRepository;
import com.coocon.admin.product.entity.Product;
import com.coocon.admin.product.entity.ProductMenu;
import com.coocon.admin.product.repository.ProductMenuRepository;
import com.coocon.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberMenuService {
    private final ProductService productService;
    private final MemberRoleRepository memberRoleRepository;

}
