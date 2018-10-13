package com.sln.web.controller.order;

import com.sln.core.*;
import com.sln.core.excel.CellConfig;
import com.sln.core.excel.ExcelConfig;
import com.sln.core.excel.ExcelManager;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.member.IMemberProductBackService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户退货商家管理controller
 *
 * @Filename: AdminProductBackController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/order/productBack")
public class AdminProductBackController extends BaseController {
    Logger                            log = Logger.getLogger(this.getClass());

    @Resource
    private IMemberProductBackService memberProductBackService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/productback/backlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductBack>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductBack>> jsonResult = new HttpJsonResult<List<MemberProductBack>>();
        jsonResult.setRows((List<MemberProductBack>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "backmoney", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> backmoney(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Integer id) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ServiceResult<Boolean> serviceResult = memberProductBackService.backMoney(id,
            adminUser.getId(), adminUser.getName());
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        if (serviceResult.getResult() == null || !serviceResult.getResult()) {
            return new HttpJsonResult<Boolean>("退款失败，请重试！");
        }
        return new HttpJsonResult<Boolean>();
    }

    @RequestMapping(value = "/downExecl",method = {RequestMethod.GET})
    @ResponseBody
    public void downEcexl(HttpServletRequest request,Map<String, Object> dataMap,
                          HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        //导出去掉不予处理的退货订单
        dataMap.put("state","4");
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService.page(queryMap, pager);
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
    private void export(HttpServletResponse response, String userAgent,
                        List<MemberProductBack> memberProductBacks) {
        ExcelConfig<MemberProductBack> config = new ExcelConfig<MemberProductBack>();
        config.setData(memberProductBacks);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("待退款订单列表");
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        config.setLineConfig(getLineConfig());
        ExcelManager.export(response, config);
    }

    /**
     * 取得行配置
     * @return
     */
    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig pc=new CellConfig("退款批次号");
        config.put("pc",pc);

        CellConfig productBackSn=new CellConfig("退货单号");
        config.put("productBackSn",productBackSn);

        CellConfig orderSn = new CellConfig("订单号");
        config.put("orderSn", orderSn);

        CellConfig paySn = new CellConfig("支付订单号");
        config.put("paySn", paySn);

        CellConfig memberName = new CellConfig("用户名");
        config.put("memberName", memberName);

        CellConfig backMoney = new CellConfig("退款金额");
        config.put("backMoney", backMoney);

        CellConfig stateMoney = new CellConfig("退款状态");
        config.put("moneyState", stateMoney);

        CellConfig stateReturn = new CellConfig("退货状态");
        config.put("returnState", stateReturn);

        CellConfig question = new CellConfig("退款理由");
        config.put("question", question);
        return config;
    }

}
