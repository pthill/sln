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
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.CourierCompany;
import com.sln.entity.order.Orders;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.entity.system.Regions;
import com.sln.service.operate.ICourierCompanyService;
import com.sln.service.order.IOrdersService;
import com.sln.service.seller.ISellerApplyService;
import com.sln.service.seller.ISellerService;
import com.sln.service.supplier.IOrderDeliveryService;
import com.sln.service.system.IRegionsService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 供应商发货管理
 * @author hlq
 *
 */
@Controller
@RequestMapping(value = "seller/supplier/orderdeliverycontroller")
public class OrderDeliveryController extends BaseController {
	     
	@Resource(name="orderDeliveryService")
	private IOrderDeliveryService deliveryService;
	
	@Resource
    private ICourierCompanyService courierCompanyService;
	
	@Resource(name = "ordersService")
    private IOrdersService         orderService;
	
	@Resource
    private ISellerService         sellerService;
	
	 @Resource
	private ISellerApplyService    sellerApplyService;
	 
	 @Resource
	private IRegionsService        resionsService;
	
	
	 /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/supplier/orderdelivery/waitOrderDeliveryList";
    }
    
    /**
     * 初始化已发货单页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "alreadydevlivery", method = { RequestMethod.GET })
    public String alreadydevliveryindex(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/supplier/orderdelivery/alreadyOrderDeliveryList";
    }
    
    
    
    /**
     * 管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrderDelivery>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        queryMap.put("q_supplierId", sellerUser.getSupplierId()+"");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<OrderDelivery>> serviceResult = deliveryService.page(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<OrderDelivery>> jsonResult = new HttpJsonResult<List<OrderDelivery>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    /**
     * 打开物流发货窗口
     */
    @RequestMapping(value = "pageSend", method = { RequestMethod.GET })
    public String pageSend(Map<String, Object> dataMap,Integer id,String orderSn) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        List<CourierCompany> list = courierCompanyService.list();
        dataMap.put("courierCompanylist", list);
        dataMap.put("id", id);
        dataMap.put("orderSn", orderSn);
        return "seller/supplier/orderdelivery/devlivery";
    }
    
    
    /**
     * 确认发货
     */
    @RequestMapping(value = "cofimdelivery", method = { RequestMethod.POST })
    public @ResponseBody ServiceResult<Integer> cofimDelivery(HttpServletRequest request,Map<String, Object> dataMap,Integer id,Integer logistics,String orderSn,String logisticsName,String waybillNumber){
    	ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
    	OrderDelivery orderDelivery = new OrderDelivery();
    	orderDelivery.setId(id);
    	orderDelivery.setLogistics(logistics);
    	orderDelivery.setOrderSn(orderSn);
    	orderDelivery.setLogisticsName(logisticsName);
    	orderDelivery.setState(2);
    	orderDelivery.setWaybillNumber(waybillNumber);
    	serviceResult = deliveryService.cofimDelivery(orderDelivery);
    	return serviceResult;
    }
    
    
    /**
     * 订单打印
     * 
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "printcourier", method = { RequestMethod.GET })
    public String printCourier(HttpServletRequest request, Map<String, Object> dataMap,
                               String orderSn,Integer logisticsId) {
        SellerUser user = WebSellerSession.getSellerUser(request);

        Orders orders = orderService.getOrdersBySn(orderSn).getResult();

        Seller seller = new Seller();
        SellerApply sellerApply = null;
        if (orders == null) {
            return "/seller/404";
        }
        if (!orders.getSellerId().equals(user.getSellerId())) {
            return "/seller/unauth";
        }
        if (orders != null) {
            seller = sellerService.getSellerById(orders.getSellerId()).getResult();
            if (seller != null) {
                sellerApply = sellerApplyService.getSellerApplyByUser(seller.getMemberId())
                    .getResult();
            }
        }

        CourierCompany courierCompany = courierCompanyService
            .getCourierCompanyById(logisticsId).getResult();
        dataMap.put("courierCompany", courierCompany);

        dataMap.put("sendName", seller.getSellerName());
        courierCompany.setContent(
            printString(courierCompany.getContent(), "sendName", seller.getSellerName()));
        dataMap.put("sendUnit", "");
        courierCompany.setContent(printString(courierCompany.getContent(), "sendUnit", ""));

        String province = "";
        String city = "";
        if (!StringUtil.isEmpty(sellerApply.getCompanyProvince(), true)) {
            Regions provinceRegion = resionsService
                .getRegionsById(ConvertUtil.toInt(sellerApply.getCompanyProvince(), 0)).getResult();
            province = provinceRegion == null ? "" : provinceRegion.getRegionName();

            Regions cityRegion = resionsService
                .getRegionsById(ConvertUtil.toInt(sellerApply.getCompanyCity(), 0)).getResult();
            city = cityRegion == null ? "" : cityRegion.getRegionName();
        }
        dataMap.put("sendProvince", province);
        courierCompany
            .setContent(printString(courierCompany.getContent(), "sendProvince", province));

        dataMap.put("sendCity", city);
        courierCompany.setContent(printString(courierCompany.getContent(), "sendCity", city));

        dataMap.put("sendAdds", sellerApply.getCompanyAdd());
        courierCompany.setContent(
            printString(courierCompany.getContent(), "sendAdds", sellerApply.getCompanyAdd()));

        dataMap.put("consigneeName", orders.getName());
        courierCompany.setContent(
            printString(courierCompany.getContent(), "consigneeName", orders.getName()));

        dataMap.put("sendPhone", "");
        courierCompany.setContent(printString(courierCompany.getContent(), "sendPhone", ""));

        dataMap.put("orders", orders);

        province = "";
        city = "";
        String area = "";
        Regions provinceRegion = resionsService
            .getRegionsById(ConvertUtil.toInt(orders.getProvinceId(), 0)).getResult();
        province = provinceRegion == null ? "" : provinceRegion.getRegionName();

        Regions cityRegion = resionsService.getRegionsById(ConvertUtil.toInt(orders.getCityId(), 0))
            .getResult();
        city = cityRegion == null ? "" : cityRegion.getRegionName();

        Regions areaRegion = resionsService.getRegionsById(ConvertUtil.toInt(orders.getAreaId(), 0))
            .getResult();
        area = areaRegion == null ? "" : areaRegion.getRegionName();

        dataMap.put("consigneeProvince", province);
        dataMap.put("consigneeCity", city);
        dataMap.put("consigneeArea", area);
        dataMap.put("consigneeAdds", orders.getAddressAll() + "" + orders.getAddressInfo());
        dataMap.put("sendGoods", "商品");

        courierCompany
            .setContent(printString(courierCompany.getContent(), "consigneeProvince", province));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeCity", city));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeArea", area));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeAdds",
            orders.getAddressAll() + "" + orders.getAddressInfo()));
        courierCompany.setContent(printString(courierCompany.getContent(), "sendGoods", "商品"));

        List<OrdersProduct> opList = orders.getOrderProductList();

        int number = 0;
        if (opList != null && opList.size() > 0) {
            for (OrdersProduct op : opList) {
                number += op.getNumber();
            }
        }

        dataMap.put("sendNumber", number);
        dataMap.put("consigneePhone", orders.getMobile());

        courierCompany
            .setContent(printString(courierCompany.getContent(), "sendNumber", number + ""));
        courierCompany.setContent(
            printString(courierCompany.getContent(), "consigneePhone", orders.getMobile()));

        return "seller/supplier/orderdelivery/ordersprintorder";
    }

    /**
     * 
     * @param content 打印的文档
     * @param str1 要替换的值
     * @param str2 替换的值
     */
    private String printString(String content, String str1, String str2) {
        String string = "\\$\\{" + str1 + "\\}";
        if (content == null || str1 == null || str1 == null) {
            return "";
        }
        return content.replaceAll(string, str2);
    }
}
