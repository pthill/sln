<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/userindex.css"/>
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
			<a href='javascript:void(0)'>退货申请</a>
		</span>
	</div>
</div>
		
<form id ="productBackForm" action="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/doproductback.html">
<!-- 隐藏域 -->
<input type="hidden" name="orderId" value="${(order.id)!''}"> 

<div class='container'>
	<!--左侧导航 -->
	<#include "/front/commons/_left.ftl" />
	<!-- 右侧主要内容 -->
	<div class='wrapper_main myorder'>
	<div class='mc-box'>
		<h3>售后申请</h3>
		<div class="payment-info">
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
										</tr>
										<#if (order.orderProductList)??>
										<#list (order.orderProductList) as product>
										<tr>
											<td>
												<div class='img-list clearfix' style="padding-left: 30px;">
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' class='img-box' target='_blank'>
														<img width='50' height='50' src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${product.productLeadLittle}'>
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
														${((product.actIntegralNum) / (product.number))!''}分
													<#else>
														￥${(product.moneyPrice)?string('0.00')!''}
													</#if>
												</span>
											</td>
											<td>${(product.number)!''}</td>
											<td>
												<span class='ftx04'>
													<#if order.orderType?? && order.orderType == 6>
														${(product.actIntegralNum)!''}分
													<#else>
														￥${(product.moneyAmount)?string('0.00')!''}
													</#if>
												</span>
												<#if product.moneyActSingle?? && product.moneyActSingle &gt; 0 >
												<br>
												<span>立减￥${(product.moneyActSingle)?string('0.00')!''}</span>
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
					
		<div class='apply-form' id='consignee-form'>
			<div class='repair-steps'>
				<div class='repair-step repair-step-curr'>
					<!-- 服务类型 -->
					<div class='sellerPrompt'>
						<span class='label'>
							<em>*</em>
							 <span>服务类型：</span> 
						</span>
						<div class='fl'>
							<ul class='list-type list-type-new'>
								<li class='selected' urlv="member/doproductexchange.html">
									<a href='javascript:void(0);'>退货<b></b></a>
								</li>
							</ul>
						</div>
					</div>
					<!-- end -->
					<!-- 问题描述 -->
					<div class='miaoshuDiv'>
						<!-- 问题描述 -->
						<div class='sellerPrompt' style='height:132px'>
							<span class='label'>
								<em>*</em>
								<span>问题描述：</span> 
							</span>
							<div class='fl'>
								<textarea name="question" class='miaoshu-text'></textarea>
								<div style='text-align:right;'>10-500字</div>
							</div>
							<em class='em-errMes'></em>	
						</div>
						
						<div class='sellerPrompt'>
							<div class="button-center">
								<a href='javascript:void(0)' class='btn btn-default btn-7'>提交</a>
							</div>
						</div>
						<!-- end -->
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
		$("#myorder").addClass("currnet_page");
		
		//校验
		jQuery("#productBackForm").validate({
			errorPlacement : function(error, element) {
				var obj = element.parent().siblings(".em-errMes").css('display', 'block');
				error.appendTo(obj);
			},  
	        rules : {
	            "question":{required:true,minlength:10,maxlength:500},
	        },
	        messages:{
	            "question":{required:"请输入内容",minlength:"不能小于10个字呦",maxlength:"不能超过500个字呦"},
	        }
	    });
		
		//点击提交按钮事件
		$(".btn-default").click(function(){
			
			if($("#productBackForm").valid()){
				
				$(".btn-default").attr("disabled","disabled");
				var params = $('#productBackForm').serialize();
				  $.ajax({
					type:"POST",
					url:$('#productBackForm').attr("action"),
					dataType:"json",
					async : false,
					data : params,
					success:function(data){
						if(data.success){
							//jAlert("保存成功");
							//window.location.href=domain+'/member/order.html';
							
							jAlert('保存成功', '提示',function(){
								window.location.href=domain+'/member/order.html'
							});
						}else{
							jAlert(data.message);
							$(".btn-default").removeAttr("disabled");
						}
					},
					error:function(){
						jAlert("异常，请重试！");
						$(".btn-default").removeAttr("disabled");
					}
				}); 
			}
			
		});
	});
	
</script>

<#include "/front/commons/_endbig.ftl" />
