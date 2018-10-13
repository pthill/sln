package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.IndexBanner;
import com.sln.entity.portal.RecommendService;
import com.sln.model.portal.RecommendServiceModel;
import com.sln.service.portal.IRecommendServiceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RecommendServiceServiceImpl implements IRecommendServiceService {
    private static Logger log = LogManager.getLogger(QuickEnterServiceImpl.class);

    @Resource
    private RecommendServiceModel recommendServiceModel;

    @Override
    public ServiceResult<RecommendService> getRecommendServiceById(Integer id) {
        ServiceResult<RecommendService> result = new ServiceResult<RecommendService>();
        try {
            result.setResult(recommendServiceModel.getById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][getRecommendServiceById]根据id["+id+"]取得recommend_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][getRecommendServiceById]根据id["+id+"]取得recommend_service对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<RecommendService>> getByParkId(Integer parkId) {
        ServiceResult<List<RecommendService>> result = new ServiceResult<List<RecommendService>>();
        try {
            result.setResult(recommendServiceModel.getByParkId(parkId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][getByParkId]根据parkId["+parkId+"]取得recommend_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][getByParkId]根据parkId["+parkId+"]取得recommend_service对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveRecommendService(RecommendService recommendService) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(recommendServiceModel.saveRecommendService(recommendService));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][saveRecommendService]保存recommend_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][saveRecommendService]保存recommend_service对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateRecommendService(RecommendService recommendService) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(recommendServiceModel.update(recommendService));
            result.setMessage("更新成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][updateRecommendService]更新recommend_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][updateRecommendService]更新recommend_service对象时出现未知异常：",
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
            RecommendService recommendService=recommendServiceModel.getById(id);
            recommendService.setState(state);
            result.setResult(recommendServiceModel.update(recommendService));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][onOrOff]更新recommend_service启用禁用状态时对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][onOrOff]更新recommend_service启用禁用状态时对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(recommendServiceModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][del]删除recommend_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IRecommendServiceService][del]删除recommend_service对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<RecommendService>> page(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<RecommendService>> serviceResult = new ServiceResult<List<RecommendService>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(recommendServiceModel, "Property 'recommendServiceModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(recommendServiceModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(recommendServiceModel.getPage(queryMap, size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IRecommendServiceService][page]查询推荐服务列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IRecommendServiceService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IRecommendServiceService][page]查询推荐服务列表发生异常:", e);
        }
        return serviceResult;
    }

}
