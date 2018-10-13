<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
<style>
	.myorder{
			width:82%;margin-left:10px
		}
</style>
<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">修改支付密码&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
		<h3>修改支付密码</h3>
		<div class=''id='pageTips'></div>
		<form class='pwform' id='editPasswordform'>
			<dl class='dl-setpw'>
				<dt class='dt_item'>
					<span style='color:red'>*&nbsp;</span><label>原密码：</label>
				</dt>
				<dd class='dd_item p-item'>
					<input type='password' class='txt' id='txt-oldpw' name='oldPwd' style='width:40%'>
					<em class='em-errMes' style='color:red'></em>
					
				</dd>
			</dl>
			<dl class='dl-setpw'>
				<dt class='dt_item'>
					<span style='color:red'>*&nbsp;</span><label>新密码：</label>
				</dt>
				<dd class='dd_item p-item'>
					<input type='password' class='txt' id='txt-newpw' name='newPwd' style='width:40%'>
					<em class='em-errMes' style='color:red'></em>
				</dd>
			</dl>
			<dl class='dl-setpw'>
				<dt class='dt_item'>
					<span style='color:red'>*&nbsp;</span><label>确认密码：</label>
				</dt>
				<dd class='dd_item p-item'>
					<input type='password' class='txt' id='txt-repw' name='confirmPwd' style='width:40%'>
					<em class='em-errMes' style='color:red'></em>
				</dd>
			</dl> 
			<input type='button' style="float:left"  class='btn btn-danger bt_submit' id='buttonSubmit' name='button' value='确认提交'>
			<span style="margin-left: 60px; display: block; float: left; margin-top: 10px;">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balancepwdreset.html">忘记密码？点此重置</a>
			</span>
		</form>
	</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      	//控制左侧菜单选中
		$("#editPassword").addClass("currnet_page");
		
      	//校验
			jQuery("#editPasswordform").validate({
				errorPlacement : function(error, element) {
					var obj = element.siblings(".em-errMes").css('display', 'block');
					error.appendTo(obj);
				},
		        rules : {
		            "oldPwd":{required:true},
		            "newPwd":{required:true,passwordLength:true},
		            "confirmPwd":{required:true,equalTo:"#txt-newpw"}
		        },
		        messages:{
		        	"oldPwd":{required:"请输入原密码"},
		        	"newPwd":{required:"请输入新密码"},
		            "confirmPwd":{required:"请输入确认密码",equalTo:"密码输入不一致"}
		        }
		    }); 
		
		
			$(".bt_submit").click(function(){
				if($("#editPasswordform").valid()){
					$(".bt_submit").attr("disabled","disabled");
					var params = $('#editPasswordform').serialize();
					$.ajax({
						type:"POST",
						url:domain+"/member/updatebalancepassword.html",
						dataType:"json",
						async : false,
						data : params,
						success:function(data){
							if(data.success){
								//jAlert("密码设置成功");
								//重新加载数据
								//window.location.href=domain+"/member/balance.html";
								
								jAlert('密码设置成功', '提示',function(){
									window.location.href=domain+"/member/balance.html"
								});
							}else{
								jAlert(data.message);
								$(".bt_submit").removeAttr("disabled");
							}
						},
						error:function(){
							jAlert("异常，请重试！");
							$(".bt_submit").removeAttr("disabled");
						}
					});
				}
				
			});
       });
</script>
<#include "/front/portal/common/footer.ftl" />