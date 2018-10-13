<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operation/manager"/>
<style>
    iframe .panel-fit, .panel-fit body {
        overflow: scroll;
    }
</style>
<script language="javascript">
    $(function () {
        var backUrl="${currentBaseUrl}";
        $("#back").click(function () {
            window.location.href = backUrl;
        })

        $("#add").click(function () {
            if($('#addForm').form('validate')){
                $("#addForm").attr("action", "${currentBaseUrl}/update")
                        .attr("method", "POST")
                        .submit();
            }
        });
    });
    <#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">业务管理方新增
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <input type="hidden" name="id" id="id" value="${(manager.id)!''}"/>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item"><font class="red">*</font>编号: </label>
                            <input type="text" id="code" name="code"   value="${(manager.code)!''}" readonly class="txt w150 easyui-validatebox" missingMessage="编号必填，2-10个字符"  data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <select id="name" class="txt w200 easyui-combobox" name="name"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['OPERATION_NAME'] as code>
                                    <option value="${code.codeCd}" <#if manager??&&manager.name?string==code.codeCd>selected</#if>>${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>园区: </label>
                            <select id="parkId" name="parkId" level="0" class="w410">
                                <#if parks?? && parks?size&gt; 0>
                                    <#list parks.result as park>
                                        <option value="${park.id}" <#if park.id==manager.parkId>selected</#if>>${park.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>地址: </label>
                            <input type="text" id="address" name="address"  value="${(manager.address)!''}" class="txt w500 easyui-validatebox" missingMessage="地址必填，5-30个字符" data-options="required:true,validType:['code','length[5,30]']"  />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>公司: </label>
                            <input type="text" id="company" name="company"   value="${(manager.company)!''}"  class="txt w500 easyui-validatebox" missingMessage="公司必填，5-25个字符" data-options="required:true,validType:['code','length[5,25]']" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>介绍: </label>
                            <input type="text" id="description" name="description"  value="${(manager.description)!''}" class="txt w500 easyui-validatebox"  missingMessage="介绍必填，5-30个字符" data-options="required:true,validType:['code','length[5,30]']"/>
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