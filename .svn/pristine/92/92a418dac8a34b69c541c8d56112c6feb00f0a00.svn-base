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
          		<a href="javascript:;">浏览记录&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>浏览记录</h3>
				<div class='mc'>
					<div class='fav-goods-list'>
						<ul>
							<#if lookLogList??>
								<#list lookLogList as lookLog>
									<#if lookLog.product?? >
									<li>
										<div class='fav-goods-item'>
											<div class='p-img'>
												<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' target="_blank">
													<!--<#if lookLog.product.source == 2>
													<img src='${(jdConfig.IMAGE_PATH_160)!}${lookLog.product.masterImg}' width='160' height='160'>
													<#else>
													<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${lookLog.product.masterImg}' width='160' height='160'>
													</#if>-->
													<img src="${(getImagePathMethod((lookLog.product.source)!"",(lookLog.product.productCode)!""))!''}${(lookLog.product.masterImg)!""}" width="160" height="160">
												</a>
											</div>
											<div class='p-name'>
												<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' target="_blank" title="${(lookLog.product.name1)!''}">${(lookLog.product.name1)!''}</a>
											</div>
											<div class='p-price'>
												<strong>￥${(lookLog.product.mallPcPrice)?string("0.00")!''}</strong>
											</div>
											<div>
												浏览日期：${(lookLog.createTime)!}
											</div>
										</div>
									</li>
									</#if>
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
		$("#productLookLog").addClass("currnet_page");
		
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/viewed.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />