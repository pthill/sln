package com.sln.service.impl.compain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import com.sln.entity.compain.ComplainRegister;
import com.sln.entity.seller.Seller;
import com.sln.model.compain.ComplainRegisterModel;
import com.sln.service.compain.IComplainRegisterService;

@Service(value = "complainRegisterService")
public class ComplainRegisterServiceImpl implements IComplainRegisterService {
	private static Logger      log = LogManager.getLogger(ComplainRegisterServiceImpl.class);
	
	@Resource
	private ComplainRegisterModel complainRegisterModel;
    
     /**
     * 根据id取得投诉登记表
     * @param  complainRegisterId
     * @return
     */
    @Override
    public ServiceResult<ComplainRegister> getComplainRegisterById(Integer complainRegisterId) {
        ServiceResult<ComplainRegister> result = new ServiceResult<ComplainRegister>();
        try {
            result.setResult(complainRegisterModel.getComplainRegisterById(complainRegisterId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IComplainRegisterService][getComplainRegisterById]根据id["+complainRegisterId+"]取得投诉登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IComplainRegisterService][getComplainRegisterById]根据id["+complainRegisterId+"]取得投诉登记表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存投诉登记表
     * @param  complainRegister
     * @return
     */
     @Override
     public ServiceResult<Integer> saveComplainRegister(ComplainRegister complainRegister) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(complainRegisterModel.saveComplainRegister(complainRegister));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IComplainRegisterService][saveComplainRegister]保存投诉登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IComplainRegisterService][saveComplainRegister]保存投诉登记表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新投诉登记表
     * @param  complainRegister
     * @return
     * @see com.sln.service.ComplainRegisterService#updateComplainRegister(ComplainRegister)
     */
     @Override
     public ServiceResult<Integer> updateComplainRegister(ComplainRegister complainRegister) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(complainRegisterModel.updateComplainRegister(complainRegister));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IComplainRegisterService][updateComplainRegister]更新投诉登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IComplainRegisterService][updateComplainRegister]更新投诉登记表时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<ComplainRegister>> getComplainRegister(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<ComplainRegister>> serviceResult = new ServiceResult<List<ComplainRegister>>();
		serviceResult.setPager(pager);
		try {
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(complainRegisterModel.getSellersCount(queryMap));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			List<ComplainRegister> sellEliminate = complainRegisterModel.getSellEliminate(queryMap,start,size);
			
	            serviceResult.setResult(sellEliminate);
	        } catch (BusinessException e) {
	            serviceResult.setSuccess(false);
	            serviceResult.setMessage(e.getMessage());
	            log.error("[IComplainRegisterService][getComplainRegister]商家表时出现异常：" + e.getMessage());
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[IComplainRegisterService][getComplainRegister] param1:" + queryMap);
	            log.error("[IComplainRegisterService][getComplainRegister]获取商家表时出现未知异常：", e);
	        }
	        return serviceResult;
	}

	//删除操作
	@Override
	public ServiceResult<Boolean> deleteById(Integer id) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(complainRegisterModel.deleteById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IComplainRegisterService][deleteById]删除投诉登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IComplainRegisterService][deleteById]删除投诉登记表时出现未知异常：",
                e);
        }
        return result;
	}
}