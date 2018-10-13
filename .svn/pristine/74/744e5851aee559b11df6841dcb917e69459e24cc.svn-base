package com.sln.service.impl.product;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.model.product.ProductRegisterModel;
import com.sln.service.product.IProductRegisterService;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.ProductRegister;

@Service(value = "productRegisterService")
public class ProductRegisterServiceImpl implements IProductRegisterService {
	private static Logger      log = LogManager.getLogger(ProductRegisterServiceImpl.class);
	
	@Resource
	private ProductRegisterModel productRegisterModel;
    
     /**
     * 根据id取得商品登记
     * @param  productRegisterId
     * @return
     */
    @Override
    public ServiceResult<ProductRegister> getProductRegisterById(Integer productRegisterId) {
        ServiceResult<ProductRegister> result = new ServiceResult<ProductRegister>();
        try {
            result.setResult(productRegisterModel.getProductRegisterById(productRegisterId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductRegisterService][getProductRegisterById]根据id["+productRegisterId+"]取得商品登记时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductRegisterService][getProductRegisterById]根据id["+productRegisterId+"]取得商品登记时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存商品登记
     * @param  productRegister
     * @return
     */
     @Override
     public ServiceResult<Integer> saveProductRegister(ProductRegister productRegister) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productRegisterModel.saveProductRegister(productRegister));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductRegisterService][saveProductRegister]保存商品登记时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductRegisterService][saveProductRegister]保存商品登记时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新商品登记
     * @param  productRegister
     * @return
     * @see com.sln.service.ProductRegisterService#updateProductRegister(ProductRegister)
     */
     @Override
     public ServiceResult<Integer> updateProductRegister(ProductRegister productRegister) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productRegisterModel.updateProductRegister(productRegister));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductRegisterService][updateProductRegister]更新商品登记时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductRegisterService][updateProductRegister]更新商品登记时出现未知异常：",
                e);
        }
        return result;
     }

     /**
      * 获取缺货集合
      */
	@Override
	public ServiceResult<List<ProductRegister>> getProductsRegister(
			Map<String, String> queryMap, PagerInfo pager) {
		
		  ServiceResult<List<ProductRegister>> serviceResult = new ServiceResult<List<ProductRegister>>();
	        try {
	            Integer start = 0, size = 0;
	            if (pager != null) {
	            	int productRegisterCount = productRegisterModel.getProductRegisterCount(queryMap);
	                pager.setRowsCount(productRegisterCount);
	                start = pager.getStart();
	                size = pager.getPageSize();
	            }
	            List<ProductRegister> list = productRegisterModel.getProductsRegister(queryMap, start, size);
	            serviceResult.setResult(list);
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(false);
	            log.error("[IProductService][getProductsByRole]根据条件分页查询待售商品时时出现异常：" + e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
	                      + " pager:" + JSON.toJSONString(pager));
	            log.error("ProductServiceImpl pageProduct exception:", e);
	        }
		return serviceResult;
	}

	/**
	 * 自定义的获取缺货商品
	 */
	@Override
	public List<ProductRegister> getProductRist() {
		 //List<ProductRegister> serviceResult = new ArrayList<ProductRegister>();
		int productRegisterCount = productRegisterModel.getProductRegisterCount();
		 List<ProductRegister> list = productRegisterModel.getProductsRegi();
		
		return list;
	}

	/**
	 * 根据会员编号id或取相关的登记反馈信息集合
	 */
	@Override
	public List<ProductRegister> getProductRegisterByCurrentId(Integer currentId) {
		List<ProductRegister> list = productRegisterModel.getProductRegisterByCurrentId(currentId);
		return list;
	}

	@Override
	public void updateProductStateById(Integer id) {
		productRegisterModel.updateProductStateById(id);
		
	}

	@Override
	public ProductRegister getMemberIdByProRegiste(Integer id) {
		return productRegisterModel.getMemberId(id);
	}

	@Override
	public void updateProductStateByIdre(Map<String,Object> datamap) {
		productRegisterModel.updateProductStateByIdre(datamap);
		
	}

	
}