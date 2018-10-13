<#include "/seller/commons/_head.ftl">

<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("ACT_STATUS","CHANNEL","ACT_SINGLE_TYPE")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/single/add";
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
            if(selected.status != 1 && selected.status != 4) {
            	$.messager.alert('提示','非新建或审核失败的活动不能修改。');
                return;
            }
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/single/edit?actSingleId="+selected.id;
		});
		$("#a-gridDetail").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/single/detail?actSingleId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status != 1 && selectedCode.status != 4) {
            	$.messager.alert('提示','非新建或审核失败的活动不能删除。');
                return;
            }

            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/single/delete",
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
		
		$("#a-gridAudit").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status != 1 && selected.status != 4) {
	        	$.messager.alert('提示','非新建或审核失败的活动不能提交审核。');
	            return;
	        }

	        $.messager.confirm('提示', '提价审核后活动不能再修改，请确认活动信息填写正确，确定提交审核该活动吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/single/audit",
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
		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status != 3) {
	        	$.messager.alert('提示','只能对审核通过的活动进行上架操作。');
	            return;
	        }

	        $.messager.confirm('提示', '如果同一个商品同时间有多个单品立减活动上架，则会使用最新的一个有效活动，确定上架该活动吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/single/up",
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
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status != 5) {
	        	$.messager.alert('提示','只能对上架状态的活动进行下架操作。');
	            return;
	        }

	        $.messager.confirm('提示', '活动下架后将不能再次执行上架操作，确定下架该活动？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/single/down",
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
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return codeBox["ACT_STATUS"][value];
	}

	function channelFormat(value,row,index){
		return codeBox["CHANNEL"][value];
	}

	function typeFormat(value,row,index){
		return codeBox["ACT_SINGLE_TYPE"][value];
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
                    <li class="active">单品立减</li>
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
	                                  <label class="lab-item">活动名称：</label>
	                                  <input type="text"  id="q_actSingleName" name="q_actSingleName" value="${q_actSingleName!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                 <label class="lab-item">状态：</label>
	                                 <@cont.select id="q_status" value="${(q_status)!''}" codeDiv="ACT_STATUS" style="width:80px" />
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                  <label class="lab-item">类型：</label>
	                                  <@cont.select id="q_type" value="${(q_type)!''}" codeDiv="ACT_SINGLE_TYPE" style="width:80px" />
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-lg-12 col-sm-12 col-xs-12">
                              <div class="row row-mgbot">
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                <label class="lab-item">渠道：</label>
	                                <@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" style="width:80px" />
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                 <label class="lab-item">日期：</label>
                 					 <input type="text" id="q_time"  name="q_time" 
                 					 	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})"/>
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
				    					,url:'${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/single/list'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:dataGridLoadSuccess
				    					,method:'get'">
						<thead>
							<tr>
								<th field="actSingleName" width="100" align="left" halign="center">活动名称</th>
								<th field="type" width="100" align="center" halign="center" formatter="typeFormat">活动类型</th>
								<th field="discount" width="100" align="right" halign="center">优惠额/折扣</th>
								<th field="startTime" width="150" align="center" halign="center">开始时间</th>
								<th field="endTime" width="150" align="center" halign="center">结束时间</th>
								<th field="channel" width="100" align="center" halign="center" formatter="channelFormat">应用渠道</th>
								<th field="status" width="100" align="center" halign="center" formatter="statusFormat">状态</th>
								<th field="remark" width="100" align="left" halign="center">活动描述</th>
								<th field="auditOpinion" width="100" align="left" halign="center">审核意见</th>
								<th field="updateUserName" width="100" align="center" halign="center">最后修改人</th>
								<th field="updateTime" width="150" align="center" halign="center">最后修改时间</th>
							</tr>
						</thead>
					</table>
					<div id="gridTools">
						<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
						<a id="a-gridDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-car_enter" plain="true">详情</a>
						
						<@shiro.hasPermission name="/seller/promotion/single/add">
						<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/promotion/single/edit">
						<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/promotion/single/delete">
						<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/promotion/single/audit">
						<a id="a-gridAudit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-accept" plain="true">提交审核</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/promotion/single/up">
						<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">上架</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/promotion/single/down">
						<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-freeze" plain="true">下架</a>
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