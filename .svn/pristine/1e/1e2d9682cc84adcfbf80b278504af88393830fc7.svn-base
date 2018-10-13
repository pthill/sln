<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operation/manager"/>
<style>
    iframe .panel-fit, .panel-fit body {
        overflow: scroll;
    }
</style>
<script language="javascript">
    $(function () {
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("OPERATION_NAME")})');
    </#noescape>
        var backUrl="${currentBaseUrl}";
        $("#back").click(function () {
            window.location.href = backUrl;
        })

        $("#add").click(function () {
            if($('#addForm').form('validate')){
                $("#addForm").attr("action", "${currentBaseUrl}/create")
                        .attr("method", "POST")
                        .submit();
            }
        });
    });
    <#if message??>$.messager.progress('close');alert('${message}');</#if>
    function statusFormat(value, row, index) {
        return codeBox["OPERATION_NAME"][value];
    }
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
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p4 p-item">
                            <label class="lab-item"><font class="red">*</font>编号: </label>
                            <input type="text" id="code" name="code"  class="txt w150 easyui-validatebox" missingMessage="编号必填，2-10个字符" data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                        <p class="p4 p-item">
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <select id="name" class="txt w200 easyui-combobox" name="name"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['OPERATION_NAME'] as code>
                                    <option value="${code.codeCd}">${code.codeText!''}</option>
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
                                        <option value="${park.id}">${park.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>地址: </label>
                            <input type="text" id="address" name="address"  class="txt w500 easyui-validatebox" missingMessage="地址必填，5-30个字符" data-options="required:true,validType:['code','length[5,30]']" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>公司: </label>
                            <input type="text" id="company" name="company" class="txt w500 easyui-validatebox" missingMessage="公司必填，5-25个字符" data-options="required:true,validType:['code','length[5,25]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>介绍: </label>
                            <input type="text" id="description" name="description"  class="txt w500 easyui-validatebox" missingMessage="介绍必填，5-30个字符" data-options="required:true,validType:['code','length[5,30]']" />
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