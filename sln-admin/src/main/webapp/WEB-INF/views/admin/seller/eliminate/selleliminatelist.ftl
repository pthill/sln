<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/eliminate"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("BUSINESS_STATE")})');
    </#noescape>
        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
       
        $("#a-gridEdit").click(function(){
            window.location.href="${currentBaseUrl}/resource";
        });

    })

     function typeState(value,row,index){
          if(value==3){
               return '<font color="red">'+codeBox["BUSINESS_STATE"][value]+'</font>';
         }
         return codeBox["BUSINESS_STATE"][value];
    }
    
     function handleFormat(value, row, index) {
        var html = "";
    if(row.businessState==3){
        html += "已禁用商家";
        }
        return html;
    }
</script>
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">
		淘汰商家 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
				   <p class="p4 p-item">
						<label class="lab-item">商家 名称:</label>
						<input type="text" class="txt" id="q_sellerName" name="q_sellerName" style="width:100px" value="${q_sellerName!''}"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态 :</label> 
						<@cont.select id="q_businessState" codeDiv="BUSINESS_STATE" name="q_businessState" style="width:100px" value="${q_businessState!''}"/>
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
						,singleSelect:true
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
    					,method:'get'">
		<thead>
		<tr>
				<th field="sellerName" width="100" align="left" halign="center" >商家名称</th>
				<th field="sellerGrade" width="100" align="left" halign="center">商家等级</th>
				<th field="comparkMark" width="100" align="left" halign="center">综合评分</th>
				<th field="orderCountOver" width="100" align="left" halign="center">完成订单总量</th>
				<th field="compainCount" width="100" align="left" halign="center">投诉数量</th>
				<th field="businessState" width="100" align="left" halign="center"  formatter="typeState">状态</th>
				<th field="hander" width="120" align="center" formatter="handleFormat">备注</th>
		</tr>
    </thead>
    </table>
					
	<#--3.function button----------------->
    <div id="gridTools">
    <#--请求到Controller相应的方法add---------------->
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/seller/eliminate/resource">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置淘汰机制</a>
		</@shiro.hasPermission>
        </div>
<#include "/admin/commons/_detailfooter.ftl" />