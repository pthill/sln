package com.sln.service.jd;

import java.security.Provider.Service;

import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;


public interface IJdCommoditypoolService {

	/**
	 * 同步JD商品池子数据
	 */
	ServiceResult<Integer> batchInsert(AccessToken token);
	
	/**
	 * 同步商品池子中SKU数据
	 */
	ServiceResult<Integer> insertSku(AccessToken token);
}