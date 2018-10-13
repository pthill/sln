package com.sln.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerTransport;

@Repository
public interface SellerTransportWriteDao {

    SellerTransport get(java.lang.Integer id);

    Integer save(SellerTransport sellerTransport);

    Integer update(SellerTransport sellerTransport);

    Integer getCount(Map<String, Object> queryMap);

    List<SellerTransport> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    Integer updateInUseById(@Param("id") Integer id, @Param("state") Integer state);
}
