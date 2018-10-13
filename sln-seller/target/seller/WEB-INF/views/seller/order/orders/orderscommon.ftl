<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders"/>

<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/easyui/datagrid-detailview.js"></script>
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
		location.href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/details?id="+selected.id;
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

function showMoneyProduct(value, row, index){
	var rows = $('#dataGrid').datagrid('getRows');//获得所有行
    var orderType = rows[index].orderType;//根据index获得其中一行。
    if(orderType==6){
    	return value+"(积分)";
    }else{
   	 	return value;
    }
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
    var orderType =$('#dataGrid').datagrid('getRows')[index].orderType
    ddv.datagrid({
       fitColumns:true,
       singleSelect:true,
       method:'get',
       url:'${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/ordersProduct/getOrdersProduct?orderId='+row.id,
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
			align : 'center',
		    formatter: function(value,row,index){  
                if (orderType==6){  
                    return value+'(积分)';  
                } else {  
                    return value;  
                }  
            } 
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

<div id="devWin"></div>