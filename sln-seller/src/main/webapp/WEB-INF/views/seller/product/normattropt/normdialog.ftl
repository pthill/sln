<div id="normDialog" class="easyui-dialog popBox" title="商品规格列表"
	style="width: 725px; height: 300px;"
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
							<label class="lab-item">规格名称 :</label><input type="text"
								class="txt" id="q_name" name="q_name" value="${q_name!''}" />
						</p>
						<p class="p4 p-item">
							<label class="lab-item">规格类型 :</label><@cont.select id="q_type"
							value="${(norm.type)!''}" codeDiv="NORM_TYPE" mode="1"/>
						</p>
						<p class="p4 p-item">
							<label class="lab-item">状态 :</label><@cont.select id="q_state"
							value="${(norm.state)!''}" codeDiv="STATE" mode="1"/>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" border="false">
			<table id="normDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							pagination : true,
							singleSelect : true,
							fit:true,
							fitColumns:true,
							pageList:[10,20,50,100],
							pageSize:20,
							toolbar:'#awardActGridTools',
							url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/norm/list',
							queryParams:getQueryParamsHandler(),
							method:'get'">
				<thead>
					<tr>
						<th field="name" width="150" align="center">规格名称</th>
						<th field="type" width="100" align="center" formatter="typeFormat">规格类型</th>
						<th field="createId" width="150" align="center">创建人</th>
						<th field="createTime" width="60" align="right">创建时间</th>
						<th field="sort" width="60" align="right">排序</th>
						<th field="image" width="110" align="center">默认图片</th>
					</tr>
				</thead>
			</table>

		</div>
	</div>
</div>
<div id="awardActGridTools">
	<a id="a-gridQuery" href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-search" plain="true">查询</a>
</div>
<div id="dlg-buttons-award-act" style="text-align: right">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="submit()">确定</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#normDialog').dialog('close')">取消</a>
</div>
<script language="javascript">
	$(function() {
		<#noescape>normTypeBox = eval('(${initJSCodeContainer("NORM_TYPE")})');
		</#noescape>

		$("#a-gridQuery").click(function() {
			$('#normDataGrid').datagrid('load', getQueryParamsHandler());
		});
	})

	function submit() {
		var selectedRow = $("#normDataGrid").datagrid('getSelected');
		if (selectedRow == null) {
			$.messager.alert('友情提示', '请至少选择一个对象');
			return false;
		}
		var callbackfunc = eval('normCallBack');
		callbackfunc(selectedRow);
		$("#normDialog").dialog('close');
	}

	function getQueryParamsHandler() {
		var strParams = '{';
		$("[name^='q_']").each(function() {
			strParams += '"' + $(this).attr('name') + '"';
			strParams += ':';
			strParams += '"' + $(this).val() + '"';
			strParams += ',';
		});
		strParams = strParams.substr(0, strParams.length - 1);
		strParams += '}';
		return eval('(' + strParams + ')');
	}

	function typeFormat(value, row, index) {
		return normTypeBox["NORM_TYPE"][value];
	}
</script>