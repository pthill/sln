package com.sln.service.product;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.seller.SellerTypeLogs;

public interface ISellerTypeLogsService {
    /**
    * 保存商家类型修改日志表
    * @param  sellerTypeLogs
    * @return
    */
    ServiceResult<Boolean> saveSellerTypeLogs(SellerTypeLogs sellerTypeLogs);

    /**
    * 更新商家类型修改日志表
    * @param  sellerTypeLogs
    * @return
    */
    ServiceResult<Boolean> updateSellerTypeLogs(SellerTypeLogs sellerTypeLogs);

    /**
    * 删除商家类型修改日志表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delSellerTypeLogs(Integer id);

    /**
    * 根据id取得商家类型修改日志表
    * @param sellerTypeLogsId
    * @return
    */
    ServiceResult<SellerTypeLogs> getSellerTypeLogsById(Integer sellerTypeLogsId);

    /**
     * 根据商品分类id获取商家类型修改日志
     * @param cateId
     * @return
     */
    ServiceResult<List<SellerTypeLogs>> getSellerTypeLogsByCateId(Integer cateId);

}