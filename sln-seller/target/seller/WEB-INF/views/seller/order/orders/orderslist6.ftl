<#include "/seller/order/orders/orderscommon.ftl">
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
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
                    <li class="active">已取消订单</li>
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
                                  <div class="col-lg-4 col-sm-4 col-xs-12">
	                                  <label class="lab-item">订单号：</label>
		                               <input type="text" id="q_orderSn" 
		                               	name="q_orderSn" value="${q_orderSn!''}" />
		                               <input type="hidden" id="q_orderState" name="q_orderState" value="6"/>
                                  </div>
                                  <div class="col-lg-6 col-sm-8 col-xs-12">
                                      <label class="lab-item">下单时间 :</label>
                                      <input type="text" id="q_startTime" name="q_startTime"
                                             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})" class="txt w180"/>
                                      -
                                      <input type="text" id="q_endTime" name="q_endTime"
                                             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59'})" class="txt w180"/>
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
								,view: detailview
								,autoRowHeight:false
								,fitColumns:false
								,toolbar:'#gridTools'
								,detailFormatter:detailFormatter
								,onExpandRow:onExpandRow
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
									<th field="orderSn" width="150" align="center" >订单号</th>
									<th field="memberName" width="120" align="center" >买家用户名</th>
									<th field="sellerName" width="120" align="center" >店铺</th>
									<th field="orderType" hidden="hidden"></th>
									<th field="moneyProduct" width="80" align="center" formatter="showMoneyProduct">商品金额</th>
									<th field="moneyOrder" width="80" align="center" formatter="showMoneyProduct">订单总金额</th>
									<th field="paymentStatus" width="70" align="center" formatter="paymentStatus">付款状态</th>
									<th field="orderState" width="80" align="center" formatter="getState">订单状态</th>
									<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
									<th field="invoiceTitle" width="100" align="center">发票抬头</th>
									<th field="invoiceType" width="70" align="center">发票类型</th>
									<th field="paymentName" width="70" align="center">支付方式</th>
									<th field="logisticsName" width="80" align="center">物流名称</th>
									<th field="logisticsNumber" width="100" align="center">快递单号</th>
									<th field="deliverTime" width="150" align="center">发货时间</th>
									<th field="createTime" width="150" align="center">下单时间</th>
									<th field="updateTime" width="150" align="center">修改时间</th>
								</tr>
							</thead>
						</table>
				
						<div id="gridTools">
							<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
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
