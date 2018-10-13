<#include "/seller/commons/_head.ftl">

<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("FLASH_SALE_STATUS","CHANNEL")})');
		</#noescape>

		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridApply").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/flash/apply?actFlashSaleId="+selected.id;
		});
		
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.SLN_FRONT_URL)!}/previewindex.html");
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return codeBox["FLASH_SALE_STATUS"][value];
	}

	function channelFormat(value,row,index){
		return codeBox["CHANNEL"][value];
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
                    <li class="active">限时抢购</li>
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
	                                 <input type="text" id="q_actFlashSaleName" name="q_actFlashSaleName" value="${q_actFlashSaleName!''}"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                 <label class="lab-item">日期：</label>
                    				<input type="text" id="q_actDate" name="q_actDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                  </div>
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                 <label class="lab-item">渠道：</label>
	                                 <@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" />
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-lg-12 col-sm-12 col-xs-12">
                              <div class="row row-mgbot">
                                  <div class="col-lg-4 col-sm-6 col-xs-12">
	                                 <label class="lab-item">状态：</label>
	                                 <@cont.select id="q_status" value="${(q_status)!''}" codeDiv="FLASH_SALE_STATUS" />
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
				    					,url:'${(domainUrlUtil.SLN_URL_RESOURCES)!}/seller/promotion/flash/list'
				    					,queryParams:queryParamsHandler()
				    					,onLoadSuccess:dataGridLoadSuccess
				    					,method:'get'">
						<thead>
							<tr>
								<th field="actFlashSaleName" width="150" align="left" halign="center">活动名称</th>
								<th field="actDate" width="150" align="center" halign="center">活动日期</th>
								<th field="channel" width="80" align="center" halign="center" formatter="channelFormat">应用渠道</th>
								<th field="status" width="80" align="center" halign="center" formatter="statusFormat">活动状态</th>
								<th field="auditRule" width="150" align="left" halign="center">申请规则</th>
								<th field="remark" width="150" align="left" halign="center">活动描述</th>
								<th field="auditOpinion" width="150" align="left" halign="center">作废原因</th>
					            <th field="updateUserName" width="150" align="center">最后修改人</th>
					            <th field="updateTime" width="150" align="center">最后修改时间</th>
							</tr>
						</thead>
					</table>
					<div id="gridTools">
						<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
						<@shiro.hasPermission name="/seller/promotion/flash/apply">
						<a id="a-gridApply" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">申请活动商品</a>
						</@shiro.hasPermission>
						<!-- <a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a> -->
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