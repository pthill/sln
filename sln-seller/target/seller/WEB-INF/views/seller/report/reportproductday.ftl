<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/productday"/>
<script language="javascript">
    $(function(){
    
     $("#btn-importlist").click(function () {
            $.messager.confirm('提示', '确定导出每日商品统计结算列表吗？', function(r){
                if (r){
                    $.fileDownload('${currentBaseUrl}/importlist',{data:queryParamsHandler()});
                }
            });
        })
        
        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
    })

</script>

<div class="main-container container-fluid">
    <!-- Page Container -->
    <div class="page-container">
        <!-- 左侧菜单开始 -->
        <#include "/seller/commons/_left.ftl">
        <!-- 左侧菜单结束 -->
        <!-- Page Content -->
        <div class="page-content">
            <!-- 主体头部开始 -->
            <div class="page-breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/index.html">首页</a>
                    </li>
                    <li class="active">每日商品统计</li>
                </ul>
                
                <!-- 头部按钮开始 -->
                <#include "/seller/commons/_headerbuttons.ftl">
                <!-- 头部按钮结束 -->
            </div>
            <!-- 主体头部结束 -->
            <!-- Page Body -->
            <div class="page-body">
              <div id="bodyhaiheyungu" class="easyui-layout ejava-easyui-layout" data-options="fit:true,split:false" style="height:300px; " >
                <div class="whtitdiv" data-options="region:'north'" style="padding-top: 10px;overflow-x:hidden;overflow-y:auto;">
                    <!-- <table id="part1">12341234</table> -->
                    <form class="from-ff">
                      <div class="row">
                          <div class="col-lg-12 col-sm-12 col-xs-12">
                              <div class="row row-mgbot">
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                  		<label class="lab-item">查询时间：</label>
				                        <input type="text" id="q_startTime" name="q_startTime"
				                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})" class="txt w180"/>
                                		<span class="space-dot">~</span>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                  		<input type="text" id="q_endTime" name="q_endTime"
                              				 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59'})" class="txt w180"/>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                    </form>
                </div>
               	<div data-options="region:'center'" border="false">
					 <table id="dataGrid" class="easyui-datagrid"
				           data-options="rownumbers:true
										,singleSelect:true
										,autoRowHeight:false
										,fitColumns:true
										,toolbar:'#gridTools'
										,striped:true
										,fit:true
										,showFooter:true
				    					,url:'${currentBaseUrl}/list'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:dataGridLoadSuccess
				    					,method:'get'">
				        <thead>
				        <tr>
				            <th field="orderDay" width="100" align="center">日期</th>
				            <th field="productName" width="200" align="center">商品名称</th>
				            <th field="moneyAmount" width="100" align="center">金额</th>
				            <th field="number" width="50" align="center">数量</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				    
				    <#--每日商品统计列表导出-->
						<@shiro.hasPermission name="/seller/report/productday">
						<a id="btn-importlist" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出列表</a>
						</@shiro.hasPermission>
				    </div>
				</div>
              </div>
            </div>
            <!-- /Page Body -->
        </div>
        <!-- /Page Content -->
    </div>
    <!-- /Page Container -->
</div>

<#include "/seller/commons/_listautoheight.ftl">
<#include "/seller/commons/_end.ftl">