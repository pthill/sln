package com.sln.service.impl.operate;

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
import com.sln.entity.operate.SystemNotice;
import com.sln.model.operate.SystemNoticeModel;
import com.sln.service.system.ISystemNoticeService;

@Service(value = "systemNoticeService")
public class SystemNoticeServiceImpl implements ISystemNoticeService {
    private static Logger     log = LogManager.getLogger(SystemNoticeServiceImpl.class);

    @Resource
    private SystemNoticeModel systemNoticeModel;

    /**
    * 根据id取得商城公告
    * @param  systemNoticeId
    * @return
    */
    @Override
    public ServiceResult<SystemNotice> getSystemNoticeById(Integer systemNoticeId) {
        ServiceResult<SystemNotice> result = new ServiceResult<SystemNotice>();
        try {
            result.setResult(systemNoticeModel.getSystemNoticeById(systemNoticeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemNoticeService][getSystemNoticeById]根据id[" + systemNoticeId
                      + "]取得商城公告时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemNoticeService][getSystemNoticeById]根据id[" + systemNoticeId
                      + "]取得商城公告时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存商城公告
     * @param  systemNotice
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSystemNotice(SystemNotice systemNotice) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemNoticeModel.saveSystemNotice(systemNotice));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemNoticeService][saveSystemNotice]保存商城公告时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemNoticeService][saveSystemNotice]保存商城公告时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新商城公告
    * @param  systemNotice
    * @return
    * @see com.sln.service.SystemNoticeService#updateSystemNotice(SystemNotice)
    */
    @Override
    public ServiceResult<Integer> updateSystemNotice(SystemNotice systemNotice) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemNoticeModel.updateSystemNotice(systemNotice));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemNoticeService][updateSystemNotice]更新商城公告时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemNoticeService][updateSystemNotice]更新商城公告时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemNotice>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SystemNotice>> serviceResult = new ServiceResult<List<SystemNotice>>();
        try {
            serviceResult.setResult(systemNoticeModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[SystemNoticeServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(systemNoticeModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[SystemNoticeServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SystemNotice> toDetail(Integer id, Integer sellerid) {
        ServiceResult<SystemNotice> result = new ServiceResult<SystemNotice>();
        try {
            result.setResult(systemNoticeModel.toDetail(id, sellerid));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemNotice>> getUnreadNotice(Integer sellerId, PagerInfo pager) {
        ServiceResult<List<SystemNotice>> result = new ServiceResult<List<SystemNotice>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemNoticeModel.getUnreadNoticeCount(sellerId));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            result.setResult(systemNoticeModel.getUnreadNotice(sellerId, start, size));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
        }
        return result;
    }
}