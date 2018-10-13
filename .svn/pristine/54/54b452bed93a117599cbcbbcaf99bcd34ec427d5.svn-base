<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/"/>

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
    })

</script>


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
	                                   	<input type="hidden" name="q_productGoodsId" id="q_productGoodsId" value="${productGoodsId}">
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
				    					,url:'${currentBaseUrl}/goodsStockRecordList'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:loadSuccess
				    					,method:'get'">
				        <thead>
				        <tr>
				            <!--<th field="productName" width="50" align="center">商品名称</th>-->
				            <th field="sku" width="100" align="center">SKU</th>
				            <th field="oldStock" width="90" align="center" >修改前库存</th>
				            <th field="newStock" width="90" align="center" >修改后库存</th>
				            <th field="oldStockWarning" width="90" align="center" >修改前预警库存</th>
				            <th field="newStockWarning" width="90" align="center" >修改后预警库存</th>
				            <th field="oldMallPcPrice" width="90" align="center" >修改前商城价</th>
				            <th field="newMallPcPrice" width="90" align="center" >修改后商城价</th>
				            <th field="oldMalMobilePrice" width="90" align="center" >修改前商城价Mobile</th>
				            <th field="newMalMobilePrice" width="90" align="center" >修改后商城价Mobile</th>
				            <th field="updateTime" width="100" align="center" >修改时间</th>
				            <th field="updateUserName" width="50" align="center" >修改人</th>
				        </tr>
				        </thead>
				    </table>
				
				</div>
              </div>
            </div>

