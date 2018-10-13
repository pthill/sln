package com.sln.service.impl.mindex;

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
import com.sln.entity.mindex.MRecommend;
import com.sln.model.mindex.MRecommendModel;
import com.sln.service.mindex.IMRecommendService;

@Service(value = "mRecommendService")
public class MRecommendServiceImpl implements IMRecommendService {

    private static Logger   log = LogManager.getLogger(MRecommendServiceImpl.class);

    @Resource
    private MRecommendModel mRecommendModel;

    @Override
    public ServiceResult<MRecommend> getMRecommendById(Integer mRecommendId) {
        ServiceResult<MRecommend> result = new ServiceResult<MRecommend>();
        try {
            result.setResult(mRecommendModel.getMRecommendById(mRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMRecommendService][getMRecommendById]根据id[" + mRecommendId
                      + "]取得M端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][getMRecommendById]根据id[" + mRecommendId
                      + "]取得M端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveMRecommend(MRecommend mRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mRecommendModel.saveMRecommend(mRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMRecommendService][saveMRecommend]保存M端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][saveMRecommend]保存M端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateMRecommend(MRecommend mRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mRecommendModel.updateMRecommend(mRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMRecommendService][updateMRecommend]更新M端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][updateMRecommend]更新M端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteMRecommend(Integer mRecommendId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mRecommendModel.deleteMRecommend(mRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMRecommendService][deleteMRecommend]删除M端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][deleteMRecommend]删除M端推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MRecommend>> getMRecommends(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<MRecommend>> serviceResult = new ServiceResult<List<MRecommend>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(mRecommendModel.getMRecommendsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(mRecommendModel.getMRecommends(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IMRecommendService][getMRecommends]根据条件取得M端推荐商品时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][getMRecommends]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IMRecommendService][getMRecommends]根据条件取得M端推荐商品时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MRecommend>> getMRecommendForView(Integer recommendType,
                                                                Boolean isPreview, PagerInfo pager) {
        ServiceResult<List<MRecommend>> result = new ServiceResult<List<MRecommend>>();
        result.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(mRecommendModel.getMRecommendForViewCount(recommendType,
                    isPreview));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            result.setResult(mRecommendModel.getMRecommendForView(recommendType, isPreview, start,
                size));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMRecommendService][getMRecommendForView]取得M端推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMRecommendService][getMRecommendForView]取得M端推荐商品时发生异常:", e);
        }
        return result;
    }

}