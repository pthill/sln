<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/supplier/settlement"/>
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/easyui/datagrid-detailview.js"></script>

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

		 $("#btn-importlist").click(function () {
            $.messager.confirm('提示', '确定导出供应商结算列表吗？', function(r){
                if (r){
                    $.fileDownload('${currentBaseUrl}/importlist',{data:queryParamsHandler()});
                }
            });
        })

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
				window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/supplier/settlement/supplieruploadInvoice?id="+selectedCode.id;
		});	
		
		$("#btn-detail").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}	
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/supplier/settlement/detail?id="+selectedCode.id;
		});	
		
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
	
	function otherType(value, row, index) {
		var box = codeBox["SETTLE_OTHER_TYPE"][value];
		return box;
	}
	
	<#--发票状态-->
	function invoiceStatus(value, row, index) {
		var box = codeBox["INVOICDSTATUS"][value];
		return box;
	}
	
	function Statusinfo(value, row, index) {
	if(row.invoiceStatus==1){
           return "<a href='${domainUrlUtil.SLN_IMAGE_RESOURCES}" + 
	  		value + "' style='color:#276892' rel='gallery' class='colorbox'>点击查看</a>";
	}
    }
</script>

<div id="devWin"></div>
<div class="main-container container-fluid">
    <!-- Page Container -->
    <div class="page-container">
        <!-- 左侧菜单开始 -->
        <#include "/seller/commons/_left.ftl">
        <!-- 左侧菜单结束 -->
        <!-- Page Content -->
        <div class="page-content">
            <!-- 主体头部开始 -->
            <div class="page-breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/index.html">首页</a>
                    </li>
                    <li class="active">供应商结算</li>
                </ul>
                
                <!-- 头部按钮开始 -->
                <#include "/seller/commons/_headerbuttons.ftl">
                <!-- 头部按钮结束 -->
            </div>
            <!-- 主体头部结束 -->
            <!-- Page Body -->
            <div class="page-body">
              <div id="bodyhaiheyungu" class="easyui-layout ejava-easyui-layout" data-options="fit:true,split:false" style="height:300px; " >
                <div class="whtitdiv" data-options="region:'north'" style="padding-top: 10px;overflow-x:hidden;overflow-y:auto;">
                    <!-- <table id="part1">12341234</table> -->
                    <form class="from-ff">
                      <div class="row">
                          <div class="col-lg-12 col-sm-12 col-xs-12">
                              <div class="row row-mgbot">
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                  		<label class="lab-item">结算周期：</label> <input type="text"
											id="q_settleCycle" name="q_settleCycle" value="${q_settleCycle!''}" />
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                  		<label class="lab-item">结算状态：</label> <@cont.select id="q_status"
											codeDiv="SETTLEMENT_STATUS" value="${q_status!''}" name="q_status" />
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                    </form>
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
								<th field="sellerName" width="120" align="center">供应商</th>
								<th field="moneyOrder" width="60" align="center">销售总额</th>
								<!--<th field="moneyPaidBalance" width="60" align="center">余额支付总额</th>
								<th field="moneyPaidReality" width="60" align="center">现金支付总额</th>
								<th field="moneyIntegralBack" width="80" align="center">退会积分金额总额</th>
								<th field="commision" width="60" align="center">佣金总额</th>-->
								<th field="moneyBack" width="60" align="center">退款总额</th>
								<th field="payable" width="60" align="center">系统计算总额</th>
								<th field="moneyOther" width="60" align="center">其他金额</th>
								<th field="moneyOtherType" width="60" align="center" formatter="otherType">其他金额类型</th>
								<th field="moneyOtherReason" width="60" align="center">其他金额理由</th>
								<th field="commision" width="60" align="center">应付金额</th>
								<th field="status" width="70" align="center" formatter="settlementStatus">结算状态</th>
								<th field="invoiceStatus" width="100" align="left" align="center" formatter="invoiceStatus">发票状态 </th>
				                <th field="uploadImages" width="100" align="left" align="center" formatter="Statusinfo">查看发票详情</th>
							</tr>
						</thead>
					</table>
				
					<div id="gridTools">
						<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
						<@shiro.hasPermission name="/supplier/settlement/detail">
						<a id="btn-detail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">详情</a>
						</@shiro.hasPermission>
						
						<#--上传发票-->
						<@shiro.hasPermission name="/supplier/settlement/supplieruploadInvoice">
						<a id="btn-uploadInvoice" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true">上传发票</a>
						</@shiro.hasPermission>
						
						<#--供应商导出-->
						<@shiro.hasPermission name="/supplier/settlement/importlist">
						<a id="btn-importlist" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出列表</a>
						</@shiro.hasPermission>
					</div>
				</div>
              </div>
            </div>
            <!-- /Page Body -->
        </div>
        <!-- /Page Content -->
    </div>
    <!-- /Page Container -->
</div>

<#include "/seller/commons/_listautoheight.ftl">
<#include "/seller/commons/_end.ftl">