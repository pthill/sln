<#include "/admin/commons/_detailheader.ftl" />
<script src="${domainUrlUtil.SLN_URL_RESOURCES!}/resources/admin/jslib/js/jquery.form.js"></script>
<#assign currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/seller/compailnregister"/>
<script type="text/javascript"
	src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

 var backUrl = "${currentBaseUrl}";
  $(function () {
        $("#back").click(function () {
            window.location.href=backUrl;
        });
        
        $("#add").click(function () {
            if($("#doForm").form('validate')){
                $("#doForm").submit();
            }
        });
        
        
    $('#selectType').click(function(){
            $('#sellerDialog').dialog('open');
        });
        $('#complainSeller').click(function(){
            $('#sellerDialog').dialog('open');
        });
        
        
        $('#selectMember').click(function(){
            $('#memberDialog').dialog('open');
        });
        $('#complainPerson').click(function(){
            $('#memberDialog').dialog('open');
        });
        
    });
    
    
    function typeCallBack(data){
        $('#sellerId').val('');
        $('#complainSeller').val('');
        if(data){
             var normNames = '';
            var normIds = '';
            $.each(data, function(j, n){
            normIds= n.id;
            normNames= n.sellerName;
            })
                $('#sellerId').val(normIds);
            if(normNames.length > 0){
                $('#complainSeller').val(normNames);
            }
        }
    }
      function normloadSuccess(data){
    	dataGridLoadSuccess(data,this);
    }
    
    
    function memberCallBack(data){
        $('#complainPerson').val('');
        if(data){
             var normNames = '';
            $.each(data, function(j, n){
            normNames= n.name;
            })
            if(normNames.length > 0){
                $('#complainPerson').val(normNames);
            }
        }
    }
    function memberloadSuccess(data){
    dataGridLoadSuccess(data,this);
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">
		投诉登记<span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="${currentBaseUrl}/create.html" method="post" id="doForm" name="queryForm">
				<div class="fluidbox" id="producttypediv">
				   <p class="p12 p-item">
						<label class="lab-item">投诉商家 :</label>
						<input class="easyui-validatebox txt w280" type="text"class="txt" id="complainSeller"  name="complainSeller" value="" missingMessage="关联商家，请选择"  style="width:100px" data-options="required:true"/>
					     <input type="hidden" name="sellerId" id="sellerId" value="${(complainSeller.sellerId)!''}"/>
                            <image id="selectType" href="javascript:void(0)"  
                            	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">投诉类型 :</label>
							<@cont.select id="q_complainType" codeDiv="COMPAIN_TYPE" name="complainType" style="width:100px" />
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">投诉用户名称:</label> 
						<input type="text" class="easyui-validatebox txt w280" id="complainPerson" name="complainPerson" style="width:100px" data-options="required:true"/>
					    <image id="selectMember" href="javascript:void(0)"  
                            	src="${domainUrlUtil.SLN_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
					</p>
				</div>
				<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">详情：</label>
							 <textarea class="easyui-validatebox txt w280" name="desceinfo" id="desceinfo" style="width:300px;height:120px" data-options="required:true,validType:'length[0,300]'"></textarea>
						</p>
					</div>
			   <p class="p-item p-btn">
                  <input type="button" id="add" class="btn" value="保存" />
                  <input type="button" id="back" class="btn" value="返回" />
               </p>
			</form>
		</div>
	</div>
</div>
<div style="display: none">
<#include "sellerdialog.ftl" />
</div>
<div style="display: none">
<#include "memberdialog.ftl" />
</div>
<#include "/admin/commons/_detailfooter.ftl" />