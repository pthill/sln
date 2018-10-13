package com.sln.model.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import com.sln.core.ConstantsEJS;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.product.ProductCateServiceSwitchReadDao;
import com.sln.dao.shop.write.product.ProductCateServiceSwitchWriteDao;
import com.sln.entity.product.ProductCateServiceSwitch;

@Component
public class ProductCateServiceSwitchModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductCateServiceSwitchModel.class);
    
    @Resource
    private ProductCateServiceSwitchReadDao productCateServiceSwitchReadDao;
    
    @Resource
    private ProductCateServiceSwitchWriteDao productCateServiceSwitchWriteDao;
    
    @Resource
    private DataSourceTransactionManager transactionManager;
    /**
     * 根据id取得product_cate_service_switch对象
     * @param  productCateServiceSwitchId
     * @return
     */
    public ProductCateServiceSwitch getProductCateServiceSwitchById(Integer id) {
    	return productCateServiceSwitchReadDao.get(id);
    }
    
    public ProductCateServiceSwitch get(){
    	List<ProductCateServiceSwitch> list = this.queryList();
    	if(CollectionUtils.isEmpty(list) || list.size()==0){
    		return null;
    	}
    	return list.get(0);
    }
    
    public List<ProductCateServiceSwitch> queryList() {
        List<ProductCateServiceSwitch> list = productCateServiceSwitchReadDao.queryList();
        return list;
    }
    
    /**
     * 保存product_cate_service_switch对象
     * @param  productCateServiceSwitch
     * @return
     */
     public Boolean saveProductCateServiceSwitch(ProductCateServiceSwitch productCateServiceSwitch) {
    	 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         try{
        	 this.dbConstrains(productCateServiceSwitch);
             Integer row = productCateServiceSwitchWriteDao.insert(productCateServiceSwitch);
             if (row == 0) {
                 throw new BusinessException("开启服务费比例操作失败！");
             }
             transactionManager.commit(status);
             return true;
         }catch (Exception e){
        	 log.error("开启服务费比例操作时出现未知异常：" + e);
             transactionManager.rollback(status);
             throw e;
         }
     }
     
     /**
     * 更新product_cate_service_switch对象
     * @param  newPcss
     * @return
     */
     public Boolean updateProductCateServiceSwitch(ProductCateServiceSwitch newPcss) {
    	 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         try{
        	 Integer row = null;
        	 ProductCateServiceSwitch oldPcss = this.get();
        	 if(oldPcss==null){
        		 newPcss.setCreateTime(new Date());
        		 newPcss.setState(ConstantsEJS.USE_YN_Y);
        		 row = productCateServiceSwitchWriteDao.insert(newPcss);
        	 }else{
        		 newPcss.setId(oldPcss.getId());
        		 newPcss.setUpdateId(newPcss.getCreateId());
        		 newPcss.setCreateId(null);
        		 newPcss.setUpdateTime(new Date());
        		 newPcss.setState(oldPcss.getState()==0?ConstantsEJS.USE_YN_Y:ConstantsEJS.USE_YN_N);
        		 productCateServiceSwitchWriteDao.update(newPcss);
        	 }
             if (row == 0) {
                 throw new BusinessException("启停服务费比例操作失败！");
             }
             transactionManager.commit(status);
             return true;
         }catch (Exception e){
        	 log.error("启停服务费比例操作时出现未知异常：" + e);
             transactionManager.rollback(status);
             throw e;
         }
     }
     
     private void dbConstrains(ProductCateServiceSwitch productCateServiceSwitch) {
		productCateServiceSwitch.setRemark(StringUtil.dbSafeString(productCateServiceSwitch.getRemark(), true, 200));
     }
}