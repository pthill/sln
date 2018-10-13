package com.sln.model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.product.ProductCommentsReadDao;
import com.sln.dao.shop.write.order.OrdersProductWriteDao;
import com.sln.dao.shop.write.order.OrdersWriteDao;
import com.sln.dao.shop.write.product.ProductCommentsWriteDao;
import com.sln.dao.shop.write.product.ProductWriteDao;
import com.sln.dao.shop.write.seller.SellerWriteDao;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductComments;
import com.sln.entity.seller.Seller;
import com.sln.util.FrontProductPictureUtil;

/**
 * 商品评论管理
 *                       
 * @Filename: ProductCommentsModel.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Component(value = "productCommentsModel")
public class ProductCommentsModel {
    private static Logger                log = LogManager.getLogger(ProductCommentsModel.class);

    @Resource
    private ProductCommentsWriteDao      productCommentsWriteDao;
    @Resource
    private ProductCommentsReadDao       productCommentsReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private FrontProductPictureUtil      frontProductPictureUtil;
    @Resource
    private OrdersProductWriteDao        ordersProductWriteDao;
    @Resource
    private OrdersWriteDao               ordersWriteDao;

    public ProductComments getProductCommentsById(Integer productCommentsId) {
        return productCommentsWriteDao.get(productCommentsId);
    }

    public Boolean auditProductComments(Integer id, Integer state) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(productCommentsWriteDao,
                "Property 'productCommentsWriteDao' is required.");
            Assert.notNull(productWriteDao, "Property 'productWriteDao' is required.");

            // 参数校验
            if (StringUtil.isNullOrZero(id)) {
                throw new BusinessException("商品评论id不能为空，请重试！");
            } else if (StringUtil.isNullOrZero(state)) {
                log.info("商品评论状态为空");
                throw new BusinessException("商品评论状态不能为空，请重试！");
            }

            ProductComments productComments = productCommentsWriteDao.get(id);

            if (productComments.getState() == ProductComments.STATE_2
                || productComments.getState() == ProductComments.STATE_3) {
                log.info("该商品评价已经审核过了");
                throw new BusinessException("该商品评价已经审核过了！");
            }

            //如果审核通过产品评价个数累加
            if (state == ProductComments.STATE_2) {
                int count = productWriteDao.updateCommentsNumber(productComments.getProductId(), 1);
                if (count == 0) {
                    throw new BusinessException("商品更新失败，请重试！");
                }
            }

            productComments.setState(state);
            Integer count = productCommentsWriteDao.update(productComments);
            if (count == 0) {
                throw new BusinessException("会员商品评论更新失败，请重试！");
            }

            transactionManager.commit(status);
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return true;
    }

    public Integer getProductCommentsCount(Map<String, String> queryMap) {
        Assert.notNull(productCommentsWriteDao, "Property 'productCommentsWriteDao' is required.");
        return productCommentsReadDao.getProductCommentsCount(queryMap);
    }

    public List<ProductComments> getProductComments(Map<String, String> queryMap, Integer start,
                                                    Integer size) throws Exception {
        Assert.notNull(productCommentsWriteDao, "Property 'productCommentsWriteDao' is required.");
        return productCommentsReadDao.getProductComments(queryMap, start, size);
    }

    public List<ProductComments> getProductCommentsWithInfo(Map<String, String> queryMap,
                                                            Integer start,
                                                            Integer size) throws Exception {
        Assert.notNull(productCommentsWriteDao, "Property 'productCommentsWriteDao' is required.");
        Assert.notNull(productWriteDao, "Property 'productWriteDao' is required.");
        Assert.notNull(sellerWriteDao, "Property 'sellerWriteDao' is required.");
        List<ProductComments> cmtList = productCommentsReadDao.getProductComments(queryMap, start,
            size);

        for (ProductComments bean : cmtList) {
            //获得产品对应的小图 
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(bean.getProductId());
            bean.setProductLeadLittle(productLeadLittle);
            Product product = productWriteDao.get(bean.getProductId());
            if (product != null) {
                bean.setProductName(product.getName1());
            }
            Seller seller = sellerWriteDao.get(bean.getSellerId());
            if (seller != null) {
                bean.setSellerName(seller.getSellerName());
            }
        }
        return cmtList;
    }

    /**
     * 保存商品评论
     * @param productComments
     * @param ordersProductId 
     * @return
     */
    public boolean saveProductComments(ProductComments productComments, Integer ordersProductId) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Integer insetResult = productCommentsWriteDao.insert(productComments);
            if (insetResult <= 0) {
                throw new BusinessException("保存商品评论失败，请重试");
            }

            //通过网单id获取网单信息
            OrdersProduct dbOrdersProduct = ordersProductWriteDao.get(ordersProductId);
            if (dbOrdersProduct == null) {
                throw new BusinessException("获取网单失败，请重试");
            }

            //修改网单评论状态
            Integer upOrderProduct = ordersProductWriteDao.updateIsEvaluate(ordersProductId,
                OrdersProduct.IS_EVALUATE_1);
            if (upOrderProduct <= 0) {
                throw new BusinessException("修改网单评价失败，请重试");
            }

            //获取该网单所对应子订单下所有的网单
            List<OrdersProduct> ordersProductList = ordersProductWriteDao
                .getByOrderId(dbOrdersProduct.getOrdersId());
            if (ordersProductList == null || ordersProductList.size() == 0) {
                throw new BusinessException("根据子订单id获取网单集合失败，请重试");
            }

            //循环所有网单，查看评价状态
            //记录状态为已评价的网单数量
            Integer isEvaluateNum = 0;
            for (OrdersProduct ordersProduct : ordersProductList) {
                if (ordersProduct.getId().equals(dbOrdersProduct.getId())) {
                    isEvaluateNum += 1;
                } else {
                    if (ordersProduct.getIsEvaluate().equals(OrdersProduct.IS_EVALUATE_1)) {
                        isEvaluateNum += 1;
                    }
                }
            }

            Integer upOrdersResult = 0;
            //1.当所有网单都为已评价时，修改订单状态为已评价。
            //2.当有一部分网单为已评价时，修改订单状态为部分评价。
            if (isEvaluateNum < ordersProductList.size()) {
                upOrdersResult = ordersWriteDao.updateEvaluateStateById(
                    dbOrdersProduct.getOrdersId(), Orders.EVALUATE_STATE_2);
            } else {
                upOrdersResult = ordersWriteDao.updateEvaluateStateById(
                    dbOrdersProduct.getOrdersId(), Orders.EVALUATE_STATE_3);
            }
            if (upOrdersResult <= 0) {
                throw new BusinessException("修改订单评价状态失败，请重试");
            }

            transactionManager.commit(status);
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return true;

    }

    /**
     * 保存商品评论
     * @param productComments
     * @param request
     * @return
     */
    public ProductComments saveProductComments(ProductComments productComments, Member member) {
        // 参数校验
        if (productComments == null) {
            throw new BusinessException("商品评论不能为空，请重试！");
        }

        //获取产品
        Product product = productWriteDao.get(productComments.getProductId());
        if (product == null) {
            log.error("产品不存在。");
            throw new BusinessException("产品不存在！");
        }

        //根据条件取评论
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_userId", String.valueOf(member.getId()));
        queryMap.put("q_orderSn", String.valueOf(productComments.getOrderSn()));
        queryMap.put("q_productId", String.valueOf(productComments.getProductId()));
        queryMap.put("q_productGoodsId", String.valueOf(productComments.getProductGoodsId()));
        List<ProductComments> beanList = productCommentsWriteDao.getProductComments(queryMap, 0, 0);
        if (beanList.size() > 0) {
            log.error("该产品已经评论过了。");
            throw new BusinessException("该产品已经评论过了！");
        }

        productComments.setUserId(member.getId());
        productComments.setUserName(member.getName());
        productComments.setState(ProductComments.STATE_1);
        productComments.setSellerId(product.getSellerId());
        //1、保存信息
        Integer count = productCommentsWriteDao.insert(productComments);
        if (count == 0) {
            throw new BusinessException("商品评论保存失败，请重试！");
        }
        return productComments;
    }

    /**
    * 更新商品评论管理
    * @param  productComments
    * @return
    */
    public Integer updateProductComments(ProductComments productComments) {
        return productCommentsWriteDao.update(productComments);
    }

    /**
     * 根据订单编号及产品id,货品ID   取得商品评论  1个订单可以有多个产品评论
     * @param orderSn
     * @param productId
     * @param request
     * @return
     */
    public ProductComments getProductCommentsByOrderSn(String orderSn, String productId,
                                                       String productGoodsId, Integer memberId) {
        if (StringUtil.isEmpty(orderSn)) {
            log.error("订单编号为空。");
            throw new BusinessException("订单编号为空，请重试！");
        } else if (StringUtil.isEmpty(productId)) {
            log.error("产品id为空。");
            throw new BusinessException("产品id为空，请重试！");
        } else if (StringUtil.isEmpty(productGoodsId)) {
            log.error("货品id为空。");
            throw new BusinessException("货品id为空，请重试！");
        }

        //根据条件取评论
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("userId", memberId);
        queryMap.put("orderSn", orderSn);
        queryMap.put("productId", productId);
        queryMap.put("productGoodsId", productGoodsId);
        List<ProductComments> beanList = productCommentsReadDao.queryList(queryMap);
        ProductComments bean = null;
        if (beanList == null || beanList.size() == 0) {
            return null;
        } else if (beanList.size() > 1) {
            log.error("一个订单一个产品产生多个评论。");
            throw new BusinessException("一个订单一个产品产生多个评论，请重试！");
        } else {
            bean = beanList.get(0);
        }

        return bean;
    }

    /**
     * 根据查询条件取所有的评论 单品页使用 
     * @param pager
     * @return
     */
    public List<ProductComments> getCommentsByCondition(Map<String, Object> queryMap,
                                                        PagerInfo pager) {

        if (queryMap.get("productId") == null) {
            throw new BusinessException("产品id不能为空，请重试！");
        }

        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productCommentsReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<ProductComments> beanList = productCommentsReadDao.queryList(queryMap);

        return beanList;
    }
}
