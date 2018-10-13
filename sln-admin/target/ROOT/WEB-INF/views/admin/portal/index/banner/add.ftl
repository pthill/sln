<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<script>
    var backUrl = "${currentBaseUrl}";
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
        $("#back").click(function () {
            window.location.href=backUrl;
        });
        $("#add").click(function () {
            var img=$("#bannerImg").val();
            var parkId=$("#parkId").val();
            if($("#addForm").form('validate')){
                if(img==null||img==''){
                    $.messager.alert('提示','请选择图片');
                    return;
                }
                if(parkId==null||parkId==''){
                    $.messager.alert('提示','请选择园区');
                    return;
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#bannerImg").change(function () {
            limitJpg('bannerImg');
        })
    });
</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            新增轮播
            <a class="a-back" href="${currentBaseUrl}">返回</a>
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
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <input type="text" id="name" name="name" class="txt w150 easyui-validatebox" missingMessage="名称必填，2-10个字符" data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>园区 </label>
                            <select id="parkId" name="parkId"  class="txt w200"  data-options="required:true">
                                <option value="">请选择园区</option>
                                <#if parks??>
                                    <#list parks as item>
                                        <option value="${item.id}">${item.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w150 easyui-numberbox" missingMessage="排序必填，1-3个字符" data-options="required:true,validType:'length[1,3]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>类型: </label>
                            <select id="type" class="txt w200 easyui-combobox" name="type"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['BANNER_TYPE'] as code>
                                    <option value="${code.codeCd}">${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>开始时间 </label>
                            <input type="text" id="startTime" name="startTime" class="txt w200 easyui-validatebox"  required
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>结束时间: </label>
                            <input type="text" id="endTime" name="endTime" class="txt w200 easyui-validatebox" required
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>简拼: </label>
                            <input type="text" id="abbreviation"  name="abbreviation"  class="txt w150 easyui-validatebox" missingMessage="简拼必填，1-8个字符" data-options="required:true,validType:['english','length[1,8]']"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>url: </label>
                            <input type="text" id="url" name="url" class="txt w300 easyui-validatebox" missingMessage="url必填，1-100个字符" data-options="required:true,validType:['url','length[1,100]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="bannerImg" name="bannerImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox" data-options="required:true" />
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
