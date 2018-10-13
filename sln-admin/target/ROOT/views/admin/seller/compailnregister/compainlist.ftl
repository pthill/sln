<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/compailnregister"/>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("COMPAIN_TYPE")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/goadd";
        });
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
             if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${currentBaseUrl}/goUpdate?id="+selectedCode.id;
        });

        $("#a-gridwark").click(function(){
           var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', '确定要删除该吗?一旦删除,此操作不可撤销', function(r){
		if (r){
           window.location.href="${currentBaseUrl}/delete?id="+selectedCode.id;
           }
		});
})
})
    //操作
    function handleFormat(value, row, index) {
        var html = "";
        html += "<a href='${currentBaseUrl}/registerinfo?id="+row.id+"'>查看详情</a>";
        return html;
    }
     function typeState(value,row,index){
         return codeBox["COMPAIN_TYPE"][value];
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">
		投诉登记<span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
				   <p class="p3 p-item">
						<label class="lab-item">投诉商家 :</label>
						<input type="text"class="txt" id="q_complainSeller"  name="q_complainSeller" style="width:100px"/>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">投诉类型 :</label>
						<@cont.select id="q_complainType" codeDiv="COMPAIN_TYPE" name="q_complainType" style="width:100px"/>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">投诉用户名称:</label> 
						<input type="text" id="q_complainPerson" name="q_complainPerson" style="width:100px" >
					</p>
					<p class="p3 p-item">
						<label class="lab-item">投诉时间 :</label> 
						<input type="text" class="txt" id="q_complainTime"  name="q_complainTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:100px"/>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>
<#--2.datagrid----------------->
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
    					,url:'${currentBaseUrl}/listli'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
	 <thead>
		<tr>
		        <th field="id" hidden="hidden"></th>
				<th field="complainSeller" width="100" align="left" halign="center">投诉商家</th>
				<th field="complainType" width="100" align="left" halign="center" formatter="typeState">投诉类型 </th>
				<th field="complainPerson" width="100" align="left" halign="center">投诉用户名称</th>
				<th field="complainTime" width="100" align="left" halign="center">投诉时间</th>
				<th field="desceinfo" width="100" align="left" halign="center">详情</th>
				<th field="createPerson" width="100" align="left" halign="center">创建人</th>
				<th field="handel" width="100" align="left" halign="center" formatter="handleFormat">操作</th>
		</tr>
    </thead>
    </table>
		</div>		

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        
    <#--请求到Controller相应的方法add---------------->
		<@shiro.hasPermission name="/admin/seller/compailnregister/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增投诉</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/seller/compailnregister/update">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/seller/compailnregister/delete">
        <a id="a-gridwark" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
  
      </div>

<#include "/admin/commons/_detailfooter.ftl" />