package com.sln.dao.shop.write.flash;

import org.springframework.stereotype.Repository;

import com.sln.entity.flash.LogActFlashSaleProduct;

@Repository
public interface LogActFlashSaleProductWriteDao {

    //    LogActFlashSaleProduct get(java.lang.Integer id);

    Integer insert(LogActFlashSaleProduct logActFlashSaleProduct);

    //    Integer update(LogActFlashSaleProduct logActFlashSaleProduct);

    //    Integer delete(java.lang.Integer id);

}