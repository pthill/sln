<#include "/seller/commons/_head.ftl"> <#assign
currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier/"/>
<script language="javascript">
    $(function () {
	$(".form-control").change(function(){
    		var mrak = $("[type = 'submit']").prop('disabled');
    		if(mrak){
    			$("[type = 'submit']").prop('disabled',false);
    		}
    	})
        var backUrl = "${currentBaseUrl}";
        
        initMenu('supplier');
    	
    	$("button[type='button'].back").click(function(){
     		window.location.href= domain+"/seller/system/supplier";
    	});
    	
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
    			 $('#addform').ajaxSubmit({
   		            url: '${currentBaseUrl}/create',
   		            type: 'post',
   		            success: function (data) {
   		                if (data && null != data.success && data.success == true) {
   		                    window.location.href=backUrl;
   		                }else if(data.backUrl != null){
   		                   $.messager.alert("提示",data.message);
   		                    window.location.href=data.backUrl;
   		                }else{
   		                    refrushCSRFToken(data.csrfToken);
   		                    $.messager.alert("提示",data.message);
   		                }
   		            }
   		        });
    		},
    		fields : {
    			name : {
    				validators : {
    					 notEmpty: true,
                         stringLength: {
                            min: 2,
                            max: 10,
                            message: '长度必须在2到10位之间'
                         },
                         regexp: {
                            regexp: /^[\Α-\￥]+$/,
                            message: '请输入中文'
                         }
    				},
    			},
    			adress : {
    				validators : {
    					 notEmpty: true,
                        stringLength: {
                            min: 2,
                            max: 20,
                            message: '长度必须在2到20位之间'
                        },
                        regexp: {
                            regexp: /^[\Α-\￥]+$/,
                            message: '请输入中文'
                        }
    				}
    			},
    			contactsName : {
    				validators : {
    					 notEmpty: true,
                        stringLength: {
                            min: 2,
                            max: 10,
                            message: '长度必须在2到10位之间'
                        },
                        regexp: {
                            regexp: /^[\Α-\￥]+$/,
                            message: '请输入中文'
                        }
    				}
    			},
    			contactsTel : {
					validators:{
						phone:{
							message:'请输入正确的手机号码',
							country:'cn'
						},
						notEmpty: true
					}  
    			},
    			bankOfAccounts : {
    				validators : {
    					 notEmpty: true,
                        stringLength: {
                            min: 2,
                            max: 10,
                            message: '长度必须在2到10位之间'
                        },
                        regexp: {
                            regexp: /^[\Α-\￥]+$/,
                            message: '请输入中文'
                        }
    				}
    			},
    			supplierType:{
    				validators : {
    					 notEmpty: true
    				}
    			},
    			benkAccount : {
    				validators : {
    					stringLength:{
					       min:18,
					       max:18,
					       message:'银行账号只限18位数'
					      },
					      numeric: true,
					      notEmpty: true
    				}
    			}
    			
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
						href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier">供应商管理</a>
					</li>
					<li class="active">添加供应商</li>
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
						enctype="multipart/form-data" data-bv-message="该项必填">
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font> 供应商名称: </label>
							<div class="col-lg-4">
								<input type="text" id="name" name="name"
									value="${(supplier.name)!''}" class="form-control"
									data-bv-stringlength="true"
                                     data-bv-stringlength-min="2"
                                     data-bv-stringlength-max="20"
                                     required
                                     data-bv-stringlength-message="名称2-20位长度"
									 />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>地址: </label>
							<div class="col-lg-4">
								<input type="text" id="adress" name="adress"
									class="form-control"
									value="${(supplier.adress)!''}"
									data-bv-stringlength="true"
                                     data-bv-stringlength-min="2"
                                     data-bv-stringlength-max="50"
                                     required
                                     data-bv-stringlength-message="地址2-50位长度"
									 />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>类型: </label>
							<div class="col-lg-4">
							<@cont.select id="supplierType" name = "supplierType"  
								value="${(supplier.supplierType)!''}" codeDiv="SUPPLIER_TYPE"
								class="form-control" mode="1"/>
								
								
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>负责人名称: </label>
							<div class="col-lg-4">
							<input type="text" id="contactsName" name="contactsName"
									class="form-control"
									value="${(supplier.contactsName)!''}" 
									data-bv-stringlength="true"
                                     data-bv-stringlength-min="2"
                                     data-bv-stringlength-max="20"
                                     required
                                     data-bv-stringlength-message="名称2-20位长度"
									/>
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>联系方式: </label>
							<div class="col-lg-4">
								<input type="text" id="contactsTel" name="contactsTel"
									class="form-control"
									value="${(supplier.contactsTel)!''}" />
							
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>开户银行: </label>
							<div class="col-lg-4">
								<input type="text" id="bankOfAccounts" name="bankOfAccounts"
									class="form-control"
									value="${(supplier.bankOfAccounts)!''}" />
							
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red">*</font>银行账号: </label>
							<div class="col-lg-4">
									<input type="text" id="benkAccount" name="benkAccount"
									class="form-control"
									value="${(supplier.benkAccount)!''}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red"></font>支付宝: </label>
							<div class="col-lg-4">
									
									<input type="text" id="alipay" name="alipay"
									class="form-control"
									value="${(supplier.alipay)!''}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><font class="red"></font>微信: </label>
							<div class="col-lg-4">
							<input type="text" id="weChat" name="weChat"
									class="form-control"
									value="${(supplier.weChat)!''}" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-8 col-lg-offset-3">
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
