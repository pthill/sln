<#assign
currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/sellerUser"/>

<script>
	$(function() {
		initMenu('sellerUser');
		$('#addform').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			submitHandler: function (validator, form, submitButton) {
				if(validator.isValid()){
					$("#addform").form('submit',{
						url : "${currentBaseUrl}/update",
						success : function(e) {
							$.messager.progress('close');
							$.messager.show({
								title : '提示',
								msg : e,
								showType : 'show'
							});
							$('#dataGrid,window.parent.document')
									.datagrid('reload');
							closeW();
						}
					});
					
				}
			},
            fields:{
                phone:{
                    validators:{
                        phone:{
                            message:'请输入正确的手机号码',
                            country:'cn'
                        }
                    }
                },
                code:{
                    validators:{
                        regexp: {
                            regexp: /[a-zA-Z0-9_]{3,15}$/,
                            message: '角色编码可以是英文或者数字(3到15位)'
                        }
                    }
                },
                realName:{
                    validators:{
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
                job:{
                    validators: {
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
                }
            }
		});
		$("#supplierId").change(function () {
            $("#supplierName").val($("#supplierId").find("option:selected").text());
        });
	});
	function closeW() {
		$("#editWin,window.parent.document").window("close");
	}

</script>

<!-- Page Body -->
<div id="bodyhaiheyungu" style="overflow-y: auto; overflow-x: hidden;">
	<div class="col-lg-12 col-sm-12 col-xs-12">
		<form method="get" id="addform" class="form-horizontal" style="margin: 30px 0px;"
			action="${currentBaseUrl}/doAdd" enctype="multipart/form-data"
			data-bv-message="该项必填">
			<input type="hidden" name="id" value="${(admin.id)!}"/>
			<#if admin??>
			<div class="form-group">
				<label class="col-lg-2 control-label">密码:</label>
				<div class="col-lg-5"> 
					<input
						placeholder="点击修改密码"
						class="form-control" type="text" id="password"
						data-bv-stringlength="true" 
                        data-bv-stringlength-max="20"
                        data-bv-stringlength-message="密码最大20位长度"
						name="password" /> 
				</div>
				<label class="col-lg-5 ejava-errinforight">为空表示不修改</label>
			</div>
			</#if>

			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>编码:</label>
				<div class="col-lg-5"> 
					<input
						class="form-control" 
						type="text" 
						id="code"
						name="code" 
						required
						value="${(admin.code)!}"
						data-bv-stringlength="true" 
                        data-bv-stringlength-min="2"
                        data-bv-stringlength-max="20"
                        data-bv-stringlength-message="编码2-20位长度"
					/>
				</div>
				<label class="col-lg-5 ejava-errinforight">长度为2-20个字符</label>
			</div>
			
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>姓名:</label>
				<div class="col-lg-5"> 
					<input
						class="form-control" 
						type="text" 
						id="realName"
						name="realName" 
						required
						value="${(admin.realName)!}"
						data-bv-stringlength="true" 
                        data-bv-stringlength-min="2"
                        data-bv-stringlength-max="20"
                        data-bv-stringlength-message="姓名2-20位长度"
					/>
				</div>
				<label class="col-lg-5 ejava-errinforight">长度为2-20个字符</label>
			</div>
			
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>电话:</label>
				<div class="col-lg-5">
					<input
						class="form-control" 
						type="text" 
						id="phone"
						name="phone" 
						value="${(admin.phone)!}"
					/>
				</div>
				<label class="col-lg-5 ejava-errinforight">手机号码，11位长度</label>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>职务:</label>
				<div class="col-lg-5"> 
					<input
						class="form-control" 
						type="text" id="job"
						name="job" 
						value="${(admin.job)!}"
						data-bv-stringlength="true" 
                        data-bv-stringlength-min="2"
                        data-bv-stringlength-max="40"
                        data-bv-stringlength-message="职务2-40位长度"
					/>
				</div>
				<label class="col-lg-5 ejava-errinforight">长度为2-40个字符</label>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>角色:</label>
				<div class="col-lg-8"> 
					<select id="roleId" class="form-control" name="roleId"
						required  readonly="readonly"
						editable="false" >
                        <option value="${admin.roleId}" selected>${roleName}</option>
					</select>
				</div>
			</div>
		    <#if admin??&&roleType??&&roleType=='1'>
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>供应商:</label>
				<div class="col-lg-8">
                    <input type="hidden" id="supplierName" name="supplierName" value="${(admin.supplierName)!''}">
                    <select id="supplierId" class="form-control" name="supplierId"
						required
						editable="false" >
						<#if suppliers?? && suppliers?size &gt; 0>
						<#list suppliers as supplier>
							<option value="${supplier.id}" <#if admin??&&admin.supplierId==supplier.id>selected</#if>>${supplier.name}</option>
						</#list>
						</#if>
					</select>
				</div>
			</div>
			</#if>
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>状态:</label>
				<div class="col-lg-8"> 
					<select id="state" class="form-control" name="state" 
						required
						editable="false">
						<#list codeManager.codeMap['ADMIN_STATUS'] as code>
							<option value="${code.codeCd}" <#if admin??&&admin.state?string==code.codeCd>selected</#if>>${code.codeText!''}</option>
						</#list>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-lg-8 col-lg-offset-4" style="margin-top: 20px;">
					<button type="submit" class="btn btn-danger btn-primary">提交</button>
					<button type="button" class="btn btn-danger back btn-primary" onclick="closeW()">取消</button>
				</div>
			</div>
		</form>

	</div>
</div>

<!-- /Page Body -->
