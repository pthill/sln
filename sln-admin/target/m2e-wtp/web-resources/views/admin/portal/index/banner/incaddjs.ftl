<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/resources/admin/jslib/js/func.js"></script>
<#assign bannerURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/index"/>
<#assign enterURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/enter"/>
<#assign serviceURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/recommendService"/>
<#assign activeURL="${domainUrlUtil.SLN_URL_RESOURCES}/admin/portal/shopActive"/>
<script language="javascript">
    $(function () {
        /*轮播banner的点击事件*/
        $('#a-bannerSearch').click(function(){
            $('#bannerGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-bannerAdd").click(function () {
            window.location.href="${bannerURL}/add?index=0";
        });
        $("#a-bannerEdit").click(function () {
            var selected = $('#bannerGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${bannerURL}/edit?id="+selected.id+"&index=0";
            }
        });
        $("#a-bannerDel").click(function () {
            var selected = $('#bannerGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${bannerURL}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#bannerGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#bannerGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        /*推荐服务点击事件*/
        $('#a-serviceSearch').click(function(){
            $('#serviceGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-serviceAdd").click(function () {
            window.location.href="${serviceURL}/add?index=2";
        });
        $("#a-serviceEdit").click(function () {
            var selected = $('#serviceGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${serviceURL}/edit?id="+selected.id+"&index=2";
            }
        });
        $("#a-serviceDel").click(function () {
            var selected = $('#serviceGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${serviceURL}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#serviceGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#serviceGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        /*快速入口点击事件*/
        $('#a-enterSearch').click(function(){
            $('#enterGrid').datagrid('reload',queryParamsHandler());
        });
        $('#a-enterAdd').click(function(){
            window.location.href="${enterURL}/add?index=1";
        });
        $('#a-enterEdit').click(function(){
            var selected = $('#enterGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${enterURL}/edit?id="+selected.id+"&index=1";
            }

        });
        $("#a-enterDel").click(function () {
            var selected = $('#enterGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${enterURL}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#enterGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#enterGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        /*电商满减的点击事件*/
        $('#a-activeSearch').click(function(){
            $('#activeGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-activeAdd").click(function () {
            window.location.href="${activeURL}/add?index=4";
        });
        $("#a-activeEdit").click(function () {
            var selected = $('#activeGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${activeURL}/edit?id="+selected.id+"&index=4";
            }
        });
        $("#a-activeDel").click(function () {
            var selected = $('#activeGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${activeURL}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#activeGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#activeGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        /*电商爆品点击事件*/
        $('#a-shopSearch').click(function(){
            $('#shopGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-shopAdd").click(function () {
            window.location.href="${activeURL}/add?index=3";
        });
        $("#a-shopEdit").click(function () {
            var selected = $('#shopGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status==1){
                $.messager.alert('提示','启用状态下不可编辑');
            }else{
                window.location.href="${activeURL}/edit?id="+selected.id+"&index=3";
            }
        });
        $("#a-shopDel").click(function () {
            var selected = $('#shopGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', "确认要删除吗？", function(r) {
                if (r) {
                    $.messager.progress({
                        text : "提交中..."
                    });
                    $.ajax({
                        url:'${activeURL}/del?id='+selected.id,
                        type:'post',
                        success:function(data){
                            if(data.data>0){
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#shopGrid').datagrid('reload');
                            }else{
                                $.messager.show({
                                    title:'提示',
                                    msg:data.message,
                                    showType:'show'
                                });
                                $('#shopGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    })
</script>