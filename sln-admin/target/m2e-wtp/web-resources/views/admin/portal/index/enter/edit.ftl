<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<#assign submitUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/enter"/>
<script>
    var backUrl = "${currentBaseUrl}";
    $(function () {
        <#if message??&&message!="">
            $.messager.progress('close');
            $.messager.alert('提示',"${message}");
        </#if>
        $("#back").click(function () {
            window.location.href=backUrl+"?index="+${index};
        });
        $("#add").click(function () {
            var parkId=$("#parkId").val();
            var url=$("#url").val();
            if(parkId==null||parkId==''){
                $.messager.alert('提示','请选择园区');
                return;
            }
            if(url==null||url==''){
                $.messager.alert('提示','请选择链接');
                return;
            }
            if($("#addForm").form('validate')){
                $("#addForm").submit();
            }
        });
    });

</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            快速入口
            <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="addForm" name="addForm" action="${submitUrl}/update">
            <input type="hidden" id="id" name="id" value="${enter.id}">
            <input type="hidden" name="index" id="index" value="${(index)!0}">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称:</label>
                            <input type="text" id="name" name="name" value="${enter.name}"
                                   class="txt w200 easyui-validatebox" missingMessage="请输入名称"
                                   data-options="required:true,validType:['code','length[2,10]']" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w200 easyui-numberbox" value="${enter.order}" missingMessage="请输入排序"
                                   data-options="required:true,validType:'length[1,5]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>园区 </label>
                            <select id="parkId" name="parkId"  class="txt w200"  data-options="required:true">
                                <option value="">请选择园区</option>
                                <#if parks??>
                                    <#list parks as item>
                                        <option <#if enter.parkId?? && enter.parkId==item.id>selected</#if> value="${item.id}">${item.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>链接</label>
                            <select id="url" name="url"  class="txt w200"  data-options="required:true">
                                <option value="">请选择链接</option>
                                <#if service??>
                                    <#list service as item>
                                        <option <#if enter.url??&&enter.url==item.id>selected</#if> value="${item.id}">${item.serviceName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                    </div>
                </dd>
            </dl>
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="提交" />
                <input type="button" id="back" class="btn" value="返回" />
            </p>
        </@form.form>
        </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
