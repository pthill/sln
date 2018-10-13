package com.sln.dao.shop.write.product;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sln.entity.product.ProductGoodsStockRecord;

@Repository
public interface ProductGoodsStockRecordWriteDao {
 
	ProductGoodsStockRecord get(java.lang.Integer id);
	
	Integer insert(ProductGoodsStockRecord productGoodsStockRecord);
	
	Integer update(ProductGoodsStockRecord productGoodsStockRecord);
	
	Integer count(@Param("param1") Map<String, String> queryMap);
	
	List<ProductGoodsStockRecord> page(@Param("param1") Map<String, String> queryMap, @Param("start") Integer start,
            @Param("size") Integer size);
}