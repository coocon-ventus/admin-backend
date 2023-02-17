package com.coocon.admin.member.service;

import com.coocon.admin.member.entity.MemberProduct;
import com.coocon.admin.member.repository.MemberProductRepository;
import com.coocon.admin.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberProductService {
    private final MemberProductRepository memberProductRepository;

    public List<MemberProduct> getMemberProductByMemberId(Long id){
        return memberProductRepository.findByMember_id(id);
    }
}
