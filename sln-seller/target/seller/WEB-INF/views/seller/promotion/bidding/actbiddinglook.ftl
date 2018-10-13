<#include "/seller/commons/_head.ftl">

<link href="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/umeditor/lang/zh-cn/zh-cn.js"></script>

<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/seller/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
$(function(){
	initMenu('actbidding');
	$(".boxer").boxer();
	
	$("button[type='button'].back").click(function(){
 		window.location.href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actbidding";
	});
});
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
					<li><i class="fa fa-home"></i> <a
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/index.html">首页</a>
					</li>
					<li><a
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actbidding">集合竞价</a>
					</li>
					<li class="active">查看集合竞价</li>
				</ul>

				<!-- 头部按钮开始 -->
				<#include "/seller/commons/_headerbuttons.ftl">
				<!-- 头部按钮结束 -->

			</div>
			<!-- 主体头部结束 -->

			<!-- Page Body -->
			<div id="bodyhaiheyungu" style="overflow-y: auto; overflow-x: hidden;">
				<div class="col-lg-12 col-sm-12 col-xs-12">
					<div style="padding-top: 10px;">基本信息</div>
					<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />

					<form method="post" id="addform" class="form-horizontal"
						action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actbidding/update"
						enctype="multipart/form-data" data-bv-message="该项必填">
				
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>集合竞价分类：</label>
							<div class="col-lg-4">
								<input disabled="disabled"
									class="form-control" type="text" id="title"
									value="${(actBidding.typeName)!}"
									/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>商品：
							</label>
							<div class="col-lg-10">
								<label id="productName" >${(actBidding.productName)!}</label>
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>促销标题：</label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									class="form-control" type="text" id="title"
									name="name"
									value="${(actBidding.name)!}"
									/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>渠道：</label>
							<div class="col-lg-4">
								<@cont.select id="channel" name="channel" value="${(actBidding.channel)!1}" codeDiv="CHANNEL"
									class="form-control"
									disabled="disabled"
									mode="2"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动图片：</label> 
							<#if (actBidding.image)??> 
							<div class="col-lg-4">
								<a href="${domainUrlUtil.SLN_IMAGE_RESOURCES}${(actBidding.image)!''}" class='boxer'>查看图片</a>
							</div>
							</#if>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>市场价: </label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									type="text" id="marketPrice" name="marketPrice"
									value="${(actBidding.marketPrice)!''}"
									class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>初始价格: </label>
							<div class="col-lg-4">
								<input type="text" id="price" name="price"
									disabled="disabled"
									value="${(actBidding.price)!''}"
									class="form-control"
									/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>首付款项: </label>
							<div class="col-lg-4">
								<input type="text" id="firstPrice" name="firstPrice"
									value="${(actBidding.firstPrice)!''}"
									disabled="disabled"
									class="form-control"
									/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>限购数量：</label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									class="form-control" 
									id="purchase"
									name="purchase"
									value="${(actBidding.purchase)!''}" />
							</div>
						</div>
						
						<div style="padding-top: 10px;">阶梯价格</div>
						<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />
						
						<div class="form-group ejformstyle" id="activityBiddingPrice">
							<#if actBiddingPrices??>
		                    	<#list actBiddingPrices as actBiddingPrice>
									<div class="pricetr">
										<div class="col-lg-5 pull-left">
											<label class="col-sm-2 control-label">销量：</label>
											<div class="col-sm-8">
												<input id="saleNumberAll" name="saleNumberAll"
													disabled="disabled"
													value="${(actBiddingPrice.saleNum)!}" type="text">
											</div>
										</div>
										<div class="col-lg-5 pull-left">
											<label class="col-sm-2 control-label">价格：</label>
											<div class="col-sm-8">
												<input id="priceNmberAll" name="priceNmberAll"
													disabled="disabled"
													value="${(actBiddingPrice.price)!}" type="text">
											</div>
										</div>
									</div>
								</#list>
							</#if>
						</div>
						<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>库存：</label> 
							<div class="col-lg-4">
								<input
									class="form-control" id="stock" name="stock"
									value="${(actBidding.stock)!''}"
									disabled="disabled" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动时间：</label> 
							<div class="col-lg-4">
								<input
									type="text" id="startTime" name="startTime"
									class="form-control"
									disabled="disabled"
									value="${(actBidding.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
							<div class="col-lg-4">
								<input type="text" id="endTime"
									name="endTime" class="form-control"
									disabled="disabled"
									value="${(actBidding.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>首付款截止时间：</label>
							<div class="col-lg-4">
								<input type="text" id="firstEndTime" name="firstEndTime"
									class="form-control"
									disabled="disabled"
									value="${(actBidding.firstEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>							
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>尾款款截止时间：</label>
							<div class="col-lg-4">
								<input type="text" id="lastEndTime" name="lastEndTime"
									class="form-control"
									disabled="disabled"
									value="${(actBidding.lastEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red"></font>活动描述: </label>
							<div class="col-lg-8">
								<script type="text/plain" 
									id="myEditor"
									style="width: 100%; height: 240px;"><#noescape>${(actBidding.descinfo)!}</#noescape></script>
								<script type="text/javascript">
							    	var um = UM.getEditor('myEditor').setDisabled();
								</script>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-8 col-lg-offset-4">
								<button type="button" class="btn btn-danger btn-primary back">返回</button>
							</div>
						</div>
					</form>

				</div>
			</div>
			<!-- /Page Body -->
		</div>
		<!-- /Page Content -->
	</div>
	<!-- /Page Container -->
</div>


<div style="display: none">
	<div id="productDlg" class="easyui-dialog popBox" title="商品列表"
		style="width: 980px; height: 520px;"
		data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
		buttons="#dlg-buttons-brand">

		<div class="easyui-layout" data-options="fit:true">
			<table id="brandDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							singleSelect : true,
							fit:true,
							fitColumns:true,
							pagination:true,
							pageSize:'20',
							url:'${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/list?q_state=6&q_isWelfareProduct=1',
							method:'get'">
				<thead>
					<tr>
						<th field="name1" width="300" align="left">商品名称</th>
						<th field="productStock" width="150" align="center">商品库存</th>
						<th field="malMobilePrice" width="150" align="center">商品价格</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="dlg-buttons-brand" style="text-align: right">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="productSubmit()">确定</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#productDlg').dialog('close')">取消</a>
		</div>
	</div>
</div>

<#include "/seller/commons/_addcommonfooter.ftl"> 
<#include "/seller/commons/_end.ftl">