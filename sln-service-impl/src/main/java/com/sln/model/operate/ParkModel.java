package com.sln.model.operate;

import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.operate.ParkReadDao;
import com.sln.dao.shop.write.operate.ParkWriteDao;
import com.sln.entity.operate.Park;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class ParkModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ParkModel.class);
    
    @Resource
    private ParkWriteDao parkWriteDao;
    
    @Resource
    private ParkReadDao parkReadDao;
    
    @Resource
    private DataSourceTransactionManager    transactionManager;
    
    
    /**
     * 查询页面列表
     */
    public List<Park> getPage(Map<String, String> queryMap,Integer size,Integer start){
    	return parkReadDao.getPage(queryMap, size, start);
    }
    
   public int getPageCount(Map<String, String> queryMap){
	   return parkReadDao.getPageCount(queryMap);
   }
   
   /**
    * 新增数据
    */
   public Integer insertPark(Park park){
       int countName=parkReadDao.countName(park.getParkName(),null);
       if(countName>0){
           throw new BusinessException("新增失败,园区名称存在重复");
       }
       int countCode=parkReadDao.countCode(park.getParkCode(),null);
       if(countCode>0){
           throw new BusinessException("新增失败,编号存在重复");
       }
	   return parkWriteDao.insertPark(park);
   }
   /**
    * 根据ID修改数据
    */
   public Integer updateParyById(Park park){
       int countName=parkReadDao.countName(park.getParkName(),park.getId());
       if(countName>0){
           throw new BusinessException("新增失败,园区名称存在重复");
       }
       int countCode=parkReadDao.countCode(park.getParkCode(),park.getId());
       if(countCode>0){
           throw new BusinessException("新增失败,编号存在重复");
       }
	   return parkWriteDao.updateParkById(park);
   }
   
   /**
    * 批量根据id修改数据
    * @param ids
    */
   public Integer batchUpdateParkById(String ids, Integer state) {
	   DefaultTransactionDefinition def = new DefaultTransactionDefinition();
       def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
       TransactionStatus status = transactionManager.getTransaction(def);
       try {
    	   //首先判断ids是否为空
           	if(ids ==null){
           		return 0;
           	}
           	String id[] = ids.split(",");
        	Park park;
    	   for (int i = 0; i < id.length; i++) {
    		  park = new Park();
    		  park.setId(Integer.valueOf(id[i]));
    		  park.setState(state);
    		  parkWriteDao.updateParkById(park);
    	   }
           transactionManager.commit(status);
       } catch (Exception e) {
           log.error("更新订单时出现未知异常：" + e);
           transactionManager.rollback(status);
           throw e;
       }
	   return 1;
   }
   
     
     private void dbConstrains(Park park) {
		park.setParkCode(StringUtil.dbSafeString(park.getParkCode(), false, 50));
		park.setParkName(StringUtil.dbSafeString(park.getParkName(), false, 50));
		park.setParkAddr(StringUtil.dbSafeString(park.getParkAddr(), true, 100));
		park.setRemark(StringUtil.dbSafeString(park.getRemark(), true, 100));
     }
     public List<Park> getParkList(){
         return parkReadDao.getParkList();
     }

     public Park getParkById(Integer id){return parkReadDao.getParkById(id);}

    /**
     * 根据园区进行分组查询下面的业务管理方
     */
    public List<Park> getOperationsGroupByParkId(){
        return parkReadDao.getOperationsGroupByParkId();
    }

    public List<Park> getParkByArea(String area) {
        return parkReadDao.getParkByArea(area);
    }

    public List<String> getArea() {
        return parkReadDao.getArea();
    }
}