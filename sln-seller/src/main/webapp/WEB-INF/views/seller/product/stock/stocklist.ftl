<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/stock/"/>

<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
    var codeBox;
    
    function loadSuccess(data){
    	dataGridLoadSuccess(data,this);
		$(".boxer").boxer();
	}
    
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        
        //商品库存编辑
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
			$("#devWin").window({
				width :"340",
				height : 220,
				href : '${currentBaseUrl}editstock?id='+selectedCode.id,
				title : "库存编辑",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			}).window('open');
					
        });

    })

    function imageFormat(value,row,index){
    	return "<a href='${domainUrlUtil.SLN_IMAGE_RESOURCES}" + 
			value + "' style='color:#276892' rel='gallery' class='boxer'>点击查看</a>";
    }

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
                    <li class="active">库存管理</li>
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
	                                   	<label class="lab-item">商品名称：</label>
                      					<input type="text" class="txt" id="q_productName" name="q_productName" value="${q_productName!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                   <label class="lab-item">SKU：</label>
	                                   <input type="text" class="txt" id="q_sku" name="q_sku" value="${q_sku!''}"/>
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
										,pagination:true
										,pageSize:'${pageSize}'
										,fit:true
				    					,url:'${currentBaseUrl}/list'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:loadSuccess
				    					,method:'get'">
				        <thead>
				        <tr>
				            <th field="productName" width="150" align="center">商品名称</th>
				            <th field="sku" width="100" align="center">SKU</th>
				            <th field="normName" width="150" align="center" >规格</th>
				            <th field="productStock" width="110" align="center" >货品库存</th>
				            <th field="productStockWarning" width="110" align="center">库存预警</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				        
						<@shiro.hasPermission name="/seller/product/stock/edit">
				        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">库存编辑</a>
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

<div id="devWin"></div>

<#include "/seller/commons/_listautoheight.ftl">
<#include "/seller/commons/_end.ftl">