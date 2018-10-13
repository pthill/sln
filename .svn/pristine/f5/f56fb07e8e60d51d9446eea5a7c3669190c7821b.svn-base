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
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductCateJd;
import com.sln.entity.seller.SellerManageCate;
import com.sln.entity.system.SystemAdmin;
import com.sln.model.product.ProductCateModel;
import com.sln.service.product.IProductCateService;
import com.sln.vo.product.FrontProductCateVO;
import com.sln.vo.product.ProductCateVO;

@Service(value = "productCateService")
public class ProductCateServiceImpl implements IProductCateService {
    private static Logger    log = LogManager.getLogger(ProductCateServiceImpl.class);

    @Resource
    private ProductCateModel productCateModel;

    @Override
    public ServiceResult<Boolean> saveProductCate(ProductCate productCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.saveProductCate(productCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductCateServiceImpl saveProductCate param:" + JSON.toJSONString(productCate));
            log.error("ProductCateServiceImpl saveProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductCate(ProductCate productCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.updateProductCate(productCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductCateServiceImpl updateProductCate param:" + JSON.toJSONString(productCate));
            log.error("ProductCateServiceImpl updateProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductCate(Integer productCateId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.delProductCate(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl delProductCate productCateId:" + productCateId);
            log.error("ProductCateServiceImpl delProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductCate> getProductCateById(Integer productCateId) {
        ServiceResult<ProductCate> serviceResult = new ServiceResult<ProductCate>();
        try {
            serviceResult.setResult(productCateModel.getProductCateById(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getProductCateById id:" + productCateId);
            log.error("ProductCateServiceImpl getProductCateById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCateVO>> pageProductCate(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ProductCateVO>> serviceResult = new ServiceResult<List<ProductCateVO>>();
        try {
            serviceResult.setResult(productCateModel.pageProductCate(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getByPid(Integer pid) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();
        try {
            serviceResult.setResult(productCateModel.getByPid(pid));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate pid:" + pid);
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();
        try {
            serviceResult.setResult(productCateModel.getCateBySellerId(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCateBySellerId sellerId:" + sellerId);
            log.error("ProductCateServiceImpl getCateBySellerId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId, Integer pid) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();

        try {
            serviceResult.setResult(productCateModel.getCateBySellerId(sellerId, pid));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCateBySellerId sellerId:" + sellerId);
            log.error("ProductCateServiceImpl getCateBySellerId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> getCatePathStrById(Integer productCateId) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
            serviceResult.setResult(productCateModel.getCatePathStrById(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCatePathStrById productCateId:" + productCateId);
            log.error("ProductCateServiceImpl getCatePathStrById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public SellerManageCate getSellerManageCate(Integer id) {
        return productCateModel.getSellerManageCate(id);
    }

    @Override
    public Boolean updateSellerManageCate(SellerManageCate cate) {
        return productCateModel.updateSellerManageCate(cate);
    }

    @Override
    public ServiceResult<List<ProductCateVO>> getByPidAndSeller(Integer pid, Integer seller) {
        ServiceResult<List<ProductCateVO>> serviceResult = new ServiceResult<List<ProductCateVO>>();
        try {

            serviceResult.setResult(productCateModel.getByPidAndSeller(pid, seller));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate pid:" + pid);
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    /**
     * 取得所有显示状态的商品分类
     * @param  map
     * @return
     */
    @Override
    public ServiceResult<List<FrontProductCateVO>> getProductCateList(Map<String, Object> queryMap) {
        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
        try {
            serviceResult.setResult(productCateModel.getProductCateList(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductCateService][getProductCateList]获取商品分类列表时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getProductCate(Map<String, Object> param) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();
        try {
            serviceResult.setResult(productCateModel.getProductCate(param));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductCateService][getProductCate]获取商品分类列表时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 同步JD分类信息
     */
	@Override
	public ServiceResult<Integer> syJdCate() {
		 ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
	        try {
	            serviceResult.setResult(productCateModel.syJdCate());
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductCateServiceImpl delProductCate exception:", e);
	        }
	        
	        return serviceResult;
	}
    
    /**
     * 添加京东分类到平台（有关联的则更新）
     * @param jdCatId
     * @param systemAdmin
     * @return
     */
    @Override
    public ServiceResult<Boolean> addJdCategory(String jdCatIds,SystemAdmin systemAdmin) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.addJdCategory(jdCatIds, systemAdmin));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductCateService][addJdCategory]添加京东分类时发生异常:", e);
        }
        return serviceResult;
    }
    
    @Override
    public ServiceResult<List<ProductCateJd>> pageProductCateJd(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ProductCateJd>> serviceResult = new ServiceResult<List<ProductCateJd>>();
        try {
            serviceResult.setResult(productCateModel.pageProductCateJd(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }
    
    /**
     * 批量新增平台分类和京东分类关系信息
     * @param productCateId
     * @param jdCategoryIds
     * @return
     */
    @Override
    public ServiceResult<Boolean> batchInsertProductCateJd(Integer productCateId,String jdCategoryIds) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        	int flag = productCateModel.batchInsertProductCateJd(productCateId, jdCategoryIds);
            serviceResult.setSuccess(flag==1);
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductCateService][batchInsertProductCateJd]批量新增平台分类和京东分类关系信息时发生异常:", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<Integer> VerifJDCate() {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(productCateModel.VerifJDCate());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl delProductCate exception:", e);
        }
        
        return serviceResult;
	}
}