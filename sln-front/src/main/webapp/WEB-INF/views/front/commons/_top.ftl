<#import "/front/commons/_macro_controller.ftl" as cont/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<title>海核云谷园区综合服务平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" type="text/css" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/bootstrap.min.css'/>
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/user.css"/>
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/base.css">
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
	<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
	<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/func.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/checkvalue.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	
	
	<style type='text/css' rel="stylesheet">
	</style>
	<script type="text/javascript">
		var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
	</script>
  </head>
	<body class='wp1200'>
	<#assign getImagePathMethod="com.sln.web.util.freemarker.ProductImagePathModel"?new()/>
	<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
			<div class='wrapper'>
			<div class='container'>
				<ul class='collect lh'>
					<li class='fore1'>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html'>海核云谷</a>
					</li>
				</ul>
				<ul class='shortcut-right lh'>
				   <#if Session.memberSession??>
				   		<#assign user = Session.memberSession.member>
				   </#if>
				   <#if user??>
				   		<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html' target="_blank" class='login'>${(user.name)!''}</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/logout.html'  onclick="logout()" class='regist'>退出</a>
						</li>
						<li class='fore2 ld'>
							<span></span>
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html" target="_blank">我的订单</a>
						</li>	
				   	 
				   	<#else>
						<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/login.html' class='login'>你好，请登录</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/register.html' class='regist'>免费注册</a>
						</li>
				   </#if>
					
					<li class='fore2-1 ld ff-vip' style='padding-left:12px;'>
						<span></span>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html'>会员中心</a>
					</li>
					<li class='fore3 ld app-ff menu' style="text-align:center;width: 126px;padding: 0 5px;">
						<div class="menubox">
							<div class="mu-line">
								<span></span>
								关注海核云谷<i class="ci-t"></i>
							</div>
							<div class="imgwx-ej" style="display: none;border:1px solid #e0e0e0;border-top:0;">
								<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/haiheyunguwx.jpg" width="80" height="80">
								<div style="padding: 0 6px; line-height: 20px;">关注海核云谷微信公众号</div>
							</div>
						</div>
					</li>
					
					<li class='fore3 ld app-ff menu' style="text-align:center;width: 78px;padding: 0 5px;">
						<div class="menubox">
							<div class="mu-line">
								<span></span>
								客户服务<i class="ci-t"></i>
							</div>
							<div class="imgwx-ej khfw clearfix " style="display: none">
								<div class="" style="text-align: left;">客户</div>
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_1.html">帮助中心</a>
								</div>
								<!--
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_2.html">店主之家</a>
								</div>
								-->
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_3.html">支付方式</a>
								</div>
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_4.html">售后服务</a>
								</div>
								<!--
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_5.html">客服中心</a>
								</div>
								-->
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_6.html">关于我们</a>
								</div>
							</div>
						</div>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a target="_blank" href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/step1.html'>商家入驻</a>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a target="_blank" href='http://www.hhyungu.com/'>联系我们</a>
					</li>
				</ul>
			</div>
		</div>

	<script type="text/javascript">
			$('.receive').on('click',function(){
				if($('.toolbar-wrap').css('display')=='none'){
						$('.toolbar-wrap').show(500);
				}else {
						$('.toolbar-wrap').hide(500);
				}
			});
			$('.close-panel').on('click',function(){
					$('.toolbar-wrap').hide(500);
			});

			// 点击更多参数
			$(".J-more-param").on("click",function(){
				$(".trig-item").eq(1).addClass("curr").siblings("li").removeClass("curr");
				$(".b-table").eq(1).addClass("bcent-table").siblings(".b-table").removeClass("bcent-table");
			});

			// 关注海核云谷显示微信码
			$(".shortcut-right li").on("mouseover",function(){
				$(this).find(".imgwx-ej").show().parent(".menubox").addClass("show-wx").find(".mu-line").css({"border-left":"1px solid #e0e0e0","border-right":"1px solid #e0e0e0"});
				$(this).find(".ci-t").css({"background-position":"-30px -17px"})
			});
			$(".shortcut-right li").on("mouseleave",function(){
				$(this).find(".imgwx-ej").hide().parent(".menubox").removeClass("show-wx");
				$(".mu-line").css({"border-left":"0","border-right":"0"});
				$(".ci-t").css({"background-position":"-21px -17px"})
			});
	</script>		