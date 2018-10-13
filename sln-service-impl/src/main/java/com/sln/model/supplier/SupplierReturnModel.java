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
import com.sln.dao.shop.read.supplier.SupplierReturnReadDao;
import com.sln.dao.shop.write.member.MemberProductBackWriteDao;
import com.sln.dao.shop.write.supplier.SupplierReturnWriteDao;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.supplier.SupplierReturn;


@Component
public class SupplierReturnModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SupplierReturnModel.class);
    
    @Resource
    private SupplierReturnWriteDao supplierReturnWriteDao;
    
    @Resource
    private SupplierReturnReadDao  supplierReturnReadDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    
    @Resource
    private MemberProductBackWriteDao productBackWriteDao;
    
    
    /**
     * 查询页面列表
     */
    public List<SupplierReturn> getPage(Map<String, String> queryMap,Integer size,Integer start){
    	return supplierReturnReadDao.getPage(queryMap, size, start);
    }
    
   public int getPageCount(Map<String, String> queryMap){
	   return supplierReturnReadDao.getPageCount(queryMap);
   }
     
   /**
    * 新增数据
    */
   public Integer insertSupplierReturn(SupplierReturn supplierReturn){
	   return supplierReturnWriteDao.insertSupplierReturn(supplierReturn);
   }
   
   /**
    * 修改数据
    * @param supplierReturn
    */
   public Integer updateSupplierReturn(SupplierReturn supplierReturn){
	   return supplierReturnWriteDao.updateSupplierReturn(supplierReturn);
   }
   
   /**
    * 批量新增数据
    * @param list
    * @return
    */
   public Integer batchInsertSupplierReturn(List<SupplierReturn> list){
	   return supplierReturnWriteDao.batchInsertSupplierReturn(list);
   }
   
   
   /**
    * 确认收货
    * @param supplierReturn
    */
   public Integer cofimReceipt(SupplierReturn supplierReturn){
	   Integer result = 0;
       DefaultTransactionDefinition def = new DefaultTransactionDefinition();
       def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
       TransactionStatus status = transactionManager.getTransaction(def);
       try{
    	   
    	   //首先先修改供应商退货单状态
    	   SupplierReturn supplierReturnDB = new SupplierReturn();
    	   supplierReturnDB.setId(supplierReturn.getId());
    	   supplierReturnDB.setReturnState(SupplierReturn.RETURN_STATE_2);
    	   result = supplierReturnWriteDao.updateSupplierReturn(supplierReturnDB);
    	   if(result==0){
    		   return 0;
    	   }
    	   //然后查询出本退货申请单还有多少未完成的供应商退货单
    	   Map<String, String> map = new HashMap<String, String>();
    	   map.put("q_returnState", "1");
    	   map.put("q_orderSn", supplierReturn.getOrderSn());
    	   int count = supplierReturnReadDao.getPageCount(map);
    	   //判断如果为1 或者为0则表示此次处理为最后条商家发货单数据，则修改退货申请单状态为已收货
    	   if(count ==1 || count==0){
    		  result =  productBackWriteDao.upStateReturn(supplierReturn.getBackId(), MemberProductBack.STATE_RETURN_3);
    		  
    		  
    		  if(result <1){
    			  transactionManager.rollback(status);
    			  return result;
    		  }
    	   }
    		   
    		   transactionManager.commit(status);
    		   return result;
       }catch(Exception e){
    	   log.error("供应商确认退货发货出现异常：" + e);
           transactionManager.rollback(status);
           throw new RuntimeException(e);
       }
   }
   
   
   
     private void dbConstrains(SupplierReturn supplierReturn) {
		supplierReturn.setReturnSn(StringUtil.dbSafeString(supplierReturn.getReturnSn(), false, 50));
		supplierReturn.setOrderSn(StringUtil.dbSafeString(supplierReturn.getOrderSn(), false, 50));
     }
}