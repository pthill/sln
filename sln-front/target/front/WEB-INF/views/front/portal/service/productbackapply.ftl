<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
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
          		<a href="javascript:;">售后申请&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>售后申请</h3>
					<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
						<tbody>
							<tr>
								<th width='100'>订单编号</th>
								<th>订单商品</th>
								<th width='100'>下单时间</th>
							</tr>
						
						<#if order??>
							<tr>
								<td>
									<a href='' target='_blank' class='ftx-05'>${(order.orderSn)!''}</a>
								</td>
								<td>
									<div class='list-h'>
											<ul>
										<#if (order.orderProductList)??>
											<#list (order.orderProductList) as product>
												<li>
													<div class='p-img'>
														<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${product.productLeadLittle}' width='50' height='50' title=''>
													</div>
													<div class='p-info-name'>
														<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' target='_blank' title="${(product.productName)!''}">${(product.productName)!''}</a>
													</div>
													
													<a id="applyButton"  href="javascript:void(0)"  onclick='applyBack(${(product.id)!''},${order.id})' class='btn btn-default apply'>申请</a>
												</li>
											</#list>
										</#if>
											</ul>
									</div>
								</td>
								<td>
									<div class='u-name'>${(order.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</div>
								</td>
							</tr>
						</tbody>
						</#if>
						
					</table>
			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      	//控制左侧菜单选中
		$("#myorder").addClass("currnet_page");

       });
       
       function applyBack(orderProductId,orderId){
		$("#applyButton").attr("disabled","disabled");
		  $.ajax({
			type:"GET",
			url: domain+"/member/canbackorexchange.html",
			dataType:"json",
			async : false,
			data : {orderProductId:orderProductId,orderId:orderId},
			success:function(data){
				if(data.success){
					window.location.href=domain+'/member/productbackapply.html?orderProductId='+orderProductId+'&&orderId='+orderId;
				}else{
					jAlert(data.message);
					$("#applyButton").removeAttr("disabled");
				}
			},
			error:function(){
				jAlert("异常，请重试！");
				$("#applyButton").removeAttr("disabled");
			}
		}); 
	}
</script>
<#include "/front/portal/common/footer.ftl" />