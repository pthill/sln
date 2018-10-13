package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.group.ActGroup;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberGradeConfig;
import com.sln.entity.operate.Park;
import com.sln.entity.order.Orders;
import com.sln.entity.portal.IndexBanner;
import com.sln.entity.portal.PortalActive;
import com.sln.entity.portal.ShopActive;
import com.sln.entity.product.Product;
import com.sln.service.member.IMemberService;
import com.sln.service.message.IMessageRecordService;
import com.sln.service.operate.IParkService;
import com.sln.service.order.IOrdersService;
import com.sln.service.portal.IPortalActiveService;
import com.sln.service.portal.IPortalIndexBannerService;
import com.sln.service.portal.IShopActiveService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.service.promotion.IActGroupService;
import com.sln.service.promotion.ICouponService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.CommUtil;
import com.sln.web.util.WebFrontSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class PortalController extends BaseController{

    @Resource
    private IPortalActiveService      portalActiveService;
    @Resource
    private IParkService              parkService;
    @Resource
    private IPortalIndexBannerService portalIndexBannerService;
    @Resource
    private IOrdersService              ordersService;
    @Resource
    private IMemberService              memberService;
    @Resource
    private ICouponService              couponService;
    @Resource
    private IShopActiveService          shopActiveService;
    @Resource
    private IActGroupService            actGroupService;
    @Resource
    private IProductFrontService        productFrontService;
    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;
    @Resource
    private IMessageRecordService		messageRecordService;


    @RequestMapping(value = "portal/index.html",method = {RequestMethod.GET})
    public String index(Map<String,Object> dataMap, HttpServletRequest request)throws Exception{
        this.head(0,dataMap,request);
        Integer parkId=super.stParkId;
        /*门户首页轮播*/
        ServiceResult<List<IndexBanner>> topBanner=portalIndexBannerService.getBannerList(parkId,"1");
        ServiceResult<List<IndexBanner>> leftBanner=portalIndexBannerService.getBannerList(parkId,"2");
        ServiceResult<List<IndexBanner>> rightBanner=portalIndexBannerService.getBannerList(parkId,"3");
        //活动banner
        ServiceResult<List<IndexBanner>> activeBanner=portalIndexBannerService.getBannerList(parkId,"4");
        dataMap.put("activeBanner",activeBanner.getResult());
        dataMap.put("topBanner",topBanner.getResult());
        dataMap.put("leftBanner",leftBanner.getResult());
        dataMap.put("rightBanner",rightBanner.getResult());
        PagerInfo pager = new PagerInfo(1, 1);
        Map<String,String>map=new HashMap<>();
        //爆款商品
        map.put("q_type","2");
        map.put("q_status","1");
        ServiceResult<List<ShopActive>> bkProduct=shopActiveService.page(map,pager);
        dataMap.put("bkProduct",bkProduct.getResult());
        //门户的满减商品
        map.put("q_type","1");
        map.put("q_status","1");
        ServiceResult<List<ShopActive>>mjProduct=shopActiveService.page(map,pager);
        dataMap.put("mjProduct",mjProduct.getResult());
        /*园区活动*/
        ServiceResult<List<PortalActive>> actives=portalActiveService.activeList(parkId);
        if(!actives.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(actives.getCode())) {
                throw new RuntimeException(actives.getMessage());
            } else {
                throw new BusinessException(actives.getMessage());
            }
        }
        ServiceResult<List<Park>> parkList=parkService.getParkList();
        if(!parkList.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(actives.getCode())) {
                throw new RuntimeException(parkList.getMessage());
            } else {
                throw new BusinessException(parkList.getMessage());
            }
        }
        //门户的最后一个爆款取4个团购数据
        PagerInfo page = WebUtil.handlerPagerInfo(request, dataMap, 4);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);
        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(page,
                type, ConstantsEJS.CHANNEL_2);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productFrontService
                    .getProductById(actGroup.getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }
        //门户秒杀的数据
        String today = TimeUtil.getToday();
        ServiceResult<ActFlashSale> result = actFlashSaleService.getEffectiveActFlashSale(
                TimeUtil.strToDate(today + " 00:00:00"), ConstantsEJS.CHANNEL_2);
        ActFlashSale actFlashSale = result.getResult();
        int hour = TimeUtil.getHour();
        int minute = TimeUtil.getMinute();
        int second = TimeUtil.getSecond();
        if (actFlashSale != null) {
            ActFlashSaleStage actFlashSaleStageNow = null;//正在开始
            List<ActFlashSaleStage> stageList = actFlashSale.getStageList();
            for (ActFlashSaleStage actFlashSaleStage : stageList) {
                ServiceResult<List<ActFlashSaleProduct>> servletActFlashSaleProduct = actFlashSaleProductService
                        .getActFlashSaleProductsByStageId(actFlashSaleStage.getId());
                List<ActFlashSaleProduct> actFlashSaleProducts = servletActFlashSaleProduct.getResult();
                for (ActFlashSaleProduct actFlashSaleProduct : actFlashSaleProducts) {
                    Product product = productFrontService
                            .getProductById(actFlashSaleProduct.getProductId()).getResult();
                    actFlashSaleProduct.setProduct(product);
                }
                actFlashSaleStage.setProductList(actFlashSaleProducts);
            }
            for (Iterator iterator = stageList.iterator(); iterator.hasNext();) {
                ActFlashSaleStage actFlashSaleStage = (ActFlashSaleStage) iterator.next();
                int startTime = actFlashSaleStage.getStartTime();
                int endTime = actFlashSaleStage.getEndTime();
                if (hour >= startTime && hour < endTime) {
                    actFlashSaleStageNow = actFlashSaleStage;
                    iterator.remove();
                    break;
                }
            }
            if (actFlashSaleStageNow != null) {
                int endTime = actFlashSaleStageNow.getEndTime();
                int hour1 = endTime - hour - 1;
                int minute1 = 60 - minute;
                int second1 = 60 - second;
                int countTime = hour1 * 3600 + minute1 * 60 + second1;
                dataMap.put("countTime", countTime);
            }
            dataMap.put("actFlashSaleStageNow", actFlashSaleStageNow);
        }
        dataMap.put("actGroups", actGroups);
        dataMap.put("parkList",parkList.getResult());

        dataMap.put("actives",actives.getResult());
        return "front/portal/index/index";
    }

    @RequestMapping(value = "portal/userCenter.html",method = {RequestMethod.GET})
    public String userCenter(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap, String orderState,
                             String evaluateNoState)throws IOException{
        Member member = WebFrontSession.getLoginedUser(request);
        if(member==null){
            return "front/portal/login";
        }
        this.head(0,dataMap,request);
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        pager.setPageSize(ConstantsEJS.DEFAULT_ORDER_PAGE_SIZE);
        queryMap.put("q_memberId", String.valueOf(member.getId()));
        if (!StringUtil.isEmpty(orderState, true)) {
            queryMap.put("q_orderState", String.valueOf(orderState));
        }
        if (!StringUtil.isEmpty(evaluateNoState, true)) {
            queryMap.put("q_evaluateNoState", String.valueOf(evaluateNoState));
        }
        //查询订单列表
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(queryMap, pager);
        String url = request.getRequestURI() + "";
        if (!StringUtil.isEmpty(orderState)) {
            url = url + "?orderState=" + orderState;
            if (!StringUtil.isEmpty(orderState)) {
                url = url + "&evaluateNoState=" + evaluateNoState;
            }
        }
        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
                String.valueOf(pager.getPageIndex()), pager.getRowsCount(), url);
        //支付随机码,对于未付款的订单
        String order_session = CommUtil.randomString(32);
        request.getSession().setAttribute("order_session", order_session);
        dataMap.put("sessionRandomStr", order_session);
        dataMap.put("ordersList", serviceResult.getResult());
        dataMap.put("page", pm);
        dataMap.put("user",member);
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (serviceResult.getSuccess()) {
            dataMap.put("member", memberResult.getResult());
        }
        ServiceResult<MemberGradeConfig> gradeConfigResult = memberService
                .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
        MemberGradeConfig memberGradeConfig = gradeConfigResult.getResult();
        int gradeValue = 0;
        switch (member.getGrade().intValue()) {
            case Member.GRADE_1:
                gradeValue = memberGradeConfig.getGrade2().intValue() - member.getGradeValue();
                break;
            case Member.GRADE_2:
                gradeValue = memberGradeConfig.getGrade3().intValue() - member.getGradeValue();
                break;
            case Member.GRADE_3:
                gradeValue = memberGradeConfig.getGrade4().intValue() - member.getGradeValue();
                break;
            case Member.GRADE_4:
                gradeValue = memberGradeConfig.getGrade5().intValue() - member.getGradeValue();
                break;
            default:
        }
        //等级信息
        dataMap.put("gradeValue", gradeValue);
        //优惠劵薪资
        ServiceResult<Integer> couResult = couponService.countCouponUserByMemberId(member.getId());
        dataMap.put("couponNum", couResult.getResult());
        
        ServiceResult<Integer> unreadNum = messageRecordService.getUnreadNumByMessageTypeId(null, member.getId());
        dataMap.put("unreadNum", unreadNum.getResult());
        
        return "front/portal/index/userCenter";
    }

    @RequestMapping(value = "portal/construction.html")
    public String construction(Map<String,Object>dataMap,HttpServletRequest request){
        this.head(0,dataMap,request);
        return "front/portal/construction";
    }
    @RequestMapping(value ="portal/protocol.html")
    public String protocol(Map<String,Object>dataMap,HttpServletRequest request) {
    	
    	return "front/portal/protocol";
    }
}
