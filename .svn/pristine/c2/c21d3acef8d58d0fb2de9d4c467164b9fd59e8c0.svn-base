package com.sln.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.entity.seller.SellerParkOperation;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.Seller;

@Repository
public interface SellerReadDao {

    Seller get(java.lang.Integer id);

    /**
     * 获取结算商家
     * @return
     */
    List<Seller> getSettlementSeller();

    /**
     * 根据用户ID获取商家
     * @param memberId
     * @return
     */
    Seller getByMemberId(@Param("memberId") Integer memberId);

    Integer getSellersCount(@Param("queryMap") Map<String, String> queryMap);

    List<Seller> getSellers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);
    Integer getSellersCountByRole(@Param("queryMap")Map<String, String> queryMap,
                                      @Param("list")List<SellerParkOperation> list);
    List<Seller> getSellersByRole(@Param("queryMap")Map<String,String> queryMap,
                                  @Param("list")List<SellerParkOperation> list,
                                  @Param("start")Integer start,@Param("size")Integer size);
    
    Seller getSellerByName(@Param("sellerName")String sellerName);

	//查询相应的需要的字段
	List<Seller> getSellEliminate(@Param("queryMap")Map<String, String> queryMap, @Param("start")Integer start, @Param("size")Integer size);

	//获取商家名与账户
	List<Seller> getSellerNameAndName(@Param("queryMap")Map<String, String> queryMap,@Param("start")Integer start,@Param("size")Integer size);

	//商户投诉根据商户名获取商家id
	Integer getSellerIdByName(@Param("complainSeller")String complainSeller);
	
	//根据商家id获取结算主体商家名称
	String getSubjectNameBySellerId(@Param("sellerId")Integer sellerId);
}
