<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/messageManager"/>

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
		   location.href = currentBaseUrl + "?type=0";
	   });
	   
	   $("#add").click(function() {
			var this_ = $(this);
			if($("#addForm").form('validate')){

                var messageTypeId = $("#messageTypeId").val();
				if (messageTypeId == null || messageTypeId == "") {
					$.messager.alert('提示','请选择消息类型。');
					return;
				}
				
				var messageCode = $("#messageCode").val();
				if (messageCode == null || messageCode == "") {
					$.messager.alert('提示','消息标识不能为空。');
					return;
				}
				
				$.ajax({
					url: currentBaseUrl + "/getMessageByMessageCode",
					data:{"messageCode":messageCode},
					async: true,
					success:function(data){
						if(!data.success && $("input[name='id']").val() == ''){
							$.messager.alert("提示","消息标识已经存在！");
							return;
						}else{
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
											location.href="${currentBaseUrl}?type=0";
										},2000);
									} else {
										this_.removeAttr("disabled");
										$.messager.alert("提示",data.message);
									}
								}
							});
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
			<#if obj??> 编辑消息 <#else> 新增消息</#if><span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}?type=0">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm"
			action="${currentBaseUrl}/doAdd"> 
			<input type="hidden" value="${(obj.id)!}" name="id">
			<input type="hidden" value="0" name="isMessageTemplate">
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>标题： </label>
							<input type="text" id="title" name="title"
								class="txt w280 easyui-validatebox" missingMessage="请输入消息标题"
								data-options="required:true,validType:'length[1,32]'"
								value="${(obj.title)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请输入1~32位消息标题。
							</font>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>消息类型： </label>
							<select id="messageTypeId" name="messageTypeId" level="0" class="w210">
								<#if messageTypeList?? && messageTypeList?size&gt; 0>
	                                    <#list messageTypeList as messageType>
	                                        <option value="${messageType.id}" <#if obj?? && obj.messageTypeId == messageType.id> selected="selected"</#if> >${messageType.typeName}</option>
	                                    </#list>
	                            </#if>
	                        </select>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>唯一标识： </label>
							<input type="text" id="messageCode" name="messageCode"
								class="txt w280 easyui-validatebox" missingMessage="请输入唯一标识"
								data-options="required:true,validType:'length[1,10]'"
								<#if obj?? && obj.messageCode??>readonly="readonly"</#if>
								value="${(obj.messageCode)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请输入最多10位字符的唯一标识
							</font>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>内容： </label>
							
							<textarea name="content" rows="5" cols="100" id="content" class="{maxlength:255}" >${(obj.content)!''}</textarea>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请输入要发送消息的内容，最多255字。
							</font>
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
							<li>请先添加消息，在推送。</li>
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