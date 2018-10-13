<#include "/front/commons/_top.ftl" />
<link rel="stylesheet"
	href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/order.css'>
<link rel="stylesheet"
	href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/payfor.css'>
<style>
.order-information .p-tips {
	line-height: 20px;
	height: 20px;
	margin-bottom: 0px;
	font-size: 14px;
	line-height: 26px;
	font-family: 'Helvetica Neue', Arial, 'Liberation Sans', FreeSans,
		'Hiragino Sans GB', sans-serif, "Microsoft YaHei", 微软雅黑,
		"Microsoft JhengHei", 华文细黑, STHeiti, MingLiu;
}
</style>
<div class='w1 header container'>
	<div id='logo'>
		<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html'
			target='_blank' class='link1'> <img
			src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/haihetaologo.png'>
		</a>
	</div>
	<div class='stepflex-box fr'>
		<ul>
			<li class=""><span class="fl">1.填写充值金额</span><i class="fl"></i></li>
			<li class="current"><span class="fl">2.在线支付</span><i class="fl"></i>
			</li>
			<li class=""><span class="fl">3.充值完成</span><i class="fl"></i></li>
		</ul>
	</div>
</div>

<form id="payForm" method="POST" autocomplete="off"
	action="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/pay.html">
	<input type="hidden" name="amount" value="${amount!}" />

	<!-- 支付 -->
	<div id='PayforBox'>
		<div class='container'>
			<!-- 订单详情 -->
			<div class='order-information'>
				<div class='p-left' style="padding: 0px;">
					<p class='p-tips'>您正在充值余额，请尽快支付。</p>
				</div>
				<div class='p-right' style="padding-top: 0px; padding-bottom: 0px;">
					<div class='pay-price'>
						<em>充值金额</em> <strong>${(amount)!'' }</strong> <em>元</em>
					</div>
				</div>

				<div class='clr'></div>
			</div>

			<!-- 支付方式 -->
			<div class='payment'>
				<div class='paybox'>
					<div class='p-wrap'>
						<ul>
							<!-- 系统只提供一卡通支付渠道 -->
							<li>
								<input type="radio" name="optionsRadios" id="optionsRadios2" value="ecardpay" checked="checked">
								<div class='img-pay'>
									<img width="127" height="32" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/ecardpay.jpg'>
								</div>
							</li>
							<#--
							<li><input type="radio" name="optionsRadios"
								id="optionsRadios2" value="alipay" checked="checked">
								<div class='img-pay'>
									<img width="130" height="45"
										src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/l142.png'>
								</div></li>
							<li><input type="radio" name="optionsRadios"
								id="optionsRadios2" value="unionpay">
								<div class='img-pay'>
									<img width="130" height="40"
										src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/unionpay.png'>
								</div></li>
							<li><input type="radio" name="optionsRadios"
								id="optionsRadios2" value="weixin">
								<div class='img-pay'>
									<img width="130" height="40"
										src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/weixin_logo.png'>
								</div></li>
								-->
						</ul>
					</div>
				</div>
				<div class='payment-column'>&nbsp;</div>
				<div class='pv-button'>
					<!-- 在线支付-->
					<input type="submit" value='立即支付' class='PayforSubmit'
						id='PayButtom'>
				</div>
			</div>
		</div>
	</div>

</form>


<#include "/front/commons/_endbig.ftl" />
