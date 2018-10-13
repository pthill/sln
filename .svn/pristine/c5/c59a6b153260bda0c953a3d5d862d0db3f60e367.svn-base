<#include "/h5/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/order.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">收银台</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"><span class="fa fa-bars"></span></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   	<@form.form action="" id="payForm" name="payForm" method="GET">
		<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
		<input type="hidden" name="paySn" id="paySn" value="${paySn!''}">
		<input type="hidden" name="orderSn" id="orderSn" value="${orderSn!''}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
		<!-- <input type="hidden" name="payType"  value=""> --><!-- 支付方式 -->
	     <#--type为2表示为积分福利积分的订单-->
		<#if type ?? && type == 2>
		    <#--积分商城的订单-->
			<div>
				 <div class="p-o-infor">
				   <p>
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
					<!-- 订单提交成功，请您尽快付款！<br>
					订单号：<font>123456789</font> -->
					</p>
				   <p class="font12 pad-top">请您在提交订单后<font class="clr53">24小时内</font>完成支付,否则订单会自动取消。</p>
				 </div>
					 <ul class="p-o-method">
						<li class="flex flex-pack-justify font16">
						   <div>选择支付方式</div>
						   <div class="clr53">${(payAmount)?string('0.00')!'' }积分
						   </div>
						</li>
					 </ul>
				   <#if orderSuccessVO?? && (orderSuccessVO.specialIntegralPay gte 0)>
							<#if orderSuccessVO.specialIntegralPay gte payAmount>
							<#--专项积分支付-->
                                <div class="add-balance pad10">
                                    <p>
                                        <input type='checkbox' id='selectOrderSpecial' checked="checked" disabled name="selectOrderSpecial" autocomplete="off" >
                                        &nbsp;使用积分支付
                                    </p>
                                    <p class="mar_top">
                                        专项积分支付：<strong class='font-red' >${(payAmount)?string('0.00')!'' }</strong>
                                    </p>
                                    <p class="mar_top">
                                        支付密码：
                                        <input class="form-control add-form-control" type='password' id="balancePassword" name="balancePassword"  >
                                    </p>
                                </div>
							<#elseif (member.integral + orderSuccessVO.specialIntegralPay )  gte payAmount>
							<#--专项积分加上通用积分支付-->
                                <div class="add-balance pad10">
                                    <p>
                                        <input type='checkbox' id='selectOrderSpecial' checked="checked" disabled name="selectOrderSpecial" autocomplete="off" >
                                        &nbsp;使用积分支付
                                    </p>
                                    <p class="mar_top">
                                        专项积分支付：<strong class='font-red' >${(orderSuccessVO.specialIntegralPay)!''}</strong>
                                        &nbsp;&nbsp;
                                        通用积分支付：<strong class='font-red' >${(payAmount-orderSuccessVO.specialIntegralPay)!'' }</strong>
                                    </p>
                                    <p class="mar_top">
                                        支付密码：
                                        <input class="form-control add-form-control" type='password' id="balancePassword" name="balancePassword"  >
                                    </p>
                                </div>
							<#else >
							<#--专项积分加上通用积分支付加余额支付-->
                                <div class="add-balance pad10">
                                    <p>
                                        <input type='checkbox' id='selectOrderSpecial' checked="checked" disabled name="selectOrderSpecial" autocomplete="off" >
                                        &nbsp;使用积分支付
                                    </p>
                                    <p class="mar_top">
                                        专项积分支付：<strong class='font-red' >${(orderSuccessVO.specialIntegralPay)!''}</strong>
                                        通用积分支付：<strong class='font-red' >${(member.integral)!'' }</strong>
                                    </p>
                                </div>
                                <div class="add-balance pad10">
                                    <p>

                                        <input type='checkbox' id="selectOrderBalance" checked="checked" disabled  name="selectOrderBalance" autocomplete="off" >
                                        &nbsp;使用余额
									</p>
                                    <p class="mar_top">
                                        我的余额：<strong class='font-red' >${(member.balance)!'' }元</strong>
										<#if member.balance < (payAmount - member.integral - orderSuccessVO.specialIntegralPay ) / config.integralScale  >
                                            余额不足
                                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/recharge.html" class='font-red'>立即充值</a>
										<#else>
                                            余额抵用支付：<strong class='font-red' >${((payAmount - member.integral - orderSuccessVO.specialIntegralPay) / config.integralScale )?string('0.##')}</strong>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</#if>
                                    </p>
                                    <p class="mar_top">
                                        支付密码：
										<#if member.balance < (payAmount - member.integral - orderSuccessVO.specialIntegralPay ) / config.integralScale>
                                        <input class="form-control add-form-control" disabled type='password' id="balancePassword" name="balancePassword"  >
										<#else >
                                            <input class="form-control add-form-control" type='password' id="balancePassword" name="balancePassword"  >
										</#if>
                                    </p>
                                </div>
							</#if>
						</#if>
				   <div class="pad10">
                   <input type="hidden" name="optionsRadios" value="integral" >
                      <button type="submit" class="btn btn-block btn-login" id='PayButtom'>立即支付</button>
				   </div>
			</div>
		<#else >
		 <#--普通商品的订单-->
           <div>
               <div class="p-o-infor">
                <p>
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
                </p>
                <p class="font12 pad-top">请您在提交订单后<font class="clr53">24小时内</font>完成支付,否则订单会自动取消。</p>
            </div>
               <ul class="p-o-method">
                   <li class="flex flex-pack-justify font16">
                       <div>选择支付方式</div>
                       <div class="clr53">${(payAmount)?string('0.00')!'' }元
					   </div>
                   </li>
                   <!-- 系统只提供一卡通支付渠道 by zhangmin -->
                   <li class="flex flex-pack-justify">
                       <div><img width="127" height="32" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/ecardpay.jpg'></div>
                       <div><input id="optionsRadios" name="optionsRadios" type="radio" value="ecardpay" class="p-radio" checked="checked"></div>
                   </li>
               </ul>
               <div class="add-balance pad10">
                   <p>
                       <input type='checkbox' id='selectOrderBalance' name="selectOrderBalance" autocomplete="off" >
                       &nbsp;使用余额(账户当前余额${(member.balance)?string('0.00')!'0.00' }元)</p>
                   <p class="mar_top">
                       支付密码：
                       <input class="form-control add-form-control" type='password' id='balancePassword' name="balancePassword" disabled >
                   </p>
               </div>
               <div class="pad10">
				   <button type="submit" class="btn btn-block btn-login" id='PayButtom'>立即支付</button>
			   </div>
           </div>
		</#if>
	</@form.form>
	<!-- 主体结束 -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
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
  		if($("#selectOrderBalance").prop("checked")){
  			if(balancePwd == null || balancePwd == ""){
  				// alert("密码不能为空");
  				$.dialog('alert','提示','密码不能为空',2000);
  				$("#balancePassword").focus();
  				return false;
  			}
  			//验证支付密码
  			var checkpwd = checkBalancePwd(balancePwd);
  			if(!checkpwd){
  				return false;
  			}
  		}
  		var actionUrl = "${(domainUrlUtil.SLN_URL_RESOURCES)!}/payindex.html";
		var h5Id = window.java_h5id == null ? null : window.java_h5id.getH5ID();
		if(h5Id !=null && h5Id.length > 0){
			actionUrl += "?sessionId="+h5Id ;
		}
		// 支付提交
		$("#payForm").attr("action", actionUrl)
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
				   		// alert("支付密码输错超过6次,请用其他方式支付");
				   		$.dialog('alert','提示','支付密码输错超过6次,请用其他方式支付',2000);
						$(".toggle-title").click();
						return false;
				   	}
					if(!correct){
						// alert("支付密码不正确，您最多还可以输入"+(6-errcount)+"次");
				   		$.dialog('alert','提示',"支付密码不正确，您最多还可以输入"+(6-errcount-1)+"次",2000);
						return false;
					}
				}else {
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
					return false;
				}
			},
			error : function() {
				// alert("验证密码失败！");
				$.dialog('alert','提示','验证密码失败！',2000);
			}
		});
		return correct;
	}
</script>

</body>
</html>