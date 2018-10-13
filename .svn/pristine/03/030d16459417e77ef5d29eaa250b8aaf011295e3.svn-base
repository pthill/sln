package com.sln.service.seller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.seller.SellerTransport;
import com.sln.vo.seller.TransportJson;

/**
 * 商家运费
 *                       
 * @Filename: ISellerTransportService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerTransportService {

    /**
     * 根据id取得卖家运费模板
     * @param  sellerTransportId
     * @return
     */
    ServiceResult<SellerTransport> getSellerTransportById(Integer sellerTransportId);

    /**
     * 保存卖家运费模板
     * @param  sellerTransport
     * @return
     */
    ServiceResult<Integer> saveSellerTransport(SellerTransport sellerTransport);

    /**
    * 更新卖家运费模板
    * @param  sellerTransport
    * @return
    */
    ServiceResult<Integer> updateSellerTransport(SellerTransport sellerTransport);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerTransport>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @param sellerId 
     * @return
     */
    ServiceResult<Boolean> del(Integer sellerId, Integer id);

    /**
     * 运费信息解析json数据
     * @param st
     * @return
     */
    ServiceResult<List<TransportJson>> assembleTransportInfo(SellerTransport st);

    /**
     * 取卖家的运费模板
     * @param id
     * @return
     */
    ServiceResult<List<SellerTransport>> getEffectTransportBySellerId(Integer sellerId);

    /**
     * 根据运费模板ID启用某个模板
     * @param sellerId
     * @param id
     * @param state 
     * @return
     */
    ServiceResult<Boolean> transportInUse(Integer id, Integer state);

    /**
     * 按照计算类型获取商家的运费模板
     * @param transportType1
     * @param sellerId
     * @return
     */
    ServiceResult<List<SellerTransport>> getTransportByTypeAndSellerId(Integer transportType,
                                                                       Integer sellerId);

    /**
     * 计算运费
     * @param product
     * @param productGoods
     * @param number
     * @param cityId
     * @return
     */
    ServiceResult<BigDecimal> calculateTransFee(Product product, ProductGoods productGoods,
                                                Integer number, Integer cityId);
}