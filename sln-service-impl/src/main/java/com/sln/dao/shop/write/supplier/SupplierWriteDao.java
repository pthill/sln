package com.sln.dao.shop.write.supplier;

import org.springframework.stereotype.Repository;

import com.sln.entity.supplier.Supplier;

/**
 * 
 *                       
 * @Filename: SupplierWriteDao.java
 * @Version: 1.0
 * @Author: pangsm
 * @Email: anna_p@yeah.net
 *
 */
@Repository
public interface SupplierWriteDao {
    /**
     * 保存供应商信息
     * @param supplier
     * @return
     */
    Integer save(Supplier supplier);

    /**
     * 修改供应商
     * @param supplier
     * @return
     */
    Integer update(Supplier supplier);


    Integer delete(Integer id);

}
