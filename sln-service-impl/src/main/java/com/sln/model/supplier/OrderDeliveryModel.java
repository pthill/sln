package com.sln.model.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.supplier.OrderDeliveryReadDao;
import com.sln.dao.shop.write.order.OrdersWriteDao;
import com.sln.dao.shop.write.supplier.OrderDeliveryWriteDao;
import com.sln.entity.message.Message;
import com.sln.entity.operate.Park;
import com.sln.entity.order.Orders;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.model.message.MessageModel;

@Component
public class OrderDeliveryModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrderDeliveryModel.class);
    
    @Resource
    private OrderDeliveryWriteDao orderDeliveryWriteDao;
    
    @Resource 
    private OrderDeliveryReadDao orderDeliveryReadDao;
    
    @Resource
    private DataSourceTransactionManager    transactionManager;
    
    @Resource
    private OrdersWriteDao ordersWriteDao;
    
    @Resource
    private MessageModel messageModel;
     
    
    
    
    /**
     * 查询页面列表
     */
    public List<OrderDelivery> getPage(Map<String, String> queryMap,Integer size,Integer start){
    	return orderDeliveryReadDao.getPage(queryMap, size, start);
    }
    
   public int getPageCount(Map<String, String> queryMap){
	   return orderDeliveryReadDao.getPageCount(queryMap);
   }
   
   /**
    * 确认发货
    */
   public int cofimDelivery(OrderDelivery orderDelivery){
	   Integer result = 0;
       DefaultTransactionDefinition def = new DefaultTransactionDefinition();
       def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
       TransactionStatus status = transactionManager.getTransaction(def);
	   try{
		   
		 //首先修改发货单状态和物流信息
			OrderDelivery orderDeliverydb = new OrderDelivery();
			orderDeliverydb.setId(orderDelivery.getId());
			orderDeliverydb.setState(2);
			orderDeliverydb.setLogistics(orderDelivery.getLogistics());
			orderDeliverydb.setWaybillNumber(orderDelivery.getWaybillNumber());
			orderDeliverydb.setLogisticsName(orderDelivery.getLogisticsName());
			result= orderDeliveryWriteDao.updateOrderDeliveryById(orderDeliverydb);
			if(result<1){
				transactionManager.rollback(status);
				return result;
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("q_state", "1");
			map.put("q_orderSn", orderDelivery.getOrderSn());
			//然后查询是否还有此订单的发货单，如果为1则表示没有
			int count = orderDeliveryReadDao.getPageCount(map);
			if(count == 1){
				//如果没有了 则修改订单状态为已发货状态根据订单号
				result = ordersWriteDao.updateDeliverByOrderSn(orderDelivery.getOrderSn());
				if(result <1){
					transactionManager.rollback(status);
					log.error("供应商确认发货出现异常：修改订单状态失败");
					return result;
				}
				
			}
			
			Orders ordersDB = ordersWriteDao.getByOrderSn(orderDelivery.getOrderSn());
			//发送消息到用户
            Map<String,Object> queryMap = new HashMap<String,Object>();
            queryMap.put("time", ordersDB.getPayTime() == null ? ordersDB.getCreateTime() : ordersDB.getPayTime());
            queryMap.put("orderSn", ordersDB.getOrderSn());
            Boolean flag = messageModel.sendMessageToMember(queryMap, ordersDB.getMemberId(), Message.DDFHTZ);
			if(!flag) {
				throw new BusinessException("发送消息到用户失败");
			}
			
			transactionManager.commit(status);
			return result;
	   }catch(Exception e){
		   log.error("供应商确认发货出现异常：" + e);
           transactionManager.rollback(status);
           throw new RuntimeException(e);
	   }
   }
    
   /**
    * 新增数据
    */
   public Integer insertOrderDelivery(OrderDelivery orderDelivery){
	   return orderDeliveryWriteDao.insertOrderDelivery(orderDelivery);
   }
   /**
    * 根据ID修改数据
    */
   public Integer updateOrderDeliveryById(OrderDelivery orderDelivery){
	   return orderDeliveryWriteDao.updateOrderDeliveryById(orderDelivery);
   }
    
   
   
   
     private void dbConstrains(OrderDelivery orderDelivery) {
		orderDelivery.setOrderSn(StringUtil.dbSafeString(orderDelivery.getOrderSn(), false, 50));
		orderDelivery.setDeliverySn(StringUtil.dbSafeString(orderDelivery.getDeliverySn(), false, 50));
		orderDelivery.setMemberName(StringUtil.dbSafeString(orderDelivery.getMemberName(), true, 20));
		orderDelivery.setMemberPhone(StringUtil.dbSafeString(orderDelivery.getMemberPhone(), true, 20));
		orderDelivery.setReceivingAddress(StringUtil.dbSafeString(orderDelivery.getReceivingAddress(), false, 100));
     }
}