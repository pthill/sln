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
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/parkAdvantage"/>
<script language="javascript">
    $(function () {
    <#if message??&&message!="">
        $.messager.progress('close');
        $.messager.alert('提示',"${message}");
    </#if>
        var backUrl="${currentBaseUrl}";
        $("#back").click(function () {
            window.location.href = backUrl;
        })
        $("#add").click(function () {
            $("#remark").val(um.getContent());
            if($('#addForm').form('validate')){
                var parkId=$("#parkId").val();
                if(parkId==null||parkId==""){
                    $.messager.alert('提示',"请选择园区");
                    return;
                }
                $.ajax({
                	url:'${currentBaseUrl}/create',
                	type:'POST',
                	data:$("#addForm").serialize(),
                	success:function(data){
                		if(data.success){
                			 $.messager.alert('提示','新增成功','info',function () {
                       			 window.location.href=backUrl;
                    		 });
                		}else{
                			$.messager.alert('提示',data.message);
                		}
                	}
                });
            }
        });
    });

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
            <input type="hidden" id="remark" name="remark" value="">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>标题: </label>
                            <input type="text" id="title" name="title"  class="txt w190 easyui-validatebox" missingMessage="标题必填，2-10个字符" data-options="required:true,validType:['code','length[2,10]']"/>
                        </p>
                    </div>
                    <div>
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>排序: </label>
                            <input type="text" id="order" name="order"  class="txt w190 easyui-numberbox" missingMessage="排序必填，1-5个字符" data-options="required:true,validType:'length[1,5]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>园区:</label>
                            <select name="parkId" id="parkId" class="txt w200"  data-options="required:true" >
                                <option value="" selected>请选择园区</option>
                                <#if parks??>
                                    <#list parks as park>
                                    <option value="${park.id}">${park.parkName}</option>
                                    </#list>
                                </#if>
                            </select>
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
                        <script type="text/plain" id="myEditor" style="width: 99%; height: 240px; max-height: 500px">
                        </script>
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
<script type="text/javascript">
var um = UM.getEditor('myEditor');
</script>
<#include "/admin/commons/_detailfooter.ftl" />

