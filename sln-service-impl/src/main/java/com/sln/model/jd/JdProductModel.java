package com.sln.model.jd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sln.core.StringUtil;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JdProductDto;
import com.sln.core.jd.util.JDApiResult;
import com.sln.dao.shop.read.jd.JdCommoditypoolReadDao;
import com.sln.dao.shop.write.jd.JdCommoditypoolWriteDao;
import com.sln.dao.shop.write.jd.JdProductWriteDao;
import com.sln.entity.jd.JdAddress;
import com.sln.entity.jd.JdCommoditypool;
import com.sln.entity.jd.JdProduct;


@Component
public class JdProductModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(JdProductModel.class);
    
    @Resource
    private JdProductWriteDao jdProductWriteDao;
    
    @Resource
    private JdCommoditypoolReadDao jdCommoditypoolReadDao;
    
    @Resource
    private JdCommoditypoolWriteDao jdCommoditypoolWriteDao;
    
    @Resource
    private DataSourceTransactionManager transactionManager;
    
    /**
     * 批量插入数据
     * @param jdProduct
     */
    public int batchInsertProductDetail(AccessToken accessToken){
    	// 事务管理
    	 TransactionStatus status = null;
    	try{
    		
    		//首先获取SKU
			int pageNo =0;
			int pageSize = 100;
			while(true){
				//开启事务
				 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    	     def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	    	     status = transactionManager.getTransaction(def);
				List<String> list = jdCommoditypoolReadDao.getSku(pageNo, pageSize,JdCommoditypool.SKUSTATE_1);
				if(list == null || list.size() <1){
					break;
				}
				
				List<JdProductDto> productDtos = new ArrayList<JdProductDto>();
				for (int i = 0; i < list.size(); i++) {
					//获取到商品详情
					JDApiResult<JdProductDto> jdApiResult = JdApi.getDetailObjBySku(accessToken.getAccess_token(), list.get(i), false);
					if(jdApiResult.isSuccess()){
						productDtos.add(jdApiResult.getResult());
					}
				}
				
				//获取到商品详情后先插入
				jdProductWriteDao.batchInsertProductDetail(productDtos);
				
				//然后修改临时sku表状态
				jdCommoditypoolWriteDao.updateStateBySku(JdCommoditypool.SKUSTATE_2, list);
				transactionManager.commit(status);
			}
    	}catch(Exception e){
    		log.error("批量导入详情数据失败："+e.getMessage());
    		e.printStackTrace();
    		if(transactionManager != null){
    			transactionManager.rollback(status);
    		}
    	}
    	return 1;
    }
    
    /**
     * 修改商品详情价格
     */
    public int updatePriceBySku(AccessToken token){
    	// 事务管理
    	TransactionStatus status = null;
    	try{
    		
    		//然后获取SKU
			int pageNo =0;
			int pageSize = 100;
			List<Map> mapList = new ArrayList<Map>();
			while(true){
				String sku="";
				List<String> list = jdCommoditypoolReadDao.getSku(pageNo, pageSize,JdCommoditypool.SKUSTATE_2);
				System.out.println("list.size:"+list.size());
				if(list == null || list.size() <1){
					break;
				}
				//把SKU拼接起来用,号隔开
				for (int i = 0; i < list.size(); i++) {
					sku+=list.get(i)+",";
				}
				sku = sku.substring(0, sku.length()-1);
				//获取商品价格
				JDApiResult<List<Map>> apiResult = JdApi.getPrice(token.getAccess_token(), sku);
				if(apiResult.isSuccess() && apiResult.getResult().size()>0){
					//开启事务
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    	        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    	        status = transactionManager.getTransaction(def);
					for (int i = 0; i < apiResult.getResult().size(); i++) {
						Map map = new HashMap();
						map.put("price", new BigDecimal(apiResult.getResult().get(i).get("price").toString()));
						map.put("jdprice", new BigDecimal(apiResult.getResult().get(i).get("jdPrice").toString()));
						map.put("sku", apiResult.getResult().get(i).get("skuId").toString());
						mapList.add(map);
					}
						System.out.println("批量修改商品价格..................");
						//批量修改商品价格
						jdProductWriteDao.updateBatchPriceBySku(mapList);
						mapList.clear();
						//批量修改临时表状态
						jdCommoditypoolWriteDao.updateStateBySku(JdCommoditypool.SKUSTATE_3, list);
						
						//事务等到本次修改提交完毕才提交
						transactionManager.commit(status);
						System.err.println("---commit;");
				}
			}
    	}catch(Exception e){
    		log.error("修改商品价格失败:"+e.getMessage());
    		e.printStackTrace();
    		if(status!=null)transactionManager.rollback(status);
    		return 0;
    	}
    	return 1;
    }
    
    /**
     * 修改商品上下架状态
     */
    public int updateStuStateBySku(AccessToken token){
    	// 事务管理
    	TransactionStatus status = null;
    	try{
    		//然后获取SKU
			int pageNo =0;
			int pageSize = 100;
			List<Map> mapList = new ArrayList<Map>();
			while(true){
				String sku="";
				List<String> list = jdCommoditypoolReadDao.getSku(pageNo, pageSize,JdCommoditypool.SKUSTATE_3);
				if(list == null || list.size() <1){
					break;
				}
				//把SKU拼接起来用,号隔开
				for (int i = 0; i < list.size(); i++) {
					sku+=list.get(i)+",";
				}
				sku = sku.substring(0, sku.length()-1);
				//获取商品价格
				JDApiResult<List<Map>> apiResult = JdApi.getSkuState(token.getAccess_token(), sku);
				if(apiResult.isSuccess() && apiResult.getResult().size()>0){
					//开启事务
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
					def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
					status = transactionManager.getTransaction(def);
					for (int i = 0; i < apiResult.getResult().size(); i++) {
						Map map = new HashMap();
						map.put("skuState", Integer.valueOf(apiResult.getResult().get(i).get("state").toString()));
						map.put("sku", apiResult.getResult().get(i).get("sku").toString());
						mapList.add(map);
						//修改商品上下架状态
						//jdProductWriteDao.updateStuStateBySku(, );
					}
						System.out.println("批量修改上下架状态..................");
						//批量修改上下架状态
						jdProductWriteDao.updateBatchStuStateBySku(mapList);
						mapList.clear();
						//修改临时表状态
						jdCommoditypoolWriteDao.updateStateBySku(JdCommoditypool.SKUSTATE_4, list);
						
						//事务等到本次修改提交完毕才提交
						transactionManager.commit(status);
						System.err.println("---commit;");
				}
			}
    	}catch(Exception e){
    		log.error("修改商品上下架失败:"+e.getMessage());
    		e.printStackTrace();
    		if(status!=null)transactionManager.rollback(status);
    		return 0;
    	}
    	return 1;
    }
    
    /**
     * 根据地址查询京东地址
     * @param token
     * @param address
     * @return
     */
    public JdAddress getJDAddressFromAddress(String token, String address) {
        JdAddress area = null;
        try {
            Map add = JdApi.getJDAddressFromAddress(token, address).getResult();
            area = new JdAddress();
            area.setNation((String)add.get("nation"));
            area.setNationId(Integer.valueOf(add.get("nationId").toString()));
            area.setProvince((String)add.get("province"));
            area.setProvinceId(Integer.valueOf(add.get("provinceId").toString()));
            area.setCity((String)add.get("city"));
            area.setCityId(Integer.valueOf(add.get("cityId").toString()));
            area.setCounty((String)add.get("county"));
            area.setCountyId(Integer.valueOf(add.get("countyId").toString()));
            area.setTown((String)add.get("town"));
            if(null != add.get("townId") && add.get("townId").toString().length() > 0){
                area.setTownId(Integer.valueOf(add.get("townId").toString()));
            } else {
                //不存在四级地址默认设置为0
                area.setTownId(0);
            }
        } catch (Exception e) {
            area = null;
        }
        return area;
    }
    
    /**
     * 根据地址查询京东三级地址编码组合
     * 格式：1_0_0 (分别代表1、2、3级地址)
     * @param token
     * @param address
     * @return
     */
    public String getJDAddressArea(String token, String address) {
        String area = null;
        try {
            JdAddress jdAddress = getJDAddressFromAddress(token, address);
            area = jdAddress.getProvinceId() + "_" + jdAddress.getCityId() + "_" + jdAddress.getCountyId();
        } catch (Exception e) {
            area = null;
        }
        return area;
    }    
    
    /**
     * 商品列表页获取库存接口
     * @param token
     * @param sku
     * @param area
     * @return
     */
    public Integer getStockById (String token, String sku, String area) {
        Integer remainNum = 0;
        try {
            JSONArray array = JSON.parseArray(JdApi.getStockById(token,sku, area).getResult().toString());
            JSONObject jo = JSONObject.fromObject(array.get(0));
            //[{\"state\":\"34\",\"area\":\"19_1607_3639_0\",\"desc\":\"无货\",\"sku\":\"1339393\"}]
            Integer state = jo.getInt("state");
            if(state != 34){
                remainNum = 50;
            }
        } catch (Exception e) {
            remainNum = 0;
        }
        return remainNum;
    }
    
    /**
     * 获取京东运费
     * @param token
     * @param sku
     * @param address
     * @return
     */
    public Integer getFreight (String token, String sku, JdAddress address) {
        Integer freight = 0;
        try {
            JSONObject jo = JSONObject.fromObject(JdApi.getFreight(token, sku, address.getProvinceId(), address.getCityId(), address.getCountyId(), address.getTownId(), JdApi.PAYMENT_TYPE).getResult().toString());
            freight = jo.getInt("freight");
        } catch (Exception e) {
            freight = -1;
        }
        return freight;
    }
     
     private void dbConstrains(JdProduct jdProduct) {
		jdProduct.setSku(StringUtil.dbSafeString(jdProduct.getSku(), false, 20));
		jdProduct.setName(StringUtil.dbSafeString(jdProduct.getName(), false, 100));
		jdProduct.setParam(StringUtil.dbSafeString(jdProduct.getParam(), true, 200));
		jdProduct.setIntroduction(StringUtil.dbSafeString(jdProduct.getIntroduction(), true, 200));
		jdProduct.setImagePath(StringUtil.dbSafeString(jdProduct.getImagePath(), true, 100));
     }
}