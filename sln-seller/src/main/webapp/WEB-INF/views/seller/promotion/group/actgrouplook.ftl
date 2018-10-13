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
	initMenu('actgroup');
	$(".boxer").boxer();
	
	$("button[type='button'].back").click(function(){
 		window.location.href= domain+"/seller/promotion/actgroup";
	});
        
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actgroup">团购管理</a>
					</li>
					<li class="active">团购查看</li>
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
						action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actgroup/update"
						enctype="multipart/form-data" data-bv-message="该项必填">
					 	<input type="hidden" id="id" name="id" value="${(actGroup.id)!}" />
						<input type="hidden" id="productId" name="productId" value="${(actGroup.productId)!}"/>
						<input type="hidden" id="descinfo" name="descinfo" value="${(actGroup.descinfo)!}"/>
				
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>团购分类：</label>
							<div class="col-lg-4">
								<select name="type" class="form-control" disabled="disabled">
									<#if actGroupTypes ??>
										<#list actGroupTypes as actGroupType>
								  			<option value = "${actGroupType.id}" <#if actGroupType.id == actGroup.type >selected</#if> >${actGroupType.name}</option>
								  		</#list>
								  	</#if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>选择商品：
							</label>
							<div class="col-lg-10">
								<label id="productName">${(actGroup.productName)!}</label>
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-10 ejava-errinforight">商品必须是上架状态</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>促销标题：</label> 
							<div class="col-lg-4">
								<input disabled="disabled"
									class="form-control" type="text" id="title"
									name="name"
									value="${(actGroup.name)!}"
									required />
							</div>
							<label class="col-lg-6 ejava-errinforight">
								促销标题必须填写，输入2到200个字符
							</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>渠道：</label>
							<div class="col-lg-4">
								<@cont.select id="channel" name="channel" value="${(actGroup.channel)!1}" codeDiv="CHANNEL"
									disabled="disabled"
									class="form-control" mode="2"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动图片：</label> 
							<#if (actGroup.image)??> 
							<div class="col-lg-4">
								<a href="${domainUrlUtil.SLN_IMAGE_RESOURCES}${(actGroup.image)!''}" class='boxer'>查看图片</a>
							</div>
							</#if>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>市场价: </label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									type="text" id="marketPrice" name="marketPrice"
									value="${(actGroup.marketPrice)!''}"
									class="form-control"
									required />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>团购价: </label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									type="text" id="price" name="price"
									value="${(actGroup.price)!''}" class="form-control"
									required/>
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
									value="${(actGroup.purchase)!''}"
									required />
							</div>
							<label class="col-lg-6 ejava-errinforight">
								限购数量，没人每次下单最多可以购买多少商品，最少为1
							</label>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>库存：</label> 
							<div class="col-lg-4">
								<input
									disabled="disabled"
									class="form-control" id="stock" name="stock"
									value="${(actGroup.stock)!''}"
									required />
							</div>
							<label class="col-lg-6 ejava-errinforight">
								库存表示可以卖多少产品
							</label>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动时间：</label> 
							<div class="col-lg-4">
								<input
									type="text" id="startTime" name="startTime"
									class="form-control" 
									disabled="disabled"
									value="${(actGroup.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" > 
							</div>
							<div class="col-lg-4">
								<input type="text" id="endTime"
									name="endTime" class="form-control"
									disabled="disabled"
									value="${(actGroup.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" >
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-10 ejava-errinforight">
								只有在当前时间内才能看到此团购商品
							</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red"></font>活动描述: </label>
							<div class="col-lg-8">
								<script type="text/plain" id="myEditor"
									readonly=true
									style="width: 100%; 
									height: 240px;"><#noescape>${(actGroup.descinfo)!}</#noescape></script>
								<script type="text/javascript">
							    	UM.getEditor('myEditor').setDisabled();
								</script>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-8 col-lg-offset-4">
								<button type="button" class="btn btn-danger back btn-primary">返回</button>
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

<#include "/seller/commons/_addcommonfooter.ftl"> 
<#include "/seller/commons/_end.ftl">