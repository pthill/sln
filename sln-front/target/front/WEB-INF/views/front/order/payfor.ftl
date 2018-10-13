<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/order.css'>
<link  rel="stylesheet" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/payfor.css'>
<div class='w1 header container'>
	<div id='logo'>
		<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html' target='_blank' class='link1'>
			<img src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/haihetaologo.png'>
		</a>
	</div>
	<div class='stepflex-box fr'>
		<ul>
			<li class="">
				<span class="fl">1.我的购物车</span><i class="fl"></i>
			</li>
			<li class="prev">
				<span class="fl">2.确认订单信息</span><i class="fl"></i>
			</li>
			<li class="current">
				<span class="fl">3.成功提交订单</span><i class="fl lasti"></i>
			</li>
		</ul>
	</div>
</div>
	
	<form id="payForm" method="GET" autocomplete="off">
		<input type="text" style="display:none" />
		<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
		<input type="hidden" name="paySn" id="paySn" value="${paySn!''}">
		<input type="hidden" name="orderSn" id="orderSn" value="${orderSn!''}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
	
		<!-- 支付 -->
		<div id='PayforBox'>
			<div class='container'>
				<!-- 订单详情 -->
				<#if type ?? && type == 2>
				
				<div class='order-information'>
					<div class='p-left'>
						<h3 class='p-title'>
							<#if fromType?? && fromType == 2>
								订单号：  
							<#else>
								订单提交成功，请您尽快付款！    订单号：  
							</#if>
							<#if payForOrderSn?? >
									${payForOrderSn!""}
							<#else>
								${paySn!''}
							</#if>
						</h3>
						<p class='p-tips'>请您在提交订单后<span class='font-red'>24小时内</span>完成支付，否则订单会自动取消。</p>
					</div>
					<div class='p-right'>
						<div class='pay-price'>
							<strong>${(payAmount)!'' }</strong>
							<#if type?? && type ==2>
								<em>积分</em>
							<#else>
								<em>元</em>
							</#if>
							
						</div>
					</div>
					
					<br>
					<div class='p-left'>
						<div class='form' style="font-size:15px;font-weight:bold ">
							<input type="hidden" name="" value="${(member.integral)!'' }">	
							<#if orderSuccessVO?? && (orderSuccessVO.specialIntegralPay > 0)>
								
								<p>专项积分支付：<strong class='font-red' >${(orderSuccessVO.specialIntegralPay)!''	}</strong>
							</#if>
							<#if member.integral ?? && member.integral gt 0 >
								<#if (member.integral + orderSuccessVO.specialIntegralPay )  gte payAmount >
									<p>福利积分支付：<strong class='font-red' >${(payAmount-orderSuccessVO.specialIntegralPay)!'' }</strong>
								<#else>
									<p>福利积分支付：<strong class='font-red' >${(member.integral)!'' }</strong>
								</#if>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								可用福利积分：<strong class='font-red' >${(member.integral)!'' }</strong></p> 
							</#if>
							<p>
								<#if member.integral < payAmount >
									
									<#if (member.integral + orderSuccessVO.specialIntegralPay) < payAmount>
										<#if member.balance < (payAmount - member.integral - orderSuccessVO.specialIntegralPay ) / config.integralScale  >
											余额不足 
											<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/recharge.html" class='font-red'>立即充值</a>
										<#else>
											余额抵用支付：<strong class='font-red' >${((payAmount - member.integral - orderSuccessVO.specialIntegralPay) / config.integralScale )?string('0.##')}</strong>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											我的余额：<strong class='font-red' >${(member.balance)!'' }</strong>
										</#if>
									</#if>
									
									
								</#if>
							</p>
							<br>
							<p>支付密码：<input type='password' id='balancePassword' name="balancePassword" ></p>
							
						</div>
					</div>
					<div class='clr'></div>
				</div>
				
				<!-- end -->
				<!-- 支付方式 -->
				<div class='payment'>
					<input type="hidden" name="optionsRadios" value="integral" > 
					<div class='pv-button'>
						<!-- 在线支付--> 
						<input type='button' value='立即支付'  class='PayforSubmit' id='PayButtom'>
						<#if payAmount &lt;= 0 >
						<br><br>
						<span style="color:red"><i style="font-style:italic;">Tip:</i>&nbsp;&nbsp;如果应付金额为0请选择任意方式点立即支付即可付款成功</span>
						</#if>
					</div>
				</div>	
				<#else>
				<div class='order-information'>
					<div class='p-left'>
						<h3 class='p-title'>
							<#if fromType?? && fromType == 2>
								订单号：  
							<#else>
								订单提交成功，请您尽快付款！    订单号：  
							</#if>
							<#if payForOrderSn?? >
									${payForOrderSn!""}
							<#else>
									${paySn!''}
							</#if>
							
						</h3>
						<p class='p-tips'>请您在提交订单后<span class='font-red'>24小时内</span>完成支付，否则订单会自动取消。</p>
					</div>
					<div class='p-right'>
						<div class='pay-price'>
							<strong>${(payAmount)!'' }</strong>
							<#if type ?? && type == 2>
								<em>积分</em>
							<#else>
								<em>元</em>
							</#if>
							
						</div>
					</div>
					
					<br>
					<div class='p-left'>
						<div class='form'>
							<input type='checkbox' id='selectOrderBalance' name="selectOrderBalance" autocomplete="off" >
							<label id='canUsedBalanceId'>使用余额（账户当前余额：${(member.balance)!'0.00' }元）</label>
							支付密码：<input type='password' id='balancePassword' name="balancePassword" disabled>
						</div>
					</div>
					
					<div class='clr'></div>
					
				</div>
				
				<!-- end -->
				<!-- 支付方式 -->
				<div class='payment'>
					<div class='paybox'>
						<div class='p-wrap'>
							<ul>
								<li>
									<!-- 系统只提供一卡通支付渠道 -->
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="ecardpay" checked="checked"> 
									<div class='img-pay'>
										<img width="127" height="32" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/ecardpay.jpg'>
									</div>
								</li>
								<#--
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="alipay"> 
									<div class='img-pay'>
										<img width="130" height="45" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/l142.png'>
									</div>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="unionpay">
									<div class='img-pay'>
										<img width="130" height="40" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/unionpay.png'>
									</div>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="weixin">
									<div class='img-pay'>
										<img width="130" height="40" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/weixin_logo.png'>
									</div>
								</li>
								-->
							</ul>
						</div>
					</div>
					<div class='payment-column'>&nbsp;</div>
					<div class='pv-button'>
						<!-- 在线支付--> 
						<input type='button' value='立即支付'  class='PayforSubmit' id='PayButtom'>
						<#if payAmount &lt;= 0 >
						<br><br>
						<span style="color:red"><i style="font-style:italic;">Tip:</i>&nbsp;&nbsp;如果应付金额为0请选择任意方式点立即支付即可付款成功</span>
						</#if>
					</div>
				</div>
				</#if>
			</div>
		</div>
		
	</form>
		<!-- end -->
		<!-- footer -->
<script type="text/javascript">
$(function(){
	//选中余额checkbox 
	$("#selectOrderBalance").click(function(){
		//如果余额小于等于0 那么不允许选中
		<#if member??&&member.balance??>
			<#if member.balance<=0 >
				$(this).prop("checked", false);
				return;
			</#if>
		</#if>

		if($(this).prop("checked")){
			$("#balancePassword").removeAttr("disabled");
		}else{
			$("#balancePassword").attr("disabled","disabled");
			$("#balancePassword").val("");
		}
	});
	
	$("#PayButtom").click(function(){
		var balancePwd = $("#balancePassword").val();
		if("${(type)!''}" == "2"){
			var mBlance = Number("${member.balance}");
			var payAmount = Number("${(payAmount)!''}");
			var integral = Number("${(member.integral)?string('0')!''}");
			var integralScale = Number("${(config.integralScale)!''}");
			var sIntegralScale = Number("${(orderSuccessVO.specialIntegralPay)!''}");

			if(integral + sIntegralScale < payAmount && (payAmount - integral - sIntegralScale)/integralScale >  mBlance){
				jAlert("余额不足 请充值");
				return false;		
			}
		}
  		if($("#selectOrderBalance").prop("checked") || "${(type)!''}" == "2"){
  			if(isEmpty(balancePwd)){
  				jAlert("密码不能为空");
  				$("#balancePassword").focus();
  				return false;
  			}
  			//验证支付密码
  			var checkpwd = checkBalancePwd(balancePwd);
  			if(!checkpwd){
  				return false;
  			}
  		}
		
		// 支付提交
		$("#payForm").attr("action", "${(domainUrlUtil.SLN_URL_RESOURCES)!}/payindex.html")
			 .attr("method", "GET")
			 .submit();
	});
});


	//验证支付密码
	function checkBalancePwd(balancePwd){
		var correct = false;
		$.ajax({
			type : "GET",
			url :  domain+"/order/checkbalancepwd.html",
			data : {balancePwd:balancePwd},
			dataType : "json",
			async:false,
			success : function(data) {
				if(data.success){
					correct = data.data.correct;
					var errcount = parseInt(data.data.pwdErrCount);
				   	if(errcount>=6){
				   		jAlert("支付密码输错超过6次,请用其他方式支付");
						$(".toggle-title").click();
						return false;
				   	}
					if(!correct){
						jAlert("支付密码不正确，您最多还可以输入"+(6-errcount-1)+"次");
						return false;
					}
				}else {
					jAlert(data.message);
					return false;
				}
			},
			error : function() {
				jAlert("验证密码失败！");
			}
		});
		return correct;
	}
</script>
	
<#include "/front/commons/_endbig.ftl" />