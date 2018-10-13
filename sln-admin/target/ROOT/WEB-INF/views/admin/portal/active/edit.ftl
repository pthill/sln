<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<link href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/themes/default/css/umeditor.css"
      type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.js"></script>
<script type="text/javascript"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/lang/zh-cn/zh-cn.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/active"/>
<style>
    .dl-group p img {
        max-width: 120px;
        float: left;
    }
    .formbox-a .lab-item {
        float: left;
        width: 120px;
        text-align: right;
        margin-right: 3px;
        display: inline;
        padding-top: 5px;
    }
    .panel-fit body.panel-noscroll {
        overflow-y: scroll;
    }
</style>
<script>
    var backUrl = "${currentBaseUrl}";
    $(function () {
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
            window.location.href=backUrl;
        });
        $("#add").click(function () {
            $("#remark").val(um.getContent());
            var parkId=$("#parkId").val();
            if($("#addForm").form('validate')){
                if(parkId==null||parkId==''){
                    $.messager.alert('提示','请选择园区');
                    return;
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#activeImg").change(function () {
            limitJpg('activeImg');
            widthHeighCheck('activeImg',300,300);
        })
    });

</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            编辑活动
            <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/create">
            <input type="hidden" id="remark" name="remark" value="">
            <input type="hidden" id="id" name="id" value="${(active.id)!''}">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>活动标题:</label>
                            <input type="text" id="title" name="title" value="${(active.title)!''}"
                                   class="txt w200 easyui-validatebox" missingMessage="请输入活动标题"
                                   data-options="required:true,validType:['code','length[2,25]']" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order" class="txt w200 easyui-numberbox" value="${(active.order)!''}" missingMessage="请输入排序"
                                   data-options="required:true,validType:'length[1,5]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>开始时间 </label>
                            <input type="text" id="startTime" name="startTime" class="txt w200 easyui-validatebox"  required value="${(active.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>结束时间: </label>
                            <input type="text" id="endTime" name="endTime" class="txt w200 easyui-validatebox" required value="${(active.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>园区 </label>
                            <select id="parkId" name="parkId" class="w200 " class="txt w200" data-options="required:true">
                                <option value="">请选择园区</option>
                                <#if parks??>
                                    <#list parks as item>
                                        <option <#if active??&&item.id=active.parkId>selected</#if> value="${item.id}">${item.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">地址: </label>
                            <input type="text" id="address" name="address" value="${(active.address)!''}" class="txt w300 easyui-validatebox" data-options="required:true,validType:['code','length[1,35]']" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">简介: </label>
                            <input type="text" id="description"  name="description" value="${(active.description)!''}" class="txt w200 easyui-validatebox" data-options="required:true,validType:['code','length[5,25]']"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="activeImg" name="activeImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox"  />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">当前图片:</label>
                            <#if active.img??>
                                <img src="${domainUrlUtil.SLN_IMAGE_RESOURCES}${active.img}"style="height: 180px;width: 180px">
                            </#if>
                        </p>
                    </div>
                </dd>
            </dl>
        <dl class="dl-group">
            <dt class="dt-group">
                <span class="s-icon"></span>内容编辑
            </dt>
        <dd class="dd-group">
        <div class="fluidbox">
            <script type="text/plain" id="myEditor" style="width: 99%; height: 240px; max-height: 600px">
                <#if active??>
				<#noescape>
                ${(active.remark)!''}
                </#noescape>
				</#if>
            </script>
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
<script type="text/javascript">
    var um = UM.getEditor('myEditor');
</script>
<#include "/admin/commons/_detailfooter.ftl" />
