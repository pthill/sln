package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.IndexBanner;
import com.sln.entity.portal.PortalMenu;
import com.sln.model.portal.PortalIndexBannerModel;
import com.sln.service.portal.IPortalIndexBannerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "indexBannerService")
public class PortalIndexBannerServiceImpl implements IPortalIndexBannerService {
    private static Logger log = LogManager.getLogger(PortalIndexBannerServiceImpl.class);

    @Resource
    private PortalIndexBannerModel indexBannerModel;

    @Override
    public ServiceResult<IndexBanner> getIndexBannerById(Integer indexBannerId) {
        ServiceResult<IndexBanner> result = new ServiceResult<IndexBanner>();
        try {
            if(indexBannerId==null){
                new BusinessException("id不能为空");
            }
            result.setResult(indexBannerModel.getIndexBannerById(indexBannerId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][getIndexBannerById]根据id["+indexBannerId+"]取得index_banner对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][getIndexBannerById]根据id["+indexBannerId+"]取得index_banner对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveIndexBanner(IndexBanner indexBanner) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(indexBannerModel.save(indexBanner));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][saveIndexBanner]保存index_banner对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][saveIndexBanner]保存index_banner对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateIndexBanner(IndexBanner indexBanner) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(indexBannerModel.update(indexBanner));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][updateIndexBanner]更新index_banner对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][updateIndexBanner]更新index_banner对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            result.setResult(indexBannerModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][del]删除轮播图时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][del]删除轮播图时出现未知异常：",
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
            IndexBanner indexBanner=indexBannerModel.getIndexBannerById(id);
            indexBanner.setState(state);
            result.setResult(indexBannerModel.update(indexBanner));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][onOrOff]更新index_banner启用禁用状态时对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][onOrOff]更新index_banner启用禁用状态时对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<IndexBanner>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<IndexBanner>> serviceResult = new ServiceResult<List<IndexBanner>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(indexBannerModel, "Property 'indexBannerModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(indexBannerModel.getCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(indexBannerModel.getPage(queryMap,size, start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][page]查询首页轮播列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalIndexBannerService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPortalIndexBannerService][page]查询首页轮播表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IndexBanner>> getBannerList(Integer parkId,String type) {
        ServiceResult<List<IndexBanner>> result = new ServiceResult<List<IndexBanner>>();
        try {
            result.setResult(indexBannerModel.getBannerList(parkId,type));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalIndexBannerService][getBannerList]根据parkId["+parkId+"]取得index_banner对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalIndexBannerService][getBannerList]根据parkId["+parkId+"]取得index_banner对象时出现未知异常：", e);
        }
        return result;
    }
}
