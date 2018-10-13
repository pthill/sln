package com.sln.dao.shop.write.pcseller;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcseller.PcSellerIndex;

@Repository
public interface PcSellerIndexWriteDao {

    PcSellerIndex get(java.lang.Integer id);

    Integer insert(PcSellerIndex pcSellerIndex);

    Integer update(PcSellerIndex pcSellerIndex);

    Integer delete(Integer id);
}