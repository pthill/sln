<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/easyui/datagrid-detailview.js"></script>
<script>

$(function (){
	<#noescape>
		codeBox = eval('(${initJSCodeContainer("ORDER_INVOICE_STATUS")})');
	</#noescape>
	});

function invoiceStatus(value, row, index) {
	var box = codeBox["ORDER_INVOICE_STATUS"][value];
	return box;
}

function detailFormatter(index,row){
    return '<div style="padding:2px"><table class="ddv"></table></div>';
}

//获取发货单详情
function onExpandRow(index,row){
    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
    ddv.datagrid({
       fitColumns:true,
       singleSelect:true,
       method:'get',
       url:'${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/ordersProduct/getByOrderSn?orderSn='+row.orderSn+'&supplierId='+row.supplierId,
		loadMsg : '数据加载中...',
		height : 'auto',
		columns : [[{
			field : 'productName',
			title : '货品名称',
			width : 120,
			align : 'left',
			halign : 'center'
		}, {
			field : 'productSku',
			title : '商品SKU',
			width : 80,
			align : 'left',
			halign : 'center'
		},{
			field : 'number',
			title : '商品数量',
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