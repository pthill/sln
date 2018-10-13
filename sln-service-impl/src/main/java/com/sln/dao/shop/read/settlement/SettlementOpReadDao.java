package com.sln.dao.shop.read.settlement;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.settlement.SettlementOp;

@Repository
public interface SettlementOpReadDao {

    SettlementOp get(java.lang.Integer id);

    /**
     * 根据订单号获取结算网单
     * @param orderId
     * @return
     */
    List<SettlementOp> getByOrderId(Integer orderId);
}