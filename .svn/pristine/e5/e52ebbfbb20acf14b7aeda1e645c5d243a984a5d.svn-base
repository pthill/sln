<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
    $(function(){
        <#if message??&&message!="">
        $.messager.progress('close');
        $.messager.alert('提示',"${message}");
        </#if>
        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#a-gridEdit").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${currentBaseUrl}/edit?id="+selected.id;
            }
        });
        $("#a-gridDel").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selected);
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${currentBaseUrl}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#dataGrid').datagrid('reload');
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    });
    function handleFormat(value, row, index) {
        var html = "";
        if (row.state == 1) {//启用状态
            html += "<a href='javascript:;' onclick='offOrOn(" + row.id +","+row.state + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' onclick='offOrOn(" + row.id +","+ row.state + ")'>启用</a>";

        }
        return html;
    }
    function  offOrOn(id,state) {
        var flag="1";
        var msg="";
        if(state=="1"){
            flag="0";
            msg="禁用成功";
        }else{
            flag="1";
            msg="启用成功";
        }
        $.ajax({
            url:'${currentBaseUrl}/onOrOff?id='+id+'&state='+flag,
            type:'post',
            success:function(data){
                if(data.data>0){
                    $.messager.show({
                        title:'提示',
                        msg:msg,
                        showType:'show'
                    });
                    $('#dataGrid').datagrid('reload');
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:"状态更新失败",
                        showType:'show'
                    });
                    $('#dataGrid').datagrid('reload');
                }

            }
        });
    }

</script>