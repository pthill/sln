<#include "/seller/commons/_head.ftl">

<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>
<script language="javascript">
	var statusBox;
	function loadSuccess(data){
    	dataGridLoadSuccess(data,this);
		$(".boxer").boxer();
	}
	
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("USE_YN")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/mindex/banner/add";
		});
		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/mindex/banner/edit?mSellerIndexBannerId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status == 1) {
            	$.messager.alert('提示','使用中的轮播图不能删除');
                return;
            }

            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/banner/delete",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
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
		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择启用图片。');
	            return;
	        }
	        if(selected.status == 1) {
	        	$.messager.alert('提示','已经是启用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定启用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/banner/up",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
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
	            $.messager.alert('提示','请选择停用图片。');
	            return;
	        }
	        if(selected.status == 0) {
	        	$.messager.alert('提示','已经是停用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定停用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/banner/down",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
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
	    
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.SLN_H5_URL)!}/previewstore/${(SESSION_SELLER_USER.sellerId)!0}.html");
		});
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return statusBox["USE_YN"][value];
	}

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
                    <li class="active">首页轮播图</li>
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
	                       		        <label class="lab-item">标题：</label>
										<input type="text" class="txt" id="q_title" name="q_title" value="${q_title!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                                  		<label class="lab-item">状态：</label>
	                       		       <@cont.select id="q_status" value="${(q_status)!''}" codeDiv="USE_YN" style="width:80px" />
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
                               		  	 <label class="lab-item"> 日期：</label>
					                    <input type="text" id="q_time" name="q_time"
					                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})" class="txt w180"/>
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
										,fitColumns:false
										,collapsible:true
										,toolbar:'#gridTools'
										,striped:true
										,pagination:true
										,pageSize:'${pageSize}'
										,fit:true
				    					,url:'${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/mindex/banner/list'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:loadSuccess
				    					,method:'get'">
						<thead>
							<tr>
								<th field="title" width="100" align="left" halign="center">标题</th>
								<th field="linkUrl" width="150" align="left" halign="center">链接地址</th>
					            <th field="image" width="80" align="center" halign="center" formatter="imageFormat">查看图片</th>
					            <th field="orderNo" width="50" align="center" halign="center">排序号</th>
					            <th field="startTime" width="150" align="left" halign="center">开始时间</th>
					            <th field="endTime" width="150" align="left" halign="center">结束时间</th>
					            <th field="status" width="70" align="center" halign="center" formatter="statusFormat">使用状态</th>
					            <th field="updateUserName" width="100" align="center">最后修改人</th>
					            <th field="updateTime" width="150" align="center">最后修改时间</th>
							</tr>
						</thead>
					</table>
					<div id="gridTools">
						<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
						
						<@shiro.hasPermission name="/seller/mindex/banner/add">
						<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/mindex/banner/edit">
						<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/mindex/banner/delete">
						<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/mindex/banner/up">
						<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-accept" plain="true">启用</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/mindex/banner/down">
						<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-fail" plain="true">停用</a>
						</@shiro.hasPermission>
						
						<a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a>
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