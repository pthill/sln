<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/messageManager"/>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.SLN_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("SEND_STATE","RECEPTION_TYPE")})');
	    </#noescape>
	    
		$("#a-gridAdd").click(function() {
			location.href = currentBaseUrl + "/templateEdit";
		});

		$("#a-gridEdit").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			location.href = currentBaseUrl + "/templateEdit?id="+selected.id;
		});

		$("#back").click(function(){
			location.href = currentBaseUrl + "?type=0";
		});
		
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
	    });
		
		$("#btn-del").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			$.messager.confirm('确认','确定删除该消息吗？', function(r) {
				if (r) {
					$.ajax({
						url:currentBaseUrl+'/del?id='+selected.id,
						success:function(data){
							var msg = "删除失败，请稍后重试";
							if (data.success) {
								msg = '删除成功';
							}
							$.messager.show({
								title : '提示',
								msg : msg,
								showType : 'show'
							});
							$('#dataGrid').datagrid('reload');
						}
					});
				}
			});
		});
	});
	
	function sendState(value,row,index){
		return codeBox["SEND_STATE"][value];
	}
	
	function receptionTypeFormat(value,row,index){
		return codeBox["RECEPTION_TYPE"][value];
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		消息管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">标题：</label> 
						<input type="text" class="txt" id="q_title" name="q_title"/>
						<input type="hidden" name="q_isMessageTemplate" value="0">
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
				<th field="title" width="70">标题</th>
				<th field="content" width="140">内容</th>
				<th field="messageCode" width="70">标识</th>
				<th field="typeName" width="40" align="center">消息类型</th>
				<th field="receptionType" width="40	" align="center" formatter="receptionTypeFormat">接收对象</th>
				<th field="createName" width="60" align="center">创建人</th>
				<th field="createTime" width="60" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/messageManager/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/messageManager/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/messageManager/del">
        <a id="btn-del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
	
</div>

<#include "/admin/commons/_detailfooter.ftl" />
