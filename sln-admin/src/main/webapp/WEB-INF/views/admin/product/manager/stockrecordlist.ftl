<#import "/admin/commons/_macro_controller.ftl" as cont/>
<style>
 .p3 img{
 	margin-left: 55px;
    max-width: 300px;
  }
.panel-fit body.panel-noscroll {
	overflow-y: scroll;
}
</style>
<script type="text/javascript">
var codeBox;
function submitProForm(){
	$('#auditGrid').datagrid('reload',queryParamsHandler());
}

$(function(){
	<#noescape>
	codeBox = eval('(${initJSCodeContainer("FLASH_PRODUCT_STATUS")})');
	</#noescape>
		
	});


</script>
                
<div data-options="region:'center'" border="false" style="height:100%">
	<table id="auditGrid" class="easyui-datagrid"
			data-options="singleSelect:true
						,fitColumns:true
						,collapsible:true
						,toolbar:'#audit-gts'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/stockRecordList?id=${productGoodsId}'
    					,method:'get'">
		<thead>
			<tr>
	            <th field="sku" width="100" align="center">SKU</th>
	            <th field="oldStock" width="90" align="center" >修改前库存</th>
	            <th field="newStock" width="90" align="center" >修改后库存</th>
	            <th field="oldStockWarning" width="90" align="center" >修改前预警库存</th>
	            <th field="newStockWarning" width="90" align="center" >修改后预警库存</th>
	            <th field="oldMallPcPrice" width="90" align="center" >修改前商城价</th>
	            <th field="newMallPcPrice" width="90" align="center" >修改后商城价</th>
	            <th field="oldMalMobilePrice" width="90" align="center" >修改前商城价Mobile</th>
	            <th field="newMalMobilePrice" width="90" align="center" >修改后商城价Mobile</th>
	            <th field="updateTime" width="100" align="center" >修改时间</th>
	            <th field="updateUserName" width="50" align="center" >修改人</th>
			</tr>
		</thead>
	</table>
	<div id="audit-gts">
	</div>
</div>
