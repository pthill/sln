package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductPicture;

public interface IProductPictureService {
    /**
    * 保存商品对应图片表
    * @param  productPicture
    * @return
    */
    ServiceResult<Boolean> saveProductPicture(ProductPicture productPicture);

    /**
    * 更新商品对应图片表
    * @param  productPicture
    * @return
    */
    ServiceResult<Boolean> updateProductPicture(ProductPicture productPicture);

    /**
    * 删除商品对应图片表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductPicture(Integer id);

    /**
    * 根据id取得商品对应图片表
    * @param productPictureId
    * @return
    */
    ServiceResult<ProductPicture> getProductPictureById(Integer productPictureId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductPicture>> pageProductPicture(Map<String, String> queryMap,
                                                           PagerInfo pager);

    ServiceResult<List<ProductPicture>> getProductPictureByProductId(Integer productId);
}