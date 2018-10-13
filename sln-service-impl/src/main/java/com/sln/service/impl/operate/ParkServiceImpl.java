package com.sln.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.Park;
import com.sln.model.operate.ParkModel;
import com.sln.service.operate.IParkService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "parkService")
public class ParkServiceImpl implements IParkService {
	private static Logger      log = LogManager.getLogger(ParkServiceImpl.class);
	
	@Resource
	private ParkModel parkModel;

	@Override
	public ServiceResult<Park> getParkById(Integer id) {
		ServiceResult<Park> serviceResult=new ServiceResult<Park>();
		try {
			Assert.notNull(parkModel, "Property 'parkModel' is required.");
			serviceResult.setResult(parkModel.getParkById(id));
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][getParkById]根据Id获取园区时出现异常：" + be.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][getParkById]根据Id获取园区时出现未知异常：", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<Park>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<Park>> serviceResult = new ServiceResult<List<Park>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(parkModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Park> list = parkModel.getPage(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ParkService][page] param1:" + JSON.toJSONString(queryMap) + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[ParkService][page] exception:", e);
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<Integer> insertPark(Park park) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			Assert.notNull(parkModel, "Property 'parkModel' is required.");
			serviceResult.setResult(parkModel.insertPark(park));
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][insertPark]保存园区时出现异常：" + be.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][insertPark]保存园区时出现未知异常：", e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateParkById(Park park) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(parkModel.updateParyById(park));
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][updateParkById]更新园区信息时出现异常：" + be.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][updateParkById]更新园区信息时出现未知异常：", e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> batchUpdateParkById(String ids, Integer state) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			Assert.notNull(parkModel, "Property 'parkModel' is required.");
			serviceResult.setResult(parkModel.batchUpdateParkById(ids, state));
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][batchUpdateParkById]批量更新园区时出现异常：" + be.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][batchUpdateParkById]批量更新园区时出现未知异常：", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<Park>> getParkList() {
		ServiceResult<List<Park>> serviceResult=new ServiceResult<List<Park>>();
		try{
			Assert.notNull(parkModel, "Property 'parkModel' is required.");
			serviceResult.setResult(parkModel.getParkList());
		}catch (BusinessException e){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(e.getMessage());
			log.error("[ParkService][getParkList]获取所有园区时出现异常：" + e.getMessage());
		}catch (Exception e){
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][getParkList]获取所有园区时出现未知异常：", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<Park>> getOperationsGroupByParkId() {
		ServiceResult<List<Park>>serviceResult=new ServiceResult<List<Park>>();
		try {
			serviceResult.setResult(parkModel.getOperationsGroupByParkId());
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][getOperationsGroupByParkId]根据园区分组查询业务管理方列表时出现异常：" + be.getMessage());
		}catch (Exception e){
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][getOperationsGroupByParkId]根据园区分组查询业务管理方列表发生异常:", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<Park>> getParkByArea(String area) {
		ServiceResult<List<Park>>serviceResult=new ServiceResult<List<Park>>();
		try {
			serviceResult.setResult(parkModel.getParkByArea(area));
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][getParkByArea]根据区域查询列表时出现异常：" + be.getMessage());
		}catch (Exception e){
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][getOperationsGroupByParkId]根据区域查询列表时出现异常:", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<String>> getArea() {
		ServiceResult<List<String>>serviceResult=new ServiceResult<List<String>>();
		try {
			serviceResult.setResult(parkModel.getArea());
		}catch (BusinessException be){
			serviceResult.setSuccess(false);
			serviceResult.setMessage(be.getMessage());
			log.error("[ParkService][getArea]获取所有区域时出现异常：" + be.getMessage());
		}catch (Exception e){
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
			log.error("[ParkService][getArea]获取所有区域时发生异常:", e);
		}
		return serviceResult;
	}

}