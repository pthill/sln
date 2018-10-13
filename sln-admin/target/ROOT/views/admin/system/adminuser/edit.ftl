<#assign
currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/system/adminuser"/>
<script>
	$(function() {
        var options={
            url:'${currentBaseUrl}/update',
            type:'post',
            success:function (data) {
                if(data && data.data>0){
                    $.messager.progress('close');
                    $.messager.show({
                        title: '提示',
                        msg: data.message,
                        showType: 'show'
                    });
                    $('#dataGrid').datagrid('reload');
                    closeW();
                }else{
                    $.messager.progress('close');
                    $.messager.show({
                        title: '提示',
                        msg: data.message,
                        showType: 'show'
                    });
                    $('#dataGrid').datagrid('reload');
                    closeW();
                }
            }
        };
		$("#addBtn").click(function() {
			var isValid = $("#adForm").form('validate');
			if (isValid) {
				var flag=$("#flag").val();
				if(flag=="true"){
                    if($("#operationId").val()==""){
                        $.messager.alert('提示','请选择所属业务管理方！');
                        return;
                    }
				}
                $.messager.progress({
                    text : "提交中..."
                });
                $("#adForm").ajaxSubmit(options);
			}
		});
        $("#parkId").change(function(){
            getOperationId($("#operationId"),$(this).val());
        });
        function getOperationId(_select,parkId) {
            _select.empty();
            $.ajax({
                type:"get",
                url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/system/adminuser/getOperationManagers",
                dataType: "json",
				data:"parkId="+parkId,
                cache:false,
                success:function(data){
                    if (data.success) {
                        _select.empty();
                        var selectOption = '<option value ="">-- 请选择 --</option>'
                        $.each(data.data, function(i, operation){
                            selectOption += "<option value=" + operation.name + ">" + operation.operationName + "</option>";
                        })
                        _select.append(selectOption);
                    } else {

                    }
                }
            });
        }
	});
	function closeW(){
		$("#editWin,window.parent.document").window("close");
	}
</script>
<div class="formbox-a">
	<form id="adForm" method="post">
		<input type="hidden" id="id" name="id" value="${(admin.id)!''}">
		<div class="form-contbox">
			<dl class="dl-group">
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>账号: </label>
							<span id="nameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="name"
								name="name" value="${(admin.name)!}"
								data-options="required:true,validType:['username','length[2,15]']"
								class="txt w400" /> <span class="title_span">登录账号名</span>
							</span>
						</p>
					</div>
					<#if admin??>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">密码: </label>
							<span id="pwdspan"> <input
								class="txt w200 easyui-validatebox" type="text" id="password"
								name="password"
								class="txt w400" data-options="validType:'length[6,8]'"/>
								<div class="tooltip tooltip-right"
									style="left: 66%;display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
									<div class="tooltip-content">为空表示不修改</div>
									<div class="tooltip-arrow-outer"
										style="border-right-color: rgb(204, 153, 51);"></div>
									<div class="tooltip-arrow"
										style="border-right-color: rgb(255, 255, 204);"></div>
								</div>
							</span>
						</p>
					</div>
					</#if>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">电话: </label>
							<span id="telspan"> <input
								class="txt w200 easyui-validatebox" type="text" id="tel"
								name="tel" value="${(admin.tel)!}"
                                data-options="required:true,validType:'mobile'"
								class="txt w400" /> <span class="title_span">长度为11-12个字符</span>

							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>角色: </label>
							<select id="roleId" class="txt w200 easyui-combobox" name="roleId"
								editable="false" readonly="readonly">
								<#if roles?? && roles?size &gt; 0>
								<#list roles as role>
									<option value="${role.id}" <#if admin??&&admin.roleId==role.id>selected</#if>>${role.rolesName}</option>
								</#list>
								</#if>
							</select>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<select id="scope" class="txt w200 easyui-combobox" name="status"
								editable="false" data-options="required:true">
								<#list codeManager.codeMap['ADMIN_STATUS'] as code>
									<option value="${code.codeCd}" <#if admin??&&admin.status?string==code.codeCd>selected</#if>>${code.codeText!''}</option>
								</#list>
							</select>
						</p>
					</div>
					<#if admin??&&admin.parkId??&&admin.parkId!=0>
					<input type="hidden" id="flag" value="true">
                    <div class="fluidbox" id="park">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>园区名称: </label>
                            <select id="parkId" name="parkId" class="txt w200">
								<#list parks.result as park>
                                    <option value="${park.id}" <#if park.id=admin.parkId>selected</#if>>${park.parkName}</option>
								</#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox" id="operation">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>业务管理方名称: </label>
                            <select id="operationId" class="txt w200" name="operationId">
								<#list operations.result as operation>
                                    <option value="${operation.name}" <#if operation.name=admin.operationId?string>selected</#if>>${operation.operationName}</option>
								</#list>
                            </select>
                        </p>
                    </div>
					</#if>
				</dd>
			</dl>
			<p class="p-item p-btn">
				<a id="addBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete" onclick="closeW()">关闭</a> <input
					type="hidden" id="rid" name="rid" value="0">
			</p>
		</div>
	</form>
</div>