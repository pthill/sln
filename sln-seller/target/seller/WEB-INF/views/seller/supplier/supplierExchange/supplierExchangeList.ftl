<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/supplierexchange"/>
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
	var codeBox;
		 $(function(){
		 	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		 	<#noescape>
				codeBox = eval('(${initJSCodeContainer("EXCHANGE_STATE")})');
			</#noescape>
        
		 
			 $('#a-gridSearch').click(function(){
	            $('#dataGrid').datagrid('reload',queryParamsHandler());
	         });
	         //确认收货按钮
	         $('#a_receipt').click(function(){
	            var selected = $('#dataGrid').datagrid('getSelected');
				if (!selected) {
					$.messager.alert('提示', '请选择操作行。');
					return;
				}
				if(selected.exchangeState != 1){
					$.messager.alert('提示', '请选择状态为未收货的退货单');
					return;
				}
				
				operationExchange(selected.id,2,selected.exchangeId)
	         });
	         
	         //发货按钮
	         $('#a_deliver').click(function(){
	            var selected = $('#dataGrid').datagrid('getSelected');
				if (!selected) {
					$.messager.alert('提示', '请选择操作行。');
					return;
				}
				if(selected.exchangeState != 2){
					$.messager.alert('提示', '请选择状态为已收货的退货单');
					return;
				}
				
				operationExchange(selected.id,3,selected.exchangeId)
	         });
	         
	         //不予处理按钮
	         $('#a_cancel').click(function(){
	            var selected = $('#dataGrid').datagrid('getSelected');
				if (!selected) {
					$.messager.alert('提示', '请选择操作行。');
					return;
				}
				if(selected.exchangeState != 1){
					$.messager.alert('提示', '请选择状态为未收货的退货单');
					return;
				}
				
				operationExchange(selected.id,4,selected.exchangeId)
	         });
		 })
		 
		 function getExchangeState(value,row,index){
		 	var box = codeBox["EXCHANGE_STATE"][value];
			return box;
		 }
		 
		 //公共操作换货单方法
		 function operationExchange(id,state,exchangeId){
			 $.messager.confirm('确认', '确认此操作，确认后不可后退', function(r) {
					if (r) {
							$.messager.progress({
								text : "提交中..."
							});
							$.ajax({
								type : "POST",
								url : "${currentBaseUrl}/updateState",
								data:{id:id,exchangeId:exchangeId,exchangeState:state},
								success : function(data) {
									if(data.success){
										$.messager.alert('提示', '操作成功');
										$('#dataGrid').datagrid('reload');
									}else{
										$.messager.alert(data.message);
										return false;
									}
								}
							});
							
								$.messager.progress('close');
						}
			 });
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
                    <li class="active">换货单</li>
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
	                                   	<label class="lab-item">订单号：</label>
                      					<input type="text" class="txt" id="q_orderSn" name="q_orderSn" value="${q_orderSn!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                      <label class="lab-item">退货状态：</label> 
                                     <@cont.select id="q_returnState"
										codeDiv="RETURN_STATE" value="${q_returnState!''}" 
										name="q_returnState"/>
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
										,fitColumns:true
										,toolbar:'#gridTools'
										,striped:true
										,pagination:true
										,pageSize:'${pageSize}'
										,fit:true
				    					,url:'${currentBaseUrl}/list'
				    					,queryParams:queryParamsHandler()
				    					,method:'get'">
				        <thead>
				        <tr>
				            <th field="id" width="100" hidden align="left" halign="center"></th>
				            <th field="exchangeId" width="100" hidden align="left" halign="center"></th>
							<th field="exchangeSn" width="100" align="left" halign="center">换货单号</th>
							<th field="orderSn" width="100" align="left" halign="center">订单编号</th>
							<th field="productName" width="100" align="left" halign="center">商品名称</th>
							<th field="memberName" width="100" align="left" halign="center">用户名</th>
							<th field="remark" width="100" align="left" halign="center">问题描述</th>
							<th field="exchangeNumber" width="100" align="left" halign="center">换货数量</th>
							<th field="exchangeState" width="100" align="left" halign="center" formatter="getExchangeState">换货状态</th>
							<th field="createTime" width="100" align="left" halign="center">创建时间</th>
							<th field="receiptTime" width="100" align="left" halign="center">收货时间</th>
							<th field="deliverTime" width="100" align="left" halign="center">发货时间</th>
							<th field="returnTime" width="100" align="left" halign="center">退还时间</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				     <@shiro.hasPermission name="/seller/supplier/supplierexchange/receipt">
						<a id="a_receipt" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">确认收货</a>
					 </@shiro.hasPermission>
					 <@shiro.hasPermission name="/seller/supplier/supplierexchange/deliver">
						<a id="a_deliver" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">发货</a>
					 </@shiro.hasPermission>
					 <@shiro.hasPermission name="/seller/supplier/supplierexchange/cancel">
						<a id="a_cancel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">不予处理原件退还</a>
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