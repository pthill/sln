<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<style>
	.myorder{
			width:80%;margin-left:10px
		}
</style>
<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">发表评价&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>我的评价</h3>
				<div class='mc'>
					<table class='tb-void p-states'>
						<thead>
							<tr>
								<th width='328'>商品信息</th>
								<th>购买时间</th>
								<th>评价状态</th>
							</tr>
						</thead>
						<tbody>
						
						<#if order.orderProductList??>
						<#list  order.orderProductList as product>
							<tr class='cli-box'>
								<td colspan="3">
									<ul class='pro-info'>
										<li class="fore1">
											<div class='p-info'>
												<div class='p-img fl'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' target='_blank'>
														<img title="${(product.productName)!''}" src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${product.productLeadLittle}' width='50' height='50'>
													</a>
												</div>
												<div class='p-name fl'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' target='_blank'>${(product.productName)!''}</a>
												</div>
											</div>
										</li>
										<li class='fore2'>
											<span class='ftx03'>${(order.createTime?string("yyyy-MM-dd"))!''}</span>
										</li>
										<li class='fore3'>
											<a href='javascript:void(0);' class='look-avalute' onclick="editEvaluat(this,'${(order.orderSn)!''}',${(product.productId)!''},${(product.productGoodsId)!''},${(product.id)!''})">发表评价</a>
										</li>
									</ul>
									
									<!-- 编辑评价 --> 
									<div id="my-evaluat${(product.productGoodsId)!''}" class='my-evaluat look-box'>
										
									</div>
									<!-- end -->
								</td>
							</tr>
							</#list>
						</#if>
							
						</tbody>
					</table>
				</div>
			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script type="text/javascript">
      $(function () {
      		//控制左侧菜单选中
			$("#myevaluation").addClass("currnet_page");
       });
       function editEvaluat(obj,orderSn,productId,productGoodsId,ordersProductId){
		 	$(obj).parent().parent().siblings(".look-box").slideToggle();
		 	$(obj).parents(".cli-box").siblings().find(".look-box").slideUp();
		 	
		 	$(".my-evaluat").empty();
		 	 $.ajax({
				type:"GET",
				url:domain+"/member/editcomment.html",
				dataType:"html",
				async : false,
				data : {orderSn:orderSn,productId:productId,productGoodsId:productGoodsId,ordersProductId:ordersProductId},
				success:function(data){
						//加载数据
						$("#my-evaluat"+productGoodsId).html(data);
				},
				error:function(){
					jAlert("异常，请重试！");
				}
			});
		}
</script>
<#include "/front/portal/common/footer.ftl" />