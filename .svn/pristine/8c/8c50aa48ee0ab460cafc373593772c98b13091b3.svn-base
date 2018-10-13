<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/supplierreturn/"/>

<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/easyui/datagrid-detailview.js"></script>

<script language="javascript">
	var codeBox;
		 $(function(){
		 	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		 	<#noescape>
				codeBox = eval('(${initJSCodeContainer("RETURN_STATE")})');
			</#noescape>
        
		 
			 $('#a-gridSearch').click(function(){
	            $('#dataGrid').datagrid('reload',queryParamsHandler());
	         });
	         
	         $("#a_comfirm").click(function(){
	         	var selected = $('#dataGrid').datagrid('getSelected');
				if (!selected) {
					$.messager.alert('提示', '请选择操作行。');
					return;
				}
				if(selected.returnState != 1){
					$.messager.alert('提示', '该退货单不是可收货状态！');
					return;
				}
				
				var msg = "您确认收货后，将由平台管理员退款给用户，请确认已收到退回的商品！";
			$.messager.confirm('确认', msg, function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type : "POST",
						url : "${currentBaseUrl}/cofimdelivery",
						data:{id:selected.id,orderSn:selected.orderSn,backId:selected.backId},
						success : function(data) {
							if(data.success){
								$.messager.alert('提示', '操作成功');
								$.messager.progress('close');
								$('#dataGrid').datagrid('reload');
							}else{
								$.messager.alert(data.message);
								return false;
							}
						}
					});
				}
			});
	         	
	         });
		 })
		 
		 function getReturnState(value,row,index){
		 	var box = codeBox["RETURN_STATE"][value];
			return box;
		 }
		 
		 function detailFormatter(index,row){
    		return '<div style="padding:2px"><table class="ddv"></table></div>';
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
                    <li class="active">退货单</li>
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
										,detailFormatter:detailFormatter
										,striped:true
										,pagination:true
										,pageSize:'${pageSize}'
										,fit:true
				    					,url:'${currentBaseUrl}/list'
				    					,queryParams:queryParamsHandler()
				    					,method:'get'">
				        <thead>
				        <tr>
				            <th field="id" width="100" hidden align="center">编号</th>
				            <th field="backId" width="100" hidden align="center">退货申请单id</th>
				            <th field="returnSn" width="100" align="center">退货单号</th>
				            <th field="orderSn" width="100" align="center" >订单号</th>
				            <th field="memberName" width="100" align="center">用户名</th>
				            <th field="productName" width="100" align="center">商品名称</th>
				            <th field="number" width="100" align="center">退货数量</th>
				            <th field="returnState" width="100" align="center" formatter="getReturnState">收货状态</th>
				            <th field="createTime" width="100"align="center">创建时间</th>
				            <th field="updateTime" width="100"align="center">收货时间</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				     <@shiro.hasPermission name="/seller/supplier/supplierreturn/confirm">
						<a id="a_comfirm" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">确认收货</a>
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