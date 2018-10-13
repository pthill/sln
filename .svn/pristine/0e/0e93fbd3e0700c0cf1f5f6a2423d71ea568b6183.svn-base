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
import com.sln.entity.operate.NoticeClickSituation;
import com.sln.model.operate.NoticeClickSituationModel;
import com.sln.service.operate.INoticeClickSituationService;

@Service(value = "noticeClickSituationService")
public class NoticeClickSituationServiceImpl implements INoticeClickSituationService {
    private static Logger             log = LogManager
        .getLogger(NoticeClickSituationServiceImpl.class);

    @Resource
    private NoticeClickSituationModel noticeClickSituationModel;

    /**
    * 根据id取得商家公告查看情况
    * @param  noticeClickSituationId
    * @return
    */
    @Override
    public ServiceResult<NoticeClickSituation> getNoticeClickSituationById(Integer noticeClickSituationId) {
        ServiceResult<NoticeClickSituation> result = new ServiceResult<NoticeClickSituation>();
        try {
            result.setResult(
                noticeClickSituationModel.getNoticeClickSituationById(noticeClickSituationId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[INoticeClickSituationService][getNoticeClickSituationById]根据id["
                      + noticeClickSituationId + "]取得商家公告查看情况时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[INoticeClickSituationService][getNoticeClickSituationById]根据id["
                      + noticeClickSituationId + "]取得商家公告查看情况时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存商家公告查看情况
     * @param  noticeClickSituation
     * @return
     */
    @Override
    public ServiceResult<Integer> saveNoticeClickSituation(NoticeClickSituation noticeClickSituation) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                noticeClickSituationModel.saveNoticeClickSituation(noticeClickSituation));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[INoticeClickSituationService][saveNoticeClickSituation]保存商家公告查看情况时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[INoticeClickSituationService][saveNoticeClickSituation]保存商家公告查看情况时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新商家公告查看情况
    * @param  noticeClickSituation
    * @return
    * @see com.sln.service.NoticeClickSituationService#updateNoticeClickSituation(NoticeClickSituation)
    */
    @Override
    public ServiceResult<Integer> updateNoticeClickSituation(NoticeClickSituation noticeClickSituation) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                noticeClickSituationModel.updateNoticeClickSituation(noticeClickSituation));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[INoticeClickSituationService][updateNoticeClickSituation]更新商家公告查看情况时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[INoticeClickSituationService][updateNoticeClickSituation]更新商家公告查看情况时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<NoticeClickSituation>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<NoticeClickSituation>> serviceResult = new ServiceResult<List<NoticeClickSituation>>();
        try {
            serviceResult.setResult(noticeClickSituationModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[NoticeClickSituationServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(noticeClickSituationModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[NoticeClickSituationServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}