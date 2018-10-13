package com.sln.service.impl.seller;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.sln.entity.seller.SellerTransport;
import com.sln.model.seller.SellerTransportModel;
import com.sln.service.seller.ISellerTransportService;
import com.sln.vo.seller.TransportJson;

@Service(value = "sellerTransportService")
public class SellerTransportServiceImpl implements ISellerTransportService {
    private static Logger        log = LogManager.getLogger(SellerTransportServiceImpl.class);

    @Resource
    private SellerTransportModel sellerTransportModel;

    /**
    * 根据id取得卖家运费模板
    * @param  sellerTransportId
    * @return
    */
    @Override
    public ServiceResult<SellerTransport> getSellerTransportById(Integer sellerTransportId) {
        ServiceResult<SellerTransport> result = new ServiceResult<SellerTransport>();
        try {
            result.setResult(sellerTransportModel.getSellerTransportById(sellerTransportId));
        } catch (Exception e) {
            log.error("根据id[" + sellerTransportId + "]取得卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerTransportId + "]取得卖家运费模板时出现未知异常");
        }
        return result;
    }

    /**
     * 保存卖家运费模板
     * @param  sellerTransport
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSellerTransport(SellerTransport sellerTransport) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerTransportModel.saveSellerTransport(sellerTransport));
        } catch (Exception e) {
            log.error("保存卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存卖家运费模板时出现未知异常");
        }
        return result;
    }

    /**
    * 更新卖家运费模板
    * @param  sellerTransport
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSellerTransport(SellerTransport sellerTransport) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerTransportModel.updateSellerTransport(sellerTransport));
        } catch (Exception e) {
            log.error("更新卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新卖家运费模板时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerTransport>> page(Map<String, String> queryMap,
                                                     PagerInfo pager) {
        ServiceResult<List<SellerTransport>> serviceResult = new ServiceResult<List<SellerTransport>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerTransportModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<SellerTransport> list = sellerTransportModel.page(param);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerTransportServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerTransportServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer sellerId, Integer id) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTransportModel.del(sellerId, id));
            serviceResult.setMessage("删除成功");
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            log.error("[SellerTransportServiceImpl][del] exception:" + e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }

    /**
     * 组装运费信息
     * @param st
     * @return
     */
    @Override
    public ServiceResult<List<TransportJson>> assembleTransportInfo(SellerTransport st) {
        ServiceResult<List<TransportJson>> serviceResult = new ServiceResult<List<TransportJson>>();
        try {
            serviceResult.setResult(sellerTransportModel.assembleTransportInfo(st));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[ISellerTransportService][assembleTransportInfo]组装运费信息时发生异常:" + e.getMessage(), e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerTransport>> getEffectTransportBySellerId(Integer sellerId) {
        ServiceResult<List<SellerTransport>> serviceResult = new ServiceResult<List<SellerTransport>>();
        try {
            serviceResult.setResult(sellerTransportModel.getEffectTransportBySellerId(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerTransportService][getEffectTransportBySellerId] 根据商家获取有效的运费模板时发生异常:"
                      + e.getMessage(),
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> transportInUse(Integer id, Integer state) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTransportModel.transportInUse(id, state));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[SellerTransportServiceImpl][transportInUse] 根据运费模板ID启用模板时发生异常:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<BigDecimal> calculateTransFee(Product product, ProductGoods productGoods,
                                                       Integer number, Integer cityId) {
        ServiceResult<BigDecimal> serviceResult = new ServiceResult<BigDecimal>();
        try {
            serviceResult.setResult(
                sellerTransportModel.calculateTransFee(product, productGoods, number, cityId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerTransportServiceImpl][calculateTransFee]计算运费时发生异常:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerTransport>> getTransportByTypeAndSellerId(Integer transportType,
                                                                              Integer sellerId) {
        ServiceResult<List<SellerTransport>> serviceResult = new ServiceResult<List<SellerTransport>>();
        try {
            serviceResult.setResult(
                sellerTransportModel.getTransportByTypeAndSellerId(transportType, sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[SellerTransportServiceImpl][getTransportByTypeAndSellerId]按照计算类型获取商家的运费模板时发生异常:"
                      + e.getMessage());
        }
        return serviceResult;
    }
}