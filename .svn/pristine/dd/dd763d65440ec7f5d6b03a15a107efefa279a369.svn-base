package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductGoodsStockRecord;

public interface IProductGoodsStockRecordService {

	/**
     * 根据id取得货品库存记录表
     * @param  productGoodsStockRecordId
     * @return
     */
    ServiceResult<ProductGoodsStockRecord> getProductGoodsStockRecordById(Integer productGoodsStockRecordId);
    
    /**
     * 分页
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductGoodsStockRecord>> page(Map<String, String> queryMap,
                                                       PagerInfo pager);
    
    /**
     * 保存货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     */
     ServiceResult<Integer> saveProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord);
     
     /**
     * 更新货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     */
     ServiceResult<Integer> updateProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord);
}