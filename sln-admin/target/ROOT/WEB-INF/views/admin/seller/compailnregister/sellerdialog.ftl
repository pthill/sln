<div id="sellerDialog" class="easyui-dialog popBox" title="商家列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-award-act">


<div id="searchbar" data-options="region:'north'" style="height: 45px;"
		border="false">
		<div id="searchbox" class="head-seachbox">
			<div class="w-p99 marauto searchCont">
				<form class="form-search" action="doForm" method="post"
					id="queryForm" name="queryForm">
					<div class="fluidbox">
						<p class="p4 p-item">
							<label class="lab-item">商家名称 :</label> <input type="text"
								class="txt" id="q_sellerName" name="q_sellerName" value="${q_sellerName!''}" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="brandGridTools">
	<a id="a-gridSearch" href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>

	<div class="easyui-layout" data-options="fit:true">
			<table id="normDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							singleSelect : false,
							fit:true,
							fitColumns:true,
							pagination:true,
							onLoadSuccess:normloadSuccess,
							url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/compailnregister/dialog',
							method:'get'">
				<thead>
					<tr>
						<th field="ck" checkbox="true"/>
		                <th field="sellerName" width="100" align="left" halign="center">投诉商家</th>
						<th field="name" width="100" align="left" halign="center">投诉用户名称</th>
						
					</tr>
				</thead>
			</table>
	</div>

	<div id="dlg-buttons-award-act" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submit1()">确定</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sellerDialog').dialog('close')">取消</a>
	</div>
</div>

<script language="javascript">
$(function(){
  $('#a-gridSearch').click(function(){
            $('#normDataGrid').datagrid('reload',queryParamsHandler());
        });
})
	function submit1() {
		var selectedRow = $("#normDataGrid").datagrid('getSelections');
		if (!selectedRow || selectedRow.length == 0) {
			$.messager.alert('友情提示', '请至少选择一个对象');
			return false;
		}
	   if(selectedRow.length>1){
	   $.messager.alert('友情提示', '只能选择一个商家');
			return false;
	   }
		var callbackfunc = eval('typeCallBack');
		callbackfunc(selectedRow);
		$("#sellerDialog").dialog('close');
	}
	
</script>