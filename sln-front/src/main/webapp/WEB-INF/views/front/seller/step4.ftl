 <#include "/front/commons/_headbig.ftl" />

<link rel="icon" type="image/x-icon" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/favicon.png" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/article.css">
<link rel="stylesheet" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/frontseller/css/errorStyle.css'>

<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/frontseller/js/step4.js'></script>
<script type="text/javascript">
	function sub(){
	 var count=0;
	 var array=[];
		<#list parkList.result as park>
			$('input[name="operationId_${park.id}"]:checked').each(function(){
				count++;
				var temp = {parkName:"${park.parkName}",parkId : ${park.id}, operationId :$(this).val(),operationName:$(this).next().html()};
				array.push(temp);
			});
		</#list>
        console.log("新的json数组:"+JSON.stringify(array));
        if(count==0){
            $.alerts.alert('提示','请至少选择一个业务管理方！');
            return false;
        }
        
        $("#form_store_info").attr("action", "${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/step4save.html?parkoperation="+JSON.stringify(array));
        $("#form_store_info").attr("method", "POST");
        $("#form_store_info").submit();
     
	}
</script>

<div id="loadingDiv"
	style="position: fixed; display: none; z-index: 2000; top: 0px; left: 0px; width: 100%; height: 100%; background-color: rgba(255, 255, 255, 0.5)"></div>

<div class='container'>
	<div class='breadcrumb'>
		<strong class='business-strong'> <a href=''>首页</a>
		</strong> <span> &nbsp;>&nbsp; <a href=''>商家入驻申请</a>
		</span>
	</div>
</div>
<div class='container'>
	<div class='business-layout'>
		<div class='joinin-step'>
			<ul>
				<li class='li-curr step1 current' data-form='1'><span>签订入驻协议</span>
				</li>
				<li class='li-curr current' data-form='2'><span>公司资质信息</span></li>
				<li class='li-curr current ' data-form='3'><span>财务资质信息</span>
				</li>
				<li class='li-curr current' data-form='4'><span>店铺经营信息</span></li>
				<!-- <li class='li-curr' data-form='5'>
							<span>合同签订及缴费</span>
						</li> -->
				<li class='li-curr step6' data-form='6'><span>店铺开通</span></li>
			</ul>
		</div>
		<div class='joinin-concrete'>
			<!-- 店铺经营信息 -->
			<div id='apply_store_info' class='apply_store_info'>
				<div class='alert'>
					<h4>注意事项：</h4>
					店铺经营类目为商城商品分类，请根据实际运营情况添加一个或多个经营类目。
				</div>
				<form id='form_store_info'
					action='###'
					method="POST">
					<table id='table' border="0" cellpadding="0" cellspacing="0"
						class="all">
						<thead>
							<tr>
								<th colspan='20'>店铺经营信息</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th class="w150"><i>*</i> 商家账号：</th>
								<td><input name='name' type='text' id="username"
									class='form-control w200'> <span></span>
									<p class='emphasis'>此账号为日后登录并管理商家中心时使用，注册后不可修改，请牢记。</p></td>
							</tr>
							<tr>
								<th class="w150"><i>*</i> 店铺名称：</th>
								<td><input id='seller_name' type='text' name='seller_name'
									class='form-control w200 '> <span></span>
									<p class='emphasis'>店铺名称注册后不可修改，请认真填写。</p></td>
							</tr>
							<tr>
								<th class="w150"><i>*</i> 业务经营：</th>
								<td>
									<#list parkList.result as park>
			                        <p class="p12 p-item">
			                           <label class="lab-item"><font class="red"></font>${park.parkName}:</label>
											<#list park.operationNames?split(",") as name>
												<#list codeManager.codeMap['OPERATION_NAME'] as code>
													<#if name==code.codeCd>
			                                            <input type="checkbox" name="operationId_${park.id}" value="${name}"><span>${code.codeText!''}</span>
													</#if>
												</#list>
											</#list>
			                        </p>
									</#list>
								</td>
									
							</tr>
						</tbody>
					</table>
				</form>
				<div class='bottom'>
					<a id='btn_apply_store_next' class='btn btn-default'
						href='javascript:void(0);'
						onclick="sub()">提交申请</a>
				</div>
			</div>
			<!-- end -->
		</div>
	</div>
</div>

<#include "/front/commons/_endbig.ftl" />