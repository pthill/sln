<#import "/admin/commons/_macro_controller.ftl" as cont/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>海核云谷多商家管理系统平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/func.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.form.js"></script>
<script type="text/javascript"
		src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/formVerification.js"></script>
<script type="text/javascript"
		src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/upLoadPicture.js"></script>
<script type="text/javascript"
		src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.iframe-transport.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.fileupload.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.filedownload.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.multifile.upload.js"></script>

<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/css/style.css"
	type="text/css"></link>

<script>
	var chromestyle = "<style>"+
		".panel-fit body.panel-noscroll {"+
		"	position: initial;"+
		"}"+
	
		".panel-fit {"+
		"	height: 100%;"+
		"	margin: 0;"+
		"	padding: 0;"+
		"	border: 0;"+
		"	overflow: inherit;"+
		"}"+
		"</style>";
	var mozstyle = "<style>"+
		".panel-fit {"+
		"	height: 100%;"+
		"	margin: 0;"+
		"	padding: 0;"+
		"	border: 0;"+
		"	overflow: inherit;"+
		"}"+
		"</style>";
	var brow = $.browser;
	if (brow.chrome) {
		document.write(chromestyle);
	} else{
		document.write(mozstyle);
	}
	
	$(document).keydown(function(e){
		var curKey = e.which;
		if (curKey == 13) {
			return false;
		}
	});
</script>
</head>
<body class="easyui-layout"><#assign
	form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>