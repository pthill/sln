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
                <div class="login-character fl">欢迎登录</div>
            </div>
        </div>
        <div class='nc-login-layout wp-1210' >
            <!-- S 登录页面 -->
            <div class='nc-signup nc-signup-sign'>
                <div class='signup-title signup-title-log'>
                    <h3>
						<span class="sp-l fl">用户登录</span>
						<div class="container-head">
							<span class="sp-r fr">没有海核云谷账号？<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/register.html" class="sign-a">立即注册</a>
							</span>
                        </div>
					</h3>
                </div>
                <div class='signup-content'>
                    <form class="form-horizontal" id='formLogin'>
                        <div class="form-group">
                            <div class="form-group-user clearfix">
                                <div class="input_icon account_icon"></div>
                                <input type="text" placeholder="手机号或员工号" id="name" name='name'>
                                <p class='forget-tip'>手机号或者员工号不能为空</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group-user clearfix">
                                <div class="input_icon password_icon"></div>
                                <input type="password" placeholder="密码" id="setPassword" name='password'>
                                <p class='forget-tip'>密码不能为空</p>
                            </div>
                        </div>
                        <div class="form-group form-group-yz">
                            <div class="form-group-user clearfix" style="padding:0 22px;">
                                <div class="input_icon randomcode_icon"></div>
                                <input type="text" placeholder="验证码" id="verifyCode" name="verifyCode">
                                <div class='' style="float:right;">
                                    <img style="cursor:pointer;" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();"  />
                                    <a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a>
                                </div>
                            </div>
                        </div>
                        <div class='form-group form-group-h'>
                            <div class=''>
                                <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/forgetpassword.html' class='forget-password'>忘记密码？</a>
                            </div>
                        </div>

                        <a href='javascript:void(0)' id="loginButton" class="ahover">
                            <div class='form-group form-group-dl'>
                                <div class=''>
                                    登录
                                </div>
                            </div>
                        </a>
                        <div class='form-group'>
                        </div>
                    </form>
                </div>
            </div>
            <!-- E 登录页面 -->
        </div>
        <!-- 
        <div class='wraper bottom-copyright' id='footer'>
            版权所有 © Copyright 2017 海核云谷.保留一切权利
        </div>
         -->
    </div>
	</body>
<script type="text/javascript">
function refreshCodeR(){
	jQuery("#code_img_r").attr("src",domain+"/verify.html?d"+new Date().getTime());
}
$(function(){
		//增加回车键监听
		$(document).keydown(function(e){
			var curKey = e.which;
			if (curKey == 13) {
				var jalert = $("#popup_container");
				//如果有弹出窗，响应弹出窗内容
				if(jalert && jalert.length > 0){
					$("#popup_ok").click();
				} else{
					//响应表单提交					
					$("#loginButton").click();
				}
				return false;
			}
		});

		$("#loginButton").click(function() {
			var name = $("#name").val();
			if (name == '') {
				jAlert("用户名不能为空");
				return;
			}
			if (name.length < 6) {
				jAlert("用户名至少6位");
				return;
			}
			var setPassword = $("#setPassword").val();
			if (setPassword == '') {
				jAlert("密码不能为空");
				return;
			}
			if (setPassword.length < 6) {
				jAlert("密码至少6位");
				return;
			}
			var verifyCode = $("#verifyCode").val();
			if (verifyCode == '') {
				jAlert("请输入验证码");
				return;
			}
			if (verifyCode.length != 4) {
				jAlert("请输入4位验证码");
				return;
			}

			if ($("#formLogin").valid()) {
				$(".ahover").attr("disabled", "disabled");
				var params = $('#formLogin').serialize();
				$.ajax({
					type : "POST",
					url : domain + "/dologin.html",
					dataType : "json",
					async : false,
					data : params,
					success : function(data) {
						if (data.success) {
							window.location = domain + data.backUrl;
						} else {
							jAlert(data.message);
							//刷新验证码
							refreshCode();
							$(".ahover").removeAttr("disabled");
						}
					},
					error : function() {
						jAlert("异常，请重试！");
						$(".ahover").removeAttr("disabled");
					}
				});
			}

		});

	});
</script>
<#include "/front/commons/_endbig.ftl" />