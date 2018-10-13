<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/jobProductBack"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("BACK_MONEY_STATUE")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
    });
    function pcFormat(value,row,index){
        var html="";
        html += "<a href='${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/jobProductBack/detail?pc="+row.pc+"' style='color: blue' >"+row.pc+"</a>";
        return html;
    }

    function statusFormat(value,row,index){
        return codeBox["BACK_MONEY_STATUE"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">退款批次列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">退款批次:</label>
                        <input type="text" class="txt" id="q_pc" name="q_pc" />
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">退款状态 :</label>
                    <@cont.select id="q_status" codeDiv="BACK_MONEY_STATUE" style="width:100px"/>
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
            <th field="pc" width="150" align="left" formatter="pcFormat">批次</th>
            <th field="countProductBack" width="100" align="left">退款订单数</th>
            <th field="countMoney" width="100" align="center" >退款金额</th>
            <th field="status" width="100" align="center" formatter="statusFormat">退款状态</th>
            <th field="createTime" width="200" align="left">生成时间</th>
            <th field="finishTime" width="200" align="left">完成时间</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <div>
   </div>

<#include "/admin/commons/_detailfooter.ftl" />