package com.sln.model.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.core.jd.util.JDMessageType;
import com.sln.dao.shop.read.bidding.ActBiddingReadDao;
import com.sln.dao.shop.read.coupon.CouponReadDao;
import com.sln.dao.shop.read.coupon.CouponUserReadDao;
import com.sln.dao.shop.read.flash.ActFlashSaleReadDao;
import com.sln.dao.shop.read.flash.ActFlashSaleStageReadDao;
import com.sln.dao.shop.read.group.ActGroupReadDao;
import com.sln.dao.shop.read.integral.ActIntegralReadDao;
import com.sln.dao.shop.read.member.MemberGradeIntegralLogsReadDao;
import com.sln.dao.shop.read.member.MemberProductExchangeReadDao;
import com.sln.dao.shop.read.member.MemberSpecialIntegralReadDao;
import com.sln.dao.shop.read.operate.ConfigReadDao;
import com.sln.dao.shop.read.order.OrdersProductReadDao;
import com.sln.dao.shop.read.order.OrdersReadDao;
import com.sln.dao.shop.read.product.ProductGoodsReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.read.supplier.OrderDeliveryReadDao;
import com.sln.dao.shop.write.bidding.ActBiddingWriteDao;
import com.sln.dao.shop.write.cart.CartWriteDao;
import com.sln.dao.shop.write.coupon.CouponOptLogWriteDao;
import com.sln.dao.shop.write.coupon.CouponUserWriteDao;
import com.sln.dao.shop.write.flash.ActFlashSaleLogWriteDao;
import com.sln.dao.shop.write.flash.ActFlashSaleProductWriteDao;
import com.sln.dao.shop.write.full.ActFullLogWriteDao;
import com.sln.dao.shop.write.group.ActGroupWriteDao;
import com.sln.dao.shop.write.integral.ActIntegralWriteDao;
import com.sln.dao.shop.write.member.*;
import com.sln.dao.shop.write.order.*;
import com.sln.dao.shop.write.product.ProductGoodsWriteDao;
import com.sln.dao.shop.write.product.ProductWriteDao;
import com.sln.dao.shop.write.seller.SellerWriteDao;
import com.sln.dao.shop.write.single.ActSingleLogWriteDao;
import com.sln.dao.shop.write.supplier.OrderDeliveryWriteDao;
import com.sln.dto.OrderDayDto;
import com.sln.dto.OrderDeliveryDto;
import com.sln.entity.bidding.ActBidding;
import com.sln.entity.cart.Cart;
import com.sln.entity.coupon.Coupon;
import com.sln.entity.coupon.CouponOptLog;
import com.sln.entity.coupon.CouponUser;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleLog;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.full.ActFullLog;
import com.sln.entity.group.ActGroup;
import com.sln.entity.integral.ActIntegral;
import com.sln.entity.member.*;
import com.sln.entity.message.Message;
import com.sln.entity.operate.Config;
import com.sln.entity.order.*;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.single.ActSingleLog;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.entity.system.SystemAdmin;
import com.sln.model.cart.CartModel;
import com.sln.model.message.MessageModel;
import com.sln.model.seller.SellerTransportModel;
import com.sln.util.FrontProductPictureUtil;
import com.sln.vo.cart.CartInfoVO;
import com.sln.vo.cart.CartListVO;
import com.sln.vo.order.OrderCommitVO;
import com.sln.vo.order.OrderCouponVO;
import com.sln.vo.order.OrderSuccessVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Component(value = "ordersModel")
public class OrdersModel {
    private static Logger                   log = LogManager.getLogger(OrdersModel.class);

    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private OrdersReadDao                   ordersReadDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private ProductWriteDao                 productWriteDao;
    @Resource
    private SellerReadDao                   sellerReadDao;
    @Resource
    private SellerWriteDao                  sellerWriteDao;
    @Resource
    private ProductGoodsWriteDao            productGoodsWriteDao;
    @Resource
    private OrdersProductWriteDao           ordersProductWriteDao;
    @Resource
    private OrderLogWriteDao                orderLogWriteDao;
    @Resource
    private OrdersReadDao                   orderReadDao;
    @Resource
    private OrdersProductReadDao            ordersProductReadDao;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberAddressWriteDao           memberAddressWriteDao;
    @Resource
    private CartWriteDao                    cartWriteDao;
    @Resource
    private ConfigReadDao                   configReadDao;
    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;
    @Resource
    private MemberGradeIntegralLogsReadDao  memberGradeIntegralLogsReadDao;
    @Resource
    private FrontProductPictureUtil         frontProductPictureUtil;
    @Resource
    private CartModel                       cartModel;
    @Resource
    private MemberBalanceLogsWriteDao       memberBalanceLogsWriteDao;
    @Resource
    private OrderPayLogWriteDao             orderPayLogWriteDao;
    @Resource
    private OrderPayCashLogWriteDao         orderPayCashLogWriteDao;
    @Resource
    private CouponUserReadDao               couponUserReadDao;
    @Resource
    private CouponUserWriteDao              couponUserWriteDao;
    @Resource
    private CouponReadDao                   couponReadDao;
    @Resource
    private CouponOptLogWriteDao            couponOptLogWriteDao;
    @Resource
    private ActFullLogWriteDao              actFullLogWriteDao;
    @Resource
    private ActSingleLogWriteDao            actSingleLogWriteDao;
    @Resource
    private ActFlashSaleReadDao             actFlashSaleReadDao;
    @Resource
    private ActFlashSaleStageReadDao        actFlashSaleStageReadDao;
    @Resource
    private ActFlashSaleProductWriteDao     actFlashSaleProductWriteDao;
    @Resource
    private SellerTransportModel            sellerTransportModel;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private ProductGoodsReadDao             productGoodsReadDao;
    @Resource
    private ActFlashSaleLogWriteDao         actFlashSaleLogWriteDao;
    @Resource
    private ActGroupReadDao                 actGroupReadDao;
    @Resource
    private ActGroupWriteDao                actGroupWriteDao;
    @Resource
    private ActBiddingReadDao               actBiddingReadDao;
    @Resource
    private ActBiddingWriteDao              actBiddingWriteDao;
    @Resource
    private ActIntegralReadDao              actIntegralReadDao;
    @Resource
    private ActIntegralWriteDao             actIntegralWriteDao;

    @Resource
    private OrderDeliveryWriteDao 			 deliveryWriteDao;

    @Resource
    private OrderDeliveryReadDao 			 deliveryReadDao;

    @Resource
    private MemberProductExchangeReadDao     memberProductExchangeReadDao;

    @Resource
    private MessageModel	messageModel;

    @Resource
    private MemberSpecialIntegralWriteDao memberSpecialIntegralWriteDao;
    @Resource
    private MemberSpecialIntegralReadDao  memberSpecialIntegralReadDao;


    /**
     * 根据id取得订单
     * @param  ordersId
     * @return
     */
    public Orders getOrdersById(Integer ordersId) {
        return ordersWriteDao.get(ordersId);
    }

    /**
     * 根据orderSn取得订单
     * @param  orderSn
     * @return
     */
    public Orders getOrdersBySn(String orderSn) {
        return ordersWriteDao.getByOrderSn(orderSn);
    }

    /**
     * 根据订单ID获取订单号
     * @param id
     * @return
     */
    public String getOrderSnById(Integer id) {
        if (id == null) {
            throw new BusinessException("id不能为null");
        }
        Orders orders = ordersWriteDao.get(id);
        if (orders.getOrderSn() == null) {
            throw new BusinessException("没有该订单:" + orders.getId());
        }
        return orders.getOrderSn();
    }

    /**
     * 根据订单ID删除订单
     * @param ordersId
     * @return
     */
    public boolean deleteOrder(Integer ordersId) {
        if (ordersId == null)
            throw new BusinessException("删除订单[" + ordersId + "]时出错");
        return ordersWriteDao.delete(ordersId) > 0;
    }

    public Integer getOrdersCount(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount(queryMap);
    }

    public List<Orders> getOrders(Map<String, String> queryMap, Integer start,
                                  Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getOrders(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerWriteDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 修改订单
     * @param orders 订单
     * @param type 商家操作订单类型
     * @param sellerUser 商家管理员对象
     * @param updateStore 是否需要修改库存
     * @return
     * @throws Exception
     */
    public Integer updateOrdersBySeller(Orders orders, int type,
                                        SellerUser sellerUser) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.更新订单
            result = ordersWriteDao.update(orders);
            Orders ordersDB = ordersWriteDao.get(orders.getId());
            //2.写订单日志
            writeOrderInfo(ordersDB, sellerUser, type);

            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("更新订单时出现未知异常：" + e);
            transactionManager.rollback(status);
            throw e;
        }
        return result;
    }

    /**
     * 商家确认订单
     * @param orderId
     * @param seller
     * @return
     * @throws Exception
     */
    public boolean confirmOrdersBySeller(Integer orderId, SellerUser sellerUser) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            Orders ordersDb = ordersReadDao.get(orderId);
            if (ordersDb == null) {
                throw new BusinessException("订单信息获取失败，请稍后再试。");
            }
            if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_2)) {
                throw new BusinessException("只有待确认状态的订单可以执行此操作！");
            }
            if (!ordersDb.getSellerId().equals(sellerUser.getSellerId())) {
                throw new BusinessException("您无权操作该条数据。");
            }

            Orders orders = new Orders();
            orders.setId(orderId);
            //待发货
            orders.setOrderState(Orders.ORDER_STATE_3);

            // 更新订单
            result = ordersWriteDao.update(orders);

            // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 更新货品和商品的库存
            //            this.updateProductStock(orderId, false);

            // 写订单日志
            writeOrderInfo(ordersDb, sellerUser, Orders.CONFIRM);

            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("商家确认订单时出现未知异常：" + e);
            transactionManager.rollback(status);
            throw e;
        }
        return result > 0;
    }

    /**
     * 写订单日志
     * @param orders
     * @throws Exception
     */
    private void writeOrderInfo(Orders orders, SellerUser sellerUser, int type) throws Exception {
        OrderLog log = new OrderLog(sellerUser.getId(), sellerUser.getName(), orders.getId(),
            orders.getOrderSn(), "", new Date());
        switch (type) {
            case Orders.DELIVER:
                log.setContent("商家已发货");
                break;
            case Orders.CANCEL:
                log.setContent("商家取消了此订单");
                break;
            case Orders.UPDATE_AMOUNT:
                log.setContent("商家修改订单金额为" + orders.getMoneyOrder());
                break;
            case Orders.SUBMIT_PAY:
                log.setContent("商家已确认收款");
                break;
            case Orders.CONFIRM:
                log.setContent("商家确认订单");
                break;
            default:
                break;
        }
        orderLogWriteDao.save(log);
    }

    /**
     * 修改订单
     * @param orders 订单
     * @param type 商家操作订单类型
     * @param systemAdmin 平台管理员对象
     * @return
     * @throws Exception
     */
    public Integer updateOrdersByAdmin(Orders orders, int type,
                                       SystemAdmin systemAdmin) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.更新订单
            result = ordersWriteDao.update(orders);
            Orders ordersDB = ordersWriteDao.get(orders.getId());
            //2.写订单日志
            writeOrderInfo(ordersDB, systemAdmin, type);
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("更新订单时出现未知异常：" + e);
            transactionManager.rollback(status);
            throw e;
        }
        return result;
    }

    /**
     * 写订单日志
     * @param orders
     * @throws Exception
     */
    private void writeOrderInfo(Orders orders, SystemAdmin systemAdmin, int type) throws Exception {
        OrderLog log = new OrderLog(systemAdmin.getId(), systemAdmin.getName(), orders.getId(),
            orders.getOrderSn(), "", new Date());
        switch (type) {
            case Orders.DELIVER:
                log.setContent("已由平台管理员发货");
                break;
            case Orders.CANCEL:
                log.setContent("平台管理员取消了此订单");
                break;
            case Orders.UPDATE_AMOUNT:
                log.setContent("平台管理员修改订单金额为" + orders.getMoneyOrder());
                break;
            case Orders.SUBMIT_PAY:
                log.setContent("平台管理员已确认收款");
                break;
            case Orders.CONFIRM:
                log.setContent("平台管理员确认订单");
                break;
            case Orders.UPDATE_STATE:
                log.setContent("平台管理员更新了订单状态");
                break;
            default:
                break;
        }
        orderLogWriteDao.save(log);
    }

    //    /**
    //     * 修改商品和货品的库存<br>
    //     * <li>检验库存是否足够
    //     * <li>修改货品库存
    //     * <li>修改商品库存
    //     * @param ordersId 订单ID
    //     * @param isAddStock 是否是增加库存：如果是true，表示库存还原，增加库存；如果是false，表示减库存
    //     */
    //    private void updateProductStock(Integer ordersId, boolean isAddStock) {
    //        List<OrdersProduct> opList = ordersProductWriteDao.getByOrderId(ordersId);
    //        for (OrdersProduct ordersProduct : opList) {
    //            // 如果是增加库存，不需要校验库存数量
    //            if (!isAddStock) {
    //                // 校验库存数量是否足够
    //                ProductGoods productGoods = productGoodsWriteDao
    //                    .get(ordersProduct.getProductGoodsId());
    //                if ((productGoods.getProductStock() - ordersProduct.getNumber()) < 0) {
    //                    throw new BusinessException("商品【" + ordersProduct.getProductName() + "】的库存不足！");
    //                }
    //            }
    //
    //            Integer updateStock = ordersProduct.getNumber();
    //            // 如果是true，把数量变为负数
    //            if (isAddStock) {
    //                updateStock = 0 - updateStock;
    //            }
    //            // 修改货品库存
    //            Integer updateGoodStock = productGoodsWriteDao
    //                .updateStock(ordersProduct.getProductGoodsId(), updateStock);
    //            if (updateGoodStock == 0) {
    //                log.error("[OrdersModel][updateProductStock]更新货品库存时失败。");
    //                throw new BusinessException("更新商品库存时失败，请重试！");
    //            }
    //            // 修改商品库存
    //            Integer updateProductStock = productWriteDao.updateStock(ordersProduct.getProductId(),
    //                updateStock);
    //            if (updateProductStock == 0) {
    //                log.error("[OrdersModel][updateProductStock]更新商品库存时失败。");
    //                throw new BusinessException("更新商品库存时失败，请重试！");
    //            }
    //        }
    //    }
    //
    //    /**
    //     * 修改商品和货品的库存<br>
    //     * <li>修改货品库存
    //     * <li>修改商品库存
    //     * @param ordersId 订单ID
    //     */
    //    private void updateProductStockForPayAfter(Integer ordersId) {
    //        List<OrdersProduct> opList = ordersProductWriteDao.getByOrderId(ordersId);
    //        for (OrdersProduct ordersProduct : opList) {
    //            Integer updateStock = ordersProduct.getNumber();
    //            // 修改货品库存
    //            Integer updateGoodStock = productGoodsWriteDao
    //                .updateStock(ordersProduct.getProductGoodsId(), updateStock);
    //            if (updateGoodStock == 0) {
    //                log.error("[OrdersModel][updateProductStock]更新货品库存时失败，ordersProductId="
    //                          + ordersProduct.getId() + "。");
    //            }
    //            // 修改商品库存
    //            Integer updateProductStock = productWriteDao.updateStock(ordersProduct.getProductId(),
    //                updateStock);
    //            if (updateProductStock == 0) {
    //                log.error("[OrdersModel][updateProductStock]更新商品库存时失败，ordersProductId="
    //                          + ordersProduct.getId() + "。");
    //            }
    //        }
    //    }

    /**
     * 取消订单，目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param seller 商家对象
     * @return
     * @throws Exception
     */
    public boolean cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Orders orderDb = ordersWriteDao.get(ordersId);
            // 只能取消未付款或者待确认的订单
            if (Orders.ORDER_STATE_1 != orderDb.getOrderState().intValue()
                && Orders.ORDER_STATE_2 != orderDb.getOrderState().intValue()) {
                throw new BusinessException("对不起，只能取消未付款或者待确认状态的订单！");
            }
            if (orderDb.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
                // 竞价尾款订单不能取消
                throw new BusinessException("集合竞价尾款订单不能取消！");
            }

            Orders ordersNew = new Orders();
            ordersNew.setId(ordersId);
            ordersNew.setFinishTime(new Date());
            ordersNew.setOrderState(Orders.ORDER_STATE_6);
            // 1.更新订单
            result = ordersWriteDao.update(ordersNew);
            // 2.写订单日志
            this.writeOrderInfo(orderDb, sellerUser, Orders.CANCEL);
            // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 3.更新货品和商品的库存（可以取消的订单还没有占用库存，不用还原库存）
            //            this.updateProductStock(ordersNew.getId(), true);
            // 3.返还积分
            this.cancelOrderBackIntegral(orderDb);
            // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 5.还原销量
            //            // 普通订单需要还原销量，限时抢购、团购订单、集合竞价、积分换购订单的销量在还原库存时一起还原
            //            if (orderDb.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
            //                this.restoreActualSales(ordersProductReadDao.getByOrderId(ordersId));
            //            }

            // 4.取消订单还原库存和实际销量
            this.cancelOrderBackStockAndActualSales(orderDb.getOrderType(), ordersId);
            // 5.返回优惠券
            this.cancelOrderBackCoupon(orderDb, sellerUser.getId(), sellerUser.getName());
            // 6.退回付款金额
            this.cancelOrderBackMoney(orderDb, sellerUser.getId(), sellerUser.getName());

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("取消订单时出现未知异常：" + e);
            throw e;
        }
        return result > 0;
    }

    /**
     * 根据会员ID，订单状态获取订单数量
     * @param memberId
     * @param orderState
     * @return
     */
    public Integer getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        return ordersReadDao.getOrderNumByMIdAndState(memberId, orderState);
    }

    /**
     * 根据条件取得订单并封装网单
     * @param queryMap
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Orders> getOrderWithOrderProduct(Map<String, String> queryMap, Integer start,
                                                 Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getOrders(parammap, start, size);
        for (Orders orders : list) {

            List<OrdersProduct> orderProductList = ordersProductWriteDao
                .getByOrderId(orders.getId());

            //记录订单下所有网单可以退换货数量

            int exchangeState = 0;
            //判断网单是否正在执行换货如果在执行则不能执行退货
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", orders.getId());
            List<MemberProductExchange> list1 = memberProductExchangeReadDao.queryList(map);
            for (int i = 0; i < list1.size(); i++) {
            	if(list1.get(i).getState() < 4){
            		exchangeState = 1;
            		break;
            	}
			}
            orders.setExchangeState(exchangeState);
            int backOrExchangeNum = 0;
            if (orderProductList.size() == 0) {
                log.error("网单信息获取失败。");
                throw new BusinessException("网单信息获取失败，请联系管理员！");
            } else {
                //根据产品id查小图路径
                for (OrdersProduct op : orderProductList) {
                    String productLeadLittle = frontProductPictureUtil
                        .getproductLeadLittle(op.getProductId());
                    Product product = new Product();
                    product = productReadDao.get(op.getProductId());
                    op.setSource(product.getSource());
                    op.setProductCode(product.getProductCode());
                    String images = productGoodsReadDao.get(op.getProductGoodsId()).getImages();
                    if(images != null && !"".equals(images)) {
                    	productLeadLittle = images;
                    }
                    op.setProductLeadLittle(productLeadLittle);
                    backOrExchangeNum += (op.getNumber() - op.getBackNumber()
                                          - op.getExchangeNumber());
                }
                if (backOrExchangeNum < 0) {
                	backOrExchangeNum= 0;
                } else {
                    orders.setBackOrExchangeNum(backOrExchangeNum);
                }
                orders.setOrderProductList(orderProductList);
            }
        }
        return list;
    }

    /**
     * 取消订单 目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     * @return
     */
    public boolean cancelOrder(Integer ordersId, Integer optId, String optName) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (ordersId == null || ordersId == 0) {
                throw new BusinessException("订单ID为空，请重试！");
            }
            //获取订单
            Orders ordersDb = ordersWriteDao.get(ordersId);
            
            if (ordersDb == null) {
                throw new BusinessException("获取订单信息失败，请重试！");
            } else if (!(ordersDb.getOrderState().equals(Orders.ORDER_STATE_1)
                         || (ordersDb.getOrderState().equals(Orders.ORDER_STATE_2))
                         || (ordersDb.getOrderState().equals(Orders.ORDER_STATE_3)))) {
                throw new BusinessException("订单已发货不能取消！");
            }
            if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
                // 竞价尾款订单不能取消
                throw new BusinessException("集合竞价尾款订单不能取消！");
            }

            //设置订单状态
            Orders orders = new Orders();
            orders.setId(ordersId);
            orders.setFinishTime(new Date());
            orders.setOrderState(Orders.ORDER_STATE_6);
            int count = ordersWriteDao.update(orders);
            if (count == 0) {
                throw new BusinessException("订单更新失败，请重试！");
            }

            String content = "订单已被用户主动取消";
            if(null != optId && optId == 0) {
            	content = "订单已被管理员取消";
            }
            
            //记录订单日志
            OrderLog orderLog = new OrderLog();
            orderLog.setContent(content);
            orderLog.setOperatingId(optId);
            orderLog.setOrdersId(ordersId);
            orderLog.setOrdersSn(ordersDb.getOrderSn());
            orderLog.setOperatingName(optName);

            int logCount = orderLogWriteDao.save(orderLog);
            if (logCount == 0) {
                throw new BusinessException("订单日志保存失败，请重试！");
            }
            
            // 未付款订单直接取消 不退回积分和余额
            if(ordersDb.getPaymentStatus() == Orders.PAYMENT_STATUS_0) {
            	// 还原库存和实际销量
                this.cancelOrderBackStockAndActualSales(ordersDb.getOrderType(), ordersId);
                transactionManager.commit(status);
                
            	return true;
            }
            
            // 返还积分
            this.cancelOrderBackIntegral(ordersDb);

            // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 普通订单需要还原销量，限时抢购、团购、集合竞价、积分换购订单的销量在还原库存时一起还原
            //            if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
            //                // 还原销量
            //                this.restoreActualSales(ordersProductReadDao.getByOrderId(ordersId));
            //            }

            // 返回优惠券
            this.cancelOrderBackCoupon(ordersDb, optId, optName);

            // 退回付款金额
            this.cancelOrderBackMoney(ordersDb, optId, optName);

            // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 如果订单是待发货状态的订单，则还原库存
            //            if (ordersDb.getOrderState().equals(Orders.ORDER_STATE_3)) {
            //                if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
            //                    // 限时抢购订单时还原活动商品的库存
            //                    List<OrdersProduct> opList = ordersProductReadDao
            //                        .getByOrderId(ordersDb.getId());
            //                    // 限时抢购只有一个网单
            //                    this.backFlashProductStockAndActualSales(
            //                        opList.get(0).getActFlashSaleProductId(), opList.get(0).getNumber());
            //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_3) {
            //                    // 团购订单时还原活动商品的库存
            //                    List<OrdersProduct> opList = ordersProductReadDao
            //                        .getByOrderId(ordersDb.getId());
            //                    // 团购只有一个网单
            //                    this.backGroupStockAndActualSales(opList.get(0).getActGroupId(),
            //                        opList.get(0).getNumber());
            //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_4
            //                           || ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
            //                    // 竞价定金订单时不会进入此逻辑，因为定金订单支付后订单状态直接设置为已完成
            //                    // 竞价尾款订单时不会进入此逻辑，因为尾款订单不能取消
            //                    // 此处为逻辑清晰、代码易读增加此逻辑
            //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_6) {
            //                    // 积分换购订单时还原活动商品的库存
            //                    List<OrdersProduct> opList = ordersProductReadDao
            //                        .getByOrderId(ordersDb.getId());
            //                    // 积分换购只有一个网单
            //                    this.backIntegralStockAndActualSales(opList.get(0).getActIntegralId(),
            //                        opList.get(0).getNumber());
            //                } else {
            //                    // 普通订单
            //                    // 更新货品和商品的库存
            //                    this.updateProductStock(ordersDb.getId(), true);
            //                }
            //            }

            // 还原库存和实际销量
            this.cancelOrderBackStockAndActualSales(ordersDb.getOrderType(), ordersId);

            //订单已付款 订单取消推送消息 未付款 不推送
            if(ordersDb.getPaymentStatus() == Orders.PAYMENT_STATUS_1) {
            	Map<String,Object> map = new HashMap<String,Object>();

            	map.put("startTime", ordersDb.getPayTime());
                map.put("endTime",new Date());
                map.put("orderSn", ordersDb.getOrderSn());
                //推送消息给用户
                Boolean flag = messageModel.sendMessageToMember(map, ordersDb.getMemberId(), Message.DDQXTZ);
                if(!flag) {
                	throw new BusinessException("发送消息到用户失败");
                }
            }

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 取消订单时，把现金支付和余额支付的钱退到用户余额中
     * @param order
     * @param optId
     * @param optName
     */
    private void cancelOrderBackMoney(Orders order, Integer optId, String optName) {

        Member memberDB = memberWriteDao.get(order.getMemberId());

        //如果订单未付款 则执行如下操作
        if(order.getPaymentStatus() == Orders.PAYMENT_STATUS_0) {
    		return;
    	}

        // 付款的金额（现金支付+余额支付）
        BigDecimal money = BigDecimal.ZERO;
        //如果是福利积分订单 则获取该订单付款的余额
        if(order.getIsWelfareOrder() == Product.IS_WELFARE_2) {
        	money = order.getMoneyIntegral();
        }else {
        	money = order.getMoneyPaidReality().add(order.getMoneyPaidBalance());
        }


        // 如果付款金额大于0，则退回并记录日志
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            // 修改用户的余额
            Member updateBalanceObj = new Member();
            updateBalanceObj.setId(order.getMemberId());
            updateBalanceObj.setBalance(money);
            Integer updateBalance = memberWriteDao.updateBalance(updateBalanceObj);
            if (updateBalance == 0) {
                log.error("退回余额时失败。");
                throw new BusinessException("退回余额时失败，请重试！");
            }

            // 记录【会员账户余额变化日志表】
            MemberBalanceLogs logs = new MemberBalanceLogs();
            logs.setMemberId(memberDB.getId());
            logs.setMemberName(memberDB.getName());
            logs.setMoneyBefore(memberDB.getBalance());
            logs.setMoneyAfter(memberDB.getBalance().add(money));
            logs.setMoney(money);
            logs.setCreateTime(new Date());
            logs.setState(MemberBalanceLogs.STATE_2);
            logs.setRemark("取消订单，订单号" + order.getOrderSn());
            logs.setOptId(optId);
            logs.setOptName(optName);

            Integer balanceLog = memberBalanceLogsWriteDao.save(logs);
            if (balanceLog == 0) {
                log.error("记录会员余额变化日志时出错。");
                throw new BusinessException("退回余额时失败，请重试！");
            }
        }
    }

    /**
     * 取消订单时退回用户优惠券
     * @param order
     * @param optId
     * @param optName
     */
    private void cancelOrderBackCoupon(Orders order, Integer optId, String optName) {

        if (order.getCouponUserId() != null && order.getCouponUserId() > 0) {
            CouponUser couponUser = couponUserReadDao.get(order.getCouponUserId());
            if (couponUser == null) {
                log.error("用户优惠券获取失败。");
                throw new BusinessException("返还用户优惠券时失败，请重试！");
            }
            Integer backCouponUser = couponUserWriteDao.backCouponUser(order.getMemberId(),
                couponUser.getId());
            if (backCouponUser < 1) {
                log.error("修改用户优惠券使用次数失败。");
                throw new BusinessException("返还用户优惠券时失败，请重试！");
            }
            // 设定优惠券使用日志
            CouponOptLog couponOptLog = new CouponOptLog();
            couponOptLog.setCouponUserId(couponUser.getId());
            couponOptLog.setMemberId(couponUser.getMemberId());
            couponOptLog.setSellerId(couponUser.getSellerId());
            couponOptLog.setCouponId(couponUser.getCouponId());
            couponOptLog.setOptType(CouponOptLog.OPT_TYPE_3);
            couponOptLog.setOrderId(order.getId());
            couponOptLog.setCreateUserId(optId);
            couponOptLog.setCreateUserName(optName);
            couponOptLog.setCreateTime(new Date());
            couponOptLogWriteDao.insert(couponOptLog);
        }
    }

    //    private void restoreActualSales(List<OrdersProduct> opList) {
    //        if (opList != null && opList.size() > 0) {
    //            for (OrdersProduct op : opList) {
    //                this.updateProductActualSales(op.getProductId(), op.getProductGoodsId(),
    //                    0 - op.getNumber());
    //            }
    //        }
    //    }

    /**
     * 订单取消 退回积分和余额 并生成日志
     * @param order
     */
    private void cancelOrderBackIntegral(Orders order) {
    	//如果是福利积分订单 并且未付款 则不修改积分
    	if(order.getIsWelfareOrder() == Product.IS_WELFARE_2 && order.getPaymentStatus() == Orders.PAYMENT_STATUS_0) {
    		return;
    	}
        // 最终需要修改的积分数量
        int backValue = 0;

        //判断订单是否使用了专项积分
        if(null != order.getSpecialIntegral() && order.getSpecialIntegral() > 0) {

             List<MemberGradeIntegralLogs> mgilList =
             memberGradeIntegralLogsReadDao.getMemberGradeIntegralLogsByRefCode(order.getOrderSn());

             for(MemberGradeIntegralLogs mgil : mgilList) {
            	 MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                 memberGradeIntegralLogs.setMemberId(order.getMemberId());
                 memberGradeIntegralLogs.setMemberName(order.getMemberName());
                 memberGradeIntegralLogs.setValue(order.getSpecialIntegral());
                 memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_11);
                 memberGradeIntegralLogs.setOptDes("取消订单返还专项积分(订单号:" + order.getOrderSn() + ")");
                 memberGradeIntegralLogs.setRefCode(order.getOrderSn());
                 memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                 memberGradeIntegralLogs.setCreateTime(new Date());
                 Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);

                 if (save == 0) {
                     throw new BusinessException("取消订单返还专项积分失败订单号(" + order.getOrderSn() + ")，请重试！");
                 }

                 MemberSpecialIntegral newmsi = new MemberSpecialIntegral();
            	 newmsi.setId(mgil.getMsiId());
            	 newmsi.setValue(mgil.getValue());
            	 save = memberSpecialIntegralWriteDao.updateValue(newmsi);
            	 if (save == 0) {
                     throw new BusinessException("修改用户专项积分失败请重试！");
                 }
             }
        }
        // 1、返还该订单消耗的积分
        // 消耗了积分才返还
        if (order.getIntegral() > 0) {
            // 返还积分是增加
            backValue = order.getIntegral();

            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
            memberGradeIntegralLogs.setMemberId(order.getMemberId());
            memberGradeIntegralLogs.setMemberName(order.getMemberName());
            memberGradeIntegralLogs.setValue(order.getIntegral());
            memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_11);
            memberGradeIntegralLogs.setOptDes("取消订单返还积分(订单号:" + order.getOrderSn() + ")");
            memberGradeIntegralLogs.setRefCode(order.getOrderSn());
            memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
            memberGradeIntegralLogs.setCreateTime(new Date());
            Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (save == 0) {
                throw new BusinessException("记录用户积分消费日志失败（取消订单返还积分），请重试！");
            }
        }

        // 2、追回付款时送给用户的积分
        if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
            // 订单已付款才会发生此类型积分追回

            // 计算购物时平台送出的积分追回为减少用户的积分
            MemberGradeIntegralLogs sendIntLog = memberGradeIntegralLogsReadDao
                .getIntLogByMIdAndOrderSnAndOptType(order.getMemberId(),
                    MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_3, order.getOrderSn(),
                    MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
            if (sendIntLog != null && sendIntLog.getValue() > 0) {
                //  追回积分是减少
                backValue = backValue - sendIntLog.getValue();

                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                memberGradeIntegralLogs.setMemberId(order.getMemberId());
                memberGradeIntegralLogs.setMemberName(order.getMemberName());
                memberGradeIntegralLogs.setValue(sendIntLog.getValue());
                memberGradeIntegralLogs
                    .setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_13);
                memberGradeIntegralLogs.setOptDes("取消订单追回积分(订单号:" + order.getOrderSn() + ")");
                memberGradeIntegralLogs.setRefCode(order.getOrderSn());
                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                memberGradeIntegralLogs.setCreateTime(new Date());
                Integer saveLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                if (saveLog == 0) {
                    throw new BusinessException("记录用户积分消费日志失败（取消订单追回积分），请重试！");
                }
            }
        }

        // 3、修改用户积分数量，记录积分消耗日志
        Member memberNew = new Member();
        memberNew.setId(order.getMemberId());
        memberNew.setIntegral(backValue);
        Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
        if (updateIntegral == 0) {
            throw new BusinessException("取消订单修改用户积分时失败，请重试！");
        }
    }

    /**
     * 根据订单id 取订单、网单及产品图片信息
     * @param request
     * @return
     */
    public Orders getOrderWithOPById(Integer orderId) {
        Orders orders = ordersWriteDao.get(orderId);
        if (orders == null) {
            throw new BusinessException("订单信息获取失败，请重试！");
        }

        //根据订单id查询网单
        List<OrdersProduct> orderProductList = ordersProductWriteDao.getByOrderId(orders.getId());

        if (orderProductList.size() == 0) {
            throw new BusinessException("网单信息获取失败，请联系管理员！");
        }
        //根据产品id查小图路径
        for (OrdersProduct op : orderProductList) {
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(op.getProductId());
            String images = productGoodsReadDao.get(op.getProductGoodsId()).getImages();
            if(images != null && !"".equals(images)) {
            	productLeadLittle = images;
            }
            op.setProductLeadLittle(productLeadLittle);
            //判断积分换购活动ID不等于空 则查询出积分换购活动
            if(op.getActIntegralId()!= null && op.getActIntegralId() !=0){
            	op.setActIntegral(actIntegralReadDao.get(op.getActIntegralId()));
            }
            Product product = productReadDao.get(op.getProductId());
            op.setSource(product.getSource());
            op.setProductCode(product.getProductCode());

        }
        orders.setOrderProductList(orderProductList);

        return orders;
    }

    /**
     * 用户提交订单<br>
     * 1、判断是否使用余额、判断支付密码<br>
     * 2、按商家拆分订单<br>
     * 3、保存网单<br>
     * 4、清除购物车<br>
     * 5、如果使用余额，并且余额足够支付所有订单，修改支付状态、修改库存<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommit(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            // 根据登录用户取得购物车信息，数据都从写库获取，数据已经根据商家封装好
            CartInfoVO cartInfoVO = cartModel.getCartInfoByMId(orderCommitVO.getMemberId(), address,
                orderCommitVO.getSource(), 2,Cart.SOURCE_1);
            // 获取提交订单的用户
            Member member = memberWriteDao.get(orderCommitVO.getMemberId());

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            // 根据来源判断渠道，默认渠道为PC
            int channel = ConstantsEJS.CHANNEL_2;
            if (orderCommitVO.getSource() != null
                && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                    || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                    || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
                channel = ConstantsEJS.CHANNEL_3;
            }

            if (cartInfoVO != null && cartInfoVO.getCartListVOs().size() > 0) {

                // 使用商家优惠券信息
                Map<Integer, OrderCouponVO> sellerCouponMap = orderCommitVO.getSellerCouponMap();
                // 优惠券使用日志信息（用于记录优惠券用户表记录的操作日志）
                List<CouponOptLog> couponOptLogList = new ArrayList<CouponOptLog>();
                // 满减活动参加日志表
                List<ActFullLog> actFullLogList = new ArrayList<ActFullLog>();
                // 单品立减活动参加日志表
                List<ActSingleLog> actSingleLogList = new ArrayList<ActSingleLog>();

                List<Orders> orderList = new ArrayList<Orders>();
                // 所有订单总金额（用于计算每个订单分摊的积分金额）
                BigDecimal allMoneyOrder = BigDecimal.ZERO;
                // 获取支付订单号
                // 支付订单号规则：下单时存入的支付订单号为paySn+本次下单第一个订单ID的后6位
                // 如果从订单中心点击支付，则修改支付订单号与订单号一样（所以支付订单号可能有的位数比其他订单的位数少6位）
                String paySn = RandomUtil.getOrderSn();
                // 记录是否是本地下单的第一个订单，如果是需要修改该订单的支付订单号
                boolean isFirstOrder = true;
                // 循环各个商家，每个商家是一个订单
                for (CartListVO cartListVO : cartInfoVO.getCartListVOs()) {

                    List<Cart> cartList = cartListVO.getCartList();
                    if (cartList != null && cartList.size() > 0) {
                        Seller seller = cartListVO.getSeller();
                        if (seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
                            throw new BusinessException(
                                "商家[" + seller.getSellerName() + "]已被冻结，请把该商家的商品移出购物车后再下单，谢谢！");
                        }
                        // 如果使用了当前商家的优惠券，校验优惠券信息
                        OrderCouponVO orderCouponVO = sellerCouponMap.get(seller.getId());
                        if (orderCouponVO != null) {
                            this.checkSellerCoupon(orderCouponVO, member.getId(), cartListVO,
                                channel);
                        }

                        Integer isWelfare = 1;
                        if(cartList.get(0).getProduct().getIsWelfareProduct() == Product.IS_WELFARE_2) {
                        	isWelfare = Product.IS_WELFARE_2;
                        }
                        // 保存订单及日志信息
                        Orders order = this.saveOrderInfo(paySn, cartListVO, member, orderCommitVO,
                            address, orderCouponVO, couponOptLogList, actFullLogList,isWelfare);
                        orderList.add(order);
                        allMoneyOrder = allMoneyOrder.add(order.getMoneyOrder());

                        // 保存网单信息
                        this.saveOrderProductInfo(order, cartList, member, actSingleLogList);

                        // 是第一个订单则修改如下内容，不是第一个则不执行如下内容
                        if (isFirstOrder) {
                            // 修改标志位
                            isFirstOrder = false;
                            // 修改支付订单号（连接上第一个订单的ID后6位）
                            paySn = this.paySnJoinId(paySn, order.getId());
                            // 修改第一个订单的支付订单号
                            Integer updateRow = ordersWriteDao.updatePaySnById(order.getId(),
                                paySn);
                            if (updateRow == 0) {
                                log.error("修改第一个订单的支付订单号时失败。");
                                throw new BusinessException("订单提交出现异常，请重试！");
                            }
                        }
                    }
                }

                //删除购物车数据，不判断删除的成功与否，购物车的数据不影响，只打日志，不抛异常
                int count = cartWriteDao.deleteByMemberId(member.getId(),Cart.SOURCE_1);
                if (count == 0) {
                    log.error("删除购物车失败！");
                }

                // 转换总金额
                int moneyIntegral = 0;
                // 计算订单使用积分金额（都计算成整数）
                if (integral != null && integral > 0) {
                    // 计算转换总金额
                    moneyIntegral = integral / config.getIntegralScale();
                    // 已经分摊的金额
                    int moneyShared = 0;
                    // 已经分摊的积分
                    int integralShared = 0;

                    for (int i = 0; i < orderList.size(); i++) {
                        Orders order = orderList.get(i);
                        // 订单金额为0则不分摊
                        if (order.getMoneyOrder().compareTo(BigDecimal.ZERO) <= 0) {
                            continue;
                        }
                        if (integralShared >= integral) {
                            // 如果已经分摊的积分大于等于使用的积分则跳出循环
                            break;
                        }

                        // 用一个新对象来更新数据库
                        Orders orderNew = new Orders();
                        orderNew.setId(order.getId());

                        // 当前订单分摊金额、积分数
                        int orderMoneyIntegral = 0;
                        int orderIntegral = 0;
                        if ((i + 1) == orderList.size()) {
                            // 如果是最后一个订单，按总的数量减去已经分摊的数量
                            orderMoneyIntegral = moneyIntegral - moneyShared;
                            orderIntegral = integral - integralShared;
                            orderNew.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            orderNew.setIntegral(orderIntegral);
                            order.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            order.setIntegral(orderIntegral);
                        } else {
                            // 计算分摊比例
                            BigDecimal scale = order.getMoneyOrder().divide(allMoneyOrder, 2,
                                BigDecimal.ROUND_HALF_UP);
                            // 计算分摊金额
                            BigDecimal decimal = (new BigDecimal(moneyIntegral)).multiply(scale);
                            // 不足一块时按一块计算
                            if (decimal.compareTo(new BigDecimal(1)) < 0) {
                                orderMoneyIntegral = 1;
                            } else {
                                orderMoneyIntegral = decimal.intValue();
                            }
                            if (orderMoneyIntegral > (moneyIntegral - moneyShared)) {
                                // 如果计算出的金额比剩余金额大，则分摊金额=总换算金额-已分摊金额
                                // 防止小金额订单占用1块钱出现后续比例计算后超出的情况
                                orderMoneyIntegral = moneyIntegral - moneyShared;
                            }
                            // 计算分摊积分数
                            orderIntegral = orderMoneyIntegral * config.getIntegralScale();
                            orderNew.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            orderNew.setIntegral(orderIntegral);
                            order.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            order.setIntegral(orderIntegral);
                        }
                        // 记录已经分摊的金额和积分
                        moneyShared += orderMoneyIntegral;
                        integralShared += orderIntegral;

                        // 当前订单分摊金额、积分数大于0则更改订单的积分转换金额和积分数
                        if (orderMoneyIntegral > 0 || orderIntegral > 0) {
                            Integer update = ordersWriteDao.update(orderNew);
                            if (update == 0) {
                                throw new BusinessException("设置订单积分金额时失败，请重试！");
                            }
                            // 记录积分消耗日志
                            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                            memberGradeIntegralLogs.setMemberId(member.getId());
                            memberGradeIntegralLogs.setMemberName(member.getName());
                            memberGradeIntegralLogs.setValue(orderIntegral);
                            memberGradeIntegralLogs
                                .setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
                            memberGradeIntegralLogs
                                .setOptDes("积分支付消费(订单号:" + order.getOrderSn() + ")");
                            memberGradeIntegralLogs.setRefCode(order.getOrderSn());
                            memberGradeIntegralLogs
                                .setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                            memberGradeIntegralLogs.setCreateTime(new Date());
                            Integer save = memberGradeIntegralLogsWriteDao
                                .save(memberGradeIntegralLogs);
                            if (save == 0) {
                                throw new BusinessException("记录用户积分消费日志失败，请重试！");
                            }

                            // 记录积分支付日志
                            OrderPayLog payLog = new OrderPayLog();
                            payLog.setOrdersId(order.getId());
                            payLog.setOrdersSn(order.getOrderSn());
                            payLog.setPaymentCode(Orders.PAYMENT_CODE_INTEGRAL);
                            payLog.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);
                            payLog.setPayMoney(new BigDecimal(orderMoneyIntegral));
                            payLog.setPayIntegral(orderIntegral);
                            payLog.setMemberId(member.getId());
                            payLog.setMemberName(member.getName());
                            payLog.setCreateTime(new Date());
                            orderPayLogWriteDao.insert(payLog);
                        }
                    }

                    // 修改用户积分数
                    Member memberNew = new Member();
                    memberNew.setId(member.getId());
                    memberNew.setIntegral(0 - integral);
                    Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
                    if (updateIntegral == 0) {
                        throw new BusinessException("扣除用户积分时失败，请重试！");
                    }

                }

                if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
                    // 只要是货到付款，则不跳转到支付页面
                    orderSuccVO.setGoJumpPayfor(false);
                }

                // 如果订单还没有支付
                // 循环判断订单应支付的金额，如果应支付金额小于等于0则修改订单的状态为支付状态
                if (!orderSuccVO.getIsPaid()) {
                    // 返回true说明修改了支付状态
                    if (this.checkNeedPay(orderList, member)) {
                        orderSuccVO.setIsPaid(true);
                        orderSuccVO.setGoJumpPayfor(false);
                    }
                }

                // 订单操作完毕，操作优惠券、满减、立减的使用和参与日志
                if (couponOptLogList.size() > 0) {
                    // 记录用户优惠券操作日志
                    couponOptLogWriteDao.batchInsertCouponOptLog(couponOptLogList);
                    // 修改用户优惠券可使用次数
                    for (CouponOptLog couponOptLog : couponOptLogList) {
                        couponUserWriteDao.updateCanUse(couponOptLog.getMemberId(),
                            couponOptLog.getCouponUserId(), couponOptLog.getOrderId());
                    }
                }
                // 记录满减活动参与日志
                if (actFullLogList.size() > 0) {
                    actFullLogWriteDao.batchInsertActFullLog(actFullLogList);
                }
                // 记录单品立减参与日志
                if (actSingleLogList.size() > 0) {
                    actSingleLogWriteDao.batchInsertActSingleLog(actSingleLogList);
                }

                //封装返回对象
                orderSuccVO.setOrdersList(orderList);
                orderSuccVO.setPaySn(paySn);
                orderSuccVO.setPayAmount(this.orderMoneyAlls(orderList));
            } else {
                throw new BusinessException("购物车中无商品，请选择购买的商品。");
            }
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 支付订单号连接订单ID，如果订单ID长度小于6位则补0，如果大于6为截取后6位
     * @param paySn
     * @param id
     * @return
     */
    private String paySnJoinId(String paySn, Integer id) {
        String idStr = id.toString();
        if (idStr.length() >= 6) {
            String idEnd = idStr.substring(idStr.length() - 6);
            return paySn + idEnd;
        } else {
            String idSix = String.format("%0" + 6 + "d", id);
            return paySn + idSix;
        }
    }

    /**
     * 使用积分校验
     * @param member
     * @param config 系统配置
     * @param integral 使用的积分数量
     */
    private void checkIntegral(Member member, Config config, Integer integral) {
        // 判断用户的积分是否够填入的积分数
        if (integral.compareTo(member.getIntegral()) > 0) {
            throw new BusinessException("积分不够了，请重新填写积分数量！");
        }
        // 至少消耗积分换算比例的积分数量
        if (config == null) {
            throw new BusinessException("积分转换金额失败，请联系系统管理员！");
        }
        if (integral.compareTo(config.getIntegralScale()) < 0) {
            throw new BusinessException("对不起，请至少使用" + config.getIntegralScale() + "个积分！");
        }
    }

    //    /**
    //     * 支付密码、余额量校验
    //     * @param orderCommitVO
    //     * @param member
    //     * @param payAmount 需要支付的金额
    //     */
    //    private void checkBalance(OrderCommitVO orderCommitVO, Member member, BigDecimal payAmount) {
    //        if (StringUtil.isEmpty(orderCommitVO.getBalancePwd())) {
    //            throw new BusinessException("请输入支付密码后重试！");
    //        }
    //        if (!Md5.getMd5String(orderCommitVO.getBalancePwd()).equals(member.getBalancePwd())) {
    //            throw new BusinessException("支付密码不正确，请重试！");
    //        }
    //        // 余额为零直接抛出
    //        if (member.getBalance() == null || member.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
    //            throw new BusinessException("您没有余额，不能使用余额支付！");
    //        }
    //
    //        // 如果订单是货到付款，且余额不足以支付所有的金额则抛出，如果足够支付所有金额，则继续
    //        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())
    //            && payAmount.compareTo(member.getBalance()) > 0) {
    //            throw new BusinessException("余额不足，请重新选择支付方式，或拆分订单后再下单！");
    //        }
    //    }

    /**
     * 订单使用优惠券校验
     * @param orderCouponVO 使用店铺优惠券信息
     * @param memberId 下单用户ID
     * @param cartListVO 订单购物车信息
     * @param channel 渠道
     */
    private void checkSellerCoupon(OrderCouponVO orderCouponVO, Integer memberId,
                                   CartListVO cartListVO, Integer channel) {
        Seller seller = cartListVO.getSeller();
        Integer couponType = orderCouponVO.getCouponType();

        if (couponType != null && couponType > 0) {

            List<CouponUser> couponUserList = couponUserReadDao
                .getCouponUserByCouponSn(orderCouponVO.getCouponSn(), seller.getId());
            if (couponUserList == null || couponUserList.size() == 0) {
                log.error("获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
                throw new BusinessException(
                    "获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
            }
            CouponUser couponUser = couponUserList.get(0);

            if (couponType == OrderCouponVO.COUPON_TYPE_1) {
                // 检查优惠券所属用户
                if (!memberId.equals(couponUser.getMemberId())) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】不是属于您的优惠券，不能使用。");
                }
            } else if (couponType == OrderCouponVO.COUPON_TYPE_2) {
                // 校验密码
                if (couponUser.getPassword() == null || !couponUser.getPassword()
                    .equals(Md5.getMd5String(orderCouponVO.getCouponPassword()))) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】密码不对，请重新输入。");
                }
                // 检查优惠券所属用户
                if (couponUser.getMemberId() > 0 && !couponUser.getMemberId().equals(memberId)) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】不是属于您的优惠券，不能使用。");
                }
            } else {
                throw new BusinessException("优惠券信息错误，请重新输入。");
            }

            // 优惠券可使用次数
            if (couponUser.getCanUse() < 1) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】已使用过，不能再次使用。");
            }

            // 优惠券用户关联的优惠券信息校验
            Coupon coupon = couponReadDao.get(couponUser.getCouponId());
            if (coupon == null) {
                log.error("获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误（couponId="
                          + couponUser.getCouponId() + " ）。");
                throw new BusinessException(
                    "获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
            }

            // 适用最低金额校验
            if (coupon.getMinAmount()
                .compareTo(cartListVO.getSellerCheckedDiscountedAmount()) > 0) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】最低适用订单金额不得小于"
                                            + coupon.getMinAmount() + "元。");
            }
            // 优惠券使用时间校验
            if (coupon.getUseStartTime().after(new Date())) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】还没有到可使用时间。");
            }
            if (coupon.getUseEndTime().before(new Date())) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】已过期。");
            }

            // 使用渠道校验
            if (coupon.getChannel().intValue() != ConstantsEJS.CHANNEL_1
                && coupon.getChannel().intValue() != channel.intValue()) {
                String channelStr = channel.intValue() == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException(
                    "优惠券【" + orderCouponVO.getCouponSn() + "】只能在" + channelStr + "使用。");
            }

            orderCouponVO.setCoupon(coupon);
            orderCouponVO.setCouponUser(couponUser);
        }

    }

    /**
     * 循环判断订单应支付的金额，如果应支付金额小于等于0则修改订单的状态为支付状态<br>
     * 处理例如使用积分足够支付整个订单金额等的特殊情况
     * @param orderList
     * @param member
     * @return 不满足修改状态的条件则返回false，否则返回true
     */
    private boolean checkNeedPay(List<Orders> orderList, Member member) {

        for (Orders order : orderList) {
            BigDecimal payMoney = order.getMoneyOrder().subtract(order.getMoneyIntegral());
            if (payMoney.compareTo(BigDecimal.ZERO) <= 0) {

                // 更新订单付款状态
                Orders newOrder = new Orders();
                newOrder.setId(order.getId());
                newOrder.setOrderState(Orders.ORDER_STATE_3);
                newOrder.setPayTime(new Date());
                newOrder.setPaymentStatus(Orders.PAYMENT_STATUS_1);

                int updateRow = ordersWriteDao.update(newOrder);
                if (updateRow == 0) {
                    log.error("修改订单支付状态时失败。");
                    throw new BusinessException("下单时发生异常，请重试！");
                }
                //支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                // 支付成功，需要修改每个货品的库存
                //                this.updateProductStock(order.getId(), false);

                OrderLog orderLog = new OrderLog();
                orderLog.setContent("您的订单已支付");
                orderLog.setOperatingId(member.getId());
                orderLog.setOrdersId(order.getId());
                orderLog.setOrdersSn(order.getOrderSn());
                orderLog.setOperatingName(member.getName());
                orderLogWriteDao.save(orderLog);

            } else {
                // 如果有一个订单不满这种情况，则整个订单就不会满足这种情况，直接返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 保存商家的订单，以及订单日志<br>
     *
     * @param paySn
     * @param cartListVO
     * @param member
     * @param orderCommitVO
     * @param address
     * @param orderCouponVO 使用的优惠券信息
     * @param actFullLogList
     * @param actSingleLogList
     * @param isWelfare 1:普通订单  2:福利订单
     * @return
     */
    private Orders saveOrderInfo(String paySn, CartListVO cartListVO, Member member,
                                 OrderCommitVO orderCommitVO, MemberAddress address,
                                 OrderCouponVO orderCouponVO, List<CouponOptLog> couponOptLogList,
                                 List<ActFullLog> actFullLogList,Integer isWelfare) {
        Seller seller = cartListVO.getSeller();
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        // 设定支付订单号
        order.setPaySn(paySn);
        // 设为普通订单
        order.setOrderType(Orders.ORDER_TYPE_1);
        order.setOrderSn(orderSn);

        order.setIsWelfareOrder(Orders.ORDER_TYPE_1); //设置为普通订单
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        order.setPaymentName(orderCommitVO.getPaymentName());
        order.setPaymentCode(orderCommitVO.getPaymentCode());

        // 收货地址信息设置
        order.setName(address.getmemberName());
        order.setProvinceId(address.getProvinceId());
        order.setCityId(address.getCityId());
        order.setAreaId(address.getAreaId());
        order.setAddressAll(address.getAddAll());
        order.setAddressInfo(address.getAddressInfo());
        order.setMobile(address.getMobile());
        order.setEmail(address.getEmail());
        order.setZipCode(address.getZipCode());

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        order.setLogisticsId(0);
        order.setLogisticsName("");
        order.setLogisticsNumber("");

        // 是否货到付款订单，根据payMentCode判断
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            // 是否货到付款订单0、不是；1、是
            order.setIsCodconfim(Orders.IS_CODCONFIM_1);
            // 货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
            order.setCodconfirmState(Orders.CODCONFIRM_STATE_1);
        } else {
            order.setIsCodconfim(Orders.IS_CODCONFIM_0);
            order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);
        }
        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        /**
         * 金额的计算
         */

        // 金额信息

        // 商品总额（只是商品价格*数量的金额之和）
        order.setMoneyProduct(cartListVO.getSellerCheckedAmount());
        // 判断物流费用
        order.setMoneyLogistics(cartListVO.getSellerLogisticsFee());
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(new BigDecimal(0));

        // 优惠券优惠金额、优惠券ID（coupon_user的ID）
        // 如果使用了优惠券
        if (orderCouponVO != null && orderCouponVO.getCoupon() != null) {
            order.setMoneyCoupon(orderCouponVO.getCoupon().getCouponValue());
            order.setCouponUserId(orderCouponVO.getCouponUser().getId());
        } else {
            order.setMoneyCoupon(new BigDecimal(0));
            order.setCouponUserId(0);
        }
        // 订单满减金额
        if (cartListVO.getOrderDiscount() != null
            && cartListVO.getOrderDiscount().compareTo(BigDecimal.ZERO) > 0) {
            order.setMoneyActFull(cartListVO.getOrderDiscount());
            order.setActFullId(cartListVO.getActFull().getId());
        } else {
            order.setMoneyActFull(BigDecimal.ZERO);
            order.setActFullId(0);
        }
        // 活动id（与订单类型对应），比如团购时存团购的ID，此处为普通订单，存0
        order.setActivityId(0);
        // 优惠金额总额（满减、立减、优惠券和）
        // 如果从购物车中计算出的优惠总额为空，则赋0，然后加上商家优惠券的额度，计算出所有的优惠总额
        BigDecimal moneyDiscount = cartListVO.getSellerCheckedDiscounted() == null ? BigDecimal.ZERO
            : cartListVO.getSellerCheckedDiscounted();
        // 现在是单品立减、订单满减、商家优惠券三个优惠，以后如果加其他优惠政策则加上新优惠的金额
        order.setMoneyDiscount(moneyDiscount.add(order.getMoneyCoupon()));
        // 新订单退款的金额为0
        order.setMoneyBack(new BigDecimal(0));
        // 订单使用积分金额，所有订单入库后再计算该金额
        order.setMoneyIntegral(BigDecimal.ZERO);
        order.setIntegral(0);
        //专项积分使用数量
        order.setSpecialIntegral(0);
        order.setEvaluateState(Orders.EVALUATE_STATE_1);

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        order.setMoneyOrder(((order.getMoneyProduct().add(order.getMoneyLogistics()))
            .subtract(order.getMoneyDiscount())));

        if (order.getMoneyOrder().compareTo(new BigDecimal(0)) <= 0) {
            // 如果订单金额为0 直接认为已付款
            order.setOrderState(Orders.ORDER_STATE_3);
            order.setPayTime(new Date());
            order.setPaymentStatus(Orders.PAYMENT_STATUS_1);
        } else {
            // 其他情况
            if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
                // 如果是货到付款
                order.setOrderState(Orders.ORDER_STATE_2);
                order.setPaymentStatus(Orders.PAYMENT_STATUS_0);
            } else {
                // 如果不是货到付款
                order.setOrderState(Orders.ORDER_STATE_1);
                order.setPaymentStatus(Orders.PAYMENT_STATUS_0);
            }
        }

        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        if (orderCouponVO != null && orderCouponVO.getCoupon() != null) {
            CouponUser couponUser = orderCouponVO.getCouponUser();
            // 设定优惠券使用日志
            CouponOptLog couponOptLog = new CouponOptLog();
            couponOptLog.setCouponUserId(couponUser.getId());
            couponOptLog.setMemberId(member.getId());
            couponOptLog.setSellerId(seller.getId());
            couponOptLog.setCouponId(orderCouponVO.getCoupon().getId());
            couponOptLog.setOptType(CouponOptLog.OPT_TYPE_2);
            couponOptLog.setOrderId(order.getId());
            couponOptLog.setCreateUserId(member.getId());
            couponOptLog.setCreateUserName(member.getName());
            couponOptLog.setCreateTime(new Date());
            couponOptLogList.add(couponOptLog);
        }

        // 订单满减金额
        if (cartListVO.getOrderDiscount() != null
            && cartListVO.getOrderDiscount().compareTo(BigDecimal.ZERO) > 0) {
            // 设定订单满减参与日志
            ActFullLog actFullLog = new ActFullLog();
            actFullLog.setActFullId(cartListVO.getActFull().getId());
            actFullLog.setMemberId(member.getId());
            actFullLog.setSellerId(seller.getId());
            actFullLog.setOrderId(order.getId());
            actFullLog.setCreateUserId(member.getId());
            actFullLog.setCreateUserName(member.getName());
            actFullLog.setCreateTime(new Date());
            actFullLogList.add(actFullLog);
        }

        return order;
    }

    /**
     * 保存网单信息
     * @param order
     * @param cartList
     */
    private void saveOrderProductInfo(Orders order, List<Cart> cartList, Member member,
                                      List<ActSingleLog> actSingleLogList) {
        for (Cart cart : cartList) {
            ProductGoods goods = cart.getProductGoods();
            Product product = cart.getProduct();
            // 判断商品状态
            if (product.getState().intValue() != Product.STATE_6) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断分类状态
            if (product.getProductCateState().intValue() != Product.PRODUCT_CATE_STATE_1) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断库存
            if (product.getSource() == 1 && (goods.getProductStock() <= 0 || goods.getProductStock() < cart.getCount())) {
                throw new BusinessException(
                    "商品[" + product.getName1() + " " + cart.getSpecInfo() + "]的库存不足，请重新选择商品！");
            }
            // 货品状态异常或未启用的，不能下单
            if (goods.getState() == null || goods.getState().intValue() == ProductGoods.DISABLE) {
                String productName = product.getName1();
                if (!StringUtil.isEmpty(goods.getNormName(), true)) {
                    productName += " " + goods.getNormName();
                }
                throw new BusinessException("商品[" + productName + "]已下架，不能下单！");
            }

            //保存网单信息
            OrdersProduct op = new OrdersProduct();
            op.setOrdersId(order.getId());
            op.setOrdersSn(order.getOrderSn());
            op.setSellerId(cart.getSellerId());
            op.setProductCateId(product.getProductCateId());
            op.setProductId(product.getId());
            op.setProductGoodsId(cart.getProductGoodsId());
            op.setSpecInfo(cart.getSpecInfo());
            op.setProductName(product.getName1());
            op.setProductSku(goods.getSku());
            op.setPackageGroupsId(0);
            op.setMallGroupsId(0);
            op.setGiftId(0);
            op.setIsGift(OrdersProduct.IS_GIFT_0);
            // 根据来源确定使用PC价格或者移动端价格，默认使用PC端价格
            BigDecimal price = goods.getMallPcPrice();
            if (order.getSource() != null
                && (order.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                    || order.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                    || order.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
                price = goods.getMallMobilePrice();
            }
            op.setMoneyPrice(price);
            op.setNumber(cart.getCount());
            // 网单金额（减去立减优惠后的金额和）
            op.setMoneyAmount(cart.getCurrDiscountedAmount());
            // 立减优惠金额和
            if (cart.getCurrDiscounted() != null
                && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                op.setMoneyActSingle(cart.getCurrDiscounted());
                op.setActSingleId(cart.getActSingle().getId());
            } else {
                op.setMoneyActSingle(BigDecimal.ZERO);
                op.setActSingleId(0);
            }
            op.setActGroupId(0);
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(0);
            op.setActIntegralId(0);
            op.setActSpecialIntegral(0);
            op.setActIntegralNum(0);
            op.setBackNumber(0);
            op.setExchangeNumber(0);
            op.setIsEvaluate(OrdersProduct.IS_EVALUATE_0);

            // 1、保存网单
            int count = ordersProductWriteDao.insert(op);
            if (count == 0) {
                throw new BusinessException("网单保存失败，请重试！");
            }
            //支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 更新商品的销量
            //            this.updateProductActualSales(cart.getProductId(), cart.getProductGoodsId(),
            //                cart.getCount());
            // 修改库存和销量
            this.updateProductActualSalesAndStock(cart.getProductId(), cart.getProductGoodsId(),
                cart.getCount(), false);

            // 设定网单的立减活动参与日志
            if (cart.getCurrDiscounted() != null
                && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                ActSingleLog actSingleLog = new ActSingleLog();
                actSingleLog.setActSingleId(cart.getActSingle().getId());
                actSingleLog.setMemberId(member.getId());
                actSingleLog.setSellerId(cart.getSellerId());
                actSingleLog.setOrderId(order.getId());
                actSingleLog.setOrderProductId(op.getId());
                actSingleLog.setProductId(product.getId());
                actSingleLog.setCreateUserId(member.getId());
                actSingleLog.setCreateUserName(member.getName());
                actSingleLog.setCreateTime(new Date());
                actSingleLogList.add(actSingleLog);
            }
        }
    }

    //    /**
    //     * 根据商品ID和货品ID更新商品货品的实际销量
    //     * @param productId
    //     * @param productGoodsId
    //     * @param number
    //     */
    //    private void updateProductActualSales(Integer productId, Integer productGoodsId,
    //                                          Integer number) {
    //        // 商品销量 累加
    //        int pcount = productWriteDao.updateActualSales(productId, number);
    //        if (pcount == 0) {
    //            log.error("商品实际销量更新失败。");
    //            throw new BusinessException("商品销量更新失败！");
    //        }
    //        pcount = productGoodsWriteDao.updateActualSales(productGoodsId, number);
    //        if (pcount == 0) {
    //            log.error("货品实际销量更新失败。");
    //            throw new BusinessException("商品销量更新失败！");
    //        }
    //    }

    /**
     * 确认收货
     * @param Member member
     * @param ordersId
     * @return
     * @throws Exception
     */
    public boolean goodsReceipt(Member member, Integer ordersId) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            //参数校验
            if (ordersId == null || ordersId == 0) {
                log.error("订单ID为空。");
                throw new BusinessException("订单ID为空，请重试！");
            }

            //获取订单
            Orders orders = ordersWriteDao.get(ordersId);
            if (orders == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请重试！");
            } else if (!orders.getOrderState().equals(Orders.ORDER_STATE_4)) {
                log.error("订单不处于已发货状态，不能确认收货。");
                throw new BusinessException("订单不处于已发货状态，不能确认收货！");
            }

            orders.setOrderState(Orders.ORDER_STATE_5);
            orders.setFinishTime(new Date());
            int count = ordersWriteDao.update(orders);
            if (count == 0) {
                log.error("订单更新失败。");
                throw new BusinessException("订单更新失败！");
            }

            OrderLog orderLog = new OrderLog(member.getId(), member.getName(), orders.getId(),
                orders.getOrderSn(), "您已签收订单", new Date());

            int logCount = orderLogWriteDao.save(orderLog);
            if (logCount == 0) {
                throw new BusinessException("订单日志保存失败，请重试！");
            }

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("time",orders.getPayTime());
            map.put("orderSn", orders.getOrderSn());

            Boolean flag = messageModel.sendMessageToMember(map, orders.getMemberId(), Message.DDQSTZ);
            if(!flag) {
            	throw new BusinessException("推送消息到用户失败。");
            }

            transactionManager.commit(status);

            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:", e);
            throw e;
        }
    }

    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    public List<OrderDayDto> getOrderDayDto(Map<String, String> queryMap) {
        return ordersReadDao.getOrderDayDto(queryMap);
    }

    public boolean jobSystemFinishOrder() {

        // 获取当前时间15天之前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -15);

        String deliverTime = TimeUtil.getDateTimeString(calendar.getTime());

        //获取发货时间超过15天的订单
        List<Orders> ordersList = ordersReadDao.getUnfinishedOrders(deliverTime);

        if (ordersList != null && ordersList.size() > 0) {
            // 单条数据处理异常不影响其他数据执行
            for (Orders orders : ordersList) {
                // 事务管理
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                TransactionStatus status = transactionManager.getTransaction(def);
                try {
                    Orders orderNew = new Orders();
                    orderNew.setId(orders.getId());
                    orderNew.setOrderState(Orders.ORDER_STATE_5);
                    orderNew.setFinishTime(new Date());

                    Integer update = ordersWriteDao.update(orderNew);
                    if (update == 0) {
                        throw new BusinessException("系统自动完成订单时失败。");
                    }

                    OrderLog log = new OrderLog(0, "system", orders.getId(), orders.getOrderSn(),
                        "系统自动完成订单", new Date());

                    int orderlogCount = orderLogWriteDao.save(log);
                    if (orderlogCount == 0) {
                        throw new BusinessException("系统自动完成订单，订单日志保存失败，请重试！");
                    }

                    transactionManager.commit(status);
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    log.error("[OrderModel][jobSystemFinishOrder]系统自动完成订单时发生异常:", e);
                    log.error(
                        "[OrderModel][jobSystemFinishOrder]发生异常的订单：" + JSON.toJSONString(orders));
                }
            }
        }

        return true;
    }

    /**
     * 系统自动取消24小时没有付款订单
     * @return
     */
    public boolean jobSystemCancelOrder() {

        // 获取当前时间1天之前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        String cancelTime = TimeUtil.getDateTimeString(calendar.getTime());

        // 获取下单24小时还未付款的订单
        List<Orders> ordersList = ordersReadDao.getUnPaiedOrders(cancelTime);

        if (ordersList != null && ordersList.size() > 0) {
            // 单条数据处理异常不影响其他数据执行
            for (Orders orders : ordersList) {
                if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_4
                    || orders.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
                    // 竞价定金订单和竞价尾款订单都不自动取消
                    continue;
                }
                // 事务管理
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                TransactionStatus status = transactionManager.getTransaction(def);
                try {
                    Orders orderNew = new Orders();
                    orderNew.setId(orders.getId());
                    orderNew.setFinishTime(new Date());
                    orderNew.setOrderState(Orders.ORDER_STATE_6);

                    Integer update = ordersWriteDao.update(orderNew);
                    if (update == 0) {
                        throw new BusinessException("系统自动取消订单时失败。");
                    }

                    OrderLog log = new OrderLog(0, "system", orders.getId(), orders.getOrderSn(),
                            "系统自动取消订单", new Date());

                    int orderlogCount = orderLogWriteDao.save(log);
                    if (orderlogCount == 0) {
                        throw new BusinessException("系统自动取消订单，订单日志保存失败，请重试！");
                    }

                    // 返还积分
                    this.cancelOrderBackIntegral(orders);
                    // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                    //                    // 普通订单需要还原销量，限时抢购、团购订单、集合竞价的销量在还原库存时一起还原（因为增加销量和减库存是同时进行的）
                    //                    if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
                    //                        // 还原销量
                    //                        this.restoreActualSales(ordersProductReadDao.getByOrderId(orders.getId()));
                    //                    }

                    // 还原库存和实际销量
                    this.cancelOrderBackStockAndActualSales(orders.getOrderType(), orders.getId());

                    // 返回优惠券
                    this.cancelOrderBackCoupon(orders, 0, "system");

                    // 退回付款金额
                    this.cancelOrderBackMoney(orders, 0, "system");

                    //订单已付款 订单取消推送消息 未付款 不推送
                    if(orders.getPaymentStatus() == Orders.PAYMENT_STATUS_1) {
                    	Map<String,Object> map = new HashMap<String,Object>();
                        java.util.Calendar c=java.util.Calendar.getInstance();
                        java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    	map.put("startTime", orders.getPayTime());
                        map.put("endTime",f.format(c.getTime()));
                        map.put("orderSn", orders.getOrderSn());
                        //推送消息给用户
                        Boolean flag = messageModel.sendMessageToMember(map, orders.getMemberId(), Message.DDQXTZ);
                        if(!flag) {
                        	throw new BusinessException("发送消息到用户失败");
                        }
                    }


                    transactionManager.commit(status);
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    log.error("[OrderModel][jobSystemCancelOrder]系统自动取消订单时发生异常:", e);
                    log.error(
                        "[OrderModel][jobSystemCancelOrder]发生异常的订单：" + JSON.toJSONString(orders));
                }
            }
        }

        return true;
    }

    /**
     * 根据支付订单号查询订单信息，计算支付的金额等
     * @param paySn
     * @param memberId
     * @return
     */
    public OrderSuccessVO getOrdersByPaySn(String paySn, Integer memberId) {
        OrderSuccessVO orderSuccVO = new OrderSuccessVO();
        //参数校验
        if (StringUtil.isEmpty(paySn)) {
            log.error("支付订单编号为空。");
            throw new BusinessException("支付订单编号为空，请重试！");
        }

        // 订单总价之和
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 积分使用和
        BigDecimal totalIntegral = BigDecimal.ZERO;
        // 余额使用和
        BigDecimal totalBalance = BigDecimal.ZERO;
        // 现金使用和
        BigDecimal totalReality = BigDecimal.ZERO;

        // 被支付的订单orderSn
        String payForOrderSn = "";

        // 此处必须从写库读取数据，因为如果网络环境很差的时候读写库同步会延迟
        List<Orders> orderList = ordersWriteDao.getByPaySn(paySn);
        if (orderList != null && orderList.size() > 0) {
            int count = 0;
            String productName = "";
            for (int i = 0; i < orderList.size(); i++) {
                Orders order = orderList.get(i);
                if (order != null) {
                    if (!order.getMemberId().equals(memberId)) {
                        throw new BusinessException("该订单不是您的订单，您不能操作，谢谢！");
                    }
                    totalPrice = totalPrice.add(order.getMoneyOrder());//计算总金额
                    totalIntegral = totalIntegral.add(order.getMoneyIntegral());
                    totalBalance = totalBalance.add(order.getMoneyPaidBalance());
                    totalReality = totalReality.add(order.getMoneyPaidReality());

                    List<OrdersProduct> prolist = ordersProductWriteDao.getByOrderId(order.getId());
                    if (prolist.size() > 0) {
                        count += prolist.size();
                        productName = prolist.get(0).getProductName();
                    }
                    if (i > 0) {
                        // 从第二个订单开始才加逗号
                        payForOrderSn += ",";
                    }
                    payForOrderSn += order.getOrderSn();
                }
            }
            if (count > 1) {
                orderSuccVO.setProductName(productName + "等" + count + "个商品");
            } else {
                orderSuccVO.setProductName(productName);
            }
        } else {
            throw new BusinessException("订单信息获取失败，请重试！");
        }

        BigDecimal payMount = totalPrice.subtract(totalIntegral).subtract(totalBalance)
            .subtract(totalReality);
        payMount = payMount.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payMount;
        orderSuccVO.setPayAmount(payMount);
        orderSuccVO.setOrdersList(orderList);
        orderSuccVO.setPaySn(paySn);
        orderSuccVO.setPayForOrderSn(payForOrderSn);

        return orderSuccVO;
    }

    /**
     * 根据订单号查询订单信息，计算支付的金额等
     * @param orderSn
     * @param memberId
     * @return
     */
    public OrderSuccessVO getOrdersByOrderSn(String orderSn, Integer memberId) {
        OrderSuccessVO orderSuccVO = new OrderSuccessVO();
        //参数校验
        if (StringUtil.isEmpty(orderSn)) {
            log.error("订单编号为空。");
            throw new BusinessException("订单编号为空，请重试！");
        }

        Orders order = ordersWriteDao.getByOrderSn(orderSn);
        if (order != null) {
            if (!order.getMemberId().equals(memberId)) {
                throw new BusinessException("该订单不是您的订单，您不能操作，谢谢！");
            }

            List<OrdersProduct> prolist = ordersProductWriteDao.getByOrderId(order.getId());
            if (prolist.size() > 1) {
                orderSuccVO
                    .setProductName(prolist.get(0).getProductName() + "等" + prolist.size() + "个商品");
            } else {
                orderSuccVO.setProductName(prolist.get(0).getProductName());
            }

        } else {
            throw new BusinessException("订单信息获取失败，请重试！");
        }
        List<Orders> orderList = new ArrayList<Orders>();
        orderList.add(order);

        BigDecimal payMount = BigDecimal.ZERO;
        if(order.getIsWelfareOrder() == Product.IS_WELFARE_2) {
        	payMount = order.getMoneyOrder();
        }else {
        	payMount = (order.getMoneyOrder()).subtract(order.getMoneyIntegral())
            .subtract(order.getMoneyPaidBalance()).subtract(order.getMoneyPaidReality());
        }
        payMount = payMount.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payMount;

        // 重新计算支付的专项积分和通用积分
        if(order.getIsWelfareOrder() == Product.IS_WELFARE_2) {
        	Member member = memberWriteDao.get(memberId);
            Integer specialIntegral =
            getMemberSpecialIntegralByMemberId(memberId, order.getSellerId());

            orderSuccVO.setSpecialIntegralPay(specialIntegral);

        	if(specialIntegral >= order.getMoneyOrder().intValue()) {
        		orderSuccVO.setSpecialIntegralPay(order.getMoneyOrder().intValue());
        	}
        }
        orderSuccVO.setPayAmount(payMount);
        orderSuccVO.setOrdersList(orderList);
        orderSuccVO.setPaySn(orderSn);
        orderSuccVO.setPayForOrderSn(orderSn);

        return orderSuccVO;
    }
    /**
     * 根据用户Id和商家id获取 专项积分
     * @param memberId
     * @param sellerId
     * @return
     */
    private Integer getMemberSpecialIntegralByMemberId(Integer memberId,Integer sellerId) {
        Integer specialIntegral = 0;
        Map<String,Object>map=new HashMap<>();
        map.put("q_memberId",memberId);
        map.put("q_sellerId",sellerId);
        List<MemberSpecialIntegral> msiList = memberSpecialIntegralReadDao.page(map,null, null);
        if(msiList!=null && msiList.size()>0){
            for(MemberSpecialIntegral msi : msiList) {
                specialIntegral += msi.getValue();
            }
        }
        return specialIntegral;
    }

    /**
     * 订单支付前处理<br>
     * <li>支付状态校验等
     * <li>校验库存
     * @param orderId
     * @param orderSn
     */
    private void payBefore(List<Orders> ordersList) {

        for (Orders orders : ordersList) {
            // 校验
            //只有订单状态为 1（未付款的订单）   买家付款状态 payment_status 为未付款，才能支付
            Integer orderState = orders.getOrderState();//订单状态
            Integer paymentStatus = orders.getPaymentStatus();//买家支付状态
            String paymentCode = orders.getPaymentCode();//支付类型

            if (orderState.intValue() != Orders.ORDER_STATE_1 && paymentStatus != Orders.PAYMENT_STATUS_0) {
                log.error("订单" + orders.getOrderSn() + "不是未付款状态，请选择未支付的订单！");
                throw new BusinessException("订单" + orders.getOrderSn() + "不是未付款状态，请选择未支付的订单！");
            }

            if (paymentStatus.intValue() != Orders.PAYMENT_STATUS_0) {
                log.error("订单" + orders.getOrderSn() + "已经支付过了，请选择未支付的订单！");
                throw new BusinessException("订单" + orders.getOrderSn() + "已经支付过了，请选择未支付的订单！");
            }

            if (!paymentCode.equals(Orders.PAYMENT_CODE_ONLINE) && orders.getIsWelfareOrder() != Product.IS_WELFARE_2) {
                log.error("订单" + orders.getOrderSn() + "不是在线支付订单，不能进行在线支付！");
                throw new BusinessException("订单" + orders.getOrderSn() + "不是在线支付订单，不能进行在线支付！");
            }

            // 支付前校验库存，从写库读网单防止由于网络原因导致的延迟
            List<OrdersProduct> opList = ordersProductWriteDao.getByOrderId(orders.getId());

            if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
                // 限时抢购订单校验活动商品的库存

                // 限时抢购只有一个网单，校验库存从写库读取数据
                ActFlashSaleProduct actFlashSaleProduct = actFlashSaleProductWriteDao
                    .get(opList.get(0).getActFlashSaleProductId());
                // 校验阶段是否已经过了时间点
                ActFlashSaleStage actFlashSaleStage = actFlashSaleStageReadDao
                    .get(actFlashSaleProduct.getActFlashSaleStageId());
                // 获取当前时间点
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour >= actFlashSaleStage.getEndTime()) {
                    throw new BusinessException("对不起，您参加的秒杀活动已结束，不能支付了！");
                }
                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                if (actFlashSaleProduct.getStock() < opList.get(0).getNumber()) {
                //                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                //                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_3) {
                // 团购订单校验活动商品的库存

                // 团购只有一个网单，校验库存从写库读取数据
                ActGroup actGroup = actGroupWriteDao.get(opList.get(0).getActGroupId());
                // 获取当前时间
                Date date = new Date();
                if (date.after(actGroup.getEndTime())) {
                    throw new BusinessException("对不起，您参加的团购活动已结束，不能支付了！");
                }
                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                if (actGroup.getStock() < opList.get(0).getNumber()) {
                //                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                //                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_4) {
                // 竞价定金订单校验活动商品的库存

                // 竞价定金订单只有一个网单，校验库存从写库读取数据
                ActBidding actBidding = actBiddingWriteDao.get(opList.get(0).getActBiddingId());
                // 获取当前时间
                Date date = new Date();
                if (date.after(actBidding.getFirstEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是定金订单，定金付款时间已结束！");
                    throw new BusinessException(
                        "订单" + orders.getOrderSn() + "是定金订单，定金付款时间已结束不能再支付！");
                }
                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                if (actBidding.getStock() < opList.get(0).getNumber()) {
                //                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                //                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
                // 竞价尾款订单校验活动商品的库存

                // 竞价尾款订单只有一个网单
                ActBidding actBidding = actBiddingWriteDao.get(opList.get(0).getActBiddingId());
                // 获取当前时间
                Date date = new Date();
                if (date.before(actBidding.getFirstEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是尾款订单，付款时间还没有开始！");
                    throw new BusinessException("订单" + orders.getOrderSn() + "是尾款订单，付款时间还没有开始！");
                }
                if (date.after(actBidding.getLastEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是尾款订单，付款时间已结束！");
                    throw new BusinessException("订单" + orders.getOrderSn() + "是尾款订单，付款时间已结束不能再支付！");
                }
                // 尾款订单不扣减库存，所以不校验库存量
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_6) {
                // 积分换购订单校验活动商品的库存
                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                // 积分换购正常不会走支付的逻辑（下单时直接用积分支付），为代码完整、易读加此逻辑
                //                // 积分换购只有一个网单，校验库存从写库读取数据
                //                ActIntegral actIntegral = actIntegralWriteDao.get(opList.get(0).getActIntegralId());
                //                if (actIntegral.getStock() < opList.get(0).getNumber()) {
                //                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                //                }
            } else {
                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                // 普通订单校验每个网单的库存
                //                for (OrdersProduct op : opList) {
                //                    ProductGoods productGoods = productGoodsWriteDao.get(op.getProductGoodsId());
                //                    if (productGoods.getProductStock() < op.getNumber()) {
                //                        throw new BusinessException("商品【" + opList.get(0).getProductName() + " "
                //                                                    + opList.get(0).getSpecInfo() + "】库存不足！");
                //                    }
                //                }
            }
        }

    }

    /**
     * 订单支付后处理<br>
     * <li>修改订单支付状态
     * <li>记录订单日志
     * <li>记录订单支付日志
     *
     * @param ordersList
     * @param paidMoney 现金支付的金额
     * @param member
     * @param paySn 支付订单号
     * @param tradeSn 支付流水号
     * @param tradeContent 支付返回信息
     * @param paymentCode 支付方式code
     * @param paymentName 支付方式名称
     * @param isPaidBefore 是否是支付前调用（余额支付所有订单时会调用）
     */
    private void payAfter(List<Orders> ordersList, BigDecimal paidMoney, Member member,
                          String paySn, String tradeSn, String tradeContent, String paymentCode,
                          String paymentName, boolean isPaidBefore) {

        // 订单号集合
        String payOrderSn = "";
        BigDecimal allPaidMoney = paidMoney;
        for (int i = 0; i < ordersList.size(); i++) {
            Orders ordersDb = ordersList.get(i);

            if (i > 0) {
                // 从第二个订单开始先加逗号再加订单号
                payOrderSn += ",";
            }
            payOrderSn += ordersDb.getOrderSn();

            // 修改订单支付状态
            Orders orders = new Orders();
            orders.setId(ordersDb.getId());

            // 计算余额账户支付总金额、现金支付金额
            // 订单应该支付的金额：订单金额-积分换算金额-余额支付金额-现金支付金额
            BigDecimal payMoney = ordersDb.getMoneyOrder().subtract(ordersDb.getMoneyIntegral())
                .subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyPaidReality());
            // 订单实际分配的支付金额
            BigDecimal actualPaid = BigDecimal.ZERO;
            if (payMoney.compareTo(BigDecimal.ZERO) <= 0) {
                // 如果应付金额小于等于0
                // 不计算金额
            } else {
                // 订单循环分配现金支付的金额
                if ((i + 1) == ordersList.size()) {
                    // 如果是最后一个订单，则记录剩下的金额
                    orders.setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(paidMoney));
                    actualPaid = paidMoney;
                } else {
                    if (paidMoney.compareTo(BigDecimal.ZERO) > 0) {
                        if (paidMoney.compareTo(payMoney) >= 0) {
                            // 支付的金额大于等于订单待支付金额
                            orders
                                .setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(payMoney));
                            actualPaid = payMoney;
                        } else {
                            orders
                                .setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(paidMoney));
                            actualPaid = paidMoney;
                        }
                    }
                }
                // 现金支付金额减少
                paidMoney = paidMoney.subtract(actualPaid);
            }

            // 修改金额后应支付金额，如果该金额小于等于0，则支付完成，修改状态，记录支付日志，占库存
            // DB订单金额-DB积分支付金额-DB余额支付金额-NEW现金支付金额
            BigDecimal needPay = ordersDb.getMoneyOrder().subtract(ordersDb.getMoneyIntegral())
                .subtract(ordersDb.getMoneyPaidBalance())
                .subtract(ordersDb.getMoneyPaidReality().add(actualPaid));
            if (needPay.compareTo(BigDecimal.ZERO) <= 0) {
                // 支付完成
                if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_4) {
                    // 如果是竞价定金订单，支付完成即设置订单状态为已完成（因为不需要发货）
                    orders.setOrderState(Orders.ORDER_STATE_5);
                } else {
                    orders.setOrderState(Orders.ORDER_STATE_3);
                }

                //如果是先发货的订单 则直接修改状态为 已发货
                if(ordersDb.getPaymentStatus() == Orders.PAYMENT_STATUS_0 && ordersDb.getOrderState() == Orders.ORDER_STATE_4) {
                	orders.setOrderState(Orders.ORDER_STATE_4);
                }

                orders.setPayTime(new Date());
                orders.setPaymentStatus(Orders.PAYMENT_STATUS_1);
                orders.setPaymentCode(paymentCode);
                orders.setPaymentName(paymentName);
                orders.setTradeSn(tradeSn);

                Integer update = ordersWriteDao.update(orders);
                if (update == 0) {
                    throw new BusinessException("修改订单状态时失败！");
                }

                // 记录订单日志
                OrderLog orderLog = new OrderLog(member.getId(), member.getName(), ordersDb.getId(),
                    ordersDb.getOrderSn(), "订单完成支付", new Date());
                Integer save = orderLogWriteDao.save(orderLog);
                if (save == 0) {
                    throw new BusinessException("记录订单日志时失败！");
                }

                // 支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
                //                // 占库存
                //                if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
                //                    // 限时抢购订单的情况占限时抢购活动商品的库存
                //                    List<OrdersProduct> opList = ordersProductWriteDao
                //                        .getByOrderId(ordersDb.getId());
                //                    // 限时抢购只有一个网单
                //                    this.updateFlashProductStockAndActualSales(
                //                        opList.get(0).getActFlashSaleProductId(), opList.get(0).getNumber());
                //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_3) {
                //                    // 团购订单的情况占团购活动的库存
                //                    List<OrdersProduct> opList = ordersProductWriteDao
                //                        .getByOrderId(ordersDb.getId());
                //                    // 团购只有一个网单
                //                    this.updateGroupStockAndActualSales(opList.get(0).getActGroupId(),
                //                        opList.get(0).getNumber());
                //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_4) {
                //                    // 竞价定金订单的情况占竞价活动的库存
                //                    List<OrdersProduct> opList = ordersProductWriteDao
                //                        .getByOrderId(ordersDb.getId());
                //                    // 竞价定金订单只有一个网单
                //                    this.updateBiddingStockAndActualSales(opList.get(0).getActBiddingId(),
                //                        opList.get(0).getNumber());
                //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_5) {
                //                    // 竞价尾款订单的情况不占库存，因为库存在定金订单支付时已占
                //                    // 此处使逻辑清晰代码易读增加此判断
                //                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_6) {
                //                    // 积分换购订单的情况占积分换购活动的库存
                //                    // 积分换购正常不会走支付的逻辑（下单时直接用积分支付），为代码完整、易读加此逻辑
                //                    List<OrdersProduct> opList = ordersProductWriteDao
                //                        .getByOrderId(ordersDb.getId());
                //                    // 积分换购只有一个网单
                //                    this.updateIntegralStockAndActualSales(opList.get(0).getActIntegralId(),
                //                        opList.get(0).getNumber());
                //                } else {
                //                    this.updateProductStockForPayAfter(ordersDb.getId());
                //                }

                // orderPayBefore中调用时不记录日志，因为在扣除余额时已经记录过了
                // 只有在payAfter中调用且实际支付金额大于0时才记录此日志
                if (!isPaidBefore && (actualPaid.compareTo(BigDecimal.ZERO) > 0)) {
                    // 记录订单支付日志
                    OrderPayLog payLog = new OrderPayLog();
                    payLog.setOrdersId(ordersDb.getId());
                    payLog.setOrdersSn(ordersDb.getOrderSn());
                    payLog.setPaymentCode(paymentCode);
                    payLog.setPaymentName(paymentName);
                    payLog.setPayMoney(actualPaid);
                    payLog.setPayIntegral(0);
                    payLog.setPaySn(paySn);
                    payLog.setTradeSn(tradeSn);
                    payLog.setMemberId(member.getId());
                    payLog.setMemberName(member.getName());
                    payLog.setCreateTime(new Date());
                    orderPayLogWriteDao.insert(payLog);
                }

                // 购物送积分，出错时吞掉异常不影响支付
                /*try {
                    memberModel.memberOrderSendValueNoTrans(member.getId(), member.getName(),
                        ordersDb.getId());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }*/
            } else {
                // 订单支付的总金额不够订单金额，表示订单未支付完成，商城现有逻辑正常不会进入此逻辑
                // 记录部分支付信息
                orders.setTradeSn(tradeSn);
                Integer update = ordersWriteDao.update(orders);
                if (update == 0) {
                    throw new BusinessException("修改订单时失败！");
                }

                // 记录订单日志
                OrderLog orderLog = new OrderLog(member.getId(), member.getName(), ordersDb.getId(),
                    ordersDb.getOrderSn(), "订单部分支付", new Date());
                Integer save = orderLogWriteDao.save(orderLog);
                if (save == 0) {
                    throw new BusinessException("记录订单日志时失败！");
                }

                // orderPayBefore中调用时不记录日志，因为在扣除余额时已经记录过了
                // 只有在payAfter中调用且实际支付金额大于0时才记录此日志
                if (!isPaidBefore && (actualPaid.compareTo(BigDecimal.ZERO) > 0)) {
                    // 记录订单支付日志
                    OrderPayLog payLog = new OrderPayLog();
                    payLog.setOrdersId(ordersDb.getId());
                    payLog.setOrdersSn(ordersDb.getOrderSn());
                    payLog.setPaymentName(paymentName);
                	payLog.setPayMoney(actualPaid);
                    payLog.setPayIntegral(0);
                    payLog.setPaymentCode(paymentCode);
                    payLog.setPaySn(paySn);
                    payLog.setTradeSn(tradeSn);
                    payLog.setMemberId(member.getId());
                    payLog.setMemberName(member.getName());
                    payLog.setCreateTime(new Date());
                    orderPayLogWriteDao.insert(payLog);
                }
            }
        }

        // 只在payAfter中调用时才记录此日志，因为payBefore中调用此方法时不涉及第三方支付
        if (!isPaidBefore) {
            // 记录第三方支付的支付日志
            OrderPayCashLog cashLog = new OrderPayCashLog();
            cashLog.setPaySn(paySn);
            cashLog.setTradeSn(tradeSn);
            cashLog.setPaymentCode(paymentCode);
            cashLog.setPaymentName(paymentName);
            cashLog.setPayMoney(allPaidMoney);
            cashLog.setPayOrderSn(payOrderSn);
            cashLog.setPayContent(tradeContent);
            cashLog.setMemberId(member.getId());
            cashLog.setMemberName(member.getName());
            cashLog.setCreateTime(new Date());
            orderPayCashLogWriteDao.insert(cashLog);
        }
    }

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param isPaySn 是否是支付订单号，true则paySn是支付订单号，false则paySn为订单号
     * @param paySn 支付订单号或者订单号（根据isPaySn决定）
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    public OrderSuccessVO orderPayBefore(boolean isPaySn, String paySn, boolean isBalancePay,
                                         String balancePassword, Member member) {
        OrderSuccessVO orderSuccessVO;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (StringUtil.isEmpty(paySn, true)) {
                log.error("订单号为空。");
                throw new BusinessException("订单号不能为空，请重试！");
            }

            // 如果不是支付订单号
            if (!isPaySn) {
                // 先修改订单的支付订单号为订单号
                Integer updatePaySn = ordersWriteDao.updatePaySn(paySn);
                if (updatePaySn == 0) {
                    log.error("修改订单支付订单号失败。orderSn=" + paySn);
                    throw new BusinessException("修改订单支付订单号失败，请稍后再试！");
                }
                // 然后根据订单号获取订单信息
                orderSuccessVO = this.getOrdersByOrderSn(paySn, member.getId());
            } else {
                // 根据支付订单号获取订单信息
                orderSuccessVO = this.getOrdersByPaySn(paySn, member.getId());
            }

            List<Orders> ordersList = orderSuccessVO.getOrdersList();

            if (ordersList == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请重试！");
            }
            // 支付前验证操作
            this.payBefore(ordersList);

            //计算订单总金额
            BigDecimal orderMoneyAlls = orderMoneyAlls(ordersList);
            orderSuccessVO.setPayOrderAllsVO(orderMoneyAlls);
            Member memberNew = memberWriteDao.get(member.getId());
            if (orderMoneyAlls.compareTo(BigDecimal.ZERO) <= 0) {
                // 如果支付金额为0，则直接更改订单状态（主要针对使用余额后支付中断的情况）
                this.payAfter(ordersList, BigDecimal.ZERO, memberNew, "", "", "",
                    Orders.PAYMENT_CODE_BALANCE, Orders.PAYMENT_NAME_BALANCE, true);
                orderSuccessVO.setBanlancePayMoneyVO(orderMoneyAlls);
                orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_2);
            } else {
                if (isBalancePay) {//余额支付
                    if (!memberNew.getBalancePwd().equals(Md5.getMd5String(balancePassword))) {
                        throw new BusinessException("支付密码错误，请重新输入");
                    }
                    // 使用余额，先扣除余额，防止用户并发支付时余额被多次使用
                    // 用户的账户余额
                    BigDecimal balance = memberNew.getBalance();
                    // 订单使用的余额总计（用于修改用户账户余额）
                    BigDecimal paidMoney = BigDecimal.ZERO;
                    for (Orders orders : ordersList) {
                        // 如果余额大于0，继续扣减
                        if (balance.compareTo(BigDecimal.ZERO) > 0) {
                            // 订单应支付金额
                            BigDecimal needPay = orders.getMoneyOrder()
                                .subtract(orders.getMoneyIntegral())
                                .subtract(orders.getMoneyPaidBalance())
                                .subtract(orders.getMoneyPaidReality());
                            needPay = needPay.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO
                                : needPay;
                            // 本次余额实际能支付的金额
                            BigDecimal actualPaid = BigDecimal.ZERO;

                            // 修改订单余额支付金额
                            Orders newOrder = new Orders();
                            newOrder.setId(orders.getId());
                            if (balance.compareTo(needPay) >= 0) {
                                // 如果余额大于等于应付金额
                                actualPaid = needPay;
                            } else {
                                // 如果余额小于应付金额
                                actualPaid = balance;
                            }
                            // 余额支付金额累加
                            newOrder
                                .setMoneyPaidBalance(orders.getMoneyPaidBalance().add(actualPaid));
                            // 把新的余额支付金额赋值给list中的订单对象，因为有可能调用payAfter方法，需要使用该值
                            orders
                                .setMoneyPaidBalance(orders.getMoneyPaidBalance().add(actualPaid));
                            // 余额累减
                            balance = balance.subtract(actualPaid);
                            // 已支付金额累加
                            paidMoney = paidMoney.add(actualPaid);

                            // 实际支付金额大于0才记录日志及修改订单
                            if (actualPaid.compareTo(BigDecimal.ZERO) > 0) {
                                Integer update = ordersWriteDao.update(newOrder);
                                if (update == 0) {
                                    throw new BusinessException("修改订单余额支付金额时失败！");
                                }

                                // 记录余额日志
                                MemberBalanceLogs logs = new MemberBalanceLogs();
                                logs.setMemberId(member.getId());
                                logs.setMemberName(member.getName());
                                logs.setMoneyBefore(balance.add(actualPaid));
                                logs.setMoneyAfter(balance);
                                logs.setMoney(actualPaid);
                                logs.setCreateTime(new Date());
                                logs.setState(MemberBalanceLogs.STATE_3);
                                logs.setRemark("消费，订单号" + orders.getOrderSn());
                                logs.setOptId(member.getId());
                                logs.setOptName(member.getName());
                                Integer save = memberBalanceLogsWriteDao.save(logs);
                                if (save == 0) {
                                    throw new BusinessException("记录余额日志时失败！");
                                }

                                // 记录订单支付日志
                                OrderPayLog payLog = new OrderPayLog();
                                payLog.setOrdersId(orders.getId());
                                payLog.setOrdersSn(orders.getOrderSn());
                                payLog.setPaymentCode(Orders.PAYMENT_CODE_BALANCE);
                                payLog.setPaymentName(Orders.PAYMENT_NAME_BALANCE);
                                payLog.setPayMoney(actualPaid);
                                payLog.setPayIntegral(0);
                                payLog.setMemberId(member.getId());
                                payLog.setMemberName(member.getName());
                                payLog.setCreateTime(new Date());
                                save = orderPayLogWriteDao.insert(payLog);
                                if (save == 0) {
                                    throw new BusinessException("记录订单支付日志时失败！");
                                }
                            }
                        } else {
                            // 如果小于等于0则跳出循环
                            break;
                        }
                    }
                    //更改余额
                    Member memberBalance = new Member();
                    memberBalance.setId(member.getId());
                    memberBalance.setBalance(paidMoney.multiply(new BigDecimal(-1)));
                    Integer updateBalance = memberWriteDao.updateBalance(memberBalance);
                    if (updateBalance == 0) {
                        throw new BusinessException("修改会员余额金额时失败！");
                    }
                    if (orderMoneyAlls.compareTo(memberNew.getBalance()) <= 0) {//余额够支付
                        orderSuccessVO.setBanlancePayMoneyVO(orderMoneyAlls);
                        orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_2);
                        // 更改订单状态
                        this.payAfter(ordersList, BigDecimal.ZERO, memberNew, "", "", "",
                            Orders.PAYMENT_CODE_BALANCE, Orders.PAYMENT_NAME_BALANCE, true);
                    } else {//余额不够支付
                        orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_3);
                        orderSuccessVO.setBanlancePayMoneyVO(memberNew.getBalance());
                    }
                } else {
                    orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_1);
                }
            }

            transactionManager.commit(status);
            return orderSuccessVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][orderPayBefore]获取订单数据发生异常:", e);
            throw e;
        }
    }

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param isPaySn 是否是支付订单号，true则paySn是支付订单号，false则paySn为订单号
     * @param paySn 支付订单号或者订单号（根据isPaySn决定）
     * @param type  4:福利订单 积分足够  5:福利订单 积分不够 余额抵扣
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    public OrderSuccessVO orderPayByIntegral(boolean isPaySn, String paySn, Integer type,
                                             String balancePassword, Member member) {
        OrderSuccessVO orderSuccessVO;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (StringUtil.isEmpty(paySn, true)) {
                log.error("订单号为空。");
                throw new BusinessException("订单号不能为空，请重试！");
            }


            // 如果不是支付订单号
            if (!isPaySn) {
                // 先修改订单的支付订单号为订单号
                Integer updatePaySn = ordersWriteDao.updatePaySn(paySn);
                if (updatePaySn == 0) {
                    log.error("修改订单支付订单号失败。orderSn=" + paySn);
                    throw new BusinessException("修改订单支付订单号失败，请稍后再试！");
                }
                // 然后根据订单号获取订单信息
                orderSuccessVO = this.getOrdersByOrderSn(paySn, member.getId());
            } else {
                // 根据支付订单号获取订单信息
                orderSuccessVO = this.getOrdersByPaySn(paySn, member.getId());
            }

            List<Orders> ordersList = orderSuccessVO.getOrdersList();
            Member newMember=memberWriteDao.get(member.getId());
            if (ordersList == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请重试！");
            }
            // 支付前验证操作
            this.payBefore(ordersList);

            Config config = configReadDao.get(ConstantsEJS.CONFIG_ID);

            if(null == config) {
                throw new BusinessException("获取积分换算比例失败。");
            }
            
            if (!newMember.getBalancePwd().equals(Md5.getMd5String(balancePassword))) {
                throw new BusinessException("支付密码错误，请重新输入");
            }
            //用户账户总的专项积分数
            Integer memberSpecial=getMemberSpecialIntegralListByOrdersList(ordersList);
            if((null == newMember.getIntegral() || newMember.getIntegral() <= 0 )&&memberSpecial<=0) {
                throw new BusinessException("您还没有积分，无法兑换积分商品。");
            }
            //计算订单总积分
            BigDecimal orderMoneyAlls = orderMoneyAllsFormWelfare(ordersList);
            /*//余额可以兑换的积分数量
            Integer moneyIntegral = newMember.getBalance().multiply(new BigDecimal(config.getIntegralScale())).intValue();
            //判断积分+余额 是否能够支付订单
            if(memberSpecial+newMember.getIntegral() + moneyIntegral < orderMoneyAlls.intValue()) {
            	throw new BusinessException("积分和余额不足支付订单。");
            }*/
            
            
            for (Orders orders:ordersList){
                Orders order = new Orders();
                order.setId(orders.getId());
                order.setOrderState(Orders.ORDER_STATE_3);
                order.setPayTime(new Date());
                order.setPaymentStatus(Orders.PAYMENT_STATUS_1);
                //List<OrdersProduct> OrdersProductList=ordersProductReadDao.getByOrderId(orders.getId());
                Map<String,Object>map=new HashMap<>();
                map.put("q_memberId",newMember.getId());
                map.put("q_sellerId",orders.getSellerId());
                map.put("q_value",1);
                //单笔订单需要支付的金额(积分)
                Integer payMoney=orders.getMoneyOrder().intValue();
                //单笔订单实际支付的金额(专项积分)
                Integer actuallyMoney=0;
                Integer count=0;
                List<MemberSpecialIntegral> list=memberSpecialIntegralReadDao.page(map,null,null);
                if(list!=null && list.size()>0){
                    for( int i=0;i<list.size();i++){
                        MemberSpecialIntegral memberSpecialIntegral=list.get(i);
                        if(memberSpecialIntegral.getValue()>0){
                            if(memberSpecialIntegral.getValue()>=payMoney){
                                actuallyMoney+=memberSpecialIntegral.getValue()-payMoney;
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                memberGradeIntegralLogs.setValue(payMoney);
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_3);
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberName(member.getName());
                                memberGradeIntegralLogs.setRefCode(orders.getOrderSn());
                                memberGradeIntegralLogs.setOptDes("(专项积分)消费订单号"+orders.getOrderSn());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                memberGradeIntegralLogs.setMsiId(memberSpecialIntegral.getId());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                }
                                memberSpecialIntegral.setValue(memberSpecialIntegral.getValue()-payMoney);
                                count=memberSpecialIntegralWriteDao.update(memberSpecialIntegral);
                                if(count==0){
                                    throw new BusinessException("修改专项积分失败");
                                }
                                payMoney=0;
                                break;
                            }else{
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                actuallyMoney+=memberSpecialIntegral.getValue();
                                payMoney = payMoney - memberSpecialIntegral.getValue();
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_3);
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberName(member.getName());
                                memberGradeIntegralLogs.setRefCode(orders.getOrderSn());
                                memberGradeIntegralLogs.setOptDes("(专项积分)消费订单号"+orders.getOrderSn());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                memberGradeIntegralLogs.setMsiId(memberSpecialIntegral.getId());
                                memberGradeIntegralLogs.setValue(memberSpecialIntegral.getValue());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                }
                                memberSpecialIntegral.setValue(0);
                                count=memberSpecialIntegralWriteDao.update(memberSpecialIntegral);
                                if(count==0){
                                    throw new BusinessException("修改专项积分失败");
                                }
                            }
                        }
                    }
                }
                if(payMoney>0){
                    if(newMember.getIntegral()>0){
                        if(newMember.getIntegral()>=payMoney){
                            type=OrderSuccessVO.BANLANCEPAYVO_4;
                            //通用积分+专项积分够付款
                            //通用积分日志记录
                            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                            memberGradeIntegralLogs.setValue(payMoney);
                            memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_2);
                            memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
                            memberGradeIntegralLogs.setMemberId(newMember.getId());
                            memberGradeIntegralLogs.setMemberName(newMember.getName());
                            memberGradeIntegralLogs.setRefCode(orders.getOrderSn());
                            memberGradeIntegralLogs.setOptDes("(通用积分)消费订单号"+orders.getOrderSn());
                            memberGradeIntegralLogs.setCreateTime(new Date());
                            count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                            if (count == 0) {
                                throw new BusinessException("会员积分值日志记录失败，请重试！");
                            }
                            newMember.setIntegral(newMember.getIntegral()-payMoney);
                            Integer update = memberWriteDao.update(newMember);
                            if (update == 0) {
                                throw new BusinessException("修改会员积分时失败！");
                            }
                            //订单支付的实际通用积分
                            order.setIntegral(payMoney);
                            //订单
                            order.setSpecialIntegral(actuallyMoney);
                            order.setMoneyIntegral(BigDecimal.ZERO);
                            payMoney=0;
                        }else{
                            if(newMember.getIntegral()>0){
                                //通用积分日志记录
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                memberGradeIntegralLogs.setValue(newMember.getIntegral());
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_2);
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
                                memberGradeIntegralLogs.setMemberId(newMember.getId());
                                memberGradeIntegralLogs.setMemberName(newMember.getName());
                                memberGradeIntegralLogs.setRefCode(orders.getOrderSn());
                                memberGradeIntegralLogs.setOptDes("(通用积分)消费订单号"+orders.getOrderSn());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                }
                            }
                            BigDecimal balance = newMember.getBalance();
                            payMoney=payMoney-newMember.getIntegral();
                            BigDecimal need=new BigDecimal(payMoney);
                            // 余额换算成积分的数量
                            BigDecimal balanceInter = balance.multiply(new BigDecimal(config.getIntegralScale()));
                            if(balanceInter.compareTo(need) < 0) {	//判断余额抵扣积分是否足够
                                throw new BusinessException("可抵扣的余额不足，请充值后  到我的订单完成支付。");
                            }else {		//如果抵扣的金额足够 则计算出抵扣的金额
                                //专项积分加通用积分加余额
                                type=OrderSuccessVO.BANLANCEPAYVO_5;
                                order.setIntegral(newMember.getIntegral());
                                order.setSpecialIntegral(actuallyMoney);
                                order.setMoneyIntegral(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                                balance =balance.subtract(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN)) ;
                                saveMemberBalanceLogs(newMember, orders, need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                                orderSuccessVO.setBanlancePayMoneyVO(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                                newMember.setBalance(balance);
                                Integer update = memberWriteDao.update(newMember);
                                if (update == 0) {
                                    throw new BusinessException("修改余额时失败");
                                }
                                newMember.setIntegral(0);
                            }
                        }
                    }else{
                        //没有通用积分 专项加上余额支付
                        BigDecimal balance = newMember.getBalance();
                        BigDecimal need=new BigDecimal(payMoney);
                        // 余额换算成积分的数量
                        BigDecimal balanceInter = balance.multiply(new BigDecimal(config.getIntegralScale()));
                        if(balanceInter.compareTo(need) < 0) {	//判断余额抵扣积分是否足够
                            throw new BusinessException("可抵扣的余额不足，请充值后  到我的订单完成支付。");
                        }else {		//如果抵扣的金额足够 则计算出抵扣的金额
                            //专项积分加通用积分加余额
                            type=OrderSuccessVO.BANLANCEPAYVO_5;
                            order.setIntegral(0);
                            order.setSpecialIntegral(actuallyMoney);
                            order.setMoneyIntegral(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                            balance =balance.subtract(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN)) ;
                            saveMemberBalanceLogs(newMember, orders, need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                            orderSuccessVO.setBanlancePayMoneyVO(need.divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN));
                            newMember.setBalance(balance);
                            Integer update = memberWriteDao.update(newMember);
                            if (update == 0) {
                                throw new BusinessException("修改余额时失败！");
                            }
                            newMember.setIntegral(0);
                        }
                    }
                }else{
                    //单独专项积分够支付
                    type = OrderSuccessVO.BANLANCEPAYVO_4;
                    order.setIntegral(0);
                    order.setSpecialIntegral(orders.getMoneyOrder().intValue());
                    order.setMoneyIntegral(BigDecimal.ZERO);
                }
                Integer flag = ordersWriteDao.update(order);
                if(flag == 0) {
                    throw new BusinessException("修改订单已付款状态失败，请重试！");
                }
                flag = memberWriteDao.update(newMember);
                if (flag == 0) {
                    throw new BusinessException("修改会员积分时失败！");
                }
                //订单支付日志
                savePayLog(newMember, orders, orders.getMoneyIntegral(),actuallyMoney);
            }
            
            orderSuccessVO.setPayOrderAllsVO(orderMoneyAlls);
            // 更改订单状态
            this.payAfter(ordersList, BigDecimal.ZERO, newMember, "", "", "",
                    Orders.PAYMENT_CODE_BALANCE, Orders.PAYMENT_NAME_BALANCE, true);
            orderSuccessVO.setBanlancePayVO(type);
            //发送消息到用户
            Map<String,Object> queryMap = new HashMap<>();
            queryMap.put("time", new Date());
            queryMap.put("orderSn", paySn);
            messageModel.sendMessageToMember(queryMap, newMember.getId(), Message.DDTJTZ);
            transactionManager.commit(status);
            return orderSuccessVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][orderPayBefore]获取订单数据发生异常:", e);
            throw e;
        }
    }

    //计算用户总的专项积分数
    public Integer getMemberSpecialIntegralListByOrdersList(List<Orders> ordersList){
        Integer allMoney=0;
        Map<String,Object>map=new HashMap<>();
        for (Orders orders : ordersList) {
            map.put("q_memberId",orders.getMemberId());
            map.put("q_sellerId",orders.getSellerId());
            List<MemberSpecialIntegral> list=memberSpecialIntegralReadDao.page(map,null,null);
            if(list!=null && list.size()>0){
                for(MemberSpecialIntegral m:list){
                    if(m.getValue()>0){
                        allMoney+=m.getId();
                    }
                }
            }
        }
        return allMoney;
    }

    //记录余额消费日志
    public void saveMemberBalanceLogs(Member member,Orders orders,BigDecimal actualPaid) {
        MemberBalanceLogs logs = new MemberBalanceLogs();
        logs.setMemberId(member.getId());
        logs.setMemberName(member.getName());
        logs.setMoneyBefore(member.getBalance());
        logs.setMoneyAfter(member.getBalance().subtract(actualPaid));
        logs.setMoney(actualPaid);
        logs.setCreateTime(new Date());
        logs.setState(MemberBalanceLogs.STATE_3);
        logs.setRemark("消费，订单号" + orders.getOrderSn());
        logs.setOptId(member.getId());
        logs.setOptName(member.getName());
        Integer save = memberBalanceLogsWriteDao.save(logs);
        if (save == 0) {
            throw new BusinessException("记录余额日志时失败！");
        }
    }
    public void savePayLog(Member member,Orders orders,BigDecimal actualPaid,Integer integral) {
        // 记录订单支付日志
        OrderPayLog payLog = new OrderPayLog();
        payLog.setOrdersId(orders.getId());
        payLog.setOrdersSn(orders.getOrderSn());
        payLog.setPaymentCode(Orders.PAYMENT_CODE_BALANCE);
        payLog.setPaymentName(Orders.PAYMENT_NAME_BALANCE);
        payLog.setPayMoney(actualPaid);
        payLog.setPayIntegral(integral);
        payLog.setMemberId(member.getId());
        payLog.setMemberName(member.getName());
        payLog.setCreateTime(new Date());
        Integer save = orderPayLogWriteDao.insert(payLog);
        if (save == 0) {
            throw new BusinessException("记录订单支付日志时失败！");
        }
    }

    /**
     * 计算订单实际需要支付的金额
     * @param ordersList
     * @return
     */
    private BigDecimal orderMoneyAlls(List<Orders> ordersList) {
        BigDecimal orderMoneyAlls = new BigDecimal(0);
        for (Orders orders : ordersList) {
            // 订单应支付金额：money_order - money_integral - money_paid_balance - money_paid_reality
            // 如果订单勾选余额支付则先扣除余额，所以存在用户第一次支付未成功或者放弃支付时余额已经扣除，订单的余额支付字段已有值，所以应支付金额应减去余额支付的部分
            BigDecimal needPay = orders.getMoneyOrder().subtract(orders.getMoneyIntegral())
                    .subtract(orders.getMoneyPaidBalance()).subtract(orders.getMoneyPaidReality());
            needPay = needPay.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : needPay;
            orderMoneyAlls = orderMoneyAlls.add(needPay);
        }
        return orderMoneyAlls;
    }

    /**
     * 计算订单实际需要支付的金额
     * @param ordersList
     * @return
     */
    private BigDecimal orderMoneyAllsFormWelfare(List<Orders> ordersList) {
        BigDecimal orderMoneyAlls = new BigDecimal(0);
        for (Orders orders : ordersList) {
            // 订单应支付金额：money_order - money_integral - money_paid_balance - money_paid_reality
            // 如果订单勾选余额支付则先扣除余额，所以存在用户第一次支付未成功或者放弃支付时余额已经扣除，订单的余额支付字段已有值，所以应支付金额应减去余额支付的部分
            BigDecimal needPay = orders.getMoneyOrder();
            needPay = needPay.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : needPay;
            orderMoneyAlls = orderMoneyAlls.add(needPay);
        }
        return orderMoneyAlls;
    }

    /**
     * 支付成功之后更改订单的状态
     * @param trade_no 订单
     * @param total_fee 金额
     * @param paycode 支付方式
     * @param payname 支付方式
     * @param tradeSn 交易流水号
     * @param tradeContent 交易返回信息
     * @return
     */
    public Boolean orderPayAfter(String trade_no, String total_fee, String paycode, String payname,
                                 String tradeSn, String tradeContent) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            List<Orders> ordersList = ordersWriteDao.getByPaySn(trade_no);
            if (ordersList == null || ordersList.size() == 0) {
                throw new BusinessException("订单信息获取失败。");
            }
            int memberID = ordersList.get(0).getMemberId();
            Member member = memberWriteDao.get(memberID);

            // 更改订单状态、记录余额日志、支付日志

            // TODO 测试代码 正式环境中删除这段代码使用下面的正式代码  开始-------------
            // 计算订单总金额
            //            BigDecimal orderMoneyAlls = orderMoneyAlls(ordersList);
            //            this.payAfter(ordersList, orderMoneyAlls, member, trade_no, tradeSn, tradeContent,
            //                paycode, payname, false);

            // 测试代码  结束-------------

            // 正式代码  开始-------------
            this.payAfter(ordersList, new BigDecimal(total_fee), member, trade_no, tradeSn,
                    tradeContent, paycode, payname, false);
            // 正式代码  结束-------------

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][orderPayAfter]订单修改付款状态时发生异常:", e);
            throw e;
        }
    }

    // ------------------------限时抢购开始----------------------------------------------------------------------------------

    /**
     * 用户限时抢购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForFlash(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，限时抢购活动不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null
            && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        Calendar calendar = Calendar.getInstance();
        Integer currHour = calendar.get(Calendar.HOUR_OF_DAY);
        String actDate = TimeUtil.getToday() + " 00:00:00";
        // 取当天有效的活动
        ActFlashSale actFlashSale = actFlashSaleReadDao.getEffectiveActFlashSale(actDate, channel);
        // 当前阶段
        ActFlashSaleStage actFlashSaleStage = null;
        // 当前下单商品的活动
        ActFlashSaleProduct actFlashSaleProduct = null;
        // 取当前有效活动的时间段信息
        if (actFlashSale != null) {
            // 如果有活动取阶段
            actFlashSaleStage = actFlashSaleStageReadDao.getStageByTime(actFlashSale.getId(),
                    currHour);
            if (actFlashSaleStage != null) {
                // 如果阶段不空，继续取活动商品，需要校验库存，所以从写库读取
                actFlashSaleProduct = actFlashSaleProductWriteDao.getByStageIdAndProductId(
                        actFlashSaleStage.getId(), orderCommitVO.getProductId());
                if (actFlashSaleProduct != null) {
                    // 如果商品不为空，则继续
                } else {
                    // 如果为空，直接抛异常
                    throw new BusinessException("该商品当前时间没有限时抢购活动！");
                }
            } else {
                // 如果阶段为空，直接抛异常
                throw new BusinessException("当前时间没有限时抢购活动！");
            }
        } else {
            // 如果没有活动，直接抛异常
            throw new BusinessException("当前时间没有限时抢购活动！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        if (actFlashSale.getStatus().intValue() != ActFlashSale.STATUS_5) {
            throw new BusinessException("该秒杀活动未上架，不能下单！");
        }
        if (actFlashSaleProduct.getStatus().intValue() != ActFlashSaleProduct.STATUS_2) {
            throw new BusinessException("该商品未通过审核，不能下单！");
        }
        // 判断库存
        if (actFlashSaleProduct.getStock() <= 0) {
            throw new BusinessException("该商品已被抢光了，下次赶早哦！");
        }

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberWriteDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(product,
                    productGoods, orderCommitVO.getNumber(), address.getCityId());
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO
                    : calculateTransFee;

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_2,
                    actFlashSaleProduct, null, null, calculateTransFee, member, orderCommitVO, address,
                    integral, config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_2,
                    actFlashSaleProduct, null, null, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode(Orders.PAYMENT_NAME_BALANCE);
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setPaySn(order.getPaySn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    // ------------------------限时抢购结束----------------------------------------------------------------------------------

    // ------------------------活动公用方法开始-------------------------------------------------------------------------------
    /**
     * 保存商家的限时抢购、团购、集合竞价定金订单，以及订单日志
     * @param seller 商家信息
     * @param orderType 订单类型：Orders.ORDER_TYPE_2为限时抢购订单，Orders.ORDER_TYPE_3为团购订单，Orders.ORDER_TYPE_4为竞价定金订单
     * @param actFlashSaleProduct 抢购活动商品
     * @param actGroup 团购活动
     * @param actBidding 集合竞价活动
     * @param transFee 运费
     * @param member 会员
     * @param orderCommitVO 订单提交信息
     * @param address 地址
     * @param integral 积分
     * @param config 积分配置
     * @return
     */
    private Orders saveOrderInfoForActivity(Seller seller, Integer orderType,
                                            ActFlashSaleProduct actFlashSaleProduct,
                                            ActGroup actGroup, ActBidding actBidding,
                                            BigDecimal transFee, Member member,
                                            OrderCommitVO orderCommitVO, MemberAddress address,
                                            Integer integral, Config config) {
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        // 设定支付订单号(因为活动订单每次生成都是一个订单，所以直接设定支付订单号与订单号一致)
        order.setPaySn(orderSn);
        // 设为限时抢购、团购订单、竞价定金订单
        order.setOrderType(orderType);
        order.setOrderSn(orderSn);
        order.setIsWelfareOrder(Product.IS_WELFARE_1);
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        order.setPaymentName(orderCommitVO.getPaymentName());
        order.setPaymentCode(orderCommitVO.getPaymentCode());

        // 收货地址信息设置
        order.setName(address.getmemberName());
        order.setProvinceId(address.getProvinceId());
        order.setCityId(address.getCityId());
        order.setAreaId(address.getAreaId());
        order.setAddressAll(address.getAddAll());
        order.setAddressInfo(address.getAddressInfo());
        order.setMobile(address.getMobile());
        order.setEmail(address.getEmail());
        order.setZipCode(address.getZipCode());

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        order.setLogisticsId(0);
        order.setLogisticsName("");
        order.setLogisticsNumber("");

        // 是否货到付款订单0、不是；1、是
        order.setIsCodconfim(Orders.IS_CODCONFIM_0);
        // 货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
        order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);
        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        /**
         * 金额的计算
         */

        // 金额信息

        // 商品总额（只是商品价格*数量的金额之和）
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            BigDecimal moneyProduct = actFlashSaleProduct.getPrice()
                    .multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），限时抢购订单存抢购活动商品表ID
            order.setActivityId(actFlashSaleProduct.getId());
        } else if (orderType.intValue() == Orders.ORDER_TYPE_3) {
            BigDecimal moneyProduct = actGroup.getPrice()
                    .multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），团购订单存团购活动表ID
            order.setActivityId(actGroup.getId());
        } else if (orderType.intValue() == Orders.ORDER_TYPE_4) {
            BigDecimal moneyProduct = actBidding.getFirstPrice()
                    .multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），集合竞价订单存集合竞价活动表ID
            order.setActivityId(actBidding.getId());
        }

        // 判断物流费用
        order.setMoneyLogistics(transFee);
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(BigDecimal.ZERO);

        // 优惠券优惠金额、优惠券ID（coupon_user的ID），大活动不能使用优惠券
        order.setMoneyCoupon(BigDecimal.ZERO);
        order.setCouponUserId(0);
        // 订单满减金额，大活动不参加满减
        order.setMoneyActFull(BigDecimal.ZERO);
        order.setActFullId(0);
        // 优惠金额总额（满减、立减、优惠券和），大活动不参加活动、不使用优惠券
        order.setMoneyDiscount(BigDecimal.ZERO);
        // 新订单退款的金额为0
        order.setMoneyBack(BigDecimal.ZERO);
        // 订单使用积分金额
        // 积分支付日志
        OrderPayLog orderPayLogInt = null;
        if (integral > 0) {
            // 计算转换总金额
            int moneyIntegral = integral / config.getIntegralScale();
            order.setMoneyIntegral(new BigDecimal(moneyIntegral));
            order.setIntegral(integral);

            // 修改用户积分数，记录积分消耗日志
            Member memberNew = new Member();
            memberNew.setId(member.getId());
            memberNew.setIntegral(0 - integral);
            Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
            if (updateIntegral == 0) {
                throw new BusinessException("扣除用户积分时失败，请重试！");
            }
            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
            memberGradeIntegralLogs.setMemberId(member.getId());
            memberGradeIntegralLogs.setMemberName(member.getName());
            memberGradeIntegralLogs.setValue(integral);
            memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_7);
            memberGradeIntegralLogs.setOptDes("积分支付消费(订单号:" + order.getOrderSn() + ")");
            memberGradeIntegralLogs.setRefCode(order.getOrderSn());
            memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
            memberGradeIntegralLogs.setCreateTime(new Date());
            Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (save == 0) {
                throw new BusinessException("记录用户积分消费日志失败，请重试！");
            }
            // 记录订单积分支付日志
            orderPayLogInt = new OrderPayLog();
            // orderPayLogInt.setOrdersId(order.getId());
            orderPayLogInt.setOrdersSn(order.getOrderSn());
            orderPayLogInt.setPaymentCode(Orders.PAYMENT_CODE_INTEGRAL);
            orderPayLogInt.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);
            orderPayLogInt.setPayMoney(new BigDecimal(moneyIntegral));
            orderPayLogInt.setPayIntegral(integral);
            orderPayLogInt.setMemberId(member.getId());
            orderPayLogInt.setMemberName(member.getName());
            orderPayLogInt.setCreateTime(new Date());
        } else {
            order.setMoneyIntegral(BigDecimal.ZERO);
            order.setIntegral(0);
        }

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        order.setMoneyOrder(((order.getMoneyProduct().add(order.getMoneyLogistics()))
                .subtract(order.getMoneyDiscount())));

        OrderLog orderLogForPaid = null;
        if ((order.getMoneyOrder().subtract(order.getMoneyIntegral()))
                    .compareTo(new BigDecimal(0)) <= 0) {
            // 如果订单金额减去积分支付金额小于等于0，则直接设定为已付款
            if (orderType.intValue() == Orders.ORDER_TYPE_4) {
                // 竞价定金订单付款之后直接设定状态为已完成（定金订单不需要发货）
                order.setOrderState(Orders.ORDER_STATE_5);
            } else {
                order.setOrderState(Orders.ORDER_STATE_3);
            }
            order.setPayTime(new Date());
            order.setPaymentStatus(Orders.PAYMENT_STATUS_1);

            orderLogForPaid = new OrderLog();
            orderLogForPaid.setContent("您使用积分支付了订单");
            orderLogForPaid.setOperatingId(member.getId());
            // 订单保存后设定订单ID
            // orderLogForPaid.setOrdersId(order.getId());
            orderLogForPaid.setOrdersSn(order.getOrderSn());
            orderLogForPaid.setOperatingName(member.getName());
        } else {
            // 其他情况

            // 付款状态
            order.setOrderState(Orders.ORDER_STATE_1);
            order.setPaymentStatus(Orders.PAYMENT_STATUS_0);

        }
        order.setEvaluateState(Orders.EVALUATE_STATE_1);

        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        //        // 如果订单已经支付，则占库存，增加销量
        //        if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
        //            Integer stockRow = 0;
        //            if (orderType.intValue() == Orders.ORDER_TYPE_2) {
        //                stockRow = this.updateFlashProductStockAndActualSales(actFlashSaleProduct.getId(),
        //                    orderCommitVO.getNumber());
        //            } else if (orderType.intValue() == Orders.ORDER_TYPE_3) {
        //                stockRow = this.updateGroupStockAndActualSales(actGroup.getId(),
        //                    orderCommitVO.getNumber());
        //            } else if (orderType.intValue() == Orders.ORDER_TYPE_4) {
        //                stockRow = this.updateBiddingStockAndActualSales(actBidding.getId(),
        //                    orderCommitVO.getNumber());
        //            }
        //            if (stockRow == 0) {
        //                throw new BusinessException("修改商品库存时失败，请重试！");
        //            }
        //
        //            // 订单支付时记录订单日志
        //            if (orderLogForPaid != null) {
        //                orderLogForPaid.setOrdersId(order.getId());
        //                orderLogWriteDao.save(orderLogForPaid);
        //            }
        //        }

        // 占库存，增加销量
        Integer stockRow = 0;
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            stockRow = this.updateFlashProductStockAndActualSales(actFlashSaleProduct.getId(),
                    orderCommitVO.getNumber(), false);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_3) {
            stockRow = this.updateGroupStockAndActualSales(actGroup.getId(),
                    orderCommitVO.getNumber(), false);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_4) {
            stockRow = this.updateBiddingStockAndActualSales(actBidding.getId(),
                    orderCommitVO.getNumber(), false);
        }
        if (stockRow == 0) {
            throw new BusinessException("修改商品库存时失败，请重试！");
        }
        if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
            // 订单支付时记录订单日志
            if (orderLogForPaid != null) {
                orderLogForPaid.setOrdersId(order.getId());
                orderLogWriteDao.save(orderLogForPaid);
            }
        }

        // 订单支付日志
        // 如果积分支付日志不为空则记录
        if (orderPayLogInt != null) {
            orderPayLogInt.setOrdersId(order.getId());
            orderPayLogWriteDao.insert(orderPayLogInt);
        }

        return order;
    }

    /**
     * 保存限时抢购、团购、集合竞价定金网单信息
     * @param order 订单
     * @param product 商品
     * @param goods 货品
     * @param orderType 订单类型
     * @param actFlashSaleProduct 限时抢购活动
     * @param actGroup 团购活动
     * @param actBidding 集合竞价活动
     * @param member 会员
     * @param seller 商家
     * @param number 数量
     */
    private void saveOrderProductInfoForActivity(Orders order, Product product, ProductGoods goods,
                                                 Integer orderType,
                                                 ActFlashSaleProduct actFlashSaleProduct,
                                                 ActGroup actGroup, ActBidding actBidding,
                                                 Member member, Seller seller, Integer number) {

        //保存网单信息
        OrdersProduct op = new OrdersProduct();
        op.setOrdersId(order.getId());
        op.setOrdersSn(order.getOrderSn());
        op.setSellerId(seller.getId());
        op.setProductCateId(product.getProductCateId());
        op.setProductId(product.getId());
        op.setProductGoodsId(goods.getId());
        op.setSpecInfo(goods.getNormName());
        op.setProductName(product.getName1());
        op.setProductSku(goods.getSku());
        op.setPackageGroupsId(0);
        op.setMallGroupsId(0);
        op.setGiftId(0);
        op.setIsGift(OrdersProduct.IS_GIFT_0);
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            op.setMoneyPrice(actFlashSaleProduct.getPrice());
            // 网单金额
            BigDecimal moneyAmount = actFlashSaleProduct.getPrice()
                    .multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(0);
            op.setActFlashSaleId(actFlashSaleProduct.getActFlashSaleId());
            op.setActFlashSaleProductId(actFlashSaleProduct.getId());
            op.setActBiddingId(0);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_3) {
            op.setMoneyPrice(actGroup.getPrice());
            // 网单金额
            BigDecimal moneyAmount = actGroup.getPrice().multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(actGroup.getId());
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(0);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_4) {
            op.setMoneyPrice(actBidding.getFirstPrice());
            // 网单金额
            BigDecimal moneyAmount = actBidding.getFirstPrice().multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(0);
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(actBidding.getId());
        }
        op.setActIntegralId(0);
        op.setActIntegralNum(0);
        op.setActMoney(BigDecimal.ZERO);
        op.setNumber(number);

        // 立减优惠金额和，不参加活动
        op.setMoneyActSingle(BigDecimal.ZERO);
        op.setActSingleId(0);
        op.setBackNumber(0);
        op.setExchangeNumber(0);
        op.setIsEvaluate(OrdersProduct.IS_EVALUATE_0);

        // 1、保存网单
        int count = ordersProductWriteDao.insert(op);
        if (count == 0) {
            throw new BusinessException("网单保存失败，请重试！");
        }

        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            // 设定网单的限时抢购活动参与日志
            ActFlashSaleLog actFlashSaleLog = new ActFlashSaleLog();
            actFlashSaleLog.setActFlashSaleId(actFlashSaleProduct.getActFlashSaleId());
            actFlashSaleLog.setActFlashSaleProductId(actFlashSaleProduct.getId());
            actFlashSaleLog.setMemberId(member.getId());
            actFlashSaleLog.setSellerId(seller.getId());
            actFlashSaleLog.setOrderId(order.getId());
            actFlashSaleLog.setOrderProductId(op.getId());
            actFlashSaleLog.setProductId(product.getId());
            actFlashSaleLog.setCreateUserId(member.getId());
            actFlashSaleLog.setCreateUserName(member.getName());
            actFlashSaleLog.setCreateTime(new Date());
            actFlashSaleLogWriteDao.insert(actFlashSaleLog);
        }
    }

    /**
     * 检查商品和商家信息
     * @param product
     * @param productGoods
     * @param seller
     */
    private void checkProductAndSellerForActivity(Product product, ProductGoods productGoods,
                                                  Seller seller) {
        if (product == null) {
            throw new BusinessException("商品信息获取失败！");
        }
        if (productGoods == null) {
            throw new BusinessException("货品信息获取失败！");
        }
        if (seller == null) {
            throw new BusinessException("商家信息获取失败！");
        }
        if (!productGoods.getProductId().equals(product.getId())) {
            throw new BusinessException("货品信息和商品信息不匹配！");
        }
        if (!seller.getId().equals(product.getSellerId())) {
            throw new BusinessException("商品信息和商家信息不匹配！");
        }
        if (seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
            throw new BusinessException(
                    "商家[" + seller.getSellerName() + "]已被冻结，请把该商家的商品移出购物车后再下单，谢谢！");
        }
        if (product.getState().intValue() != Product.STATE_6) {
            throw new BusinessException("该商品已下架，不能下单！");
        }
        // 判断分类状态
        if (product.getProductCateState().intValue() != Product.PRODUCT_CATE_STATE_1) {
            throw new BusinessException("该商品已下架，请重新选择商品！");
        }
        // 货品状态异常或未启用的，不能下单
        if (productGoods.getState() == null
            || productGoods.getState().intValue() == ProductGoods.DISABLE) {
            String productName = product.getName1();
            if (!StringUtil.isEmpty(productGoods.getNormName(), true)) {
                productName += " " + productGoods.getNormName();
            }
            throw new BusinessException("商品[" + productName + "]已下架，不能下单！");
        }
    }
    // ------------------------活动公用方法结束-------------------------------------------------------------------------------

    // ------------------------团购开始-------------------------------------------------------------------------------------

    /**
     * 用户团购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForGroup(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，团购活动不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null
            && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        // 获取团购活动
        ActGroup actGroup = actGroupReadDao.get(orderCommitVO.getActGroupId());
        this.checkActGroupEffect(actGroup);
        // 校验渠道
        if (actGroup.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
            if (actGroup.getChannel().intValue() != channel) {
                String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException("该团购活动不能在" + channelName + "下单。");
            }
        }
        // 校验库存
        if (actGroup.getStock() < orderCommitVO.getNumber()) {
            throw new BusinessException("对不起，商品库存不足了，请修改购买数量后再下单！");
        }
        // 单次购买限制
        if (orderCommitVO.getNumber() > actGroup.getPurchase()) {
            throw new BusinessException("对不起，单次最多购买" + actGroup.getPurchase() + "个该商品！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberWriteDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(product,
                    productGoods, orderCommitVO.getNumber(), address.getCityId());
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO
                    : calculateTransFee;

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_3, null,
                    actGroup, null, calculateTransFee, member, orderCommitVO, address, integral,
                    config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_3,
                    null, actGroup, null, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode(Orders.PAYMENT_NAME_BALANCE);
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setPaySn(order.getPaySn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 团购活动有效性校验
     * @param actGroup
     */
    private void checkActGroupEffect(ActGroup actGroup) {
        if (actGroup.getTypeState() == null || actGroup.getTypeState() != ActGroup.TYPE_STATE_1) {
            log.error("团购活动" + actGroup.getName() + "的分类状态为不显示，下单失败。");
            throw new BusinessException("对不起，该团购活动已下线！");
        }
        if (actGroup.getState() == null || actGroup.getState() != ActGroup.STATE_3) {
            throw new BusinessException("对不起，团购活动不存在！");
        }
        if (actGroup.getActivityState() == null
            || actGroup.getActivityState() != ActGroup.ACTIVITY_STATE_2) {
            throw new BusinessException("对不起，该团购活动还没有发布！");
        }
        Date date = new Date();
        if (date.before(actGroup.getStartTime())) {
            throw new BusinessException("对不起，该团购活动还没有开始！");
        }
        if (date.after(actGroup.getEndTime())) {
            throw new BusinessException("对不起，该团购活动已结束！");
        }
        if (actGroup.getStock() < 1) {
            throw new BusinessException("对不起，该商品已经被抢光了！");
        }
    }

    // ------------------------团购结束-------------------------------------------------------------------------------------

    // ------------------------集合竞价开始----------------------------------------------------------------------------------

    /**
     * 用户提交集合竞价订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForBidding(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }

        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，当前活动订单不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null
            && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        // 获取活动
        ActBidding actBidding = actBiddingReadDao.get(orderCommitVO.getActBiddingId());
        // 校验活动
        this.checkActBidding(actBidding);

        // 校验渠道
        if (actBidding.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
            if (actBidding.getChannel().intValue() != channel) {
                String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException("当前活动不能在" + channelName + "下单。");
            }
        }
        // 校验库存
        if (actBidding.getStock() < orderCommitVO.getNumber()) {
            throw new BusinessException("对不起，商品库存不足了，请修改购买数量后再下单！");
        }
        // 单次购买限制
        if (orderCommitVO.getNumber() > actBidding.getPurchase()) {
            throw new BusinessException("对不起，单次最多购买" + actBidding.getPurchase() + "个该商品！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberWriteDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(product,
                    productGoods, orderCommitVO.getNumber(), address.getCityId());
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO
                    : calculateTransFee;

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_4, null, null,
                    actBidding, calculateTransFee, member, orderCommitVO, address, integral, config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_4,
                    null, null, actBidding, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode(Orders.PAYMENT_NAME_BALANCE);
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setPaySn(order.getPaySn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            transactionManager.commit(status);

            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 校验集合竞价活动
     * @param actBidding
     */
    private void checkActBidding(ActBidding actBidding) {
        if (actBidding == null) {
            throw new BusinessException("集合竞价活动不存在，请稍后再试！");
        }
        if (actBidding.getTypeState() == null
            || actBidding.getTypeState() != ActBidding.TYPE_STATE_1) {
            log.error("活动" + actBidding.getName() + "的分类状态为不显示，下单失败。");
            throw new BusinessException("对不起，该活动已下线！");
        }
        Date date = new Date();
        if (date.before(actBidding.getStartTime())) {
            throw new BusinessException("活动还没有开始，请活动开始后再下单！");
        }
        if (date.after(actBidding.getEndTime())) {
            throw new BusinessException("活动已经结束了，请参加下次活动吧！");
        }
        if (actBidding.getStock() < 1) {
            throw new BusinessException("活动名额已经抢完了，请参加下次活动吧！");
        }
        if (!actBidding.getActivityState().equals(ActBidding.ACTIVITY_STATE_2)) {
            throw new BusinessException("活动未发布，请参加其他活动！");
        }
        if (!actBidding.getState().equals(ActBidding.STATE_3)) {
            throw new BusinessException("活动未被审核通过，请参加其他活动！");
        }
        if (!actBidding.getExecuteState().equals(ActBidding.EXECUTE_STATE_0)) {
            throw new BusinessException("活动已经结束了，请参加下次活动吧！");
        }
    }

    // ------------------------集合竞价结束----------------------------------------------------------------------------------

    // ------------------------积分商城开始-------------------------------------------------------------------------------------

    /**
     * 用户积分换购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForIntegral(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } /* else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
             log.error("订单提交信息中支付方式为空。");
             throw new BusinessException("订单提交信息中支付方式为空，请重试！");
          }*/

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null
            && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        // 获取积分换购活动
        ActIntegral actIntegral = actIntegralReadDao.get(orderCommitVO.getActIntegralId());
        this.checkActIntegralEffect(actIntegral);
        // 校验渠道
        if (actIntegral.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
            if (actIntegral.getChannel().intValue() != channel) {
                String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException("该积分换购活动不能在" + channelName + "下单。");
            }
        }

        // 校验库存
        if (actIntegral.getStock() < orderCommitVO.getNumber()) {
            throw new BusinessException("对不起，商品库存不足了，请修改购买数量后再下单！");
        }
        // 单次购买限制
        if (orderCommitVO.getNumber() > actIntegral.getPurchase()) {
            throw new BusinessException("对不起，单次最多购买" + actIntegral.getPurchase() + "个该商品！");
        }
        // 会员等级限制
        // 获取提交订单的用户
        Member member = memberWriteDao.get(orderCommitVO.getMemberId());
        if (member.getGrade() < actIntegral.getGradeValue()) {
            throw new BusinessException("对不起，您的会员等级不能换购该商品，谢谢。");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            //            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(
            //                orderCommitVO.getSellerId(), orderCommitVO.getNumber(), address.getCityId());
            //            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO
            //                : calculateTransFee;

            // 积分换购只能使用积分支付，所以使用积分数量根据积分换购活动计算
            Integer integral = actIntegral.getPrice() * orderCommitVO.getNumber();
            Integer integral1 = integral;	//记录订单总积分
            //商家专项积分
            Integer specialIntegral = getMemberSpecialIntegralByMemberId(member.getId(),seller.getId());

            Config config = configReadDao.get(ConstantsEJS.CONFIG_ID);
            // 判断用户的积分是否够换购商品的积分数量
            if ((member.getIntegral() == null || member.getIntegral() <= 0) && specialIntegral <= 0) {
                throw new BusinessException("您还没有积分，无法兑换积分商品");
            }
            if (config == null) {
                throw new BusinessException("积分转换金额失败，请联系系统管理员！");
            }

            orderCommitVO.setMoney(BigDecimal.ZERO);

            if((specialIntegral + member.getIntegral()) >= integral) {		//判断专项积分和通用积分 是否足够支付
                if(specialIntegral > integral) {
                    orderCommitVO.setSpecialIntegral(integral);	//设置使用的专项积分
                    orderCommitVO.setIntegral(0);
                    orderSuccVO.setSpecialIntegralPay(integral);
                }else {
                    Integer differ = integral - specialIntegral;
                    orderCommitVO.setSpecialIntegral(specialIntegral);	//设置使用的专项积分
                    orderCommitVO.setIntegral(differ);					//设置使用的通用积分
                    orderSuccVO.setSpecialIntegralPay(specialIntegral);
                }
            }else {
                orderCommitVO.setSpecialIntegral(specialIntegral);	//设置使用的专项积分
                orderCommitVO.setIntegral(member.getIntegral());	//设置使用的通用积分
                //积分支付还差的数量
                Integer  differ = integral - specialIntegral - member.getIntegral();

                //用户余额可以兑换的积分数量
                BigDecimal  balanceIntegral = member.getBalance().multiply(new BigDecimal(config.getIntegralScale()));

                integral = member.getIntegral();
                BigDecimal money = new BigDecimal(differ).divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN);
                orderCommitVO.setMoney(money);
                orderSuccVO.setBanlancePayMoneyVO(money);
                orderSuccVO.setSpecialIntegralPay(specialIntegral);

            }

            orderSuccVO.setIntegralPayMoneyVo(new BigDecimal(member.getIntegral()));

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActIntegral(seller, actIntegral, BigDecimal.ZERO,
                    member, orderCommitVO, address, integral1, config);
            orderList.add(order);


            // 保存网单信息
            this.saveOrderProductInfoForActIntegral(order, product, productGoods, actIntegral,
                    seller, orderCommitVO.getNumber(), integral, config);

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode(Orders.PAYMENT_NAME_BALANCE);
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setPaySn(order.getPaySn());
            orderSuccVO.setPayAmount(new BigDecimal(integral1));
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 保存积分换购订单，以及订单日志
     * @param seller 商家信息
     * @param actInteral 积分换购活动
     * @param transFee 运费
     * @param member 会员
     * @param orderCommitVO 订单提交信息
     * @param address 地址
     * @param integral 积分
     * @param config 积分配置
     * @return
     */
    private Orders saveOrderInfoForActIntegral(Seller seller, ActIntegral actIntegral,
                                               BigDecimal transFee, Member member,
                                               OrderCommitVO orderCommitVO, MemberAddress address,
                                               Integer integral, Config config) {
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        order.setIsWelfareOrder(Product.IS_WELFARE_2);
        // 设定支付订单号(因为活动订单每次生成都是一个订单，所以直接设定支付订单号与订单号一致)
        order.setPaySn(orderSn);
        // 设为积分换购订单
        order.setOrderType(Orders.ORDER_TYPE_6);
        order.setOrderSn(orderSn);
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        if(orderCommitVO.getMoney().compareTo(BigDecimal.ZERO) > 0) {
        	order.setPaymentName(Orders.PAYMENT_NAME_INTEGRALGROUP);
            order.setPaymentCode(Orders.PAYMENT_CODE_INTEGRALGROUP);
        }else {
        	order.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);
            order.setPaymentCode(Orders.PAYMENT_CODE_INTEGRAL);
        }

        // 收货地址信息设置
        order.setName(address.getmemberName());
        order.setProvinceId(address.getProvinceId());
        order.setCityId(address.getCityId());
        order.setAreaId(address.getAreaId());
        order.setAddressAll(address.getAddAll());
        order.setAddressInfo(address.getAddressInfo());
        order.setMobile(address.getMobile());
        order.setEmail(address.getEmail());
        order.setZipCode(address.getZipCode());

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        order.setLogisticsId(0);
        order.setLogisticsName("");
        order.setLogisticsNumber("");

        // 是否货到付款订单0、不是；1、是
        order.setIsCodconfim(Orders.IS_CODCONFIM_0);
        // 货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
        order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);
        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setEvaluateState(Orders.EVALUATE_STATE_1);

        /**
         * 金额的计算
         */

        // 金额信息

        // 商品总额
        BigDecimal moneyProduct = new BigDecimal(integral);

        order.setMoneyProduct(moneyProduct);
        // 活动id（与订单类型对应），积分换购订单存积分换购活动表ID
        order.setActivityId(actIntegral.getId());

        // 物流费用
        order.setMoneyLogistics(transFee);
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(new BigDecimal(0));

        // 优惠券优惠金额、优惠券ID（coupon_user的ID），大活动不能使用优惠券
        order.setMoneyCoupon(new BigDecimal(0));
        order.setCouponUserId(0);
        // 订单满减金额，大活动不参加满减
        order.setMoneyActFull(BigDecimal.ZERO);
        order.setActFullId(0);
        // 优惠金额总额（满减、立减、优惠券和），大活动不参加活动、不使用优惠券
        order.setMoneyDiscount(BigDecimal.ZERO);
        // 新订单退款的金额为0
        order.setMoneyBack(new BigDecimal(0));

        // 订单使用积分支付

        // 积分支付金额与商品总额相等
        order.setMoneyIntegral(orderCommitVO.getMoney());
        order.setIntegral(orderCommitVO.getIntegral());
        order.setSpecialIntegral(orderCommitVO.getSpecialIntegral());

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        order.setMoneyOrder(moneyProduct);


        order.setPaymentStatus(Orders.PAYMENT_STATUS_0);
        order.setOrderState(Orders.ORDER_STATE_1);

        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        // 生成订单之后，占库存，增加销量
        Integer stockRow = this.updateIntegralStockAndActualSales(actIntegral.getId(),
                orderCommitVO.getNumber(), false);
        if (stockRow == 0) {
            throw new BusinessException("修改商品库存时失败，请重试！");
        }

        return order;
    }

    /**
     * 保存积分换购网单信息
     * @param order 订单
     * @param product 商品
     * @param goods 货品
     * @param actIntegral 积分换购
     * @param seller 商家
     * @param number 数量
     */
    private void saveOrderProductInfoForActIntegral(Orders order, Product product,
                                                    ProductGoods goods, ActIntegral actIntegral,
                                                    Seller seller, Integer number, Integer integral,
                                                    Config config) {

        //保存网单信息
        OrdersProduct op = new OrdersProduct();
        op.setOrdersId(order.getId());
        op.setOrdersSn(order.getOrderSn());
        op.setSellerId(seller.getId());
        op.setProductCateId(product.getProductCateId());
        op.setProductId(product.getId());
        op.setProductGoodsId(goods.getId());
        op.setSpecInfo(goods.getNormName());
        op.setProductName(product.getName1());
        op.setProductSku(goods.getSku());
        op.setPackageGroupsId(0);
        op.setMallGroupsId(0);
        op.setGiftId(0);
        op.setIsGift(OrdersProduct.IS_GIFT_0);

        //设置商品单价为 PC价格
        op.setMoneyPrice(product.getMallPcPrice());

        // 网单金额
        op.setMoneyAmount(order.getMoneyOrder());

        op.setActGroupId(0);
        op.setActFlashSaleId(0);
        op.setActFlashSaleProductId(0);
        op.setActBiddingId(0);
        op.setActSpecialIntegral(order.getSpecialIntegral());
        op.setActIntegralId(actIntegral.getId());
        op.setActIntegralNum(integral);
        op.setActMoney(order.getMoneyIntegral());
        op.setNumber(number);

        // 立减优惠金额和，不参加活动
        op.setMoneyActSingle(BigDecimal.ZERO);
        op.setActSingleId(0);
        op.setBackNumber(0);
        op.setExchangeNumber(0);
        op.setIsEvaluate(OrdersProduct.IS_EVALUATE_0);

        // 1、保存网单
        int count = ordersProductWriteDao.insert(op);
        if (count == 0) {
            throw new BusinessException("网单保存失败，请重试！");
        }
    }


    /**
     * 用户福利积分提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForWelfare(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } /* else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
             log.error("订单提交信息中支付方式为空。");
             throw new BusinessException("订单提交信息中支付方式为空，请重试！");
          }*/

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null
            && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }



        Member member = memberWriteDao.get(orderCommitVO.getMemberId());

        orderCommitVO.setPaymentCode(Orders.PAYMENT_CODE_INTEGRAL);
        orderCommitVO.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            orderSuccVO.setPayAmount(BigDecimal.ZERO);
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            Config config = configReadDao.get(ConstantsEJS.CONFIG_ID);

            if (config == null) {
                throw new BusinessException("积分转换金额失败，请联系系统管理员！");
            }

            // 获取地址
            MemberAddress address = memberAddressWriteDao.get(orderCommitVO.getAddressId());

            CartInfoVO cartInfoVO = cartModel.getCartInfoByMId(orderCommitVO.getMemberId(), address,
                    orderCommitVO.getSource(), 2,Cart.SOURCE_2);

            if (cartInfoVO != null && cartInfoVO.getCartListVOs().size() > 0) {

                List<Orders> orderList = new ArrayList<Orders>();
                // 所有订单总金额（用于计算每个订单分摊的积分金额）
                BigDecimal allMoneyOrder = BigDecimal.ZERO;
                // 获取支付订单号
                // 支付订单号规则：下单时存入的支付订单号为paySn+本次下单第一个订单ID的后6位
                // 如果从订单中心点击支付，则修改支付订单号与订单号一样（所以支付订单号可能有的位数比其他订单的位数少6位）
                String paySn = RandomUtil.getOrderSn();
                // 记录是否是本地下单的第一个订单，如果是需要修改该订单的支付订单号
                boolean isFirstOrder = true;
                // 循环各个商家，每个商家是一个订单

                // 订单使用专项积分的总和
                Integer memberSpecialIntegral = 0;
                for (CartListVO cartListVO : cartInfoVO.getCartListVOs()) {

                    List<Cart> cartList = cartListVO.getCartList();
                    if (cartList != null && cartList.size() > 0) {
                        Seller seller = cartListVO.getSeller();
                        if (seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
                            throw new BusinessException(
                                    "商家[" + seller.getSellerName() + "]已被冻结，请把该商家的商品移出购物车后再下单，谢谢！");
                        }

                        BigDecimal sellerMoney = BigDecimal.ZERO;

                        for(Cart cart : cartList) {		//循环购物车 验证积分换购活动的规则
                            // 获取积分换购活动
                            ActIntegral actIntegral = actIntegralReadDao.get(cart.getActIntegralId());
                            this.checkActIntegralEffect(actIntegral);
                            // 校验渠道
                            if (actIntegral.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
                                if (actIntegral.getChannel().intValue() != channel) {
                                    String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                                    throw new BusinessException("积分换购活动【"+actIntegral.getName()+"】不能在" + channelName + "下单。");
                                }
                            }
                            // 校验库存
                            if (actIntegral.getStock() < cart.getCount()) {
                                throw new BusinessException("对不起，积分换购活动【"+actIntegral.getName()+"】库存不足了，请修改购买数量后再下单！");
                            }
                            // 单次购买限制
                            if (cart.getCount() > actIntegral.getPurchase()) {
                                throw new BusinessException("对不起，积分换购活动【"+actIntegral.getName()+"】单次最多购买" + actIntegral.getPurchase() + "个该商品！");
                            }
                            // 会员等级限制
                            // 获取提交订单的用户
                            if (member.getGrade() < actIntegral.getGradeValue()) {
                                throw new BusinessException("对不起，您的会员等级不能换购"+actIntegral.getName()+"的商品，谢谢。");
                            }
                            //重新计算商家选中商品的价格
                            sellerMoney = sellerMoney.add(new BigDecimal(cart.getActIntegral().getPrice() * cart.getCount()));
                        }
                        cartListVO.setSellerCheckedAmount(sellerMoney);
                        orderSuccVO.setPayAmount(orderSuccVO.getPayAmount().add(sellerMoney));

                        // 积分换购只能使用积分支付，所以使用积分数量根据积分换购活动计算
                        Integer integral = sellerMoney.intValue();
                        //单个订单可使用的专项积分
                        Integer specialIntegral = getMemberSpecialIntegralByMemberId(orderCommitVO.getMemberId(),seller.getId());

                        if((null == member.getIntegral() || member.getIntegral()  == 0) && specialIntegral <= 0) {
                            throw new BusinessException("积分不足  无法购买积分商品！");
                        }

                        orderCommitVO.setMoney(BigDecimal.ZERO);
                        if((specialIntegral + member.getIntegral())  >= integral) {		//判断专项积分和通用积分 是否足够支付
                            if(specialIntegral > integral) {
                                memberSpecialIntegral += integral;
                                orderCommitVO.setSpecialIntegral(integral);	//设置使用的专项积分
                                orderCommitVO.setIntegral(0);
                            }else {
                                Integer differ = integral - specialIntegral;
                                orderCommitVO.setSpecialIntegral(specialIntegral);	//设置使用的专项积分
                                orderCommitVO.setIntegral(differ);					//设置使用的通用积分
                                memberSpecialIntegral += specialIntegral;
                            }
                        }else {
                            memberSpecialIntegral += specialIntegral;
                            orderCommitVO.setSpecialIntegral(specialIntegral);	//设置使用的专项积分
                            orderCommitVO.setIntegral(member.getIntegral());	//设置使用的通用积分
                            //积分支付还差的数量
                            Integer  differ = integral - specialIntegral - member.getIntegral();

                            //用户余额可以兑换的积分数量
                            BigDecimal  balanceIntegral = member.getBalance().multiply(new BigDecimal(config.getIntegralScale()));

                            integral = member.getIntegral();
                            BigDecimal money = new BigDecimal(differ).divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN);
                            orderCommitVO.setMoney(money);
                            orderSuccVO.setBanlancePayMoneyVO(money);

                        }
                        orderSuccVO.setIntegralPayMoneyVo(new BigDecimal(member.getIntegral()));


                        Orders order = this.saveOrderInfoForWelfare(paySn, cartListVO, member,
                                orderCommitVO,address);

                        orderList.add(order);

                        // 保存网单信息
                        this.saveOrderProductInfoForActIntegral(order, cartList, member,orderCommitVO,config);

                        if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                            // 记录是否已支付，返回判断跳往什么页面
                            orderSuccVO.setIsPaid(true);
                            orderSuccVO.setPaymentCode(Orders.PAYMENT_NAME_BALANCE);
                            orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                            orderSuccVO.setGoJumpPayfor(false);
                        }

                        // 是第一个订单则修改如下内容，不是第一个则不执行如下内容
                        if (isFirstOrder) {
                            // 修改标志位
                            isFirstOrder = false;
                            // 修改支付订单号（连接上第一个订单的ID后6位）
                            paySn = this.paySnJoinId(paySn, order.getId());
                            // 修改第一个订单的支付订单号
                            Integer updateRow = ordersWriteDao.updatePaySnById(order.getId(),
                                    paySn);
                            if (updateRow == 0) {
                                log.error("修改第一个订单的支付订单号时失败。");
                                throw new BusinessException("订单提交出现异常，请重试！");
                            }
                        }
                    }
                }

                //删除购物车数据，不判断删除的成功与否，购物车的数据不影响，只打日志，不抛异常
                int count = cartWriteDao.deleteByMemberId(member.getId(),Cart.SOURCE_2);
                if (count == 0) {
                    log.error("删除购物车失败！");
                }

                // 如果订单还没有支付
                // 循环判断订单应支付的金额，如果应支付金额小于等于0则修改订单的状态为支付状态
                if (!orderSuccVO.getIsPaid()) {
                    // 返回true说明修改了支付状态
                    if (this.checkNeedPay(orderList, member)) {
                        orderSuccVO.setIsPaid(true);
                        orderSuccVO.setGoJumpPayfor(false);
                    }
                }
                //BigDecimal orderMoney = this.orderMoneyAllsFormWelfare(orderList);
                orderSuccVO.setSpecialIntegralPay(memberSpecialIntegral);
                orderSuccVO.setOrdersList(orderList);
                orderSuccVO.setPaySn(paySn);

            } else {
                throw new BusinessException("购物车中无商品，请选择购买的商品。");
            }

            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 保存积分换购订单，以及订单日志
     * @param seller 商家信息
     * @param actInteral 积分换购活动
     * @param transFee 运费
     * @param member 会员
     * @param orderCommitVO 订单提交信息
     * @param address 地址
     * @param integral 积分
     * @param config 积分配置
     * @return
     */
    private Orders saveOrderInfoForWelfare(String paySn, CartListVO cartListVO, Member member,
                                           OrderCommitVO orderCommitVO, MemberAddress address) {

        Seller seller = cartListVO.getSeller();
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        // 设定支付订单号
        order.setPaySn(paySn);
        // 设为普通订单
        order.setOrderType(Orders.ORDER_TYPE_6);
        order.setOrderSn(orderSn);

        order.setIsWelfareOrder(Product.IS_WELFARE_2); //设置为普通订单 或福利订单
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        if(orderCommitVO.getMoney().compareTo(BigDecimal.ZERO) > 0) {
        	order.setPaymentName(Orders.PAYMENT_NAME_INTEGRALGROUP);
            order.setPaymentCode(Orders.PAYMENT_CODE_INTEGRALGROUP);
        }else {
        	order.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);
            order.setPaymentCode(Orders.PAYMENT_CODE_INTEGRAL);
        }

        // 收货地址信息设置
        order.setName(address.getmemberName());
        order.setProvinceId(address.getProvinceId());
        order.setCityId(address.getCityId());
        order.setAreaId(address.getAreaId());
        order.setAddressAll(address.getAddAll());
        order.setAddressInfo(address.getAddressInfo());
        order.setMobile(address.getMobile());
        order.setEmail(address.getEmail());
        order.setZipCode(address.getZipCode());

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        order.setLogisticsId(0);
        order.setLogisticsName("");
        order.setLogisticsNumber("");

        // 是否货到付款订单，根据payMentCode判断
        order.setIsCodconfim(Orders.IS_CODCONFIM_0);
        order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);

        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        /**
         * 金额的计算
         */

        // 金额信息

        // 商品总额（只是商品价格*数量的金额之和）
        order.setMoneyProduct(cartListVO.getSellerCheckedAmount());
        // 判断物流费用
        order.setMoneyLogistics(cartListVO.getSellerLogisticsFee());
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(new BigDecimal(0));

        order.setMoneyIntegral(orderCommitVO.getMoney());
        order.setIntegral(orderCommitVO.getIntegral());
        order.setSpecialIntegral(orderCommitVO.getSpecialIntegral());
        // 优惠券优惠金额、优惠券ID（coupon_user的ID）
        order.setMoneyCoupon(new BigDecimal(0));
        order.setCouponUserId(0);

        // 订单满减金额
        if (cartListVO.getOrderDiscount() != null
            && cartListVO.getOrderDiscount().compareTo(BigDecimal.ZERO) > 0) {
            order.setMoneyActFull(cartListVO.getOrderDiscount());
            order.setActFullId(cartListVO.getActFull().getId());
        } else {
            order.setMoneyActFull(BigDecimal.ZERO);
            order.setActFullId(0);
        }
        // 活动id（与订单类型对应），比如团购时存团购的ID，此处为普通订单，存0
        order.setActivityId(0);
        // 优惠金额总额（满减、立减、优惠券和）
        // 如果从购物车中计算出的优惠总额为空，则赋0，然后加上商家优惠券的额度，计算出所有的优惠总额
        BigDecimal moneyDiscount = cartListVO.getSellerCheckedDiscounted() == null ? BigDecimal.ZERO
                : cartListVO.getSellerCheckedDiscounted();
        // 现在是单品立减、订单满减、商家优惠券三个优惠，以后如果加其他优惠政策则加上新优惠的金额
        order.setMoneyDiscount(moneyDiscount.add(order.getMoneyCoupon()));
        // 新订单退款的金额为0
        order.setMoneyBack(new BigDecimal(0));
        order.setEvaluateState(Orders.EVALUATE_STATE_1);

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        order.setMoneyOrder(order.getMoneyProduct());

        if (order.getMoneyOrder().compareTo(new BigDecimal(0)) <= 0) {
            // 如果订单金额为0 直接认为已付款
            order.setOrderState(Orders.ORDER_STATE_3);
            order.setPayTime(new Date());
            order.setPaymentStatus(Orders.PAYMENT_STATUS_1);
        }

        // 如果不是货到付款
        order.setOrderState(Orders.ORDER_STATE_1);

        order.setPaymentStatus(Orders.PAYMENT_STATUS_0);


        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        
        List<Cart> cartList = cartListVO.getCartList();
        for(Cart cart : cartList) {
        	// 获取积分换购活动
            ActIntegral actIntegral = actIntegralReadDao.get(cart.getActIntegralId());
            // 生成订单之后，占库存，增加销量
            Integer stockRow = this.updateIntegralStockAndActualSales(actIntegral.getId(),
                    cart.getCount(), false);
            if (stockRow == 0) {
                throw new BusinessException("修改商品库存时失败，请重试！");
            }
        }

        return order;
    }

    /**
     * 保存积分换购网单信息
     * @param order 订单
     * @param product 商品
     * @param goods 货品
     * @param actIntegral 积分换购
     * @param seller 商家
     * @param number 数量
     */
    private void saveOrderProductInfoForActIntegral(Orders order, List<Cart> cartList, Member member,OrderCommitVO orderCommitVO,Config config) {

        // 订单使用的专项积分总数
        BigDecimal specialIntegral = new BigDecimal(orderCommitVO.getSpecialIntegral());
        // 订单使用积分总数
        BigDecimal integral = new BigDecimal(orderCommitVO.getIntegral());
        //订单使用余额总数
        BigDecimal money 	= orderCommitVO.getMoney();


        for (Cart cart : cartList) {
            ProductGoods goods = cart.getProductGoods();
            Product product = cart.getProduct();
            // 判断商品状态
            if (product.getState().intValue() != Product.STATE_6) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断分类状态
            if (product.getProductCateState().intValue() != Product.PRODUCT_CATE_STATE_1) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断库存
            if (product.getSource() == 1 && (goods.getProductStock() <= 0 || goods.getProductStock() < cart.getCount())) {
                throw new BusinessException(
                        "商品[" + product.getName1() + " " + cart.getSpecInfo() + "]的库存不足，请重新选择商品！");
            }
            // 货品状态异常或未启用的，不能下单
            if (goods.getState() == null || goods.getState().intValue() == ProductGoods.DISABLE) {
                String productName = product.getName1();
                if (!StringUtil.isEmpty(goods.getNormName(), true)) {
                    productName += " " + goods.getNormName();
                }
                throw new BusinessException("商品[" + productName + "]已下架，不能下单！");
            }

            //保存网单信息
            OrdersProduct op = new OrdersProduct();
            op.setOrdersId(order.getId());
            op.setOrdersSn(order.getOrderSn());
            op.setSellerId(cart.getSellerId());
            op.setProductCateId(product.getProductCateId());
            op.setProductId(product.getId());
            op.setProductGoodsId(cart.getProductGoodsId());
            op.setSpecInfo(cart.getSpecInfo());
            op.setProductName(product.getName1());
            op.setProductSku(goods.getSku());
            op.setPackageGroupsId(0);
            op.setMallGroupsId(0);
            op.setGiftId(0);
            op.setIsGift(OrdersProduct.IS_GIFT_0);
            // 根据来源确定使用PC价格或者移动端价格，默认使用PC端价格
            BigDecimal price = goods.getMallPcPrice();
            if (order.getSource() != null
                && (order.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                    || order.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID)
                    || order.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
                price = goods.getMallMobilePrice();
            }
            op.setMoneyPrice(price);
            op.setNumber(cart.getCount());
            // 网单金额（减去立减优惠后的金额和）
            op.setMoneyAmount(cart.getCurrDiscountedAmount());
            // 立减优惠金额和
            if (cart.getCurrDiscounted() != null
                && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                op.setMoneyActSingle(cart.getCurrDiscounted());
                op.setActSingleId(cart.getActSingle().getId());
            } else {
                op.setMoneyActSingle(BigDecimal.ZERO);
                op.setActSingleId(0);
            }
            op.setActGroupId(0);
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(0);

            op.setActIntegralId(0);
            if(cart.getSource() == Cart.SOURCE_2) {
                op.setActIntegralId(cart.getActIntegralId());
            }



            //优先使用专项积分
            if(specialIntegral.compareTo(cart.getCurrDiscountedAmount()) > 0) {
                op.setActSpecialIntegral(cart.getCurrDiscountedAmount().intValue());
                specialIntegral = specialIntegral.subtract(cart.getCurrDiscountedAmount());
                op.setActMoney(BigDecimal.ZERO);
                op.setActIntegralNum(BigDecimal.ZERO.intValue());
            }else {
                // 使用了专项积分后的总价
                BigDecimal differ = cart.getCurrDiscountedAmount().subtract(specialIntegral);

                op.setActSpecialIntegral(specialIntegral.intValue());
                specialIntegral = BigDecimal.ZERO;

                //优先使用积分 如果积分足够
                if(integral.compareTo(differ) > 0) {
                    op.setActIntegralNum(differ.intValue());
                    op.setActMoney(BigDecimal.ZERO);
                    integral =  integral.subtract(differ);
                }else {
                    differ = (differ.subtract(integral)).divide(new BigDecimal(config.getIntegralScale()), 2, BigDecimal.ROUND_HALF_DOWN);;

                    op.setActMoney(differ);
                    money = money.subtract(differ);

                    op.setActIntegralNum(integral.intValue());
                    integral = BigDecimal.ZERO;
                }
            }

            op.setBackNumber(0);
            op.setExchangeNumber(0);
            op.setIsEvaluate(OrdersProduct.IS_EVALUATE_0);

            // 1、保存网单
            int count = ordersProductWriteDao.insert(op);
            if (count == 0) {
                throw new BusinessException("网单保存失败，请重试！");
            }
            //支付时减库存修改为下单即减库存，旧代码注释，但保留，客户可根据需要还原或删除
            //            // 更新商品的销量
            //            this.updateProductActualSales(cart.getProductId(), cart.getProductGoodsId(),
            //                cart.getCount());
            // 修改库存和销量
            this.updateProductActualSalesAndStock(cart.getProductId(), cart.getProductGoodsId(),
                    cart.getCount(), false);

        }
    }

    /**
     * 积分换购活动有效性校验
     * @param actIntegral
     */
    private void checkActIntegralEffect(ActIntegral actIntegral) {
        if (actIntegral.getTypeState() == null
            || actIntegral.getTypeState() != ActIntegral.TYPE_STATE_1) {
            log.error("积分换购活动" + actIntegral.getName() + "的分类状态为不显示，下单失败。");
            throw new BusinessException("对不起，该积分换购活动已下线！");
        }
        if (actIntegral.getState() == null || actIntegral.getState() != ActIntegral.STATE_3) {
            throw new BusinessException("对不起，积分换购活动不存在！");
        }
        if (actIntegral.getActivityState() == null
            || actIntegral.getActivityState() != ActIntegral.ACTIVITY_STATE_2) {
            throw new BusinessException("对不起，该积分换购活动还没有发布！");
        }
        Date date = new Date();
        if (date.before(actIntegral.getStartTime())) {
            throw new BusinessException("对不起，该积分换购活动还没有开始！");
        }
        if (date.after(actIntegral.getEndTime())) {
            throw new BusinessException("对不起，该积分换购活动已结束！");
        }
        if (actIntegral.getStock() < 1) {
            throw new BusinessException("对不起，该商品已经被抢光了！");
        }
    }

    // ------------------------积分商城结束-------------------------------------------------------------------------------------

    public Integer getReconfOrdersCount() {
        Map<String, String> param = new HashMap<>();
        param.put("q_orderState", "2");

        //        String day = TimeUtil.getToday();
        //        param.put("startTime", day + " 00:00:00");
        //        param.put("endTime", day + " 23:59:59");

        Integer res = ordersReadDao.getOrdersCount(param);
        return res;
    }

    /**
     * 查看待评价订单数量
     * @param memberId
     * @return
     */
    public Integer getOrderNumByMIdAndEvaluateState(Integer memberId) {
        return ordersReadDao.getNumByMIdAndEvaluateState(memberId);
    }

    /**
     * 取消订单还原库存和实际销量公共方法
     * @param orderType
     * @param ordersId
     */
    private void cancelOrderBackStockAndActualSales(Integer orderType, Integer ordersId) {
        //还原库存和实际销量
        List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersId);
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            // 限时抢购订单时还原活动商品的库存
            // 限时抢购只有一个网单
            this.updateFlashProductStockAndActualSales(opList.get(0).getActFlashSaleProductId(),
                    opList.get(0).getNumber(), true);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_3) {
            // 团购订单时还原活动商品的库存
            // 团购只有一个网单
            this.updateGroupStockAndActualSales(opList.get(0).getActGroupId(),
                    opList.get(0).getNumber(), true);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_4) {
            // 集合竞价订单时还原活动商品的库存
            // 竞价定金订单时不会进入此逻辑，因为定金订单支付后订单状态直接设置为已完成
            this.updateBiddingStockAndActualSales(opList.get(0).getActBiddingId(),
                    opList.get(0).getNumber(), true);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_5) {
            // 竞价尾款订单时不会进入此逻辑，因为尾款订单不能取消
            // 此处为逻辑清晰、代码易读增加此逻辑
        } else if (orderType.intValue() == Orders.ORDER_TYPE_6) {
            // 积分换购订单时还原活动商品的库存
            // 积分换购只有一个网单
            this.updateIntegralStockAndActualSales(opList.get(0).getActIntegralId(),
                    opList.get(0).getNumber(), true);
        } else {
            // 普通订单
            // 更新货品和商品的库存
            if (opList != null && opList.size() > 0) {
                for (OrdersProduct op : opList) {
                    this.updateProductActualSalesAndStock(op.getProductId(), op.getProductGoodsId(),
                            op.getNumber(), true);
                }
            }
        }
    }

    /**
     * 根据商品ID和货品ID更新商品货品的实际销量和库存
     * @param productId
     * @param productGoodsId
     * @param number
     * @param isCancelOrders 是否是取消订单操作
     */
    private void updateProductActualSalesAndStock(Integer productId, Integer productGoodsId,
                                                  Integer number, boolean isCancelOrders) {

        // 如果是取消订单，则将数量变为负数
        if (isCancelOrders) {
            number = 0 - number;
        }
        // 修改商品库存和销量 累加或着累减
        int pcount = productWriteDao.updateActualSalesAndStock(productId, number);
        if (pcount == 0) {
            log.error("商品库存和实际销量更新失败。");
            throw new BusinessException("商品库存和销量更新失败！");
        }

        // 修改货品库存和销量 累加或者累减
        pcount = productGoodsWriteDao.updateActualSalesAndStock(productGoodsId, number);
        if (pcount == 0) {
            log.error("修改货品库存和销量失败。");
            throw new BusinessException("货品库存和销量更新失败！");
        }
    }

    /**
     * 修改限时抢购活动商品的库存和销量
     * @param actFlashSaleProductId
     * @param saleNum 修改数量
     * @param isCancelOrders 是否是取消订单操作
     */
    private Integer updateFlashProductStockAndActualSales(Integer actFlashSaleProductId,
                                                          Integer saleNum, Boolean isCancelOrders) {
        //如果是取消订单，则将数量变为负数
        if (isCancelOrders) {
            saleNum = 0 - saleNum;
        }
        return actFlashSaleProductWriteDao.updateStockAndActualSales(actFlashSaleProductId,
                saleNum);
    }

    /**
     * 修改团购活动商品的库存和销量
     * @param actGroupId
     * @param number
     * @param isCancelOrders 是否是取消订单操作
     */
    private Integer updateGroupStockAndActualSales(Integer actGroupId, Integer number,
                                                   Boolean isCancelOrders) {
        if (isCancelOrders) {
            number = 0 - number;
        }
        return actGroupWriteDao.updateStockAndActualSales(actGroupId, number);
    }

    /**
     * 修改集合竞价活动商品的库存和销量
     * @param actBiddingId
     * @param number
     * @param isCancelOrders 是否是取消订单操作
     */
    private Integer updateBiddingStockAndActualSales(Integer actBiddingId, Integer number,
                                                     Boolean isCancelOrders) {
        //如果是取消订单，则将数量变为负数
        if (isCancelOrders) {
            number = 0 - number;
        }
        return actBiddingWriteDao.updateStockAndActualSales(actBiddingId, number);
    }

    /**
     * 修改积分换购活动商品的库存和销量
     * @param actIntegralId
     * @param number
     * @param isCancelOrders 是否是取消订单操作
     */
    private Integer updateIntegralStockAndActualSales(Integer actIntegralId, Integer number,
                                                      Boolean isCancelOrders) {
        //如果不是取消订单，则将数量改为负
        if (isCancelOrders) {
            number = 0 - number;
        }
        return actIntegralWriteDao.updateStockAndActualSales(actIntegralId, number);
    }

    /**
     * 根据订单号发货
     * @param ccName
     * @param ccId
     * @param source
     * @param supplierId
     * @return
     */
    public Integer doDelivery(Orders orders, Orders ordersDb, SellerUser sellerUser) {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            //1.更新订单
            result = ordersWriteDao.update(orders);
            Orders ordersDB = ordersWriteDao.get(orders.getId());
            //2.写订单日志
            writeOrderInfo(ordersDB, sellerUser, Orders.DELIVER);
            //在发货单中插入一条已经发货的发货单数据
            OrderDelivery orderDelivery = new OrderDelivery();
            orderDelivery.setOrderSn(ordersDb.getOrderSn());
            orderDelivery.setDeliverySn(RandomUtil.getOrderDeliverySn());
            orderDelivery.setInvoiceStatus(ordersDb.getInvoiceStatus());
            orderDelivery.setInvoiceTitle(ordersDb.getInvoiceTitle());
            orderDelivery.setMemberName(ordersDb.getName());
            orderDelivery.setMemberPhone(ordersDb.getMobile());
            orderDelivery.setOrderTime(ordersDb.getCreateTime());
            orderDelivery.setReceivingAddress(ordersDb.getAddressAll()+ordersDb.getAddressInfo());
            orderDelivery.setSellerId(ordersDb.getSellerId());
            orderDelivery.setState(2);
            orderDelivery.setUpdateTime(new Date());

            //根据订单ID获取订单详情，在根据订单详情中的第一个产品ID获取供应商ID ，因为此处供应商是一直的
            orderDelivery.setSupplierId(productReadDao.get(ordersProductReadDao.getByOrderId(orders.getId()).get(0).getProductId()).getSupplierId());
            orderDelivery.setLogistics(orders.getLogisticsId());
            orderDelivery.setLogisticsName(orders.getLogisticsName());
            orderDelivery.setWaybillNumber(orders.getLogisticsNumber());
            deliveryWriteDao.insertOrderDelivery(orderDelivery);

            //发送消息到用户
            Map<String,Object> queryMap = new HashMap<String,Object>();
            queryMap.put("time", null == ordersDB.getPayTime() ? ordersDB.getCreateTime(): ordersDB.getPayTime());
            queryMap.put("orderSn", ordersDB.getOrderSn());
            Boolean flag = messageModel.sendMessageToMember(queryMap, ordersDB.getMemberId(), Message.DDFHTZ);
            if(!flag) {
                throw new BusinessException("发送消息到用户失败");
            }

            transactionManager.commit(status);
        }catch(Exception e){
            transactionManager.rollback(status);
            log.error("订单发货失败："+e.getMessage());
            throw new BusinessException("订单发货失败。！");
        }
        return result;

    }

    /**
     * 根据订单号验证发货
     * @param orderSn
     * @return
     */
    public Integer verifiDelivery(String orderSn){
        //定义插入发货单数据集合
        List<OrderDelivery> orderDeliverys = new ArrayList<OrderDelivery>();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{

            //首先判断是否已经生成了发货数据
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("q_orderSn", orderSn);
            int count = deliveryReadDao.getPageCount(queryMap);
            if(count >0){
                return 4;
            }


            int result = ordersReadDao.getVerifiDeliveryCount(orderSn);


            if(result>0){
                //如果有则通过查询出来的信息生成发货单
                List<OrderDeliveryDto> list = ordersReadDao.getVerifiDelivery(orderSn);
                int supplierId = 0;
                for (int i = 0; i < list.size(); i++) {
                    OrderDeliveryDto deliveryDto = list.get(i);
                    if(i==0){
                        supplierId = deliveryDto.getSupplierId();
                    }else{
                        if(supplierId == deliveryDto.getSupplierId()){
                            continue;
                        }else{
                            supplierId = deliveryDto.getSupplierId();
                        }
                    }
                    OrderDelivery orderDelivery = new OrderDelivery();
                    orderDelivery.setOrderSn(deliveryDto.getOrderSn());
                    orderDelivery.setState(1);
                    orderDelivery.setSupplierId(supplierId);
                    orderDelivery.setInvoiceStatus(deliveryDto.getInvoiceStatus());
                    orderDelivery.setInvoiceTitle(deliveryDto.getInvoiceTitle());
                    orderDelivery.setReceivingAddress(deliveryDto.getAddress());
                    orderDelivery.setMemberName(deliveryDto.getName());
                    orderDelivery.setMemberPhone(deliveryDto.getMobile());
                    orderDelivery.setOrderTime(deliveryDto.getOrderTime());
                    orderDelivery.setDeliverySn(RandomUtil.getOrderDeliverySn());
                    orderDelivery.setSellerId(deliveryDto.getSellerId());
                    //orderDeliverys.add(orderDelivery);
                    deliveryWriteDao.insertOrderDelivery(orderDelivery);
                }
                //orderDeliveryWriteDao.batchInsertOrderDelivery(list);
                transactionManager.commit(status);
                return 1;
            }else{
                transactionManager.commit(status);
                //如果没有则返回
                return 0;
            }
        }catch(Exception e){
            log.error("更新订单时出现未知异常：" + e);
            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 处理京东推送过来的订单相关消息
     * @param token 访问京东的令牌
     * @param messageList 消息集合
     * @return 处理是否成功
     */
    public Boolean handleJDOrderMessage(AccessToken token ,Map<Integer,List<JDMessage>> messageList) throws Exception{

        if(messageList == null || messageList.size() == 0){
            //未收到消息，默认处理成功
            return true ;
        }

        //订单拆分变更
        this.doJDOrderSplit(token, messageList.get(JDMessageType.ORDER_SPLIT));

        //代表订单取消（不区分取消原因）
        this.doJDOrderCancel(token, messageList.get(JDMessageType.ORDER_CANCEL));

        //7天未支付取消消息/未确认取消
        this.doJDOrderAutoCancel(token, messageList.get(JDMessageType.ORDER_AUTO_CANCEL));

        return true ;
    }

    /**
     * 处理订单拆分变更
     * @param token 访问京东的令牌
     * @param splitList
     * @throws Exception
     */
    private void doJDOrderSplit(AccessToken token ,List<JDMessage> splitList) throws Exception{
        //============TODO===========
    }

    /**
     * 处理订单取消（不区分取消原因）
     * @param token 访问京东的令牌
     * @param cancelList
     * @throws Exception
     */
    private void doJDOrderCancel(AccessToken token ,List<JDMessage> cancelList) throws Exception{
        if(cancelList!=null && cancelList.size() > 0){
            TransactionStatus status = null;
            DefaultTransactionDefinition def = null;
            try {
                //定义事务
                def = new DefaultTransactionDefinition();;
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                status = transactionManager.getTransaction(def);

                for(JDMessage message : cancelList){
                    JSONObject result = message.getResult() ;
                    if(result == null ){
                        continue ;
                    }
                    String jdOrderId = result.getString("orderId");
                    if(StringUtils.isEmpty(jdOrderId)){
                        continue ;
                    }
                    //根据京东查找当前订单id

                }

                //提交事务
                transactionManager.commit(status);
            } catch (Exception e) {
                if(status != null){
                    //如有异常，回滚事务
                    transactionManager.rollback(status);
                }
                //异常抛给业务方处理
                throw e ;
            }
        }
    }

    /**
     * 处理订单7天未支付取消消息/未确认取消
     * @param token 访问京东的令牌
     * @param autoList
     * @throws Exception
     */
    private void doJDOrderAutoCancel(AccessToken token ,List<JDMessage> autoList) throws Exception{

    }


}
