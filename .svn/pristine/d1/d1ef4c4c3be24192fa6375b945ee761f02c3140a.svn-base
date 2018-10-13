<#include "/admin/commons/_detailheader.ftl" />
<script src="${domainUrlUtil.SLN_URL_RESOURCES!}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/compailnregister"/>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

 var backUrl = "${currentBaseUrl}";
  $(function () {
        $("#back").click(function () {
            window.location.href=backUrl;
        });
        $("#update").click(function () {
            if($("#doForm").form('validate')){
                $("#doForm").submit();
            }
        });
    });
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">
		投诉登记<span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="${currentBaseUrl}/toUpdate" method="post" id="doForm" name="queryForm">
				<div class="fluidbox">
				  <input type="hidden" name="id" value="${(complainRegister.id)!''}"/>
				   <p class="p12 p-item">
						<label class="lab-item">投诉商家 :</label>
						<input class="easyui-validatebox txt w280" type="text"class="txt" id="complainSeller"  name="complainSeller" style="width:100px" data-options="required:true" value="${(complainRegister.complainSeller)!''}"/>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">投诉类型 :</label>
							<@cont.select id="q_complainType" codeDiv="COMPAIN_TYPE" name="complainType" style="width:100px" value="${(complainRegister.complainType)!''}"/>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">投诉用户名称:</label> 
						<input type="text" class="easyui-validatebox txt w280" id="complainPerson" name="complainPerson" style="width:100px" data-options="required:true" value="${(complainRegister.complainPerson)!''}"/>
					</p>
				</div>
				<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">详情：</label>
							 <textarea class="easyui-validatebox txt w280" name="desceinfo" id="desceinfo" style="width:300px;height:120px" data-options="required:true,validType:'length[0,300]'">${(complainRegister.desceinfo)!''}</textarea>
						</p>
					</div>
			   <p class="p-item p-btn">
                  <input type="button" id="update" class="btn" value="保存" />
                  <input type="button" id="back" class="btn" value="返回" />
               </p>
			</form>
		</div>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />