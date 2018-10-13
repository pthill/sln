package com.sln.service.impl.pcindex;

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
import com.sln.entity.pcindex.PcRecommend;
import com.sln.model.pcindex.PcRecommendModel;
import com.sln.service.pcindex.IPcRecommendService;

@Service(value = "pcRecommendService")
public class PcRecommendServiceImpl implements IPcRecommendService {
    private static Logger    log = LogManager.getLogger(PcRecommendServiceImpl.class);

    @Resource
    private PcRecommendModel pcRecommendModel;

    @Override
    public ServiceResult<PcRecommend> getPcRecommendById(Integer pcRecommendId) {
        ServiceResult<PcRecommend> result = new ServiceResult<PcRecommend>();
        try {
            result.setResult(pcRecommendModel.getPcRecommendById(pcRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcRecommendService][getPcRecommendById]根据id[" + pcRecommendId
                      + "]取得PC端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][getPcRecommendById]根据id[" + pcRecommendId
                      + "]取得PC端推荐商品时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcRecommend(PcRecommend pcRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcRecommendModel.savePcRecommend(pcRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcRecommendService][savePcRecommend]保存PC端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][savePcRecommend]保存PC端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcRecommend(PcRecommend pcRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcRecommendModel.updatePcRecommend(pcRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcRecommendService][updatePcRecommend]更新PC端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][updatePcRecommend]更新PC端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcRecommend(Integer pcRecommendId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcRecommendModel.deletePcRecommend(pcRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcRecommendService][deletePcRecommend]删除PC端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][deletePcRecommend]删除PC端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcRecommend>> getPcRecommends(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<PcRecommend>> serviceResult = new ServiceResult<List<PcRecommend>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcRecommendModel.getPcRecommendsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(pcRecommendModel.getPcRecommends(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcRecommendService][getPcRecommends]根据条件取得PC端推荐商品时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][getPcRecommends]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcRecommendService][getPcRecommends]根据条件取得PC端推荐商品时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcRecommend>> getPcRecommendForView(Integer recommendType,
                                                                  Boolean isPreview,
                                                                  PagerInfo pager) {
        ServiceResult<List<PcRecommend>> result = new ServiceResult<List<PcRecommend>>();
        result.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(
                    pcRecommendModel.getPcRecommendForViewCount(recommendType, isPreview));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            result.setResult(
                pcRecommendModel.getPcRecommendForView(recommendType, isPreview, start, size));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcRecommendService][getPcRecommendForView]取得PC端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcRecommendService][getPcRecommendForView]取得PC端推荐商品时发生异常:", e);
        }
        return result;
    }

}