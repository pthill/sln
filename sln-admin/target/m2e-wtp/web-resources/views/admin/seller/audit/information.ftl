<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/audit"/>
<style>
	.panel-fit body.panel-noscroll {
		overflow-y: scroll;
	}
</style>
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){
    var count=0;
    var backUrl="${currentBaseUrl}";
	$("#back").click(function(){
 		window.location.href=backUrl;
	});
    var options = {
        url: '${currentBaseUrl}/update',
        type: 'post',
        success: function (data) {
            if (data && data.data>0) {
                $.messager.alert('提示',data.message,'info',function () {
                    window.location.href=backUrl;
                });
            }else{
                $.messager.alert('提示',data.message,'info',function () {
                    window.location.href=backUrl;
                });
            }
        }
    };
	
	$("#companyProvince").change(function(){
        getRegion($("#companyCity"), $(this).val(), "");
    });

	$("#bankProvince").change(function(){
        getRegion($("#bankCity"), $(this).val(), "");
    });
});

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">查看商家详请<span class="s-poar"><a class="a-back" href="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/audit">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(sellerApply.id)!''}">
            <input type="hidden" name="parkoperation" id="parkoperation" value="">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>公司及联系人信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="company" name="company" value="${(sellerApply.company)!''}" data-options="required:true,validType:['code','length[1,30]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司详细地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="companyAdd" name="companyAdd"  value="${(sellerApply.companyAdd)!''}" data-options="required:true,validType:['code','length[1,25]']">
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司电话：</label>
							<input class="easyui-validatebox txt w280" type="text" id="telephone" name="telephone" value="${(sellerApply.telephone)!''}" data-options="required:true,validType:'phone'"  >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">传真：</label>
							<input class="easyui-validatebox txt w280" type="text" id="fax" name="fax" value="${(sellerApply.fax)!''}"  data-options="validType:'faxno'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人：</label>
							<input class="easyui-validatebox txt w280" type="text" id="legalPerson" name="legalPerson" value="${(sellerApply.legalPerson)!''}" data-options="required:true,validType:['code','length[1,10]']"  >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>联系人电话：</label>
							<input class="easyui-validatebox txt w280" type="text" id="personPhone" name="personPhone" validType="mobile" invalidMessage="手机号码格式不正确"  value="${(sellerApply.personPhone)!''}" data-options="required:true" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>邮箱：</label>
							<input class="easyui-validatebox txt w280" id="email" name="email" value="${(sellerApply.email)!''}" data-options="required:true,validType:'email'" >
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>营业执照信息（副本）</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>营业执照号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bussinessLicense" name="bussinessLicense" value="${(sellerApply.bussinessLicense)!''}" data-options="required:true,validType:['code','length[0,50]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>组织机构代码：</label>
							<input class="easyui-validatebox txt w280" type="text" id="organization" name="organization" value="${(sellerApply.organization)!''}" data-options="required:true,validType:['code','length[0,50]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>营业日期：</label>
						<input type="text" id="companyStartTime" name="companyStartTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'companyEndTime\')}'});"
								value="${(sellerApply.companyStartTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
						~
						<input type="text" id="companyEndTime" name="companyEndTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'companyStartTime\')}'});"
								value="${(sellerApply.companyEndTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
					</div>
					<br/>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>一般纳税人证明</dt>
				<dd class="dd-group">
					<!-- <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: red">
							注：所属企业具有一般纳税人证明时，此项为必填。
							</font>
						</p>
					</div>
					<br/> -->
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人身份证：</label>
							<input class="easyui-validatebox txt w280" type="text" id="legalPersonCard" validType="idcard" invalidMessage="身份证格式不正确"  name="legalPersonCard" value="${(sellerApply.legalPersonCard)!''}" data-options="required:true" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">身份证正面图片：</label>
							<a href="${domainUrlUtil.SLN_IMAGE_RESOURCES}${(sellerApply.personCardUp)!''}" target="_blank">点击查看</a>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">身份证背面图片：</label>
							<a href="${domainUrlUtil.SLN_IMAGE_RESOURCES}${(sellerApply.personCardDown)!''}" target="_blank">点击查看</a>
						</p>
					</div>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>开户银行信息(此账号为结算账号)</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行账号名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankUser" name="bankUser"  value="${(sellerApply.bankUser)!''}" data-options="required:true,validType:['code','length[2,15]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankName" name="bankName"  value="${(sellerApply.bankName)!''}" data-options="required:true,validType:['code','length[2,15]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行支行名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankNameBranch" name="bankNameBranch"  value="${(sellerApply.bankNameBranch)!''}" data-options="required:true,validType:['code','length[2,15]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行支行联行号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="brandNameCode" name="brandNameCode" invalidMessage="请输入12位支行联行号"   value="${(sellerApply.brandNameCode)!''}" data-options="required:true,validType:['code','length[12,12]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>银行账号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankCode" name="bankCode" value="${(sellerApply.bankCode)!''}" data-options="required:true,validType:'card'" >
						</p>
					</div>
					<br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>一卡通商户号：</label>
                            <input class="easyui-validatebox txt w280" type="text" id="cardMerchantNumber"  value="${(sellerApply.cardMerchantNumber)!''}" name="cardMerchantNumber" data-options="required:true,validType:['code','length[1,25]']" >
                        </p>
                    </div>
				</dd>
			</dl>
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>其他信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>税务登记证号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="taxLicense" name="taxLicense" value="${(sellerApply.taxLicense)!''}" data-options="required:true,validType:['code','length[2,25]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>结算主体：</label>
							<input class="easyui-validatebox txt w280" type="text" value="${(subjectName)!''}" " >
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			

			<#--2.batch button-------------->
			<p class="p-item p-btn">
							<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />