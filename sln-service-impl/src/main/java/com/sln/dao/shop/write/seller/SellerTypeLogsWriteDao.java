package com.sln.dao.shop.write.seller;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerTypeLogs;

@Repository
public interface SellerTypeLogsWriteDao {

    Integer insert(SellerTypeLogs sellerTypeLogs);

    Integer update(SellerTypeLogs sellerTypeLogs);

    Integer del(Integer id);

    SellerTypeLogs get(Integer id);

    List<SellerTypeLogs> getByCateId(Integer id);

}
