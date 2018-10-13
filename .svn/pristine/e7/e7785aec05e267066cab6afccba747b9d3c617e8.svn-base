<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/settlement"/>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

<script type="text/javascript">
	$(function() {
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});
	});
	
</script>

<div class="wrapper">
	<div class="formbox-a" style="height:300px;">
		<h2 class="h2-title">
			发票上传页面
			</span>
		</h2>
		<#--1.addForm----------------->
		<div class="form-contbox">
		    <@form.form class="validForm" id="addForm" name="addForm" method="post" action="${currentBaseUrl}/doUploadImage" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(id)!''}">
			<dl class="dl-group">
				<dt class="dt-group">
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">上传发票凭证:
							</label>
						      <input type="file" id="douploadfile" name="douploadfile" missingMessage="请选择要上传的凭证" class="txt w240 easyui-validatebox" data-options="required:true"/>
						</p>
					</div>
				</dd>
			</dl>
			<p class="p-item p-btn">
				    <input type="submit"  class="btn" value="上传" /> 
				    <input type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
