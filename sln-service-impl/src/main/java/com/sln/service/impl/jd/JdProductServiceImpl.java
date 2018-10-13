package com.sln.service.impl.jd;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.entity.jd.JdAddress;
import com.sln.model.jd.JdProductModel;
import com.sln.service.jd.IJdProductService;

@Service(value = "JdProductService")
public class JdProductServiceImpl implements IJdProductService {
	private static Logger      log = LogManager.getLogger(JdProductServiceImpl.class);

	@Resource
	private JdProductModel jdProductModel;
	@Override
	public ServiceResult<Integer> batchInsertProductDetail(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdProductModel.batchInsertProductDetail(token));
        } catch (Exception e) {
            log.error("同步京东商品详情异常" + e);
            result.setSuccess(false);
            result.setMessage("同步京东商品详情异常");
        }
        return result;
	}

	@Override
	public ServiceResult<Integer> updatePriceBySku(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdProductModel.updatePriceBySku(token));
        } catch (Exception e) {
            log.error("同步商品详情价格异常" + e);
            result.setSuccess(false);
            result.setMessage("同步商品详情价格异常");
        }
        return result;
	}

	@Override
	public ServiceResult<Integer> updateStuStateBySku(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdProductModel.updateStuStateBySku(token));
        } catch (Exception e) {
            log.error("同步商品上下架状态异常" + e);
            result.setSuccess(false);
            result.setMessage("同步商品上下架状态异常");
        }
        return result;
	}
	
	@Override
    public ServiceResult<JdAddress> getJDAddressFromAddress(String token, String address) {
        ServiceResult<JdAddress> result = new ServiceResult<JdAddress>();
        try {
            JdAddress jdAddress = jdProductModel.getJDAddressFromAddress(token, address);
            if (null != jdAddress) {
                result.setResult(jdAddress);
            } else {
                result.setSuccess(false);
                result.setMessage("根据地址查询京东地址异常");
            }
        } catch (Exception e) {
            log.error("根据地址查询京东地址异常:" + e.getMessage());
            result.setSuccess(false);
            result.setMessage("根据地址查询京东地址异常");
        }
        return result;
    }

    @Override
    public ServiceResult<String> getJDAddressArea(String token, String address) {
        ServiceResult<String> result = new ServiceResult<String>();
        try {
            String jdAddress = jdProductModel.getJDAddressArea(token, address);
            if (null != jdAddress) {
                result.setResult(jdAddress);
            } else {
                result.setSuccess(false);
                result.setMessage("根据地址查询京东三级地址编码组合异常");
            }
        } catch (Exception e) {
            log.error("根据地址查询京东三级地址编码组合异常:" + e.getMessage());
            result.setSuccess(false);
            result.setMessage("根据地址查询京东三级地址编码组合异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> getStockById(String token, String sku, String area) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdProductModel.getStockById(token, sku, area));
        } catch (Exception e) {
            log.error("商品列表页获取库存异常:" + e.getMessage());
            result.setSuccess(false);
            result.setMessage("商品列表页获取库存异常");
        }
        return result;
    }
    
    @Override
    public ServiceResult<Integer> getFreight(String token, String sku, JdAddress address) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(jdProductModel.getFreight(token, sku, address));
        } catch (Exception e) {
            log.error("获取京东运费异常:" + e.getMessage());
            result.setSuccess(false);
            result.setMessage("获取京东运费异常");
        }
        return result;
    }

}