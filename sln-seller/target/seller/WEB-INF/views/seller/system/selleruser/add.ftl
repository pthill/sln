<#assign
currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/sellerUser"/>

<script>
	$(function() {
	    $("#supplier").hide();
		initMenu('sellerUser');
		$('#addform').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			submitHandler: function (validator, form, submitButton) {
				if(validator.isValid()){
				    var flag=$("#flag").val();
                    var supplierId=$("#supplierId").val();
				    var roleId=$("#roleId").val();
				    if(roleId==null||roleId==""){
                        $.messager.alert('提示','请选择角色');
                        return;
					}
					if(flag=="1"){
                        if(supplierId==null||supplierId==""){
                            $.messager.alert('提示','请选择供应商');
                            return;
                        }
					}
					$("#addform").form('submit',{
						url : "${currentBaseUrl}/save",
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
        $("#roleId").change(function(){
            roleOnchang();
        });
        $("#supplierId").change(function () {
            $("#supplierName").val($("#supplierId").find("option:selected").text());
        })
	});
	function closeW() {
		$("#editWin,window.parent.document").window("close");
	}
	function roleOnchang(){
        $("#supplierId option:first").prop("selected", 'selected');
        var roleType=$('#roleId').find('option:selected').attr('roletype');
        if(roleType ==1){
            $("#supplier").show();
            $("#flag").val("1");
        }else{
            $("#supplier").hide();
            $("#flag").val("0");
            $("#supplierName").val("");
        }
	}
</script>

<!-- Page Body -->
<div id="bodyhaiheyungu" style="overflow-y: auto; overflow-x: hidden;">
	<div class="col-lg-12 col-sm-12 col-xs-12">
		<div style="padding-top: 10px;">
			<span>
				<img style="float: left;margin-right: 6px;" 
				src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/seller/images/warning.jpg"/>
				新增的管理员账号初始密码为:123456,
				请告知用户登录后及时修改密码
			</span>
		</div>
		<hr class="wide" style="margin-bottom: 10px; margin-top: 10px;" />

		<form method="get" id="addform" class="form-horizontal" style="margin: 30px 0px;"
			action="${currentBaseUrl}/doAdd" enctype="multipart/form-data"
			data-bv-message="该项必填">
			<input type="hidden" id="flag" name="flag" value="1">
			<input type="hidden" id="rid" name="rid" value="0">
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
					<select id="roleId" class="form-control" name="roleId" <#if admin??&&admin.roleId??>disabled=true</#if>
						required
						editable="false" >
                        <option value="">请选择角色</option>
						<#if roles?? && roles?size &gt; 0>
						<#list roles as role>
							<option value="${role.id}" roletype="${role.roleType}">${role.rolesName}</option>
						</#list>
						</#if>
					</select>
				</div>
			</div>
			 <div class="form-group" id="supplier">
				<label class="col-lg-2 control-label"><font class="red">*</font>供应商:</label>
				<div class="col-lg-8">
					<input type="hidden" id="supplierName" name="supplierName" value="">
					<select id="supplierId" class="form-control" name="supplierId"
						required
						editable="false" >
						<#if suppliers?? && suppliers?size &gt; 0>
                            <option value="">请选择供应商</option>
						<#list suppliers as supplier>
							<option value="${supplier.id}" >${supplier.name}</option>
						</#list>
						<#else>
                            <option value="">请选择供应商</option>
						</#if>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label"><font class="red">*</font>状态:</label>
				<div class="col-lg-8"> 
					<select id="state" class="form-control" name="state" 
						required
						editable="false">
						<#list codeManager.codeMap['ADMIN_STATUS'] as code>
							<option value="${code.codeCd}">${code.codeText!''}</option>
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
