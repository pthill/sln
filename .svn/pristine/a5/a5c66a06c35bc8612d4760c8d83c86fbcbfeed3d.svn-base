package com.sln.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.model.product.ProductGoodsModel;
import com.sln.service.product.IProductGoodsService;

@Service(value = "productGoodsService")
public class ProductGoodsServiceImpl implements IProductGoodsService {
    private static Logger     log = LogManager.getLogger(ProductGoodsServiceImpl.class);

    @Resource
    private ProductGoodsModel productGoodsModel;

    @Override
    public ServiceResult<List<ProductGoods>> getGoodSByProductId(Integer productId) {
        ServiceResult<List<ProductGoods>> serviceResult = new ServiceResult<List<ProductGoods>>();
        try {
            serviceResult.setResult(productGoodsModel.getGoodSByProductId(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[ProductGoodsService][getGoodSByProductId]根据商品ID" + productId + "获取货品时发生异常:"
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductGoodsService][getGoodSByProductId]根据商品ID" + productId + "获取货品时发生异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveProductGoods(ProductGoods productGoods) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productGoodsModel.saveProductGoods(productGoods));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl saveProductGoods param:"
                      + JSON.toJSONString(productGoods));
            log.error("ProductGoodsServiceImpl saveProductGoods exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductGoods(ProductGoods productGoods) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productGoodsModel.updateProductGoods(productGoods));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl updateProductGoods param:"
                      + JSON.toJSONString(productGoods));
            log.error("ProductGoodsServiceImpl updateProductGoods exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductGoods(Integer productGoodsId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productGoodsModel.delProductGoods(productGoodsId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl delProductGoods productGoodsId:" + productGoodsId);
            log.error("ProductGoodsServiceImpl delProductGoods exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductGoods> getProductGoodsById(Integer productGoodsId) {
        ServiceResult<ProductGoods> serviceResult = new ServiceResult<ProductGoods>();
        try {
            serviceResult.setResult(productGoodsModel.getProductGoodsById(productGoodsId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl getProductGoodsById id:" + productGoodsId);
            log.error("ProductGoodsServiceImpl getProductGoodsById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductGoods>> pageProductGoods(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ProductGoods>> serviceResult = new ServiceResult<List<ProductGoods>>();
        try {
            serviceResult.setResult(productGoodsModel.pageProductGoods(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl pageProductGoods queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductGoodsServiceImpl pageProductGoods exception:", e);
        }
        return serviceResult;
    }
    

	@Override
	public ServiceResult<List<ProductGoods>> pageBySeller(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<ProductGoods>> serviceResult = new ServiceResult<List<ProductGoods>>();
        try {
            serviceResult.setResult(productGoodsModel.pageBySeller(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsServiceImpl page queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductGoodsServiceImpl page exception:", e);
        }
        return serviceResult;
	}
	
    /**
     * 根据条件（规格id组合）取得货品信息
     * @param queryMap
     * @return
     */
    @Override
    public ServiceResult<ProductGoods> getProductGoodsByCondition(Map<String, String> queryMap) {
        ServiceResult<ProductGoods> result = new ServiceResult<ProductGoods>();

        try {
            result.setResult(productGoodsModel.getProductGoodsByCondition(queryMap));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductGoodsService][getProductGoodsByCondition]根据条件取货品信息时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateProductGoods(List<ProductGoods> goodslist) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            result.setResult(productGoodsModel.updateProductGoods(goodslist));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductGoodsService][updateProductGoods]:更新货品异常", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> isUnique(Integer sellerId, String sku) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            result.setResult(productGoodsModel.isUnique(sellerId, sku));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductGoodsService][isUnique]:异常", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateProductAndGoods(Product product) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            result.setResult(productGoodsModel.updateProductAndGoods(product));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductGoodsService][updateProductAndGoods]:异常", e);
        }
        return result;
    }

	@Override
	public ServiceResult<ProductGoods> getProductById(Integer productId) {
		 ServiceResult<ProductGoods> serviceResult = new ServiceResult<ProductGoods>();
	        try {
	            serviceResult.setResult(productGoodsModel.getProductGoodsById(productId));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl getProductById id:" + productId);
	            log.error("ProductServiceImpl getProductById exception:", e);
	        }
	        return serviceResult;
	}


}