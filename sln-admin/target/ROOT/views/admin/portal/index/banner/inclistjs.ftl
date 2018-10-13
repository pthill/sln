<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/func.js"></script>
<#assign bannerURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<#assign enterURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/enter"/>
<#assign serviceURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/recommendService"/>
<#assign activeURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/shopActive"/>
<script language="javascript">
    $(function () {
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("BANNER_TYPE","ACTIVE_TYPE","MENU_STATE","ACTIVITY_STATE")})');
    </#noescape>
        <#if message??&&message!="">
            $.messager.progress('close');
            $.messager.alert('提示',"${message}");
        </#if>
        <#if index??>
            $('#tt').tabs('select',${index});
            selectTab(${index});
        </#if>
        $('#tt').tabs({
            tabPosition:'left',
            headerWidth:100,
            tabWidth:100,
            border:false,
            onSelect:function(title){
                var tab = $('#tt').tabs('getSelected');
                var index = $('#tt').tabs('getTabIndex',tab);
                selectTab(index);
            }
        });
    });
    function selectTab(index) {
        if(index==0){
        	var height = Number($("#tt").height()) - Number($("#banner").find('.head-seachbox').height());
            $('#bannerGrid').datagrid({
                height:height,
                rownumbers:true,
                idField :'id',
                method: 'POST',
                autoRowHeight:false,
                striped:true,
                fitColumns:true,
                pagePosition:'bottom',
                showFooter: true,
                singleSelect: true,
                toolbar:'#bannerTools',
                pagination:true,
                pageSize:'${pageSize}',
                queryParams:queryParamsHandler(),
                url:'${bannerURL}/list',
                columns: [[
                    { field: 'id', hidden:'true',width:80 },
                    { field: 'name', title: '名称', width: 80, align: 'left' },
                    { field: 'parkName', title: '园区', width: 80, align: 'left' },
                    { field: 'order', title: '排序', width: 100, align: 'left' },
                    { field: 'type', title: '类型', width: 100, align: 'left',formatter:typeFormat},
                    { field: 'abbreviation', title: '简拼', width: 100, align: 'left' },
                    { field: 'url', title: 'URL', width: 100, align: 'left' },
                    { field: 'state', title: '状态', width: 50, align: 'left',formatter: stateFormat},
                    { field: 'startTime', title: '开始时间', width: 150, align: 'left' },
                    { field: 'endTime', title: '结束时间', width: 150, align: 'left' },
                    { field: 'handle', title: '操作', width: 100, align: 'left',formatter:bannerFormat }
                ]]
            });
        }
        if(index==1){
        	var height = Number($("#quick").height()) - Number($("#quick").find('.head-seachbox').height());
            $('#enterGrid').datagrid({
                height:height,
                rownumbers:true,
                idField :'id',
                method: 'POST',
                autoRowHeight:false,
                striped: true,
                fitColumns: true,
                pagePosition:'bottom',
                showFooter: true,
                singleSelect: true,
                toolbar:'#enterTools',
                queryParams:queryParamsHandler(),
                pagination:true,
                pageSize:'${pageSize}',
                url:'${enterURL}/list',
                columns: [[
                    { field: 'id', hidden:'true',width:80 },
                    { field: 'name', title: '名称', width: 180, align: 'left' },
                    { field: 'order', title: '排序', width: 150, align: 'left' },
                    { field: 'serviceName', title: '链接', width: 100, align: 'left' },
                    { field: 'state', title: '状态', width: 100, align: 'left',formatter:stateFormat},
                    { field: 'hand', title: '操作', width: 100, align: 'left',formatter: enterFormat}
                ]]
            });
        }
        if(index==2){
        	var height = Number($("#service").height()) - Number($("#service").find('.head-seachbox').height());
            $('#serviceGrid').datagrid({
                height:height,
                rownumbers:true,
                idField :'id',
                method: 'POST',
                striped: true,
                autoRowHeight:false,
                fitColumns: true,
                singleSelect: true,
                toolbar:'#serviceTools',
                pagination:true,
                pageSize:'${pageSize}',
                url:'${serviceURL}/list',
                columns: [[
                    { field: 'id', hidden:'true',width:80 },
                    { field: 'fwx', title: '服务类', width: 180, align: 'left' },
                    { field: 'order', title: '排序', width: 150, align: 'left' },
                    { field: 'fwl', title: '服务项', width: 100, align: 'left' },
                    { field: 'img', title: 'icon', width: 100, align: 'center',formatter:imgFormat},
                    { field: 'state', title: '状态', width: 100, align: 'left',formatter:stateFormat},
                    { field: 'hand', title: '操作', width: 100, align: 'left',formatter:serviceFormat}
                ]]
            });
        }
        if(index==3){
        	var height = Number($("#shop").height()) - Number($("#shop").find('.head-seachbox').height());
            $('#shopGrid').datagrid({
                height:height,
                rownumbers:true,
                idField :'id',
                method: 'POST',
                striped: true,
                fitColumns: true,
                autoRowHeight:false,
                singleSelect: true,
                queryParams:queryParamsHandler(),
                pagination:true,
                toolbar:'#shopTools',
                pageSize:'${pageSize}',
                url:'${activeURL}/listBk',
                columns: [[
                    { field: 'id', hidden:'true',width:80 },
                    { field: 'name', title: '活动名称', width: 180, align: 'left' },
                    { field: 'type', title: '活动类型', width: 150, align: 'left',formatter:activeFormat},
                    { field: 'status', title: '状态', width: 100, align: 'left',formatter:stateFormat },
                    { field: 'handler', title: '操作', width: 100, align: 'left',formatter:shopFormat }
                ]]
            });
        }
        if(index==4){
        	var height = Number($("#active").height()) - Number($("#active").find('.head-seachbox').height());
            $('#activeGrid').datagrid({
                height:height,
                rownumbers:true,
                method: 'POST',
                idField :'id',
                striped: true,
                autoRowHeight:false,
                fitColumns: true,
                singleSelect: true,
                toolbar:'#activeTools',
                queryParams:queryParamsHandler(),
                pagination:true,
                pageSize:'${pageSize}',
                url:'${activeURL}/listMj',
                columns: [[
                    { field: 'id', hidden:'true',width:80 },
                    { field: 'name', title: '活动名称', width: 180, align: 'left' },
                    { field: 'type', title: '活动类型', width: 150, align: 'left',formatter:activeFormat},
                    { field: 'status', title: '状态', width: 100, align: 'left',formatter:stateFormat },
                    { field: 'handler', title: '操作', width: 100, align: 'left',formatter:lastFormat }
                ]]
            });
        }
    }
    function typeFormat(value,row,index){
        return codeBox["BANNER_TYPE"][value];
    }
    function activeFormat(value,row,index){
        return codeBox["ACTIVE_TYPE"][value];
    }
    function stateFormat(value,row,index) {
        return codeBox["MENU_STATE"][value];
    }
    function activityFormat(value,row,index) {
        return codeBox["ACTIVITY_STATE"][value];
    }
    function imgFormat(value,row,index){
        var hrefVal = "${domainUrlUtil.SLN_IMAGE_RESOURCES}" + value;
        return"<img src='"+hrefVal+"'/>";
    }
    function shopFormat(value, row, index) {
        var html = "";
        if (row.status == "1") {//启用状态
            html += "<a href='javascript:;'style='color: blue' onclick='shopOrOn(" + row.id +","+row.status + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' style='color: blue'  onclick='shopOrOn(" + row.id +","+row.status + ")'>启用</a>";
        }
        return html;
    }
    function lastFormat(value, row, index) {
        var html = "";
        if (row.status == "1") {//启用状态
            html += "<a href='javascript:;'style='color: blue' onclick='lastOrOn(" + row.id +","+row.status + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' style='color: blue'  onclick='lastOrOn(" + row.id +","+row.status + ")'>启用</a>";
        }
        return html;
    }
    function bannerFormat(value, row, index) {
        var html = "";
        if (row.state == "1") {//启用状态
            html += "<a href='javascript:;'style='color: blue' onclick='bannerOrOn(" + row.id +","+row.state + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' style='color: blue'  onclick='bannerOrOn(" + row.id +","+row.state + ")'>启用</a>";
        }
        return html;
    }
    function serviceFormat(value, row, index) {
        var html = "";
        if (row.state == "1") {//启用状态
            html += "<a href='javascript:;'style='color: blue' onclick='serviceOrOn(" + row.id +","+row.state + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' style='color: blue'  onclick='serviceOrOn(" + row.id +","+row.state + ")'>启用</a>";
        }
        return html;
    }
    function enterFormat(value, row, index) {
        var html = "";
        if (row.state == "1") {//启用状态
            html += "<a href='javascript:;'style='color: blue' onclick='enterOrOn(" + row.id +","+row.state + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' style='color: blue'  onclick='enterOrOn(" + row.id +","+row.state + ")'>启用</a>";
        }
        return html;
    }
    function bannerOrOn(id,status) {
        var flag="1";
        var msg="";
        if(status=="1"){
            flag="0";
            msg="禁用成功";
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${bannerURL}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#bannerGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#bannerGrid').datagrid('reload');
                }

            }
        });
    }
    function enterOrOn(id,status) {
        var flag="1";
        var msg="";
        if(status=="1"){
            flag="0";
            msg="禁用成功";
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${enterURL}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#enterGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#enterGrid').datagrid('reload');
                }

            }
        });
    }
    function serviceOrOn(id,status) {
        var flag="1";
        var msg="";
        if(status=="1"){
            flag="0";
            msg="禁用成功";
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${serviceURL}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#serviceGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#serviceGrid').datagrid('reload');
                }

            }
        });
    }
    function shopOrOn(id,status) {
        var flag="1";
        var msg="";
        if(status=="1"){
            flag="0";
            msg="禁用成功";
            $.messager.show({
                title:'提示',
                msg:'至少保留一个启用数据',
                showType:'show'
            });
            $('#shopGrid').datagrid('reload');
            return;
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${activeURL}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#shopGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#shopGrid').datagrid('reload');
                }

            }
        });
    }
    function lastOrOn(id,status) {
        var flag="1";
        var msg="";
        if(status=="1"){
            flag="0";
            msg="禁用成功";
            $.messager.show({
                title:'提示',
                msg:'至少保留一个启用数据',
                showType:'show'
            });
            $('#activeGrid').datagrid('reload');
            return;
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${activeURL}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#activeGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#activeGrid').datagrid('reload');
                }

            }
        });
    }
</script>