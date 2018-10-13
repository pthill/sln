<#include "/admin/commons/_detailheader.ftl" />
<script src="${domainUrlUtil.SLN_URL_RESOURCES!}/resources/admin/jslib/js/jquery.form.js"></script>
<link href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/themes/default/css/umeditor.css"
        type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.js"></script>
<script type="text/javascript"
        src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/umeditor/lang/zh-cn/zh-cn.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/eliminate"/>
<script language="javascript">
 var backUrl = "${currentBaseUrl}";
  $(function () {
        $("#back").click(function () {
            window.location.href=backUrl;
        });
        $("#add").click(function () {
            if($("#addForm").form('validate')){
            
                $("#addForm").submit();
            }
        });
    });
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">商户机制参数设置
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
        <div class="form-contbox">
        <@form.form  method="post" class="validForm" id="addForm" name="addForm" action="${currentBaseUrl}/create.html">
            <dl class="dl-group">
                <dt class="dt-group">
                    <span class="s-icon"></span>基本信息
                </dt>
                <dd class="dd-group">
                <div class="fluidbox">
                        <p class="p3 p-item">
                            <label class="lab-item">&nbsp;&nbsp;&nbsp</label>
                        </p>
                        <p class="p3 p-item">
                            <label class="lab-item">提示值</label>
                        </p>
                         <p class="p3 p-item">
                            <label class="lab-item">警告值</label>
                        </p>
                         <p class="p3 p-item">
                            <label class="lab-item">淘汰值</label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p3 p-item">
                            <label class="lab-item">完成订单量</label>
                            <input type="hidden" id="lim_id" name="lim_id" value="1"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="onetipValue" name="onetipValue" value="${one.tipValue}" class="txt w200 easyui-numberbox" missingMessage="请输入相应的提示值"
                                   data-options="required:true"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="onewarnValue1" name="onewarnValue" value="${one.warnValue}"
                                   class="txt w200 easyui-numberbox"  missingMessage="请输入相应的警告值"
                                   data-options="required:true" validType="comparewith['onetipValue']"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="oneeliminateValue" name="oneeliminateValue"  value="${one.eliminateValue}" class="txt w200 easyui-numberbox"  missingMessage="请输入相应的淘汰值"
                                   data-options="required:true" validType="comparewith['onewarnValue1']"/>
                        </p>
                    </div>
                  <div class="fluidbox">
                        <p class="p3 p-item">
                            <label class="lab-item">综合评分</label>
                             <input type="hidden" id="lim_id" name="lim_id" value="2"/>
                        </p>
                         <p class="p3 p-item">
                            <input type="text" id="twotipValue1" name="onetipValue" value="${two.tipValue}" class="txt w200 easyui-numberbox" missingMessage="请输入相应的提示值"
                                   data-options="required:true" validType="rangvalue"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="twowarnValue2" name="onewarnValue" value="${two.warnValue}"
                                   class="txt w200 easyui-numberbox"  missingMessage="请输入相应的警告值"
                                   data-options="required:true,validType:['rangvalue','comparewith[\'twotipValue1\']']" />
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="twoeliminateValue" name="oneeliminateValue" value="${two.eliminateValue}" class="txt w200 easyui-numberbox"  missingMessage="请输入相应的淘汰值"
                                   data-options="required:true,validType:['rangvalue','comparewith[\'twowarnValue2\']']"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p3 p-item">
                            <label class="lab-item">投诉</label>
                            <input type="hidden" id="lim_id" name="lim_id" value="3"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="threetipValue1" name="onetipValue" value="${three.tipValue}" class="txt w200 easyui-numberbox" missingMessage="请输入相应的提示值"
                                   data-options="required:true"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="threewarnValue2" name="onewarnValue" value="${three.warnValue}"
                                   class="txt w200 easyui-numberbox"  missingMessage="请输入相应的警告值"
                                   data-options="required:true" validType="compareto['threetipValue1']"/>
                        </p>
                        <p class="p3 p-item">
                            <input type="text" id="threeeliminateValue" name="oneeliminateValue" value="${three.eliminateValue}" class="txt w200 easyui-numberbox"  missingMessage="请输入相应的淘汰值"
                                   data-options="required:true" validType="compareto['threewarnValue2']"/>
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