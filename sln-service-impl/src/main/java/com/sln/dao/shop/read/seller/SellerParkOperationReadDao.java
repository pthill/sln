package com.sln.dao.shop.read.seller;

import com.sln.entity.seller.SellerParkOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerParkOperationReadDao {
    /**
     * 批量新增商家园区和业务管理方
     * @param sellerParkOperations
     * @return
     */
    Integer batchSave(@Param("list") List<SellerParkOperation> sellerParkOperations);


    /**
     * 批量删除
     * @param
     * @return
     */
    Integer deleteById(@Param("sellerId")Integer sellerId);

    /**
     * 查询当前商家属于那些园区的业务管理方
     * @param sellerId
     * @return
     */
    List<SellerParkOperation> getBySellerId(@Param("sellerId")Integer sellerId);

    List<SellerParkOperation> getSellerParkOperations(@Param("parkId") Integer parkId, @Param("operationId") Integer operationId);
}
