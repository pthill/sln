<#include "/admin/commons/_detailheader.ftl" />
<script src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/member/welfareSend"/>
<script>
    var backUrl = "${currentBaseUrl}";
    $(function () {
        var options = {
            url: '${currentBaseUrl}/add',
            type: 'post',
            success: function (data) {
                if (data && data.data>0) {
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
        $("#add").click(function(){
            $('#addForm').ajaxSubmit(options);
        });
        $("#back").click(function () {
            window.location.href=backUrl;
        })
        $("#fileUpload").change(function () {
            importExcelTest('fileUpload');
        })
    });
    function importExcelTest(fileId) {
        var tmpFile = document.getElementById(fileId);
        if(tmpFile.files&&tmpFile.files[0]){
            if (!/\.(xls|xlsx)$/.test(tmpFile.value)) {
                $.messager.alert('提示','请上传execl文件');
                tmpFile.value = "";
                return false;
            }
        }else{
            $.messager.alert('提示','请上传文件');
            return false;
        }
    }
</script>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            上传文件
            <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data" action="${currentBaseUrl}/add">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                    <#if m??>
                        <input type="hidden" id="pid" name="pid" value="${m.id}">
                    <#else >
                        <input type="hidden" id="pid" name="pid" value="0">
                    </#if>
                    <div class="fluidbox">
                        <p class="p8 p-item" >
                            <label style="float: left;margin-left: 80px;">
                           请根据模板填写对应数据，以便上传，请文件会覆盖源文件：下载模板：<a style="color: #ed6f05" href="${currentBaseUrl}/downModel">点击下载</a>
                            </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>添加文件: </label>
                            <input type="file" id="fileUpload" name="fileUpload"
                                   class="txt w200"  multiple="multiple"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商家</label>
                            <select id="sellerId" name="sellerId"  class="txt w200"  data-options="required:true">
                                <option value="">请选择商家</option>
                                <#if sellers?? && sellers?size gt 0>
                                    <#list sellers as seller>
                                        <option value="${seller.id}">${seller.sellerName}</option>
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
