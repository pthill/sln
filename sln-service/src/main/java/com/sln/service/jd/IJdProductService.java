package com.sln.service.jd;

import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.entity.jd.JdAddress;

public interface IJdProductService {

	/**
	 * 批量同步JD商品详情
	 */
	ServiceResult<Integer> batchInsertProductDetail(AccessToken token);
	
	/**
	 * 同步JD商品价格
	 */
	ServiceResult<Integer> updatePriceBySku(AccessToken token);
	
	/**
	 * 同步JD商品上下架状态
	 */
	ServiceResult<Integer> updateStuStateBySku(AccessToken token);
	
	/**
	 * 根据地址查询京东地址
	 * @param token
	 * @param address
	 * @return
	 */
	ServiceResult<JdAddress> getJDAddressFromAddress(String token, String address);
	
	/**
     * 根据地址查询京东三级地址编码组合
     * 格式：1_0_0 (分别代表1、2、3级地址)
     * @param token
     * @param address
     * @return
     */
    ServiceResult<String> getJDAddressArea(String token, String address);
	
	/**
	 * 商品列表页获取库存接口
	 * @param token
	 * @param sku
	 * @param area
	 * @return
	 */
	ServiceResult<Integer> getStockById (String token, String sku, String area);
	
	/**
	 * 获取京东运费
	 * @param token
	 * @param sku
	 * @param address
	 * @return
	 */
	ServiceResult<Integer> getFreight (String token, String sku, JdAddress address);
}