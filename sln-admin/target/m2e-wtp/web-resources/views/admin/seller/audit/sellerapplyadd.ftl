<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/audit"/>
<style>
	.panel-fit body.panel-noscroll {
		overflow-y: scroll;
	}
</style>
<script language="javascript">
$(function(){
    var count=0;
    $("#add").removeAttr('disabled');
    var backUrl = "${currentBaseUrl}";
	$("#back").click(function(){
 		window.location.href=backUrl;
	});
    var options = {
        url: '${currentBaseUrl}/create',
        type: 'post',
        success: function (data) {
            if (data && data.data>0) {
                $.messager.alert('提示',data.message,'info',function () {
                    window.location.href=backUrl;
                });
            }else{
                $.messager.alert('提示',data.message,'info',function () {
                	$('#add').removeAttr("disabled");
                    //window.location.href=backUrl;
                });
            }
        }
    };
	$("#add").click(function(){
	    var array=[];
		var companyProvince = $("#companyProvince").val();
		var companyCity = $("#companyCity").val();
		if (companyProvince == null || companyProvince == "" || companyCity == null || companyCity == "") {
			$.messager.alert('提示','请选择公司所在地！');
			return false;
		}
		var bankProvince = $("#bankProvince").val();
		var bankCity = $("#bankCity").val();
		if (bankProvince == null || bankProvince == "" || bankCity == null || bankCity == "") {
			$.messager.alert('提示','请选择开户行所在地！');
			return false;
		}
		<#list parkList.result as park>
			$('input[name="operationId_${park.id}"]:checked').each(function(){
				count++;
				var temp = {parkName:"${park.parkName}",parkId : ${park.id}, operationId :$(this).val(),operationName:$(this).next().html()};
				array.push(temp);
			});
		</#list>

        if(count==0){
            $.messager.alert('提示','请至少选择一个业务管理方！');
            return false;
        }
        console.log("新的json数组:"+JSON.stringify(array));
        $("#parkoperation").val(JSON.stringify(array));
        if($('#addForm').form('validate')){
            $("#add").attr("disabled","disabled");
            $('#addForm').ajaxSubmit(options);
        }
	});
	
	$("#companyProvince").change(function(){
        getRegion($("#companyCity"), $(this).val(), "");
    });

	$("#bankProvince").change(function(){
        getRegion($("#bankCity"), $(this).val(), "");
    });

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

	function getRegion(_select, _parentId, _selectId) {
	    _select.empty();
	    $.ajax({
	        type:"get",
	        url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/regions/getRegionByParentId",
	        dataType: "json",
	        data: {parentId: _parentId},
	        cache:false,
	        success:function(data){
	            if (data.success) {
	                _select.empty();
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                $.each(data.data, function(i, region){
	                    if (_selectId == region.id) {
	                        selectOption += "<option selected='true' value=" + region.id + ">" + region.regionName + "</option>";
	                    } else {
	                        selectOption += "<option value=" + region.id + ">" + region.regionName + "</option>";
	                    }
	                })
	                _select.append(selectOption);
	            } else {

	            }
	        }
	    });
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加商家申请<span class="s-poar"><a class="a-back" href="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/audit">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form  method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			    <input type="hidden" name="parkoperation" id="parkoperation" value="">
				<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>公司及联系人信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="company" name="company"  value="${(sellerApply.company)!''}" missingMessage="公司名称必填，1-30个字符"  data-options="required:true,validType:['code','length[1,30]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司所在地：</label>
							<select class="" id="companyProvince" name="companyProvince">
								<option value="">-- 请选择 --</option>
								<#if provinceList ??>
		               			<#list provinceList as region>
		                   			<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		           				</#if>
		       				</select>
		       				<select class="" id="companyCity" name="companyCity">
								<option value="">-- 请选择 --</option>
								<#if cityList ??>
		                 		<#list cityList as region>
		                     	<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		             			</#if>
		         			</select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司详细地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="companyAdd" name="companyAdd" value="${(sellerApply.companyAdd)!''}" missingMessage="公司详细地址必填，1-25个字符" data-options="required:true,validType:['code','length[1,25]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司电话：</label>
							<input class="easyui-validatebox txt w280" type="text" id="telephone" name="telephone"  value="${(sellerApply.telephone)!''}" missingMessage="公司电话必填" data-options="required:true,validType:'phone'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">传真：</label>
							<input class="easyui-validatebox txt w280" type="text" id="fax" name="fax" value="${(sellerApply.fax)!''}" missingMessage="传真必填" data-options="required:true,validType:'faxno'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人：</label>
							<input class="easyui-validatebox txt w280" type="text" id="legalPerson" name="legalPerson"  value="${(sellerApply.legalPerson)!''}" missingMessage="法定代表人必填，1-10个字符" data-options="required:true,validType:['code','length[1,10]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>联系人电话：</label>
							<input class="easyui-numberbox txt w280" type="text" id="personPhone" name="personPhone" value="${(sellerApply.personPhone)!''}" missingMessage="联系人电话必填"  data-options="required:true,validType:'mobile'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>邮箱：</label>
							<input class="easyui-validatebox txt w280" id="email" name="email"  value="${(sellerApply.email)!''}" missingMessage="邮箱必填"  data-options="required:true,validType:'email'" >
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
							<input class="easyui-validatebox txt w280" type="text" id="bussinessLicense" name="bussinessLicense" value="${(sellerApply.bussinessLicense)!''}" missingMessage="营业执照号必填，0-50个字符" data-options="required:true,validType:['code','length[0,50]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>组织机构代码：</label>
							<input class="easyui-validatebox txt w280" type="text" id="organization" name="organization" value="${(sellerApply.organization)!''}" missingMessage="组织机构代码必填，0-50个字符"  data-options="required:true,validType:['code','length[0,50]']" >
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
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>营业执照扫描件：</label>
							<input type="file" id="up_bussinessLicenseImage" name="up_bussinessLicenseImage"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请确保图片清晰，文字可辨并有清晰的红色公章。
							</font>
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>一般纳税人证明</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人身份证：</label>
							<input class="easyui-numberbox txt w280" validType="idcard" invalidMessage="身份证格式不正确" type="text" id="legalPersonCard" name="legalPersonCard" value="${(sellerApply.legalPersonCard)!''}" data-options="required:true" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>身份证正面图片：</label>
							<input type="file" id="up_personCardUp" name="up_personCardUp"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>身份证背面图片：</label>
							<input type="file" id="up_personCardDown" name="up_personCardDown"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请确保图片清晰。
							</font>
						</p>
					</div>
					<br/>
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
							<input class="easyui-validatebox txt w280" type="text" id="bankNameBranch" name="bankNameBranch"   value="${(sellerApply.bankNameBranch)!''}" data-options="required:true,validType:['code','length[2,15]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行支行联行号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="brandNameCode" invalidMessage="请输入12位支行联行号" name="brandNameCode"  value="${(sellerApply.brandNameCode)!''}" data-options="required:true,validType:['code','length[12,12]']" >
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
							<label class="lab-item"><font class="red">*</font>开户行所在地：</label>
							<select class="" id="bankProvince" name="bankProvince">
								<option value="">-- 请选择 --</option>
								<#if provinceList ??>
		               			<#list provinceList as region>
		                   			<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		           				</#if>
		       				</select>
		       				<select class="" id="bankCity" name="bankCity">
								<option value="">-- 请选择 --</option>
								<#if cityList ??>
		                 		<#list cityList as region>
		                     	<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		             			</#if>
		         			</select>
						</p>
					</div>
					<br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>一卡通商户号：</label>
                            <input class="easyui-validatebox txt w280" type="text" id="cardMerchantNumber" name="cardMerchantNumber" data-options="required:true,validType:['code','length[1,25]']" >
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
							<input class="easyui-validatebox txt w280" type="text" id="taxLicense" name="taxLicense" value="${(sellerApply.taxLicense)!''}" missingMessage="税务登记号必填，2-25个字符" data-options="required:true,validType:['code','length[2,25]']" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商家账号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="userName" name="userName"  value="${(userName)!''}" missingMessage="商家账号必填，3-16个字符"  data-options="required:true,validType:['code','length[3,16]']" >
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							此账号为日后登录并管理商家中心时使用，密码默认123456，请及时通知商家修改此密码。
							</font>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>店铺名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="sellerName" name="sellerName" value="${(sellerName)!''}" missingMessage="店铺名称必填，2-20个字符"  data-options="required:true,validType:['code','length[2,20]']" >
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							店铺名称注册后不可修改，请认真填写。
							</font>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<#list parkList.result as park>
                        <p class="p12 p-item">
                           <label class="lab-item"><font class="red">*</font>${park.parkName}:</label>
								<#list park.operationNames?split(",") as name>
									<#list codeManager.codeMap['OPERATION_NAME'] as code>
										<#if name==code.codeCd>
                                            <input type="checkbox" name="operationId_${park.id}" value="${name}"><span>${code.codeText!''}</span>
										</#if>
									</#list>
								</#list>
                        </p>
						</#list>
					</div>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />