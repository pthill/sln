<#include "/admin/commons/_detailheader.ftl" />
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
	var statusBox;
	 function loadSuccess(data){
    	if(data.rows.length==0){//无数据提示
    		var body1 = $(this).data().datagrid.dc.body1;
    		var body2 = $(this).data().datagrid.dc.body2;
    		body1.find('table').html('<tr class="datagrid-row"><td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber"></div></td></tr>');
    		body2.find('table').width('100%').find('tbody').append('<tr><td style="height: 25px; text-align: center;">没有数据</td></tr>');
    	}
    	
    	$(".colorbox").boxer();
    }
 
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("USE_PRE")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/pcindex/floor/add";
		});
		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/pcindex/floor/edit?pcIndexFloorId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status == 1) {
            	$.messager.alert('提示','使用中的楼层不能删除');
                return;
            }

            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/pcindex/floor/delete",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择启用楼层。');
	            return;
	        }
	        if(selected.status == 1) {
	        	$.messager.alert('提示','已经是启用状态，请勿重复操作');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','不能从停用状态直接修改成使用状态');
	            return;
	        }

	        $.messager.confirm('提示', '确定启用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/pcindex/floor/up",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
		$("#a-gridPre").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择预使用楼层。');
	            return;
	        }
	        if(selected.status == 2) {
	        	$.messager.alert('提示','已经是预使用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定预使用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/pcindex/floor/pre",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    $("#a-gridDown").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择停用图片。');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','已经是停用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定停用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/pcindex/floor/down",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.SLN_FRONT_URL)!}/previewindex.html");
		});
        
		$("#newstypeWin").window({
			width : 666,
			height : 420,
			title : "楼层片",
			closed : true,
			shadow : false,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return statusBox["USE_PRE"][value];
	}

	function imageFormat(value, row, index) {
		return "<a href='${domainUrlUtil.SLN_IMAGE_RESOURCES}" + 
	  		value + "' style='color:#276892' rel='gallery' class='colorbox'>点击查看</a>";
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
           url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/pcindex/floorclass/listbyfloor?floorId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'name',
				title : '名称',
				width : 100,
				align : 'center',
				halign : 'center'
			}, {
				field : 'orderNo',
				title : '排序号',
				width : 50,
				align : 'center',
				halign : 'center'
			}, {
				field : 'status',
				title : '使用状态',
				width : 100,
				align : 'center',
				halign : 'center',
				formatter : function(value, row, index) {
					return statusBox["USE_PRE"][value];
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
    
    function statusFormatForDetail(index, row){
    	return statusFormat(1, index, row);
	}

	function addFormat(value, row, index){
		return "<a href='" + value + "' style='color:#276892' target='_blank'>点击访问</a>";
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">PC端首页楼层列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">楼层名称：<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/></p>
				<p class="p4 p-item">状态：<@cont.select id="q_status" value="${(q_status)!''}" codeDiv="USE_PRE" style="width:80px" /></p>
			</div>
		</form>
		</div>
	</div>
</div>


<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
			data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,collapsible:true
						,toolbar:'#gridTools'
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/pcindex/floor/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:loadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="name" width="100" align="left" halign="center">名称</th>
	            <th field="orderNo" width="50" align="center" halign="center">排序号</th>
	            <th field="masterImage" width="80" align="center" halign="center" formatter="imageFormat">主图</th>
				<th field="masterLinkUrl" width="80" align="center" halign="center" formatter="addFormat">主图链接地址</th>
	            <th field="advImage" width="80" align="center" halign="center" formatter="imageFormat">顶图</th>
				<th field="advLinkUrl" width="80" align="center" halign="center" formatter="addFormat">顶图链接地址</th>
	            <th field="status" width="70" align="center" halign="center" formatter="statusFormat">使用状态</th>
	            <th field="updateUserName" width="100" align="center">最后修改人</th>
	            <th field="updateTime" width="150" align="center">最后修改时间</th>
	            <th field="remark" width="100" align="center" halign="center">备注</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/pcindex/floor/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/pre">
		<a id="a-gridPre" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">预使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/up">
		<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/down">
		<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">停用</a>
		</@shiro.hasPermission>
		<a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>

<#include "/admin/commons/_detailfooter.ftl" />