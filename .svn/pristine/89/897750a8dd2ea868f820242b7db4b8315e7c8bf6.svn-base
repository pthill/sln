<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operate/park"/>
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
        $("#province").change(function(){
            getRegion($("#city"), $(this).val(), "");
        });
        $("#add").click(function () {
            var province=$("#province").val();
            var city=$("#city").val();
            var area=$("#area").val();
            if($("#addForm").form('validate')){
                if(province==null||province==''){
                    $.messager.alert('提示','请选择省份');
                    return;
                }
                if(city==null||city==''){
                    $.messager.alert('提示','请选择城市');
                    return;
                }
                if(area==null||area==''){
                    $.messager.alert('提示','请选择区域');
                    return;
                }
                $("#addForm").ajaxSubmit(options);
            }
        });
        $("#parkImg").change(function () {
            limitJpg('parkImg');
            setImagePreviews('parkImg','imgShow',1);
        });
    });
    function getRegion(_select, _parentId, _selectId) {
        _select.empty();
        $.ajax({
            type:"get",
            url: "${domainUrlUtil.SLN_URL_RESOURCES}/admin/regions/getRegionByParentId",
            dataType: "json",
            data: {parentId: _parentId},
            cache:false,
            success:function(data){
                if (data.success) {
                    _select.empty();
                    var selectOption = '<option value ="">-- 请选择 --</option>'
                    $.each(data.data, function(i, region){
                        if (_selectId == region.id) {
                            selectOption += "<option selected='true' value=" + region.id + ">" + region.regionName + "</option>";
                        } else {
                            selectOption += "<option value=" + region.id + ">" + region.regionName + "</option>";
                        }
                    })
                    _select.append(selectOption);
                } else {

                }
            }
        });
    }

</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            编辑园区
            <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
		<@form.form  method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/update">
            <input type="hidden" name="id" id="id" value="${(park.id)!''}"/>
			<dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>编号:</label>
                            <input type="text" id="parkCode" name="parkCode" value="${(park.parkCode)!''}"
                                   class="txt w200 easyui-validatebox" missingMessage="请输入园区编号"
                                   data-options="required:true,validType:['code','length[1,10]']" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>名称: </label>
                            <input type="text" id="parkName" name="parkName" value="${(park.parkName)!''}"
                                   class="txt w200 easyui-validatebox" missingMessage="请输入园区名称"
                                   data-options="required:true,validType:['code','length[2,9]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>省份 </label>
                            <select id="province" name="province" class="w200 " data-options="required:true">
                                <option value="">请选择省份</option>
								<#if provinceList??>
									<#list provinceList as item>
                                        <option <#if park??&&park.province??&&park.province==(item.id)?string>selected</#if> value="${item.id}">${item.regionName}</option>
									</#list>
								</#if>
                            </select>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>城市</label>
                            <select id="city" name="city" class="w200 " data-options="required:true">
                                <option value="">请选择城市</option>
                                <#if cityList??>
                                    <#list cityList as region>
                                        <option  <#if park.city??&& park.city==(region.id)?string>selected</#if> value="${(region.id)!''}">${(region.regionName)!''}</option>
                                    </#list>
                                </#if>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>电话 </label>
                            <input type="text" id="tel" name="tel" class="txt w200 easyui-validatebox"   value="${(park.tel)!''}"  data-options="required:true,validType:'mobile'"  />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>地址: </label>
                            <input type="text" id="parkAddr" name="parkAddr" class="txt w400 easyui-validatebox" value="${(park.parkAddr)!''}" data-options="required:true,validType:'length[1,30]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">服务时间: </label>
                            <input type="text" id="serviceTime" name="serviceTime" value="${(park.serviceTime)!''}" class="txt w400 easyui-validatebox" data-options="required:true,validType:['code','length[1,15]']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">描述: </label>
                            <input type="text" id="remark" name="remark" value="${(park.remark)!''}" class="txt w400 easyui-validatebox" data-options="required:true,validType:['code','length[1,25]']"/>
                        </p>
                    </div>
                    <div class="fluibox">
                        <p class="p6 p-item">
                            <label class="lab-item">区域: </label>
                            <select id="area" class="txt w200" name="area" data-options="required:true">
                                <option value="">请选择区域</option>
                                <#list codeManager.codeMap['AREA_CODE'] as code>
                                    <option  <#if park.area??&&park.area==code.codeCd>selected</#if> value="${code.codeCd}">${code.codeText!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加图片: </label>
                            <input type="file" id="parkImg" name="parkImg" style="height: 21px; float: left;line-height: 30px;
							  vertical-align: middle;" missingMessage="请选择图片"
                                   class="txt w200 easyui-validatebox" />
                        </p>
                    </div>
                    <div id="imgShow" class="files" style="margin-top: 15px;margin-left: 40px;">
                        <#if park.img??>
                       <img src="${domainUrlUtil.SLN_IMAGE_RESOURCES}${park.img}" style="height: 180px;width: 180px">
                        </#if>
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
