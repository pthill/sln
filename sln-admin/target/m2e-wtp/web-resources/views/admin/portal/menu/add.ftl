<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/menu"/>
<script language="javascript">
    $(function () {
        <#if message??&&message!="">
            $.messager.progress('close');
            $.messager.alert('提示',"${message}");
        </#if>
        var count=0;
        var backUrl="${currentBaseUrl}";
        $("#back").click(function () {
            window.location.href = backUrl;
        })
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                <@shiro.hasRole name="admin">
                $('input[name="parkIds"]:checked').each(function(){
                    count++;
                });
                if(count==0){
                    $.messager.alert('提示','请至少选择一个园区！');
                    return false;
                }
                </@shiro.hasRole>
                $("#addForm").attr("action", "${currentBaseUrl}/create")
                        .attr("method", "GET")
                        .submit();
            }
        });
    });
    function isCodeExist(code) {
        $.ajax({
            url:'${currentBaseUrl}/isCodeExist',
            data:'code='+code,
            type:'post',
            success:function(result){
                if(result.data>0){
                    $.messager.alert('提示','编号存在重复请更换！');
                    $("#code").val("");
                }
            }
        });
    };
    function isAbbreviationExist(abbreviation) {
        $.ajax({
            url:'${currentBaseUrl}/isAbbreviationExist',
            data:'abbreviation='+abbreviation,
            type:'post',
            success:function(result){
                if(result.data>0){
                    $.messager.alert('提示','简称存在重复请更换！');
                    $("#abbreviation").val("");
                }
            }
        });
    };
    function isOrderExist(order) {
        $.ajax({
            url:'${currentBaseUrl}/isOrderExist',
            data:'order='+order,
            type:'post',
            success:function(result){
                if(result.data>0){
                    $.messager.alert('提示','排序存在重复请更换！');
                    $("#order").val("");
                }
            }
        });
    }

</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">菜单新增
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
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>菜单名称: </label>
                            <input type="text" id="name" name="name"  class="txt w150 easyui-validatebox" missingMessage="菜单名称必填，2-10个字符" data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                    </div>
                    <div>
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" onblur="isOrderExist($(this).val())"  class="txt w150 easyui-numberbox" missingMessage="排序必填，1-3个字符" data-options="required:true,validType:'length[1,3]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>简拼: </label>
                            <input type="text" id="abbreviation" onblur="isAbbreviationExist($(this).val())" name="abbreviation"  class="txt w150 easyui-validatebox" missingMessage="简拼必填，1-10个字符" data-options="required:true,validType:['english','length[1,10]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>编号: </label>
                            <input type="text" id="code" name="code" onblur="isCodeExist($(this).val())" class="txt w150 easyui-validatebox" missingMessage="编号必填，1-10个字符" data-options="required:true,validType:['code','length[1,10]']" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>url: </label>
                            <input type="text" id="url" name="url" class="txt w500 easyui-validatebox" missingMessage="url必填，1-100个字符" data-options="required:true,validType:['url','length[1,100]']"/>
                        </p>
                    </div>
                    <@shiro.hasRole name="admin">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>园区:</label>
                            <#list parks.result as park>
                                <input type="checkbox" name="parkIds" value="${park.id}" ><span>${park.parkName!''}</span>
                            </#list>
                        </p>
                    </div>
                    </@shiro.hasRole>
                    <@shiro.hasRole name="operation">
                                  <input type="hidden" name="parkIds" value="${parkId}">
                    </@shiro.hasRole>
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

