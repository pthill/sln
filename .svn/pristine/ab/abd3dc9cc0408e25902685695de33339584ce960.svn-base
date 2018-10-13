<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier/"/>

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
	    <#noescape>
			codeBox = eval('(${initJSCodeContainer("SUPPLIER_TYPE")})');
			</#noescape>
        //为客户端装配本页面需要的字典数据,多个用逗号分隔

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
        
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id;
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
                        url: "${currentBaseUrl}del",
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

        $('#a-gridCommit').click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('确认', '确定提交审核吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}commit",
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
                            $('#dataGrid').datagrid('reload',queryParamsHandler());
                        }
                    });
                }
            });
        });
        
        		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择启用用户。');
	            return;
	        }
	        if(selected.state == 1) {
	        	$.messager.alert('提示','已经是启用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定启用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier/up",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                        	 $.messager.alert('提示', '该用户已启用');
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    
	    $("#a-gridDown").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择停用用户。');
	            return;
	        }
	        if(selected.state == 0) {
	        	$.messager.alert('提示','已经是停用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定停用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier/down",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                        	$.messager.alert('提示', '该用户已停用');
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
    })
	function formatSupplier(value,row,index){
		var box = codeBox["SUPPLIER_TYPE"][value];
		return box;
	
	}
	function formatState(value,row,index){
		if(value == 1){
			return "启用";
		}else{
			return "停用";
		}
	
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
                    <li class="active">供应商管理</li>
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
	                                   	<label class="lab-item">供应商编号：</label>
                      					<input type="text" class="txt" id="q_code" name="q_code" value="${q_code!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                   	<label class="lab-item">供应商名称：</label>
                      					<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
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
				            <th field="id" width="110" align="center">编号</th>
				            <th field="name" width="100" align="center">名称</th>
				            <th field="adress" width="150" align="center" >地址</th>
				            <th field="supplierType" width="110" align="center" formatter="formatSupplier" >类型</th>
				            <th field="contactsName" width="110" align="center">负责人</th>
				            <th field="contactsTel" width="170" align="center">联系人电话</th>
				            <th field="bankOfAccounts" width="110" align="center">开户银行</th>
				            <th field="benkAccount" width="170" align="center">银行账号</th>
				            <th field="alipay" width="110" align="center">支付宝</th>
				            <th field="weChat" width="110" align="center">微信</th>
				            <th field="state" width="110" align="center" formatter="formatState">状态</th>
				            <th field="createTime" width="170" align="center" >创建时间</th>
				        </tr>
				        </thead>
				    </table>
				
				<#--3.function button----------------->
				    <div id="gridTools">
				        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				        
						<@shiro.hasPermission name="/seller/system/supplier/add">
				        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
				        </@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/system/supplier/edit">
				        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
				        </@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/system/supplier/up">
						<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-accept" plain="true">启用</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/system/supplier/down">
						<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-fail" plain="true">停用</a>
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