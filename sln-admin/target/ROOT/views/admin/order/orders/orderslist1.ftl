<#include "/admin/order/orders/orderscommon.ftl"/>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		订单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<form class="form-search" action="doForm" method="post" id="queryForm"
			name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item">
					<label class="lab-item">订单号:</label> <input type="text" class="txt"
						id="q_orderSn" name="q_orderSn" value="${q_orderSn!''}" />
				</p>
				<p class="p8 p-item">
					<label class="lab-item">下单时间 :</label>
					<input id="q_startTime" name="q_startTime" style="width:160px;" value="${q_startTime!''}" type="text" class="Wdate {required:true}" onFocus="WdatePicker({readOnly:true,startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_endTime\')}'})" data-options="required:true"/>
					~
					<input id="q_endTime" name="q_endTime" style="width:160px;" value="${q_endTime!''}" type="text" class="Wdate {required:true}" onFocus="WdatePicker({readOnly:true,startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_startTime\') || \'%y-%M-{%d+1} 00:00:00\'}'})" data-options="required:true"/>
				</p>
				<input type="hidden" id="q_orderState" name="q_orderState" value="1"/>
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
				<th field="orderSn" width="150" align="left" halign="center">订单号</th>
				<th field="memberName" width="120" align="left">买家用户名</th>
				<th field="sellerName" width="120" align="left">店铺</th>
				<th field="moneyProduct" width="80" align="center">商品金额</th>
				<th field="moneyOrder" width="80" align="center">订单总金额</th>
				<th field="paymentStatus" width="70" align="center" formatter="paymentStatus">付款状态</th>
				<th field="orderState" width="80" align="center" formatter="getState">订单状态</th>
				<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
				<th field="invoiceTitle" width="100" align="left">发票抬头</th>
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
		<a id="btn-print" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="btn-delivery" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">先发货</a>
		<a id="btn-delete" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">取消订单</a>
		<a id="btn-editMoney" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改价格</a>
	</div>
</div>

<div id="_orderAmountOpt" class="easyui-dialog popBox" title="订单金额变更" style="width:400px;height:120px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div class="fluidbox">
		<p class="p12 p-item" style="margin-top:10px">	
			<label class="lab-item"><font class="red">*</font>变更余额: </label>
			<input class="easyui-numberbox" id="amount" name="amount"  data-options="min:0">
		</p>
	</div>
	<#--2.batch button-------------->
	<p class="p-item p-btn">
		<input type="button" id="amountOptOk" class="btn" value="确定"/>
		<input type="button" id="amountOptCancel" class="btn" value="取消"/>
	</p>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
