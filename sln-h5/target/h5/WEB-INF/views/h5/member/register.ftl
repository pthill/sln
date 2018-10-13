<#include "/h5/commons/_head.ftl" />
<body style="background:#f2f2f2;">
   <!-- 头部 -->
   <header>
   	  <div class="flex flex-align-center login-header">
   	  	 <div class="padlr10 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">注册</div>
   	  </div>
   	  
   </header>
   <!-- 头部 end-->
   
	<div class="s-container">
	  <div class="pad10">
	  	  <div class="tiperror"></div>
	  	  <form id="registerForm">
	  	    <div class="form-group flex">
	  	        <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入手机号或员工号" >
	  	    </div>
	  	    <div class="form-group" style="position:relative;">
	  	      <input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="请输入验证码" maxlength="6" >
	  	      <span class="captcha-img" style="right:10px; left:auto;">
	  	      	<img style="cursor:pointer;" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="63" height="25" />
	  	      </span>
	  	    </div>
	  	    <div class="form-group" style="position:relative;">
	  	      <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号码" maxlength="11" >
	  	      <span class="captcha-img" style="right:10px; left:auto;">
	  	      	<div class="register-phone-verifycode"><button type="button" id="getSMSVerify">获取短信验证码</button></div>
	  	      </span>
	  	    </div>
	  	      <div class="form-group flex">
	  	        <input type="text" class="form-control" id="smsCode" name="smsCode" placeholder="请输入手机验证码" >
	  	    </div>
	  	    
	  	    <div class="form-group" style="position:relative;">
	  	      <input type="password" class="form-control" id="password" name="password" placeholder="请设置6-20位登录密码"  >
	  	    </div>
	  	    <div class="form-group" style="position:relative;">
	  	      <input type="password" class="form-control" id="passwordCfm" name="passwordCfm" placeholder="请再次输入密码"  >
	  	    </div>
	  	    <button type="button" class="btn btn-block btn-login" id="registerBtn">注册</button>
	  	  </form>
	  	  
	  </div>
    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	var verifysms = false;
	$(function(){
		$("#registerBtn").click(function(){
			var verifyCode = $("#verifyCode").val();
			if (verifyCode == null || verifyCode == "") {
				$(".tiperror").html("请输入验证码");
				return;
			}
			
			var userName = $("#userName").val();
			if (userName == null || userName == "") {
				$(".tiperror").html("请输入用户名");
				return;
			}
			var b = /^[a-z0-9]{6,16}$/;
			if(!b.test(userName)){
				$(".tiperror").html("用户名必须为6到16位的字母和数字的结合");
				return;
			}
			
			if(!verifysms){
				$(".tiperror").html('请先获取手机验证码进行手机验证', '提示');
				return;
			}
			
			var phone = $("#phone").val();
			if (phone == null || phone == "") {
				$(".tiperror").html("请输入手机号码");
				return;
			}
			
			var smsCode = $("#smsCode").val();
			if (smsCode == null || smsCode == "") {
				$(".tiperror").html("请输入手机验证码");
				return;
			}
			
			var password = $("#password").val();
			if (password == null || password == "") {
				$(".tiperror").html("请输入密码");
				return;
			}
			if (password.length < 6 || password.length > 20) {
				$(".tiperror").html("请输入6-20位密码");
				return false;
			}
			var passwordCfm = $("#passwordCfm").val();
			if (passwordCfm == null || passwordCfm == "") {
				$(".tiperror").html("请输入确认密码");
				return;
			}
			if (passwordCfm.length < 6 || passwordCfm.length > 20) {
				$(".tiperror").html("请输入6-20位确认密码");
				return false;
			}
			if (password != passwordCfm) {
				$(".tiperror").html("确认密码不正确");
				return;
			}
			$("#registerBtn").attr("disabled","disabled");
			var params = $('#registerForm').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/doregister.html",
				dataType:"json",
				async : false,
				data : params,
				success:function(data){
					if(data.success){
						// 跳转到用户中心
						window.location=domain+"/member/index.html";
					}else{
						$(".tiperror").html(data.message);
						//刷新验证码
						refreshCode();
						$("#registerBtn").removeAttr("disabled");
					}
				},
				error:function(){
					$(".tiperror").html("异常，请重试！");
					$("#registerBtn").removeAttr("disabled");
				}
			});
		});
		
		
		//获取验证码
		$("#getSMSVerify").click(function() {
			var sendverfiy = false;
			var obj = $(this);
			var mob = $("#phone").val();

			var verifyCode = $("#verifyCode").val();
			if (verifyCode == null || verifyCode == "") {
				$(".tiperror").html("请输入验证码");
				return;
			}
			
			var phone = $("#phone").val();
			var re = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
			if (phone == null || phone == "") {
				$(".tiperror").html("请输入手机号码");
				return;
			} 
			if(!re.test(phone)){
				$(".tiperror").html("请输入正确的手机号码");
				return;
			}
			
			$.ajax({
				type : 'get',
				url : domain + '/sendVerifySMS.html?mob=' + mob
						+ '&verifycode=' + verifyCode + '&type=reg',
				async:false,
				success : function(e) {
					if (!e.success) {
						$(".tiperror").html(e.message);
						refreshCode();
						obj.text("获取短信验证码");
					} else{
						$(".tiperror").empty();
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
	});
	
	//刷新验证码
	function refreshCode(){
		jQuery("#code_img").attr("src","${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html?d"+new Date().getTime());
	}
</script>



</body>
</html>