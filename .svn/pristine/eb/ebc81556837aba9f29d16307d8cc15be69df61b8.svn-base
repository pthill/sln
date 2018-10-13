<#include "/admin/commons/_detailheader.ftl" />
<#include "inclistjs.ftl"/>
<#include "incaddjs.ftl"/>
<div id="tt" class="easyui-tabs" data-options="fit:true">
    <div title="轮播banner" id="banner">
        <div class="head-seachbox" style="background: #e4ebf1">
            <div class="w-p99 marauto searchCont">
                <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item">名称 :</label>
                            <input type="text" class="txt" id="q_bannername" name="q_bannername" />
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item">状态 :</label>
                        <@cont.select id="q_bannerState" codeDiv="MENU_STATE"  name="q_bannerState"  style="width:100px"/>
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item">类型 :</label>
                        <@cont.select id="q_bannertype" codeDiv="BANNER_TYPE"  name="q_bannertype" style="width:100px"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
        <table id="bannerGrid"></table>
        <div id="bannerTools">
            <a id="a-bannerSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
            <@shiro.hasPermission name="/admin/portal/index/add">
                <a id="a-bannerAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/admin/portal/index/edit">
                <a id="a-bannerEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/admin/portal/index/del">
                <a id="a-bannerDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
            </@shiro.hasPermission>
        </div>
    </div>
    <div title="快速入口"  id="quick">
        <div class="head-seachbox" style="background: #e4ebf1">
            <div class="w-p99 marauto searchCont">
                <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
            <div class="fluidbox">
                <p class="p4 p-item">
                    <label class="lab-item">名称 :</label>
                    <input type="text" class="txt" id="q_enterName" name="q_enterName" />
                </p>
                <p class="p4 p-item">
                    <label class="lab-item">状态 :</label>
                <@cont.select id="q_enterState" codeDiv="MENU_STATE"  name="q_enterState" style="width:100px"/>
                </p>
            </div>
        </form>
            </div>
        </div>
        <table id="enterGrid"></table>
        <div id="enterTools">
            <a id="a-enterSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/portal/enter/add">
            <a id="a-enterAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/enter/edit">
            <a id="a-enterEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/enter/del">
            <a id="a-enterDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        </div>
    </div>
    <div title="推荐服务"  id="service">
        <div class="head-seachbox" style="background: #e4ebf1">
            <div class="w-p99 marauto searchCont">
                <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item">服务项 :</label>
                            <select name="q_pid" id="q_pid" >
                                <option value="">请选择服务项</option>
                            <#if list??>
                                <#list list as item>
                                    <option value="${item.id}">${item.serviceName}</option>
                                </#list>
                            </#if>
                            </select>
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item">服务类 :</label>
                            <select name="q_id" id="q_id" >
                                <option value="">请选择服务类</option>
                            <#if services??>
                                <#list services as item>
                                    <option value="${item.id}">${item.serviceName}</option>
                                </#list>
                            </#if>
                            </select>
                        </p>
                    </div>
                </form>
            </div>
        </div>
         <table id="serviceGrid"></table>
         <div id="serviceTools">
            <a id="a-serviceSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/portal/service/add">
            <a id="a-serviceAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/service/edit">
            <a id="a-serviceEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/service/del">
            <a id="a-serviceDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        </div>
    </div>
    <div title="电商-爆品"  id="shop">
        <div class="head-seachbox" style="background: #e4ebf1">
            <div class="w-p99 marauto searchCont">
                <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item">名称 :</label>
                            <input type="text" class="txt" id="q_shopName" name="q_shopName" />
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item">状态 :</label>
                        <@cont.select id="q_shopState" codeDiv="ACTIVITY_STATE"  name="q_shopState" style="width:100px"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
        <table id="shopGrid"></table>
        <div id="shopTools">
            <a id="a-shopSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/portal/shopActive/add">
            <a id="a-shopAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/shopActive/edit">
            <a id="a-shopEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/shopActive/del">
            <a id="a-shopDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        </div>
    </div>
    <div title="电商-满减" id="active">
        <div class="head-seachbox" style="background: #e4ebf1">
            <div class="w-p99 marauto searchCont">
                <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item">名称 :</label>
                            <input type="text" class="txt" id="q_activeName" name="q_activeName" />
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item">状态 :</label>
                        <@cont.select id="q_activeState" codeDiv="ACTIVITY_STATE"  name="q_activeState" style="width:100px"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
        <table id="activeGrid"></table>
        <div id="activeTools">
            <a id="a-activeSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/portal/shopActive/add">
            <a id="a-activeAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/shopActive/edit">
            <a id="a-activeEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/portal/shopActive/del">
            <a id="a-activeDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />