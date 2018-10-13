package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.QuickEnter;
import com.sln.entity.portal.ShopActive;
import com.sln.model.portal.ShopActiveModel;
import com.sln.service.portal.IShopActiveService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ShopActiveServiceImpl implements IShopActiveService {

    private static Logger log = LogManager.getLogger(ShopActiveServiceImpl.class);
    @Resource
    private ShopActiveModel shopActiveModel;

    @Override
    public ServiceResult<ShopActive> getShopActiveById(Integer id) {
        ServiceResult<ShopActive> result = new ServiceResult<ShopActive>();
        try {
            result.setResult(shopActiveModel.getById(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][getShopActiveById]根据id["+id+"]取得shop_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IShopActiveService][getShopActiveById]根据id["+id+"]取得shop_active对象时出现未知异常：", e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> saveShopActive(ShopActive shopActive) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(shopActiveModel.saveShopActive(shopActive));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][saveShopActive]保存shop_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IShopActiveService][saveShopActive]保存shop_active对象时出现未知异常：", e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> updateShopActive(ShopActive shopActive) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(shopActiveModel.updateShopActive(shopActive));
            result.setMessage("更新成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][updateShopActive]更新shop_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IShopActiveService][updateShopActive]更新shop_active对象时出现未知异常：", e);
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
            result.setResult(shopActiveModel.updateStatus(id,state));
            result.setMessage("更新成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][onOrOff]更新shop_active启用禁用状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][onOrOff]更新shop_active启用禁用状态时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(shopActiveModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IShopActiveService][del]删除shop_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IShopActiveService][del]删除shop_active对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ShopActive>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ShopActive>> serviceResult = new ServiceResult<List<ShopActive>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(shopActiveModel, "Property 'shopActiveModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(shopActiveModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(shopActiveModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IShopActiveService][page]查询电商活动列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IShopActiveService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IShopActiveService][page]查询电商活动列表发生异常:", e);
        }
        return serviceResult;
    }
}
