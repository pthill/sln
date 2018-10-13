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
          		<a href="javascript:;">我的咨询&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>我的咨询</h3>
				<div class='mc'>
					<table class='tb-void'>
						<thead>
							<tr>
								<th>咨询商品</th>
								<th >咨询内容</th>
								<th width="25%">咨询回复</th>
								<th width="13%">咨询时间</th>
							</tr>
						</thead>
						
						<tbody>
						<#if askList??>
							<#list askList as askinfo >
							<tr>
								<td>
								   <div class="clearfix" style="width:260px;">
										<div class='img-list zixun-img mg0 clearfix fl'>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(askinfo.productId)!0}.html' target='_blank' class='img-box'>
												<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${askinfo.productLeadLittle}' width='60' height='60' class='err-product' title='${(askinfo.productName)!''}'>
											</a>
										</div>
										<span>${(askinfo.productName)!''}</span>
									</div>
								</td>
								<td>
									${(askinfo.askContent)!''}
								</td>
								<td>
									${(askinfo.replyContent)!''}
								</td>
								<td>
									${(askinfo.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}
								</td>
							</tr>
								
							</#list>
						</#if>
						</tbody>
					</table>
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
		$("#myconsultation").addClass("currnet_page");
				
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/question.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />