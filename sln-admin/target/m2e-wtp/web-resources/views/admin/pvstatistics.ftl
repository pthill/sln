<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
<script
	src='${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/echarts/theme.js'></script>
<script
	src='${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/echarts/echarts.js'></script>
	
<script>
$(function() {
	var myChart = echarts.init($("#chartdiv")[0]);
	<#noescape>
		myChart.setOption(eval(${option}));
	</#noescape>
});
</script>

<div id="chartdiv" style="width: 100%; height: 100%;"></div>
