package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.seller.SellerCate;

public interface ISellerCateService {
    /**
    * 保存商家分类
    * @param  sellerCate
    * @return
    */
    ServiceResult<Boolean> saveSellerCate(SellerCate sellerCate);

    /**
    * 更新商家分类
    * @param  sellerCate
    * @return
    */
    ServiceResult<Boolean> updateSellerCate(SellerCate sellerCate);

    /**
    * 删除商家分类
    * @param  id
     * @param sellerId 
    * @return
    */
    ServiceResult<Boolean> delSellerCate(Integer id, Integer sellerId);

    /**
    * 根据id取得商家分类
    * @param sellerCateId
    * @return
    */
    ServiceResult<SellerCate> getSellerCateById(Integer sellerCateId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerCate>> pageSellerCate(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据pid查询商家分类
     * @param pid
     * @param sellerId
     * @return
     */
    ServiceResult<List<SellerCate>> getByPid(Integer pid, Integer sellerId);

    /**
     * 获得所有商家分类（显示状态），封装了分类的子分类
     * @param sellerId
     * @return
     */
    public ServiceResult<List<SellerCate>> getOnuseSellerCate(Integer sellerId);
}