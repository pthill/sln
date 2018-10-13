<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/stock/"/>
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
    var codeBox;
    
    function loadSuccess(data){
    	if(data.rows.length==0){//无数据提示
    		var body1 = $(this).data().datagrid.dc.body1;
    		var body2 = $(this).data().datagrid.dc.body2;
    		body1.find('table').html('<tr class="datagrid-row"><td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber"></div></td></tr>');
    		body2.find('table').width('100%').find('tbody').append('<tr><td style="height: 25px; text-align: center;">没有数据</td></tr>');
    	}
    	
    	$(".colorbox").boxer({
    		fixed:true
    	});
    }
    
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });

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

    
    function lookFormat(value,row,index){
        return codeBox["LOOK_METHOD"][value];
    }
    
    function imageFormat(value,row,index){
        return "<a href='${domainUrlUtil.SLN_IMAGE_RESOURCES}" + 
        	value + "' style='color:#276892' rel='gallery' class='colorbox'>点击查看</a>";
    }
    
    function useYnFormat(value,row,index){
        return codeBox["BRAND_TOP"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">库存管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">商品名称 :</label>
                        <input type="text" class="txt" id="q_productName" name="q_productName" value="${q_productName!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">SKU:</label>
                        <input type="text" class="txt" id="q_sku" name="q_sku" value="${q_sku!''}"/>
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
            <th field="sellerName" width="150" align="center">商家名称</th>
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
		<@shiro.hasPermission name="/admin/product/stock/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">库存编辑</a>
		</@shiro.hasPermission>

    </div>
</div>
<div id="devWin"></div>
<#include "/admin/commons/_detailfooter.ftl" />