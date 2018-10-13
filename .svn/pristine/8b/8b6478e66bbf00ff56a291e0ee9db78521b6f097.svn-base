<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
<style>
	#addressList {
		width:80%;margin-left:10px
	}
</style>

<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">我的收货地址&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder' id='addressList'>
				<!-- 地址列表区域 -->
				<h3>
		<a href='javascript:void(0);' class='add-address ftx-05' onclick="addOrEditAddress(0)">新增收货地址</a>
					我的收货地址
		</h3>
				<#if addressList??>
					<#list addressList as address>
						<input type="hidden" value="${address.id}" id ="addressId${address_index }" />
						<ul class="ul_col2">
							<#if (address.state)=1>
								<li class='li-default' style="display:block"><span class='default-address'>默认地址</span></li>
							<#else>
								<li class='li-default' style="display:none"><span class='default-address'>默认地址</span></li>
							</#if>
							
							<li class='li-infomation'>
								<em class='em-infotitle'>收货人：</em>
								<span class='ed_fresher'>${address.memberName}</span>
							</li>
							<li class='li-infomation'>
								<em class='em-infotitle'>手机号码：</em>
								<span class='ed_contact'>${address.mobile}</span>
							</li>
							<br>
							<li class='li-infomation'>
								<em class='em-infotitle'>邮箱：</em>
								<span class='ed_email'>${address.email}</span>
							</li>
							<li class='li-infomation'>
								<em class='em-infotitle'>邮政编码：</em>
								<span class='ed_fresh'>${(address.zipCode)!''}</span>
							</li>
							<br>
							<li class='li-infomation'>
								<em class='em-infotitle'>收货地址：</em>
								<span class='dd_col2 ed_addr'>${address.addAll}${address.addressInfo}</span>
							</li>
							<li class='li-operation'>
								<#if (address.state)=1>
									<a href='javascript:void(0);' style="display:none" class='ftx-05 defaultAddress' onclick="defaultAddress('${address.id}')">设为默认</a>
								<#else>
									<a href='javascript:void(0);' style="display:block" class='ftx-05 defaultAddress' onclick="defaultAddress('${address.id}')">设为默认</a>
								</#if>
								
								<a href='javascript:void(0);' class='ftx-05 edit-address' onclick="addOrEditAddress('${address.id}')">编辑</a>
								<a href='javascript:void(0);' class='ftx-05 delete-address'  onclick="deleteAddress('${address.id}')">删除</a>
							</li>
						</ul>
					</#list>
				</#if>
			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
      <!-- 收货人信息 -->
		<div class='background-layer' id='Harvest'>
		</div>
</div>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/member/myreciptaddress.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<script type="text/javascript">
     
</script>
<#include "/front/portal/common/footer.ftl" />