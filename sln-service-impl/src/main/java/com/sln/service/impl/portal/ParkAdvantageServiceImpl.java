package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.ParkAdvantage;
import com.sln.model.portal.ParkAdvantageModel;
import com.sln.service.portal.IParkAdvantageService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ParkAdvantageServiceImpl implements IParkAdvantageService{
    private static Logger log = LogManager.getLogger(PortalActiveServiceImpl.class);

    @Resource
    private ParkAdvantageModel parkAdvantageModel;

    @Override
    public ServiceResult<ParkAdvantage> getParkAdvantageById(Integer id) {
        ServiceResult<ParkAdvantage> result = new ServiceResult<ParkAdvantage>();
        try {
            result.setResult(parkAdvantageModel.getById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][getParkAdvantageById]根据id["+id+"]取得park_advantage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][getParkAdvantageById]根据id["+id+"]取得park_advantage对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveParkAdvantage(ParkAdvantage parkAdvantage) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parkAdvantageModel.saveParkAdvantage(parkAdvantage));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][saveParkAdvantage]保存park_advantage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][saveParkAdvantage]保存park_advantage对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> updateParkAdvantage(ParkAdvantage parkAdvantage) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parkAdvantageModel.updateParkAdvantage(parkAdvantage));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][updateParkAdvantage]更新park_advantage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][updateParkAdvantage]更新park_advantage对象时出现未知异常：", e);
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
            ParkAdvantage parkAdvantage =parkAdvantageModel.getById(id);
            parkAdvantage.setState(state);
            result.setResult(parkAdvantageModel.updateParkAdvantage(parkAdvantage));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][onOrOff]更新park_advantage启用禁用状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][onOrOff]更新park_advantage启用禁用状态时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<List<ParkAdvantage>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ParkAdvantage>> serviceResult = new ServiceResult<List<ParkAdvantage>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(parkAdvantageModel, "Property 'parkAdvantageModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(parkAdvantageModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(parkAdvantageModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][page]查询园区优势列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IParkAdvantageService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IParkAdvantageService][page]查询园区优势列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parkAdvantageModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][del]删除park_advantage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][del]删除park_advantage对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ParkAdvantage>> getByParkId(Integer parkId) {
        ServiceResult<List<ParkAdvantage>> result = new ServiceResult<>();
        try {
            result.setResult(parkAdvantageModel.getByParkId(parkId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParkAdvantageService][getByParkId]获取park_advantage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParkAdvantageService][getByParkId]获取park_advantage对象时出现未知异常：", e);
        }
        return result;
    }

}
