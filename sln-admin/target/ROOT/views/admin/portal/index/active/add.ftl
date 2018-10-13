<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/shopActive"/>
<#assign back="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<script>
    var backUrl = "${back}";
    $(function () {
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
        $("#Back").click(function () {
            window.location.href=backUrl+"?index="+${index};
        });
        $("#add").click(function () {
            if($("#addForm").form('validate')){
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#fileupload").change(function () {
            setImageSize('fileupload','imgShow',3,515,551);
        })
    });

</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            <#if index??&&index==3?string>电商-爆品<#else>电商-满减</#if>
            <a class="a-back" href="${back}?index=${index}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/create">
            <input type="hidden" name="index" id="index" value="${(index)!0}">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称:</label>
                            <input type="text" id="name" name="name"
                                   class="txt w200 easyui-validatebox" missingMessage="请输入名称"
                                   data-options="required:true,validType:['code','length[2,20]']" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>活动类型: </label>
                            <select id="type" class="txt w200 easyui-combobox" name="type" readonly="readonly"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['ACTIVE_TYPE'] as code>
                                    <option value="${code.codeCd}" <#if index==3?string&&code.codeCd?string==2?string>selected</#if>>${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片:</label>
                            <input id="fileupload" type="file" name="fileupload"  multiple="multiple">
                        </p>
                    </div>
                    <div id="imgShow" class="files" style="margin-top: 15px;margin-left: 30px;"></div>
                    <br/>
                </dd>
            </dl>
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="提交" />
                <input type="button" id="Back" class="btn" value="返回" />
            </p>
        </@form.form>
       </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
