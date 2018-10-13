<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<style>
	.myorder{
			width:80%;margin-left:10px
		}
</style>
<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">订单详情&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder wrapper_main-wd'>
				<h3>订单详情</h3>
				<#if order.orderState != 6>
				<div class='mc-box clearfix'>
					<!-- 进度条 -->
					<ul class="pro clearfix">
						<li>
							<div class="bar">
								<#include "/front/member/orderprogress.ftl" />
							</div>
						</li>
					</ul>
				</div>
				</#if>
				<!-- 订单基本信息 -->
				<div class='mc-box'>
					<div class="payment-info">
						<h3>基本信息</h3>
						<table cellpadding='0' cellspacing='0' width='100%'>
							<tbody>
								<tr>
									<td width='33.3333%'>订单编号：${(order.orderSn)!''}</td>
									<td width='33.3333%'>
										<div class='p-num'>
											订单类型：<@cont.codetext value="${(order.orderType)!0}" codeDiv="ORDER_TYPE"/>
										</div>
									</td>
									<td width='33.3333%' >
										<div class='p-num'>
											订单状态：<@cont.codetext value="${(order.orderState)!0}" codeDiv="ORDERS_ORDER_STATE"/>
										</div>
									</td>
								</tr>
								<tr>
									<td> 下单时间：${(order.createTime)?string("yyyy-MM-dd HH:mm:ss")!''}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 付款信息 -->
				<div class='mc-box'>
					<div class="payment-info">
						<h3>付款信息</h3>
						<table cellpadding='0' cellspacing='0' width='100%'>
							<tbody>
								<tr>
									<td width='33.3333%' id='daiFuName'>
										<div class='p-num'>
											付款状态：<@cont.codetext value="${(order.paymentStatus)!0}" codeDiv="ORDER_PAYMENT_STATUS"/>
										</div>
									</td>
									<td width='33.3333%' id='daiFuPeople'>付款方式：${(order.paymentName)!""}</td>
									<td width='33.3333%' ></td>
								</tr>
								<#if order.orderType?? && order.orderType == 6>
								<tr>
									<td> 商品积分：+&nbsp;&nbsp;${(order.moneyOrder)!'' }分</td>
									<td> 积分支付：-&nbsp;&nbsp;${(order.moneyOrder)!'' }分</td>
								</tr>
								<tr>
									<td> 通用积分：-&nbsp;&nbsp;&nbsp;&nbsp;${(order.integral)!'' }分</td>
                                    <td> 专项积分：-&nbsp;&nbsp;&nbsp;&nbsp;${(order.specialIntegral)!'' }分</td>
								</tr>
								<tr>
                                    <td> 实付金额：-&nbsp;&nbsp;&nbsp;&nbsp;${(order.moneyIntegral)!'' }元</td>
								</tr>
								<#else>
								<tr>
									<td> 商品金额：+&nbsp;￥${(order.moneyProduct)?string('0.00')!'' }</td>
									<td> 运费金额：+&nbsp;￥${(order.moneyLogistics)?string('0.00')!'' }</td>
									<td> 优惠总额：-&nbsp;&nbsp;￥${(order.moneyDiscount)?string('0.00')!'' }</td>
								</tr>
								<tr>
									<td> 现金支付：-&nbsp;&nbsp;￥${(order.moneyPaidReality)?string('0.00')!'' }</td>
									<td> 余额支付：-&nbsp;&nbsp;￥${(order.moneyPaidBalance)?string('0.00')!'' }</td>
									<td> 积分支付：-&nbsp;&nbsp;￥${(order.moneyIntegral)?string('0.00')!'' }</td>
								</tr>
								<tr>
									<td> 订单金额：&nbsp;&nbsp;&nbsp;￥${(order.moneyOrder)?string('0.00')!'' }</td>
									<td>
										<#if order.paymentStatus??>
											<#if (order.paymentStatus==1)>
									 			实付金额：&nbsp;&nbsp;&nbsp;￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!'' }
											<#else>
												应付金额：&nbsp;&nbsp;&nbsp;￥${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }
											</#if>
										</#if>
									</td>
									<td> </td>
								</tr>
								</#if>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 发票信息 -->
				<div class='mc-box'>
					<div class="payment-info">
						<h3>发票信息</h3>
						<table cellpadding='0' cellspacing='0' width='100%'>
							<#if order.invoiceStatus != 0 >
							<tbody>
								<tr>
									<td width="33.333%">发票类型：普通发票&nbsp;
										<#if order.invoiceStatus != 1 >
											个人
										<#elseif order.invoiceStatus != 2 >
											单位
										</#if>
									</td>
									<td width="33.333%">发票抬头：${(order.invoiceTitle)!'' }</td>
									<td width="33.333%">发票内容：${(order.invoiceType)!'' }</td>
								</tr>
							</tbody>
							<#else>
							<tbody>
								<tr>
									<td width="33.333%">不要发票</td>
									<td width="33.333%"></td>
									<td width="33.333%"></td>
								</tr>
							</tbody>
							</#if>
						</table>
					</div>
				</div>
				<!-- 收货人信息 -->
				<div class='mc-box'>
					<div class="payment-info">
						<h3>收货人信息</h3>
						<table cellpadding='0' cellspacing='0' width='100%'>
							<tbody>
								<tr>
									<td>收&nbsp;货&nbsp;人&nbsp;：${(order.name)!''}</td>
								</tr>
								<tr>
									<td>地　　址：${(order.addressAll)!''}&nbsp;${(order.addressInfo)!''}</td>
								</tr>
								<tr>
									<td>手机号码：<#if commUtil??>${commUtil.hideMiddleStr(order.mobile,3,4)}</#if></td>
								</tr>
								<tr>
									<td>邮　　编：${(order.zipCode)!''}</td>
								</tr>
								<tr>
									<td>邮　　箱：${(order.email)!''}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 订单跟踪 -->
				<div class='mc-box'>
					<div class="payment-info">
							<h3>订单跟踪</h3>
							<table cellpadding='0' cellspacing='0' width='100%'>
								<tbody id='tbody_track'>
									<tr>
										<th class='15%'>
											<strong style="font-size: 14px;color:#222;font-weight: 400;">处理时间</strong>
										</th>	
										<th class='50%'>
											<strong style="font-size: 14px;color:#222;font-weight: 400;">处理信息</strong>
										</th>
										<th class='35%'>
											<strong style="font-size: 14px;color:#222;font-weight: 400;">操作人</strong>
										</th>
									</tr>
								</tbody>
								<tbody>
								<#if orderLogList??>
									<#list orderLogList as orderLog> 
										<tr>
											<td>${orderLog.createTime?string("yyyy-MM-dd HH:mm:ss") }</td>
											<td>${orderLog.content}</td>
											<td>${orderLog.operatingName}</td>
										</tr>
									</#list>
								</#if>
								</tbody>
							</table>
					</div>
				</div>
				<!-- 商品信息 -->
				<div class='mc-box'  style="margin-bottom: 0px;">
					<div class="payment-info">
						<h3>商品信息</h3>
						<dl>
							<dd class='p-list'>
								<table cellpadding='0' cellspacing="0" width='100%' style="margin:0px;">
									<tbody>
										<tr>
											<th width='15%'>商品图片</th>
											<th width='45%'>商品名称</th>
											<th width='10%'>单价</th>
											<th width='10%'>数量</th>
											<th width='10%'>小计</th>
											<th width='10%'>操作</th>
										</tr>
										<#if (order.orderProductList)??>
										<#list (order.orderProductList) as product>
										<tr>
											<td>
												<div class='img-list clearfix' style="padding-left: 30px;">
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' class='img-box' target='_blank'>
													<#if product.source?? &&  product.source == 2>
														<img width='50' height='50' src='${(getImagePathMethod(product.source,product.productCode))!''}${product.masterImg}'>
													<#else>
														<img width='50' height='50' src='${(getImagePathMethod(product.source,product.productCode))!''}${product.productLeadLittle}'>
													</#if>
													</a>
												</div>
											</td>
											<td>
												<div class='al fl'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' class='flk13'>${(product.productName)!''}</a>
												</div>
											</td>
											<td>
												<span class='ftx04'>
													<#if order.orderType?? && order.orderType == 6>
														${(product.actIntegral.price)!''}(积分)
													<#else>
														￥${(product.moneyPrice)?string('0.00')!''}
													</#if>
												</span>
											</td>
											<td>${(product.number)!''}</td>
											<td>
												<span class='ftx04'>
													<#if order.orderType?? && order.orderType == 6>
														${((product.actIntegral.price)*(product.number))!''}(积分)
													<#else>
														￥${(product.moneyAmount)?string('0.00')!''}
													</#if>
												</span>
												<#if product.moneyActSingle?? && product.moneyActSingle &gt; 0 >
												<br>
												<span>立减￥${(product.moneyActSingle)?string('0.00')!''}</span>
												</#if>
											</td>
											<td>
												<#if order.orderState?? && order.paymentCode??>
													<!-- 已完成的订单 才能评价和申请退换货 -->
													<#if (order.orderState==5)>
														<span>
															<#if product?? && product.isEvaluate == 0>
															<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}' target='blank' class='flk13'>评价</a>
															<br>
															</#if>
															<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}' taget='_blank' class='flk13'>申请退换货</a>
														</span>
													<#else>
														—
													</#if>
												</#if>
											</td>
										</tr>
										</#list>
										</#if>
									</tbody>
								</table>
							</dd>
						</dl>
						<!-- end -->
					</div>
				</div>
				<!-- 金额 -->
				<div class='mc-box'>
					<div class='total'>
						<#if order.orderType?? && order.orderType == 6>
							<ul >
								<li style="float:right">
									<div class="tit-proct">${(order.moneyOrder)!'' }分</div>
									<div class="tit-proct">${(order.moneyOrder)!'' }分</div>
								</li>
								<li style="float:right">
									<div class="tit-name">+ 商品积分：</div>
									<div class="tit-name">- 积分支付：</div>
								</li>
							</ul>
							<div class="clr"></div>
							<div class='extra'>
						 			通用积分：
									<span class='ftx04'>
										<b>${(order.integral)!''}分</b>
									</span>
								    专项积分:
                                    <span class='ftx04'>
										<b>${(order.specialIntegral)!''}分</b>
									</span>
									实付金额：
									<span class='ftx04'>
										<b>${(order.moneyIntegral)!''}元</b>
									</span>
							</div>
						<#else>
							<ul >
								<li style="float:right">
									<div class="tit-proct">￥${(order.moneyProduct)?string('0.00')!'0.00' }</div>
									<div class="tit-proct">￥${(order.moneyLogistics)?string('0.00')!'0.00' }</div>
									<div class="tit-proct">￥${(order.moneyDiscount)?string('0.00')!'0.00'}</div>
									<div class="tit-proct">￥${(order.moneyPaidReality)?string('0.00')!'0.00' }</div>
									<div class="tit-proct">￥${(order.moneyPaidBalance)?string('0.00')!'0.00' }</div>
									<div class="tit-proct">￥${(order.moneyIntegral)?string('0.00')!'0.00' }</div>
								</li>
								<li style="float:right">
									<div class="tit-name">+ 商品总额：</div>
									<div class="tit-name">+ 运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</div>
									<div class="tit-name">- 优惠总额：</div>
									<div class="tit-name">- 现金支付：</div>
									<div class="tit-name">- 余额支付：</div>
									<div class="tit-name">- 积分支付：</div>
								</li>
							</ul>
							<div class="clr"></div>
							<div class='extra'>
								<#if order.paymentStatus?? && order.paymentStatus==1>
						 			实付金额：
									<span class='ftx04'>
										<b>￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!''}</b>
									</span>
								<#else>
									应付金额：
									<span class='ftx04'>
										<b>￥${(order.moneyOrder - order.moneyIntegral)?string('0.00')!''}</b>
									</span>
								</#if>
							</div>
						</#if>
					</div>
				</div>
			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script type="text/javascript">
      $(function () {
      		//控制左侧菜单选中
			$("#myorder").addClass("currnet_page");
			
			//订单
	var obj=$(".li-table:eq(0)");//获取每个li索引
    $(".li-table").click(function(){
       $(".li-table").eq($(this).index()).addClass("curr").siblings().removeClass("curr");
        var obj=$(this);
        var table=$(this).data('table');
        $(".hide").removeClass("show");
        $("#table"+table).addClass("show");
    });

    $("#barcode span").mouseover(function(){
    	$("#sn_list").removeClass("hide");
    }).mouseout(function(){
    	$("#sn_list").addClass("hide");
    });
       });
       
</script>
<#include "/front/portal/common/footer.ftl" />