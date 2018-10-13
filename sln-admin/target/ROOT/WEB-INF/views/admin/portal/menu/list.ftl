<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/menu"/>
<#include "inclistjs.ftl"/>
<#include "inclistcss.ftl">
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">菜单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">菜单名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" />
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                    <@cont.select id="q_state" codeDiv="MENU_STATE" style="width:80px"/>
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
            <th field="name" width="100" align="left">名称</th>
            <th field="order" width="100" align="left">排序</th>
            <th field="abbreviation" width="100" align="left">简拼</th>
            <th field="code" width="100" align="center" >编号</th>
            <th field="url" width="300" align="center">URL</th>
            <th field="state" width="100" align="center" formatter="stateFormat">启用状态</th>
            <th field="isShow" width="100" align="center" formatter="isShowFormat">显示状态</th>
            <@shiro.hasRole name="admin">
            <th field="parkName" width="330" align="left">园区</th>
            <th field="handler" width="110" align="center" formatter="handleFormat">操作</th>
            </@shiro.hasRole>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <@shiro.hasPermission name="/admin/portal/menu/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/menu/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/menu/del">
        <a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
    </@shiro.hasPermission>
    <div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />