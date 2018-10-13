package com.sln.service.impl.seller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.model.seller.SellerEliminateModel;
import com.sln.service.seller.ISellerEliminateService;
import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.SellerEliminate;

@Service(value = "sellerEliminateService")
public class SellerEliminateServiceImpl implements ISellerEliminateService {
	private static Logger      log = LogManager.getLogger(SellerEliminateServiceImpl.class);
	
	@Resource
	private SellerEliminateModel sellerEliminateModel;
    
     /**
     * 根据id取得淘汰机制表
     * @param  sellerEliminateId
     * @return
     */
    @Override
    public ServiceResult<SellerEliminate> getSellerEliminateById(Integer sellerEliminateId) {
        ServiceResult<SellerEliminate> result = new ServiceResult<SellerEliminate>();
        try {
            result.setResult(sellerEliminateModel.getSellerEliminateById(sellerEliminateId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISellerEliminateService][getSellerEliminateById]根据id["+sellerEliminateId+"]取得淘汰机制表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerEliminateService][getSellerEliminateById]根据id["+sellerEliminateId+"]取得淘汰机制表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存淘汰机制表
     * @param  sellerEliminate
     * @return
     */
     @Override
     public ServiceResult<Integer> saveSellerEliminate(SellerEliminate sellerEliminate) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerEliminateModel.saveSellerEliminate(sellerEliminate));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISellerEliminateService][saveSellerEliminate]保存淘汰机制表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerEliminateService][saveSellerEliminate]保存淘汰机制表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新淘汰机制表
     * @param  sellerEliminate
     * @return
     * @see com.sln.service.SellerEliminateService#updateSellerEliminate(SellerEliminate)
     */
     @Override
     public ServiceResult<Integer> updateSellerEliminate(SellerEliminate sellerEliminate) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerEliminateModel.updateSellerEliminate(sellerEliminate));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISellerEliminateService][updateSellerEliminate]更新淘汰机制表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerEliminateService][updateSellerEliminate]更新淘汰机制表时出现未知异常：",
                e);
        }
        return result;
     }

     /**
      * 批量插入
     * @return 
      */
	@Override
	public ServiceResult<Integer> insertlist(List<SellerEliminate> sellerEliminatelist) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerEliminateModel.insertSellerEliminate(sellerEliminatelist));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISellerEliminateService][insertSellerEliminate]批量插入淘汰机制表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerEliminateService][updateSellerEliminate]批量更新淘汰机制表时出现未知异常：",
                e);
        }
        return result;
}

	@Override
	public Integer getcount() {
		// TODO Auto-generated method stub
		return sellerEliminateModel.getcount();
	}

	@Override
	public ServiceResult<Integer> updateBatch(List<SellerEliminate> list) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerEliminateModel.updateBatchSellerEliminate(list));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISellerEliminateService][insertSellerEliminate]批量插入淘汰机制表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISellerEliminateService][updateBatchSellerEliminate]批量更新淘汰机制表时出现未知异常：",
                e);
        }
        return result;
	}

	//获取淘汰表中四条记录
	@Override
	public List<SellerEliminate> getSellerEliminateList() {
		// TODO Auto-generated method stub
		return sellerEliminateModel.getSellerEliminateList();
	}
}