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
import com.sln.entity.pcindex.PcIndexImage;
import com.sln.model.pcindex.PcIndexImageModel;
import com.sln.service.pcindex.IPcIndexImageService;

@Service(value = "pcIndexImageService")
public class PcIndexImageServiceImpl implements IPcIndexImageService {
    private static Logger     log = LogManager.getLogger(PcIndexImageServiceImpl.class);

    @Resource
    private PcIndexImageModel pcIndexImageModel;

    @Override
    public ServiceResult<PcIndexImage> getPcIndexImageById(Integer pcIndexImageId) {
        ServiceResult<PcIndexImage> result = new ServiceResult<PcIndexImage>();
        try {
            result.setResult(pcIndexImageModel.getPcIndexImageById(pcIndexImageId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexImageService][getPcIndexImageById]根据id[" + pcIndexImageId
                      + "]取得PC端首页的一些图片时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][getPcIndexImageById]根据id[" + pcIndexImageId
                      + "]取得PC端首页的一些图片时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcIndexImage(PcIndexImage pcIndexImage) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexImageModel.savePcIndexImage(pcIndexImage));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexImageService][savePcIndexImage]保存PC端首页的一些图片时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][savePcIndexImage]保存PC端首页的一些图片时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcIndexImage(PcIndexImage pcIndexImage) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexImageModel.updatePcIndexImage(pcIndexImage));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexImageService][updatePcIndexImage]更新PC端首页的一些图片时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][updatePcIndexImage]更新PC端首页的一些图片时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcIndexImage(Integer pcIndexImageId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexImageModel.deletePcIndexImage(pcIndexImageId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexImageService][deletePcIndexImage]删除PC端首页的一些图片时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][deletePcIndexImage]删除PC端首页的一些图片时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcIndexImage>> getPcIndexImages(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<PcIndexImage>> serviceResult = new ServiceResult<List<PcIndexImage>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcIndexImageModel.getPcIndexImagesCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(pcIndexImageModel.getPcIndexImages(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcIndexImageService][getPcIndexImages]根据条件取得PC端首页的一些图片时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][getPcIndexImages]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcIndexImageService][getPcIndexImages]根据条件取得PC端首页的一些图片时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcIndexImage>> getPcIndexImageForView(Boolean isPreview, int type) {
        ServiceResult<List<PcIndexImage>> result = new ServiceResult<List<PcIndexImage>>();
        try {
            result.setResult(pcIndexImageModel.getPcIndexImageForView(isPreview, type));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexImageService][getPcIndexImageForView]取得PC端首页的一些图片时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexImageService][getPcIndexImageForView]取得PC端首页的一些图片时发生异常:", e);
        }
        return result;
    }

}