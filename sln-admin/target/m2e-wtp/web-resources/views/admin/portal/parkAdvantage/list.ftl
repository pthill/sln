<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/parkAdvantage"/>
<#include "inclistjs.ftl"/>
<#include "inclistcss.ftl" />
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">园区优势列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">活动标题:</label>
                        <input type="text" class="txt" id="q_title" name="q_title" />
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
    					,method:'post'">
        <thead>
        <tr>
            <th field="id" width="100" align="left" hidden="true" ></th>
            <th field="title" width="100" align="left">活动标题</th>
            <th field="order" width="100" align="left">排序</th>
            <th field="createTime" width="100" align="left">创建时间</th>
            <th field="parkName" width="100" align="left">园区</th>
            <th field="handler" width="110" align="center" formatter="handleFormat">操作</th>
        </tr>
        </thead>
    </table>
</div>
<#--3.function button----------------->
<div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <@shiro.hasPermission name="/admin/portal/parkAdvantage/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/parkAdvantage/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/parkAdvantage/del">
        <a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </@shiro.hasPermission>
<div>
<#include "/admin/commons/_detailfooter.ftl" />