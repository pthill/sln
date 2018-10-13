<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/system/adminuser"/>
<script>
	$(function() {
        var options={
            url:'${currentBaseUrl}/save',
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
			var parkId=$("#parkId").val();
			var roleId=$("#roleId").val();
			var operationId=$("#operationId").val();
			var flag=$("#flag").val();
            if(roleId==null||roleId==""){
                $.messager.alert('提示','请选择角色！');
                return;
            }
			if(flag=="0"){
                if(operationId==null||operationId==""||parkId==null||parkId==""){
                    $.messager.alert('提示','请选择所属园区和物业管理方！');
                    return;
                }
            }
            if (isValid) {
                $.messager.progress({
                    text: "提交中..."
                });
                $("#adForm").ajaxSubmit(options);
            }
		});
		$("#park").hide();
        $("#operation").hide();
        $("#roleId").change(function(){
            checkRoletype();
        });
        function checkRoletype() {
            var roleType=$('#roleId').find('option:selected').attr('roletype');
            if(roleType==0){
                $("#flag").val("0");
                $("#park").show();
                $("#operation").show();
                getParkId($("#parkId"));
            }else{
                $("#flag").val("1");
                $("#park").hide();
                $("#operationId").empty();
                $("#operationId").append('<option value="">--请选择--</option>')
                $("#operation").hide();
            }
        }
        $("#parkId").change(function(){
            getOperationId($("#operationId"),$(this).val());
        });
        function getParkId(_select) {
            _select.empty();
            $.ajax({
                type:"get",
                url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/system/adminuser/getParks",
                dataType: "json",
                cache:false,
                success:function(data){
                    if (data.success) {
                        _select.empty();
                        var selectOption = '<option value ="">-- 请选择 --</option>'
                        $.each(data.data, function(i, park){
                            selectOption += "<option value=" + park.id + ">" + park.parkName + "</option>";
                        })
                        _select.append(selectOption);
                    } else {
                    }
                }
            });
        }
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
	<#if !admin??>
	<span style="color: #F00;float: left;margin-left: 20px;margin-top: 2px;">
		<img style="float: left;margin-right: 6px;" 
		src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/images/warning.jpg"/>
		新增的管理员账号初始密码为:123456,
		请告知用户登录后及时修改密码
	</span>
	</#if>

	<form id="adForm" method="post">
        <input type="hidden" id="flag" name="flag" value="1">
		<div class="form-contbox">
			<dl class="dl-group">
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>账号: </label>
							<span id="nameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="name"
								name="name" data-options="required:true,validType:['username','length[2,15]']"
								class="txt w400" /> <span class="title_span">登录账号名</span>
							</span>
						</p>
					</div>
					<#if admin??>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">密码: </label>
							<span id="pwdspan"> <input
								class="txt w200" type="text" id="password"
								name="password"
								class="txt w400" /> 
								
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
								name="tel"  data-options="required:true,validType:'mobile'"
								class="txt w400" /> <span class="title_span">请输入正确的手机号</span>
							</span>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>角色: </label>
							<select id="roleId" class="txt w200" name="roleId" editable="false" data-options="required:true">
                                <option value="">请选择角色</option>
                                <#if roles?? && roles?size &gt; 0>
                                    <#list roles as role>
                                        <option value="${role.id}" roleType="${role.roleType}">${role.rolesName}</option>
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
									<option value="${code.codeCd}">${code.codeText!''}</option>
								</#list>
							</select>
						</p>
					</div>
                    <div class="fluidbox" id="park">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>园区名称: </label>
                            <select id="parkId" name="parkId" class="txt w200" name="parkId"  data-options="required:true">
                                <option value="">-- 请选择 --</option>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox" id="operation">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>业务管理方名称: </label>
                            <select id="operationId" name="operationId" class="txt w200" name="operationId"  data-options="required:true">
                                <option value="">-- 请选择 --</option>
                            </select>
                        </p>
                    </div>
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