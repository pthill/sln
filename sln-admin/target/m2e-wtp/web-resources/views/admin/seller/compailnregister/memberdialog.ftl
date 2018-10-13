<div id="memberDialog" class="easyui-dialog popBox" title="会员列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons">


<div id="searchbar" data-options="region:'north'" style="height: 45px;"
		border="false">
		<div id="searchbox" class="head-seachbox">
			<div class="w-p99 marauto searchCont">
				<form class="form-search" action="doForm" method="post"
					id="queryForm" name="queryForm">
					<div class="fluidbox">
						<p class="p4 p-item">
							<label class="lab-item">会员名称 :</label> <input type="text"
								class="txt" id="q_name" name="q_name" value="${q_name!''}" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="brandGridTools">
	<a id="ali-gridSearch" href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>

	<div class="easyui-layout" data-options="fit:true">
			<table id="membernormDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							singleSelect : false,
							fit:true,
							fitColumns:true,
							pagination:true,
							onLoadSuccess:memberloadSuccess,
							url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/compailnregister/memberdialog',
							method:'get'">
				<thead>
					<tr>
						<th field="ck" checkbox="true"/>
		                <th field="name" width="100" align="left" halign="center">会员名称</th>
						<th field="registerTime" width="100" align="left" halign="center">注册时间</th>
						
					</tr>
				</thead>
			</table>
	</div>

	<div id="dlg-buttons" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submit()">确定</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sellerDialog').dialog('close')">取消</a>
	</div>
</div>

<script language="javascript">
$(function(){
  $('#ali-gridSearch').click(function(){
            $('#membernormDataGrid').datagrid('reload',queryParamsHandler());
        });
})
	function submit() {
		var selectedRow = $("#membernormDataGrid").datagrid('getSelections');
		if (!selectedRow || selectedRow.length == 0) {
			$.messager.alert('友情提示', '请至少选择一个对象');
			return false;
		}
	   if(selectedRow.length>1){
	   $.messager.alert('友情提示', '只能选择一个用户');
			return false;
	   }
		var callbackfunc = eval('memberCallBack');
		callbackfunc(selectedRow);
		$("#memberDialog").dialog('close');
	}
	
</script>