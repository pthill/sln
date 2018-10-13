<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/cate/"/>
<#assign refJdCatId="${(cate.jdCatId)!''}"/>
<script language="javascript">
    var codeBox;
    var fileview = $.extend({}, $.fn.datagrid.defaults.view, { onAfterRender: function (target) { ischeckItem(); } });
    var checkedItems = [];
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    	<#noescape>
        	codeBox = eval('(${initJSCodeContainer("NORM_TYPE","CATE_TYPE")})');
        	<#if pcjList??>
		    	<#list pcjList as vo>
		    		checkedItems.push('${vo.jdCategoryId}');
		    	</#list>
		    </#if>
    	</#noescape>
    	
    	var backUrl = "${currentBaseUrl}";
    	$("#back").click(function () {
            window.location.href = backUrl;
        });
        
        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
    	
		//上级分类
        $("#firstCate").live("change", function(){
            var parentId = $(this).val();
        	$("#secondCate option:not(:first)").remove();
            $.ajax({
                type:"get",
                url: "${currentBaseUrl}getJdCateByPid?id=" + parentId,
                dataType: "json",
                cache:false,
                success:function(data){
                    if (data.length > 0) {
                        $.each(data, function(i, n){
                            $("#secondCate").append("<option value=" + n.catId + ">" + n.name + "</option>");
                        })
                    }
                }
            });
        });
        
        
        $("#a-gridAddRelJd").click(function(){
        	addcheckItem();
            if(checkedItems.length==0){
                $.messager.alert('提示','请选择一行或多行数据。');
                return;
            }
            $.messager.confirm('确认', '确定关联京东分类吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}addProductCateJd",
                        data: "productCateId=${cateId}&jdCatIds="+checkedItems.join(',')+"&"+getCSRFTokenParam(),
                        dataType: "json",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                            	alert("关联成功！");
                                window.location.reload();
                            }else{
                                $.messager.alert('提示', "关联失败！");
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        
        
        $("#a-gridAddJdCate").click(function(){
        	var ids = [];
			var rows = $('#dataGrid').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示','请至少选择一行数据。');
				return;
			}
			for(var i=0; i<rows.length; i++){
				ids.push(rows[i].catId);
			}
            
            $.messager.confirm('确认', '确定添加吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}addJdCate",
                        data: "jdCatIds="+ids.join(',')+"&"+getCSRFTokenParam(),
                        dataType: "json",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $.messager.alert('提示', "添加京东分类成功！");
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
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
        if(value == 1 || value == 'open'){
        	return "有效";
        }else{
        	return "无效";
        }
    }
    
    function firstCateFormat(value,row,index){
    	if(row.parent && row.parent.parent){
    		return row.parent.parent.name;
    	}
    	return "";
    }
    
    function secondCateFormat(value,row,index){
    	if(row.parent){
    		return row.parent.name;
    	}
    	return "";
    }
    
    function afterDataGridLoaded(){
    }
    function dataGridLoadFail(){
        alert("服务器异常");
    }
    
    function loadSuccess(data) {
		if (checkedItems.length > 0) {  
             for (var i = 0; i < checkedItems.length; i++) {
            	var catId = checkedItems[i];
                if (data.rows[i].catId == catId) {
                 	var index = $("#dataGrid").datagrid("getRowIndex",data.rows[i]);
                 	$("#dataGrid").datagrid("checkRow",index);
                }  
            }  
        }  
	}
	
	function clickRow(rowIndex, field, value){
		var flag = $("input[type='checkbox']")[rowIndex + 1].disabled; 
		if (flag) {
          $("#dataGrid").datagrid('uncheckRow', rowIndex);
        }
        addcheckItem();
	}
	
	function ischeckItem() {
		var rows = $("#dataGrid").datagrid("getRows"); 
		for(var i=0;i<rows.length;i++){
			for (var j = 0; j < checkedItems.length; j++) {
            	var catId = checkedItems[j];
                if (rows[i].catId == catId) {
                 	var index = $("#dataGrid").datagrid("getRowIndex",rows[i]);
                 	$("#dataGrid").datagrid("checkRow",index);
                }  
            }  
		}
		
        for (var i = 0; i < checkedItems.length; i++) {
            $('#dataGrid').datagrid('selectRecord', checkedItems[i]); //根据id选中行 
        }
	}
	
	function findCheckedItem(ID) {  
        for (var i = 0; i < checkedItems.length; i++) {  
            if (checkedItems[i] == ID) return i;  
        }  
        return -1;  
    }  
  
 	function addcheckItem() {
        var row = $('#dataGrid').datagrid('getChecked');  
        for (var i = 0; i < row.length; i++) {  
            if (findCheckedItem(row[i].catId) == -1) {  
                checkedItems.push(row[i].catId);  
            }  
        }  
    }  
    function removeAllItem(rows) {  
        for (var i = 0; i < rows.length; i++) {  
            var k = findCheckedItem(rows[i].catId);  
            if (k != -1) {  
                checkedItems.splice(i, 1);  
            }  
        }  
    }  
    function removeSingleItem(rowIndex, rowData) {  
        var k = findCheckedItem(rowData.catId);  
        if (k != -1) {  
            checkedItems.splice(k, 1);  
        }  
     }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">	
    		<#if cate??>
    			平台分类：${(cate.name)!''} --
    		</#if>
    		 京东商品分类列表 
    	<span class="s-poar">
    	<a class="a-back" href="${currentBaseUrl}">返回</a>
    	</span>
    </h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p6 p-item">
                        <label class="lab-item">一级分类 :</label>
                        <select id="firstCate" name="q_firstCate" level="0" class="w110">
                            <option value="">全部</option>
                             <#if pJdCateList?? && pJdCateList?size&gt;0>
                                <#list pJdCateList as jdCate>
                                    <option value="${jdCate.catId}" <#if firstCate?? && firstCate?string == jdCate.catId?string>selected</#if>>${jdCate.name}</option>
                                </#list>
                            </#if>
                        </select>
                        <label class="lab-item">二级分类 :</label>
                        <select id="secondCate" name="q_secondCate" level="1" class="w110">
                            <option value="">全部</option>
                            <#if sJdCateList?? && sJdCateList?size&gt;0>
                                <#list sJdCateList as jdCate>
                                    <option value="${jdCate.catId}" <#if secondCate?? && secondCate?string == jdCate.catId?string>selected</#if>>${jdCate.name}</option>
                                </#list>
                            </#if>
                        </select>
                        <label class="lab-item">状态:</label>
                        <select id="state" name="q_state" level="1" class="w110">
                            <option value="">全部</option>
                            <option value="1">有效</option>
                            <option value="0">无效</option>
                        </select>
                        <input type="hidden" id="q_cateId" name="q_cateId" value="${cateId}"/>
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
						,singleSelect:false
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/getJdCateList'
    					,view: fileview
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:loadSuccess
    					,onClickRow:clickRow
    					,onUncheck:removeSingleItem
    					,method:'get'">
        <thead>
        <tr>
        	<th field="id" checkbox="true"></th>
            <th field="name" width="150" align="left">名称</th>
            <th field="firstName" width="60" align="left" formatter="firstCateFormat">一级分类</th>
            <th field="secondName" width="50" align="center" formatter="secondCateFormat">二级分类</th>
            <th field="state" width="50" align="center" formatter="stateFormat">状态</th>
        </tr>
        </thead>
    </table>

    <#--3.function button----------------->
    <div id="gridTools">
    	<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <#if cate??>
	        <@shiro.hasPermission name="/admin/product/cate/addProductCateJd">
	        	<a id="a-gridAddRelJd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">确认关联</a>
	        </@shiro.hasPermission>
        <#else>
        	<@shiro.hasPermission name="/admin/product/cate/addJdCate">
	        	<a id="a-gridAddJdCate" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">确认添加</a>
	        </@shiro.hasPermission>
	    </#if>
    </div>
</div>

<#--4.batch button-------------->
<div data-options="region:'south'" style="height:50px;">
	<p class="p-item p-btn">
    <input type="button" id="back" class="btn" value="返回"/>
	</p>
</div>
<#include "/admin/commons/_detailfooter.ftl" />