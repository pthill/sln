package com.sln.service.impl.promotion;

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
import com.sln.model.promotion.ActUnitIntegralModel;
import com.sln.service.promotion.ActUnitIntegralService;

@Service(value = "actUnitIntegralService")
public class ActUnitIntegralServiceImpl implements ActUnitIntegralService{
	private static Logger      log = LogManager.getLogger(ActUnitIntegralServiceImpl.class);
	
	@Resource
	private ActUnitIntegralModel actUnitIntegralModel;
	
	@Override
	public ServiceResult<List> getIntegralStatistics(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List> serviceResult = new ServiceResult<List>();
		
		try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actUnitIntegralModel.getIntegralStatisticsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }else {
            	size = Integer.MAX_VALUE;
            }
            
            List list = actUnitIntegralModel.getIntegralStatistics(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ActUnitIntegralServiceImpl][getIntegralStatistics] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ActUnitIntegralServiceImpl][getIntegralStatistics] exception:" + e.getMessage());
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List> getIntegralDetail(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List> serviceResult = new ServiceResult<List>();
		try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actUnitIntegralModel.getIntegralDetailCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }else {
            	size = Integer.MAX_VALUE;
            }

            List list = actUnitIntegralModel.getIntegralDetail(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ActUnitIntegralServiceImpl][getIntegralDetail] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ActUnitIntegralServiceImpl][getIntegralDetail] exception:" + e.getMessage());
        }
		return serviceResult;
	}
	
}
