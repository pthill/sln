package com.sln.web.controller.promotion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.excel.CellConfig;
import com.sln.core.excel.ExcelConfig;
import com.sln.core.excel.ExcelManager;
import com.sln.core.exception.BusinessException;
import com.sln.entity.integral.ActIntegral;
import com.sln.entity.integral.ActIntegralType;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.seller.Seller;
import com.sln.entity.settlement.Settlement;
import com.sln.service.promotion.ActUnitIntegralService;
import com.sln.web.controller.BaseController;

/**
 * 单位积分消费
 * @author hulongqiao
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/unitactintegral")
public class AdminActIntegralStatisticsController extends BaseController {
	
	@Resource
	private ActUnitIntegralService actUnitIntegralService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        return "admin/promotion/integral/unitintegrallist";
    }
	
	
	/**
     * 单位积分消费列表页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List> list(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List> serviceResult = actUnitIntegralService.getIntegralStatistics(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<List> jsonResult =  new HttpJsonResult<List>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
	
	/**
     * 单位积分消费列表页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
	@RequestMapping(value = "detailist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List> detailist(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List> serviceResult = actUnitIntegralService.getIntegralDetail(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<List> jsonResult =  new HttpJsonResult<List>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
	
	/**
	 * 导出各部门消费积分
	 */
	@RequestMapping(value = "importlist",method = {RequestMethod.GET})
    @ResponseBody
    public void importlist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List> serviceResult = actUnitIntegralService.getIntegralStatistics(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        this.export(response, userAgent, serviceResult.getResult(),1);
	}
	
	/**
	 * 导出单个消费积分明细
	 */
	@RequestMapping(value = "impordetaillist",method = {RequestMethod.GET})
    @ResponseBody
    public void impordetaillist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List> serviceResult = actUnitIntegralService.getIntegralDetail(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        this.export(response, userAgent, serviceResult.getResult(),2);
	}
	
	private void export(HttpServletResponse response, String userAgent, List list,int type) {
        ExcelConfig<Settlement> config = new ExcelConfig<Settlement>();
        config.setData(list);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        if(type ==1) {
        	 config.setFileName("积分消费统计");
        }else {
        	 config.setFileName("单部门积分消费统计");
        }
       
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        if(type ==1) {
        	config.setLineConfig(getLineConfig());
        }else {
        	config.setLineConfig(getDetailLineConfig());
        }
        
        ExcelManager.export(response, config);
    }
	
	/**
	 * 设置导出excel 行参数
	 */
	private LinkedHashMap<String, CellConfig> getLineConfig(){
		LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
		CellConfig company=new CellConfig("公司");
	    config.put("company",company);
	    CellConfig dept=new CellConfig("部门");
	    config.put("dept",dept);	
	    CellConfig xfintegral=new CellConfig("消费积分");
	    config.put("xfintegral",xfintegral);	
	    CellConfig pfsumintegral=new CellConfig("派发积分");
	    config.put("pfsumintegral",pfsumintegral);
	    CellConfig sumvalue=new CellConfig("剩余积分");
	    config.put("sumvalue",sumvalue);
	    CellConfig tkintegral=new CellConfig("退款积分");
	    config.put("tkintegral",tkintegral);
		return config;
	}
	
	/**
	 * 设置导出详情excel 行参数
	 */
	private LinkedHashMap<String, CellConfig> getDetailLineConfig(){
		LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
		CellConfig name=new CellConfig("姓名");
	    config.put("name",name);
	    CellConfig mobile=new CellConfig("手机号");
	    config.put("mobile",mobile);	
	    CellConfig ref_code=new CellConfig("订单号");
	    config.put("ref_code",ref_code);	
	    CellConfig value=new CellConfig("消费积分");
	    config.put("value",value);
	    CellConfig create_time=new CellConfig("消费时间");
	    config.put("create_time",create_time);
		return config;
	}
}
