<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/service"/>
<script language="javascript">
    $(function () {
        <#if message??&&message!="">
            $.messager.progress('close');
            $.messager.alert('提示',"${message}");
        </#if>
        var backUrl="${currentBaseUrl}";
        var options={
            url:'${currentBaseUrl}/update',
            type:'post',
            success:function (data) {
                if(data && data.data>0){
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
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $(".red").change(function () {
            var type = $("input[name='type']:checked").val();
            if (type == 1) {
                $("#hide").hide();
                $("#caidan").show();
                $("#pid option:first").prop("selected", 'selected');
            }else{
                $("#hide").show();
                $("#caidan").hide();
                $("#menuId option:first").prop("selected", 'selected');
            }
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                var menu=$("#menuId").val();
                var type = $("input[name='type']:checked").val();
                var pid=$("#pid").val();
                if(type!=1){
                    if(pid==null||pid==""){
                        $.messager.alert('提示','请选择所属服务项！');
                        return;
                    }
                }else{
                    if(menu==null||menu==""){
                        $.messager.alert('提示','请选择菜单！');
                        return;
                    }
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#serviceImg").change(function () {
            limitPNG('serviceImg');
            widthHeighCheck('serviceImg',30,30);
        });
    });


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
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/update">
            <input type="hidden" id="id" name="id" value="${service.id}">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>类型: </label>
                            <input type="radio"  name="type" value="1" class="red" disabled <#if service??&&service.type=="1">checked</#if>>服务项
                            <input type="radio"  name="type" value="2" class="red" disabled <#if service??&&service.type=="2">checked</#if>>服务类
                        </p>
                        <#if service??&&service.pid??&&service.pid==0>
                        <p class="p6 p-item" id="caidan">
                            <label class="lab-item"><font class="red">*</font>菜单:</label>
                            <select name="menuId" id="menuId" class="txt w160" data-options="required:true">
                                <option value="">请选择菜单</option>
                                <#if menu??>
                                    <#list menu as item>
                                        <option value="${item.id}" <#if service??&&service.menuId==item.id>selected</#if>>${item.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                        </#if>
                    </div>
                    <#if service??&&service.pid??&&service.pid!=0>
                    <div id="hide">
                        <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item"><font class="red">*</font>所属: </label>
                                <select name="pid" id="pid" class="txt w160" data-options="required:true">
                                    <option value="" >请选择所属服务项</option>
                                    <#if pid??>
                                        <#list pid as item>
                                            <option value="${item.id}" <#if service.pid==item.id>selected</#if>>${item.serviceName}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </p>
                            <p class="p6 p-item">
                                <label class="lab-item"><font class="red">*</font>文字高亮: </label>
                                <input type="radio"  name="highLight" value="1" <#if service.highLight??&&service.highLight=="1">checked</#if>>是
                                <input type="radio"  name="highLight" value="0" <#if service.highLight??&&service.highLight=="0">checked</#if>>否
                            </p>
                        </div>
                    </div>
                    </#if>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <input type="text" id="serviceName"  name="serviceName" value="${(service.serviceName)!''}"
                                   class="txt w150 easyui-validatebox" missingMessage="名称必填，1-10个字符"
                                   data-options="required:true,validType:['code','length[1,10]']"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>简拼: </label>
                            <#if service.abbreviation?? && service.abbreviation?split("-")?size &gt; 1>
                            <#list service.abbreviation?split("-") as name>
                                <#if name_index==0>
                                    <input type="text" value="${name}-" readonly class="txt w60 easyui-validatebox">
                                </#if>
                                <#if name_index==1>
                                    <input type="text" id="abbreviation"  name="abbreviation"  value="${name}"
                                           class="txt w90 easyui-validatebox" missingMessage="简拼必填，1-10个字符"
                                           data-options="required:true,validType:['english','length[1,10]']" />
                                </#if>
                            </#list>
                            <#else >
                                <input type="text" id="abbreviation"  name="abbreviation" value="${(service.abbreviation)!''}"
                                       class="txt w150 easyui-validatebox" missingMessage="简拼必填，1-10个字符"
                                       data-options="required:true,validType:['english','length[1,10]']"/>
                            </#if>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w150 easyui-numberbox"
                                   value="${(service.order)!''}" missingMessage="排序必填，1-5个字符"
                                   data-options="required:true,validType:'length[1,5]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>编号: </label>
                            <#if service.code?? && service.code?split("-")?size &gt; 1>
                                <#list service.code?split("-") as name>
                                    <#if name_index==0>
                                        <input type="text" value="${name}-" readonly class="txt w60 easyui-validatebox">
                                    </#if>
                                    <#if name_index==1>
                                        <input type="text" id="code"  name="code"  value="${name}"
                                               class="txt w90 easyui-validatebox" missingMessage="编号必填，1-8个字符"
                                               data-options="required:true,validType:['code','length[1,8]']" />
                                    </#if>
                                </#list>
                            <#else >
                                <input type="text" id="code"  name="code"  value="${(service.code)!''}"
                                       class="txt w150 easyui-validatebox" missingMessage="编号必填，1-8个字符"
                                       data-options="required:true,validType:['code','length[1,8]']" />
                            </#if>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">当前图片:</label>
                            <#if service.img??>
                            <img src="${domainUrlUtil.SLN_IMAGE_RESOURCES}${service.img}"style="height: 180px;width: 180px">
                            </#if>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="serviceImg" name="serviceImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox"  />
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

