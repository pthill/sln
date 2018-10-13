package com.sln.dao.shop.write.flash;

import org.springframework.stereotype.Repository;

import com.sln.entity.flash.ActFlashSale;

@Repository
public interface ActFlashSaleWriteDao {

    ActFlashSale get(java.lang.Integer id);

    Integer insert(ActFlashSale actFlashSale);

    Integer update(ActFlashSale actFlashSale);

    Integer delete(java.lang.Integer id);

    /**
     * 只修改活动状态、修改者信息
     * @param actFlashSale
     * @return
     */
    Integer updateStatus(ActFlashSale actFlashSale);

    /**
     * 取最后一个限时抢购
     * @return
     */
    ActFlashSale getByLast();

}