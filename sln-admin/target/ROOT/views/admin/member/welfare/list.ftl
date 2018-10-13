<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/member/welfareSend"/>
<script language="javascript">
	var codeBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
	<#noescape>
        codeBox = eval('(${initJSCodeContainer("SEND_STATUS")})');
	</#noescape>
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
        $("#a-gridDown").click(function () {
            $.messager.confirm('提示', '确定导出模板吗？', function(r){
                window.location.href='${currentBaseUrl}/downModel';
            });
        });
        $("#a-gridAdd").click(function () {
            window.location.href = '${currentBaseUrl}/toAdd';
        });
		$("#a-gridEdit").click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sendStatus=='1'){
                $.messager.alert('提示','已发送的不能编辑。');
                return;
            }
            window.location.href = '${currentBaseUrl}/toAdd?id='+selected.id;
        });
		$("#a-gridSend").click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sendStatus=='1'){
                $.messager.alert('提示','该积分已发送，请不要重复发送');
                return;
            }
            $.messager.confirm('确认', "确认要发送吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "发送中..."
                    });
                    $.ajax({
                        url:'${currentBaseUrl}/send?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });

                }
            });

        })
		$("#a-gridDetail").click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href = '${currentBaseUrl}/detail?id='+selected.id;
        })
        $("#a-gridDel").click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sendStatus=='1'){
                $.messager.alert('提示','已发送的不能删除。');
                return;
			}
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selected);
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${currentBaseUrl}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });

                }
            });
        });

	});

	function StateFormat(value,row,index){
		return codeBox["SEND_STATUS"][value];
	}

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">字典列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p3 p-item">
					<label class="lab-item">公司名称 :</label>
					<input type="text" class="txt" id="q_company" name="q_company" value="${q_company!''}"/>
				</p>
				<p class="p3 p-item">
					<label class="lab-item">部门名称:</label>
					<input type="text" class="txt" id="q_dept" name="q_dept" value="${q_email!''}"/>
				</p>
				<p class="p3 p-item">
					<label class="lab-item">发送状态 :</label>
					<@cont.select id="q_status" name="q_status" codeDiv="SEND_STATUS" style="width:80px"/>
				</p>
                <p class="p3 p-item">
                    <label class="lab-item">积分类型 :</label>
                    <@cont.select id="q_sellerId" name="q_sellerId" codeDiv="INTEGRAL_TYPE" style="width:80px"/>
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
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'post'">
		<thead>
			<tr>
				<th field="company" width="150" align="left" halign="center">公司名称</th>
	            <th field="dept" width="100" align="left" halign="center">部门名称</th>
	            <th field="costName" width="100" align="left" halign="center">费用名称</th>
                <th field="sellerName" width="100" align="left" halign="center">店铺名称</th>
	            <th field="countPerson" width="100" align="left" halign="center">总人数</th>
	            <th field="name" width="150" align="left" halign="center">创建人</th>
	            <th field="createTime" width="150" align="left" halign="center">创建时间</th>
                <th field="sendTime" width="150" align="left" halign="center">发送时间</th>
                <th field="sendStatus" width="150" align="left" halign="center" formatter="StateFormat">发送状态</th>
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<#--<@shiro.hasPermission name="/admin/member/welfareSend/downModel">
		<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">模板下载</a>
		</@shiro.hasPermission>-->
		<@shiro.hasPermission name="/admin/member/welfareSend/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">导入积分发放清单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/welfareSend/add">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/welfareSend/detail">
		<a id="a-gridDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看详情</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/welfareSend/send">
		<a id="a-gridSend" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">发送</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/welfareSend/del">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>
