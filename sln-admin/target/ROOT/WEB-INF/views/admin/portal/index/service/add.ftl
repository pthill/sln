<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/recommendService"/>
<#assign backUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<script language="javascript">
    $(function () {
        var backUrl="${backUrl}";
        var options={
            url:'${currentBaseUrl}/create',
            type:'post',
            success:function (data) {
                if(data && data.data>0){
                    $.messager.alert('提示',data.message,'info',function () {
                        window.location.href=backUrl+"?index="+${index};
                    });
                }else{
                    $.messager.alert('提示',data.message,'info',function () {
                        window.location.href=backUrl+"?index="+${index};
                    });
                }
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl+"?index="+${index};
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                var parkId=$("#parkId").val();
                var fwx=$("#fwx").val();
                var serviceId=$("#serviceId").val();
                if(parkId==null||parkId==""){
                    $.messager.alert('提示','请选择园区！');
                    return;
                }
                if(fwx==null||fwx==""){
                    $.messager.alert('提示','请选择服务项！');
                    return;
                }
                if(serviceId==null||serviceId==""){
                    $.messager.alert('提示','请选择服务类！');
                    return;
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#parkId").change(function(){
            getFwx($("#fwx"),$(this).val());
        });
        $("#fwx").change(function () {
            getFwl($("#serviceId"),$(this).val());
        });
        $("#serviceImg").change(function () {
            limitPNG('serviceImg');
            widthHeighCheck('serviceImg',30,30);
        });
    });
    function getFwx(_select,parkId){
        _select.empty();
        $.ajax({
            type:"post",
            url: "${currentBaseUrl}/getFwx",
            dataType: "json",
            data:"parkId="+parkId,
            cache:false,
            success:function(data){
                if (data.success) {
                    _select.empty();
                    var selectOption = '<option value ="">-- 请选择 --</option>'
                    $.each(data.data, function(i, service){
                        selectOption += "<option value=" + service.id + ">" + service.serviceName + "</option>";
                    })
                    _select.append(selectOption);
                } else {

                }
            }
        });
    }
    function getFwl(_select,fwx){
        _select.empty();
        $.ajax({
            type:"post",
            url: "${currentBaseUrl}/getFwl",
            dataType: "json",
            data:"fwx="+fwx,
            cache:false,
            success:function(data){
                if (data.success) {
                    _select.empty();
                    var selectOption = '<option value ="">-- 请选择 --</option>'
                    $.each(data.data, function(i, service){
                        selectOption += "<option value=" + service.id + ">" + service.serviceName + "</option>";
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
        <h2 class="h2-title">服务新增
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/create">
            <input type="hidden" name="index" id="index" value="${(index)!0}">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>园区: </label>
                            <select name="parkId" id="parkId" class="txt w160" data-options="required:true">
                                <option value="">请选择园区</option>
                                <#if parks??>
                                    <#list parks as item>
                                        <option value="${item.id}">${item.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w150 easyui-numberbox" missingMessage="排序必填，1-5个字符" data-options="required:true,validType:'length[1,5]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>服务项: </label>
                            <select name="fwx" id="fwx" class="txt w160" data-options="required:true">
                                <option value="">请选择服务项</option>
                            </select>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>服务类: </label>
                            <select name="serviceId" id="serviceId" class="txt w160" data-options="required:true">
                                <option value="">请选择服务类</option>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="serviceImg" name="serviceImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox" data-options="required:true" />
                        </p>
                    </div>
                </dd>
            </dl>
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="提交"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />

