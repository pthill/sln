package com.sln.model.product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.StringUtil;
import com.sln.dao.shop.read.product.ProductRegisterReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.product.ProductRegisterWriteDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.product.ProductRegister;
import com.sln.entity.seller.SellerParkOperation;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;


@Component
public class ProductRegisterModel {
	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductRegisterModel.class);
    @Resource
    private ProductRegisterWriteDao productRegisterWriteDao;
    @Resource
    private ProductRegisterReadDao productRegisterReadDao;
    @Resource
    private SystemAdminReadDao         systemAdminReadDao;
    @Resource
    private SystemRolesWriteDao        systemRolesWriteDao;
    /**
     * 根据id取得商品登记
     * @param  productRegisterId
     * @return
     */
    public ProductRegister getProductRegisterById(Integer productRegisterId) {
    	return productRegisterWriteDao.get(productRegisterId);
    }
    
    /**
     * 保存商品登记
     * @param  productRegister
     * @return
     */
     public Integer saveProductRegister(ProductRegister productRegister) {
     	this.dbConstrains(productRegister);
     	return productRegisterWriteDao.insert(productRegister);
     }
     
     /**
     * 更新商品登记
     * @param  productRegister
     * @return
     */
     public Integer updateProductRegister(ProductRegister productRegister) {
     	this.dbConstrains(productRegister);
     	return productRegisterWriteDao.update(productRegister);
     }
     
     private void dbConstrains(ProductRegister productRegister) {
		productRegister.setProductCode(StringUtil.dbSafeString(productRegister.getProductCode(), true, 50));
		productRegister.setProductName(StringUtil.dbSafeString(productRegister.getProductName(), true, 30));
		productRegister.setProductAddress(StringUtil.dbSafeString(productRegister.getProductAddress(), true, 50));
		productRegister.setStaffId(StringUtil.dbSafeString(productRegister.getStaffId(), true, 30));
		productRegister.setStaffName(StringUtil.dbSafeString(productRegister.getStaffName(), true, 20));
		productRegister.setProductRegiStat(StringUtil.dbSafeString(productRegister.getProductRegiStat(), true, 10));
		productRegister.setRetroactionReason(StringUtil.dbSafeString(productRegister.getRetroactionReason(), true, 10));
		productRegister.setMemberId(StringUtil.dbSafeString(productRegister.getMemberId(), true, 30));
     }

     /**
      * 获取缺货集合
      * @param queryMap
      * @param start
      * @param size
      * @return
      */
	public List<ProductRegister> getProductsRegister(Map<String, String> queryMap, Integer start, Integer size) {
		return productRegisterReadDao.getProductRegisterPageList(queryMap, start, size);
	}

	//获取总记录条数
	public int getProductRegisterCount(Map<String, String> queryMap) {
	    String adminId=queryMap.get("adminId");
        SystemAdmin admin=systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        String state = queryMap.get("q_staffId");
        if(state!=null){
        	List<String> states = Arrays.asList(state.split(","));
        }
        	return productRegisterReadDao.getRegisterProductCount();
        }

	/**
	 * 简单获取总记录条数
	 * @return
	 */
	public int getProductRegisterCount() {
		return productRegisterReadDao.getRegisterProductCount();
	}

	/**
	 * 获取登记反馈信息集合
	 * @return
	 */
	public List<ProductRegister> getProductsRegi() {
		return productRegisterReadDao.getProductRegistert();
	}

	/**
	 * 根据当前用户id获取集合
	 * @param currentId
	 */
	public List<ProductRegister> getProductRegisterByCurrentId(Integer currentId) {
		return productRegisterReadDao.getProductRegisterByCurrentId(currentId);
	}

	public void updateProductStateById(Integer id) {
		productRegisterWriteDao.updateProductRegister(id);
		
	}

	public ProductRegister getMemberId(Integer id) {
		// TODO Auto-generated method stub
		return productRegisterReadDao.getMemberId(id);
	}

	public void updateProductStateByIdre(Map<String, Object> datamap) {
		productRegisterWriteDao.updateProductStateByIdre(datamap);
		
	}

	}
//}