<style>
#editstock {
	margin: 10px 20px;
}

#editstock table td {
	padding: 8px 0;
}

form input[type="button"] {
	margin: 0 100px;
}
</style>

<script>
	$(function() {
		$('#submitBtn').click(function() {
			var productStock = $("#productStock").val();
			
			if(productStock <= 0){
				$.messager.alert('提示', '库存不能为0，请重新输入！');
			}else{
				$('#editstock').submit();
			}
			
		});
	});
</script>


<form id="editstock" action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/stock/update" method="post">
	<input type="hidden" name="id" value="${(productGoods.id)}">
	<input type="hidden" name="oldStock" value="${(productGoods.productStock)}">
	<input type="hidden" name="oldStockWarning" value="${(productGoods.productStockWarning)}">
	<table cellpadding="20" cellspacing="10">
		<tr>
			<td><label>当前库存</label></td>
			<td><input type="text" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" class="form-control" id="productStock" name="productStock" value="${(productGoods.productStock)!''}"/>
			</td>
		</tr>
		<tr>
			<td><label>库存预警</label></td>
			<td><input type="text" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" class="form-control" id="productStockWarning" name="productStockWarning" value="${(productGoods.productStockWarning)!''}"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="submitBtn" class="btn btn-primary btn-danger"
				value="确定修改" /></td>
		</tr>
	</table>
</form>