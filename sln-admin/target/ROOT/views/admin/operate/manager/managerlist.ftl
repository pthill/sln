<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operation/manager"/>
<script language="javascript">
    <#if message??>
    $.messager.alert('提示','${(message)!}');
    </#if>
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("MANAGER_STATUS","OPERATION_NAME")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#a-gridEdit").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status==1){
                $.messager.alert('提示','启用状态下不可编辑。');
            }else{
                window.location.href="${currentBaseUrl}/edit?id="+selected.id;
            }
        });

        $("#a-gridOn").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择业务管理方。');
                return;
            }
            if(selected.status == 1) {
                $.messager.alert('提示','已经是启用状态，请勿重复操作');
                return;
            }
            $.messager.confirm('提示', '确定启用吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}/on",
                        dataType: "json",
                        data: "id="+selected.id+"&status=1",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.data) {
                                $.messager.alert('提示', data.message);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        $("#a-gridOff").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status == 0) {
                $.messager.alert('提示','业务管理方已经停用，请勿重复操作。');
                return;
            }
            $.messager.confirm('提示', '确定要停用吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}/off",
                        dataType: "json",
                        data: "id="+selected.id+"&status=0",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.data) {
                                $.messager.alert('提示', data.message);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    })

    function statusFormat(value,row,index){
        return codeBox["MANAGER_STATUS"][value];
    }
    function nameFormat(value,row,index){
        return codeBox["OPERATION_NAME"][value];
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">业务管理方列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">管理方编码 :</label>
                        <input type="text" class="txt" id="q_code" name="q_code" />
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">管理方名称 :</label>
                    <@cont.select id="q_name" codeDiv="OPERATION_NAME" style="width:80px"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_status" codeDiv="MANAGER_STATUS" style="width:80px"/>
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
    					,method:'get'">
        <thead>
        <tr>
            <th field="code" width="60" align="left">编码</th>
            <th field="name" width="100" align="left" formatter="nameFormat">管理方名称</th>
            <th field="parkName" width="100" align="left">园区</th>
            <th field="status" width="100" align="center" formatter="statusFormat">状态</th>
            <th field="address" width="300" align="center">地址</th>
            <th field="description" width="300" align="left">介绍</th>
            <th field="company" width="300" align="left">公司</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <@shiro.hasPermission name="/admin/operation/manager/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/operation/manager/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/operation/manager/off">
        <a id="a-gridOff" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">禁用</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/operation/manager/on">
        <a id="a-gridOn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
    </@shiro.hasPermission>
    <div>
    </div>

<#include "/admin/commons/_detailfooter.ftl" />