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
          		<a href="javascript:;">我的优惠券&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>我的优惠劵</h3>
				
				<div class='mc'>
					<div class="coupon-toolbar">
						<ul>
							<li class="active"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/coupon-use.html">未使用</a></li>
							<li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/coupon-use-yes.html">已使用</a></li>
						</ul>
					</div>

					<div class="voucher">
						<!-- 未使用 -->
						<div class="unused-box-use main-use selected">
						<#if couponUsers??>
							<#list couponUsers as couponUser>
								<#if !couponUser.timeout>
									<div class="unused-box">
										<div class="unused-left">
												<strong class="unused-price">￥${(couponUser.couponValue)!}</strong>
												<p class="unused-limit">【消费满 ${(couponUser.minAmount)!}元 可用】</p>
												<div class="unused-time"><#if couponUser.useStartTime??>${couponUser.useStartTime?string("yyyy-MM-dd HH:mm:ss")}</#if>
													--<#if couponUser.useEndTime??>${couponUser.useEndTime?string("yyyy-MM-dd HH:mm:ss")}</#if></div>
												<div class="unused-icon"></div>
										</div>
										<div class="unused-right">
												<div class="unused-range-item">
													<span class="">券&nbsp;&nbsp;编&nbsp;&nbsp;号：</span>
													<span class="txt">${(couponUser.couponSn)!}</span>
												</div>
												<div class="unused-range-item">
													<span class="">优惠价名称：</span>
													<span class="txt">${(couponUser.couponName)!}</span>
												</div>
												<div class="unused-range-item">
													<span class="">适用商家：</span>
													<span class="txt">${(couponUser.sellerName)!}</span>
												</div>
												<div class="unused-btns">
													<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(couponUser.sellerId)!0}.html" target='_blank' class="a-btn" target="_blank">立即使用</a>
												</div>
										</div>
									</div>
								<#else>
									<div class="unused-box">
										<div class="unused-left">
												<div class="bg-color-overdue"></div>
												<strong class="unused-price">￥${(couponUser.couponValue)!}</strong>
												<p class="unused-limit">【消费满 ${(couponUser.minAmount)!}元 可用】</p>
												<div class="unused-time"><#if couponUser.useStartTime??>${couponUser.useStartTime?string("yyyy-MM-dd HH:mm:ss")}</#if>
													--<#if couponUser.useEndTime??>${couponUser.useEndTime?string("yyyy-MM-dd HH:mm:ss")}</#if></div>
												<div class="unused-icon"></div>
												<div class="overdue-icon"></div>
										</div>
										<div class="unused-right">
												<div class="unused-range-item">
													<span class="">券&nbsp;&nbsp;编&nbsp;&nbsp;号：</span>
													<span class="txt">${(couponUser.couponSn)!}</span>
												</div>
												<div class="unused-range-item">
													<span class="">优惠券名称：</span>
													<span class="txt">${(couponUser.couponName)!}</span>
												</div>
												<div class="unused-range-item">
													<span class="">适用商家：</span>
													<span class="txt">${(couponUser.sellerName)!}</span>
												</div>
										</div>
									</div>
								</#if>	
								
							</#list>
						</#if>	
							
							
						</div>
					</div>

					<div class='mt10'>
						<!-- 分页 -->
						<div class="pagination-container"></div>
					</div>
			  </div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      		//控制左侧菜单选中
			$("#myecouponuser").addClass("currnet_page");
			
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/coupon-use.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />