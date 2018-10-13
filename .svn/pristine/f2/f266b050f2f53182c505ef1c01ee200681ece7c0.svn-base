package com.sln.web.controller.promotion;

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

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.group.ActGroup;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductAttr;
import com.sln.entity.product.ProductBrand;
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductNorm;
import com.sln.entity.product.ProductNormAttr;
import com.sln.entity.seller.Seller;
import com.sln.service.product.IProductAttrService;
import com.sln.service.product.IProductBrandService;
import com.sln.service.product.IProductCateService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.product.IProductGoodsService;
import com.sln.service.promotion.IActGroupService;
import com.sln.service.seller.ISellerService;
import com.sln.web.controller.BaseController;
import com.google.gson.Gson;

/**
 * 团购单品页controller
 * 
 * @Filename: ActGroupDetailController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class ActGroupDetailController extends BaseController {

    @Resource
    private IProductFrontService productFrontService;
    @Resource
    private ISellerService       sellerService;
    @Resource
    private IProductGoodsService productGoodsService;
    @Resource
    private IProductBrandService productBrandService;
    @Resource
    private IProductCateService  productCateService;
    @Resource
    private IProductAttrService  productAttrService;
    @Resource
    private IActGroupService     actGroupService;

    /**
     * 跳转到单品页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/tuan/{actGroupId}.html", method = { RequestMethod.GET })
    public String toDetail(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap, @PathVariable Integer actGroupId) {

        dataMap.put("actGroupId", actGroupId);

        ServiceResult<ActGroup> groupResult = actGroupService.getActGroupById(actGroupId);
        if (!groupResult.getSuccess() || groupResult.getResult() == null) {
            dataMap.put("info", "团购信息为空。");
            return "front/commons/error";
        }

        ActGroup actGroup = groupResult.getResult();
        if (!isNull(actGroup.getDescinfo())) {
            actGroup.setDescinfo(actGroup.getDescinfo().replace(
                "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}", DomainUrlUtil.getSLN_IMAGE_RESOURCES()));
        }
        if (actGroup.getTypeState() == null || actGroup.getTypeState() != ActGroup.TYPE_STATE_1) {
            log.error("团购活动" + actGroup.getName() + "的分类状态为不显示，下单失败。");
            dataMap.put("info", "对不起，该团购活动已下线！");
            return "front/commons/error";
        }
        if (actGroup.getState() == null || actGroup.getState() != ActGroup.STATE_3) {
            dataMap.put("info", "对不起，团购活动不存在！");
            return "front/commons/error";
        }
        if (actGroup.getActivityState() == null
            || actGroup.getActivityState() != ActGroup.ACTIVITY_STATE_2) {
            dataMap.put("info", "对不起，该团购活动还没有发布！");
            return "front/commons/error";
        }
        if (actGroup.getChannel() != ConstantsEJS.CHANNEL_1
            && actGroup.getChannel() != ConstantsEJS.CHANNEL_2) {
            dataMap.put("info", "对不起，该团购活动不在电脑端进行！");
            return "front/commons/error";
        }
        dataMap.put("actGroup", actGroup);

        //获得商品信息
        ServiceResult<Product> productResult = productFrontService
            .getProductById(actGroup.getProductId());
        if (!productResult.getSuccess() || productResult.getResult() == null) {
            dataMap.put("info", "获得商品信息失败。");
            return "front/commons/error";
        }
        Product product = productResult.getResult();
        if (!isNull(product.getDescription())) {
            product.setDescription(product.getDescription().replace(
                "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}", DomainUrlUtil.SLN_IMAGE_RESOURCES));
        }
        dataMap.put("product", product);

        //取得商品品牌信息
        ServiceResult<ProductBrand> brandResult = productBrandService
            .getById(product.getProductBrandId());
        if (!brandResult.getSuccess()) {
            throw new BusinessException("获得商品品牌信息失败！");
        }
        if (brandResult.getResult() == null) {
            throw new BusinessException("获得商品品牌信息为空！");
        }
        dataMap.put("productBrand", brandResult.getResult());

        //取得分类名称，一级分类，二级分类
        ServiceResult<ProductCate> cateResult = productCateService
            .getProductCateById(product.getProductCateId());
        if (!cateResult.getSuccess()) {
            throw new BusinessException("获得商品分类信息失败！");
        }
        if (cateResult.getResult() == null) {
            throw new BusinessException("获得商品分类信息为空！");
        }
        ProductCate productCate = cateResult.getResult();
        dataMap.put("productCate", productCate);
        // 父分类，
        if (productCate.getPid() != null && productCate.getPid().intValue() != 0) {
            ServiceResult<ProductCate> catePResult = productCateService
                .getProductCateById(productCate.getPid());
            ProductCate productCateP = catePResult.getResult();
            dataMap.put("productCateP", productCateP);
            if (productCateP.getPid() != null && productCateP.getPid() != 0) {
                ServiceResult<ProductCate> catePPResult = productCateService
                    .getProductCateById(productCateP.getPid());
                ProductCate productCatePP = catePPResult.getResult();
                dataMap.put("productCatePP", productCatePP);
            }
        }

        //取得属性值
        ServiceResult<List<ProductAttr>> attrResult = productAttrService
            .getProductAttrByProductId(product.getId());
        if (!attrResult.getSuccess()) {
            throw new BusinessException("获得商品属性信息失败！");
        }
        if (attrResult.getResult() == null) {
            throw new BusinessException("获得商品属性信息为空！");
        }
        dataMap.put("productAttr", attrResult.getResult());

        //取得该团购所属分类下的前5个商品
        ServiceResult<List<ActGroup>> actGroupsByTypeRlt = actGroupService
            .getActGroupsByType(actGroup.getType(), 5);
        dataMap.put("actGroupTop", actGroupsByTypeRlt.getResult());

        //获得货品信息,默认取第一个 包含某规格商品的库存及价格信息 
        ServiceResult<List<ProductGoods>> goodsServiceResult = productGoodsService
            .getGoodSByProductId(product.getId());
        List<ProductGoods> goods = goodsServiceResult.getResult();

        if (goods == null || goods.size() == 0) {
            dataMap.put("info", "货品信息为空。");
            return "front/commons/error";
        }

        // 组装商品规格信息
        List<ProductNorm> normList = new ArrayList<ProductNorm>();
        Map<String, ProductNorm> normMap = new HashMap<String, ProductNorm>();
        // 设定默认货品，第一个有效的货品
        ProductGoods defaultgoods = null;
        // 记录有效的规格组合
        List<String> effectAttr = new ArrayList<>();
        // 组装商品规格信息
        Map<String, ProductNormAttr> attrMap = new HashMap<String, ProductNormAttr>();
        for (ProductGoods good : goods) {
            String normName = good.getNormName(); // 如：颜色,红色;内存,4G
            String normAttrId = good.getNormAttrId(); // 如：1,17

            if (StringUtil.isEmpty(normName, true)) {
                // 规则属性为空则表示该商品没有启用规格（只有一个货品）
                // 设置默认货品，只有一个货品时设定该货品
                defaultgoods = good;
                dataMap.put("goods", defaultgoods);
                continue;
            }

            if (good.getState() == null || good.getState().intValue() == ProductGoods.DISABLE) {
                // 货品没有启用则规格不参与组装
                continue;
            }

            if (defaultgoods == null) {
                // 设置默认货品，有多个货品时，第一个有效货品为默认货品
                defaultgoods = good;
                dataMap.put("goods", defaultgoods);
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
                        // 货品中存的图片与product_norm_attr_opt中存的图片一样，所以使用货品的图片即可
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
                            // 货品中存的图片与product_norm_attr_opt中存的图片一样，所以使用货品的图片即可
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

        // 获得商家信息、商家详细信息及 商家qq信息  以及活动信息 
        ServiceResult<Seller> sellerServiceResult = sellerService
            .getSellerById(actGroup.getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 记录活动进行状态
        int stageType = 0;
        Date date = new Date();
        if (date.before(actGroup.getStartTime())) {
            // 还未开始
            // 计算开始倒计时
            long countTime = actGroup.getStartTime().getTime() - date.getTime();
            dataMap.put("countTime", countTime / 1000);
            stageType = 1;
        } else if (date.after(actGroup.getEndTime())) {
            // 已结束不计算时间
            stageType = 3;
        } else {
            // 计算结束倒计时
            long countTime = actGroup.getEndTime().getTime() - date.getTime();
            dataMap.put("countTime", countTime / 1000);
            stageType = 2;
        }
        dataMap.put("stageType", stageType);

        return "front/promotion/actgroupdetail";
    }

}
