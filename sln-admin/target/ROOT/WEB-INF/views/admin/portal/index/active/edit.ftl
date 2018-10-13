<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/shopActive"/>
<#assign back="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<style type="text/css">
	.img-con{
		position: relative;
		display: inline-block;
		vertical-align: middle;
        margin-left: 15px;
	}
	.img-con span{
		position: absolute;
		right: 0;
		top: 0;
		width: 30px;
		height: 30px;
		line-height: 30px;
		color: #fff;
		font-size: 14px;
		text-align: center;
		z-index: 10;
		background-color: rgba(0,0,0,0.45);
		display: none;
		cursor: pointer;
	}
	.img-con:hover:before{
		content: "";
		position: absolute;
		right: 0;
		top: 0;
		display: block;
		width: 180px;
		height: 180px;
		background-color: rgba(0,0,0,0.4);
		z-index: 5;
	}
	.img-con:hover span{
		display: block;
	}
 </style>
<script>
    var backUrl = "${back}";
    $(function () {
        var options={
            url:'${currentBaseUrl}/update',
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
        var imgForm={
            url:'${currentBaseUrl}/fileUpload',
            type:'post',
            success:function (data) {
                if(data && data.data>0){

                }else{
                    $.messager.alert('提示','上传失败','info',function () {
                    });
                }
            }
        }
        $("#back").click(function () {
            window.location.href=backUrl+"?index="+${index};
        });
        $("#add").click(function () {
            if($("#addForm").form('validate')){
                var count=$(".img-con").length;
                if(count<1){
                    $.messager.alert('提示','至少保留一张图片');
                    return;
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#fileupload").change(function () {
            limitJpg('fileupload');
            var count=$(".img-con").length;
            if(count==3){
                $.messager.alert('提示','最多只能上传3张图片');
                return;
            }
            imgCheck('fileupload','imgShow',515,551);
            var file=$("#fileupload")[0];
            if(file==null){
            }else{
                $("#addForm").ajaxSubmit(imgForm);
            }

        });
        
        $(document).on('click', '.img-con > span',function (event) {
            var src=$(".img-con img").attr("src");
            var path=src.replace("${domainUrlUtil.SLN_IMAGE_RESOURCES}","");
            $.ajax({
                type:'post',
                url:'${currentBaseUrl}/deleteImg',
                data:{'id':${shop.id},'path':path},
                dataType:'json',
                success:function (data) {
                    
                }
            });
            debugger;
            event.stopPropagation();
            $(this).parent().remove();
        });
        
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
        <@form.form  method="post" class="validForm" id="addForm" name="addForm"  action="${currentBaseUrl}/update">
            <input type="hidden" name="index" id="index" value="${(index)!0}">
            <input type="hidden" id="id" name="id" value="${shop.id}">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称:</label>
                            <input type="text" id="name" name="name" value="${shop.name}"
                                   class="txt w200 easyui-validatebox"  missingMessage="请输入名称"
                                   data-options="required:true,validType:['code','length[2,20]']" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>活动类型: </label>
                            <select id="type" class="txt w200 easyui-combobox" name="type"
                                    editable="false" data-options="required:true">
                                <#list codeManager.codeMap['ACTIVE_TYPE'] as code>
                                    <option <#if shop.type=code.codeCd>selected</#if> value="${code.codeCd}">${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>状态: </label>
                            <input type="radio"  name="state" value="0" class="red"  <#if shop??&&shop.state=="0">checked</#if>>即将开始
                            <input type="radio"  name="state" value="1" class="red"  <#if shop??&&shop.state=="1">checked</#if>>进行中
                            <input type="radio"  name="state" value="2" class="red"  <#if shop??&&shop.state=="2">checked</#if>>已结束
                        </p>
                    </div>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">当前图片:</label>
                            <div id="imgShow">
                            <#if shop.img??>
                                <#if shop.img?split(",")?size gt 0>
                                 <#list shop.img?split(",") as pic>
                                      <#if pic?length gt 0>
                                      <div class="img-con">
                                    	<img src="${domainUrlUtil.SLN_IMAGE_RESOURCES}${pic}"style="height: 180px;width: 180px">
                                    	<span>✖</span>
                                    </div>
                                      </#if>
                                </#list>
                                </#if>
                            </#if>
                            </div>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input id="fileupload" type="file"  name="fileupload" />
                        </p>
                    </div>
                    <br/>
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
