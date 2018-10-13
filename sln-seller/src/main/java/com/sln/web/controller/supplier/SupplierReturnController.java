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
import com.sln.entity.supplier.SupplierReturn;
import com.sln.service.operate.ICourierCompanyService;
import com.sln.service.supplier.IOrderDeliveryService;
import com.sln.service.supplier.ISupplierReturnService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 供应商发货管理
 * @author hlq
 *
 */
@Controller
@RequestMapping(value = "seller/supplier/supplierreturn")
public class SupplierReturnController extends BaseController {
	     
	@Resource
	private ISupplierReturnService supplierReturnService;
	
	
	 /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/supplier/supplierReturn/supplierReturnList";
    }
    
    /**
     * 管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SupplierReturn>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("sellerId", sellerUser.getSellerId() + "");
        queryMap.put("supplierId", sellerUser.getSupplierId()+"");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, queryMap);
        ServiceResult<List<SupplierReturn>> serviceResult = supplierReturnService.page(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SupplierReturn>> jsonResult = new HttpJsonResult<List<SupplierReturn>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    /**
     * 确认收货
     */
    
    @RequestMapping(value = "cofimdelivery", method = { RequestMethod.POST })
    public @ResponseBody ServiceResult<Integer> cofimDelivery(HttpServletRequest request,Map<String, Object> dataMap,Integer id,Integer backId,String orderSn){
    	ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
    	SupplierReturn supplierReturn = new SupplierReturn();
    	supplierReturn.setBackId(backId);
    	supplierReturn.setOrderSn(orderSn);
    	supplierReturn.setId(id);
    	serviceResult = supplierReturnService.cofimReceipt(supplierReturn);
    	if(serviceResult.getResult()<1){
    		serviceResult.setMessage("操作异常，请稍后重试");
    	}
    	return serviceResult;
    }
}
