<#include "/front/commons/_headbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/productback.css">

<div class='container'>
	<div class='breadcrumb'>
		<strong class='business-strong'>
			<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html'>首页</a>
		</strong>
		<span>
			&nbsp;>&nbsp;
			<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html'>我的海核云谷</a>
		</span>
		<span>
			&nbsp;>&nbsp;
			<a href='javascript:void(0)'>退货申请查看</a>
		</span>
	</div>
</div>
		
<form id ="productBackForm" action="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/doproductexchange.html">
<!-- 隐藏域 -->
<input type="hidden" name="sellerId" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="seller" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="orderId" value="${(order.id)!''}"> 
<!-- 网单id -->
<input type="hidden" name="orderProductId" value="${(orderProduct.id)!''}">
<input type="hidden" name="productId" value="${(orderProduct.productId)!''}">

		<div class='container'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>申请售后</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>商品名称</th>
							<th>购买数量</th>
						</tr>
						<tr>
							<td>
								<ul class='list-proinfo'>
									<li>
										<#if orderProduct??>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target="_blank">
												<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${orderProduct.productLeadLittle}' width='50' height='50' title='${(orderProduct.productName)!''}'>
											</a>
											<div class='p-info-name'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target='_blank'>${(orderProduct.productName)!''}</a>
											</div>
										</#if>
									</li>
								</ul>
							</td>
							<td>${(orderProduct.number)!''}</td>
						</tr>
					</tbody>
				</table>
				<div class='apply-form' id='consignee-form'>
					<div class='repair-steps'>
						<div class='repair-step repair-step-curr'>
							<div class='miaoshuDiv'>
								<!-- 退货数量 -->
								<div class='sellerPrompt'>
									<span class='label'>
										<span>退货数量：</span> 
									</span>
									<div class='fl'>
										 ${(info.number)!0 }
									</div>
									<em class='em-errMes'></em>	
								</div>
								<!-- 问题描述 -->
								<div class='sellerPrompt'>
									<span class='label'>
										<span>问题描述：</span> 
									</span>
									<div class='fl'>
										 ${(info.question)!'' }
									</div>
									<em class='em-errMes'></em>	
								</div>
								
								
								<#if info??>
									<div class='sellerPrompt'>
										<span class='label'>
											<span>退款金额：</span> 
										</span>
										<div class='fl'>&yen;${(info.backMoney)?string("0.00")!""}</div>
									</div>
									<div class='sellerPrompt'>
										<span class='label'>
											<span>通用积分：</span>
										</span>
										<div class='fl'>${(info.backIntegral)!"0"}</div>
									</div>
                                    <div class='sellerPrompt'>
										<span class='label'>
											<span>专项积分：</span>
										</span>
                                        <div class='fl'>${(info.backSpecialIntegral)!"0"}</div>
                                    </div>
									<#if couponUser?? >
									<div class='sellerPrompt'>
										<span class='label'>
											<span>返回优惠券：</span> 
										</span>
										<div class='fl'>${(couponUser.couponSn)!""}</div>
									</div>
									</#if>
									
							  		<#if info.stateReturn==1>
							  			<div class='sellerPrompt'>
											<span class='label'>
												<span>状&#12288;&#12288;态：</span> 
											</span>
											<div class='fl'>未处理</div>
										</div>
							  		<#elseif info.stateReturn==2>
							  			<div class='sellerPrompt'>
											<span class='label'>
												<span>状&#12288;&#12288;态：</span> 
											</span>
											<div class='fl'>审核通过待收货</div>
										</div>
							  		<#elseif info.stateReturn==3>
							  			<div class='sellerPrompt'>
											<span class='label'>
												<span>状&#12288;&#12288;态：</span> 
											</span>
											<div class='fl'>已经收货</div>
										</div>
										<div class='sellerPrompt'>
											<span class='label'>
												<span>退款状态：</span> 
											</span>
											<div class='fl'>
												<#if info.stateMoney==1>未退款</#if>
												<#if info.stateMoney==2>退款到账户</#if>
												<#if info.stateMoney==3>退款到银行</#if>
											</div>
										</div>
										<div class='sellerPrompt'>
											<span class='label'>
												<span>退款时间：</span> 
											</span>
											<div class='fl'>
												<#if info.backMoneyTime??>
													${(info.backMoneyTime)?string("yyyy-MM-dd HH:mm:ss")}
												<#else>
													正在处理
												</#if>
											</div>
										</div>
							  		<#else>
							  			<div class='sellerPrompt'>
											<span class='label'>
												<span>状&#12288;&#12288;态：</span> 
											</span>
											<div class='fl'>不予处理</div>
											<!-- 此时可以发起投诉 -->
								  			<#assign canComplain = 'true'/>
										</div>
							  		</#if>
							  	</#if>
								
								<div class='sellerPrompt'>
									<span class='label'>
										<span>处理意见：</span> 
									</span>
									<div class='fl'>${(info.remark)!'无'}</div>
								</div>
							</div>
							<!-- end -->
						</div>
					</div>
				</div>
			</div>
 </div>
 
</form>
<script type="text/javascript">
	
	$(function(){
		//控制左侧菜单选中
		$("#productback").addClass("currnet_page");

	});
	
</script>

<#include "/front/commons/_endbig.ftl" />
