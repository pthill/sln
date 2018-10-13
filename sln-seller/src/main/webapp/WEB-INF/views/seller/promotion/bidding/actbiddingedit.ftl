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
	
	initValidtor();
	
	$("#addActivityBiddingPrice").click(function(){
	 	var html_ = "";
		 html_ += '<div class="pricetr form-group">'+
		 	'	<div class="col-lg-5 pull-left">'+
		 	'		<label class="col-sm-2 control-label">销量：</label>'+
		 	'		<div class="col-sm-8">'+
		 	'			<input id="saleNumberAll" name="saleNumberAll" required min="1"'+
		 	'				max="999999" data-bv-numeric="true"'+
		 	'				data-bv-numeric-message="请输入正确的数字" pattern="^\\d{1,6}$"'+
		 	'				data-bv-regexp-message="销量为整数，取值1-999999"'+
		 	'				data-bv-lessthan-message="销量必须小于999999"'+
		 	'				data-bv-greaterthan-message="销量必须大于1" class="form-control"'+
		 	'				value="${(actBiddingPrice.saleNum)!}" type="text">'+
		 	'		</div>'+
		 	'	</div>'+
		 	'	<div class="col-lg-5 pull-left">'+
		 	'		<label class="col-sm-2 control-label">价格：</label>'+
		 	'		<div class="col-sm-8">'+
		 	'			<input id="priceNmberAll" name="priceNmberAll" required '+
		 	'				data-bv-numeric="true" data-bv-numeric-message="请输入正确的金额" min="0.01"'+
		 	'				max="999999" pattern="^(([1-9]+)|([0-9]+\\.?[0-9]{1,2}))$"'+
		 	'				data-bv-regexp-message="金额保留两位小数" data-bv-lessthan-inclusive="true"'+
		 	'				data-bv-lessthan-message="金额必须小于999999"'+
		 	'				data-bv-greaterthan-inclusive="true"'+
		 	'				data-bv-greaterthan-message="金额必须大于0.01" class="form-control"'+
		 	'				value="${(actBiddingPrice.price)!}" type="text">'+
		 	'		</div>'+
		 	'	</div>'+
		 	'	<div class="col-lg-2 pull-right">'+
		 	'		<button type="button" onclick="deltr(this)"'+
		 	'			class="a-del-proItem btn btn-del btn-primary">删除</button>'+
		 	'	</div>'+
		 	'</div>';
	 	$("#activityBiddingPrice").append(html_);
	 	delvalidate();
    });
	
	$('#selectProduct').click(function(){
    	$('#productDlg').dialog('open');
    });
        
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
});

function initValidtor(){
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
			
			var r = /^[0-9]*[1-9][0-9]*$/　　//正整数  
			var saleNumberAll=$("input[name='saleNumberAll']")
			var numberSaleNumber = saleNumberAll.length;
			for(var i=0; i<numberSaleNumber; i++) {
				var saleNumber = saleNumberAll[i].value;
				if(!(r.test(saleNumber))) {
					$.messager.alert('提示','销量请输入正整数');
					return false;
				}
			}
			if(numberSaleNumber > 1) {
				for(var i=0; i<numberSaleNumber; i++) {
					if((i+1) != numberSaleNumber) {
						var saleNumber1 = saleNumberAll[i].value;
						var saleNumber2 = saleNumberAll[i+1].value;
						if(Number(saleNumber1) >= Number(saleNumber2)) {
							$.messager.alert('提示','销量必须逐级累加');
							return false;
						}
					}
				}
			}
			
			var rPrice = /^[0-9]*[1-9][0-9]*$/　　//正整数  
			var priceNmberAll=$("input[name='priceNmberAll']")
			var priceNmberNumber = priceNmberAll.length;
			for(var i=0; i<priceNmberNumber; i++) {
				var priceNmber = priceNmberAll[i].value;
				if(!isNaN(priceNmber) && Number(priceNmber) < 0) {
					$.messager.alert('提示','请输入正确的价格');
					return false;
				}
			}
			if(priceNmberNumber > 1) {
				for(var i=0; i<priceNmberNumber; i++) {
					if((i+1) != priceNmberNumber) {
						var priceNmber1 = priceNmberAll[i].value;
						var priceNmber2 = priceNmberAll[i+1].value;
						if(Number(priceNmber1) <= Number(priceNmber2)) {
							$.messager.alert('提示','价格必须逐级递减');
							return false;
						}
					}
				}
			}
			var productId = $('#productId').val();
			if(!productId) {
				$.messager.alert('提示','请选择商品');
				return false;
			}
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var firstEndTime = $('#firstEndTime').val();
			var lastEndTime = $('#lastEndTime').val();
			
			var startInt = new Date(startTime.replace("-", "/").replace("-", "/"));
			var endInt = new Date(endTime.replace("-", "/").replace("-", "/"));
			var firstEndTimeInt = new Date(firstEndTime.replace("-", "/").replace("-", "/"));
			var lastEndTimeInt = new Date(lastEndTime.replace("-", "/").replace("-", "/"));
			
			if(endInt <= startInt) {
				$.messager.alert('提示','活动结束时间不能小雨开始时间');
				return false;
			}
			if(firstEndTimeInt <= endInt) {
				$.messager.alert('提示','首付款结束时间不能小于活动结束时间');
				return false;
			}
			if(lastEndTimeInt <= firstEndTimeInt) {
				$.messager.alert('提示','尾款结束时间不能小于首付款结束时间');
				return false;
			}
			
			var descinfo = UM.getEditor('myEditor').getContent();
            $('#descinfo').val(descinfo);//商品描述信息
            validator.defaultSubmit();
		},
		fields:{
			saleNumberAll:{
				validators : {
					callback: {
						message: '销量必须递增',
						callback: function(value, validator,field) {
							return valideSale(field[0]);
						}
					}
				}
			},
			priceNmberAll:{
				validators : {
					callback: {
						message: '价格必须递减',
						callback: function(value, validator,field) {
							return validePrice(field[0]);
						}
					}
				}
			}
		}
	});
}

/**
 * 销量递增
 */
function valideSale(obj){
	var prev = $(obj).closest(".pricetr").prev();
	if(prev.length < 1){
		return true;
	}
	var lastsale = prev.find(":input[name='saleNumberAll']");
	if(!lastsale.val()){
		return false;
	}
	if(lastsale && Number(obj.value) <= Number(lastsale.val())){
		return false;
	}
	return true;
}

/**
 * 价格递减
 */
function validePrice(obj){
	var prev = $(obj).closest(".pricetr").prev();
	if(prev.length < 1){
		return true;
	}
	var lastsale = prev.find(":input[name='priceNmberAll']");
	if(!lastsale.val()){
		return false;
	}
	if(lastsale && Number(obj.value) >= Number(lastsale.val())){
		return false;
	}
	return true;
}

function deltr(obj) {
	$(obj).closest(".pricetr").remove();
	delvalidate();
}

function delvalidate(){
// 	var validatorfields = $("#addform").data('bootstrapValidator').options.fields;
// 	delete validatorfields.saleNumberAll;
// 	delete validatorfields.priceNmberAll;
	var validator = $("#addform").data('bootstrapValidator');
	validator.removeField("saleNumberAll");
	validator.removeField("priceNmberAll");
	
	$("#addform").data('bootstrapValidator').destroy();
	$('#addform').data('bootstrapValidator', null);
	
	initValidtor();
}

function productCallBack(data){
    $('#productId').val('');
    $('#productName').html("");
    if(data && data.length > 0){
        var productName = '';
        var productId = '';
        $.each(data, function(j, n){
            productId = n.id;
            productName = n.name1;
        })
         $('#productId').val(productId);
         $('#productName').html(productName);
    }
}

function productSubmit() {
	var selectedRow = $("#brandDataGrid").datagrid('getSelections');
	if (selectedRow == null) {
		$.messager.alert('提示', '请至少选择一个对象');
		return false;
	}
	var callbackfunc = eval('productCallBack');
	callbackfunc(selectedRow);
	$("#productDlg").dialog('close');
}
   
function updateStatus(name){
	$("#addform").bootstrapValidator('updateStatus', name, 'NOT_VALIDATED').
		bootstrapValidator('validateField', name);
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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actbidding">集合竞价</a>
					</li>
					<li class="active">编辑集合竞价</li>
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
						<input type="hidden" id="productId" name="productId" value="${(actBidding.productId)!}"/>
						<input type="hidden" id="descinfo" name="descinfo" />
						<input type="hidden" id="id" name="id" value="${(actBidding.id)!}"/>
				
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>集合竞价分类：</label>
							<div class="col-lg-4">
								<select name="type" class="form-control" required>
									<#if actBiddingTypes ??>
										<#list actBiddingTypes as actBiddingType>
								  			<option value = "${actBiddingType.id}" <#if actBiddingType.id == actBidding.type >selected</#if> >${actBiddingType.name}</option>
								  		</#list>
								  	</#if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>选择商品：
							</label>
							<div class="col-lg-10">
								<input type="button" value="选择商品" id="selectProduct" />
								<label id="productName" >${(actBidding.productName)!}</label>
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-10 ejava-errinforight">商品必须是上架状态</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>促销标题：</label> 
							<div class="col-lg-4">
								<input
									required
									class="form-control" type="text" id="title"
									name="name"
									value="${(actBidding.name)!}"
									/>
							</div>
							<label class="col-lg-6 ejava-errinforight">
								促销标题必须填写，输入2到200个字符
							</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>渠道：</label>
							<div class="col-lg-4">
								<@cont.select id="channel" name="channel" value="${(actBidding.channel)!1}" codeDiv="CHANNEL"
									class="form-control"
									mode="2"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动图片：</label> 
							<div class="col-lg-4">
								<input type="file" id="imageFile" 
									name="imageFile" class="form-control"  />
							</div>
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
									required
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的金额"
									min="0.01"
									max="999999"
									pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
                             	  	data-bv-regexp-message="金额保留两位小数" 
									data-bv-lessthan-inclusive="true"
	                                data-bv-lessthan-message="金额必须小于999999"
	                                data-bv-greaterthan-inclusive="true"
	                               	data-bv-greaterthan-message="金额必须大于0.01"
									type="text" id="marketPrice" name="marketPrice"
									value="${(actBidding.marketPrice)!''}"
									class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>初始价格: </label>
							<div class="col-lg-4">
								<input type="text" id="price" name="price"
									required
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的金额"
									min="0.01"
									max="999999"
									pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
                             	  	data-bv-regexp-message="金额保留两位小数" 
									data-bv-lessthan-inclusive="true"
	                                data-bv-lessthan-message="金额必须小于999999"
	                                data-bv-greaterthan-inclusive="true"
	                               	data-bv-greaterthan-message="金额必须大于0.01"
									value="${(actBidding.price)!''}"
									class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>首付款项: </label>
							<div class="col-lg-4">
								<input type="text" id="firstPrice" name="firstPrice"
									value="${(actBidding.firstPrice)!''}"
									required
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的金额"
									min="0.01"
									max="999999"
									pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
                             	  	data-bv-regexp-message="金额保留两位小数" 
									data-bv-lessthan-inclusive="true"
	                                data-bv-lessthan-message="金额必须小于999999"
	                                data-bv-greaterthan-inclusive="true"
	                               	data-bv-greaterthan-message="金额必须大于0.01"
									class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>限购数量：</label> 
							<div class="col-lg-4">
								<input
									required
									min="1"
									max="999999"
									data-bv-lessthan-message="数量必须小于999999"
                             	  	data-bv-greaterthan-message="数量必须大于1"
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的数字"
									pattern="^\d{1,6}$"
                             	  	data-bv-regexp-message="数量不正确" 
									class="form-control" 
									id="purchase"
									name="purchase"
									value="${(actBidding.purchase)!''}" />
							</div>
							<label class="col-lg-6 ejava-errinforight">
								 限购数量，每人每次下单最多可以购买多少商品，最少为1
							</label>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">阶梯价格：</label> 
							<div class="col-lg-4">
								<a href="javascript:void(0);" id="addActivityBiddingPrice"><img
									src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/images/newclass.jpg"
									/>新增阶梯价格</a>
							</div>
						</div>

						<div class="ejformstyle" id="activityBiddingPrice">
							<#if actBiddingPrices??>
		                    	<#list actBiddingPrices as actBiddingPrice>
									<div class="pricetr form-group">
										<div class="col-lg-5 pull-left">
											<label class="col-sm-2 control-label">销量：</label>
											<div class="col-sm-8">
												<input id="saleNumberAll" name="saleNumberAll"
													min="1"
													max="999999"
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的数字"
													pattern="^\d{1,6}$"
				                             	  	data-bv-regexp-message="销量为整数，取值1-999999"
				                             	  	data-bv-lessthan-message="销量必须小于999999"
				                             	  	data-bv-greaterthan-message="销量必须大于1"
													class="form-control" 
													value="${(actBiddingPrice.saleNum)!}" type="text">
											</div>
										</div>
										<div class="col-lg-5 pull-left">
											<label class="col-sm-2 control-label">价格：</label>
											<div class="col-sm-8">
												<input id="priceNmberAll" name="priceNmberAll"
													data-bv-numeric="true"
													data-bv-numeric-message="请输入正确的金额"
													min="0.01"
													max="999999"
													pattern="^(([1-9]+)|([0-9]+\.?[0-9]{1,2}))$"
				                             	  	data-bv-regexp-message="金额保留两位小数" 
													data-bv-lessthan-inclusive="true"
					                                data-bv-lessthan-message="金额必须小于999999"
					                                data-bv-greaterthan-inclusive="true"
					                               	data-bv-greaterthan-message="金额必须大于0.01"
													class="form-control" 
													value="${(actBiddingPrice.price)!}" type="text">
											</div>
										</div>
										<div class="col-lg-2 pull-right">
											<button type="button" onclick="deltr(this)"
												class="a-del-proItem btn btn-del btn-primary">删除</button>
										</div>
									</div>
								</#list>
							</#if>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-10 ejava-errinforight">
								销售额到相应数量之后，享受不同的价格
							</label>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>库存：</label> 
							<div class="col-lg-4">
								<input
									class="form-control" id="stock" name="stock"
									value="${(actBidding.stock)!''}"
									required
									min="1"
									max="999999"
									data-bv-numeric="true"
									data-bv-numeric-message="请输入正确的数字"
									pattern="^\d{1,6}$"
                             	  	data-bv-regexp-message="库存为整数，取值1-999999"
                             	  	data-bv-lessthan-message="库存数必须小于999999"
                             	  	data-bv-greaterthan-message="库存数必须大于1" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>活动时间：</label> 
							<div class="col-lg-4">
								<input
									type="text" id="startTime" name="startTime"
									class="form-control"
									required
									onblur="updateStatus(this.name);"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
									value="${(actBidding.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
							<div class="col-lg-4">
								<input type="text" id="endTime"
									name="endTime" class="form-control"
									required
									onblur="updateStatus(this.name);"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
									value="${(actBidding.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>首付款截止时间：</label>
							<div class="col-lg-4">
								<input type="text" id="firstEndTime" name="firstEndTime"
									class="form-control"
									required
									onblur="updateStatus(this.name);"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endTime\')}'});"
									value="${(actBidding.firstEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>							
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-6 ejava-errinforight">
								首付款付款截止时间，该时间不小于活动结束时间
							</label>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>尾款款截止时间：</label>
							<div class="col-lg-4">
								<input type="text" id="lastEndTime" name="lastEndTime"
									class="form-control"
									required
									onblur="updateStatus(this.name);"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'firstEndTime\')}'});"
									value="${(actBidding.lastEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
									/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"></label>
							<label class="col-lg-6 ejava-errinforight">
								尾款截止时间，该时间必须大于首付款截止时间
							</label>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red"></font>活动描述: </label>
							<div class="col-lg-8">
								<script type="text/plain" 
									id="myEditor"
									style="width: 100%; height: 240px;"><#noescape>${(actBidding.descinfo)!}</#noescape></script>
								<script type="text/javascript">
							    	var um = UM.getEditor('myEditor');
								</script>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-8 col-lg-offset-4">
								<button type="submit" class="btn btn-danger btn-primary">提交</button>
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