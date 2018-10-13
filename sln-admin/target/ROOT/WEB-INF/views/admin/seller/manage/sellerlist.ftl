<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("YES_NO","SELLER_AUDIT_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$('#btn_edit').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$("#editWin").window({
				width : 600,
				height : 295,
				href : "${currentBaseUrl}/edit?id="+selected.id,
				title : "修改",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		// 冻结商家
		$('#btn_freeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定停用该商家吗？停用后，该商家的店铺及所有商品将被冻结', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/freeze",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		// 解冻商家
		$('#btn_unfreeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定解冻该商家吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/unfreeze",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		// 设置为自营店铺
		$('#btn_updateIsSelf').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '设为自营店铺后将不可以取消，是否将该店铺设为自营店铺？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/updateIsSelf",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		// 修改商家 结算主体
		$("#btn_updateSubjectId").click(function(){
			$("input[name='isContributing'][value=0]").attr('checked','true');
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#slt_subject").empty();
			$.ajax({
				type:"GET",
				async: false,
				url: "${currentBaseUrl}/getSubjectList",
				data: {sellerId:selected.id},
				cache:false,
				success:function(data, textStatus){
					if (data.success) {
						$.each(data.rows,function(i,value) {
							$("#slt_subject").append("<option value='"+value.id+"'>"+value.sellerName+"</option>");
							if(value.id == selected.id){
								if(value.isContributing == 1){
									$("input[name='isContributing'][value=1]").attr('checked','true');
								}
							}
						});
					} else {
						 $.messager.alert('提示',data.message);
						 return;
					}
				}
			});
			$('#editSubject').dialog('open');
		});
		
		// 修改结算主体 取消
		$("#editCancel").click(function(){
			$('#editSubject').dialog('close');
		});
		
		// 修改结算主体 确定
		$("#editPass").click(function(){
			//当前列表选中的对象
			var selected = $('#dataGrid').datagrid('getSelected');
			// option选中的值
			var subjectId = $('#slt_subject').find("option:selected").val();
			var isContributing = $('input[name="isContributing"]:checked').val();
			
				$.ajax({
					type:"GET",
					url: "${currentBaseUrl}/updateSubject",
					data: {id:selected.id,subjectId:subjectId,isContributing:isContributing},
					cache:false,
					success:function(data){
						if (data.success) {
							$('#dataGrid').datagrid('reload');
						} else {
							 $.messager.alert('提示',data.message);
							 return;
						}
					}
				});
			
			$('#editSubject').dialog('close');
		});
	});

	function getState(value, row, index) {
		var box = codeBox["SELLER_AUDIT_STATE"][value];
		return box;
	}
	
	function getIsSelf(value, row, index){
		var box = codeBox["YES_NO"][value];
		return box;
	}
	
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">
		商家列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">用户名 :</label> 
						<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">店铺名称 :</label> 
						<input type="text" class="txt" id="q_sellerName" name="q_sellerName" value="${q_sellerName!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">审核状态 :</label> 
						<@cont.select id="q_auditStatus" codeDiv="SELLER_AUDIT_STATE" name="q_auditStatus" style="width:100px"/>
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
				<th field="name" width="120" align="center">申请注册账号名</th>
				<th field="memberName" width="120" align="center">申请人账号</th>
				<th field="sellerName" width="150" align="center">申请店铺名</th>
				<th field="sellerGrade" width="55" align="center">店铺等级</th>
				<th field="scoreService" width="55" align="center">服务评分</th>
				<th field="scoreDeliverGoods" width="55" align="center">发货评分</th>
				<th field="scoreDescription" width="55" align="center">描述评分</th>
				<th field="productNumber" width="55" align="center">商品数量</th>
				<th field="collectionNumber" width="70" align="center">被收藏数量</th>
				<th field="saleMoney" width="85" align="center">店铺总销售金额</th>
				<th field="orderCount" width="50" align="center">总订单量</th>
				<th field="orderCountOver" width="50" align="center">完成订单量</th>
				<th field="subjectName" width="100" align="center">结算主体</th>
				<th field="isSelf" width="55" align="center" formatter="getIsSelf">是否自营</th>
				<th field="auditStatus" width="70" align="center" formatter="getState">状态</th>
				
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/seller/manage/freeze">
			<a id="btn_freeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">停用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage/unfreeze">
			<a id="btn_unfreeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage/updateIsSelf">
			<a id="btn_updateIsSelf" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设为平台自营店铺</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage/updateSubjectId">
			<a id="btn_updateSubjectId" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改结算主体</a>
		</@shiro.hasPermission>
	</div>
	
	<div class="wrapper" id="editWin"></div>
</div>

<div id="editSubject" class="easyui-dialog popBox" title="结算主体" style="width:350px;height:180px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div class="fluidbox1">
		<p class="p10 p-item" style="margin-top:15px">	
			<label class="lab-item"><font class="red"></font>结算主体: </label>
			<input type="hidden" id="subjectId" value="0">
			<select id="slt_subject">
				<option  value="0">0</option>
			</select>
		</p>
	</div>
	<div class="fluidbox1">
		<p class="p10 p-item" style="margin-top:15px;margin-bottom:15px">	
			<label class="lab-item"><font class="red"></font>特约商户: </label>
			否 <input type="radio" name="isContributing" value="0" checked >
			是 <input type="radio" name="isContributing" value="1" >
		</p>
	</div>
	<#--2.batch button-------------->
	<p class="p-item p-btn">
		<input type="button" id="editPass" class="btn" value="确定"/>
		<input type="button" id="editCancel" class="btn" value="取消"/>
	</p>
</div>

<#include "/admin/commons/_detailfooter.ftl" />