package com.sln.service.impl.member;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.JobProductBack;
import com.sln.model.member.JobProductBackModel;
import com.sln.service.member.IJobProductBackService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JobProductBackServiceImpl implements IJobProductBackService {
    private static Logger log = LogManager.getLogger(JobProductBackServiceImpl.class);

    @Resource
    private JobProductBackModel jobProductBackModel;

    @Override
    public ServiceResult<Integer> save(JobProductBack jobProductBack) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            result.setResult(jobProductBackModel.save(jobProductBack));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IJobProductBackService][save]保存job_product_back对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IJobProductBackService][save]保存job_product_back对象job_product_back对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<JobProductBack> getById(Integer id) {
        ServiceResult<JobProductBack> result = new ServiceResult<>();
        try {
            result.setResult(jobProductBackModel.getById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IJobProductBackService][getById]根据id["+id+"]取得job_product_back对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IJobProductBackService][getById]根据id["+id+"]取得job_product_back对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<JobProductBack>> getPage(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<JobProductBack>> serviceResult = new ServiceResult<List<JobProductBack>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(jobProductBackModel, "Property 'jobProductBackModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(jobProductBackModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(jobProductBackModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IJobProductBackService][getPage]查询退款批次列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IJobProductBackService][getPage]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IJobProductBackService][getPage]查询退款批次列表发生异常:", e);
        }
        return serviceResult;
    }
}
