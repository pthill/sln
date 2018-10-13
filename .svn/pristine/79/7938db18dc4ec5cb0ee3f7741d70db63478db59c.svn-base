<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<script type="text/javascript"
	src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/user_retrieve_password.css">
<style>
	.myorder{
			width:82%;margin-left:10px
		}
	#editPasswordform span{
		font-size:12px
	}
	#editPasswordform input{
		
	}
</style>
<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">密码重置&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class="wrapper_main myorder wrapper_main-wd">
		<div class="mc">
			<div class="stepflex clearfix">
				
				<dl class="first doing">
					<dt class="s-num">1</dt>
					<dd class="s-text">
						验证身份<s></s>
					</dd>
				</dl>
				<dl class="normal">
					<dt class="s-num">2</dt>
					<dd class="s-text">
						修改支付密码<s></s><b></b>
					</dd>
				</dl>
				<dl class="last ">
					<dt class="s-num"></dt>
					<dd class="s-text">
						完成<s></s><b></b>
					</dd>
				</dl>
			</div>
			<div class="form-boxzh">
				<form id='editPasswordform'>
					<input type="hidden" value="${(member.mobile)!}" id="phone"/>
					<div class="item-user">
						<span class="w108"> 已验证手机：</span> 
							<label class="tel-yz">
								${commUtil.sensitiveInfo(member.mobile,'tel')}
							</label>
						<span style="color: #2722F4">通过注册手机验证 </span>
					</div>
					<div class="item-user">
						<span class="w108"> 请填写手机校验码：</span> 
						<input class="user-zh" type="text" id="smscode" 
							placeholder="请输入您收到的短信验证码" name="smscode"> 
						<button class="ip-dx"
							id="getverify" type="button">获取短信验证码</button>
						<span class="colr err-box"></span>
					</div>
					<div class="item-user">
						<span class="w108"> 验证码：</span> 
							<input class="user-zh" id="verifycode" 
							  placeholder="请输入右侧图片验证码" name="verifycode" type="text">
						<span> 
						<img style="cursor:pointer;" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();"  />
						<a href='javascript:void(0);' style="color: #005ea7" onclick="refreshCode();">看不清，换一张</a>	
						</span> 
						<span class="colr err-box"></span>
					</div>
					<div class="item-user">
						<input value="提交" class="btn-ip" type="button" id="bt_submit">
	<!-- 					 <span>无法验证身份？试试&nbsp;<a -->
	<!-- 						href="" style="color: #005ea7">自助申请</a></span> -->
					</div>
				</form>
			</div>
		</div>
	</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script type="text/javascript">
$(function () {
      	//控制左侧菜单选中
		$("#balance").addClass("currnet_page");
		
      	//校验
		$("#editPasswordform").validate({
			errorPlacement : function(error, element) {
				var obj = element.siblings("span.err-box").show();
				error.appendTo(obj);
			},
			rules : {
				"verifycode" : {
					required : true
				},
				"smscode" : {
					required : true
				}
			},
			messages : {
				"verifycode" : {
					required : "请输入验证码"
				},
				"smscode" : {
					required : "请输入手机验证码"
				}
			}
		});
		
		$("#getverify").click(function(){
			var obj = $(this);
			var mob = $("#phone").val();

			$.ajax({
				type : 'get',
				url : domain + '/sendVerifySMS.html?mob=' + mob + '&type=blt',
				success : function(e) {
					if (!e.success) {
						jAlert(e.message);
						refreshCode();
						obj.text("获取短信验证码");
					} else{
						var time = 120;
						obj.attr("disabled", true);
						obj.text(time + "秒后重新获取");
						time--;
				
						var intervalId = setInterval(function() {
							obj.text(time + "秒后重新获取");
							time--;
							if (time == 0) {
								clearInterval(intervalId);
								obj.removeAttr("disabled");
								obj.text("获取短信验证码");
							}
						}, 1000);
					}
				}
			});
		
		});
		
		$("#bt_submit").click(function() {
			if ($("#editPasswordform").valid()) {
				$("#bt_submit").attr("disabled", "disabled");
				var params = $('#editPasswordform').serialize();
				$.ajax({
					type : "POST",
					url : domain
							+ "/member/balancepwdreset/valid.html",
					dataType : "json",
					async : false,
					data : params,
					success : function(data) {
						if (data.success) {
							//下一步
							window.location.href = domain+"/member/balancepwdreset/step2.html";
						} else {
							jAlert(data.message);
							refreshCode();
							$("#bt_submit").removeAttr(
									"disabled");
						}
					},
					error : function() {
						jAlert("系统异常，请稍后重试！");
						$("#bt_submit").removeAttr(
								"disabled");
					}
				});
			}

		});
});
</script>
<#include "/front/portal/common/footer.ftl" />