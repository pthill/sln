<#include "/seller/commons/_head.ftl">
<#include "/seller/supplier/orderdelivery/deliverCommon.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/orderdeliverycontroller/"/>

<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
		 $(function(){
			 $('#a-gridSearch').click(function(){
	            $('#dataGrid').datagrid('reload',queryParamsHandler());
	         });
	         
	         	
	   $('#btn-courier-company').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			window.open('${currentBaseUrl}/printcourier?orderSn='+selected.orderSn+'&logisticsId='+selected.logistics,"_self");
		});
		 })
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
                    <li class="active">已发货发货单</li>
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
	                                   	<label class="lab-item">发货单号：</label>
                      					<input type="text" class="txt" id="q_deliverySn" name="q_deliverySn" value="${q_deliverySn!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                   	<label class="lab-item">订单号：</label>
                      					<input type="text" class="txt" id="q_orderSn" name="q_orderSn" value="${q_orderSn!''}"/>
                                  </div>
                                  
                              </div>
                          </div>
                      </div>
                      
                    </form>
                </div>
               	<div data-options="region:'center'" border="false">
				    <table id="dataGrid" class="easyui-datagrid"
				           data-options="rownumbers:true
										,singleSelect:true
										,autoRowHeight:false
										,view: detailview
										,fitColumns:true
										,toolbar:'#gridTools'
										,detailFormatter:detailFormatter
										,onExpandRow:onExpandRow
										,striped:true
										,pagination:true
										,pageSize:'${pageSize}'
										,fit:true
				    					,url:'${currentBaseUrl}/list?q_state=2'
				    					,queryParams:queryParamsHandler()
				    					,method:'get'">
				        <thead>
				        <tr>
				            <th field="id" width="100" hidden align="center">编号</th>
				            <th field="logistics" width="100" hidden align="center"></th>
				            <th field="deliverySn" width="100" align="center">发货单号</th>
				            <th field="orderSn" width="100" align="center" >订单号</th>
				            <th field="createTime" width="100" align="center">创建时间</th>
				            <th field="supplierName" width="100"align="center">供应商</th>
				            <th field="updateTime" width="100" align="center">发货时间</th>
				            <th field="memberName" width="100" align="center">收货人姓名</th>
				            <th field="memberPhone" width="100" align="center">发货人联系</th>
				            <th field="receivingAddress" width="100" align="center">收货地址</th>
				            <th field="invoiceStatus" width="100" align="center"formatter="invoiceStatus">是否要发票</th>
				            <th field="invoiceTitle" width="100" align="center" >发票抬头</th>
				            <th field="logisticsName" width="100" align="center">物流公司</th>
				            <th field="waybillNumber" width="100" align="center">运单号</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				     	<a id="btn-courier-company" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印快递单</a>
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