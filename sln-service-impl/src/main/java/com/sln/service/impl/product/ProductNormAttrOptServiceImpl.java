package com.sln.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.ProductNormAttrOpt;
import com.sln.model.product.ProductNormAttrOptModel;
import com.sln.service.product.IProductNormAttrOptService;

@Service(value = "productNormAttrOptService")
public class ProductNormAttrOptServiceImpl implements IProductNormAttrOptService {
    private static Logger           log = LogManager.getLogger(ProductNormAttrOptServiceImpl.class);

    @Resource
    private ProductNormAttrOptModel productNormAttrOptModel;

    /**
    * 根据id取得商品选定的规格属性(保存商品插入，暂时不用)
    * @param  productNormAttrOptId
    * @return
    */
    @Override
    public ServiceResult<ProductNormAttrOpt> getProductNormAttrOptById(Integer productNormAttrOptId) {
        ServiceResult<ProductNormAttrOpt> result = new ServiceResult<ProductNormAttrOpt>();
        try {
            result.setResult(productNormAttrOptModel
                .getProductNormAttrOptById(productNormAttrOptId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductNormAttrOptService][getProductNormAttrOptById]根据id["
                      + productNormAttrOptId + "]取得商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductNormAttrOptService][getProductNormAttrOptById]根据id["
                      + productNormAttrOptId + "]取得商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存商品选定的规格属性(保存商品插入，暂时不用)
     * @param  productNormAttrOpt
     * @return
     */
    @Override
    public ServiceResult<Integer> saveProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productNormAttrOptModel.saveProductNormAttrOpt(productNormAttrOpt));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductNormAttrOptService][saveProductNormAttrOpt]保存商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IProductNormAttrOptService][saveProductNormAttrOpt]保存商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新商品选定的规格属性(保存商品插入，暂时不用)
    * @param  productNormAttrOpt
    * @return
    * @see com.sln.service.ProductNormAttrOptService#updateProductNormAttrOpt(ProductNormAttrOpt)
    */
    @Override
    public ServiceResult<Integer> updateProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productNormAttrOptModel.updateProductNormAttrOpt(productNormAttrOpt));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductNormAttrOptService][updateProductNormAttrOpt]更新商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IProductNormAttrOptService][updateProductNormAttrOpt]更新商品选定的规格属性(保存商品插入，暂时不用)时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductNormAttrOpt>> page(Map<String, String> queryMap,
                                                        PagerInfo pager) {
        ServiceResult<List<ProductNormAttrOpt>> serviceResult = new ServiceResult<List<ProductNormAttrOpt>>();
        try {
            serviceResult.setResult(productNormAttrOptModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[ProductNormAttrOptServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productNormAttrOptModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[ProductNormAttrOptServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}