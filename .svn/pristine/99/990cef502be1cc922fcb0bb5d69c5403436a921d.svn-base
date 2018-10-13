package com.sln.core.jd;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sln.core.HttpClientUtil;
import com.sln.core.Md5;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.AfsServicebyCustomerPinPage;
import com.sln.core.jd.bean.CompatibleServiceDetailDTO;
import com.sln.core.jd.bean.ComponentExport;
import com.sln.core.jd.bean.JDOrder;
import com.sln.core.jd.bean.JdProductDto;
import com.sln.core.jd.util.JDApiResult;
import com.sln.core.jd.util.JDConfig;
import com.sln.core.jd.util.JDHttpResult;

/**
 * 京东接口调用
 * @author longqiao.hu
 *
 */
public class JdApi {
	private static Logger log = Logger.getLogger(JdApi.class);
	
	/**
	 * 金采支付
	 */
	public static final int PAYMENT_TYPE = 101;
	
	/**
	 * 获取或刷新 Access Token
	 * 如果参数为Null 或者 为""则是获取，如果不为null并且不为空则是刷新
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static JDApiResult<AccessToken> getAccessToken() {
		AccessToken accessToken = new AccessToken();
		
		String clientId=JDConfig.APP_KEY;         //设置clientId参数
		String clientSecret=JDConfig.APP_SECRET;  //设置clientSecret参数
		JDApiResult<AccessToken> apiRes = new JDApiResult<AccessToken>();
		
		StringBuffer paraBuffer = new StringBuffer();//请求参数
		String result = "";
		String url ="";
		try{
			//判断参数是否为Null 或者""
				String grant_type = JDConfig.GRANT_TYPE;
				String username = JDConfig.USERNAME;
				String password = JDConfig.PASSWORD;
				String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				/*
				 签名,生成规则如下：
				1.按照以下顺序将字符串拼接起来
				client_secret+timestamp+client_id+username+password+grant_type+scope+client_secret
				其中client_secret的值是京东分配的
				username使用原文，password需要md5加密后的
				2.将上述拼接的字符串使用MD5加密，加密后的值再转为大写
				 */
				String sign = clientSecret + timestamp + clientId+ username 
						+ password + grant_type + clientSecret;
				sign = Md5.getMd5String(sign).toUpperCase();
				
				//拼接请求参数
				paraBuffer.append("grant_type").append("=").append(grant_type);
				paraBuffer.append("&").append("client_id").append("=").append(clientId);
				paraBuffer.append("&").append("username").append("=").append(URLEncoder.encode(username,"UTF-8"));
				paraBuffer.append("&").append("password").append("=").append(password);
				paraBuffer.append("&").append("timestamp").append("=").append(timestamp);
				paraBuffer.append("&").append("sign").append("=").append(sign);
				
				url = JDConfig.ACCESS_TOKEN_URL;
			result = HttpClientUtil.httpPostRequest(url, paraBuffer.toString());
			apiRes = JDHttpResult.parse(result, AccessToken.class);
		}catch(Exception e){
			//系统异常则记录系统异常
			accessToken.setState(AccessToken.ACCESSTOKEN_STATE_3);
			accessToken.setResult_message("系统异常，请查看系统日志");
			log.error("请求JD Access_token 接口异常："+e.getMessage());
		}
		return apiRes;
	}
	
	/**
	 * 获取京东商品池信息
	 * @param AccessToken
	 * @return
	 */
	public static JDApiResult<List<Map>> getJdCommodityPool(String AccessToken){
		return currencyMethodToList(AccessToken, new HashMap<String, Object>(), Map.class, JDConfig.GET_PAGE_NUM_URL);
	}
	
	
	/**
	 * 获取京东商品分类列表接口
	 * @param AccessToken pageNo
	 * @return
	 */
	public static JDApiResult<Map> getCategory(String token,Integer pageNo,Integer pageSize,Integer parentId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", JDConfig.PAGE_SIZE);
		if(parentId != null && parentId >0){
			map.put("parentId", parentId);
		}
		return currencyMethod(token, map, Map.class, JDConfig.GET_CATEGORYS_URL);
	}
	/**
	 * 获取京东商品分类根据分类ID
	 * @param AccessToken pageNo
	 * @return
	 */
	public static JDApiResult<Map> getCategoryBycId(String token,String cid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", cid);
		return currencyMethod(token, map, Map.class, JDConfig.GET_CATEGORY_URL);
	}
	/**
	 * 根据商品池子编号获取商品SKU接口
	 * 返回数据示例：{"result":"123123,152352"}

	 * @param args
	 */
	public static JDApiResult<String> getSkuByPageNum(String token,Integer pageNum){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		return currencyMethod(token, map, String.class, JDConfig.GET_SKU_URL);
	}
	 
	/**
	 * 根据商品池子编号页码 获取商品SKU接口
	 * 页码，默认取第一页；每页最多10000条数据，品类商品池可能存在多页数据，具体根据返回的页总数判断是否有下一页数据
	 * 返回数据示例：{"success": true,"resultMessage": "操作成功","resultCode": "0000","result": {"pageCount": 1,"skuIds": [916606,853073]}}
	 * @param args
	 */
	public static JDApiResult<Map> getSkuByPage(String token,Integer pageNum,Integer pageNo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageNo", pageNo);
		return currencyMethod(token, map, Map.class, JDConfig.GET_SKU_URL_2);
	}
	/**
	 * 根据商品SKU获取商品详细信息
	 * @param AccessToken 
	 * @param skus 商品SKU 
	 * @param flg false:查询商品基本信息；true:商品基本信息 + 商品售后信息 + 移动商品详情介绍信息 默认false

	 * @return
	 */
	public static JDApiResult<Map> getDetailBySku(String token,String sku,boolean flg){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sku", sku);
		map.put("isShow", flg);
		return currencyMethod(token, map, Map.class, JDConfig.GET_DETAIL_URL);
	}
	
	/**
	 * 根据商品SKU获取商品详细信息(返回jdproduct对象)
	 * @param AccessToken 
	 * @param skus 商品SKU 
	 * @param flg false:查询商品基本信息；true:商品基本信息 + 商品售后信息 + 移动商品详情介绍信息 默认false
	 */
	public static JDApiResult<JdProductDto> getDetailObjBySku(String token,String sku,boolean flg){
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sku", sku);
			map.put("isShow", flg);
			return currencyMethod(token, map, JdProductDto.class, JDConfig.GET_DETAIL_URL);
		}
	/**
	 * 根据商品SKU获取商品上下架信息
	 */
	public static JDApiResult<List<Map>> getSkuState(String token,String sku){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sku", sku);
		return currencyMethodToList(token, map, Map.class, JDConfig.GET_SKU_STATE_URL);
	}
	
	/**
	 * 根据SKU获取商品协议价格
	 */
	public static JDApiResult<List<Map>> getPrice(String token,String sku){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sku", sku);
		return currencyMethodToList(token, map, Map.class, JDConfig.GET_PRICE_URL);
	}
	
	/**
	 * 根据地址查询京东地址编码
	 */
	public static JDApiResult<Map> getJDAddressFromAddress(String token,String address){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("address", address);
		return currencyMethod(token, map, Map.class, JDConfig.GET_JDADDRESS_FROM_ADDRESS_URL);
	}
	
	/**
	 * 通用访问方法
	 * @param token token 
	 * @param map 参数集合，KER为参数名称 value为参数值
	 * @param map uri 访问地址 
	 * @param map uri 访问地址 type 类型 1： T 、2：list<T>
	 */
	private static  <T> JDApiResult<T> currencyMethod(String token,Map<String, Object> map,Class<T> c,String uri){
		StringBuffer paraBuffer = new StringBuffer();//请求参数
		JDApiResult<T> jdApiResult = new JDApiResult<T>();
		String result = null;
		paraBuffer.append("token").append("=").append(token);
		//迭代 设置参数
		try{
			for(Map.Entry<String, Object> entry:map.entrySet()){
				 paraBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
			 result = HttpClientUtil.httpPostRequest(uri, paraBuffer.toString());
			 jdApiResult = JDHttpResult.parse(result, c);
		}catch(Exception e){
			result= null;
			jdApiResult.setSuccess(false);
			e.printStackTrace();
			log.error("通用方法访问失败:"+e.getMessage());
		}
		return jdApiResult;
	}
		
		/**
		 * 通用访问方法
		 * @param token token 
		 * @param map 参数集合，KER为参数名称 value为参数值
		 * @param map uri 访问地址 
		 */
		private static  <T> JDApiResult<List<T>> currencyMethodToList(String token,Map<String, Object> map,Class<T> c,String uri){
			StringBuffer paraBuffer = new StringBuffer();//请求参数
			JDApiResult<List<T>> jdApiResult = new JDApiResult<List<T>>();
			String result = null;
			paraBuffer.append("token").append("=").append(token);
			//迭代 设置参数
			try{
				for(Map.Entry<String, Object> entry:map.entrySet()){
					 paraBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
				 result = HttpClientUtil.httpPostRequest(uri, paraBuffer.toString());
				 jdApiResult = JDHttpResult.parseToList(result, c);
			}catch(Exception e){
				result= null;
				jdApiResult.setSuccess(false);
				e.printStackTrace();
				log.error("通用方法访问失败:"+e.getMessage());
			}
		return jdApiResult;
	}
		/**
	     * 根据SKU查询商品售卖价
	     */
	    public static JDApiResult<List<Map>> getSellPrice(String token,String sku){
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sku", sku);
	        return currencyMethodToList(token, map, Map.class, JDConfig.GET_SELL_PRICE_URL);
	    }
	    
	    /**
         * 根据SKU获取所有图片信息
         */
        public static JDApiResult<List<Map>> getSkuImage(String token,String sku){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sku", sku);
            return currencyMethodToList(token, map, Map.class, JDConfig.SKU_IMAGE_URL);
        }
        
        /**
         * 商品列表页获取库存接口
         * @param token
         * @param sku
         * @param area
         * @return
         */
        public static JDApiResult<List<Map>> getStockById(String token, String sku, String area) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sku", sku);
            map.put("area", area);
            return currencyMethodToList(token, map, Map.class, JDConfig.GET_STOCK_BY_ID_URL);
        }
        
        /**
         * 订单详情页下单时获取库存接口
         * @param token
         * @param skuNums
         * @param area
         * @return
         */
        public static JDApiResult<List<Map>> getNewStockById(String token, String skuNums, String area) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("skuNums", skuNums);
            map.put("area", area);
            return currencyMethodToList(token, map, Map.class, JDConfig.GET_NEW_STOCK_BY_ID_URL);
        }
        
        /**
         * 商品好评度查询
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<List<Map>> getCommentSummarys(String token, String sku) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sku", sku);
            return currencyMethodToList(token, map, Map.class, JDConfig.GET_COMMENT_SUMMARYS_URL);
        }
        
        /**
         * 运费查询接口
         * @param token
         * @param sku
         * @param province
         * @param city
         * @param county
         * @param town
         * @param paymentType
         * @return
         */
        public static JDApiResult<Map> getFreight(String token, String sku, int province, int city, int county, int town, int paymentType) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sku", sku);
            map.put("province", province);
            map.put("city", city);
            map.put("county", county);
            map.put("town", town);
            map.put("paymentType", paymentType);
            //{"baseFreight":6,"freight":6,"remoteRegionFreight":0,"remoteSku":"[]"}
            return currencyMethod(token, map, Map.class, JDConfig.GET_FREIGHT_URL);
        }
        
        /**
         * 商品可售验证
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<List<Map>> check(String token, String skuIds) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("skuIds", skuIds);
            return currencyMethodToList(token, map, Map.class, JDConfig.CHECK_URL);
        }
        
        /**
         * 统一下单
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<Map> submitOrder(String token, JDOrder order) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("thirdOrder", order.getThirdOrder());
            map.put("sku", order.getSku());
            map.put("name", order.getName());
            map.put("province", order.getProvince());
            map.put("city", order.getCity());
            map.put("county", order.getCounty());
            map.put("address", order.getAddress());
            map.put("zip", order.getZip());
            map.put("phone", order.getPhone());
            map.put("mobile", order.getMobile());
            map.put("email", order.getEmail());
            map.put("remark", order.getRemark());
            map.put("invoiceState", order.getInvoiceState());
            map.put("invoiceType", order.getInvoiceType());
            map.put("selectedInvoiceTitle", order.getSelectedInvoiceTitle());
            map.put("companyName", order.getCompanyName());
            map.put("invoiceContent", order.getInvoiceContent());
            map.put("paymentType", order.getPaymentType());
            map.put("isUseBalance", order.getIsUseBalance());
            map.put("submitState", order.getSubmitState());
            map.put("invoiceName", order.getInvoiceName());
            map.put("invoicePhone", order.getInvoicePhone());
            map.put("invoiceProvice", order.getInvoiceProvice());
            map.put("invoiceCity", order.getInvoiceCity());
            map.put("invoiceCounty", order.getInvoiceCounty());
            map.put("invoiceAddress", order.getInvoiceAddress());
            map.put("doOrderPriceMode", order.getDoOrderPriceMode());
            map.put("orderPriceSnap", order.getOrderPriceSnap());
            map.put("reservingDate", order.getReservingDate());
            map.put("installDate", order.getInstallDate());
            map.put("needInstall", order.isNeedInstall());
            map.put("promiseDate", order.getPromiseDate());
            map.put("promiseTimeRange", order.getPromiseTimeRange());
            map.put("promiseTimeRangeCode", order.getPromiseTimeRangeCode());
            return currencyMethod(token, map, Map.class, JDConfig.SUBMIT_ORDER_URL);
        }
        
        /**
         * 确认预占库存订单
         * @param token
         * @param jdOrderId
         * @return 错误码3103，代表该订单已确认下单，不需要重复确认
         */
        public static JDApiResult<Map> confirmOrder(String token, String jdOrderId) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jdOrderId", jdOrderId);
            return currencyMethod(token, map, Map.class, JDConfig.CONFIRM_ORDER_URL);
        }
        
        /**
         * 取消订单
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<Boolean> cancel(String token, String jdOrderId) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jdOrderId", jdOrderId);
            //{"success":false,"resultMessage":"订单不存在","resultCode":"3401","result":false}
            return currencyMethod(token, map, Boolean.class, JDConfig.CANCEL_URL);
        }
        
        /**
         * 支付
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<Boolean> doPay(String token, String jdOrderId) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jdOrderId", jdOrderId);
            //{"success":false,"resultMessage":"订单不存在","resultCode":"3401","result":false}
            return currencyMethod(token, map, Boolean.class, JDConfig.DOPAY_URL);
        }
        
       /**
        * 订单反查接口
        * 根据客户系统订单号查询京东订单
        * @param token
        * @param thirdOrder
        * @return
        */
        public static JDApiResult<Map> selectJdOrderIdByThirdOrder(String token, String thirdOrder) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("thirdOrder", thirdOrder);//客户系统订单号
            return currencyMethod(token, map, Map.class, JDConfig.SELECT_JDORDERID_BY_THIRDORDER_URL);
        }
        
        /**
         * 查询京东订单信息接口
         * @param token
         * @param jdOrderId
         * @return
         */
        public static JDApiResult<Map> selectJdOrder(String token, String jdOrderId) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jdOrderId", jdOrderId);//客户系统订单号
            return currencyMethod(token, map, Map.class, JDConfig.SELECT_JDORDER_URL);
        }
        
        
        /**
         * 校验某订单中某商品是否可以提交售后服务
         * @param token
         * @param param 参数示例：{"jdOrderId":40245152920,"skuId":800032}
         * @return
         */
        public static JDApiResult<Integer> getAvailableNumberComp(String token,String param){
        	 Map<String, Object> map = new HashMap<String, Object>();
             map.put("param", param);
             return currencyMethod(token, map, Integer.class, JDConfig.GET_AVAILABLE_NUMBER_COMP_URL);
        }
        
        /**
         *根据订单号、商品编号查询支持的服务类型
         * 
         * @param jdOrderId
         * @param skuId
         * @return
         */
        public static JDApiResult<List<ComponentExport>> getCustomerExpectComp(String token,String param){
        	 Map<String, Object> map = new HashMap<String, Object>();
             map.put("param", param);
             return currencyMethodToList(token, map, ComponentExport.class, JDConfig.GET_CUSTOMER_EXPECT_COMP_URL);
        }
        
        /**
         * 根据订单号、商品编号查询支持的商品返回京东方式
         */
        public static JDApiResult<List<ComponentExport>> getWareReturnJdComp(String token,String param){
        	 Map<String, Object> map = new HashMap<String, Object>();
             map.put("param", param);
             return currencyMethodToList(token, map, ComponentExport.class, JDConfig.GET_WARE_RETURN_Jd_COMP_URL);
        }
        
        
        /**
         * 根据服务单号查询服务单明细信息
         * @param {"afsServiceId":100011597,"appendInfoSteps":[]}
         * appendInfoSteps:不设置数据表示只获取服务单主信息、商品明细以及客户信息；1、代表增加获取售后地址信息 2、代表增加获取客户发货信息 3、代表增加获取退款明细 4、 增加获取跟踪信息 5.获取允许的操作信息 
         */
        
        public static JDApiResult<CompatibleServiceDetailDTO> getServiceDetailInfo(String token,String param){
        	 Map<String, Object> map = new HashMap<String, Object>();
             map.put("param", param);
             return currencyMethod(token, map, CompatibleServiceDetailDTO.class, JDConfig.GET_SERVICE_DETAIL_INFO_URL);
        }
        
        /**
         * 根据客户账号和订单号分页查询服务单概要信息
         * @param {"jdOrderId":40215143944,"pageIndex":1,"pageSize":10}
         * 返回示例 {"success": true, "resultMessage": "","resultCode": "0","result": {
        "serviceInfoList": [{"afsServiceId": 381766106,"customerExpect": 10, "customerExpectName": "退货","afsApplyTime": "2018-01-09 16:37:28",
         "orderId": 70908914949,"wareId": 639585,"wareName": "海吉亚扁线牙线棒单支独立包装60支装","afsServiceStep": 10,
           "afsServiceStepName": "申请阶段","cancel": 1
            }],"totalNum": 1,"pageSize": 10,"pageNum": 1,"pageIndex": 1
    }
         */
        public static JDApiResult<AfsServicebyCustomerPinPage> getServiceListPage(String token,String param){
        	 Map<String, Object> map = new HashMap<String, Object>();
             map.put("param", param);
             return currencyMethod(token, map, AfsServicebyCustomerPinPage.class, JDConfig.GET_SERVICE_LIST_PAGE_URL);
        }
        
        /**
         * 取消服务单/客户放弃
         * @Param serviceIdList  服务单号集合
         * @Param approveNotes   审核意见
         * @param {"serviceIdList":[1,2,3],"approveNotes"："审核意见"}
         */
        public static JDApiResult<Boolean> auditCancel(String token,String param){
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("param", param);
            return currencyMethod(token, map, Boolean.class, JDConfig.AUDIT_CANCEL_URL);
        }
        
        /**
         *服务单保存申请
         *@param param={"jdOrderId":41220440659,"customerExpect":30,
         *"questionDesc":"123","isNeedDetectionReport":true,"questionPic":
         *null,"isHasPackage":true,"packageDesc":0,"asCustomerDto":
         *{"customerContactName":"yhj","customerTel":"13810343698",
         *"customerMobilePhone":"13810343698","customerEmail":"123456@163.com",
         *"customerPostcode":"065201"},"asPickwareDto":{"pickwareType":40,
         *"pickwareProvince":1,"pickwareCity":72,"pickwareCounty":2839,
         *"pickwareVillage":0,"pickwareAddress":"test"},
         *"asReturnwareDto":{"returnwareType":10,"returnwareProvince":2839,
         *"returnwareCity":72,"returnwareCounty":1,"returnwareVillage":0,
         *"returnwareAddress":"111"},"asDetailDto":{"skuId":712609,"skuNum":1}}
         */
        public static JDApiResult<String> createAfsApply(String token,String param){
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("param", param);
            return currencyMethod(token, map, String.class, JDConfig.CREATE_AFSAPPLY);
        }
        
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		
		AccessToken token = JdApi.getAccessToken().getResult();
		System.out.println(token.getAccess_token());
//		System.out.println(token.getRefresh_token());
//		//池子接口分解JSON示例
//		JDApiResult<List<Map>> jdApiResult = JdApi.getJdCommodityPool("OoFrxsttxoW7qRkmb30VEnXwg");
//		System.out.println(jdApiResult.getResult().get(0).get("name"));  //名称
//		System.out.println(jdApiResult.getResult().get(0).get("page_num")); //池子编号
//		
//		//分类接口分解JSON示例
//		List<Map<String,String>> list = (List<Map<String,String>>)JdApi.getCategory("ia0wkgceR8Ta4K2KGN27Yp52I",1,20,null).getResult().get("categorys");
//		System.out.println(list.get(0).get("catId"));   //分类ID
//		System.out.println(list.get(0).get("parentId")); //父ID
//		System.out.println(list.get(0).get("name"));    //名称
//		System.out.println(list.get(0).get("catClass")); //0：一级分类；1：二级分类；2：三级分类；
//		System.out.println(list.get(0).get("state"));    //1：有效；0：无效；
		
//		//根据商品池子编号获取商品SKU接口分页 分解JSON示例
//		JDApiResult<Map> jdApiResult = JdApi.getSkuByPage("OoFrxsttxoW7qRkmb30VEnXwg",3,1);
//		JSONArray jsonArray = JSON.parseArray(jdApiResult.getResult().get("skuIds").toString());
//		Object[] skuIds = jsonArray.toArray();
// 		System.out.println(skuIds.length);  //skuid
// 		System.out.println(jdApiResult.getResult().get("pageCount")); //页数 用于判断是否还有下一页
		
//		//根据商品池子编号获取商品SKU接口 分解JSON示例
//		JDApiResult<String> jdApiResult = JdApi.getSkuByPageNum("ia0wkgceR8Ta4K2KGN27Yp52I", 1);
//		String[] skuIds = jdApiResult.getResult().split(",");
//		System.out.println(skuIds[0]);
//		
//		//根据商品SKU获取商品详细信息
//		JDApiResult<Map> jdApiResult = JdApi.getDetailBySku("EsDV5rLgNeOV3N0pJi2H1bn6c", "1842804", false);
//		System.out.println(jdApiResult.getResult().get("param"));
//		System.out.println(jdApiResult.getResult().get("introduction"));
		
//		//根据地址获取京东地址编码
//		//{"success":true,"resultMessage":"操作成功","resultCode":"0000","result":{"provinceId":"22","county":"武侯区","cityId":"1930","province":"四川","townId":"52198","town":"城区","countyId":"50947","nation":"中国","city":"成都市","nationId":"4744"}}
		/*JDApiResult<Map> jdApiResult = JdApi.getJDAddressFromAddress("B6zwVLwRqVFWApK4oVwKoL4SM", "广东省深圳市福田区深南中路2039号核电大厦1401室");
		System.out.println(jdApiResult.getResult().get("nationId")); //4744
		System.out.println(jdApiResult.getResult().get("nation"));   //中国
		System.out.println(jdApiResult.getResult().get("cityId"));   //1607
		System.out.println(jdApiResult.getResult().get("city"));     //深圳市
		System.out.println(jdApiResult.getResult().get("provinceId")); //19
		System.out.println(jdApiResult.getResult().get("county"));   //
		System.out.println(jdApiResult.getResult().get("townId"));
		System.out.println(jdApiResult.getResult().get("town"));
		System.out.println(jdApiResult.getResult().get("countyId"));*/
		
//		//返回详情对象
		/*JDApiResult<JdProductDto> jdApiResult = JdApi.getDetailObjBySku("B6zwVLwRqVFWApK4oVwKoL4SM", "5181380", false);
		System.out.println(jdApiResult.getResult().getSku());*/
//		
//	    //[{"price":38.00,"skuId":1842804,"jdPrice":39.00}]
//		JDApiResult<List<Map>> result = JdApi.getSellPrice(JdApi.getAccessToken(null).getResult().getAccess_token(), "1842804");
//		System.out.println(result.getResult().get(0).get("price"));
		
	/*    String token = JdApi.getAccessToken(null).getResult().getAccess_token();
		
	    String token = JdApi.getAccessToken(null).getResult().getAccess_token();
//	    
//	  //获取地址
//        String ad = "中国广东省深圳市福田区深南中路";
//        //{"success":true,"resultMessage":"操作成功","resultCode":"0000","result":{"provinceId":"19","county":"福田区","cityId":"1607","province":"广东","townId":"","town":"","countyId":"3639","nation":"中国","city":"深圳市","nationId":"4744"}}
//        JDApiResult<Map> add = JdApi.getJDAddressFromAddress(JdApi.getAccessToken(null).getResult().getAccess_token(), ad);
//        System.out.println(add.getResult().get("provinceId") + "_" + add.getResult().get("cityId")  + "_" +add.getResult().get("countyId"));
        
//	    String token = JdApi.getAccessToken(null).getResult().getAccess_token();
//        
//        JdApi.getSkuImage(token, "1842804");
//        JdApi.doPay(token,"20171228");
//        JdApi.cancel(token,"20171228");
//        System.out.println(JdApi.selectJdOrderIdByThirdOrder(token,"17122915273850405472000256").getMessage());
		JdApi.getSkuImage(token, "1842804");
		JdApi.doPay(token,"20171228");
		JdApi.cancel(token,"20171228");
	    */
		//JdApi.getSkuImage(token, "1842804");
		//JdApi.doPay(token,"20171228");
		//JdApi.cancel(token,"20171228");
	    
	  //获取地址
        //String ad = "广东省深圳市福田区深南中路2039号核电大厦";
        //{"success":true,"resultMessage":"操作成功","resultCode":"0000","result":{"provinceId":"19","county":"福田区","cityId":"1607","province":"广东","townId":"","town":"","countyId":"3639","nation":"中国","city":"深圳市","nationId":"4744"}}
        //JDApiResult<Map> add = JdApi.getJDAddressFromAddress(JdApi.getAccessToken(null).getResult().getAccess_token(), ad);
//        System.out.println(add.get("provinceId") + "_" + add.get("cityId")  + "_" +add.get("countyId"));
	  //获取地址
        //String ad = "广东省深圳市福田区深南中路2039号核电大厦";
        //{"success":true,"resultMessage":"操作成功","resultCode":"0000","result":{"provinceId":"19","county":"福田区","cityId":"1607","province":"广东","townId":"","town":"","countyId":"3639","nation":"中国","city":"深圳市","nationId":"4744"}}
        //JDApiResult<Map> add = JdApi.getJDAddressFromAddress(JdApi.getAccessToken(null).getResult().getAccess_token(), ad);
//        System.out.println(add.get("provinceId") + "_" + add.get("cityId")  + "_" +add.get("countyId"));
		
		//param={"jdOrderId":40245152920,"skuId":800032}
		/*String param="{\"jdOrderId\":70801828045,\"skuId\":639585}";
		System.out.println(param);
		JdApi.getAvailableNumberComp("lLbLHPAgcTsp86yq0IH9rZymE", param);*/
		
		/*//String param="{\"jdOrderId\":70801828045,\"skuId\":639585}";
		System.out.println(param);
		JdApi.getCustomerExpectComp("lLbLHPAgcTsp86yq0IH9rZymE",param);*/
		
		/*String param="{\"jdOrderId\":70908914949,\"skuId\":639585}";
		System.out.println(param);
		JdApi.getWareReturnJdComp("lLbLHPAgcTsp86yq0IH9rZymE",param);*/
		
		//String param="{\"jdOrderId\":70801828045,\"pageIndex\":1,\"pageSize\":10}";
		//System.out.println(param);
		//JDApiResult<AfsServicebyCustomerPinPage> apiResult =  JdApi.getServiceListPage("5Nd3dXz6Li2zGJ7J2r7Zu2dEf",param);
		//System.out.println(apiResult.getResult().getServiceInfoList().get(0).getAfsServiceStepName());
		//{"afsServiceId":100011597,"appendInfoSteps":[]}
		
		//JdApi.selectJdOrder("lLbLHPAgcTsp86yq0IH9rZymE", "70801828045");
		
		/*String param="{\"afsServiceId\":381766106,\"appendInfoSteps\":[1,2,3,4,5]}";
		JdApi.getServiceDetailInfo("lLbLHPAgcTsp86yq0IH9rZymE", param);*/
		
		
		
		//测试售后服务
		//客户信息
//		AfterSaleCustomerDto asCustomerDto = new AfterSaleCustomerDto();
//		asCustomerDto.setCustomerContactName("陈建强");
//		asCustomerDto.setCustomerTel("18566286811");
//		asCustomerDto.setCustomerEmail("262347503@qq.com");
//		asCustomerDto.setCustomerMobilePhone("18566286811");
//		asCustomerDto.setCustomerPostcode("518000");
		
		//取件地址
//		AfterSalePickwareDto afterSalePickwareDto = new AfterSalePickwareDto();
//		afterSalePickwareDto.setPickwareType(AfterSalePickwareDto.PICKWARETYPE_4);
//		afterSalePickwareDto.setPickwareProvince(19);
//		afterSalePickwareDto.setPickwareCity(1607);
//		afterSalePickwareDto.setPickwareCounty(3639);
//		afterSalePickwareDto.setPickwareVillage(0);
//		afterSalePickwareDto.setPickwareAddress("广东省深圳市福田区深南中路2039号核电大厦1401室");
		
		//返件地址
//		AfterSaleReturnwareDto afterSaleReturnwareDto = new AfterSaleReturnwareDto();
//		afterSaleReturnwareDto.setReturnwareType(AfterSaleReturnwareDto.RETURNWARETYPE_10);
//		afterSaleReturnwareDto.setReturnwareProvince(19);
//		afterSaleReturnwareDto.setReturnwareCity(1607);
//		afterSaleReturnwareDto.setReturnwareCounty(3639);
//		afterSaleReturnwareDto.setReturnwareVillage(0);
//		afterSaleReturnwareDto.setReturnwareAddress("广东省深圳市福田区深南中路2039号核电大厦1401室");
//		
//		AfterSaleDetailDto afterSaleDetailDto = new AfterSaleDetailDto();
//		afterSaleDetailDto.setSkuId(new Long(639585));
//		afterSaleDetailDto.setSkuNum(1);
//		
//		AfterSaleDto afterSaleDto = new AfterSaleDto();
//		afterSaleDto.setJdOrderId(new Long("70908914949"));
//		afterSaleDto.setCustomerExpect(AfterSaleDto.CUSTOMER_EXPECT_10);
//		afterSaleDto.setQuestionDesc("不想要了");
//		afterSaleDto.setNeedDetectionReport(false);
//		afterSaleDto.setHasPackage(true);
//		afterSaleDto.setPackageDesc(AfterSaleDto.PACKAGE_DESC_10);
//		afterSaleDto.setAsCustomerDto(asCustomerDto);
//		afterSaleDto.setAsDetailDto(afterSaleDetailDto);
//		afterSaleDto.setAsPickwareDto(afterSalePickwareDto);
//		afterSaleDto.setAsReturnwareDto(afterSaleReturnwareDto);
		
		//System.out.println(JSONObject.toJSONString(afterSaleDto));
		
		//JdApi.createAfsApply("lLbLHPAgcTsp86yq0IH9rZymE", JSONObject.toJSONString(afterSaleDto));
	
		/*String param="{\"serviceIdList\":[381766106],\"approveNotes\":\"先用着先不退\"}";
		JdApi.auditCancel("lLbLHPAgcTsp86yq0IH9rZymE", param);*/
		
		//String ad = "广东省深圳市福田区深南中路2039号核电大厦";
        //{"provinceId":"19","county":"福田区","cityId":"1607","province":"广东","townId":"","town":"","countyId":"3639","nation":"中国","city":"深圳市","nationId":"4744"}
        //JDApiResult<Map> add = JdApi.getJDAddressFromAddress(token.getAccess_token(), ad);
        //System.out.println(add.getResult().toString());
		
		JDApiResult<Map> freight = JdApi.getFreight(token.getAccess_token(), "[{\"skuId\":639585, \"num\":1}]", 19, 1607, 3639, 0, 101);
		System.out.println(freight.getMessage());
		System.out.println(freight.getResult().toString());
	} 		    
}
