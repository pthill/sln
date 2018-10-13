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
import com.sln.entity.flash.ActFlashBanner;
import com.sln.model.promotion.ActFlashBannerModel;
import com.sln.service.promotion.IActFlashBannerService;

@Service(value = "actFlashBannerService")
public class ActFlashBannerServiceImpl implements IActFlashBannerService {
    private static Logger       log = LogManager.getLogger(ActFlashBannerServiceImpl.class);

    @Resource
    private ActFlashBannerModel actFlashBannerModel;

    @Override
    public ServiceResult<ActFlashBanner> getActFlashBannerById(Integer actFlashBannerId) {
        ServiceResult<ActFlashBanner> result = new ServiceResult<ActFlashBanner>();
        try {
            result.setResult(actFlashBannerModel.getActFlashBannerById(actFlashBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFlashBannerService][getActFlashBannerById]根据id[" + actFlashBannerId
                      + "]取得限时抢购首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][getActFlashBannerById]根据id[" + actFlashBannerId
                      + "]取得限时抢购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActFlashBanner(ActFlashBanner actFlashBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashBannerModel.saveActFlashBanner(actFlashBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFlashBannerService][saveActFlashBanner]保存限时抢购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][saveActFlashBanner]保存限时抢购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActFlashBanner(ActFlashBanner actFlashBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashBannerModel.updateActFlashBanner(actFlashBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFlashBannerService][updateActFlashBanner]更新限时抢购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][updateActFlashBanner]更新限时抢购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActFlashBanner(Integer actFlashBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashBannerModel.deleteActFlashBanner(actFlashBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFlashBannerService][deleteActFlashBanner]删除限时抢购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][deleteActFlashBanner]删除限时抢购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFlashBanner>> getActFlashBanners(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<ActFlashBanner>> serviceResult = new ServiceResult<List<ActFlashBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actFlashBannerModel.getActFlashBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actFlashBannerModel.getActFlashBanners(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActFlashBannerService][getActFlashBanners]根据条件取得限时抢购首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][getActFlashBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActFlashBannerService][getActFlashBanners]根据条件取得限时抢购首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 查询限时抢购首页轮播图
     * @param pcMobile
     * @return
     * @see com.sln.service.promotion.IActFlashBannerService#getActFlashBannersPcMobile(int)
     */
    @Override
    public ServiceResult<List<ActFlashBanner>> getActFlashBannersPcMobile(int pcMobile) {
        ServiceResult<List<ActFlashBanner>> serviceResult = new ServiceResult<List<ActFlashBanner>>();
        try {
            serviceResult.setResult(actFlashBannerModel.getActFlashBannersPcMobile(pcMobile));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActFlashBannerService][getActFlashBannersPcMobile]根据条件取得限时抢购首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashBannerService][getActFlashBannersPcMobile]根据条件取得限时抢购首页轮播图时发生异常:",
                e);
        }
        return serviceResult;
    }

}