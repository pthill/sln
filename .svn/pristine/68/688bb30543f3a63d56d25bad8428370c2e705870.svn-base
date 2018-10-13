package com.sln.service.impl.jd;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.entity.order.Invoice;
import com.sln.model.jd.JdCategoryModel;
import com.sln.model.jd.JdCommoditypoolModel;
import com.sln.model.jd.JdProductModel;
import com.sln.service.jd.IJdCategoryService;
import com.sln.service.jd.IJdCommoditypoolService;
import com.sln.service.jd.IJdProductService;

@Service(value = "JdCommoditypool")
public class JdCommoditypoolServiceImpl implements IJdCommoditypoolService {
	private static Logger      log = LogManager.getLogger(JdCommoditypoolServiceImpl.class);

	@Resource
	private JdCommoditypoolModel jdCommoditypoolModel;
	@Override
	public ServiceResult<Integer> batchInsert(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdCommoditypoolModel.batchInsert(token));
        } catch (Exception e) {
            log.error("同步京东商品池子信息异常" + e);
            result.setSuccess(false);
            result.setMessage("同步京东商品池子信息异常");
        }
        return result;
	}

	@Override
	public ServiceResult<Integer> insertSku(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
        	//同步SKU
        	jdCommoditypoolModel.insertSku(token);
        	//对SKU去重
        	jdCommoditypoolModel.duplicateRemoval();
            result.setResult(1);
        } catch (Exception e) {
            log.error("同步京东商品SKU异常" + e);
            result.setSuccess(false);
            result.setMessage("同步京东商品SKU异常");
        }
        return result;
	}

	
}