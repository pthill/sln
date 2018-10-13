package com.sln.web.controller.supplier;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.CourierCompany;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.entity.supplier.SupplierExchange;
import com.sln.service.operate.ICourierCompanyService;
import com.sln.service.supplier.IOrderDeliveryService;
import com.sln.service.supplier.ISupplierExchangeService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 供应商发货管理
 * @author hlq
 *
 */
@Controller
@RequestMapping(value = "seller/supplier/supplierexchange")
public class SupplierExchangeController extends BaseController {
	     
	
	@Resource
	private ISupplierExchangeService supplierExchangeService;
	
	 /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/supplier/supplierExchange/supplierExchangeList";
    }
    
    
    
    /**
     * 管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SupplierExchange>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        queryMap.put("q_supplierId", sellerUser.getSupplierId()+"");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SupplierExchange>> serviceResult = supplierExchangeService.page(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SupplierExchange>> jsonResult = new HttpJsonResult<List<SupplierExchange>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    /**
     * 操作供应商换货单 确认收货，确认发货，不予处理
     */
    @RequestMapping(value = "updateState", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Integer> updateStateByid(HttpServletRequest request,HttpServletResponse response
    		,Integer id,Integer exchangeId,Integer exchangeState){
    	HttpJsonResult<Integer> httpJsonResult = new HttpJsonResult<Integer>();
    	
    	SupplierExchange supplierExchange = new SupplierExchange();
    	supplierExchange.setId(id);
    	supplierExchange.setExchangeId(exchangeId);
    	supplierExchange.setExchangeState(exchangeState);
    	
    	ServiceResult<Integer> serviceResult = supplierExchangeService.updateStateById(supplierExchange);
    	if(serviceResult.getResult()<1){
    		httpJsonResult.setMessage("操作异常，请稍后重试");
    	}
    	return httpJsonResult;
    }
}
