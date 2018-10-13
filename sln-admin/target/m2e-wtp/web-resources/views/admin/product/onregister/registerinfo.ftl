<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/admin/product/"/>
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" 
	href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/css/jquery.fs.boxer.css" type="text/css">
<script type="text/javascript" 
	src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.boxer/js/jquery.fs.boxer.js"></script>

<script language="javascript">
    $(function () {
        var backUrl = "${domainUrlUtil.SLN_URL_RESOURCES}${currentBaseUrl}";
        var options = {
            url: '${domainUrlUtil.SLN_URL_RESOURCES}${currentBaseUrl}Auditing',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href=backUrl;
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                    refrushCSRFToken(data.csrfToken);
                   $.messager.alert('提示',data.message);
                }
            }
        };
        $("#back").click(function () {
            window.location.href ='${currentBaseUrl}/onregister';
        });
        $("#add").click(function () {
            //var validator = $('#addForm').validate();
            //if(validator.form()){
            if($('#addForm').form('validate')){
                $('#addForm').ajaxSubmit(options);
            }
        });
        <#if message??>$.messager.progress('close');
         $.messager.alert('提示','${message}');</#if>
         
         $(".colorbox").boxer({
     		fixed:true
     	});
    })
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">通过
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.SLN_URL_RESOURCES}${currentBaseUrl}/onregister">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <input type="hidden" name="id" value="${(ProductRegister.id)!''}"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">商品编号: </label>
                            <input type="text" id="productCode" name="productCode" value="${(ProductRegister.productCode)!''}"  class="txt w200 easyui-validatebox"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">商品名称: </label>
                            <input type="text" id="productName" name="productName" value="${(ProductRegister.productName)!''}" class="txt w200 easyui-validatebox"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">商品地址: </label>
                             <input type="text" id="productAddress" name="productAddress" value="${(ProductRegister.productAddress)!''}" class="txt w200 easyui-validatebox"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">员工姓名: </label>
                            <input type="text" id="staffName" name="staffName" value="${(ProductRegister.staffName)!''}" class="txt w200 easyui-validatebox"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">手机号码: </label>
                             <input type="text" id="phoneNumber" name="phoneNumber" value="${(ProductRegister.phoneNumber)!''}" class="txt w200 easyui-numberbox"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">创建时间: </label>
                            <input type="text" id="createTime" name="createTime" value="${(ProductRegister.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}" class="txt w200 easyui-validatebox" required
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createTime\')}'});" />
                        </p>
                    </div>
                </dd>
            </dl>
            
            <dl class="dl-group">
            <#if ProductRegister.productRegiStat??>
                <dt class="dt-group"><span class="s-icon"></span>处理结果</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                    <div style="margin:20px 0;"></div>
                      <#if ProductRegister.productRegiStat=='1'>
                 <textarea name="retroactionReason" id="retroactionReason" style="width:200px;height:60px">待处理</textarea>
                 <#elseif ProductRegister.productRegiStat=='2'>
                     <textarea name="retroactionReason" id="retroactionReason" style="width:200px;height:60px">审核通过</textarea>
                <#else>
                 <textarea name="retroactionReason" id="retroactionReason" style="width:200px;height:60px">已打回</textarea>
                 </#if>
                    </div>
                </dd>
                </#if>
            </dl>
            
             <dl class="dl-group">
              <#if ProductRegister.retroactionReason??>
                <dt class="dt-group"><span class="s-icon"></span>反馈原因</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                    <div style="margin:20px 0;"></div>
                    <textarea name="retroactionReason" id="retroactionReason" style="width:300px;height:120px">${ProductRegister.retroactionReason}</textarea>
                    </div>
                </dd>
                </#if>
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