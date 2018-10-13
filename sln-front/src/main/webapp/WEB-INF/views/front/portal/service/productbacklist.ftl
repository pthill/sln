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
          		<a href="javascript:;">退货&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
				<h3>退货</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>订单编号</th>
							<th style="width: 328px;">商品名称</th>
							<th>退货数量</th>
							<th>申请时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<#if backList??>
							<#list backList as backinfo>
								<tr>
									<td>
										<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/orderdetail.html?id=${(backinfo.orderId)!''}' target='_blank' class='ftx-05'>${(backinfo.orderSn)!''}</a>
									</td>
									<td>
										<#if backinfo.orderType ==6>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${backinfo.actIntegralId!0}.html' target='_blank' class='ftx-05'>${(backinfo.productName)!''}</a>
										<#else>	
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(backinfo.productId)!0}.html' target='_blank' class='ftx-05'>${(backinfo.productName)!''}</a>
										</#if>
									</td>
									<td>${(backinfo.number)!0}</td>
									<td>${(backinfo.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
									<td>
										<#assign canComplain = 'false'/>
										<#if  backinfo.stateReturn??>
							  				<#assign state = backinfo.stateReturn/>
							  				<#if state==1>未处理
								  				<#elseif state==2>审核通过待收货
								  				<#elseif state==3>已经收货
								  				<#elseif state==4>不予处理
								  					<!-- 此时可以发起投诉 -->
								  					<#assign canComplain = 'true'/>
								  				<#else>
							  				</#if>
					  		    		</#if>
									</td>
									<td>
										<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/backdetail.html?backid=${(backinfo.id)!''}&&orderProductId=${(backinfo.orderProductId)!''}&&orderId=${(backinfo.orderId)!''}' class='acolorblue'>查看</a>
										<#if canComplain??>
											<#if canComplain=='true'>
											| <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/addbackcomplain.html?productBackId=${(backinfo.id)!''}&&orderProductId=${(backinfo.orderProductId)!''}&&orderId=${backinfo.orderId}' class='acolorblue'>投诉</a>
											</#if>
										</#if>
										
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
		$("#productback").addClass("currnet_page");
				
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/back.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />