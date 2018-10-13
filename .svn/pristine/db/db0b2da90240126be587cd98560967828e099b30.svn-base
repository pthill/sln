package com.sln.model.jd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import com.sln.core.ConvertUtil;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.util.JDApiResult;
import com.sln.core.jd.util.JDConfig;
import com.sln.dao.shop.read.jd.JdCategoryReadDao;
import com.sln.dao.shop.write.jd.JdCategoryWriteDao;
import com.sln.entity.jd.JdCategory;

@Component
public class JdCategoryModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(JdCategoryModel.class);
    
    @Resource
    private JdCategoryWriteDao jdCategoryWriteDao;
    @Resource
    private JdCategoryReadDao 	jdCategoryReadDao;
    
    @Resource
    private DataSourceTransactionManager transactionManager;
    
    /**
     * 批量新增分类信息
     * @param jdCategory
     */
    public int batchInsertCategory(AccessToken token){
    		 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	     def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    	     TransactionStatus   status = transactionManager.getTransaction(def);
    	try{
    		//获取分类信息
			int pageNo = 1;
			int pageCount = 0;
			int totalRows= 0;
			
			while(true){
				JDApiResult<Map> apiResult =JdApi.getCategory(token.getAccess_token(), pageNo, JDConfig.PAGE_SIZE, 0);
				if(!apiResult.isSuccess()){
					//如果返回异常则直接程序结束
					log.error("获取分类信息失败");
					transactionManager.rollback(status);
		    		return 0;
				}
				Map map = apiResult.getResult();
				List<Map> list = (List<Map>)map.get("categorys");
					jdCategoryWriteDao.batchInsertCategory(list);
				
				if(pageNo ==1){
					//获取列表总行数
					totalRows = Integer.valueOf(map.get("totalRows").toString());
					//算出总页数
					pageCount = totalRows%JDConfig.PAGE_SIZE ==0 ?totalRows/JDConfig.PAGE_SIZE:totalRows/JDConfig.PAGE_SIZE+1;
				}
				//如果当前行数小与列表总数则当前行数+1 用于下一次反问
				if(pageCount>pageNo){
					pageNo++;
				}else{
					//否则退出
					break;
				}
				
			}
			transactionManager.commit(status);
			
    	}catch(Exception e){
    		log.error("插入失败："+e.getMessage());
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		return 0;
    	}
    	return 1;
    }
    
    /**
     * 对JD分类信息进行查漏补缺
     * @return
     */
    public int checkUpCat(AccessToken token,int catClass){
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	     def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	     TransactionStatus   status = transactionManager.getTransaction(def);
		try{
			List<JdCategory> list  = jdCategoryReadDao.getErrorParentId(catClass);
			List<Map> catListMap = new ArrayList<Map>();
			for (int i = 0; i < list.size(); i++) {
				JDApiResult<Map> apiResult =  JdApi.getCategoryBycId(token.getAccess_token(), list.get(i).getParentId()+"");
				if(!apiResult.isSuccess()){
					continue;
				}
				catListMap.add(apiResult.getResult());
			}
			jdCategoryWriteDao.batchInsertCategory(catListMap);
			transactionManager.commit(status);
		}catch(Exception e){
			log.error("插入失败："+e.getMessage());
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		return 0;
		}
		return 1;
    }
     
    /**
	 * 查询京东商品分类列表
     * @param queryMap
     * @return
     */
    public List<JdCategory> getJdCategoryList(Map<String, Object> queryMap,Integer start, Integer size){
    	List<JdCategory> list = jdCategoryReadDao.queryList(queryMap,start,size);
    	if(!CollectionUtils.isEmpty(list)){
    		List<JdCategory> allList = jdCategoryReadDao.queryList(new HashMap<String, Object>(),null,null);
    		for (JdCategory jdCategory : list) {
    			int pId = ConvertUtil.toInt(jdCategory.getParentId(), 0) ;
    			if(pId==0){
    				continue;
    			}
    			//查询上级（二级分类）
				JdCategory parent = this.getByCateId(allList,pId);
				if(parent!=null && parent.getParentId()!=null && parent.getParentId()!=0){
					//查询上上级（一级分类）
					JdCategory pParent = this.getByCateId(allList,parent.getParentId());
					parent.setParent(pParent);
				}
				jdCategory.setParent(parent);
			}
    	}
    	return list;
    }
    
    private JdCategory getByCateId(List<JdCategory> allList,int pId){
    	for (JdCategory jdCategory : allList) {
			if(jdCategory.getCatId() == pId){
				return jdCategory;
			}
		}
    	return null;
    }
    
    /**
	 * 查询京东商品分类列表总行数
	 * @param queryMap
	 * @return
	 */
    public Integer getCount(Map<String, Object> queryMap) {
        return jdCategoryReadDao.getCount(queryMap);
    }
    
    /**
	 * 根据pid获取分类列表
     * @param pid
     * @return
     */
    public List<JdCategory> getByPid(Integer pid) {
        if (null == pid)
            throw new BusinessException("根据父id获取商品分类失败，父id为空");
        List<JdCategory> list = jdCategoryReadDao.getByPid(pid);
        return list;
    }
    
    /**
   	 * 根据京东分类id取得分类信息
     * @param catId
     * @return
     */
    public JdCategory getByCatId(Integer catId) {
        return jdCategoryReadDao.getByCatId(catId);
    }
    
    private void dbConstrains(JdCategory jdCategory) {
    	jdCategory.setName(StringUtil.dbSafeString(jdCategory.getName(), true, 50));
    }
}