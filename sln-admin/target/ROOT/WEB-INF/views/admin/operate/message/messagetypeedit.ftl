<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/messageType"/>

<style>
	.panel-fit body.panel-noscroll {
		overflow-y: scroll;
	}
</style>
<link
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/lang/zh-cn/zh-cn.js"></script>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.SLN_URL_RESOURCES}";
	
	$(function(){
	   $("#back").click(function(){
		   location.href = currentBaseUrl;
	   });
	   
	 	<#if (obj.content)??>
        UM.getEditor('myEditor').setContent(<#noescape>"${(obj.content)}"</#noescape>);
   		</#if>
	   
	   $("#add").click(function() {
			var this_ = $(this);
			if($("#addForm").form('validate')){

                var receptionType = $("#receptionType").val();
				if (receptionType == null || receptionType == "") {
					$.messager.alert('提示','请选择接收对象。');
					return;
				}
                
				this_.attr("disabled",true);
				$.messager.progress({
					text : "提交中..."
				});
				$.ajax({
					type:"POST",
					url: currentBaseUrl + "/doAdd",
					dataType: "json",
					data: $('#addForm').serialize(),
					cache:false,
					success:function(data){
						$.messager.progress('close')
						if (data.success) {
							$.messager.show({
    							title : '提示',
    							msg : <#if obj??>"编辑成功"<#else>"新增成功"</#if>,
    							showType : 'show'
    						});
							setTimeout(function(){
								location.href="${currentBaseUrl}";
							},2000);
						} else {
							this_.removeAttr("disabled");
							$.messager.alert("提示",data.message);
						}
					}
				});
			}
		});
	});
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑消息类型 <#else> 新增消息类型</#if><span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!}" name="id">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>类型名称： </label>
							<input type="text" id="typeName" name="typeName"
								class="txt w280 easyui-validatebox" missingMessage="请输入类型名称"
								data-options="required:true,validType:'length[1,20]'"
								value="${(obj.typeName)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							类型名称，20字以内
							</font>
						</p>
					</div>
					
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>编号： </label>
							<input type="text" id="typeNo" name="typeNo"
								class="txt w280 easyui-numberbox" missingMessage="请输入编号"
								data-options="required:true,min:1,precision:0,max:999999"
								value="${(obj.typeNo)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							类型编号为必填项
							</font>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>接收对象： </label>
							<@cont.select id="receptionType" value="${(obj.receptionType)!''}" codeDiv="RECEPTION_TYPE" style="width:100px" mode="1"/>
							
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>是否启用：</label>
							<@cont.radio id="state" name="state" value="${(obj.state)!''}" codeDiv="MESSAGE_IS_NORM"/>
						</p>
					</div>
					
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<ul style="margin-left: 30px;">
							<li>设置消息类型，绑定接收对象</li>
						</ul>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>

	<#include "/admin/commons/_detailfooter.ftl" />