<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/member/welfareSend"/>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">
            福利发放详情
            <a class="a-back" href="${currentBaseUrl}">返回</a>
        </h2>
        <div data-options="region:'center'" border="false">
            <table id="dataGrid" class="easyui-datagrid" data-options="rownumbers:true">
                <thead>
                <tr>
                    <th field="staffNo" width="200" align="left">员工号</th>
                    <th field="name" width="150" align="left">姓名</th>
                    <th field="tel" width="100" align="left" >手机号</th>
                    <th field="money" width="100" align="center" >发放额度</th>
                    <th field="birthday" width="100" align="center" >出生日期</th>
                    <th field="startTime" width="200" align="left">积分生效日期</th>
                    <th field="endTime" width="200" align="left">积分失效日期</th>
                </tr>
                </thead>
                <tbody>
                <#if memberWelfareSend??&& (memberWelfareSend.children?size>0)>
                    <#list memberWelfareSend.children as item>
                    <tr>
                        <td>${item.staffNo!}</td>
                        <td>${item.name!}</td>
                        <td>${item.tel}</td>
                        <td>${item.money!}</td>
                        <td>${item.birthday}</td>
                        <td>
                        ${item.startTime}</td>
                        <td>
                            <#if item.endTime??>
					    ${item.endTime}
					    <#else >
                                不限时间
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

        </div>
    </div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />