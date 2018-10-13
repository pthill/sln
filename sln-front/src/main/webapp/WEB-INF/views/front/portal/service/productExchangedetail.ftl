<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/productback.css">
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
          		<a href="javascript:;">换货信息&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
		<h3>换货信息</h3>
		<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
			<tbody>
				<tr>
					<th>商品名称</th>
					<th>购买数量</th>
				</tr>
				<tr>
					<td>
						<ul class='list-proinfo'>
							<li>
								<#if orderProduct??>
									<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target="_blank">
										<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${orderProduct.productLeadLittle}' width='50' height='50' title='${(orderProduct.productName)!''}'>
									</a>
									<div class='p-info-name'>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target='_blank'>${(orderProduct.productName)!''}</a>
									</div>
								</#if>
							</li>
						</ul>
					</td>
					<td>${(orderProduct.number)!''}</td>
				</tr>
			</tbody>
		</table>
		<div class='apply-form' id='consignee-form'>
			<div class='repair-steps'>
				<div class='repair-step repair-step-curr'>
					<div class='miaoshuDiv'>
						<!-- 换货数量 -->
						<div class='sellerPrompt'>
							<span class='label'>
								<span>换货数量：</span> 
							</span>
							<div >
								 ${(info.number)!0 }
							</div>
						</div>
						<!-- 问题描述 -->
						<div class='sellerPrompt'>
							<span class='label'>
								<span>问题描述：</span> 
							</span>
							<div >
								 ${(info.question)!'' }
							</div>
						</div>
						
						
						<div class='sellerPrompt'>
							<span class='label'>
								<span>状&#12288;&#12288;态：</span> 
							</span>
							<div>
								 <#if  info.state??>
					  				<#assign state = info.state>
					  				<#if state==1>未处理
						  				<#elseif state==2>审核通过待收货
						  				<#elseif state==3>已经收货
						  				<#elseif state==4>发货处理完成
						  				<#elseif state==5>不予处理原件退还
						  				<#elseif state==6>不处理
						  				<#else>
					  				</#if>
			  		    		</#if>
							</div>
							<em class='em-errMes'></em>	
						</div>
						
						<div class='sellerPrompt'>
							<span class='label'>
								<span>处理意见：</span> 
							</span>
							<div>
								${(info.remark)!'无'}
							</div>
							<em class='em-errMes'></em>	
						</div>
					</div>
					
					<div class='miaoshuDiv'>
						<!-- 状态 -->
						<!-- end -->
					</div>
					<!-- end -->
				</div>
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
		$("#productexchange").addClass("currnet_page");
		
      	
       });
</script>
<#include "/front/portal/common/footer.ftl" />