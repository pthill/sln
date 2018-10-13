package com.sln.job;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.sln.core.ServiceResult;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.service.jd.IJdCategoryService;
import com.sln.service.jd.IJdCommoditypoolService;
import com.sln.service.jd.IJdProductService;
import com.sln.service.product.IProductCateService;
import com.sln.service.product.IProductService;

/**
 * 
 * @author hlq
 *
 */
public class JDBasicData {
	private static Logger logger = Logger.getLogger(JDBasicData.class);
	@Resource
	private IJdProductService jdProductService;
	@Resource
	private IJdCategoryService jdCategoryService;
	@Resource
	private IJdCommoditypoolService jdCommoditypoolService;
	@Resource
	private IProductCateService productCateService;
	@Resource
	private IProductService    productService;
	/**
	 * 定时任务获取京东数据
	 */
	public void jobGetJdData(){
		System.out.println("开始同步...................................................");
		AccessToken token = JdApi.getAccessToken().getResult();
		if(token != null && token.getState() == AccessToken.ACCESSTOKEN_STATE_1){
			ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
			serviceResult.setResult(1);
			serviceResult = jdCommoditypoolService.batchInsert(token);
			if(serviceResult.getResult()>0){
				serviceResult = jdCommoditypoolService.insertSku(token);
				if(serviceResult.getResult()>0){
					serviceResult = jdCategoryService.batchInsertCategory(token);
						if(serviceResult.getResult()>0){
							serviceResult = productCateService.syJdCate();
							productCateService.VerifJDCate();
							if(serviceResult.getResult()>0){
							serviceResult = jdProductService.batchInsertProductDetail(token);
								if(serviceResult.getResult()>0){
									serviceResult = jdProductService.updatePriceBySku(token);
										if(serviceResult.getResult()>0){
											serviceResult = jdProductService.updateStuStateBySku(token);
												if(serviceResult.getResult()>0){
													serviceResult = productService.syJdProduct();
													if(serviceResult.getResult() >0){
														logger.error("同步JD商品数据成功");
													}else{
														logger.error("同步JD商品到平台失败");
													}
													
												}else{
													logger.error("同步JD商品上下架状态异常");
												}
										}else{
											logger.error("同步JD商品价格异常");
										}
								}else{
									logger.error("同步JD商品详情异常");
								}
							}else{
								logger.error("同步JD分类到平台异常");
							}
						}else{
							logger.error("同步JD分类信息异常");
						}
				}else{
					logger.error("同步JD SKU异常");
				}
			}else{
				logger.error("同步JD商品池子异常");
			}
			System.out.println("结束同步...................................................");
		}else{
			logger.error("token异常");
		}
		
	}
	public static void main(String[] args) {
		JDBasicData basicData = new JDBasicData();
		basicData.jobGetJdData();
	}
}
