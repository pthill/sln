<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/messageType"/>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.SLN_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("MESSAGE_IS_NORM","RECEPTION_TYPE")})');
	    </#noescape>
	    
		$("#a-gridAdd").click(function() {
			location.href = currentBaseUrl + "/edit";
		});

		$("#a-gridEdit").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			if(selected.state == 0){
				$.messager.alert('提示', '消息类型为启用状态 无法编辑 请禁用后再试。');
				return;
			}
			location.href = currentBaseUrl + "/edit?id="+selected.id;
		});
		
		

		$("#back").click(function(){
			location.href = currentBaseUrl;
		});
		
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
	    });
		   
	});
	
	function stateFormat(value,row,index){
		return codeBox["MESSAGE_IS_NORM"][value];
	}
	
	function receptionTypeFormat(value,row,index){
		return codeBox["RECEPTION_TYPE"][value];
	}
	
	function editState(state){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			if(selected.state == state){
				var message = state == 0 ? '启用':'禁用';
				$.messager.alert('提示', '消息类型已结是'+message+'状态 请勿重复操作。');
				return;
			}
			
			$.ajax({
				url : currentBaseUrl + "/editState",
				type: 'GET',
				dataType:'json',
				data: {'id':selected.id,'state':state},
				success:function(data){
					if(!data.success){
						$.messager.alert('提示', data.message);
						return;
					}
					$('#dataGrid').datagrid('reload',queryParamsHandler());
				}
			});
		}

</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		消息类型管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">类型名称：</label> <input type="text"
							class="txt" id="q_typeName" name="q_typeName"/>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="typeName" width="70">类型名称</th>
				<th field="typeNo" width="100">编号</th>
				<th field="receptionType" width="70" align="center" formatter="receptionTypeFormat" >绑定对象</th>
				<th field="state" width="60" align="center" formatter="stateFormat">状态</th>
				<th field="createUserName" width="70" align="center" >创建人</th>
				<th field="createTime" width="100" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/messageType/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/messageType/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/messageType/edit">
        <a id="a-gridEditState1" href="javascript:void(0)" onclick="editState(1)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">禁用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/messageType/edit">
        <a id="a-gridEditState0" href="javascript:void(0)" onclick="editState(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
		</@shiro.hasPermission>
	</div>
	
</div>

<#include "/admin/commons/_detailfooter.ftl" />
