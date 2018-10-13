package com.sln.service.product;
import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductRegister;
public interface IProductRegisterService {

	/**
     * 根据id取得商品登记
     * @param  productRegisterId
     * @return
     */
    ServiceResult<ProductRegister> getProductRegisterById(Integer productRegisterId);
    
    /**
     * 保存商品登记
     * @param  productRegister
     * @return
     */
     ServiceResult<Integer> saveProductRegister(ProductRegister productRegister);
     
     /**
     * 更新商品登记
     * @param  productRegister
     * @return
     */
     ServiceResult<Integer> updateProductRegister(ProductRegister productRegister);

     /**
      * 获取缺货商品列表集合
      * @param queryMap
      * @param pager
      * @return
      */
	ServiceResult<List<ProductRegister>> getProductsRegister(
			Map<String, String> queryMap, PagerInfo pager);

     List<ProductRegister> getProductRist();

     /**
      * 更据当前操作用户的id查询该会员下的相关的登记反馈情况集合
      * @param currentId
      * @return
      */
	List<ProductRegister> getProductRegisterByCurrentId(Integer currentId);

	//根据id更新审核状态 ---通过
	void updateProductStateById(Integer id);
    //更据id获取会员id
	ProductRegister getMemberIdByProRegiste(Integer id);
    //根据id更新审核状态----不通过
	void updateProductStateByIdre(Map<String, Object> datamap);
}