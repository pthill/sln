package com.sln.web.controller.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.sln.web.controller.BaseController;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.TimeUtil;
import com.sln.core.WebUtil;
import com.sln.core.excel.CellConfig;
import com.sln.core.excel.ExcelConfig;
import com.sln.core.excel.ExcelManager;
import com.sln.core.exception.BusinessException;
import com.sln.dto.OrderDayDto;
import com.sln.entity.seller.Seller;
import com.sln.entity.settlement.Settlement;
import com.sln.service.order.IOrdersService;
import com.sln.service.seller.ISellerService;

@Controller
@RequestMapping(value = "admin/report")
public class AdminReportOrderController extends BaseController {

    @Resource
    private IOrdersService ordersService;
    @Resource
    private ISellerService sellerService;

    @RequestMapping(value = "orderday", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/report/reportorderday";
    }

    @RequestMapping(value = "orderday/list", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<List<OrderDayDto>> list(HttpServletRequest request,
                                                  Map<String, Object> dataMap) {
        HttpJsonResult<List<OrderDayDto>> jsonResult = new HttpJsonResult<List<OrderDayDto>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (StringUtil.isEmpty(queryMap.get("q_startTime"), true)
            && StringUtil.isEmpty(queryMap.get("q_endTime"), true)) {
            String day = TimeUtil.getToday();
            queryMap.put("q_startTime", day + " 00:00:00");
            queryMap.put("q_endTime", day + " 23:59:59");
        }

        ServiceResult<List<OrderDayDto>> serviceResult = ordersService.getOrderDayDto(queryMap);
        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
            List<OrderDayDto> orderDayDtos = serviceResult.getResult();

            BigDecimal moneyProduct = new BigDecimal(0);
            BigDecimal moneyLogistics = new BigDecimal(0);
            BigDecimal moneyOrder = new BigDecimal(0);
            BigDecimal moneyPaidBalance = new BigDecimal(0);
            BigDecimal moneyPaidReality = new BigDecimal(0);
            BigDecimal moneyBack = new BigDecimal(0);
            Integer count = 0;

            for (OrderDayDto orderDayDto : orderDayDtos) {
                moneyProduct = moneyProduct.add(orderDayDto.getMoneyProduct());
                moneyLogistics = moneyLogistics.add(orderDayDto.getMoneyLogistics());
                moneyOrder = moneyOrder.add(orderDayDto.getMoneyOrder());
                moneyPaidBalance = moneyPaidBalance.add(orderDayDto.getMoneyPaidBalance());
                moneyPaidReality = moneyPaidReality.add(orderDayDto.getMoneyPaidReality());
                moneyBack = moneyBack.add(orderDayDto.getMoneyBack());
                count += orderDayDto.getCount();
            }

            List<OrderDayDto> listFooter = new ArrayList<OrderDayDto>();
            OrderDayDto orderDayDtoFooter = new OrderDayDto();
            orderDayDtoFooter.setOrderDay("统计：");
            orderDayDtoFooter.setMoneyProduct(moneyProduct);
            orderDayDtoFooter.setMoneyLogistics(moneyLogistics);
            orderDayDtoFooter.setMoneyOrder(moneyOrder);
            orderDayDtoFooter.setMoneyPaidBalance(moneyPaidBalance);
            orderDayDtoFooter.setMoneyPaidReality(moneyPaidReality);
            orderDayDtoFooter.setCount(count);

            listFooter.add(orderDayDtoFooter);

            jsonResult.setRows(orderDayDtos);
            jsonResult.setFooter(listFooter);
        }

        return jsonResult;
    }
    
    
    /**
     * 导出每日订单统计列表
     * @param request
     * @param dataMap
     * @param response
     * @param userAgent 浏览器类型
     */
    @RequestMapping(value = "orderday/importlist",method = {RequestMethod.GET})
    @ResponseBody
    public void importlist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String day = TimeUtil.getToday();
        queryMap.put("q_startTime", day + " 00:00:00");
        queryMap.put("q_endTime", day + " 23:59:59");
        ServiceResult<List<OrderDayDto>> serviceResult = ordersService.getOrderDayDto(queryMap);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        this.export(response, userAgent, serviceResult.getResult());
    }
    
    /**
     * 导出excel数据
     * @param response
     * @param userAgent
     * @return
     */
    private void export(HttpServletResponse response, String userAgent, List<OrderDayDto> orderDayDtos) {
        ExcelConfig<OrderDayDto> config = new ExcelConfig<OrderDayDto>();
        config.setData(orderDayDtos);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("商家结算列表");
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        config.setLineConfig(getLineConfig());
        ExcelManager.export(response, config);
    }
    
    /**
     * 设置行参数
     * @return
     */
    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig orderDay=new CellConfig("日期");
        config.put("orderDay",orderDay);
        CellConfig moneyProduct=new CellConfig("商品金额");
        config.put("moneyProduct",moneyProduct);
        CellConfig moneyLogistics = new CellConfig("运费金额");
        config.put("moneyLogistics", moneyLogistics);
        CellConfig moneyOrder = new CellConfig("订单金额");
        config.put("moneyOrder", moneyOrder);
        CellConfig moneyPaidReality = new CellConfig("现金支付总额");
        config.put("moneyPaidReality", moneyPaidReality);
        CellConfig moneyPaidBalance = new CellConfig("余额支付金额");
        config.put("moneyPaidBalance", moneyPaidBalance);
        CellConfig moneyBack = new CellConfig("退款金额");
        config.put("moneyBack", moneyBack);
        CellConfig count = new CellConfig("订单数量");
        config.put("count", count);
        return config;
    }
}
