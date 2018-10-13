package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.QuickEnter;
import com.sln.model.portal.QuickEnterModel;
import com.sln.service.portal.IQuickEnterService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class QuickEnterServiceImpl implements IQuickEnterService {

    private static Logger log = LogManager.getLogger(QuickEnterServiceImpl.class);

    @Resource
    private QuickEnterModel quickEnterModel;

    @Override
    public ServiceResult<QuickEnter> getQuickEnterById(Integer id) {
        ServiceResult<QuickEnter> result = new ServiceResult<QuickEnter>();
        try {
            result.setResult(quickEnterModel.getQuickEnterById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IQuickEnterService][getQuickEnterById]根据id["+id+"]取得quick_enter对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IQuickEnterService][getQuickEnterById]根据id["+id+"]取得quick_enter对象时出现未知异常：", e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> saveQuickEnter(QuickEnter quickEnter) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(quickEnterModel.saveQuickEnter(quickEnter));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IQuickEnterService][saveQuickEnter]保存quick_enter对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IQuickEnterService][saveQuickEnter]保存quick_enter对象时出现未知异常：", e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> updateQuickEnter(QuickEnter quickEnter) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(quickEnterModel.updateQuickEnter(quickEnter));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IQuickEnterService][updateQuickEnter]更新quick_enter对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IQuickEnterService][updateQuickEnter]更新quick_enter对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> onOrOff(Integer id, String state) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            if(state==null||state.equals("")){
                new BusinessException("启用禁用状态不能为空");
            }
            QuickEnter quickEnter =quickEnterModel.getQuickEnterById(id);
            quickEnter.setState(state);
            result.setResult(quickEnterModel.update(quickEnter));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IQuickEnterService][onOrOff]更新quick_enter启用禁用状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IQuickEnterService][onOrOff]更新quick_enter启用禁用状态时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(quickEnterModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IQuickEnterService][del]删除quick_enter对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IQuickEnterService][del]删除quick_enter对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<QuickEnter>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<QuickEnter>> serviceResult = new ServiceResult<List<QuickEnter>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(quickEnterModel, "Property 'quickEnterModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(quickEnterModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(quickEnterModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IQuickEnterService][page]查询快速入口列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IQuickEnterService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IQuickEnterService][page]查询快速入口列表发生异常:", e);
        }
        return serviceResult;
    }
}
