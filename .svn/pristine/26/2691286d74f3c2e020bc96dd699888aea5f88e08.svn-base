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
import com.sln.entity.integral.ActIntegralBanner;
import com.sln.model.promotion.ActIntegralBannerModel;
import com.sln.service.promotion.IActIntegralBannerService;

@Service(value = "actIntegralBannerService")
public class ActIntegralBannerServiceImpl implements IActIntegralBannerService {
    private static Logger          log = LogManager.getLogger(ActIntegralBannerServiceImpl.class);

    @Resource
    private ActIntegralBannerModel actIntegralBannerModel;

    @Override
    public ServiceResult<ActIntegralBanner> getActIntegralBannerById(Integer actIntegralBannerId) {
        ServiceResult<ActIntegralBanner> result = new ServiceResult<ActIntegralBanner>();
        try {
            result.setResult(actIntegralBannerModel.getActIntegralBannerById(actIntegralBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActIntegralBannerService][getActIntegralBannerById]根据id["
                      + actIntegralBannerId + "]取得积分商城首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActIntegralBannerService][getActIntegralBannerById]根据id["
                      + actIntegralBannerId + "]取得积分商城首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActIntegralBanner(ActIntegralBanner actIntegralBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralBannerModel.saveActIntegralBanner(actIntegralBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActIntegralBannerService][saveActIntegralBanner]保存积分商城首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActIntegralBannerService][saveActIntegralBanner]保存积分商城首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActIntegralBanner(ActIntegralBanner actIntegralBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralBannerModel.updateActIntegralBanner(actIntegralBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActIntegralBannerService][updateActIntegralBanner]更新积分商城首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActIntegralBannerService][updateActIntegralBanner]更新积分商城首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActIntegralBanner(Integer actIntegralBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralBannerModel.deleteActIntegralBanner(actIntegralBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActIntegralBannerService][deleteActIntegralBanner]删除积分商城首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActIntegralBannerService][deleteActIntegralBanner]删除积分商城首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActIntegralBanner>> getActIntegralBanners(Map<String, String> queryMap,
                                                                        PagerInfo pager) {
        ServiceResult<List<ActIntegralBanner>> serviceResult = new ServiceResult<List<ActIntegralBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actIntegralBannerModel.getActIntegralBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actIntegralBannerModel.getActIntegralBanners(queryMap, start,
                size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActIntegralBannerService][getActIntegralBanners]根据条件取得积分商城首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActIntegralBannerService][getActIntegralBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActIntegralBannerService][getActIntegralBanners]根据条件取得积分商城首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 查询积分商城首页轮播图
     * @param pcMobile
     * @return
     * @see com.sln.service.promotion.IActIntegralBannerService#getActIntegralBannersPcMobile(int)
     */
    @Override
    public ServiceResult<List<ActIntegralBanner>> getActIntegralBannersPcMobile(int pcMobile) {
        ServiceResult<List<ActIntegralBanner>> serviceResult = new ServiceResult<List<ActIntegralBanner>>();
        try {
            serviceResult.setResult(actIntegralBannerModel.getActIntegralBannersPcMobile(pcMobile));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActIntegralBannerService][getActIntegralBannersPcMobile]根据条件取得积分商城首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IActIntegralBannerService][getActIntegralBannersPcMobile]根据条件取得积分商城首页轮播图时发生异常:",
                e);
        }
        return serviceResult;
    }

}