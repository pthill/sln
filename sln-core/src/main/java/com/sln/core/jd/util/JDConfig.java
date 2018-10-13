package com.sln.core.jd.util;


/**
 * 京东接口配置
 * @author li.biao
 */
public class JDConfig {

    /**即对接账号(由京东人员提供)*/
    public static final String APP_KEY = "9c2e1acaba784f6296a2f72ab683e972";
    /**即对接账号的密码 (由京东人员提供)*/
    public static final String APP_SECRET = "f33765c267334080bd6356200445f53f";
    /**京东的用户名
		（注意：如果username是中文，
		在url参数里面，需要将username使用urf-8格式进行UrlEncode编码，
		在生成sign签名串时，不需要进行编码，使用原文！）
     */ 
    public static final String USERNAME = "核电海核淘2016";
    /**京东的用户密码，必须是md5加密后的字符串，不要使用原文！*/
    public static final String PASSWORD = "25d55ad283aa400af464c76d713c07ad";
    
    /**行数*/
    public static final int PAGE_SIZE = 500;
    
    /**grant_type类型*/
    public static final String GRANT_TYPE ="access_token";
    
    /** 商品的主图片地址前缀和尺寸*/
    //n0(最大图)、n1(350*350px)、n2(160*160px)、n3(130*130px)、n4(100*100px) 
    public static final String IMAGE_PATH = "http://img13.360buyimg.com/n0/";
    public static final String IMAGE_PATH_350 = "http://img13.360buyimg.com/n1/";
    public static final String IMAGE_PATH_160 = "http://img13.360buyimg.com/n2/";
    public static final String IMAGE_PATH_130 = "http://img13.360buyimg.com/n3/";
    public static final String IMAGE_PATH_100 = "http://img13.360buyimg.com/n4/";
    
    /**信息推送接口*/
    public static final String MESSAGE_URL = "https://bizapi.jd.com/api/message/get" ; 
    
    /**信息推送删除接口*/
    public static final String MESSAGE_DEL_URL = "https://bizapi.jd.com/api/message/del" ; 
    
    /**access_token请求地址*/
    public static final String ACCESS_TOKEN_URL = "https://bizapi.jd.com/oauth2/accessToken";
    
    /**刷新access_token请求地址*/
    public static final String REFRESH_TOKEN_URL = "https://bizapi.jd.com/oauth2/refreshToken";
    
    /**获取商品池请求地址*/
    public static final String GET_PAGE_NUM_URL = "https://bizapi.jd.com/api/product/getPageNum";
    
    /**获取分类信息列表*/
    public static final String GET_CATEGORYS_URL = "https://bizapi.jd.com/api/product/getCategorys";
    
    /**根据分类id获取分类信息*/
    public static final String GET_CATEGORY_URL ="https://bizapi.jd.com/api/product/getCategory";
    
    /**根据商品池子编号获取商品SKU*/
    public static final String GET_SKU_URL = "https://bizapi.jd.com/api/product/getSku";
    
    /** 获取池内商品编号接口-品类商品池（兼容老接口*/
    public static final String GET_SKU_URL_2 = "https://bizapi.jd.com/api/product/getSkuByPage";
    
    /** 3.4 获取商品详细信息接口*/
    public static final String GET_DETAIL_URL = "https://bizapi.jd.com/api/product/getDetail";
    
    /** 获取商品上下架状态接口*/
    public static final String GET_SKU_STATE_URL = "https://bizapi.jd.com/api/product/skuState";
    
    /** 批量查询协议价价格*/
    public static final String GET_PRICE_URL = "https://bizapi.jd.com/api/price/getPrice";
    
    /**根据地址查询京东地址编码*/
    public static final String GET_JDADDRESS_FROM_ADDRESS_URL = "https://bizapi.jd.com/api/area/getJDAddressFromAddress";
    
    /** 批量查询商品售卖价*/
    public static final String GET_SELL_PRICE_URL = "https://bizapi.jd.com/api/price/getSellPrice";
    
    /** 获取所有图片信息*/
    public static final String SKU_IMAGE_URL = "https://bizapi.jd.com/api/product/skuImage";
    
    /** 商品列表页获取库存接口*/
    public static final String GET_STOCK_BY_ID_URL = "https://bizapi.jd.com/api/stock/getStockById";
    
    /** 订单详情页、下单页使用获取库存接口*/
    public static final String GET_NEW_STOCK_BY_ID_URL = "https://bizapi.jd.com/ api/stock/getNewStockById";
    
    /** 商品好评度查询*/
    public static final String GET_COMMENT_SUMMARYS_URL = "https://bizapi.jd.com/api/product/getCommentSummarys";
    
    /** 运费查询接口*/
    public static final String GET_FREIGHT_URL = "https://bizapi.jd.com/api/order/getFreight";
    
    /** 商品可售验证接口*/
    public static final String CHECK_URL = "https://bizapi.jd.com/api/product/check";
    
    /** 统一下单接口*/
    public static final String SUBMIT_ORDER_URL = "https://bizapi.jd.com/api/order/submitOrder";
    
    /** 确认预占库存订单接口*/
    public static final String CONFIRM_ORDER_URL = "https://bizapi.jd.com/api/order/confirmOrder";
    
    /** 取消未确认订单接口*/
    public static final String CANCEL_URL = "https://bizapi.jd.com/api/order/cancel";
    
    /** 发起支付接口*/
    public static final String DOPAY_URL = "https://bizapi.jd.com/api/order/doPay";
    
    /** 订单反查接口*/
    public static final String SELECT_JDORDERID_BY_THIRDORDER_URL = "https://bizapi.jd.com/api/order/selectJdOrderIdByThirdOrder";
    
    /** 查询京东订单信息接口*/
    public static final String SELECT_JDORDER_URL = "https://bizapi.jd.com/api/order/selectJdOrder";
	
    /**校验某订单中某商品是否可以提交售后服务*/
    public static final String GET_AVAILABLE_NUMBER_COMP_URL = "https://bizapi.jd.com/api/afterSale/getAvailableNumberComp";
    
    /**根据订单号、商品编号查询支持的服务类型*/
    public static final String GET_CUSTOMER_EXPECT_COMP_URL ="https://bizapi.jd.com/api/afterSale/getCustomerExpectComp";
    
    /**根据订单号、商品编号查询支持的商品返回京东方式*/
    public static final String GET_WARE_RETURN_Jd_COMP_URL ="https://bizapi.jd.com/api/afterSale/getWareReturnJdComp";
    
    /**根据客户账号和订单号分页查询服务单概要信息*/
    public static final String  GET_SERVICE_LIST_PAGE_URL ="https://bizapi.jd.com/api/afterSale/getServiceListPage";
   
    /**根据服务单号查询服务单明细信息*/
    public static final String GET_SERVICE_DETAIL_INFO_URL ="https://bizapi.jd.com/api/afterSale/getServiceDetailInfo";
    
    /**取消服务单/客户放弃*/
    public static final String AUDIT_CANCEL_URL ="https://bizapi.jd.com/api/afterSale/auditCancel";

    /**服务单保存申请*/
    public static final String CREATE_AFSAPPLY = "https://bizapi.jd.com/api/afterSale/createAfsApply";
}
