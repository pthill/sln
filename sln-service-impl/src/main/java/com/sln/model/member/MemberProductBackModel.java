package com.sln.model.member;

import com.alibaba.fastjson.JSONObject;
import com.sln.core.ConvertUtil;
import com.sln.core.PagerInfo;
import com.sln.core.RandomUtil;
import com.sln.core.StringUtil;
import com.sln.core.TimeUtil;
import com.sln.core.email.MailSenderInfo;
import com.sln.core.email.SendMail;
import com.sln.core.exception.BusinessException;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.*;
import com.sln.core.jd.util.JDApiResult;
import com.sln.dao.shop.read.coupon.CouponUserReadDao;
import com.sln.dao.shop.read.member.MemberGradeIntegralLogsReadDao;
import com.sln.dao.shop.read.member.MemberProductBackReadDao;
import com.sln.dao.shop.read.member.MemberSpecialIntegralReadDao;
import com.sln.dao.shop.read.order.OrdersProductReadDao;
import com.sln.dao.shop.read.order.OrdersReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.read.supplier.SupplierReturnReadDao;
import com.sln.dao.shop.write.coupon.CouponOptLogWriteDao;
import com.sln.dao.shop.write.coupon.CouponUserWriteDao;
import com.sln.dao.shop.write.member.*;
import com.sln.dao.shop.write.order.OrdersProductWriteDao;
import com.sln.dao.shop.write.order.OrdersWriteDao;
import com.sln.dao.shop.write.supplier.SupplierReturnWriteDao;
import com.sln.dao.shop.write.system.CodeWriteDao;
import com.sln.entity.coupon.CouponOptLog;
import com.sln.entity.coupon.CouponUser;
import com.sln.entity.member.*;
import com.sln.entity.message.Message;
import com.sln.entity.order.Orders;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.product.Product;
import com.sln.entity.supplier.SupplierReturn;
import com.sln.entity.system.Code;
import com.sln.model.message.MessageModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员退货管理
 *
 */
@Component(value = "memberProductBackModel")
public class MemberProductBackModel {
    private static Logger                   log = LogManager
        .getLogger(MemberProductBackModel.class);
    @Resource
    private MemberProductBackWriteDao       memberProductBackWriteDao;
    @Resource
    private MemberProductBackReadDao        memberProductBackReadDao;
    @Resource
    private MemberProductExchangeWriteDao   memberProductExchangeWriteDao;
    @Resource
    private OrdersReadDao                   ordersReadDao;
    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private OrdersProductReadDao            ordersProductReadDao;
    @Resource
    private OrdersProductWriteDao           ordersProductWriteDao;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberBalanceLogsWriteDao       memberBalanceLogsWriteDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;
    @Resource
    private MemberGradeIntegralLogsReadDao  memberGradeIntegralLogsReadDao;
    @Resource
    private CouponUserReadDao               couponUserReadDao;
    @Resource
    private CouponUserWriteDao              couponUserWriteDao;
    @Resource
    private CouponOptLogWriteDao            couponOptLogWriteDao;
    @Resource
    private SupplierReturnWriteDao          supplierReturnWriteDao;
    @Resource
    private SupplierReturnReadDao          supplierReturnReadDao;
    @Resource
    private JobProductBackWriteDao         jobProductBackWriteDao;
    @Resource
    private CodeWriteDao                   codeWriteDao;
    @Resource
    private MessageModel 					messageModel;
    @Resource
    private MemberSpecialIntegralWriteDao   memberSpecialIntegralWriteDao;
    @Resource
    private MemberSpecialIntegralReadDao    memberSpecialIntegralReadDao;

    /**
     * 根据id取得用户退货
     * @param memberProductBackId
     * @return
     */
    public MemberProductBack getMemberProductBackById(Integer memberProductBackId) {
        MemberProductBack memberProductBack = memberProductBackWriteDao.get(memberProductBackId);
        memberProductBack
            .setProductName(productReadDao.get(memberProductBack.getProductId()).getName1());
        memberProductBack
            .setOrderSn(ordersReadDao.get(memberProductBack.getOrderId()).getOrderSn());
        return memberProductBack;
    }

    /**
     * 更新用户退货
     * @param memberProductBack
     * @return
     */
    /**
     * @param memberProductBack
     * @return
     */
    public Integer updateMemberProductBack(MemberProductBack memberProductBack) {
    	// 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        int result = 0;
    	try{
    		//判断如果是了审核通过退货则生成商家退货单
        	if(memberProductBack.getStateReturn()== MemberProductBack.STATE_RETURN_2){
    	    		//首先先查询出订单所有的商品
        		List<SupplierReturn> supplierReturns = supplierReturnReadDao.getSupplierReturnByOrderId(memberProductBack.getOrderId());
        			//然后在循环生成商家退货单
        		for (int i = 0; i < supplierReturns.size(); i++) {
    				SupplierReturn supplierReturn = new SupplierReturn();
    				//OrdersProduct ordersProduct = new OrdersProduct();
    				//ordersProduct = OrdersProductList.get(i);
    				supplierReturn = supplierReturns.get(i);
    				supplierReturn.setBackId(memberProductBack.getId());
    				supplierReturn.setReturnState(1);
    				supplierReturn.setReturnSn(RandomUtil.getSupplierReturnSn());
    				supplierReturnWriteDao.insertSupplierReturn(supplierReturn);
    			}
        		//批量插入供应商退货单
        		//supplierReturnWriteDao.batchInsertSupplierReturn(list);
        		 result = memberProductBackWriteDao.update(memberProductBack);

        	}else if(memberProductBack.getStateReturn() == MemberProductBack.STATE_RETURN_3){
        		//如果状态为确认收货状态则判断退货申请单下的供应商退货单是否全部都为商家供应

        		Map<String, String> queryMap = new HashMap<String, String>();
        		queryMap.put("backId", memberProductBack.getId()+"");
        		List<SupplierReturn> supplierReturnOne =  supplierReturnReadDao.getSupplierReturnByBckId(queryMap);
        		if(supplierReturnOne.size()<1){
        			return 0;
        		}
        		Boolean flg = true;
        		//循环判断是否都为商家供应
        		for (int i = 0; i < supplierReturnOne.size(); i++) {
					if(supplierReturnOne.get(i).getSupplierType() ==1){
						flg = false;
						break;
					}
				}


        		if(flg){
        			//如果都为商家供应则允许点击发货
        			//首先修改退货申请单
        			 result = memberProductBackWriteDao.update(memberProductBack);

        			if(result >0){
        				//然后根据退货申请单修改供应商退货单
        				result = supplierReturnWriteDao.updateSupplierReturnByBackId(2, memberProductBack.getId());
        				

        			}else{
        				transactionManager.rollback(status);
            			return 0;
        			}

        		}else{
        			transactionManager.rollback(status);
        			return 2;

        		}

        	}else{
        		 result = memberProductBackWriteDao.update(memberProductBack);
        	}
        	if(result >0){
        		MemberProductBack mpb = memberProductBackReadDao.get(memberProductBack.getId());
    			Orders order = ordersReadDao.get(mpb.getOrderId());
        		// 推送消息到用户
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("time", new Date());
                queryMap.put("orderSn", order.getOrderSn());
                
                boolean flag = false;
                if(memberProductBack.getStateReturn() == MemberProductBack.STATE_RETURN_3) {		//退货通过审核 卖家收货后推送消息
                	flag = messageModel.sendMessageToMember(queryMap, order.getMemberId(), Message.TKCGTZ);
                }else if(memberProductBack.getStateReturn() == MemberProductBack.STATE_RETURN_4) {	//退货失败
                	flag = messageModel.sendMessageToMember(queryMap, order.getMemberId(), Message.TKSBTZ);
                }
                
                if(!flag && memberProductBack.getStateReturn() != MemberProductBack.STATE_RETURN_2) {
                	throw new BusinessException("推送消息到用户失败。");
                }
                
                transactionManager.commit(status);
    			return 1;
    		}else{
    			transactionManager.rollback(status);
    			return 0;
    		}

    	}catch(Exception e){
    		log.error("更新用户退货单失败 state="+memberProductBack.getStateReturn());
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		throw new RuntimeException();
    	}
    }

    /**
     * 退货逻辑简略说明（详细请参考代码注释）<br>
     * 退货涉及退款主要影响字段：orders.money_paid_balance,orders.money_paid_reality,orders.money_integral,
     *                      orders_product.money_product,orders_product.money_act_single
     * <li>money_paid_balance：订单余额支付的金额
     * <li>money_paid_reality：订单现金支付的金额，退款时现金支付和余额支付部分无区别，都按照余额退到账户余额中
     * <li>money_integral：订单使用的积分转换后的金额，这部分金额只退回积分，不退余额，积分支付部分是在现金支付和余额支付部分金额退完之后再退积分；
     * <li>money_product：网单金额
     * <li>money_act_single：网单单品立减金额
     * 
     * <br>
     * <li>退款原则：
     * <li>1、退到余额的金额不能大于现金支付+余额支付之和，退回的积分不能大于integral值；<br>
     * <li>2、先退现金支付+余额支付部分，再退积分支付部分，最后退优惠券；<br>
     * <li>3、优惠券只有在退到最后一个商品的时候才会退回（因为优惠券不可拆分，所以即使优惠券的金额大于最后一个商品的金额也不会退）；<br>
     * <li>4、退款金额计算不考虑是否参加了满减活动；<br>
     * <li>5、退款金额按照最终支付金额乘以退货商品实际成交金额在整个订单的实际成交金额比例计算；
     * <li>6、运费不退；
     * <br>
     * <br>
     * 避免主从库数据同步延迟所有退货表的数据从写库读取
     * 
     * @param memberProductBack
     * @param request
     * @return
     */
    public boolean saveMemberProductBack(MemberProductBack memberProductBack, Member member) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 参数校验
            if (memberProductBack == null) {
                throw new BusinessException("退货申请不能为空，请重试！");
            } else if (memberProductBack.getProductId() == null
                       || memberProductBack.getProductId() == 0) {
                throw new BusinessException("退货申请产品id不能为空，请重试！");
            } else if (memberProductBack.getOrderProductId() == null
                       || memberProductBack.getOrderProductId() == 0) {
                throw new BusinessException("退货申请网单id不能为空，请重试！");
            } else if (memberProductBack.getOrderId() == null
                       || memberProductBack.getOrderId() == 0) {
                throw new BusinessException("退货申请订单id不能为空，请重试！");
            }
            if (memberProductBack.getNumber() < 1) {
                throw new BusinessException("退货数量必须大于0，请重新填写！");
            }

            
            // 已经申请过退货的退款金额和
            BigDecimal backedMoney = BigDecimal.ZERO;
            // 已经申请过退货的返回专项积分和
            Integer backSpecialIntegral = 0;
            // 已经申请过退货的返回积分和
            Integer backedIntegral = 0;
            // 已经申请过退货的返回积分金额
            BigDecimal backedIntegralMoney = BigDecimal.ZERO;
            // 已经返回的优惠券
            List<Integer> couponUserList = new ArrayList<Integer>();

            // 当前网单下已经退货或者换货的数量（该数量为本次退货的数量 + 已经退货或者换货的数量，不能大于网单的总数量）
            int opServicedNum = memberProductBack.getNumber();
            // 订单下已经退货的数量（判断是否是当前订单下最后一次退货）
            int orderBackedNum = 0;
            // 订单下商品总数量
            int orderNum = 0;
            // 订单下所有网单立减金额和（用于计算退货退款金额）
            BigDecimal singleSum = BigDecimal.ZERO;

            // 获取该订单下已经申请退货的退货表数据
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.clear();
            queryMap.put("memberId", member.getId());
            queryMap.put("orderId", memberProductBack.getOrderId());
            List<MemberProductBack> backedList = memberProductBackWriteDao.queryList(queryMap);
            
            //根据订单id取对应的订单信息 
            Orders order = ordersReadDao.get(memberProductBack.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            } else {
                int orderState = order.getOrderState();
                if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                    || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {
                    log.error("订单此时所处状态不允许提交退货申请。");
                    throw new BusinessException("订单此时所处状态不允许提交退货申请！");
                }
            }

            if (backedList.size() > 0) {
                for (MemberProductBack back : backedList) {
                    // 统计订单下已经退货的数量
                    orderBackedNum += back.getNumber();
                    
                    
                    if(order.getPaymentCode().equals(Orders.PAYMENT_CODE_INTEGRAL)) {
                    	// 如果网单ID等于本次退货的ID，则统计已经退货或者换货的数量
                        if (memberProductBack.getOrderProductId().equals(back.getOrderProductId())) {
                            opServicedNum += back.getNumber();
                            // 累计退款金额
                            backedMoney = backedMoney.add(back.getBackMoney());
                            // 累计退回的积分、积分金额
                            if (back.getBackIntegral() != null && back.getBackIntegral() > 0) {
                                backedIntegral = backedIntegral + back.getBackIntegral();
                                backedIntegralMoney = backedIntegralMoney.add(back.getBackIntegralMoney());
                            }
                            
                            if(back.getBackSpecialIntegral() != null && back.getBackSpecialIntegral() > 0 ) {
                            	backSpecialIntegral += back.getBackSpecialIntegral();
                            }
                        }
                        
                    }else {
                    	// 如果网单ID等于本次退货的ID，则统计已经退货或者换货的数量
                        if (memberProductBack.getOrderProductId().equals(back.getOrderProductId())) {
                            opServicedNum += back.getNumber();
                        }
                    	 // 累计退款金额
                        backedMoney = backedMoney.add(back.getBackMoney());
                        // 累计退回的积分、积分金额
                        if (back.getBackIntegral() != null && back.getBackIntegral() > 0) {
                            backedIntegral = backedIntegral + back.getBackIntegral();
                            backedIntegralMoney = backedIntegralMoney.add(back.getBackIntegralMoney());
                        }
                    }
                    
                    // 记录已退还的优惠券
                    if (back.getBackCouponUserId() != null && back.getBackCouponUserId() > 0) {
                        couponUserList.add(back.getBackCouponUserId());
                    }
                }
            }

            // 获取当前退货网单下的换货数量
            Integer exchangeNum = memberProductExchangeWriteDao
                .getExchangeNumByOpId(memberProductBack.getOrderProductId());
            exchangeNum = exchangeNum == null ? 0 : exchangeNum;
            // 当前网单下已经退货或者换货的数量加上换货的数量
            opServicedNum += exchangeNum;

            //获取网单信息
            List<OrdersProduct> opList = ordersProductWriteDao.getByOrderId(order.getId());
            if (opList == null || opList.size() == 0) {
                log.error("根据订单号（" + order.getId() + "）获取网单为空。");
                throw new BusinessException("网单不存在！");
            }
            OrdersProduct ordersProduct = null;
            for (OrdersProduct op : opList) {
                // 统计订单下商品总数量
                orderNum += op.getNumber();
                // 统计所有网单的立减金额和
                singleSum = singleSum.add(op.getMoneyActSingle());
                // 获得当前退货的订单
                if (op.getId().equals(memberProductBack.getOrderProductId())) {
                    ordersProduct = op;
                }
            }
            if (ordersProduct == null) {
                log.error("获取网单(网单号：" + memberProductBack.getOrderProductId() + ")为空。");
                throw new BusinessException("网单不存在！");
            }
            // 网单中已退货或换货数量之和+本次退货的数量
            int opInNum = ordersProduct.getBackNumber() + ordersProduct.getExchangeNumber()
                          + memberProductBack.getNumber();
            // 两个条件如果结果不一致，则表示代码处理退换货数量的地方有BUG
            if (opServicedNum > ordersProduct.getNumber() || opInNum > ordersProduct.getNumber()) {
                log.error(
                    "网单(网单号：" + memberProductBack.getOrderProductId() + ")的退货和换货数量之和超过网单数量了。");
                throw new BusinessException("退货数量超过了可退货数量，请重新填写数量！");
            }

            /*
             * 计算退款金额
             */
            // 订单总退款限制金额（现金+余额-运费），运费不退
            BigDecimal backLimit = order.getMoneyPaidBalance().add(order.getMoneyPaidReality())
                .subtract(order.getMoneyLogistics());
            backLimit = backLimit.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : backLimit;
            
            if(order.getPaymentCode().equals(Orders.PAYMENT_CODE_INTEGRAL)) {
            	backLimit = ordersProduct.getActMoney();
            }
            
            // 订单总返回专项积分限制数量
            Integer specialIntegral = 0;
            if(null != ordersProduct.getActSpecialIntegral() && ordersProduct.getActSpecialIntegral() > 0) {
            	specialIntegral = ordersProduct.getActSpecialIntegral();
            }
            
            // 订单总返回积分限制数量
            Integer integralLimit = ordersProduct.getActIntegralNum();
            // 订单返回积分金额限制金额
            BigDecimal integralMoneyLimit = ordersProduct.getActMoney();
            
            if(integralMoneyLimit == null) {
            	integralMoneyLimit = BigDecimal.ZERO;
            }
            
            Integer backSIntegral = specialIntegral - backSpecialIntegral;
            // 整个订单还能退回的金额：总退款限制金额-已经退款的金额和
            BigDecimal backBalance = backLimit.subtract(backedMoney);
            // 整个订单还能退回的积分：总返回积分限制数量-已经返回积分和
            Integer backIntBalance = integralLimit - backedIntegral;
            // 整个订单还能退回的积分金额：总返回积分金额限制金额-已经退回的积分金额和
            BigDecimal backIntMoneyBalance = integralMoneyLimit.subtract(backedIntegralMoney);
            
            if (opList.size() == 1 && orderNum == memberProductBack.getNumber().intValue()) {
                /*
                 * 如果整个订单只有一个网单，且商品数量与退货数量相等，则不需要计算比例等
                 */
                // 退款金额就是总退款限制金额
                memberProductBack.setBackMoney(backLimit);
                // 返回专项积分就是专项积分限制数量
                memberProductBack.setBackSpecialIntegral(specialIntegral);
                // 返回积分就是积分限制数量
                memberProductBack.setBackIntegral(integralLimit);
                // 返回积分金额就是积分金额限制金额
                memberProductBack.setBackIntegralMoney(integralMoneyLimit);
                // 返回优惠券为订单记录的优惠券
                memberProductBack.setBackCouponUserId(order.getCouponUserId());
            } else {
                /*
                 * 如果是多个商品且不是全退，计算退货退款金额（解决用户下单后由商家修改订单金额、满减等活动发生的情况）
                 * 退货退款金额 =（订单总退款限制金额 + 积分限制金额）*（（实际成交单价* 退货数量）/实际成交商品总金额））
                 * 实际成交单价 = 原单价 - （单品立减金额总额/数量）
                 * 实际成交商品总金额 = order.moneyProduct - 订单下所有网单的立减金额和(因为order.moneyProduct是没有减去立减金额的钱，所以计算时需要减去立减的优惠)
                 * 因为实际金额只是用来计算比例，所以此处定义的实际金额均为不考虑满减等发生在整个订单上的价格变化（如果后续在网单上有金额变化则需要加上该部分的考虑）
                 */
            	
            	
            	memberProductBack.setBackSpecialIntegral(ConvertUtil.toInt(Math.ceil(specialIntegral/memberProductBack.getNumber()), 0));
                // 单个商品的立减金额
                BigDecimal singleInProduct = ordersProduct.getMoneyActSingle().divide(
                    BigDecimal.valueOf(ordersProduct.getNumber()), 2, BigDecimal.ROUND_HALF_UP);
                // 实际成交单价
                BigDecimal priceTrue = ordersProduct.getMoneyPrice().subtract(singleInProduct);
                // 实际成交商品总金额
                BigDecimal moneyProductTrue = order.getMoneyProduct().subtract(singleSum);
                // 比例 = 实际成交单价/实际成交商品总金额（百分百，保留4位小数，为了减小误差，乘以数量的步骤放在这一步）
                BigDecimal scale = (priceTrue
                    .multiply(BigDecimal.valueOf(memberProductBack.getNumber())))
                        .divide(moneyProductTrue, 4, BigDecimal.ROUND_HALF_UP);
                // 所有退货商品的退款金额和
                BigDecimal backMoney = (backLimit.add(integralMoneyLimit)).multiply(scale);
                backMoney = backMoney.setScale(2, BigDecimal.ROUND_HALF_UP);

                if (backMoney.compareTo(backBalance) < 1) {
                    /*
                     * 如果退货退款金额 <= 【整个订单还能退回的金额】，则直接使用退货退款金额作为退款金额
                     */
                    // 此时直接使用退货退款金额作为退款金额
                    memberProductBack.setBackMoney(backMoney);
                    // 返回积分设定0
                    memberProductBack.setBackIntegral(0);
                    // 返回积分金额设定0
                    memberProductBack.setBackIntegralMoney(BigDecimal.ZERO);
                    // 返回优惠券ID设定0
                    memberProductBack.setBackCouponUserId(0);
                    if(order.getPaymentCode().equals(Orders.PAYMENT_CODE_INTEGRAL)) {	//如果是积分订单 
                    	//如果订单总数量 == 退货数量 + 已退货数量
                    	if(ordersProduct.getNumber() == memberProductBack.getNumber() + ordersProduct.getBackNumber()) {
                    		memberProductBack.setBackMoney(backBalance);
                    		memberProductBack.setBackIntegralMoney(backBalance);
                    		memberProductBack.setBackIntegral(backIntBalance);
                    	}else {
                    		//余额抵扣积分 退款金额 = 总抵扣金额 / 订单数量  * 退款数量 
                        	integralMoneyLimit = integralMoneyLimit.divide(new BigDecimal(ordersProduct.getNumber()).multiply(new BigDecimal(memberProductBack.getNumber())));
                        	memberProductBack.setBackMoney(integralMoneyLimit);
                        	memberProductBack.setBackIntegral(Math.round(integralLimit / ordersProduct.getNumber() * memberProductBack.getNumber()));
                        	memberProductBack.setBackIntegralMoney(integralMoneyLimit);
                    	}
                    }
                } else {
                    /*
                     * 如果退货退款金额 >  【整个订单还能退回的金额】，则使用积分补充，如果是最后一个网单，则优惠券也退回
                     */
                    // 退款金额直接设定成【整个订单还能退回的金额】
                    memberProductBack.setBackMoney(backBalance);
                    // 计算需要积分补充的金额
                    if (orderNum == (orderBackedNum + memberProductBack.getNumber())) {
                        /*
                         * 如果是最后一次退货，直接退回所有积分，积分金额，优惠券
                         */
                        // 返回积分设定【整个订单还能退回的积分】
                        memberProductBack.setBackIntegral(backIntBalance);
                        // 返回积分金额设定【整个订单还能退回的积分金额】
                        memberProductBack.setBackIntegralMoney(backIntMoneyBalance);
                        // 返回优惠券ID设定为订单的优惠券ID
                        if (!couponUserList.contains(order.getCouponUserId())) {
                            // 当前代码版本的逻辑couponUserList中不会出现有数据的情况
                            // 此判断是为防止客户修改优惠券使用逻辑但是没有修改退货逻辑导致一个优惠券多次退还的风险
                            memberProductBack.setBackCouponUserId(order.getCouponUserId());
                        }
                    } else {
                        // 不是最后一次退货，返回优惠券ID设定0
                        memberProductBack.setBackCouponUserId(0);
                        /*
                         * 如果不是最后一次退货则计算返回的积分数，返回优惠券设定成0
                         */
                        // 如果使用了积分则计算，如果没有使用积分则设定0
                        if (order.getIntegral() > 0) {
                            // 倒推下单时积分和金额之间的换算比例
                            int integralScale = order.getIntegral()
                                                / (order.getMoneyIntegral().intValue());
                            // 计算需要补充的积分数量
                            int supplement = ((backMoney.subtract(backBalance))
                                .multiply(new BigDecimal(integralScale))).intValue();
                            // 如果需要补充的数量小于等于【整个订单还能退回的积分】，则积分数量设定成需要补充的数量
                            // 否则设定成【整个订单还能退回的积分】
                            if (supplement <= backIntBalance) {
                                // 返回积分设定成【需要补充的数量】
                                memberProductBack.setBackIntegral(supplement);
                                // 返回积分金额设定设定：【需要补充的数量】/换算比例
                                BigDecimal supplementMoney = (new BigDecimal(supplement)).divide(
                                    new BigDecimal(integralScale), 2, BigDecimal.ROUND_HALF_UP);
                                memberProductBack.setBackIntegralMoney(supplementMoney);
                            } else {
                                // 返回积分设定成【整个订单还能退回的积分】
                                memberProductBack.setBackIntegral(backIntBalance);
                                // 返回积分金额设定【整个订单还能退回的积分金额】
                                memberProductBack.setBackIntegralMoney(backIntMoneyBalance);
                            }
                        } else {
                            // 返回积分设定0
                            memberProductBack.setBackIntegral(0);
                            // 返回积分金额设定0
                            memberProductBack.setBackIntegralMoney(BigDecimal.ZERO);
                        }
                    }
                }
            }

            // 退货人信息
            if (StringUtil.isEmpty(memberProductBack.getPhone(), true)) {
                memberProductBack.setPhone(order.getMobile());
            }
            if (StringUtil.isEmpty(memberProductBack.getReturnName(), true)) {
                memberProductBack.setReturnName(order.getName());
            }

            memberProductBack.setMemberId(member.getId());
            memberProductBack.setMemberName(member.getName());
            memberProductBack.setStateReturn(MemberProductBack.STATE_RETURN_1);
            memberProductBack.setStateMoney(MemberProductBack.STATE_MONEY_1);
            
            //判断是否为京东订单，如果是则设置来源为京东
            if(order.getOrderType() == Orders.ORDER_TYPE_7){
            	memberProductBack.setSource(MemberProductBack.SOURCE_2);
            }else{
            	memberProductBack.setSource(MemberProductBack.SOURCE_1);
            }
            
            Integer afsServiceId = null;
            //判断是否为京东订单，如果是了则走京东售后
            if(order.getOrderType() == Orders.ORDER_TYPE_7){
            	afsServiceId = saveJdCustomerService(ordersReadDao.get(memberProductBack.getOrderId()),
                		memberProductBack.getOrderProductId(),ComponentExport.CODE_10,memberProductBack.getNumber(),memberProductBack.getQuestion());
            }
            
            //1、保存信息
            memberProductBack.setAfsServiceId(afsServiceId);
            //生成退货单号
            memberProductBack.setProductBackSn(RandomUtil.getMemberProductBackSn());
            //单独福利积分的退款
            if(order.getIsWelfareOrder()!=null && order.getIsWelfareOrder()==2){
                //OrdersProduct ordersProduct=ordersProductReadDao.get(memberProductBack.getOrderProductId());
                //退回金额
                memberProductBack.setBackMoney(ordersProduct.getActMoney());
                //退回通用积分
                memberProductBack.setBackIntegral(ordersProduct.getActIntegralNum());
                //退回专项积分
                memberProductBack.setBackSpecialIntegral(ordersProduct.getActSpecialIntegral());
            }
            Integer count = memberProductBackWriteDao.save(memberProductBack);
            if (count == 0) {
                throw new BusinessException("产品退货申请保存失败，请重试！");
            }
            
            Integer pbcount = ordersProductWriteDao.updateBackNumber(ordersProduct.getId(),
                memberProductBack.getNumber());
            if (pbcount == 0) {
                throw new BusinessException("网单退货信息更新失败，请重试！");
            }
            
            if(count > 0) {
            	
            	// 推送消息到用户
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("time", new Date());
                map.put("orderSn", ordersProduct.getOrdersSn());
                
                boolean flag = messageModel.sendMessageToMember(map, member.getId(), Message.TKSQTZ);
                if(!flag) {
                	throw new BusinessException("推送消息到用户失败。");
                }
                
            	transactionManager.commit(status);
            	return flag;
            }
            
            return false;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 根据登录用户取得用户退货列表
     * @param request
     * @return
     */
    public List<MemberProductBack> getMemberProductBackList(PagerInfo pager, Integer memberId) {

        //取所有的退货申请
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductBackReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductBack> beanList = memberProductBackReadDao.queryList(queryMap);

        for (MemberProductBack bean : beanList) {
            //取对应的订单信息 根据订单id
            Orders order = ordersReadDao.get(bean.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            }
            //获得对应产品信息
            Product product = productReadDao.get(bean.getProductId());
            OrdersProduct ordersProduct = ordersProductReadDao.get(bean.getOrderProductId());
            bean.setProductName(product.getName1());
            bean.setOrderSn(order.getOrderSn());
            bean.setOrderType(order.getOrderType());
            bean.setActIntegralId(ordersProduct.getId());
        }
        return beanList;
    }

    /**
     * 判断是否可以发起退换货申请
     * @param orderId
     * @param orderProductId
     * @param request
     * @return
     */
    public Integer canApplyProductBackOrExchange(Integer orderId, Integer orderProductId,
                                                 Integer memberId) {

        // 参数校验
        if (orderProductId == null || orderProductId == 0) {
            throw new BusinessException("网单id不能为空，请重试！");
        } else if (orderId == null || orderId == 0) {
            throw new BusinessException("订单id不能为空，请重试！");
        }

        //根据订单id取对应的订单信息 
        Orders order = ordersReadDao.get(orderId);
        if (order == null) {
            log.error("订单信息获取失败。");
            throw new BusinessException("订单信息获取失败，请联系管理员！");
        } else {
            int orderState = order.getOrderState();
            if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {
                throw new BusinessException("订单此时所处状态不允许提交退换货申请。");
            }
        }
        //获取网单
        OrdersProduct ordersProduct = ordersProductReadDao.get(orderProductId);
        if (ordersProduct == null) {
            log.error("网单信息获取失败。");
            throw new BusinessException("网单信息获取失败，请联系管理员！");
        }
        Integer number = ordersProduct.getNumber() - ordersProduct.getBackNumber()
                         - ordersProduct.getExchangeNumber();
        if (number < 0) {
            log.error("网单数量有误。");
            throw new BusinessException("网单信息有误，请联系管理员！");
        }

        return number;
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return memberProductBackReadDao.getCount(queryMap);
    }

    public List<MemberProductBack> page(Map<String, String> queryMap, Integer start, Integer size) {
        List<MemberProductBack> list = memberProductBackReadDao.page(queryMap, start, size);
        for (MemberProductBack back : list) {
            back.setProductName(productReadDao.get(back.getProductId()).getName1());
            Orders order = ordersReadDao.get(back.getOrderId());
    		if(null != order) {
    			 back.setOrderSn(order.getOrderSn());
    		}
            if (back.getBackCouponUserId() != null && back.getBackCouponUserId() > 0) {
                back.setCouponUser(couponUserReadDao.get(back.getBackCouponUserId()));
            }
            switch (back.getStateReturn()) {
                case MemberProductBack.STATE_RETURN_1:
                    back.setReturnState("未处理");
                    break;
                case MemberProductBack.STATE_RETURN_2:
                    back.setReturnState("审核通过待收货");
                    break;
                case MemberProductBack.STATE_RETURN_3:
                    back.setReturnState("已经收货");
                    break;
                case MemberProductBack.STATE_RETURN_4:
                    back.setReturnState("不予处理");
                    break;
                default:
                    break;
            }
            switch (back.getStateMoney()) {
                case MemberProductBack.STATE_MONEY_1:
                    back.setMoneyState("未退款");
                    break;
                case MemberProductBack.STATE_MONEY_2:
                    back.setMoneyState("退款到账户");
                    break;
                case MemberProductBack.STATE_MONEY_3:
                    back.setMoneyState("退款到银行");
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    //定时任务将已经确认收货未退款且批次号为空的退货记录保存
    public Boolean jobGetList(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        HSSFWorkbook wb=new HSSFWorkbook();
        JobProductBack jobProductBack=new JobProductBack();
        List<MemberProductBack> list;
        OutputStream fileOut=null;
        BigDecimal countBackMoney= BigDecimal.ZERO;
        Integer count;
        //数据库操作
        try{
            Map<String,Object>dataMap=new HashMap<>();
            dataMap.put("stateReturn","3");
            dataMap.put("stateMoney","1");
            dataMap.put("jobpc","1");
            list=memberProductBackReadDao.queryList(dataMap);
            log.info("待退款订单的数据大小为"+list.size());
            if(list!=null&&list.size()>0){
                for(MemberProductBack m:list){
                    countBackMoney=countBackMoney.add(m.getBackMoney());
                }
                jobProductBack.setCountMoney(countBackMoney);
                jobProductBack.setCreateTime(new Date());
                jobProductBack.setCountProductBack(list.size());
                jobProductBack.setStatus("1");
                jobProductBack.setPc("pc"+TimeUtil.getTodayNumber()+TimeUtil.getHour());
                count=jobProductBackWriteDao.insert(jobProductBack);
                if(count==0){
                    throw new BusinessException("保存退款批次时出现异常");
                }
                for(MemberProductBack m:list){
                    MemberProductBack back=new MemberProductBack();
                    back.setPc(jobProductBack.getPc());
                    back.setId(m.getId());
                    m.setPc(jobProductBack.getPc());
                    Orders order = ordersReadDao.get(m.getOrderId());
                    if(null != order) {
                        m.setOrderSn(order.getOrderSn());
                    }
                    m.setReturnState("已经收货");
                    m.setMoneyState("未退款");
                    count=memberProductBackWriteDao.JobUpdate(back);
                    if(count==0){
                        throw new BusinessException("退货表pc数据回写失败");
                    }
                }
            }
            transactionManager.commit(status);
            log.info("退款批次数据执行成功!");
        }catch (BusinessException e){
            e.printStackTrace();
            log.error("退款订单定时任务错误信息"+e.getMessage());
            transactionManager.rollback(status);
            return false;
        }catch (Exception e){
            e.printStackTrace();
            log.error("退款订单定时任务错误信息"+e.getMessage());
            transactionManager.rollback(status);
            return false;
        }
        
        //如果没有取到数据，就不发送邮件了
        if(list == null || list.size() == 0 ){
        	return true;
        }
        
        //IO操作
        try {
            //1.创建一个execl操作
            HSSFSheet sheet=wb.createSheet("sheet1");
            HSSFCellStyle style=wb.createCellStyle();
            String[] title={"退款批次号","退货单号","订单号","支付订单号","用户名","退款金额","退款状态","退货状态","退货理由"};
            //格式居中
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //设置表头
            HSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                HSSFCell cell=row.createCell(i);
                sheet.setColumnWidth(i,35*256);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }
            int index=0;
            short hight=500;
            for(MemberProductBack m:list){
                index++;
                row=sheet.createRow(index);
                row.setHeight(hight);
                for(int i=0;i<list.size();i++){
                    HSSFCell c=row.createCell(i);
                    c.setCellStyle(style);
                    switch (i){
                        case 0:
                            c.setCellValue(m.getPc());
                            break;
                        case 1:
                            c.setCellValue(m.getProductBackSn());
                            break;
                        case 2:
                            c.setCellValue(m.getOrderSn());
                            break;
                        case 3:
                            c.setCellValue(m.getPaySn());
                            break;
                        case 4:
                            c.setCellValue(m.getMemberName());
                            break;
                        case 5:
                            c.setCellValue(String.valueOf(m.getBackMoney()));
                            break;
                        case 6:
                            c.setCellValue(m.getMoneyState());
                            break;
                        case 7:
                            c.setCellValue(m.getReturnState());
                            break;
                        case 8:
                            c.setCellValue(m.getQuestion());
                            break;
                    }
                }
            }
            //execl保存的路径
            String filename=new Date().getTime()+".xls";
            Code code=codeWriteDao.getCode("EMAIL_CODE","3");
            String  path=code.getCodeText().trim() +filename;
            File file=new File(path);
            File parent=file.getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            file.createNewFile();
            fileOut = new FileOutputStream(path);
            wb.write(fileOut);
            SendMail sendMail=new SendMail();
            String html= MailSenderInfo.productModel(jobProductBack.getPc(),jobProductBack.getCountProductBack(),countBackMoney,
                    "已发送",jobProductBack.getCreateTime());
            String subject="海核云谷门户电商批次退款订单";
            //收件人邮箱
            Code address=codeWriteDao.getCode("EMAIL_CODE","1");
            String text=address.getCodeText().trim();
            log.info("收件人的邮箱地址为"+text);
            String sendTo[] = text.split(",");//发送到那里
            sendMail.send163Email(sendTo, subject,html,path);
        }catch (IOException io){
            io.printStackTrace();
            log.error("文件导出失败"+io.getMessage());
            return false;
        }catch (Exception e){
            e.printStackTrace();
            log.error("邮件发送异常"+e.getMessage());
            return false;
        }finally {
            if(fileOut != null){
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除用户退货[" + id + "]时出错");
        return memberProductBackWriteDao.del(id) > 0;
    }

    /**
     * 退货退款<br>
     * <li>修改退款状态
     * <li>退款到账户，退积分到账户
     * <li>修改订单退款金额
     * <li>修改用户余额
     * <li>追回送出的积分
     * <li>退回优惠券
     * 
     * @param memberProductBackId
     * @param optId
     * @param optName
     * @return
     */
    public boolean backMoney(Integer memberProductBackId, Integer optId, String optName) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            MemberProductBack backDB = memberProductBackReadDao.get(memberProductBackId);
            if (backDB == null) {
                throw new BusinessException("退款申请信息获取失败，请重试！");
            }
            if (backDB.getStateReturn().intValue() != MemberProductBack.STATE_RETURN_3) {
                throw new BusinessException("只有申请状态是已经收货的申请才能退款！");
            }
            if (backDB.getStateMoney().intValue() != MemberProductBack.STATE_MONEY_1) {
                throw new BusinessException("该申请已经退款，请勿重复操作！");
            }
            // 获取订单          
            Orders order = ordersReadDao.get(backDB.getOrderId());
            if (order == null) {
                throw new BusinessException("订单信息获取失败，请重试！");
            }

            if((order.getIsWelfareOrder()!=null&&order.getIsWelfareOrder()!=2) &&order.getMoneyPaidReality().compareTo(BigDecimal.ZERO)==1){
                throw new BusinessException("只能退福利商城订单或订单全款为余额支付订单");
            }
            
            MemberProductBack back=memberProductBackReadDao.get(memberProductBackId);
            /*if(back.getBackSpecialIntegral()==0&&back.getBackIntegral()==0){
                throw new BusinessException("对不起，此退款只能退福利商城订单！");
            }*/
            // 退货状态-已收货
            back.setStateMoney(MemberProductBack.STATE_MONEY_2);
            back.setBackMoneyTime(new Date());
            Integer updateResult = memberProductBackWriteDao.update(back);
            if (updateResult == 0) {
                throw new BusinessException("退款失败，请重试！");
            }

            Member member = memberWriteDao.get(backDB.getMemberId());



            // 获取网单
            OrdersProduct ordersProduct = ordersProductReadDao.get(backDB.getOrderProductId());
            if (ordersProduct == null) {
                throw new BusinessException("网单信息获取失败，请重试！");
            }

            // 修改订单退款金额
            Integer updateMoneyBack = ordersWriteDao.updateMoneyBack(order.getId(),
                backDB.getBackMoney().toString());
            if (updateMoneyBack == 0) {
                throw new BusinessException("修改订单退款金额时失败，请重试！");
            }

            // 修改用户余额
            Member memberNew = new Member();
            memberNew.setId(member.getId());
            memberNew.setBalance(backDB.getBackMoney());
            Integer updateBalance = memberWriteDao.updateBalance(memberNew);
            if (updateBalance == 0) {
                throw new BusinessException("修改用户余额时失败，请重试！");
            }

            // 变动日志
            MemberBalanceLogs logs = new MemberBalanceLogs();
            logs.setMoneyBefore(member.getBalance());
            logs.setMemberId(member.getId());
            logs.setMemberName(member.getName());
            logs.setMoneyAfter(member.getBalance().add(backDB.getBackMoney()));
            logs.setMoney(backDB.getBackMoney());
            logs.setCreateTime(new Date());
            logs.setState(MemberBalanceLogs.STATE_2);
            logs.setRemark("退货退款，订单号" + order.getOrderSn());
            logs.setOptId(optId);
            logs.setOptName(optName);

            Integer save = memberBalanceLogsWriteDao.save(logs);
            if (save == 0) {
                throw new BusinessException("记录用户余额变更日志时失败，请重试！");
            }

            // 如果有返回积分，则修改用户积分，记录积分变更日志
            // 返回积分分两部分：1、积分支付返回为退回给用户积分（增加）；
            //               2、购物时平台送出的积分追回，为减少用户的积分
            if (backDB.getBackIntegral() != null && backDB.getBackIntegral() > 0) {
                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                memberGradeIntegralLogs.setMemberId(backDB.getMemberId());
                memberGradeIntegralLogs.setMemberName(backDB.getMemberName());
                memberGradeIntegralLogs.setValue(backDB.getBackIntegral());
                memberGradeIntegralLogs
                    .setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_8);
                memberGradeIntegralLogs.setOptDes("退货返还积分(订单号:" + order.getOrderSn() + ")");
                memberGradeIntegralLogs.setRefCode(backDB.getOrderProductId().toString());
                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                memberGradeIntegralLogs.setCreateTime(new Date());
                Integer saveLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                if (saveLog == 0) {
                    throw new BusinessException("记录用户积分日志失败，请重试！");
                }
            }
            //追回专项积分，记录日志
            if(order.getSpecialIntegral()!=null && order.getSpecialIntegral()>0){
                List<MemberGradeIntegralLogs> list=memberGradeIntegralLogsReadDao.getMemberGradeIntegralLogsByRefCode(order.getOrderSn().toString());
                if(list!=null && list.size()>0){
                    for(MemberGradeIntegralLogs m:list){
                        MemberSpecialIntegral memberSpecialIntegral=memberSpecialIntegralReadDao.get(m.getMsiId());
                        memberSpecialIntegral.setValue(m.getValue());
                        Integer saveLog=memberSpecialIntegralWriteDao.update(memberSpecialIntegral);
                        if(saveLog==0){
                            throw new BusinessException("退回积分失败失败，请重试！");
                        }
                        MemberGradeIntegralLogs memberGradeIntegralLogs  =new MemberGradeIntegralLogs();
                        memberGradeIntegralLogs.setMemberId(backDB.getMemberId());
                        memberGradeIntegralLogs.setMemberName(backDB.getMemberName());
                        memberGradeIntegralLogs.setValue(backDB.getBackSpecialIntegral());
                        memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_8);
                        memberGradeIntegralLogs.setOptDes("退货返还福利积分(订单号:" + order.getOrderSn() + ")");
                        memberGradeIntegralLogs.setRefCode(order.getOrderSn().toString());
                        memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                        memberGradeIntegralLogs.setCreateTime(new Date());
                        saveLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                        if(saveLog==0){
                            throw new BusinessException("记录用户专项积分日志失败。请重试");
                        }
                    }
                }
            }
            // 计算购物时平台送出的积分追回为减少用户的积分
            MemberGradeIntegralLogs sendIntLog = memberGradeIntegralLogsReadDao
                .getIntLogByMIdAndOrderSnAndOptType(member.getId(),
                    MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_3, order.getOrderSn(),
                    MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
            int subIntegral = 0;
            if (sendIntLog != null && sendIntLog.getValue() > 0) {
                // 追回积分 = 送出积分 *（退款金额/（订单表订单金额 - 订单表积分支付金额））

                // 订单表订单金额 - 订单表积分支付金额
                BigDecimal amount = order.getMoneyOrder().subtract(order.getMoneyIntegral());
                // 比例
                BigDecimal scale = backDB.getBackMoney().divide(amount, 4,
                    BigDecimal.ROUND_HALF_UP);
                // 追回积分，四舍五入保留0位小数
                BigDecimal backIntDec = (new BigDecimal(sendIntLog.getValue())).multiply(scale)
                    .setScale(0, BigDecimal.ROUND_HALF_UP);
                // 追回积分，int数字
                subIntegral = backIntDec.intValue();
                // 不超上限
                subIntegral = subIntegral < sendIntLog.getValue() ? subIntegral
                    : sendIntLog.getValue();

                if (subIntegral > 0) {
                    MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                    memberGradeIntegralLogs.setMemberId(backDB.getMemberId());
                    memberGradeIntegralLogs.setMemberName(backDB.getMemberName());
                    memberGradeIntegralLogs.setValue(subIntegral);
                    memberGradeIntegralLogs
                        .setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_12);
                    memberGradeIntegralLogs.setOptDes("退货追回积分(订单号:" + order.getOrderSn() + ")");
                    memberGradeIntegralLogs.setRefCode(backDB.getOrderProductId().toString());
                    memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                    memberGradeIntegralLogs.setCreateTime(new Date());
                    Integer saveLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                    if (saveLog == 0) {
                        throw new BusinessException("记录用户积分日志失败，请重试！");
                    }
                }
            }

            // 计算会员积分变化值
            int backIntegral = backDB.getBackIntegral() == null ? 0 : backDB.getBackIntegral();
            int backFinal = backIntegral - subIntegral;
            if (backFinal != 0) {
                // 积分变化不为0则修改
                memberNew = new Member();
                memberNew.setId(member.getId());
                // 用户的积分改变量=增加的积分 - 减少的积分，如果是负数则表示要减少的积分多，则用户总积分会减少
                memberNew.setIntegral(backFinal);
                Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
                if (updateIntegral == 0) {
                    throw new BusinessException("修改用户积分时失败，请重试！");
                }
            }

            // 如果有返回优惠券，则修改用户优惠券使用次数，并记录操作日志
            if (backDB.getBackCouponUserId() != null && backDB.getBackCouponUserId() > 0) {
                CouponUser couponUser = couponUserWriteDao.get(backDB.getBackCouponUserId());
                if (couponUser == null) {
                    throw new BusinessException("获取用户优惠券信息时失败，请重试！");
                }
                // 返回优惠券（使用次数加1）
                Integer backCouponUser = couponUserWriteDao.backCouponUser(backDB.getMemberId(),
                    backDB.getBackCouponUserId());
                if (backCouponUser == 0) {
                    throw new BusinessException("返回用户优惠券时失败，请重试！");
                }

                // 记录优惠券操作日志
                CouponOptLog couponOptLog = new CouponOptLog();
                couponOptLog.setCouponUserId(couponUser.getId());
                couponOptLog.setMemberId(couponUser.getMemberId());
                couponOptLog.setSellerId(couponUser.getSellerId());
                couponOptLog.setCouponId(couponUser.getCouponId());
                couponOptLog.setOptType(CouponOptLog.OPT_TYPE_4);
                couponOptLog.setOrderId(order.getId());
                couponOptLog.setCreateUserId(optId);
                couponOptLog.setCreateUserName(optName);
                couponOptLog.setCreateTime(new Date());
                couponOptLogWriteDao.insert(couponOptLog);
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Integer getSettleBacksCount(Integer sellerId, String startTime, String endTime) {
        return memberProductBackReadDao.getSettleBacksCount(sellerId, startTime, endTime);
    }

    public List<MemberProductBack> getSettleBacks(Integer sellerId, String startTime,
                                                  String endTime, Integer start, Integer size) {
        List<MemberProductBack> settleBacks = memberProductBackReadDao.getSettleBacks(sellerId,
            startTime, endTime, start, size);
        if (settleBacks != null && settleBacks.size() > 0) {
            for (MemberProductBack back : settleBacks) {
                // 设定orderSn和商品名称
                Orders orders = ordersReadDao.get(back.getOrderId());
                back.setOrderSn(orders == null ? "" : orders.getOrderSn());
                Product product = productReadDao.get(back.getProductId());
                back.setProductName(product == null ? "" : product.getName1());
            }
        }
        return settleBacks;
    }

    /**
     * 根据登录用户取得用户退货列表
     * @param pager
     * @param memberId
     * @return
     */
    public List<MemberProductBack> getBackListWithPrdAndOp(PagerInfo pager, Integer memberId) {

        //取所有的退货申请
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductBackReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductBack> beanList = memberProductBackReadDao.queryList(queryMap);

        for (MemberProductBack bean : beanList) {
            //获得对应产品信息
            bean.setProduct(productReadDao.get(bean.getProductId()));
            bean.setOrdersProduct(ordersProductReadDao.get(bean.getOrderProductId()));
            // 如果有返回优惠券则查询
            if (bean.getBackCouponUserId() != null && bean.getBackCouponUserId() > 0) {
                bean.setCouponUser(couponUserReadDao.get(bean.getBackCouponUserId()));
            }
            bean.setOrderType(ordersReadDao.get(bean.getOrderId()).getOrderType());
        }
        return beanList;
    }
    
    /**
     * 如果为京东订单则向京东发起售后申请
     * order 订单实体
     * orderProductId 网单ID
     * componentExportCode 服务类型
     * num 请求数量
     * result 服务原因
     */
    public Integer saveJdCustomerService(Orders order,int orderProductId,String componentExportCode,int num,String result){
    	
    	//根据网单ID查询出对应的SKU
    	String sku = productReadDao.get(ordersProductReadDao.get(orderProductId).getProductId()).getProductCode();
    	if(sku ==null || "".equals(sku)){
    		log.error("SKU为空请联系管理员");
    		throw new BusinessException("SKU为空,请联系管理员");
    	}
    	//获取token
    	AccessToken accessToken = JdApi.getAccessToken().getResult();
    	
    	//拼装参数param
    	String param="{\"jdOrderId\":"+order.getThirdOrderSn()+",\"skuId\":"+new Long(sku)+"}";
    	//请求JD服务
    	JDApiResult<Integer> apiResult =JdApi.getAvailableNumberComp(accessToken.getAccess_token(), param);
    	//判断能否对此订单此商品进行售后服务
    	if(!apiResult.isSuccess()){
    		//如果不允许则抛出业务异常
    		log.error(apiResult.getMessage());
    		throw new BusinessException(apiResult.getMessage());
    	}
    	
    	//判断请求数量是否大于京东返回数量如果大于则请求失败
    	if(num>apiResult.getResult()){
    		//如果不允许则抛出业务异常
    		log.error("申请数量大于可申请数量，不能进行售后服务");
    		throw new BusinessException("申请数量大于可申请数量，不能进行售后服务请重新填写售后数量");
    	}
    	//查询该商品支持的服务类型
    	JDApiResult<List<ComponentExport>> apiResultTwo  = JdApi.getCustomerExpectComp(accessToken.getAccess_token(), param);
    	if(!apiResultTwo.isSuccess()){
    		//如果不允许则抛出业务异常
    		log.error(apiResultTwo.getMessage());
    		throw new BusinessException(apiResultTwo.getMessage());
    	}
    	//如果返回的所支持的服务类型为空的话则返回异常
    	if(apiResultTwo.getResult() ==null || apiResultTwo.getResult().size()<1){
    		log.error("此商品不支持售后服务");
    		throw new BusinessException("此商品不支持售后服务");
    	}
    	//判断用户发起的服务是否是京东所支持的
    	boolean flg = false;
    	for (int i = 0; i < apiResultTwo.getResult().size(); i++) {
    		if(apiResultTwo.getResult().get(i).getCode().equals(componentExportCode)){
    			flg = true;
    			break;
    		}
		}
    	//如果没有所支持的服务类型则返回错误
    	if(!flg){
    		log.error("此商品不支持 "+ComponentExport.getNameByCode(componentExportCode));
    		throw new BusinessException("此商品不支持 "+ComponentExport.getNameByCode(componentExportCode));
    	}
    	
    	//判断是否有支持的返回京东方式 （默认是上门取件 如果没有则不能售后）
    	JDApiResult<List<ComponentExport>> apiResultThree = JdApi.getWareReturnJdComp(accessToken.getAccess_token(), param);
    	
    	//请求失败则返回异常
    	if(!apiResultThree.isSuccess()){
    		log.error(apiResultThree.getMessage());
    		throw new BusinessException(apiResultThree.getMessage());
    	}
    	//判断返回的数据是否为空，如果为空则返回异常提示
    	if(apiResultThree.getResult() == null || apiResultThree.getResult().size()<1){
    		log.error("不支持返回京东方式，不能进行售后服务");
    		throw new BusinessException("不支持返回京东方式，不能进行售后服务");
    	}
    	//判断是否有上门取货的方式如果没有则返回异常
    	flg = false;
    	for (int i = 0; i < apiResultThree.getResult().size(); i++) {
			if(ComponentExport.CODE_4.equals(apiResultThree.getResult().get(i).getCode())){
				flg = true;
			}
		}
    	
    	if(!flg){
    		log.error("不支持返回京东方式，不能进行售后服务！！！");
    		throw new BusinessException("不支持返回京东方式，不能进行售后服务！！！");
    	}
    	
    	//如果以上条件都满足则表示可以对商品进行售后服务则开始拼装保存售后服务的参数
    	
    	//客户信息，取订单中的收货人信息
    	AfterSaleCustomerDto asCustomerDto = new AfterSaleCustomerDto();
		asCustomerDto.setCustomerContactName(order.getName());
		asCustomerDto.setCustomerTel(order.getMobile());
		asCustomerDto.setCustomerEmail(order.getEmail());
		asCustomerDto.setCustomerMobilePhone(order.getMobile());
		asCustomerDto.setCustomerPostcode(order.getZipCode());
		
		//根据订单中的详细地址获取对应京东地址ID
		JDApiResult<Map> apiResultFour = JdApi.getJDAddressFromAddress(accessToken.getAccess_token(), order.getAddressInfo());
		
		//判断地址是否请求成功不成功则不能返回异常信息
		if(!apiResultFour.isSuccess()){
			log.error(apiResultFour.getMessage());
    		throw new BusinessException("售后地址请求失败，请稍后重试");
		}
		
		//取件地址
		AfterSalePickwareDto afterSalePickwareDto = new AfterSalePickwareDto();
		afterSalePickwareDto.setPickwareType(AfterSalePickwareDto.PICKWARETYPE_4);
		afterSalePickwareDto.setPickwareProvince(Integer.valueOf(apiResultFour.getResult().get("provinceId").toString()));
		afterSalePickwareDto.setPickwareCity(Integer.valueOf(apiResultFour.getResult().get("cityId").toString()));
		afterSalePickwareDto.setPickwareCounty(Integer.valueOf(apiResultFour.getResult().get("countyId").toString()));
		afterSalePickwareDto.setPickwareVillage(apiResultFour.getResult().get("townId") != ""? Integer.valueOf(apiResultFour.getResult().get("countyId").toString()):0);
		afterSalePickwareDto.setPickwareAddress(order.getAddressInfo());
    	
		//反件地址
		AfterSaleReturnwareDto afterSaleReturnwareDto = new AfterSaleReturnwareDto();
		afterSaleReturnwareDto.setReturnwareType(AfterSaleReturnwareDto.RETURNWARETYPE_10);
		afterSaleReturnwareDto.setReturnwareProvince(Integer.valueOf(apiResultFour.getResult().get("provinceId").toString()));
		afterSaleReturnwareDto.setReturnwareCity(Integer.valueOf(apiResultFour.getResult().get("cityId").toString()));
		afterSaleReturnwareDto.setReturnwareCounty(Integer.valueOf(apiResultFour.getResult().get("countyId").toString()));
		afterSaleReturnwareDto.setReturnwareVillage(apiResultFour.getResult().get("townId") != ""? Integer.valueOf(apiResultFour.getResult().get("countyId").toString()):0);
		afterSaleReturnwareDto.setReturnwareAddress(order.getAddressInfo());
    	
		//商品信息
		AfterSaleDetailDto afterSaleDetailDto = new AfterSaleDetailDto();
		afterSaleDetailDto.setSkuId(new Long(sku));
		afterSaleDetailDto.setSkuNum(num);
		
		//请求主题信息
		AfterSaleDto afterSaleDto = new AfterSaleDto();
		afterSaleDto.setJdOrderId(new Long(order.getThirdOrderSn()));
		afterSaleDto.setCustomerExpect(Integer.valueOf(componentExportCode));
		afterSaleDto.setQuestionDesc(result);
		afterSaleDto.setNeedDetectionReport(false);
		afterSaleDto.setHasPackage(true);
		afterSaleDto.setPackageDesc(AfterSaleDto.PACKAGE_DESC_10);
		afterSaleDto.setAsCustomerDto(asCustomerDto);
		afterSaleDto.setAsDetailDto(afterSaleDetailDto);
		afterSaleDto.setAsPickwareDto(afterSalePickwareDto);
		afterSaleDto.setAsReturnwareDto(afterSaleReturnwareDto);
		
		//发货请求
		JDApiResult<String> apiResultFive =JdApi.createAfsApply(accessToken.getAccess_token(), JSONObject.toJSONString(afterSaleDto));
		if(apiResultFive.isSuccess()){
			//成功则返回1
			
			//如果成功则根据请求时间、订单号和商品SKU匹配服务单号 
			return returnJdAfsServiceId(order.getThirdOrderSn(),sku,new Date());
			
			
			
		}else{
			//不成功则抛出异常
			log.error(apiResultFive.getMessage());
    		throw new BusinessException(apiResultFive.getMessage());
		}
		
    }
    
    /**
     * 根据JD订单号 商品sku 服务单创建时间 返回服务单号
     * @throws  
     */
    public Integer returnJdAfsServiceId(String jdOrderId,String sku,Date createTime){
    	int pageIndex= 1;
		int pageSize = 20;
		Integer afsServiceId = null;
		try{
			//获取token
			AccessToken accessToken = JdApi.getAccessToken().getResult();
			while(true){
				String param="{\"jdOrderId\":"+new Long(jdOrderId)+",\"pageIndex\":"+pageIndex+",\"pageSize\":"+pageSize+"}";
				//根据订单号分页获取此订单的服务单
				JDApiResult<AfsServicebyCustomerPinPage> apiResultSix =  JdApi.getServiceListPage(accessToken.getAccess_token(),param);
				//如果不成功则返回 null
				if(!apiResultSix.isSuccess()){
					return afsServiceId;
				}
				
				//进行循环判断
				for (int i = 0; i < apiResultSix.getResult().getServiceInfoList().size(); i++) {
					AfsServicebyCustomerPin afsServicebyCustomerPin = apiResultSix.getResult().getServiceInfoList().get(i);
					//判断是否为申请状态
					if(afsServicebyCustomerPin.getAfsServiceStep() == AfsServicebyCustomerPin.AFS_SERVICE_STEP_10){
						//判断SKU是否相等
						if(afsServicebyCustomerPin.getWareId() == new Long(sku)){
						//判断申请时间与返回的申请时间相差是否小于60S
							DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							fmt.parse(afsServicebyCustomerPin.getAfsApplyTime());
							if((createTime.getTime()-fmt.parse(afsServicebyCustomerPin.getAfsApplyTime()).getTime())/1000 <= 60){
								afsServiceId = afsServicebyCustomerPin.getAfsServiceId();
								return afsServiceId;
							}
						}
					}
				}
				
				if(apiResultSix.getResult().getServiceInfoList().size()>pageSize){
					pageIndex++;
				}else{
					break;
				}
	    	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return afsServiceId;
    }


    public List<MemberProductBack> queryByPc(String pc){
        Map<String,Object>queryMap=new HashMap<>();
        queryMap.put("pc",pc);
        return memberProductBackReadDao.queryList(queryMap);
    }
}
