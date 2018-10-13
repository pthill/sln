<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operate/park"/>

<link rel="stylesheet"
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/colorbox.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/jquery.colorbox-min.js"></script>

<script language="javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/lodop/LodopFuncs.js"></script>
<script language="javascript" type="text/javascript"> 
</script> 

<script language="javascript">
	var codeBox;
	$(function() {
		
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("TRANSPORT_MODEL","PARK_STATE","AREA_CODE")})');
		</#noescape>
		// 查询按钮
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		$('#btn_add').click(function(){
			location.href = '${currentBaseUrl}/add';
		})
		//地图标记
		$('#btn_sign').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/parkSign?id='+selected.id;
		});
		//停用
		$("#btn_cancel").click(function(){
            var selected = $('#dataGrid').datagrid('getSelections');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }
            var ids ='';
            for(var i=0; i<selected.length; i++){
                ids += selected[i].id+','
                //判断选中行中是否有不能操作的数据
                if(selected[i].state ==1){
                    $.messager.alert('提示', '请选择未停用的行');
                    return;
                }
            }
            updateState(ids,1)
		});
		//编辑
		$("#btn_update").click(function () {
            var selectedCount = $('#dataGrid').datagrid('getSelections');
            var selected=$('#dataGrid').datagrid('getSelected');
            if(selectedCount.length!=1){
                $.messager.alert('提示','请选择一行。');
                return;
			}
            if(selected.state==2){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href='${currentBaseUrl}/edit?id='+selected.id;
            }
        });
		//启用
		$("#btn_edit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelections');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var ids ='';
			for(var i=0; i<selected.length; i++){
				ids += selected[i].id+','
				//判断选中行中是否有不能操作的数据
				if(selected[i].state ==2){
					$.messager.alert('提示', '请选择未启用的行');
					return;
				}
			}
			updateState(ids,2)
		});
	});
	function paymentState(value, row, index) {
		var box = codeBox["PARK_STATE"][value];
		return box;
	}
    function areaState(value, row, index) {
        return codeBox["AREA_CODE"][value];
    }
	function updateState(ids,state){
	$.messager.confirm('确认','确认操作？', function(r) {
				if (r) {
					$.messager.progress({
									text : "提交中..."
								});
								$.ajax({
									type:"GET",
								    url: "${currentBaseUrl}/doUpdate",
									dataType: "json",
								    data: "ids=" + ids+"&state="+state,
								    cache:false,
									success:function(data, textStatus){
										if (data.success) {
											$.messager.alert('提示','操作成功');
											$('#dataGrid').datagrid('reload');
									    } else {
									    	$.messager.alert('提示',data.message);
									    	$('#dataGrid').datagrid('reload');
									    }
										$.messager.progress('close');
									}
								});
						}
			});
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		园区列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">园区编码:</label> <input type="text"
							class="txt" id="q_parkCode" name="q_parkCode"
							value="${queryMap['q_parkCode']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">园区编码:</label> <input type="text"
							class="txt" id="q_parkName" name="q_parkName"
							value="${queryMap['q_parkName']!''}" />
					</p>
					<p class="p4 p-item">
					<label class="lab-item">状态 :</label> <@cont.select id="q_state"
					codeDiv="PARK_STATE" value="${q_state!''}" name="q_state" style="width:100px"/>
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
				<th field="id" width="100" align="left" hidden="true" halign="center"></th>
				<th field="parkCode" width="100" align="left" halign="center">编码</th>
				<th field="parkName" width="100" align="left" halign="center">名称</th>
				<th field="longitude" width="100" align="left" halign="center">经度</th>
				<th field="latitude" width="100" align="left" halign="center">纬度</th>
				<th field="state" width="100" align="left" halign="center" formatter="paymentState">状态</th>
                <th field="tel" width="100" align="left" halign="center">电话</th>
                <th field="area" width="100" align="left" halign="center" formatter="areaState">地区</th>
                <th field="province" width="100" align="left" halign="center">省份</th>
                <th field="city" width="100" align="left" halign="center">城市</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/operate/park/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/park/edit">
        <a id="btn_update" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/park/cancel">
		<a id="btn_cancel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">停用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/park/enable">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
		</@shiro.hasPermission>		
		<@shiro.hasPermission name="/admin/operate/park/parkSign">
		<a id="btn_sign" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sign" plain="true">地图标记</a>
		</@shiro.hasPermission>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
