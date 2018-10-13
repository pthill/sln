package com.sln.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.ProductPicture;
import com.sln.model.product.FrontProductPictureModel;
import com.sln.service.product.IFrontProductPictureService;

@Service(value = "frontProductPictureService")
public class FrontProductPictureServiceImpl implements IFrontProductPictureService {
    private static Logger            log = LogManager
        .getLogger(FrontProductPictureServiceImpl.class);

    @Resource
    private FrontProductPictureModel frontProductPictureModel;

    /**
    * 根据id取得商品对应图片表
    * @param  productPictureId
    * @return
    */
    @Override
    public ServiceResult<ProductPicture> getProductPictureById(Integer productPictureId) {
        ServiceResult<ProductPicture> result = new ServiceResult<ProductPicture>();
        try {
            result.setResult(frontProductPictureModel.getProductPictureById(productPictureId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getProductPictureById]根据id["
                      + productPictureId + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + productPictureId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据id取得商品对应图片表
     * @param  productId
     * @return
     */
    @Override
    public ServiceResult<List<ProductPicture>> getByProductIds(Integer productId) {
        ServiceResult<List<ProductPicture>> result = new ServiceResult<List<ProductPicture>>();
        try {
            result.setResult(frontProductPictureModel.getByProductIds(productId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getByProductIds]根据商品id[" + productId
                      + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据商品id[" + productId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据商品ID获取商品的主图
     * @param productId
     * @return
     */
    @Override
    public ServiceResult<ProductPicture> getproductLead(Integer productId) {
        ServiceResult<ProductPicture> result = new ServiceResult<ProductPicture>();
        try {
            result.setResult(frontProductPictureModel.getproductLead(productId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getproductLead]根据商品id[" + productId
                      + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据商品id[" + productId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<String>> getSkuImage(String token,String sku){
        ServiceResult<List<String>> serviceResult = new ServiceResult<List<String>>();
        try {
            serviceResult.setResult( frontProductPictureModel.getSkuImage(token, sku));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("FrontProductPictureServiceImpl getSkuImage exception:", e);
        }
        return serviceResult;
     }

}