package com.sln.service.product;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductPicture;

/**
 * 商城图片处理
 *                       
 * @Filename: IFrontProductPictureService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface IFrontProductPictureService {

    /**
     * 根据id取得商品对应图片表
     * @param  productPictureId
     * @return
     */
    ServiceResult<ProductPicture> getProductPictureById(Integer productPictureId);

    /**
     * 根据商品ID取得商品下面所有的图片
     * @param productId
     * @return
     */
    ServiceResult<List<ProductPicture>> getByProductIds(Integer productId);

    /**
     * 根据商品ID取得获取商品的主图
     * @param productId
     * @return
     */
    ServiceResult<ProductPicture> getproductLead(Integer productId);
    
    /**
     * 获取京东商品图片信息
     * @param token
     * @param sku
     * @return
     */
    ServiceResult<List<String>> getSkuImage(String token,String sku);
}