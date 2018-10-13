package com.sln.model.jd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sln.core.StringUtil;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.util.JDApiResult;
import com.sln.dao.shop.read.jd.JdCommoditypoolReadDao;
import com.sln.dao.shop.write.jd.JdCommoditypoolWriteDao;
import com.sln.entity.jd.JdCommoditypool;


@Component
public class JdCommoditypoolModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(JdCommoditypoolModel.class);
    
    @Resource
    private JdCommoditypoolWriteDao jdCommoditypoolWriteDao;
    
    @Resource
    private JdCommoditypoolReadDao jdCommoditypoolReadDao;
   
    
   /**
    * 同步京东商品池子数据
    */
   public Integer batchInsert(AccessToken token){
	   try{
			//然后获取商品池子
			JDApiResult<List<Map>> apiResult =JdApi.getJdCommodityPool(token.getAccess_token());
			if(!apiResult.isSuccess()){
				//如果返回异常则直接程序结束
				log.error("获取商品池子失败："+apiResult.getMessage());
				return 0;
			}
			jdCommoditypoolWriteDao.batchInsert(apiResult.getResult());
	   }catch(Exception e){
		   	//如果返回异常则直接程序结束
			log.error("新增商品池数据："+e.getMessage());
			return 0;
	   }
	   return 1;
   }
   
   /**
    * 同步商品池子中SKU数据
    */
   public int insertSku(AccessToken token){
	   try{
			//然后获取商品池子
			int pageNo =0;
			int skuPageNo = 1;
			int pageSize = 20;
			while(true){
				List<JdCommoditypool> list = jdCommoditypoolReadDao.getJdCommoditypool(pageNo, pageSize);
				//如果返回数据为空则表示已经查询出全部的商品池子 则跳出循环
				if(list.size()<1){
					break;
				}else{
					pageNo+=pageSize;
				}
				for (int i = 0; i < list.size(); i++) {
					JDApiResult<Map> apiResult = JdApi.getSkuByPage(token.getAccess_token(),list.get(i).getPageNum(), skuPageNo);
					if(!apiResult.isSuccess()){
						log.equals(list.get(i).getPageNum()+"：返回数据为空");
						continue;
					}
					while(true){
						JSONArray jsonArray = JSON.parseArray(apiResult.getResult().get("skuIds").toString());
						Object[] skuIds = jsonArray.toArray();
						for (int j = 0; j < skuIds.length;) {
							
							//判断数组中的SKU是否超过200个如果不超过200个则直接插入
							if(skuIds.length<500 && j==0){
								jdCommoditypoolWriteDao.batchInsertSku(skuIds);
								break;
							}else{
								int size = 500;
								if(skuIds.length-j<500){
									size = skuIds.length-j;
								}
								Object[] skuTwo = new Object[size];
								System.arraycopy(skuIds, j, skuTwo, 0, size);
								jdCommoditypoolWriteDao.batchInsertSku(skuTwo);
								j=j+size;
							}
						}
						int pageCount= (int) apiResult.getResult().get("pageCount");
						if(skuPageNo <pageCount){
							skuPageNo++;
						}else{
							skuPageNo=1;
							break;
						}
					}
				}
		   }
	   }catch(Exception e){
		   log.equals("新增商品SKU异常:"+e.getMessage());
		   System.out.println(e.getMessage());
		   return 0;
	   }
	   return 1;
   }
   
   /**
    * 获取池子数据（分页）
    * @param jdCommoditypool
    */
   public List<JdCommoditypool>  getJdCommoditypool(Integer start,Integer size){
	   return jdCommoditypoolReadDao.getJdCommoditypool(start, size);
   }
     
   /**
    * 对临时SKU表去重
    * @param jdCommoditypool
    */
   public int duplicateRemoval(){
	   return jdCommoditypoolWriteDao.duplicateRemoval();
   }
     
     private void dbConstrains(JdCommoditypool jdCommoditypool) {
		jdCommoditypool.setName(StringUtil.dbSafeString(jdCommoditypool.getName(), false, 50));
     }
}