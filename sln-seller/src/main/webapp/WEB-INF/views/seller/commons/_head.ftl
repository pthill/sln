<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>海核云谷商家系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="Keywords" content="海核云谷商家系统">
<meta name="Description" content="海核云谷商家系统" />

<meta name="description" content="form validation" />
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable = 0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/img/favicon.png"
	type="image/x-icon">

<!--Basic Styles-->
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/font-awesome.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/beyond.css">
	
<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/index.css">

<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/ext.css">

<link rel="stylesheet" type="text/css"
	href="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/themes/mobile.css">
<link rel="stylesheet" type="text/css"
	href="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/skins/azure.min.css">
	
<script>
	var domain = "${domainUrlUtil.SLN_URL_RESOURCES}";
	var imgdomain = "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}";
</script>

<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/js/jquery.filedownload.js"></script>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/js/func.js"></script>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/jquery.easyui.mobile.js"></script>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/js/jquery.form.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/My97DatePicker/WdatePicker.js"></script>

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/js/skins.js"></script>

</head>
<body style="overflow-y: hidden;">
	<#import "/seller/commons/_macro_controller.ftl" as cont/> <#assign
	form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>

	<!-- 加载动画开始 -->
	<#include "_loading.ftl">
	<!-- 加载动画结束 -->
	<!-- 头部导航栏开始 -->
	<#include "_navbar.ftl">
	<!-- 头部导航栏结束 -->