<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#back").click(function(){
	 		window.location.href="${domainUrlUtil.SLN_URL_RESOURCES}/admin/searchrecord";
		});
	$("#add").click(function(){
			if($("#addForm").form('validate')){
		 		$("#addForm").attr("action", "${domainUrlUtil.SLN_URL_RESOURCES}/admin/searchrecord/create")
	  				 .attr("method", "POST")
	  				 .submit();
	  		}
		});
	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">模糊搜索词新加<span class="s-poar"><a class="a-back" href="${domainUrlUtil.SLN_URL_RESOURCES}/admin/searchrecord">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>模糊搜索词: </label>
							<input type="text" id="keyword" name="keyword" value="${(searchRecord.keyword)!''}" class="easyui-validatebox" missingMessage="模糊搜索词必须填写，输入2到6个字符" data-options="required:true,validType:'length[2,6]'"/>
						</p>
					</div>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">
							</label>
								&nbsp;&nbsp;&nbsp;&nbsp;第一次添加模糊搜索词是自动计算出索引数量。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;以后每天凌晨4点更新模糊搜索词的索引数量。
						</p>
					</div>
				</dd>
			</dl>
			
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交"/>
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />