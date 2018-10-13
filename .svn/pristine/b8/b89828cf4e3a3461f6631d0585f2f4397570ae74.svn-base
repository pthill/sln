package com.sln.core.jd.util;

/**
 * 京东推送类型
 * @author li.biao
 */
public class JDMessageType {

	/** 
	 *  订单拆分
	 *  <li>{"id":推送id, "result" : {"pOrder" :父订单id} , "type": 1, "time":推送时间}</li>
	 *  <li></li><p>京东订单可能会被多次拆单； 例如：订单1 首先被拆成订单2、订单3；然后订单2有继续被拆成订单4、订单5；
	 *  最终订单1的子单是订单3、订单4、订单5；每拆一次单我们都会发送一次拆单消息，但父订单号只会传递订单1（原始单），需要通过查询接口获取到最新所有子单，进行相关更新；</li>
	 *  </p>
	 *   */
    public static final int ORDER_SPLIT = 1 ;
    /** 代表商品价格变更
	 *  <li>{"id":推送id, "result":{"skuId" : 商品编号 }, "type": 2, "time":推送时间}</li>
     *  */
    public static final int PRODUCT_PRICE = 2 ;
    /** 商品上下架变更消息
	 *  <li>{"id":推送id, "result":{"skuId" : 商品编号 }, "type": 4 "time":推送时间}</li>
     *  */
    public static final int PRODUCT_STATE = 4 ;
    /** 代表添加、删除商品池内商品
	 *  <li>{"id":推送id, "result":{"skuId": 商品编号, "page_num":商品池编号, "state":"1添加，2删除"}, "type" : 6, "time":推送时间}</li>
     *  */
    public static final int PRODUCT_UPDATE = 6 ;
    /** 代表订单取消（不区分取消原因）
	 *  <li>{"id":推送id, "result":{" orderId": 京东订单编号 }, "type" : 10, "time":推送时间}</li>
     *  */
    public static final int ORDER_CANCEL = 10 ;
    /** 支付失败消息
	 *  <li>{"id":推送id, "result":{" orderId": 京东订单编号}, "type" : 14, "time":推送时间}</li>
     *  */
    public static final int PAY_FAIL = 14 ;
    /** 7天未支付取消消息/未确认取消（cancelType, 1: 7天未支付取消消息; 2: 未确认取消）
	 *  <li>{"id":推送id, "result":{"orderId": 京东订单编号, "cancelType": 取消类型}， "type" : 15, "time":推送时间}</li>
     *  */
    public static final int ORDER_AUTO_CANCEL = 15 ;
    /** 商品介绍及规格参数变更消息
	 *  <li>{"id":推送id, "result":{"skuId" : 商品编号 } "type" : 16, "time":推送时间}</li>
     *  */
    public static final int PRODUCT_PARAM = 16 ;
    
	
}
