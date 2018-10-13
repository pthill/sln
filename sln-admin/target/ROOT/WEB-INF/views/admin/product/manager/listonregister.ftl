<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/product/onregister"/>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("PRODUCT_REGI_STAT")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
              if(selectedCode.productRegiStat !=1){
                $.messager.alert('提示','只有[待处理]的记录才可以审核通过。');
                return;
            }
            window.location.href="${currentBaseUrl}/pass?id="+selectedCode.id;
        });

        $("#a-gridwark").click(function(){
           var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.productRegiStat !=1){
                $.messager.alert('提示','只有[待处理]的记录才可以打回。');
                return;
            }
            window.location.href="${currentBaseUrl}/drawback?id="+selectedCode.id;
        });
        
        $("#a-gridRemoe").click(function(){
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('确认', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}del",
                        dataType: "json",
                        data: "id="+selectedCode.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                //$('#dataGrid').datagrid('deleteRow',selectedIndex);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    })

    function typeFormat(value,row,index){
        return codeBox["PRODUCT_REGI_STAT"][value];
    }
    
    //操作
    function handleFormat(value, row, index) {
        var html = "";
        html += "<a href='${currentBaseUrl}/registerinfo?id="+row.id+"'>查看详情</a>";
        return html;
    }
    
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">京东商品缺货列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                
                    <p class="p4 p-item">
                        <label class="lab-item">员工编号 :</label>
                        <input type="text" class="txt" id="q_staffId" name="q_staffId" value="${q_staffId!''}"/>
                    </p>
                    
                    <p class="p4 p-item">
                        <label class="lab-item">员工姓名 :</label>
                        <input type="text" class="txt" id="q_staffName" name="q_staffName" value="${q_staffName!''}"/>
                    </p>
                    
                   <p class="p4 p-item">
						<label class="lab-item">创建时间：</label>
						 <input type="text" id="q_createTime" name="q_createTime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt" />
					</p>
					
                    <p class="p4 p-item">
                        <label class="lab-item">处理结果 :</label>
                        <@cont.select id="q_productRegiStat" codeDiv="PRODUCT_REGI_STAT" style="width:80px"/>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
                ,singleSelect:true
                ,autoRowHeight:false
                ,fitColumns:true
                ,toolbar:'#gridTools'
                ,striped:true
                ,pagination:true
                ,pageSize:'${pageSize}'
                ,fit:true
                ,url:'${currentBaseUrl}/list'
                ,queryParams:queryParamsHandler()
                ,onLoadSuccess:dataGridLoadSuccess
                ,method:'get'">
        <thead>
        <tr>
            <th field="productCode" width="60" align="center">商品编号</th>
            <th field="productName" width="120" align="center">商品名称</th>
            <th field="productAddress" width="150" align="left" halign="center">商品地址</th>
            <th field="phoneNumber" width="60" align="center">手机号码</th>
            <th field="staffId" width="120" align="center">员工编号</th>
            <th field="staffName" width="120" align="center">员工姓名</th>
            <th field="productRegiStat" width="120" align="center" formatter="typeFormat">处理结果</th>
            <th field="createTime" width="120" align="center">创建时间</th>
            <th field="updateTime" width="120" align="center">处理时间</th>
            <th field="handler" width="120" align="center" formatter="handleFormat">操作</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
    <#--请求到Controller相应的方法add---------------->
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		
		
		<@shiro.hasPermission name="/admin/product/onregister/pass">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">通过</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/product/onregister/drawback">
        <a id="a-gridwark" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">打回</a>
		</@shiro.hasPermission>
        <div>
        </div>

<#include "/admin/commons/_detailfooter.ftl" />