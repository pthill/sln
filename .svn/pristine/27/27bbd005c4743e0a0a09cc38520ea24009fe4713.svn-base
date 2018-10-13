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
          		<a href="javascript:;">收藏的商品&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>收藏的商品</h3>
				<div class='mc'>
					<div class='fav-goods-list'>
						<ul>
							<#if productList??>
								<#list productList as product>
									<li>
										<div class='fav-goods-item'>
											<div class='p-img'>
												<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' target="_blank">
													<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${product.productLeadLittle}' width='160' height='160'>
												</a>
											</div>
											<div class='p-name'>
												<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(product.productId)!0}.html' target="_blank" title="${(product.productName)!''}">${(product.productName)!''}</a>
											</div>
											<div class='p-price'>
												<strong>￥${(product.mallPcPrice)?string("0.00")!''}</strong>
											</div>
											<div class='p-opbtns'>
												<a href='javascript:void(0);' onclick="unfollowProduct('${(product.productId)!''}')" class='btn btn-default'>取消收藏</a>
											</div>
										</div>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
				</div>
				<!-- 分页 -->
				<div class="pagination-container"></div>
			
			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      	//控制左侧菜单选中
		$("#collectionGoods").addClass("currnet_page");
		
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/collectproduct.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />