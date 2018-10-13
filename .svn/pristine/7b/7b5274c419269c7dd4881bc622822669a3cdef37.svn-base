<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/orders"/>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<!-- 订单列表公共js方法 -->
<script>
var codeBox;
$(function (){
	<#noescape>
		codeBox = eval('(${initJSCodeContainer("ORDERS_ORDER_STATE","ORDER_PAYMENT_STATUS","ORDER_INVOICE_STATUS")})');
	</#noescape>
	
	// 查询按钮
	$('#btn-search').click(function() {
		$('#dataGrid').datagrid('reload', queryParamsHandler());
	});
	
	// 订单打印
	$('#btn-print').click(function() {
		var selected = $('#dataGrid').datagrid('getSelected');
		if (!selected) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		window.open("${currentBaseUrl}/print?id="+selected.id);
	});
	
	//订单详情
	$("#btn-details").click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if(!selected){
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		location.href="${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/orders/details?id="+selected.id;
	});
	
	
	//订单先发货
	$("#btn-delivery").click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if(!selected){
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		if(selected.isWelfareOrder == '2'){
			$.messager.alert('提示', '福利订单不能先发货');
			return;
		}
		
		$.ajax({
				type : "GET",
				url :  '${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/orders/update.html',
				data : {id:selected.id},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
					}else {
						$.messager.alert('提示', data.message);
					}
				},
				error : function() {
					$.messager.alert('提示', '数据加载失败！');
				}
		});
	});
	
	//取消订单
	$("#btn-delete").click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if(!selected){
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		
		$.messager.confirm('确认', '确定要取消该订单吗？此操作不可撤销', function(r){
		if (r){
			$.ajax({
				type : "GET",
				url :  '${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/orders/cancalorder.html',
				data : {id:selected.id},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
					}else {
						$.messager.alert('提示', data.message);
					}
				},
				error : function() {
					$.messager.alert('提示', '数据加载失败！');
				}
			});
		}
		});
	});
	
	//修改订单价格
	$("#btn-editMoney").click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if(!selected){
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		if(selected.isWelfareOrder == '2'){
			$.messager.alert('提示', '福利订单不能修改订单价格');
			return;
		}
		
		$("#amount").val(selected.moneyOrder);
		$('#_orderAmountOpt').dialog('open');
	});
	
	// 订单金额修改取消
	$("#amountOptCancel").click(function(){
		$('#_orderAmountOpt').dialog('close');
	});
	
	// 订单金额修改确定
	$("#amountOptOk").click(function(){
		var amount = $("#amount").val();
		if(Number(amount) <= 0){
			$.messager.alert('提示', '请输入正确的金额');
		}
		
		var selected = $('#dataGrid').datagrid('getSelected');
		
		if(amount != selected.moneyOrder){
			$.ajax({
				type : "GET",
				url :  '${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/orders/updateAmount.html',
				data : {id:selected.id,amount:amount},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
					}else {
						$.messager.alert('提示', data.message);
					}
				},
				error : function() {
					$.messager.alert('提示', '数据加载失败！');
				}
			});
		}
		$('#_orderAmountOpt').dialog('close');
	});
	
});

function getState(value, row, index) {
	var box = codeBox["ORDERS_ORDER_STATE"][value];
	return box;
}

function paymentStatus(value, row, index) {
	var box = codeBox["ORDER_PAYMENT_STATUS"][value];
	return box;
}

function invoiceStatus(value, row, index) {
	var box = codeBox["ORDER_INVOICE_STATUS"][value];
	return box;
}

function detailFormatter(index,row){
    return '<div style="padding:2px"><table class="ddv"></table></div>';
}

function onExpandRow(index,row){
    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
    ddv.datagrid({
       fitColumns:true,
       singleSelect:true,
       method:'get',
       url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/ordersProduct/getOrdersProduct?orderId='+row.id,
		loadMsg : '数据加载中...',
		height : 'auto',
		columns : [[{
			field : 'productName',
			title : '货品名称',
			width : 120,
			align : 'left',
			halign : 'center'
		}, {
			field : 'specInfo',
			title : '规格',
			width : 70,
			align : 'left',
			halign : 'center'
		}, {
			field : 'productSku',
			title : '商品SKU',
			width : 80,
			align : 'left',
			halign : 'center'
		}, {
			field : 'moneyPrice',
			title : '商品单价',
			width : 50,
			align : 'center'
		}, {
			field : 'number',
			title : '商品数量',
			width : 50,
			align : 'center'
		}, {
			field : 'moneyAmount',
			title : '网单金额',
			width : 50,
			align : 'center'
		}]],
		onResize : function() {
			$('#dataGrid').datagrid('fixDetailRowHeight',index);
		},
		onLoadSuccess : function() {
			setTimeout(function() {
				$('#dataGrid').datagrid('fixDetailRowHeight',index);
			}, 0);
		}
	});
}
</script>
<!-- 打印加载窗口 -->
<div id="devWin"></div>