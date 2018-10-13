package com.sln.dao.shop.read.seller;

import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerParkOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerApply;

@Repository
public interface SellerApplyReadDao {

    SellerApply get(java.lang.Integer id);

    /**
     * 以用户获取其商家申请
     * @param userid
     * @return
     */
    SellerApply getSellerApplyByUser(Integer userid);

    List<SellerApply> getSellerApplyByCondition(Map<String, Object> map);

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getSellerApplysCount(Map<String, String> queryMap);
    /**
     * 获取园区和业务
     * @return
     */
    List<Map<String,Object>> getAddresAndBusiness();



    /**
     * 根据条件和角色类型查询商家申请列表
     * @param queryMap
     * @return
     */
    List<SellerApply> getSellerApplysByRole(@Param("list")List<SellerParkOperation> list,@Param("queryMap") Map<String, String> queryMap,@Param("start")Integer start,@Param("size") Integer size);
    /**
     * 根据条件和角色类型查询count
     * @param queryMap
     * @return
     */
    Integer getSellerApplyCountByRole(@Param("queryMap") Map<String, String> queryMap,@Param("list") List<SellerParkOperation> list);
}
