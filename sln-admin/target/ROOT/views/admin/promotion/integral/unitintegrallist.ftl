<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/promotion/unitactintegral"/>
<script src='${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>	
<script	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.filedownload.js"></script>
<script language="javascript">
	var codeBox;
	$(function(){
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		
		<#--导出列表-->
		 $("#a-export").click(function () {
            $.messager.confirm('提示', '确定导出积分统计吗？', function(r){
                if (r){
                    $.fileDownload('${currentBaseUrl}/importlist',{data:queryParamsHandler()});
                }
            });
         })
		
		$("#a-exportDetail").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			var params = '{"q_company":"'+selected.company+'","q_dept":"'+selected.dept+'","q_starTime":"'+$("#q_starTime").val()+'","q_endTime":"'+$("#q_endTime").val()+'"}'
	 		
	 		$.messager.confirm('提示', '确定导出积分统计吗？', function(r){
                if (r){
                    $.fileDownload('${currentBaseUrl}/impordetaillist',
                    {data:eval('('+params+')')});
                }
            });
		});
		
	});
    
    function detailFormatter(index,row){
   	 	return '<div style="padding:2px"><table class="ddv"></table></div>';
	}

function onExpandRow(index,row){
    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
    var q_starTime = $("#q_starTime").val();
    var q_endTime = $("#q_endTime").val();
    ddv.datagrid({
       fitColumns:true,
       singleSelect:true,
       pageSize:'${pageSize}',
       pagination:true,
       method:'get',
       url:'${currentBaseUrl}/detailist?q_company='+row.company+'&q_dept='+row.dept+'&q_starTime='+q_starTime+'&q_endTime='+q_endTime,
		loadMsg : '数据加载中...',
		height : 'auto',
		columns : [[{
			field : 'name',
			title : '姓名',
			width : 50,
			align : 'center',
			halign : 'center'
		}, {
			field : 'mobile',
			title : '手机号',
			width : 50,
			align : 'center',
			halign : 'center'
		}, {
			field : 'ref_code',
			title : '订单号',
			width : 100,
			align : 'center',
			halign : 'center'
		}, {
			field : 'value',
			title : '消费积分',
			width : 50,
			align : 'center'
		}, {
			field : 'create_time',
			title : '消费时间',
			width : 80,
			align : 'center'
		}
		]],
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

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">积分商城管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p3 p-item">
						<label class="lab-item">公司名称:</label> 
						<input type="text" id="q_company" name="q_company" style="width:100px" >
				</p>
				<p class="p3 p-item">
						<label class="lab-item">部门名称:</label> 
						<input type="text" id="q_dept" name="q_dept" style="width:100px" >
				</p>
	            <p class="p3 p-item">
						<label class="lab-item">开始时间：</label> <input type="text"
							id="q_starTime" name="q_starTime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt" />
					</p>
				<p class="p3 p-item">
						<label class="lab-item">结束时间：</label> <input type="text"
							id="q_endTime" name="q_endTime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt" />
				</p>
			</div>
		</form>
		</div>
	</div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid" 
			data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,fitColumns:false
						,collapsible:true
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
			    <th field="company" width="220" align="center" halign="center">公司</th>
			    <th field="dept" width="220" align="center" halign="center">部门</th>
			    <th field="xfintegral" width="220" align="center" halign="center">消费积分</th>
			    <th field="pfsumintegral" width="220" align="center" halign="center">派发积分</th>
			    <th field="sumvalue" width="220" align="center" halign="center">剩余积分</th>
			    <th field="tkintegral" width="210" align="center" halign="center">退款积分</th>
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/promotion/unitintegral/print">
			<a id="a-export" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/unitintegral/exportDetail">
			<a id="a-exportDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出详情</a>
		</@shiro.hasPermission>
	</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />