<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/active"/>
<#include "inclistjs.ftl"/>
<#include "inclistcss.ftl">
<div id="searchbar" data-options="region:'north'"
     style="margin: 0 auto;" border="false">
    <h2 class="h2-title">
        活动列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
    </h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post"
                  id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">活动标题:</label>
                        <input type="text" class="txt" id="q_title" name="q_title" />
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                       <@cont.select id="q_state" codeDiv="ACTIVITY_STATE"  name="q_state" style="width:100px"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">园区:</label>
                        <select name="q_parkId" id="q_parkId">
                            <option value="">请选择园区名称</option>
                        <#if park??>
                            <#list park as item>
                                <option value="${item.id}">${item.parkName}</option>
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
            <th field="title" width="100" align="left" >活动标题</th>
            <th field="order" width="100" align="left" >排序</th>
            <th field="parkName" width="100" align="left" >园区</th>
            <th field="address" width="100" align="left" >地址</th>
            <th field="state" width="100" align="left"  formatter="stateFormat">状态</th>
            <th field="startTime" width="100" align="left" >开始时间</th>
            <th field="endTime" width="100" align="left" >结束时间</th>
            <th field="handler" width="100" align="center" formatter="handleFormat">操作</th>
        </tr>
        </thead>
    </table>
    <div id="gridTools">
        <a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <@shiro.hasPermission name="/admin/portal/active/add">
        <a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/active/edit">
        <a id="btn_update" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/portal/active/del">
        <a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </@shiro.hasPermission>
    </div>
</div>
<div id="newstypeWin">
    <form id="newstypeForm" method="post">
        <ul id="newstypeTree"
            style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
    </form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
