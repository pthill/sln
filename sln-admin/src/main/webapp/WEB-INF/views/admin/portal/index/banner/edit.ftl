<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign subUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<script type="text/javascript">
    var backUrl = "${subUrl}";
    $(function () {
        var form={
            url:'${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index/update',
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
        $("#edit").click(function () {
            if($("#editForm").form('validate')){
                $("#editForm").ajaxSubmit(form);
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
            轮播
            <a class="a-back" href="${subUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="editForm" name="editForm" enctype="multipart/form-data" action="${subUrl}/update">
            <input type="hidden" name="index" id="index" value="${(index)!0}">
            <input type="hidden" id="id" name="id" value="${indexBanner.id}">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <input type="text" id="name" name="name"  value="${(indexBanner.name)!''}"  class="txt w150 easyui-validatebox" missingMessage="名称必填，2-10个字符" data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>简拼: </label>
                            <input type="text" id="abbreviation"  name="abbreviation" value="${(indexBanner.abbreviation)!''}"   class="txt w150 easyui-validatebox" missingMessage="简拼必填，1-8个字符" data-options="required:true,validType:['english','length[1,8]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w150 easyui-numberbox"  value="${(indexBanner.order)!''}"  missingMessage="排序必填，1-3个字符" data-options="required:true,validType:'length[1,3]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>类型: </label>
                            <select id="type" class="txt w200 easyui-combobox" name="type"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['BANNER_TYPE'] as code>
                                    <option <#if indexBanner.type??&&code.codeCd==indexBanner.type></#if> value="${code.codeCd}">${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>开始时间 </label>
                            <input type="text" id="startTime" name="startTime"  value="${(indexBanner.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" class="txt w200 easyui-validatebox"  required
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>结束时间: </label>
                            <input type="text" id="endTime" name="endTime" value="${(indexBanner.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" class="txt w200 easyui-validatebox" required
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>url: </label>
                            <input type="text" id="url" name="url"  class="txt w500 easyui-validatebox" value="${(indexBanner.url)!''}" missingMessage="url必填，1-100个字符" data-options="required:true,validType:['url','length[1,100]']""/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">当前图片:</label>
                            <#if indexBanner.img??>
                            <img src="${domainUrlUtil.SLN_IMAGE_RESOURCES}${indexBanner.img}" style="height: 150px;width: 150px">
                            </#if>
                            </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="bannerImg" name="bannerImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox"  />
                        </p>
                    </div>
                </dd>
            </dl>
            <p class="p-item p-btn">
                <input type="button" id="edit" class="btn" value="提交" />
                <input type="button" id="back" class="btn" value="返回" />
            </p>
        </@form.form>
        </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
