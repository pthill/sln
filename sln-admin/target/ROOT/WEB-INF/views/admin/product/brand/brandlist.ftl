<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/brand/"/>
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
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("NORM_STATE", "BRAND_TOP", "LOOK_METHOD")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id + "&type=0";
        });

        $("#a-gridRemove").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('确认', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/brand/del",
                        dataType: "json",
                        data: "id="+selectedCode.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    })

    function stateFormat(value,row,index){
        return codeBox["NORM_STATE"][value];
    }
    
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
    <h2 class="h2-title">品牌列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">品牌名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
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
            <th field="name" width="170" align="center">品牌名称</th>
            <th field="nameFirst" width="40" align="center">首字母</th>
            <th field="image" width="90" align="center" formatter="imageFormat">图片地址</th>
            <th field="top" width="100" align="center" formatter="useYnFormat">是否推荐</th>
            <th field="sort" width="110" align="center">排序</th>
            <th field="createUser" width="110" align="center">创建人</th>
            <th field="createTime" width="170" align="center">创建时间</th>
            <th field="updateUser" width="110" align="center">修改人</th>
            <th field="updateTime" width="170" align="center">修改时间</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/product/brand/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/brand/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/brand/del">
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />