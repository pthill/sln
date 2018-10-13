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
import com.sln.entity.product.ProductBrand;
import com.sln.model.product.ProductBrandModel;
import com.sln.service.product.IProductBrandService;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Service
public class ProductBrandServiceImpl implements IProductBrandService {
    private static Logger     log = LogManager.getLogger(ProductBrandServiceImpl.class);

    @Resource
    private ProductBrandModel productBrandModel;

    @Override
    public ServiceResult<Boolean> save(ProductBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.save(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[BrandServiceImpl][save] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductBrand> getById(Integer id) {
        ServiceResult<ProductBrand> serviceResult = new ServiceResult<ProductBrand>();
        try {
            serviceResult.setResult(productBrandModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][getById] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> getByIds(String ids) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.getByIds(ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + ids);
            log.error("[BrandServiceImpl][getById] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> update(ProductBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.update(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[BrandServiceImpl][audit] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param1:" + JSON.toJSONString(queryMap) + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[BrandServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> getBrandByTypeId(Integer typeId) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.getBrandByTypeId(typeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][getBrandByTypeId] typeId:" + typeId);
            log.error("[BrandServiceImpl][getBrandByTypeId] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> audit(Integer id, Integer state, Integer userId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.audit(id, state, userId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][audit] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.del(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][del] exception:", e);
        }
        return serviceResult;
    }

    /**
     * 取出所有可用的品牌
     * @return
     * @see com.sln.service.product.IProductBrandService#listNoPage()
     */
    @Override
    public ServiceResult<List<ProductBrand>> listNoPage() {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.listNoPage());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][getBrandByTypeId] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> getHotBrands() {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.getHotBrands());
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IProductBrandService][getHotBrands]获取推荐的品牌时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductBrandService][getHotBrands]获取推荐的品牌时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, List<ProductBrand>>> getBrandsLetterGrouping() {
        ServiceResult<Map<String, List<ProductBrand>>> serviceResult = new ServiceResult<Map<String, List<ProductBrand>>>();
        try {
            serviceResult.setResult(productBrandModel.getBrandsLetterGrouping());
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IProductBrandService][getBrandsLetterGrouping]获取所有品牌，按首字母分组时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductBrandService][getBrandsLetterGrouping]获取所有品牌，按首字母分组时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getUndoBrand() {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            Integer res = productBrandModel.getUndoBrand();
            serviceResult.setResult(res);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }
}
