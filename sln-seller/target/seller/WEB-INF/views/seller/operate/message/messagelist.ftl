<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/message"/>

<style>
	#content{
		text-align:center;
		line-height: 20px; 
		padding:20px;
		font-size:14px;
		display:block
	}
</style>
<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.SLN_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("ISREAD")})');
	    </#noescape>
	    
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
	    });
		
		$('#todetail').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			//$("#title").text(selected.title);
			$("#content").text(selected.content);
			$("#detail").window({
				width :400,
				height : 220,
				title : "消息详情",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			}).window('open');
	    });
	    
	    $('#setRead').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			
			$.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/message/setRead",
                        dataType: "json",
                        data: "id="+selected.id,
                        cache:false,
                        success:function(data){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                        }
            });
	    });
	    
	    
		   
	});
	
	function isRead(value,row,index){
		return codeBox["ISREAD"][value];
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
                    <li class="active">商城公告</li>
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
	                                  	<label class="lab-item">标题：</label> <input type="text"
											class="txt" id="q_title" name="q_title"/>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                    </form>
                </div>
               	<div data-options="region:'center'" border="false">
					<table id="dataGrid" class="easyui-datagrid"
						data-options="rownumbers:true
										,idField :'id'
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
				    					,onLoadSuccess:dataGridLoadSuccess
				    					,method:'get'">
						<thead>
							<tr>
								<th field="title" width="70">标题</th>
								<th field="typeName" width="70">类型名称</th>
								<th field="content" width="300">内容</th>
								<th field="isRead" width="70" formatter="isRead">是否已读</th>
								<th field="createTime" width="70" align="center">创建时间</th>
							</tr>
						</thead>
					</table>
				
					<div id="gridTools">
						<a id="btn-search"
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" plain="true">查询</a>
						<@shiro.hasPermission name="/seller/systemNotice">
						<a id="todetail"
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-edit_task" plain="true">详情</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/seller/message/setRead">
						<a id="setRead"
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-edit" plain="true">设为已读</a>
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

<div id="detail">
	<span id="title"></span>
	<span id="content"></span>
</div>

<#include "/seller/commons/_listautoheight.ftl">
<#include "/seller/commons/_end.ftl">
