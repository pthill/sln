package com.sln.model.supplier;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.StringUtil;
import com.sln.dao.shop.read.supplier.SupplierExchangeReadDao;
import com.sln.dao.shop.write.member.MemberProductExchangeWriteDao;
import com.sln.dao.shop.write.supplier.SupplierExchangeWriteDao;
import com.sln.entity.member.MemberProductExchange;
import com.sln.entity.supplier.SupplierExchange;

@Component
public class SupplierExchangeModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SupplierExchangeModel.class);
    @Resource
    private SupplierExchangeReadDao supplierExchangeReadDao;
     
    @Resource
    private SupplierExchangeWriteDao supplierExchangeWriteDao;
    
    @Resource
    private DataSourceTransactionManager    transactionManager;
    
    @Resource
    private MemberProductExchangeWriteDao memberProductExchangeWriteDao;
    /**
     * 查询页面列表
     */
    public List<SupplierExchange> getPage(Map<String, String> queryMap,Integer size,Integer start){
    	return supplierExchangeReadDao.getPage(queryMap, size, start);
    }
    
   public int getPageCount(Map<String, String> queryMap){
	   return supplierExchangeReadDao.getPageCount(queryMap);
   }
   
   /**
    * 新增数据
    * @param supplierExchange
    * @return
    */
   public int insertSupplierExchange(SupplierExchange supplierExchange){
	   return  supplierExchangeWriteDao.insertSupplierExchange(supplierExchange);
   }
   
   /**
    * 修改换货单状态,并且修改换货申请单状态
    * @param supplierExchange
    * @return
    */
   public int updateStateById(SupplierExchange supplierExchange){
	   Integer result = 0;
       DefaultTransactionDefinition def = new DefaultTransactionDefinition();
       def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
       TransactionStatus status = transactionManager.getTransaction(def);
       try{
    	   //首先先修改供应商换货单状态
    	   int exchangeId = supplierExchange.getExchangeId();
    	   supplierExchange.setExchangeId(null);
    	   result = supplierExchangeWriteDao.updateStateById(supplierExchange);
    	   if(result <1){
    		   transactionManager.rollback(status);
    		   return result;
    	   }
    	   int exchangeState =0;
    	   if(supplierExchange.getExchangeState()==SupplierExchange.EXCHANGE_STATE_2){
    		   exchangeState =3;
    	   }else if(supplierExchange.getExchangeState()==SupplierExchange.EXCHANGE_STATE_3){
    		   exchangeState =4;
    	   }else if(supplierExchange.getExchangeState()==SupplierExchange.EXCHANGE_STATE_4){
    		   exchangeState =5;
    	   }else{
    		   log.error("供应商操作换货单状态异常：");
    		   transactionManager.rollback(status);
    		   return 0;
    	   }
    	   //修改成功后在修改换货申请单数据
    	  result = memberProductExchangeWriteDao.upState(exchangeId, exchangeState);
    	  if(result >0){
    		  transactionManager.commit(status);
    		  return result;
    	  }else{
    		  transactionManager.rollback(status);
   		   	  return result;
    	  }
       }catch(Exception e){
    	   log.error("供应商操作换货单异常：" + e);
           transactionManager.rollback(status);
           throw new RuntimeException(e);
       }
   }
    
     private void dbConstrains(SupplierExchange supplierExchange) {
		supplierExchange.setExchangeSn(StringUtil.dbSafeString(supplierExchange.getExchangeSn(), false, 50));
		supplierExchange.setOrderSn(StringUtil.dbSafeString(supplierExchange.getOrderSn(), false, 50));
		supplierExchange.setProductName(StringUtil.dbSafeString(supplierExchange.getProductName(), false, 50));
		supplierExchange.setMemberName(StringUtil.dbSafeString(supplierExchange.getMemberName(), false, 50));
		supplierExchange.setRemark(StringUtil.dbSafeString(supplierExchange.getRemark(), true, 255));
     }
}