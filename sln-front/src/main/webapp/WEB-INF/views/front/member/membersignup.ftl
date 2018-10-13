<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link  rel="stylesheet" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/user.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/register.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
		</script>
	</head>
	<body style="background: #f2f2f2;">
		<div class="register_wrap login_content">
            <div class='container' style="padding-top: 10px;">
                <div class='header-wrap clearfix'>
                    <div class='snlogo fl'>
                        <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html'>
                            <img src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/hhyungu_logo.png'>
                        </a>
                    </div>
                    <div class="login-character fl">欢迎注册</div>
                </div>
			</div>
			<div class='nc-login-layout wp-1210' >
				<!-- S 注册页面 -->
				<div class='nc-signup nc-signup-sign'>
					<div class='signup-title signup-title-log'>
						<h3>
							<span class="sp-l fl">用户注册</span>
                            <div class="container-head">
								<span class="sp-r fr">已有账号？<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/login.html" class="register-a">立即登录</a>
								</span>
							</div>
						</h3>
					</div>
					<div class='signup-content'>
						<form class="form-horizontal" id='formRegister'>
						  	<div class="form-group">
							    <div class="form-group-user clearfix">
							    	<div class="input_icon account_icon"></div>
							      	<input type="text" placeholder="请输入用户名" id="name" name='name'>
							      	<span class='errortip'>
						      			<p></p>
						      		</span>
							    </div>
						  	</div>
						  	
						  	<div class="form-group form-group-yz">
							    <div class="form-group-user clearfix" style="padding:0 22px;">
							    		<div class="input_icon randomcode_icon"></div>
							      	<input type="text" placeholder="验证码" id="verifyCode" name="verifyCode">
							      	<div class='register-verify'>
									    	<img style="cursor:pointer;" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html" id="code_img_r" onclick="refreshCodeR();"  />
						    				<a href='javascript:void(0);' onclick="refreshCodeR();">看不清，换一张</a>
									 </div>
									 <span class='errortip verifycode-tip'>
						      			<p></p>
						      		</span>
							    </div>
						  	</div>
						  	
						  	<div class="form-group register-phone-input">
							    <div class="form-group-user clearfix">
							    	<div class="input_icon randomcode_icon"></div>
							      	<input type="text" placeholder="请输入手机号码" id="mobile" name='mobile'>
							      	<div class="register-phone-verifycode"><button type="button" id="getSMSVerify">获取短信验证码</button></div>
							      	<span class='errortip verifycode-tip signup-message-sms'>
						      			<p></p>
						      		</span>
							    </div>
						  	</div>
						  	
						  	<div class="form-group">
							    <div class="form-group-user clearfix">
							    	<div class="input_icon password_icon"></div>
							      	<input type="text" placeholder="请输入手机验证码" id="smsCode" name='smsCode'>
							      	<span class='errortip'>
						      			<p></p>
						      		</span>
							    </div>
						  	</div>
						  	
						  	<div class="form-group">
							    <div class="form-group-user clearfix">
							    	<div class="input_icon password_icon"></div>
							      	<input type="password" placeholder="请输入密码，6-16位字符" id="setPassword" name='password'>
							      	<span class='errortip signup-message-pwd'>
							      		<p></p>
							      	</span>
							    </div>
						  	</div>
						  	<div class="form-group">
							    <div class="form-group-user clearfix">
							    	<div class="input_icon password_icon"></div>
							      	<input type="password" placeholder="确认密码" id="confirmPsw" name='repassword'>
							      	<span class='errortip signup-message-pwd'>
							      		<p></p>
							      	</span>
							    </div>
						  	</div>
						  
						  	<div class='form-group-yes clearfix'>
						  		<input type='checkbox' class='agree' name="acceptProtocol" id="acceptProtocol">
						  		<span>我已阅读并同意<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/service_protocol.html">《海核云谷用户协议》</a></span>
						  	</div>
						  	<a href='javascript:void(0)' id="signupButton" class="ahover">
						  	<div class='form-group form-group-dl form-group-zc'>
						  		<div class=''>
						  			立即注册
						  		</div>
						  	</div>
						  	</a>
						</form>
					</div>
				</div>
				<!-- E 注册页面 -->
			</div>
			<!-- 
            <div class='wraper bottom-copyright' id='footer'>
               版权所有 © Copyright 2017 海核云谷.保留一切权利
            </div>
             -->
		</div>
	</body>
<script type="text/javascript">
var verifysms = false;
function refreshCodeR(){
	jQuery("#code_img_r").attr("src",domain+"/verify.html?d"+new Date().getTime());
}


/**
 * 增加邮箱校验
 */
jQuery.validator.addMethod('validname', function(value, element) {
    return this.optional(element) || validname(value);
},"请输入正确的邮箱地址");

function validname(value){
	var patrn=/^(\w){6,20}$/;  
	if (!patrn.exec(value)) 
		return false
	return true 
}

$(function(){
	$("#acceptProtocol").attr("checked","checked");
	
	$("#formRegister").validate({
		errorPlacement : function(error, element) {
			var obj = element.siblings(".errortip")
					.find('p').addClass('error');
			error.appendTo(obj);
		},
        rules : {
            "mobile":{required:true,isMobile:true},
            "verifyCode":{required:true}
        },
        messages:{
        	"mobile":{
        		required:"请输入手机号码",
        		isMobile:"请输入正确的手机号码"
        		},
        	"verifyCode":{required:"请输入验证码"}
        }
    }); 
	
	$("#mobile").keyup(function(){
		$(this).siblings(".errortip").find("p").empty();
	});
	
	$("#getSMSVerify").click(function() {
		var sendverfiy = false;
		var obj = $(this);
		var mob = $("#mobile").val();
		var verifycode = $("#verifyCode").val();
		var verifytip = $(".signup-message-sms p");

		if ($("#formRegister").valid()) {
			$.ajax({
				type : 'get',
				url : domain + '/sendVerifySMS.html?mob=' + mob
						+ '&verifycode=' + verifycode + '&type=reg',
				async:false,
				success : function(e) {
					if (!e.success) {
						verifytip.html(e.message).addClass('error').parent().show();
						refreshCode();
						obj.text("获取短信验证码");
					} else{
						verifytip.empty();
						sendverfiy = true;
						verifysms = true;
						$("#name").rules("add", {  
							required : true,  
							validname : true,
							minlength : 6,
			                messages : {  
			                	required : "请输入用户名",
			                	validname : "只能输入6-20个字母、数字、下划线",
			                	minlength : "用户名最小长度不得低于6位"
			                }  
			            });  
						$("#smsCode").rules("add", {  
							required : true,  
			                messages : {  
			                	required : "请输入短信验证码"
			                }  
			            });  
						$("#setPassword").rules("add", {  
							required : true,  
							validname : true,
			                messages : {  
			                	required : "请输入密码",
			                	validname : "只能输入6-20个字母、数字、下划线"
			                }  
			            });  
						$("#confirmPsw").rules("add", {  
							required : true,  
							equalTo:"#setPassword",
			                messages : {  
			                	required : "请输入确认密码",
			                	equalTo : "两次密码不一致"
			                }  
			            });  
						$("#acceptProtocol").rules("add", {  
							required : true,  
			                messages : {  
			                	required : "请接受服务条款"  
			                }  
			            });  
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
			
		}

	});
	
	$("#signupButton").click(function(){
		if(!verifysms){
			jAlert('请先获取手机验证码进行手机验证', '提示');
			return;
		}
		if ($("#formRegister").valid()) {
			$(".ahover").attr("disabled","disabled");
			var params = $('#formRegister').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/doregister.html",
				dataType:"json",
				async : false,
				data : params,
				success:function(data){
					if(data.success){
						jAlert('注册成功！', '提示',function(){
							window.location=domain+"/member/order.html"
						});
					}else{
						jAlert(data.message);
						refreshCode();//刷新验证码
						$(".ahover").removeAttr("disabled");
					}
				},
				error:function(){
					jAlert("异常，请重试！");
					$(".ahover").removeAttr("disabled");
				}
			});
		}
		
	});
	
});
</script>
<#include "/front/commons/_endbig.ftl" />