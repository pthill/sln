package com.sln.service.impl.supplier;

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
import com.sln.entity.operate.CourierCompany;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.model.supplier.OrderDeliveryModel;
import com.sln.service.supplier.IOrderDeliveryService;

@Service(value = "orderDeliveryService")
public class OrderDeliveryServiceImpl implements IOrderDeliveryService {
	private static Logger      log = LogManager.getLogger(OrderDeliveryServiceImpl.class);
	
	@Resource
	private OrderDeliveryModel orderDeliveryModel;

	@Override
	public ServiceResult<List<OrderDelivery>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<OrderDelivery>> serviceResult = new ServiceResult<List<OrderDelivery>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(orderDeliveryModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<OrderDelivery> list = orderDeliveryModel.getPage(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[OrderDeliveryServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrderDeliveryServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<Integer> insertOrderDelivery(OrderDelivery orderDelivery) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		serviceResult.setResult(orderDeliveryModel.insertOrderDelivery(orderDelivery));
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateOrderDeliveryById(OrderDelivery orderDelivery) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		serviceResult.setResult(orderDeliveryModel.updateOrderDeliveryById(orderDelivery));
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> cofimDelivery(OrderDelivery orderDelivery) {
			ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(orderDeliveryModel.cofimDelivery(orderDelivery));
		}catch(Exception e){
			log.error(e);
		}
		return serviceResult;
	}
    
}