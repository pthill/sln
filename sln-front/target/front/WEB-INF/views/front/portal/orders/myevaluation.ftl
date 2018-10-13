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
          		<a href="javascript:;">我的评价&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
				<div class='wrapper_main myorder'>
				<h3>我的评价</h3>
				<div class='mc'>
					<table class='tb-void'>
						<thead>
							<tr>
								<th width='328'>商品信息</th>
								<th>购买时间</th>
								<th>评价状态</th>
							</tr>
						</thead>
						<tbody>
						<#if commentsList??>
								<#list commentsList as comment>
							<tr class='cli-box'>
								<td colspan="3">
									<ul class='pro-info'>
										<li class="fore1">
											<div class='p-info'>
												<div class='p-img fl'>
													<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(comment.productId)!0}.html" target='_blank'>
														<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${comment.productLeadLittle!''}" width='50' height='50'>
													</a>
												</div>
												<div class='p-name fl'>
													<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(comment.productId)!0}.html" target='_blank'>${(comment.productName)!''}</a>
												</div>
											</div>
										</li>
										<li class='fore2'>
											<span class='ftx03'>${(comment.createTime?string("yyyy-MM-dd"))!''}</span>
										</li>
										<li class='fore3'>
											<a href='javascript:void(0)' class="look-avalute" onclick="editEvaluat(this)">查看评价</a>
										</li>
									</ul>
									<!-- 查看评价 --> 
									<div class='my-evaluat look-box'>
										<div class='box-t'></div>
										<div class='evaluat-form'>
											<div class='item'>
												<span class="label">
													评分：
												</span>
												<div class='fl'>
													<span class='commstar look-commstart' value="${(comment.grade)!''}">
														<a href='javascript:void(0);' class='star1'></a>
														<a href='javascript:void(0);' class='star2'></a>
														<a href='javascript:void(0);' class='star3'></a>
														<a href='javascript:void(0);' class='star4'></a>
														<a href='javascript:void(0);' class='star5 '></a>
													</span>
												</div>
												<span class='msg-error-01 hide'>你的评分是偶们前进的动力</span>
											</div>
											<div class='item'>
												<span class="label">
													<em>*</em>
													描述相符：
												</span>
												<div class='fl'>
													<span class='commstar' attrname="description" value="${(comment.description)!''}">
														<a href='javascript:void(0);' class='star1' val="1"></a>
														<a href='javascript:void(0);' class='star2' val="2"></a>
														<a href='javascript:void(0);' class='star3' val="3"></a>
														<a href='javascript:void(0);' class='star4' val="4"></a>
														<a href='javascript:void(0);' class='star5' val="5"></a>
														
													</span>
												</div>
												<span class='msg-error-01 hide'></span>
											</div>
											<div class='item'>
												<span class="label">
													<em>*</em>
													服务态度：
												</span>
												<div class='fl'>
													<span class='commstar'  attrname="serviceAttitude" value="${(comment.serviceAttitude)!''}">
														<a href='javascript:void(0);' class='star1' val="1"></a>
														<a href='javascript:void(0);' class='star2' val="2"></a>
														<a href='javascript:void(0);' class='star3' val="3"></a>
														<a href='javascript:void(0);' class='star4' val="4"></a>
														<a href='javascript:void(0);' class='star5' val="5"></a>
													</span>
												</div>
												<span class='msg-error-01 hide'></span>
											</div>
											
											<div class='item'>
												<span class="label">
													<em>*</em>
													发货速度：
												</span>
												<div class='fl' >
													<span class='commstar' attrname="productSpeed" value="${(comment.productSpeed)!''}">
														<a href='javascript:void(0);' class='star1' val="1"></a>
														<a href='javascript:void(0);' class='star2' val="2"></a>
														<a href='javascript:void(0);' class='star3' val="3"></a>
														<a href='javascript:void(0);' class='star4' val="4"></a>
														<a href='javascript:void(0);' class='star5' val="5"></a>
													</span>
												</div>
												<span class='msg-error-01 hide'></span>
											</div>
											<div class='item'>
												<span class="label">
													心得：
												</span>
												<div class='cont fl'>${comment.content!''}</div>
											</div>
										</div>
									</div>
									<!-- end -->
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
		$("#myevaluation").addClass("currnet_page");
		
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
       
        function editEvaluat(obj){
			$(obj).parent().parent().siblings(".look-box").slideToggle();
			$(obj).parents(".cli-box").siblings().find(".look-box").slideUp();
		}
			
		//初始化星星 
		$(".commstar").each(function(){
			var index = $(this).attr('value');
			$(this).children(".star"+index).addClass("active");
		});
</script>
<#include "/front/portal/common/footer.ftl" />