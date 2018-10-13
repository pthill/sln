<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>海核云谷商家系统</title>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/jquery.min.js"></script>
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/font-awesome.css"
	rel="stylesheet" />
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/login.css"
	rel="stylesheet" />
<script>
	//刷新验证码
	function refreshCode() {
		$("#code_img").attr("src","${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/system/verifyCode?d"
						+ new Date().getTime());
	}
</script>
</head>

<body class="login-page">
	<div class="login-form">
		<div class="login-content">
			<div
				style="background: #F3F3F3; padding: 10px 0px; border-radius: 3px 3px 0 0;">
				<a href="javascript:;"> <img
					src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/img/haihetaologo.png"
					alt="">
				</a>
			</div>
			<form method="post"
				action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/doLogin"
				role="form" id="form_login" class="form_login">
				<div class="form-group loginTitle">用户登录</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="fa fa-user"></i>
						</div>
						<input type="text" class="form-control" name="name" required
							oninvalid="setCustomValidity('请输入用户名')"
							oninput="setCustomValidity('')" id="username"
							placeholder="用户名" />
					</div>
					<!-- <div class="erro">请输入手机号</div> -->
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="fa fa-key"></i>
						</div>
						<input type="password" class="form-control" name="password"
							required oninvalid="setCustomValidity('请输入密码')"
							oninput="setCustomValidity('')" id="password" placeholder="密码" />
					</div>
					<!-- <div class="erro">请输入密码</div> -->
				</div>

				<div class="form-group form-group-viricode">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="fa fa-keyboard-o"></i>
						</div>
						<input type="text" class="form-control form-control-viricode"
							required oninvalid="setCustomValidity('请输入验证码')"
							maxLength=4
							autocomplete="off"
							oninput="setCustomValidity('')" name="verifyCode"
							placeholder="验证码" />
						<div class=''
							style="float: right; display: inline-block; position: relative;">
							<img onclick="refreshCode();" id="code_img"
								src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/seller/system/verifyCode'
								width="100" height="34"> <a href="javascript:;"
								onclick="refreshCode();" class="a-change">看不清，换一张</a>
						</div>
					</div>
					<div class="erro">${(message)!}</div>
				</div>

				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-login">
						登录</button>
				</div>
			</form>
		</div>
		<div class="botm-copy">版权所有 © Copyright 2017
		海核云谷.保留一切权利</div>
	</div>
</body>
</html>