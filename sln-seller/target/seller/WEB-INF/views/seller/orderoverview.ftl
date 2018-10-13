<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/index.css">
<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/css/ext.css">
<link rel="stylesheet" type="text/css"
	href="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/themes/metro/easyui.css">
<script type="text/javascript"
	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/easyui/jquery.min.js"></script>

<script
	src='${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/jslib/echarts/theme.js'></script>
<script
	src='${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/jslib/echarts/echarts.js'></script>

<script>
$(function() {
	var myChart = echarts.init($("#chartdiv")[0]);
	<#noescape>
		myChart.setOption(eval(${option}));
	</#noescape>
});
</script>

<div id="chartdiv" style="width: 100%; height: 100%;"></div>
