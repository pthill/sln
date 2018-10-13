<div id="_membergradelog" class="easyui-dialog popBox" title="会员经验变更日志" style="width:800px;height:400px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div data-options="region:'center'" border="false" style="width:783px;height:358px;">
		<table id="membergradelogdataGrid" class="easyui-datagrid" 
				data-options="rownumbers:true
							,singleSelect:true
							,autoRowHeight:false
							,fitColumns:false
							,striped:true
							,pagination:true
							,pageSize:'${pageSize}'
							,fit:true
	    					,onLoadSuccess:dataGridLoadSuccess
	    					,method:'get'">
			<thead>
				<tr>
					<th field="memberName" width="150" align="left" halign="center">用户名</th>  
		            <th field="value" width="100" align="left" halign="center">经验值</th>  
		            <th field="optType" width="150" align="left" halign="center" formatter="optTypeFormat">具体动作</th>  
		            <th field="optDes" width="60" align="left" halign="center">操作描述</th>  
		            <th field="createTime" width="60" align="left" halign="center">变更时间</th>  
				</tr>
			</thead>
		</table>
	</div>
</div>
