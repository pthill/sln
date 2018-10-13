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
          		<a href="javascript:;">投诉&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
		<h3>投诉</h3>
		<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
			<tbody>
				<tr>
					<th>订单编号</th>
					<th style="width: 328px;">商品名称</th>
					<th>投诉时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<#if complaintList??>
					<#list complaintList as complaint>
						<tr>
							<td>
								<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/orderdetail.html?id=${(complaint.orderId)!''}' target='_blank' class='ftx-05'>${(complaint.orderSn)!''}</a>
							</td>
							<td>
								<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(complaint.productId)!0}.html' target='_blank' class='ftx-05'>${(complaint.productName)!''}</a>
							</td>
							<td>${(complaint.complaintTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							<td>
								<@cont.codetext value="${(complaint.state)!0}" codeDiv="SELLER_COMPLAINT"/>
							</td>
							<td>
								<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/complaindetail.html?infoId=${(complaint.id)!''}&&orderProductId=${(complaint.orderProductId)!''}&&orderId=${(complaint.orderId)!''}'  target='_self' class='acolorblue'>查看</a>
							</td>
						</tr>
					</#list>
				</#if>
			</tbody>
		</table>
		
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
		$("#complain").addClass("currnet_page");
		
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/complain.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />