<#include "/seller/commons/_head.ftl">
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/productBack"/>

<script language="javascript">
	$(function() {
		initMenu('productBack');
		
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

		$("#agree").click(function() {
			$.messager.confirm('确认', '确定同意该买家的退货申请吗？', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type : "GET",
						url : "${currentBaseUrl}/audit?type=2&id=${obj.id}&remark=" + $("#remarkStr").val()+"&orderId=${obj.orderId}",
						success : function(data) {
							if(data.data>0){
								$.messager.progress('close');
								location.href="${currentBaseUrl}";
							}else{
								$.messager.alert("提示",data.message);
								$.messager.progress('close');
								return false;
							}
							
						}
					});
				}
			});
		});

		$("#disagree").click(function() {
			var remarkStr = $("#remarkStr").val();
			if (remarkStr = null || remarkStr == "") {
				$.messager.alert("提示", "请描述不予换货的原因。");
				return;
			}
			$.messager.confirm('确认', '确定对该买家的退货申请不予处理吗？', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type : "GET",
						url : "${currentBaseUrl}/audit?type=4&id=${obj.id}&remark=" + $("#remarkStr").val()+"&orderId=${obj.orderId}",
						success : function(data) {
							if(data.success){
								$.messager.progress('close');
								location.href="${currentBaseUrl}";
							}else{
								$.messager.alert("提示",data.message);
								$.messager.progress('close');
								return false;
							}
						}
					});
				}
			});
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
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
					<li><i class="fa fa-home"></i> <a
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/index.html">首页</a>
					</li>
					<li><a
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/productBack">退货管理</a>
					</li>
					<li class="active">处理退货申请</li>
				</ul>

				<!-- 头部按钮开始 -->
				<#include "/seller/commons/_headerbuttons.ftl">
				<!-- 头部按钮结束 -->

			</div>
			<!-- 主体头部结束 -->

			<!-- Page Body -->
			<div id="bodyhaiheyungu" style="overflow-y: auto; overflow-x: hidden;">
				<div class="alert alert-warning">
					<a href="#" class="close" data-dismiss="alert">
				        &times;
				    </a>
				    <div class="warning">
				    	<img src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/seller/images/warning.jpg" />
						<label>如果您同意买家的退货申请,将进入退货流程</label>
				    </div>
					<div class="warning" style="margin-bottom: 0px;">
						<img src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/seller/images/warning.jpg" />
						<label>如果您不同意该退货申请,买家可选择向平台投诉</label>
					</div>
				</div>
				
				<div class="col-lg-12 col-sm-12 col-xs-12">
					<div style="padding-top: 10px;">基本信息</div>
					<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />

					<form method="post" id="addform" class="form-horizontal"
						action="${domainUrlUtil.SLN_URL_RESOURCES}/seller/pcindex/banner/create"
						enctype="multipart/form-data">
						
						<div class="form-group">
							<label class="col-lg-2 control-label">订单号：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.orderSn!}</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">商品名称：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.productName!}</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">用户名：</label>
							<div class="col-lg-8">
								<span class="info-span" >${obj.memberName!}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">退货人手机：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.phone!}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">联系人姓名：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.returnName!}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">问题描述：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.question!}</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">退货数量：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.number!}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">退款金额：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.backMoney!}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">退回积分：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${obj.backIntegral!}</span>
							</div>
						</div>

						<#if couponUser?? >
						<div class="form-group">
							<label class="col-lg-2 control-label">退回优惠券：</label> 
							<div class="col-lg-8">
								<span class="info-span" >${couponUser.couponSn!}</span>
							</div>
						</div>
						</#if>

						<div class="form-group">
							<label class="col-lg-2 control-label">备注：</label> 
							<div class="col-lg-8">
								<textarea name="remarkStr" id="remarkStr" 
								class="form-control">${(obj.remark)!''}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-12 textcenter">
								<!-- 退货状态：1、未处理；2、审核通过待收货；3、已经收货；4、不予处理 -->
								<#if obj.stateReturn == 1>
									<button type="button" id="agree" class="btn btn-danger btn-full-screen btn-primary">同意退货</button>
									<button type="button" id="disagree" class="btn btn-danger btn-full-screen btn-primary">不同意</button>
								</#if>
								<#--
								<#if obj.stateReturn == 2>
									<button type="button" id="received" class="btn btn-danger btn-full-screen btn-primary">已经收货</button>
								</#if>
								-->
								<button type="button" id="back" class="btn btn-danger btn-full-screen btn-primary" >返回</button>
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
