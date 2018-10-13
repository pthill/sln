package com.sln.web.controller.member;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.entity.product.ProductComments;
import com.sln.service.member.IMemberService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductCommentsService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 用户中心：我的收藏  及我的评价、我的咨询 、单品页
 *                       
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberCommentController extends BaseController {

    @Resource
    private IProductCommentsService productCommentsService;
    @Resource
    private IOrdersService          ordersService;
    @Resource
    private IMemberService          memberService;

    /**
     * 跳转到 我的评价界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/comment.html", method = { RequestMethod.GET })
    public String toMyEvaluation(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,8);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", String.valueOf(member.getId()));
        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductCommentsWithInfo(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            throw new RuntimeException(serviceResult.getMessage());
        }
        dataMap.put("commentsList", serviceResult.getResult());

  
        dataMap.put("page", pager);
        //return "front/member/ordercenter/myevaluation";
        return "front/portal/orders/myevaluation";
    }

    /**
     * 添加或编辑商品评价界面（父页面）
     * @param request
     * @param response
     * @param dataMap
     * @param orderSn 订单id
     * @return
     */
    @RequestMapping(value = "/addcomment.html", method = { RequestMethod.GET })
    public String addEvaluation(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap,
                                @RequestParam(value = "id", required = true) Integer id) {
    	
    	this.head(0,dataMap,request);

        Integer userId = WebFrontSession.getLoginedUser(request).getId();
        if (id == null) {
            throw new RuntimeException("订单id为空");
        }

        ServiceResult<Orders> orderServiceResult = ordersService.getOrderWithOPById(id);
        if (!orderServiceResult.getSuccess()) {
            dataMap.put("info", orderServiceResult.getMessage());
            return "/front/commons/error";
        }

        if (orderServiceResult.getResult() == null) {
            dataMap.put("info", "订单不存在");
            return "/front/commons/error";
        }
        if (!orderServiceResult.getResult().getMemberId().equals(userId)) {
            dataMap.put("info", "您无权访问他人信息");
            return "/front/commons/error";
        }

        dataMap.put("order", orderServiceResult.getResult());

        //return "front/member/ordercenter/addevaluation";
        return "front/portal/orders/addevaluation";
    }

    /**
     * 添加或查看商品评价界面（子页面）
     * @param request
     * @param response
     * @param dataMap
     * @param orderSn 订单编号
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "/editcomment.html", method = { RequestMethod.GET })
    public String getProductCommentsByProductId(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Map<String, Object> dataMap,
                                                @RequestParam(value = "orderSn", required = true) String orderSn,
                                                @RequestParam(value = "productId", required = true) String productId,
                                                @RequestParam(value = "productGoodsId", required = true) String productGoodsId,
                                                @RequestParam(value = "ordersProductId", required = true) String ordersProductId) {

        if (StringUtil.isEmpty(orderSn)) {
            throw new RuntimeException("订单编号为空");
        }
        if (StringUtil.isEmpty(productId)) {
            throw new RuntimeException("产品id为空");
        }
        if (StringUtil.isEmpty(productGoodsId)) {
            throw new RuntimeException("货品id为空");
        }
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<ProductComments> serviceResult = productCommentsService
            .getProductCommentsByOrderSn(orderSn, productId, productGoodsId, member.getId());
        dataMap.put("comment", serviceResult.getResult());
        dataMap.put("orderSn", orderSn);
        dataMap.put("productId", productId);
        dataMap.put("productGoodsId", productGoodsId);
        dataMap.put("ordersProductId", ordersProductId);

        return "front/member/ordercenter/editevaluation";
    }

    /**
     * 商品评价提交
     * @param request
     * @param response
     * @param productComments
     * @throws IOException 
     */
    @RequestMapping(value = "/savecomment.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductComments> evaluationSubmit(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          ProductComments productComments,
                                                                          String ordersProductId) throws Exception {
        HttpJsonResult<ProductComments> jsonResult = new HttpJsonResult<ProductComments>();
        Member member = WebFrontSession.getLoginedUser(request);

        if (ordersProductId == null || ordersProductId == "") {
            return new HttpJsonResult<ProductComments>("网单id不能为空，请重试");
        }

        productComments.setUserId(member.getId());
        productComments.setUserName(member.getName());

        ServiceResult<Orders> ordersBySnRlt = ordersService
            .getOrdersBySn(productComments.getOrderSn());
        if (!ordersBySnRlt.getSuccess()) {
            jsonResult = new HttpJsonResult<ProductComments>(ordersBySnRlt.getMessage());
            return jsonResult;
        }
        if (ordersBySnRlt.getResult() != null) {
            productComments.setSellerId(ordersBySnRlt.getResult().getSellerId());
        }

        productComments.setState(ProductComments.STATE_1);

        ServiceResult<Boolean> serviceResult = productCommentsService
            .saveProductComments(productComments, Integer.parseInt(ordersProductId));

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductComments>(serviceResult.getMessage());
            }
        }

        // 评论送积分
        /*memberService.memberEvaluateSendValue(member.getId(), member.getName(),
            productComments.getOrdersProductId());*/
        return jsonResult;
    }

}
