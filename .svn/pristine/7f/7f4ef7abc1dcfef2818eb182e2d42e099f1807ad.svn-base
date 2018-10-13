<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/settlement"/>
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>
	
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var codeBox;
	
	
	 function dataGridLoadSuccess(data){
    	if(data.rows.length==0){//无数据提示
    		var body1 = $(this).data().datagrid.dc.body1;
    		var body2 = $(this).data().datagrid.dc.body2;
    		body1.find('table').html('<tr class="datagrid-row"><td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber"></div></td></tr>');
    		body2.find('table').width('100%').find('tbody').append('<tr><td style="height: 25px; text-align: center;">没有数据</td></tr>');
    	}
    	$(".colorbox").boxer({
    		fixed:true
    	});
    }
    
    
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("SETTLEMENT_STATUS","SETTLE_OTHER_TYPE","INVOICDSTATUS")})');
		</#noescape>

        

		$("#btn-detail").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}	
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/settlement/detail?id="+selectedCode.id;
		});	
		
		<#--上传发票-->
		$("#btn-uploadInvoice").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}	
			if(selectedCode.invoiceStatus==1){
			$.messager.alert('提示','只有未上传的发票才可以上传。');
				return;
			}
				window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/admin/settlement/uploadInvoice?id="+selectedCode.id;
		});	
		
		
		<#--导出列表-->
		 $("#btn-importlist").click(function () {
            $.messager.confirm('提示', '确定导出商家统计结算吗？', function(r){
                if (r){
                    $.fileDownload('${currentBaseUrl}/importlist',{data:queryParamsHandler()});
                }
            });
        })
        
		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});

		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	});

	function settlementStatus(value, row, index) {
		var box = codeBox["SETTLEMENT_STATUS"][value];
		return box;
	}
	
	function invoiceStatus(value, row, index) {
		var box = codeBox["INVOICDSTATUS"][value];
		return box;
	}
	
	function otherType(value, row, index) {
		var box = codeBox["SETTLE_OTHER_TYPE"][value];
		return box;
	}
	
	
	<#--查看发票图片详情-->
	function Statusinfo(value, row, index) {
	if(row.invoiceStatus==1){
           return "<a href='${domainUrlUtil.SLN_IMAGE_RESOURCES}" + 
	  		value + "' style='color:#276892' rel='gallery' class='colorbox'>点击查看</a>";
	}
    }
</script>

<div id="devWin"></div>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		结算账单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<form class="form-search" action="doForm" method="post" id="queryForm"
			name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item">
					<label class="lab-item">结算周期:</label> <input type="text" class="txt"
						id="q_settleCycle" name="q_settleCycle" value="${q_settleCycle!''}" />
				</p>
				<p class="p4 p-item">
					<label class="lab-item">结算状态 :</label> 
					<@cont.select id="q_status" codeDiv="SETTLEMENT_STATUS" value="${q_status!''}" name="q_status" style="width:100px"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">发票状态 :</label> 
					<@cont.select id="q_invoiceStatus" codeDiv="INVOICDSTATUS" value="${q_invoiceStatus!''}" name="q_invoiceStatus" style="width:100px"/>
				</p>
				<p class="p4 p-item" >
					<label class="lab-item">结算主体 :</label> 
					<select id="q_subjectId" name="q_subjectId" class="drop" style="width:100px" panelheight="auto">
						<option value="0">-- 全部 --</option>
						<#if contributingList ??>
							<#list contributingList as teyue>
								<option  value="${teyue.id}">${teyue.sellerName}</option>
							</#list>
						</#if>
					</select>
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
				<th field="id" hidden="hidden"></th>
				<th field="settleCycle" width="60" align="center">结算周期</th>
				<th field="sellerName" width="120" align="center">店铺（商户）</th>
				<th field="sellerName" width="120" align="center">商户号</th>
				<th field="moneyOrder" width="60" align="center">应收金额</th>
				<th field="moneyPaidBalance" width="60" align="center">余额支付总额</th>
				<th field="moneyPaidReality" width="60" align="center">现金支付总额</th>
				<th field="moneyIntegral" width="60" align="center">积分转换总额</th>
				<th field="integral" width="60" align="center">积分总额</th>
				<th field="moneyBack" width="60" align="center">退款总额</th>
				<th field="moneyIntegralBack" width="80" align="center">退回积分总额</th>
				<th field="moneyOther" width="60" align="center">其他金额</th>
				<th field="moneyOtherType" width="60" align="center" formatter="otherType">其他金额类型</th>
				<th field="moneyOtherReason" width="60" align="center">其他金额理由</th>
				<th field="commision" width="60" align="center">佣金</th>
				<th field="payable" width="60" align="center">系统核算应付金额</th>
				<th field="status" width="70" align="center" formatter="settlementStatus">结算状态</th>
				<th field="subjectName" width="70" align="center" >结算主体</th>
				<th field="invoiceStatus" width="100" align="left" align="center" formatter="invoiceStatus">发票状态 </th>
				<th field="uploadImages" width="100" align="left" align="center" formatter="Statusinfo">查看发票详情</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/settlement/detail">
		<a id="btn-detail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">详情</a>
		</@shiro.hasPermission>
		
		<#--全部导出列表-->
		<@shiro.hasPermission name="/admin/settlement/importlist">
		<a id="btn-importlist" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出列表</a>
		</@shiro.hasPermission>
		
		<#--上传发票-->
		<@shiro.hasPermission name="/admin/settlement/uploadInvoice">
		<a id="btn-uploadInvoice" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true">上传发票</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
