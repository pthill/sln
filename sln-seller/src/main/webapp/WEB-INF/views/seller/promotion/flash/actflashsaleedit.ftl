<#include "/seller/commons/_head.ftl">

<script language="javascript">

$(function(){
	initMenu('flash');
	
	$(".back").click(function(){
 		window.location.href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/flash";
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	
	//输入值改变，让保存和删除操作可用
	$(":input").change(function(){
		$(this).closest("div.pull-left").siblings("div:last").find("button.btn").removeAttr("disabled");
	});
	
	initValidator();
});

function initValidator(){
	$('#addform').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		submitHandler: function (validator, form, submitButton) {
			if(!validator.isValid()){
				return false;
			}
		}
	});
}

function reloadValidator(){
	$("#addform").data('bootstrapValidator').destroy();
	$('#addform').data('bootstrapValidator', null);
	initValidator();
}

function addProduct(obj){
// 	$('#goodsDialog').dialog('open');
	$('#gd_dataGrid').datagrid('unselectAll');
	
	var stageIdHidden = $(obj).siblings(".stageIdHidden").val();
	$("#currStageId").val(stageIdHidden);
	
	var width_ = '1000';
    if(ismobile()){
		width_ = "100%";
    }
	$("#goodsListDiv").window({
		top:50,
		width : width_,
		height : 505,
		title : "选择商品",
		modal : true,
		shadow : false,
		collapsible : false,
		minimizable : false,
		maximizable : false
	});
}
	
function saveProduct(obj){
	var productId = $(obj).siblings("input[name='productId']").val();
	var stageId = $(obj).siblings("input[name='stageId']").val();
	var price = $(obj).closest("div.prom-items").find("input[name='price']").val();
	var orgPrice = $(obj).closest("div.prom-items").find("input[name='pcPrice']").val();
	var stock = $(obj).closest("div.prom-items").find("input[name='stock']").val();
	log.i(productId+"|"+stageId+"|"+price+"|"+stock);
	
	if (productId == null || productId == "") {
		$.messager.alert('提示', "请选择商品");
		return false;
	}
	
	if(!price){
		$.messager.alert('提示', "请输入商品活动价格");
		return false;
	}
	
	if(!stock){
		$.messager.alert('提示', "请输入商品活动库存");
		return false;
	}
	
	if(isNaN(price)){
		$.messager.alert('提示', "价格必须是数字");
		return false;
	}
	if(isNaN(stock)){
		$.messager.alert('提示', "库存必须是正整数");
		return false;
	}
	
	if(Number(price) > Number(orgPrice)){
		$.messager.alert('提示', "商品的活动价格不能高于商品原价");
		return false;
	}
	
	var params = "productId=" + productId;
		params += "&price=" + price;
		params += "&stock=" + stock;
		params += "&stageId=" + stageId;
		params += "&actFlashSaleId=" + $("#id").val();

	$(obj).attr("disabled",true);
    $.messager.confirm('提示', '确定保存该条申请信息吗？', function(r){
        if (r){
            $.messager.progress({text:"提交中..."});
            $.ajax({
                type:"POST",
                url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/flash/saveproduct",
                dataType: "json",
                data: params,
                cache:false,
                success:function(data, textStatus){
                   if (data.success) {
                       	$.messager.alert('提示', "保存成功！");
                   }else{
						$.messager.alert('提示', data.message);
	             	  	$(obj).removeAttr("disabled");
                   }
                   $.messager.progress('close');
                }
            });
        } else{
        	$(obj).removeAttr("disabled");
        }
    });
}

function deleteProduct(obj) {
	var productId = $(obj).siblings("#productId").val();
	var stageId = $(obj).siblings("#stageId").val();
	
	if (productId == null || productId == "") {
		$.messager.alert('提示', "请选择商品");
		return false;
	}
	
	var params = "productId=" + productId;
	params += "&stageId=" + stageId;

    $.messager.confirm('提示', '确定删除该条申请信息吗？', function(r){
        if (r){
            $.messager.progress({text:"提交中..."});
            $.ajax({
                type:"POST",
                url: "${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/flash/applydelete",
                dataType: "json",
                data: params,
                cache:false,
                success:function(data, textStatus){
                    if (data.success) {
                 	   	delpro(obj);
						$.messager.alert('提示', "删除成功！");
                    }else{
                        $.messager.alert('提示', data.message);
                    }
                    $.messager.progress('close');
                }
            });
        }
    });
}

function delpro(obj){
	$(obj).closest("div.prom-items").remove();
	$("#addform").data('bootstrapValidator').removeField('price'); 
	$("#addform").data('bootstrapValidator').removeField('stock'); 
	reloadValidator();
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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/flash">限时抢购</a>
					</li>
					<li class="active">活动商品申请</li>
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

					<form method="post" id="addform" class="form-horizontal">
						<input type="hidden" id="id" name="id" value="${(actFlashSale.id)!''}">
						 
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动名称：</label>
							<div class="col-lg-4">
								<span class="info-span" >${(actFlashSale.actFlashSaleName)!''}</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动日期：</label>
							<div class="col-lg-4">
								<span class="info-span" >${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动状态：</label>
							<div class="col-lg-4">
								<@cont.select disabled="disabled" id="status" 
									value="${(actFlashSale.status)!''}" codeDiv="FLASH_SALE_STATUS" 
									class="form-control" mode="1"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>应用渠道：</label>
							<div class="col-lg-4">
								<@cont.select disabled="disabled" id="channel" 
									value="${(actFlashSale.channel)!''}" codeDiv="CHANNEL" 
									class="form-control" mode="1"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>作废原因：</label>
							<div class="col-lg-4">
								<span class="info-span" >${(actFlashSale.auditOpinion)!''}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>申请规则：</label>
							<div class="col-lg-4">
								<span class="info-span" >${(actFlashSale.auditRule)!''}</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动描述：</label>
							<div class="col-lg-8">
								<span class="info-span" >${(actFlashSale.remark)!''}</span>
							</div>
						</div>

						<div style="padding-top: 10px;">抢购阶段
							<span class="flash-time-tip">请根据自己配货信息申请活动商品，
								同阶段如果添加重复商品会覆盖之前的设置，商品添加好后点击“保存”按钮以提交数据</span>
						</div>
						<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />

						<div class="prom-group">
						<input type="hidden" value="" id="currStageId"/>
						<#if stageList??>
						<#list  stageList as stage>
							<div class="prom-stage">
								<span class="flash-time-tip">${(stage.startTime)!''}点 ~ ${(stage.endTime)!''}点：</span>
							
								<#if actFlashSale.status?? && actFlashSale.status == 2 >
								<input class="inputs" type="button" value="添加商品" onclick="addProduct(this)"/>
								</#if>
								<label class="prom-stage-remark">${(stage.remark)!''}</label>
								<input type="hidden" value="${stage.id}" id="hd_stateId_${stage_index}" class="stageIdHidden"/>
							
							</div>
							<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />
							
							<div id="stage${stage.id}">
							<#if stage.productList?? && stage.productList?size &gt; 0 >
								<#if actFlashSale.status?? && actFlashSale.status == 2 >
									<#list stage.productList as stageProduct>
									<#if (stageProduct.product)?? >
									<div class="form-group prom-items">
										<div class="col-lg-4 pull-left">
											<label class="prom-label control-label">商品：</label>
											<div class="prom-div">
											<input type="text"
												disabled
												name="productName" 
												class="active-input-list form-control"
												value="${(stageProduct.product.name1)!''}" 
												title="${(stageProduct.product.name1)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">价格：</label>
											<div class="prom-div">
												<input type="text" 
													name="price"
													required
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的数字"
													min="0.1"
													max="999999"
													pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
				                             	  	data-bv-regexp-message="金额保留两位小数" 
													data-bv-lessthan-inclusive="true"
					                                data-bv-lessthan-message="金额必须小于999999"
					                                data-bv-greaterthan-inclusive="true"
					                               	data-bv-greaterthan-message="金额必须大于0.1"
													class="active-input-list form-control"
													value="${(stageProduct.price)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">原价：</label>
											<div class="prom-div">
												<input type="text"
													disabled
													name="pcPrice"
													class="active-input-list form-control"
													value="${(stageProduct.product.mallPcPrice)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">库存：</label>
											<div class="prom-div">
												<input type="text"
													name="stock"
													required
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的数字"
													pattern="^\d{1,6}$"
				                             	  	data-bv-regexp-message="输入非法字符，请检查"
													class="active-input-list form-control"
													value="${(stageProduct.stock)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-right">
											<input type="hidden" id="productId" name="productId" value="${(stageProduct.productId)!''}">
											<input type="hidden" id="stageId" name="stageId" value="${(stageProduct.actFlashSaleStageId)!''}">
											<button type="button"
												class="btn btn-del btn-primary prom-handel-btn" 
												onclick="saveProduct(this)">保存</button>
											<button type="button"
												class="btn btn-del btn-primary prom-handel-btn" 
												onclick="deleteProduct(this)">删除</button>
										</div>
									</div>
									</#if>
									</#list>
								<#else>
									<#list stage.productList as stageProduct>
									<#if (stageProduct.product)?? >
									<div class="form-group prom-items">
										<div class="col-lg-4 pull-left">
											<label class="prom-label control-label">商品：</label>
											<div class="prom-div">
											<input type="text"
												disabled
												name="productName" 
												class="active-input-list form-control"
												value="${(stageProduct.product.name1)!''}" 
												title="${(stageProduct.product.name1)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">价格：</label>
											<div class="prom-div">
												<input type="text" 
													name="price"
													disabled
													required
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的数字"
													min="0.1"
													max="999999"
													pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
				                             	  	data-bv-regexp-message="金额保留两位小数" 
													data-bv-lessthan-inclusive="true"
					                                data-bv-lessthan-message="金额必须小于999999"
					                                data-bv-greaterthan-inclusive="true"
					                               	data-bv-greaterthan-message="金额必须大于0.1"
													class="active-input-list form-control"
													value="${(stageProduct.price)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">原价：</label>
											<div class="prom-div">
												<input type="text"
													disabled
													name="pcPrice"
													class="active-input-list form-control"
													value="${(stageProduct.product.mallPcPrice)!''}" />
											</div>
										</div>
										<div class="col-lg-2 pull-left">
											<label class="prom-label control-label">库存：</label>
											<div class="prom-div">
												<input type="text"
													name="stock"
													disabled
													required
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的数字"
													pattern="^\d{1,6}$"
				                             	  	data-bv-regexp-message="输入非法字符，请检查"
													class="active-input-list form-control"
													value="${(stageProduct.stock)!''}" />
											</div>
										</div>
									</div>
									</#if>
									</#list>
								</#if>
							</#if>		
							</div>	
						</#list>
						</#if>
						</div>

						<div class="form-group">
							<div class="col-lg-8 col-lg-offset-4">
								<button type="button" style="padding: 6px 40px;" 
									class="btn btn-danger back btn-primary">返回</button>
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
<div style="display: none" id="goodsListDiv">
<#include "goodsDialog.ftl"/>
</div>
<#include "/seller/commons/_addcommonfooter.ftl"> <#include
"/seller/commons/_end.ftl">