<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("ACTIVITY_STATE")})');
    </#noescape>
        $('#btn-search').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#btn_add").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#btn_update").click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${currentBaseUrl}/edit?id="+selected.id;
            }

        });
        $("#btn_del").click(function () {
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
                            $.messager.progress('close');
                            //refrushCSRFToken(data.csrfToken);
                        }
                    });

                }
            });
        });

    });
    function stateFormat(value,row,index){
        //系统当前时间
        var timestamp=new Date().getTime();
        //活动开始时间
        var startdate = new Date(row.startTime).getTime();
        var enddate=new Date(row.endTime).getTime();
        if(timestamp>startdate&& timestamp<enddate){
            return "进行中";
        }
        if(timestamp>enddate){
            return "已结束";
        }
        if(timestamp<startdate){
            return "即将开始";
        }
    }

    //操作
    function handleFormat(value, row, index) {
        var html = "";
        if (row.status == 1) {//启用状态
            html += "<a href='javascript:;' onclick='offOrOn(" + row.id +","+row.status + ")'>禁用</a>";
        } else {//禁用状态
            html += "<a href='javascript:;' onclick='offOrOn(" + row.id +","+row.status + ")'>启用</a>";
        }
        return html;
    }
    //启用或者禁用
    function  offOrOn(id,status) {
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
                        msg:msg,
                        showType:'show'
                    });
                    $('#dataGrid').datagrid('reload');
                }

            }
        });
    }

</script>