<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/supplier/settlement"/>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/jslib/js/jquery.form.js"></script>

<script type="text/javascript">
	$(function() {
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/supplier/settlement">供应商结算</a>
					</li>
					<li class="active">上传发票</li>
				</ul>

				<!-- 头部按钮开始 -->
				<#include "/seller/commons/_headerbuttons.ftl">
				<!-- 头部按钮结束 -->
			</div>
	<div id="bodyhaiheyungu" style="overflow-y: auto; overflow-x: hidden;">
		<#--1.addForm----------------->
		<div class="col-lg-12 col-sm-12 col-xs-12">
			<div style="padding-top: 10px;">基本信息</div>
		    <@form.form class="validForm" id="addForm" name="addForm" method="post" action="${currentBaseUrl}/doUploadImage" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(id)!''}">
					<div class="form-group">
						<div class="col-lg-8 col-lg-offset-3">
						      <input type="file" id="uploadfile" name="uploadfile" missingMessage="请选择要上传的凭证" class="form-control" required/>
						</div>
					</div>
				<div class="form-group">
		        <div class="col-lg-8 col-lg-offset-3">
				    <input type="submit"  class="btn btn-danger btn-primary" value="上传" /> 
				    <input type="button" id="back" class="btn btn-danger back btn-primary" value="返回" />
			    </div>
			    </div>
			</@form.form>
		</div>
	</div>
</div>
<#include "/seller/commons/_addcommonfooter.ftl">
 <#include "/seller/commons/_end.ftl">
