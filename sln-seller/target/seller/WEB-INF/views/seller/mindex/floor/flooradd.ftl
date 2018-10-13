<#include "/seller/commons/_head.ftl">

<script language="javascript">

$(function(){
	initMenu('floor');
	$("button[type='button'].back").click(function(){
 		window.location.href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/floor";
	});
	
	$('#addform').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/floor">移动端首页楼层</a>
					</li>
					<li class="active">添加楼层</li>
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
						action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/floor/create"
						enctype="multipart/form-data" data-bv-message="该项必填">
							<input type="hidden" id="id" name="id" value="${(mSellerIndexFloor.id)!''}">
							<input type="hidden" id="status" name="status" value="${(mSellerIndexFloor.status)!''}">
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>名称：</label> 
							<div class="col-lg-4">
								<input
									class="form-control" type="text" id="name"
									name="name" value="${(mSellerIndexFloor.name)!''}"
									 data-bv-stringlength-min="2"
                                     data-bv-stringlength-max="200"
                                     required
                                     data-bv-stringlength-message="名称2-20位长度"  />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>排序号：</label> 
							<div class="col-lg-4">
								<input
									class="form-control" id="orderNo" name="orderNo"
									value="${(mSellerIndexFloor.orderNo)!''}"
									required
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的数字"
									pattern="^\d{1,6}$"
                             	  	data-bv-regexp-message="请输入正确的排序号" />
							</div>
							<label class="col-lg-6 ejava-errinforight">
								 序号越小越靠前显示
							</label>
						</div>


						<div class="form-group">
							<label class="col-lg-2 control-label">备注：</label> 
							<div class="col-lg-4">
								<textarea name="remark" id="remark"
									class="form-control">${(mSellerIndexFloor.remark)!''}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-6 col-lg-offset-3">
								<button type="submit" class="btn btn-danger btn-primary">提交</button>
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

<#include "/seller/commons/_addcommonfooter.ftl"> <#include
"/seller/commons/_end.ftl">