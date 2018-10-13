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
          		<a href="javascript:;">收藏的店铺&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>关注的店铺</h3>
				<div class='mc'>
					<div class='fav-lists'>
						<div class='fav-list fav-list-box clearfix'>
						<#if sellerList??>
							<#list sellerList as seller>
						
							<!-- 店铺信息 -->
							<div class='i-store i-store-box'>
								<div class='i-logo'>
										<a href='' target="_blank">
											<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}/${(seller.sellerLogo)!''}' width='180' height='60'> 
										</a>
								</div>
								<ul class='list-info'>
									<li>
										<span class='label'>店铺名称：</span>
										${(seller.sellerName)!''}
									</li>
									<li>
										<span class='label'>关注人气：</span>
										${(seller.seller.collectionNumber)!0}人
									</li>
									<li>
										<span class='label'>服务评价：</span>
										<a href='javascript:void(0);'>9.81</a>
									</li>
									<li>
										<span class='label'>关注时间：</span>
										${(seller.createTime?string("yyyy-MM-dd"))!''}
									</li>
								</ul>
								<div class='btns'>
									<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.sellerId)!''}.html" onclick="" class='btn btn-default'>进入店铺</a>
								</div>
								<div class='btns'>
									<a href='javascript:void(0)' onclick="unfollow(${(seller.sellerId)!''})" class='btn btn-default'>取消关注</a>
								</div>
							</div>
							</#list>
						</#if>
							
						</div>
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
			$("#collectionShop").addClass("currnet_page");
			
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/collectshop.html?rows=5&page="+page;
              }
          });
       });
       
       //取消关注
		function unfollow(id){
			if(confirm("是否取消关注")){
				$.ajax({
					type:"GET",
					url:domain+"/member/cancelcollectshop.html",
					dataType:"json",
					async : false,
					data : {sellerId:id},
					success:function(data){
						if(data.success){
							//重新加载数据
							window.location.href=domain+"/member/collectshop.html";
						}else{
							jAlert(data.message);
						}
					},
					error:function(){
						jAlert("异常，请重试！");
					}
				});
			}
		}
</script>
<#include "/front/portal/common/footer.ftl" />