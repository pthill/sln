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
import com.sln.dto.ProductDayDto;
import com.sln.entity.seller.Seller;
import com.sln.service.order.IOrdersProductService;
import com.sln.service.seller.ISellerService;

@Controller
@RequestMapping(value = "admin/report")
public class AdminReportProductController extends BaseController {

    @Resource
    private IOrdersProductService ordersProductService;
    @Resource
    private ISellerService        sellerService;

    @RequestMapping(value = "productday", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/report/reportproductday";
    }

    @RequestMapping(value = "productday/list", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<List<ProductDayDto>> productDayList(HttpServletRequest request,
                                                              Map<String, Object> dataMap) {
        HttpJsonResult<List<ProductDayDto>> jsonResult = new HttpJsonResult<List<ProductDayDto>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (StringUtil.isEmpty(queryMap.get("q_startTime"), true)
            && StringUtil.isEmpty(queryMap.get("q_endTime"), true)) {
            String day = TimeUtil.getToday();
            queryMap.put("q_startTime", day + " 00:00:00");
            queryMap.put("q_endTime", day + " 23:59:59");
        }

        ServiceResult<List<ProductDayDto>> serviceResult = ordersProductService
            .getProductDayDto(queryMap);
        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
            List<ProductDayDto> productDayDtos = serviceResult.getResult();

            BigDecimal moneyAmount = new BigDecimal(0);
            Integer number = 0;

            for (ProductDayDto productDayDto : productDayDtos) {
                moneyAmount = moneyAmount.add(productDayDto.getMoneyAmount());
                number += productDayDto.getNumber();
            }

            List<ProductDayDto> listFooter = new ArrayList<ProductDayDto>();
            ProductDayDto productDayDtoFooter = new ProductDayDto();
            productDayDtoFooter.setSellerName("统计：");
            productDayDtoFooter.setMoneyAmount(moneyAmount);
            productDayDtoFooter.setNumber(number);

            listFooter.add(productDayDtoFooter);

            jsonResult.setRows(productDayDtos);
            jsonResult.setFooter(listFooter);
        }

        return jsonResult;
    }
    
    /**
     * 导出每日商品销量报表统计列表
     * @param request
     * @param dataMap
     * @param response
     * @param userAgent 浏览器类型
     */
    @RequestMapping(value = "productday/importlist",method = {RequestMethod.GET})
    @ResponseBody
    public void importlist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String day = TimeUtil.getToday();
        queryMap.put("q_startTime", day + " 00:00:00");
        queryMap.put("q_endTime", day + " 23:59:59");
        ServiceResult<List<ProductDayDto>> serviceResult = ordersProductService.getProductDayDto(queryMap);
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
    private void export(HttpServletResponse response, String userAgent, List<ProductDayDto> productDayDtos) {
        ExcelConfig<ProductDayDto> config = new ExcelConfig<ProductDayDto>();
        config.setData(productDayDtos);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("每日商品销量列表");
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
        CellConfig productName=new CellConfig("商品名称");
        config.put("productName",productName);
        CellConfig sellerName = new CellConfig("店铺名称");
        config.put("sellerName", sellerName);
        CellConfig moneyAmount = new CellConfig("金额");
        config.put("moneyAmount", moneyAmount);
        CellConfig number = new CellConfig("数量");
        config.put("number", number);
        return config;
    }
}
