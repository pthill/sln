<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/userindex.css"/>
<style>
.wrapper_main strong {
    font-size: 16px;
    font-family: '\5fae\8f6f\96c5\9ed1';
}
</style>
<script type="text/javascript">
</script>
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
			<a href='javascript:void(0)'>余额赠送</a>
		</span>
	</div>
</div>

<div class='container'>
	<!--左侧导航 -->
	<#include "/front/commons/_left.ftl" />
	<!-- 右侧主要内容 -->
	<div class='wrapper_main myorder'>
		<h3>余额赠送</h3>
		<form action='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/balancegive.html' id='form-company-info' method='post'>
					<input type="hidden" id="phone" name="phone" value="${member.mobile}">
					<input type="hidden" id="orderSession" name="orderSession" value="${paySessionstr}">
					<table border='0' cellpadding="0" cellspacing="0" class='all' style="margin: 0 auto;"
						id='table' width="100%">
						<tbody valign="middle">
							<tr>
								<th>对&nbsp;&nbsp;方&nbsp;&nbsp;账&nbsp;&nbsp;号&nbsp;&nbsp;：</th>
								<td colspan ="2">
								  <input name='mobile' type='text' placeholder="请输入接收方账号" id="mobile" class='form-control w50' style="width:80%;display:inline;" value="">
							    </td>
							</tr>
							<tr>
								<th>手&nbsp;&nbsp;机&nbsp;&nbsp;验&nbsp;&nbsp;证&nbsp;&nbsp;码：</th>
								<td>
								<input type="text" placeholder="请输入手机验证码" id="smsCode" name='smsCode' class='form-control w50' style="width:70%;display:inline;"/>
								<td>
									<div class="register-phone-verifycode"><button type="button" id="getSMSVerify">获取短信验证码</button></div>
								      	<span class='errortip verifycode-tip signup-message-sms'>
							      			<p></p>
							      		</span>
									</td>
								</td>
							</tr>
							<tr>
								<th>金&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;&nbsp;&nbsp;&nbsp;：</th>
								<td colspan ="2"><input name='money' placeholder="请输入赠送金额" type='text' id="money" class='form-control w50' style="width:80%;display:inline;" value="" > <span id="press"></span></td>
							</tr>
							<tr>
								<th>密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp;：</th>
								<td colspan ="2"><input type="password" name='balancePwd' placeholder="请输入密码" type='text' id="balancePwd" class='form-control w50' style="width:80%;display:inline;" value="" maxlength="11"><span id="phoNum"></span></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="20">&nbsp;</td>
							</tr>
						</tfoot>
					</table>
					<tr colspan="2" style=" padding: 20px 200px;">
								<td class='p-item'>
									<input type='submit'  class='btn btn-danger bt_submit' id='buttonSubmit' name='button' value='确认'>
								</td>
								<td class='p-item'>
									<input type='reset'  class='btn btn-danger bt_submit' id='buttonReset' name='button' value='取消'>
								</td>
						</tr>		
				</form>
		<#include "/front/commons/_pagination.ftl" />
	</div>
	
</div>

<script>
	$(function(){
		$.validator.setDefaults({
		    submitHandler: function() {
				var param = $("#form-company-info").serialize(); 
				$.ajax({ 
					url : "${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/balancegive.html", 
					type : "post", 
					dataType : "json", 
					data: param, 
					success : function(result) { 
					if(result.success){
						alert("赠送成功");
						window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html"
					}else{
						alert(result.message);
					}
					}
				}); 
		    }
		});
		
		$.validator.addMethod("isMoney", function(value, element) {  
		    var regMoney = /^[0-9]+(.[0-9]{0,2})?$/;
		    return this.optional(element) || (regMoney.test( value )&& value>0);    
		}, "请输入正确金额");  
		//控制左侧菜单选中
		$("#balance").addClass("currnet_page");
		
		$("#form-company-info").validate({
		rules : {
			mobile : {
				required : true
			},
			smsCode : {
				required : true,
				number:true,
				rangelength:[6,6]
			},
			money : {
				required : true,
				isMoney:true
			},
			balancePwd : {
				required : true
			},
		},
		messages : {
			mobile : {
				required : "接受者账号不能为空"
			},
			smsCode : {
				required : "手机验证码不能为空",
				number:"必须输入数字",
				rangelength:"必须为6位"
			},
			money : {
				required : "金额不能为空",
				number:"必须输入数字"
			},
			balancePwd : {
				required : "密码不能为空"
			}
		}
	});
		
	});
	
	$("#getSMSVerify").click(function() {
		var sendverfiy = false;
		var obj = $(this);
		var mob = $("#phone").val();

			$.ajax({
				type : 'get',
				url : domain + '/sendVerifySMS.html?mob=' + mob+'&type=bal',
				async:false,
				success : function(e) {
					if (e.success) {
						sendverfiy = true;
						verifysms = true;
					}
				}
			});
			if(sendverfiy){
				var time = 120;
				obj.attr("disabled", true);
				obj.text(time + "秒后重新获取");
				time--;
		
				intervalId = setInterval(function() {
					obj.text(time + "秒后重新获取");
					time--;
					if (time == 0) {
						clearInterval(intervalId);
						obj.removeAttr("disabled");
						obj.text("获取短信验证码");
					}
				}, 1000);
			}
	});
</script>

<#include "/front/commons/_endbig.ftl" />