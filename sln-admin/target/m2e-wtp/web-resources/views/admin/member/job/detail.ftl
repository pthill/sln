<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/order/jobProductBack"/>
<script type="text/javascript">
    var codeBox;
    $(function() {

    <#noescape>
        codeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_RETURN","MEM_PB_STATE_MONEY")})');
    </#noescape>

    });

    function proBackState(value, row, index) {
        var box = codeBox["MEM_PB_STATE_RETURN"][value];
        return box;
    }

    function proMonState(value, row, index) {
        var box = codeBox["MEM_PB_STATE_MONEY"][value];
        return box;
    }
</script>
<div id="searchbar" data-options="region:'north'"
     style="margin: 0 auto;" border="false">
    <h2 class="h2-title">
        退款批次详情 <a class="a-back" href="${currentBaseUrl}">返回</a>
    </h2>
    <div id="searchbox" class="head-seachbox">
        <form class="form-search" action="doForm" method="post"
              id="queryForm" name="queryForm">
            <div class="fluidbox">
                <input type="hidden" id="q_pc" name="q_pc" value="${pc}">
            </div>
        </form>
    </div>
</div>
<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:false
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/detailPage'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'post'">
        <thead>
        <tr>
            <th field="pc" width="100" align="left">退款批次</th>
            <th field="productBackSn" width="150" align="left">退货申请单号</th>
            <th field="stateReturn" width="100" align="left" formatter="proBackState">退货状态</th>
            <th field="backMoney" width="100" align="center" >退款金额</th>
            <th field="stateMoney" width="100" align="center" formatter="proMonState">退款状态</th>
            <th field="createTime" width="200" align="left" >订单号</th>
            <th field="paySn" width="200" align="left">支付订单号</th>
        </tr>
        </thead>
    </table>
</div>
<#include "/admin/commons/_detailfooter.ftl" />