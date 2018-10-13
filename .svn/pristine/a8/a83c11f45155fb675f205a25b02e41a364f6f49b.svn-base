package com.sln.web.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.TimeUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.cart.Cart;
import com.sln.entity.coupon.Coupon;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.full.ActFull;
import com.sln.entity.member.Member;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductAsk;
import com.sln.entity.product.ProductAttr;
import com.sln.entity.product.ProductComments;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductNorm;
import com.sln.entity.product.ProductNormAttr;
import com.sln.entity.seller.Seller;
import com.sln.entity.single.ActSingle;
import com.sln.service.cart.ICartService;
import com.sln.service.member.IMemberService;
import com.sln.service.product.IProductAskService;
import com.sln.service.product.IProductAttrService;
import com.sln.service.product.IProductCommentsService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.product.IProductGoodsService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.service.promotion.IActFullService;
import com.sln.service.promotion.IActSingleService;
import com.sln.service.promotion.ICouponService;
import com.sln.service.seller.ISellerService;
import com.sln.util.FrontProductPictureUtil;
import com.sln.vo.member.FrontMemberProductBehaveStatisticsVO;
import com.sln.vo.product.ProductActVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;
import com.google.gson.Gson;

/**
 * 单品页controller
 * 
 * @Filename: ProductDetailController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class ProductDetailController extends BaseController {

    @Resource
    private IMemberService          memberService;
    @Resource
    private IProductFrontService    productFrontService;
    @Resource
    private ISellerService          sellerService;
    @Resource
    private IProductGoodsService    productGoodsService;
    @Resource
    private FrontProductPictureUtil frontProductPictureUtil;
    @Resource
    private IProductAttrService     productAttrService;
    @Resource
    private IProductCommentsService productCommentsService;
    @Resource
    private IProductAskService      productAskService;
    @Resource
    private ICartService            cartService;
    @Resource
    private IActSingleService       actSingleService;
    @Resource
    private IActFullService         actFullService;
    @Resource
    private ICouponService          couponService;
    @Resource
    private IActFlashSaleService    actFlashSaleService;

    /**
     * 跳转到单品页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/{productId}.html", method = { RequestMethod.GET })
    public String productDetail(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);
        String goodIdStr = request.getParameter("goodId");
        Integer goodId = ConvertUtil.toInt(goodIdStr, 0);

        Member member = WebFrontSession.getLoginedUser(request);

        //获得商品信息
        ServiceResult<Product> productResult = productFrontService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        if (!isNull(product.getDescription())) {
            product.setDescription(product.getDescription().replace(
                "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}", DomainUrlUtil.SLN_IMAGE_RESOURCES));
        }
        dataMap.put("product", product);

        // 取是否预览标志，1表示是预览
        String preview = request.getParameter("preview");
        if (!"1".equals(preview)) {
            // 如果不是预览，则根据属性修改商品的状态
            // 上架时间在现在之后
            if (product.getUpTime().after(new Date())) {
                product.setState(Product.STATE_7);
            }
            // 店铺被冻结
            if (product.getSellerState().intValue() != Product.SELLER_STATE_1) {
                product.setState(Product.STATE_7);
            }
        } else {
            product.setState(Product.STATE_6);
        }

        //获得产品对应的大图
        List<String> productLeadPicList = frontProductPictureUtil.getByProductIds(productId);
        dataMap.put("productLeadPicList", productLeadPicList);

        //获得用户评价数及占比
        ServiceResult<FrontMemberProductBehaveStatisticsVO> serviceResult = new ServiceResult<FrontMemberProductBehaveStatisticsVO>();
        serviceResult = memberService.getBehaveStatisticsByProductId(productId, member);
        dataMap.put("statisticsVO", serviceResult.getResult());

        //获得货品信息,默认取第一个 包含某规格商品的库存及价格信息 
        ServiceResult<List<ProductGoods>> goodsServiceResult = productGoodsService
            .getGoodSByProductId(productId);
        List<ProductGoods> goods = goodsServiceResult.getResult();

        if (goods == null || goods.size() == 0) {
            dataMap.put("info", "货品信息为空。");
            return "h5/commons/error";
        }

        // 组装商品规格信息
        List<ProductNorm> normList = new ArrayList<ProductNorm>();
        Map<String, ProductNorm> normMap = new HashMap<String, ProductNorm>();
        Map<String, ProductNormAttr> attrMap = new HashMap<String, ProductNormAttr>();
        // 记录有效的规格组合
        List<String> effectAttr = new ArrayList<>();
        boolean def = false;
        boolean defWithId = false;
        for (ProductGoods good : goods) {
            String normName = good.getNormName(); // 如：颜色,红色;内存,4G
            String normAttrId = good.getNormAttrId(); // 如：1,17

            if (StringUtil.isEmpty(normName, true)) {
                // 规则属性为空则表示该商品没有启用规格（只有一个货品）
                // 设置默认货品，只有一个货品时设定该货品
                dataMap.put("goods", good);
                continue;
            }

            if (good.getState() == null || good.getState().intValue() == ProductGoods.DISABLE) {
                // 货品没有启用则规格不参与组装
                continue;
            }

            // 获取默认显示的货品，不管id为goodId的货品存在与否、启用与否，必须设置一个默认的货品
            if (!defWithId) {
                if (good.getId().equals(goodId)) {
                    defWithId = true;
                    def = true;
                    // 如果有指定ID的货品，设定指定ID的货品为默认货品
                    dataMap.put("goods", good);
                } else if (!def) {
                    // 设置默认货品，有多个货品时，第一个有效货品为默认货品
                    dataMap.put("goods", good);
                    def = true;
                }
            }

            effectAttr.add(normAttrId);

            String[] normNameSplit = normName.split(";");
            String[] normAttrIdSplit = normAttrId.split(",");

            if (normNameSplit.length > 0) {
                // 循环
                for (int i = 0; i < normNameSplit.length; i++) {
                    String name = normNameSplit[i];

                    // 得到类似：颜色,红色的值，颜色为规格名称，红色为规格的值
                    String[] cellName = name.split(",");

                    if (normMap.get(cellName[0]) == null) {
                        // 如果规格map中没有当前规格，则添加规格和对应的规格值
                        ProductNorm norm = new ProductNorm();
                        norm.setName(cellName[0]);
                        // 保存规则名称
                        normList.add(norm);
                        normMap.put(cellName[0], norm);
                        // 保存规则值
                        List<ProductNormAttr> attrList = new ArrayList<ProductNormAttr>();
                        ProductNormAttr attr = new ProductNormAttr();
                        attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
                        attr.setName(cellName[1]);
                        
                        // 系统规定只有颜色才有图片（product_norm表固定第一条数据是颜色，id为1，name为颜色）
                        if ("颜色".equals(cellName[0])
                            && !StringUtil.isEmpty(good.getImages(), true)) {
                            attr.setUrl(good.getImages());
                        }
                        
                        attrList.add(attr);
                        norm.setAttrList(attrList);

                        // 记录到map中防止重复添加
                        attrMap.put(cellName[1], attr);
                    } else {
                        // 如果规格map中有当前规格，则不添加规格，对应的规格值再判断是否已经存在决定是否添加
                        ProductNorm norm = normMap.get(cellName[0]);

                        // 判断是否已有规则值，如果没有则添加，有则不再添加
                        if (attrMap.get(cellName[1]) == null) {
                            // 规则值
                            List<ProductNormAttr> attrList = norm.getAttrList();
                            ProductNormAttr attr = new ProductNormAttr();
                            attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
                            attr.setName(cellName[1]);
                            
                            // 因为相同颜色的图片一样，所以多次设定没有影响（product_norm表固定第一条数据是颜色，id为1，name为颜色）
                            if ("颜色".equals(cellName[0])
                                    && !StringUtil.isEmpty(good.getImages(), true)) {
                                    attr.setUrl(good.getImages());
                            }
                            
                            attrList.add(attr);
                            norm.setAttrList(attrList);
                            // 记录到map中防止重复添加
                            attrMap.put(cellName[1], attr);
                        }
                    }
                }
            }
        }

        // 有效货品包含的规格
        dataMap.put("norms", normList);
        // 规格数量
        dataMap.put("normsNum", normList.size());
        // 有效的规格组合
        dataMap.put("effectAttr", new Gson().toJson(effectAttr));

        // 获得商家信息、商家详细信息
        ServiceResult<Seller> sellerServiceResult = sellerService
            .getSellerById(productResult.getResult().getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 购物车数量
        if (member != null && member.getId() != null) {
            ServiceResult<Integer> cartResult = cartService.getCartNumberByMId(member.getId(),Cart.SOURCE_1);
            dataMap.put("cartNumber", cartResult.getResult());
        }

        // 获取类型参数：为1时表示打开限时抢购页面
        String type = request.getParameter("type");
        if (type != null && type.equals("1")) {
            // 限时抢购时获取活动信息
            // 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
            ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
                .getTodayEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);

            // 抢购活动信息，不为空则显示，为空则提示当前时间无抢购活动
            ActFlashSale actFlashSale = flashSaleResult.getResult();
            if (actFlashSale != null) {
                dataMap.put("actFlashSale", actFlashSale);

                // 记录正在进行的阶段，用于计算结束时间
                ActFlashSaleStage underWayStage = null;
                // 记录即将开始的阶段（商品有多个阶段即将开始时显示最近的）
                ActFlashSaleStage comingStage = null;
                // 记录阶段类型
                int stageType = 0;

                // 如果活动对象不为空，则返回结果中必定有阶段和活动商品
                int hour = TimeUtil.getHour();
                int minute = TimeUtil.getMinute();
                int second = TimeUtil.getSecond();
                for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                    int startTime = stage.getStartTime();
                    int endTime = stage.getEndTime();
                    if (hour >= startTime && hour < endTime) {
                        // 如果有阶段正在进行  优先显示正在进行的
                        dataMap.put("actFlashSaleStage", stage);
                        dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                        dataMap.put("stageType", ProductActVO.STAGE_TYPE_2);
                        underWayStage = stage;
                        stageType = ProductActVO.STAGE_TYPE_2;
                        break;
                    } else if (hour < startTime) {
                        // 如果有阶段即将开始，显示最近的，如果有正在进行的则会覆盖
                        if (comingStage == null
                            || stage.getStartTime() < comingStage.getStartTime()) {
                            // 如果循环当前阶段比上次记录的阶段要早，则显示当前阶段
                            dataMap.put("actFlashSaleStage", stage);
                            dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                            dataMap.put("stageType", ProductActVO.STAGE_TYPE_3);
                            comingStage = stage;
                            stageType = ProductActVO.STAGE_TYPE_3;
                        }
                    } else if (hour >= endTime) {
                        // 如果有阶段已经结束，则判断是否有正在进行或即将开始，
                        // 如果都没有，则保存结束的阶段，后续循环如果有即将开始或者正在进行的将会覆盖
                        if (stageType == 0) {
                            dataMap.put("actFlashSaleStage", stage);
                            dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                            dataMap.put("stageType", ProductActVO.STAGE_TYPE_1);
                            stageType = ProductActVO.STAGE_TYPE_1;
                        }
                    }
                }

                if (stageType == ProductActVO.STAGE_TYPE_2) {
                    //计算结束倒计时
                    int endTime = underWayStage.getEndTime();
                    int hour1 = endTime - hour - 1;
                    int minute1 = 60 - minute;
                    int second1 = 60 - second;
                    int countTime = hour1 * 3600 + minute1 * 60 + second1;
                    dataMap.put("countTime", countTime);
                } else if (stageType == ProductActVO.STAGE_TYPE_3) {
                    // 计算开始倒计时
                    int startTime = comingStage.getStartTime();
                    int hour1 = startTime - hour;
                    int countTime = hour1 * 3600 - minute * 60 - second;
                    dataMap.put("countTime", countTime);
                }
            }
            return "h5/product/productdetailflash";
        } else {
            // 类型参数为空则打开普通单品页
            return "h5/product/productdetail";
        }

    }

    /**
     * 跳转到商品详细介绍页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/info/{productId}.html", method = { RequestMethod.GET })
    public String productDetailInfo(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);
        //获得商品信息
        ServiceResult<Product> productResult = productFrontService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        if (!isNull(product.getDescription())) {
            product.setDescription(product.getDescription().replace(
                "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}", DomainUrlUtil.SLN_IMAGE_RESOURCES));
        }
        dataMap.put("product", product);
        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5/product/detailinfo";
    }

    /**
     * 跳转到单品规格页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/spec/{productId}.html", method = { RequestMethod.GET })
    public String productDetailSpec(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        //获得商品信息
        ServiceResult<Product> productResult = productFrontService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        dataMap.put("product", product);

        //取得属性值
        ServiceResult<List<ProductAttr>> attrResult = productAttrService
            .getProductAttrByProductId(productId);
        if (!attrResult.getSuccess()) {
            throw new BusinessException("获得商品属性信息失败！");
        }
        if (attrResult.getResult() == null) {
            throw new BusinessException("获得商品属性信息为空！");
        }
        dataMap.put("productAttr", attrResult.getResult());

        // 获得商家信息、商家详细信息及 商家qq信息  以及活动信息 
        ServiceResult<Seller> sellerServiceResult = sellerService
            .getSellerById(productResult.getResult().getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5/product/detailspec";
    }

    /**
     * 跳转到单品评论页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/comment/{productId}.html", method = { RequestMethod.GET })
    public String productDetailComment(HttpServletRequest request, HttpServletResponse response,
                                       Map<String, Object> dataMap,
                                       @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        Member member = WebFrontSession.getLoginedUser(request);

        //获得用户评价数及占比
        ServiceResult<FrontMemberProductBehaveStatisticsVO> serviceResult = new ServiceResult<FrontMemberProductBehaveStatisticsVO>();
        serviceResult = memberService.getBehaveStatisticsByProductId(productId, member);
        dataMap.put("statisticsVO", serviceResult.getResult());

        // 好评 grade = 4或5，中评：3，差评：1或2
        // 全部评论
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        //查找所有的评价和咨询   状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();

        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_state", String.valueOf(ProductComments.STATE_2));
        queryMap.put("q_grades", "all");
        ServiceResult<List<ProductComments>> allCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 全部评论数量和数据
        dataMap.put("allNumber", pager.getRowsCount());
        dataMap.put("allCommentList", allCmtResult.getResult());

        // 好评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "high");
        ServiceResult<List<ProductComments>> highCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("highNumber", pager.getRowsCount());
        dataMap.put("highCommentList", highCmtResult.getResult());

        // 中评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "middle");
        ServiceResult<List<ProductComments>> middleCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("middleNumber", pager.getRowsCount());
        dataMap.put("middleCommentList", middleCmtResult.getResult());

        // 差评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "low");
        ServiceResult<List<ProductComments>> lowCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("lowNumber", pager.getRowsCount());
        dataMap.put("lowCommentList", lowCmtResult.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5/product/detailcomment";
    }

    /**
     * 单品页 评价列表，异步调用加载更多评论
     * @param request
     * @param response
     * @param dataMap
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/morecomment.html", method = { RequestMethod.GET })
    public String commentsListForProcutPage(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Map<String, Object> dataMap) {

        // 商品ID
        String productIdStr = request.getParameter("productId");

        String type = request.getParameter("type");

        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = ConvertUtil.toInt(pageIndexStr, 1);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        //查找所有的评价和咨询   状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();

        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productIdStr);
        queryMap.put("q_state", String.valueOf(ProductComments.STATE_2));
        //好评  grade = 4或5 ,中评：3，差评：1或2
        queryMap.put("q_grades", type);

        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductComments(queryMap, pager);

        dataMap.put("commentList", serviceResult.getResult());
        dataMap.put("type", type);

        return "h5/product/commentlist";
    }

    /**
     * 跳转到单品咨询页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/ask/{productId}.html", method = { RequestMethod.GET })
    public String productDetailAsk(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Map<String, String> queryMap = new HashMap<String, String>();
        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_state", String.valueOf(ProductAsk.STATE_3));
        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsks(queryMap,
            pager);

        // 数量和数据
        dataMap.put("askNumber", pager.getRowsCount());
        dataMap.put("askList", serviceResult.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5/product/detailask";
    }

    /**
     * 单品页 咨询列表，异步调用
     * @param request
     * @param response
     * @param dataMap
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/moreask.html", method = { RequestMethod.GET })
    public String productAskListForProcutPage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Map<String, Object> dataMap) {

        // 商品ID
        String productIdStr = request.getParameter("productId");
        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = ConvertUtil.toInt(pageIndexStr, 1);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        //查找所有的评价和咨询，状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();
        //查找某个产品的咨询，状态为前台显示的
        queryMap.put("q_productId", productIdStr);
        queryMap.put("q_state", String.valueOf(ProductAsk.STATE_3));

        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsks(queryMap,
            pager);

        dataMap.put("askList", serviceResult.getResult());

        return "h5/product/asklist";
    }

    /**
     * 根据产品id及规格id查询货品价格及库存
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/getGoodsInfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductGoods> getGoodsInfo(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam(value = "productId", required = true) Integer productId,
                                                                   String normAttrId) {

        Map<String, String> queryMap = new HashMap<String, String>();
        //获得货品信息 包含某规格商品的库存及价格信息
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_normAttrId", normAttrId);
        ServiceResult<ProductGoods> serviceResult = productGoodsService
            .getProductGoodsByCondition(queryMap);

        HttpJsonResult<ProductGoods> jsonResult = new HttpJsonResult<ProductGoods>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductGoods>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 商品页异步加载立减、满减、优惠券信息
     * @param request
     * @param response
     * @param productId
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/getproductactinfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductActVO> getProductActInfo(HttpServletRequest request,
                                                                        HttpServletResponse response,
                                                                        @RequestParam(value = "productId", required = true) Integer productId,
                                                                        @RequestParam(value = "sellerId", required = true) Integer sellerId) {

        ProductActVO productActVO = new ProductActVO();
        // 如果获取限时抢购的结果为空，则加载商品的单品立减、订单满减、优惠券信息
        ServiceResult<ActSingle> singleResult = actSingleService.getEffectiveActSingle(sellerId,
            ConstantsEJS.CHANNEL_3, productId);
        // 满减
        ServiceResult<ActFull> fullResult = actFullService.getEffectiveActFull(sellerId,
            ConstantsEJS.CHANNEL_3);
        // 优惠券
        ServiceResult<List<Coupon>> couponResult = couponService.getEffectiveCoupon(sellerId,
            ConstantsEJS.CHANNEL_3);
        productActVO.setActSingle(singleResult.getResult());
        productActVO.setActFull(fullResult.getResult());
        productActVO.setCouponList(couponResult.getResult());

        HttpJsonResult<ProductActVO> jsonResult = new HttpJsonResult<ProductActVO>();
        jsonResult.setData(productActVO);
        return jsonResult;
    }

    /**
     * 商品页异步加载限时抢购信息
     * @param request
     * @param response
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getproductflashinfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductActVO> getProductFlashInfo(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          @RequestParam(value = "productId", required = true) Integer productId) {

        ProductActVO productActVO = new ProductActVO();
        // 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
        ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
            .getTodayEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);

        // 抢购活动
        ActFlashSale actFlashSale = flashSaleResult.getResult();
        if (actFlashSale != null) {
            productActVO.setActFlashSale(actFlashSale);
            // 如果活动对象不为空，则返回结果中必定有阶段和活动商品
            int hour = TimeUtil.getHour();
            for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                int startTime = stage.getStartTime();
                int endTime = stage.getEndTime();
                if (hour >= startTime && hour < endTime) {
                    // 如果有阶段正在进行  优先显示正在进行的
                    productActVO.setActFlashSaleStage(stage);
                    productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                    productActVO.setStageType(ProductActVO.STAGE_TYPE_2);
                    break;
                } else if (hour < startTime) {
                    // 如果有阶段即将开始，保存到productActVO，如果有正在进行的则会覆盖
                    productActVO.setActFlashSaleStage(stage);
                    productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                    productActVO.setStageType(ProductActVO.STAGE_TYPE_3);
                } else if (hour >= endTime) {
                    // 如果有阶段已经结束，则判断是否有正在进行或即将开始，
                    // 如果都没有，则保存结束的阶段，后续循环如果有即将开始或者正在进行的将会覆盖
                    if (productActVO.getStageType() == null || productActVO.getStageType() == 0) {
                        productActVO.setActFlashSaleStage(stage);
                        productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                        productActVO.setStageType(ProductActVO.STAGE_TYPE_1);
                    }
                }
            }
        }

        HttpJsonResult<ProductActVO> jsonResult = new HttpJsonResult<ProductActVO>();
        jsonResult.setData(productActVO);
        return jsonResult;
    }
}
