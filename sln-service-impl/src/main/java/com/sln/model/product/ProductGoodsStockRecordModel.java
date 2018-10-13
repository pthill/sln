package com.sln.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.dao.shop.write.product.ProductGoodsStockRecordWriteDao;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductGoodsStockRecord;

@Component
public class ProductGoodsStockRecordModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductGoodsStockRecordModel.class);
    
    @Resource
    private ProductGoodsStockRecordWriteDao productGoodsStockRecordWriteDao;
    
    /**
     * 根据id取得货品库存记录表
     * @param  productGoodsStockRecordId
     * @return
     */
    public ProductGoodsStockRecord getProductGoodsStockRecordById(Integer productGoodsStockRecordId) {
    	return productGoodsStockRecordWriteDao.get(productGoodsStockRecordId);
    }
    
    /**
     * 保存货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     */
     public Integer saveProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord) {
     	this.dbConstrains(productGoodsStockRecord);
     	return productGoodsStockRecordWriteDao.insert(productGoodsStockRecord);
     }
     
     /**
     * 更新货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     */
     public Integer updateProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord) {
     	this.dbConstrains(productGoodsStockRecord);
     	return productGoodsStockRecordWriteDao.update(productGoodsStockRecord);
     }
     
     private void dbConstrains(ProductGoodsStockRecord productGoodsStockRecord) {
		productGoodsStockRecord.setSku(StringUtil.dbSafeString(productGoodsStockRecord.getSku(), false, 50));
		productGoodsStockRecord.setUpdateUserName(StringUtil.dbSafeString(productGoodsStockRecord.getUpdateUserName(), false, 50));
     }
     
     public Integer count(Map<String, String> queryMap) {
    	 return productGoodsStockRecordWriteDao.count(queryMap);
     }
     public List<ProductGoodsStockRecord> pageProductGoodsStockRecord(Map<String, String> queryMap, PagerInfo pager) {
         Integer start = 0, size = 0;
         if (pager != null) {
             start = pager.getStart();
             size = pager.getPageSize();
         }
         return productGoodsStockRecordWriteDao.page(queryMap, start, size);
     }
}