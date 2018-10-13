<style>
#devForm {
	margin: 10px 20px;
}

#devForm table td {
	padding: 8px 0;
}

form input[type="button"] {
	margin: 0 100px;
}
</style>

<script>
	$(function() {
		$('#submitBtn').click(function() {
			if(!$("#waybillNumber").val()){
				$.messager.alert('提示','请填写物流单号');
				return;
			}
				$('#devForm').ajaxSubmit(function(data){
					if(data.result==1){
						$.messager.alert('提示','发货成功');
						$('#dataGrid').datagrid('reload');
						$("#devWin").window().close();
					}else{
						$.messager.alert('提示','异常，请稍后重试');
					}
				});
		});
	});
</script>

<form id="devForm"
	action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/orderdeliverycontroller/cofimdelivery"
	method="post">
	<input id="orderSn" type="hidden" name="orderSn" value="${orderSn!}" />
	<input id="id" type="hidden" name="id" value="${id!}" />
	<input id="logisticsName" type="hidden" name="logisticsName" value="${courierCompanylist[0].companyName!}" />
	<table cellpadding="20" cellspacing="10">
		<tr>
			<td><label>物流公司</label></td>
			<td><select class="form-control" id="logistics" name="logistics"
				onchange="$('#logisticsName').val(this.options[this.selectedIndex].text);">
					<#list courierCompanylist as cc>
						<option value="${cc.id!}">
							${cc.companyName!}
						</option> 
					</#list>
			</select></td>
		</tr>
		<tr>
			<td><label>运单号码</label></td>
			<td><input class="form-control" id="waybillNumber" name="waybillNumber" 
				/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="submitBtn" class="btn btn-primary btn-danger"
				value="确定发货" /></td>
		</tr>
	</table>
</form>