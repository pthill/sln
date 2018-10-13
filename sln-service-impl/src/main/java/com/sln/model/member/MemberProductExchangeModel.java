package com.sln.model.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Roman;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.RandomUtil;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.jd.bean.ComponentExport;
import com.sln.dao.shop.read.member.MemberProductExchangeReadDao;
import com.sln.dao.shop.read.order.OrdersProductReadDao;
import com.sln.dao.shop.read.order.OrdersReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.write.member.MemberProductExchangeWriteDao;
import com.sln.dao.shop.write.order.OrdersProductWriteDao;
import com.sln.dao.shop.write.supplier.SupplierExchangeWriteDao;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberProductExchange;
import com.sln.entity.message.Message;
import com.sln.entity.order.Orders;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.product.Product;
import com.sln.entity.supplier.SupplierExchange;
import com.sln.model.message.MessageModel;

/**
 * 会员换货管理                       
 *
 */
@Component(value = "memberProductExchangeModel")
public class MemberProductExchangeModel {
    private static Logger                 log = LogManager
        .getLogger(MemberProductExchangeModel.class);

    @Resource
    private MemberProductExchangeWriteDao memberProductExchangeWriteDao;
    @Resource
    private MemberProductExchangeReadDao  memberProductExchangeReadDao;
    @Resource
    private MemberProductBackModel memberProductBackModel;
    @Resource
    private OrdersReadDao                 ordersReadDao;
    @Resource
    private ProductReadDao                productReadDao;
    @Resource
    private OrdersProductWriteDao         ordersProductWriteDao;
    @Resource
    private OrdersProductReadDao          ordersProductReadDao;
    @Resource
    private DataSourceTransactionManager  transactionManager;
    
    @Resource
    private SupplierExchangeWriteDao 	   supplierExchangeWriteDao;
    
    @Resource
    private MessageModel 					messageModel;

    /**
    * 根据id取得用户换货
    * @param  memberProductExchangeId
    * @return
    */
    public MemberProductExchange getMemberProductExchangeById(Integer memberProductExchangeId) {
        return memberProductExchangeWriteDao.get(memberProductExchangeId);
    }

    /**
     * 保存用户换货
     * @param memberProductExchange
     * @param member
     * @return
     */
    public boolean saveMemberProductExchange(MemberProductExchange memberProductExchange,
                                             Member member) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 参数校验
            if (memberProductExchange == null) {
                throw new BusinessException("换货申请不能为空，请重试！");
            } else if (memberProductExchange.getProductId() == null
                       || memberProductExchange.getProductId() == 0) {
                throw new BusinessException("换货申请产品id不能为空，请重试！");
            } else if (memberProductExchange.getOrderProductId() == null
                       || memberProductExchange.getOrderProductId() == 0) {
                throw new BusinessException("换货申请网单id不能为空，请重试！");
            } else if (memberProductExchange.getOrderId() == null
                       || memberProductExchange.getOrderId() == 0) {
                throw new BusinessException("换货申请订单id不能为空，请重试！");
            }

            //根据订单id取对应的订单信息 
            Orders order = ordersReadDao.get(memberProductExchange.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            } else {
                int orderState = order.getOrderState();
                if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                    || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {

                    log.error("订单此时所处状态不允许提交换货申请。");
                    throw new BusinessException("订单此时所处状态不允许提交换货申请！");
                }
            }

            // 如果换货单的地址信息为空则默认用原单地址信息
            if (memberProductExchange.getProvinceId() == null
                || memberProductExchange.getProvinceId().intValue() == 0) {
                memberProductExchange.setProvinceId(order.getProvinceId());
                memberProductExchange.setCityId(order.getCityId());
                memberProductExchange.setAreaId(order.getAreaId());
                memberProductExchange.setAddressInfo(order.getAddressInfo());
                memberProductExchange.setAddressAll(order.getAddressAll());
                memberProductExchange.setPhone(order.getMobile());
                memberProductExchange.setChangeName(order.getName());
            }
            if (memberProductExchange.getProvinceId2() == null
                || memberProductExchange.getProvinceId2().intValue() == 0) {
                memberProductExchange.setProvinceId2(order.getProvinceId());
                memberProductExchange.setCityId2(order.getCityId());
                memberProductExchange.setAreaId2(order.getAreaId());
                memberProductExchange.setAddressInfo2(order.getAddressInfo());
                memberProductExchange.setAddressAll2(order.getAddressAll());
                memberProductExchange.setPhone2(order.getMobile());
                memberProductExchange.setChangeName2(order.getName());
            }
            if (StringUtil.isEmpty(memberProductExchange.getEmail(), true)) {
                memberProductExchange.setEmail(order.getEmail());
            }
            if (StringUtil.isEmpty(memberProductExchange.getZipCode(), true)) {
                memberProductExchange.setZipCode(order.getZipCode());
            }
            if (StringUtil.isEmpty(memberProductExchange.getName(), true)) {
                memberProductExchange.setName(order.getName());
            }

            memberProductExchange.setMemberId(member.getId());
            memberProductExchange.setMemberName(member.getName());
            memberProductExchange.setState(ConstantsEJS.MEM_PROD_EXCHG_STATE_1);
            if(order.getOrderType() == Orders.ORDER_TYPE_7){
            	memberProductExchange.setSource(MemberProductExchange.SOURCE_2);
            }else{
            	memberProductExchange.setSource(MemberProductExchange.SOURCE_1);
            }
            
            //判断是否为京东订单，如果是了则走京东售后
            Integer afsServiceId = null;
            if(order.getOrderType() == Orders.ORDER_TYPE_7){
            	afsServiceId = memberProductBackModel.saveJdCustomerService(ordersReadDao.get(memberProductExchange.getOrderId()),
            			memberProductExchange.getOrderProductId(),ComponentExport.CODE_10,memberProductExchange.getNumber(),memberProductExchange.getQuestion());
            }
            memberProductExchange.setAfsServiceId(afsServiceId);
            //生成退货单号
            memberProductExchange.setProductExchangeSn(RandomUtil.getMemberProductExSn());
            //1、保存信息
            Integer count = memberProductExchangeWriteDao.save(memberProductExchange);
            if (count == 0) {
                throw new BusinessException("产品换货申请保存失败，请重试！");
            }
            //获取网单信息
            OrdersProduct ordersProduct = ordersProductWriteDao
                .get(memberProductExchange.getOrderProductId());
            if (ordersProduct == null) {
                log.error("网单不存在。");
                throw new BusinessException("网单不存在！");
            } else {
                //获取当前可以退货的数量
                Integer dbNum = ordersProduct.getNumber() - ordersProduct.getBackNumber()
                                - ordersProduct.getExchangeNumber();
                if (dbNum == 0 || dbNum < memberProductExchange.getNumber()) {
                    throw new BusinessException("该网单不能进行换货申请！");
                }
                Integer pbcount = ordersProductWriteDao.updateExchangeNumber(ordersProduct.getId(),
                    memberProductExchange.getNumber());
                if (pbcount == 0) {
                    throw new BusinessException("网单换货信息更新失败，请重试！");
                }
            }
            
            // 推送消息到用户
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("time", new Date());
            queryMap.put("orderSn", ordersProduct.getOrdersSn());
            
            boolean flag = messageModel.sendMessageToMember(queryMap, member.getId(), Message.HHSQTZ);
            if(!flag) {
            	throw new BusinessException("推送消息到用户失败。");
            }
            
            transactionManager.commit(status);
            return count > 0;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
    * 更新用户换货
    * @param  memberProductExchange
    * @return
    */
    public Integer updateMemberProductExchange(MemberProductExchange memberProductExchange) {
    	 // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        SupplierExchange supplierExchange;
        int result = 0;
        try{
        	
        	MemberProductExchange exchange = new MemberProductExchange();
        	exchange.setId(memberProductExchange.getId());
        	exchange.setState(memberProductExchange.getState());
        	exchange.setRemark(memberProductExchange.getRemark());
        	
        	if(memberProductExchange.getState()==2){
        		//如果更新状态为审核通过待收货状态则 更新换货申请单 并且新增供应商换货单
        		result = memberProductExchangeWriteDao.update(exchange);
        		if(result <1){
        			transactionManager.rollback(status);
                	log.error("更新换货申请单失败");
        			return 0;
        		}
        		supplierExchange = new SupplierExchange();
        		supplierExchange.setExchangeId(memberProductExchange.getId());
        		supplierExchange.setExchangeNumber(memberProductExchange.getNumber());
        		supplierExchange.setExchangeState(1);
        		supplierExchange.setMemberId(memberProductExchange.getMemberId());
        		supplierExchange.setMemberName(memberProductExchange.getMemberName());
        		supplierExchange.setOrderSn(ordersReadDao.get(memberProductExchange.getOrderId()).getOrderSn());
        		supplierExchange.setProductId(memberProductExchange.getProductId());
        		Product product = productReadDao.get(memberProductExchange.getProductId());
        		supplierExchange.setProductName(product.getName1());
        		supplierExchange.setExchangeSn(RandomUtil.getSupplierExchange());
        		supplierExchange.setSupplierId(product.getSupplierId());
        		supplierExchange.setRemark(memberProductExchange.getRemark());
        		result = supplierExchangeWriteDao.insertSupplierExchange(supplierExchange);
        	}else if(memberProductExchange.getState() != 6){
        		//首先先判断此换货单商品是否为自营商品
        		Integer type = memberProductExchangeReadDao.getSupplierType(memberProductExchange.getId());
            	
        		if(type == null || type ==1){
            		//如果不是自营的则返回2，页面上提示不是自营商品
        			transactionManager.rollback(status);
        			return 2;
            	}
        		//首先先更新换货申请单
            	result = memberProductExchangeWriteDao.update(exchange);
            	if(result<1){
            		transactionManager.rollback(status);
                	log.error("更新换货申请单失败");
            	}
            	//更新成功后判断更新的状态，在对供应商换货单操作
            	supplierExchange = new SupplierExchange();
            	supplierExchange.setExchangeId(memberProductExchange.getId());
            	if(memberProductExchange.getState() == 3){
            		supplierExchange.setExchangeState(2);
            	}else if(memberProductExchange.getState() == 4){
            		supplierExchange.setExchangeState(3);
            	}else if(memberProductExchange.getState() == 5){
            		supplierExchange.setExchangeState(4);
            	}else{
            		log.error("供应商操作换货单状态异常：");
          		   transactionManager.rollback(status);
          		   return 0;
            	}
            	result = supplierExchangeWriteDao.updateStateById(supplierExchange);
        	}else{
        		result = memberProductExchangeWriteDao.update(exchange);
        	}
        	if(result<1){
        		transactionManager.rollback(status);
        		log.error("更新换货申请单失败");
        		return 0;
        	}else{
        		
        		Orders order = ordersReadDao.get(memberProductExchange.getOrderId());
        		// 推送消息到用户
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("time", new Date());
                queryMap.put("orderSn", order.getOrderSn());
                
                boolean flag = false;
        		
        		if(memberProductExchange.getState()==2) {	//换货成功
        			flag = messageModel.sendMessageToMember(queryMap, memberProductExchange.getMemberId(), Message.HHCGTZ);
        		}else{										//换货失败
        			flag = messageModel.sendMessageToMember(queryMap, memberProductExchange.getMemberId(), Message.HHSBTZ);
        		}
        		
        		if(!flag) {
                	throw new BusinessException("推送消息到用户失败。");
                }
        		
        		transactionManager.commit(status);
        		return result;
        	}
        	
        }catch(Exception e){
        	transactionManager.rollback(status);
        	log.error("更新换货申请单失败："+e);
            throw e;
        }
    }

    /**
     * 根据登录用户取得用户换货列表 分页
     * @param pager
     * @param request
     * @return
     */
    public List<MemberProductExchange> getMemberProductExchangeList(Map<String, Object> queryMap,
                                                                    PagerInfo pager,
                                                                    Integer memberId) {

        //取所有的换货申请
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductExchangeReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductExchange> beanList = memberProductExchangeReadDao.queryList(queryMap);

        for (MemberProductExchange bean : beanList) {
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
            bean.setActIntegralId(ordersProduct.getActIntegralId());
        }
        return beanList;
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return memberProductExchangeReadDao.getCount(queryMap);
    }

    public List<MemberProductExchange> page(Map<String, String> queryMap, Integer start,
                                            Integer size) {
        List<MemberProductExchange> list = memberProductExchangeReadDao.page(queryMap, start, size);
        for (MemberProductExchange exchange : list) {
        	Product product = productReadDao.get(exchange.getProductId());
        	if(null != product) {
        		exchange.setProductName(product.getName1());
        	}
            Orders order = ordersReadDao.get(exchange.getOrderId());
            if(null != order) {
            	exchange.setOrderSn(order.getOrderSn());
            }
        }
        return list;
    }

    /**
     * 根据登录用户取得用户换货列表(封装商品对象和网单对象)
     * @param pager
     * @param memberId
     * @return
     */
    public List<MemberProductExchange> getExchangeListWithPrdAndOp(PagerInfo pager,
                                                                   Integer memberId) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductExchangeReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductExchange> beanList = memberProductExchangeReadDao.queryList(queryMap);

        for (MemberProductExchange bean : beanList) {
            bean.setProduct(productReadDao.get(bean.getProductId()));
            bean.setOrdersProduct(ordersProductReadDao.get(bean.getOrderProductId()));
            bean.setOrderType(ordersReadDao.get(bean.getOrdersProduct().getOrdersId()).getOrderType());
        }
        return beanList;
    }
}
