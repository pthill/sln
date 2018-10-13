package com.sln.model.supplier;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.supplier.SupplierReadDao;
import com.sln.dao.shop.write.supplier.SupplierWriteDao;
import com.sln.entity.supplier.Supplier;

/***
 * 
 *                       
 * @Filename: SupplierModel.java
 * @Version: 1.0
 * @Author: pangsm
 * @Email: anna_p@yeah.net
 *
 */
@Component(value = "supplierModel")
public class SupplierModel {
    @Resource
    private SupplierReadDao  supplierReadDao;
    @Resource
    private SupplierWriteDao supplierWriteDao;


    /***
     * 数据过滤
     * 根据商家id查看商家下的供应商列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<Supplier> getSupplierList(Map<String, String> queryMap, Integer start, Integer size) {
        return supplierReadDao.getSupplierList(queryMap, start, size);
    }

    /***
     * 数据过滤
     * 统计商家id查看商家下的供应商列表
     * @param queryMap
     * @return
     */
    public Integer getSupplierCount(Map<String, String> queryMap) {
        return supplierReadDao.getSupplierCount(queryMap);
    }

    /***
     * 保存供应商列表
     * @param supplier
     * @return
     */
    public boolean saveSupplier(Supplier supplier,Integer sellerId) {
        if (null == supplier) {
            throw new BusinessException("保存供应商失败，供应商信息为空");
        }
        if (StringUtil.isEmpty(supplier.getName())) {
            throw new BusinessException("保存供应商失败，供应商名称为空");
        }
        Integer hasName = supplierReadDao.isNameExist(supplier.getName(),sellerId);
        if (hasName > 0) {
            throw new BusinessException("保存供应商失败，供应商名称已经存在");
        }
        if ("0".equals(supplier.getSupplierType())) {
            Integer bySupplierTypeCount = supplierReadDao.isSellerTypeExist(supplier.getSellerId());
            if (bySupplierTypeCount > 0)
                throw new BusinessException("已有商家供应，请选其他类型供应商");
        }
        return supplierWriteDao.save(supplier) > 0;

    }


    public boolean update(Supplier supplier) {
        if (null == supplier)
            throw new BusinessException("修改供应商失败，供应商信息为空");
        if (StringUtil.isEmpty(supplier.getName()))
            throw new BusinessException("修改供应商失败，供应商名称为空");
        return supplierWriteDao.update(supplier) > 0;
    }
    public Supplier getById(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("无效的id");
        return supplierReadDao.getById(id);
    }

    public Boolean del(Integer id) {
        if (null == id || 0 == id) {
            throw new BusinessException("无效的id");
        }
        Supplier byId = supplierReadDao.getById(id);
        if (null == byId) {
            throw new BusinessException("删除供应商失败，该供应商不存在");
        }
        return supplierWriteDao.delete(id) > 0;
    }

    /***
     * 修改供应商启用和禁用状态
     * @param supplier
     * @return
     */
    public boolean updateSuppplier(Supplier supplier) {
        if (null == supplier)
            throw new BusinessException("修改启用供应商失败，供应商信息为空");
        return supplierWriteDao.update(supplier) > 0;
    }

    public Integer isNameExist(String name,Integer sellerId) {
        return supplierReadDao.isNameExist(name,sellerId);
    }

    public Integer isSellerTypeExist(Integer sellerId){
        return supplierReadDao.isSellerTypeExist(sellerId);
    }
    /***
     * 获取商家下面供应商
     * @param sellerId
     * @return
     */
    public List<Supplier> getSupplierBySellerId(Integer sellerId) {
        if (null == sellerId)
            throw new BusinessException("商家无效的id");
        List<Supplier> supplierBySellerId = supplierReadDao.getSupplierBySellerId(sellerId);
        if (supplierBySellerId == null){
            throw new BusinessException("供应商信息为空,请添加供应商");
        }
        return supplierBySellerId;
    }

}
