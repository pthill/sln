<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/service"/>
<#include "inclistjs.ftl"/>
<#include "inclistcss.ftl">
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">服务列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">服务名称 :</label>
                        <input type="text" class="txt" id="q_serviceName" name="q_serviceName" />
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                    <@cont.select id="q_state" codeDiv="MENU_STATE" style="width:80px"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">类型 :</label>
                    <@cont.select id="q_type" codeDiv="SERVICE_TYPE" style="width:80px"/>
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
            <th field="serviceName" width="130" align="center" >名称</th>
            <th field="order" width="100" align="center" >排序</th>
            <th field="abbreviation" width="100" align="center" >简称</th>
            <th field="code" width="100" align="center" >编号</th>
            <th field="type" width="100" align="center" formatter="typeFormat">类型</th>
            <th field="belong" width="100" align="center" >所属</th>
            <th field="menuName" width="100" align="center">所属菜单</th>
            <@shiro.hasRole name="admin">
            <th field="parkName" width="200" align="left">园区</th>
            </@shiro.hasRole>
            <th field="state" width="60" align="center" formatter="stateFormat">启用状态</th>
            <th field="isShow" width="60" align="center" formatter="isShowFormat">显示状态</th>
            <th field="handler" width="100" align="center" formatter="handleFormat">操作</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <@shiro.hasPermission name="/admin/portal/service/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/service/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/service/del">
        <a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </@shiro.hasPermission>
    <div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />