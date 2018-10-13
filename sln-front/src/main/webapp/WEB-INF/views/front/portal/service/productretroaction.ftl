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
          		<a href="javascript:;">缺货商品登记反馈&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder' id='productRegisterList'>
				<!-- 反馈详情区域 -->
				<h3>
				<h3>京东缺货商品登记反馈</h3>
				<div class='mc'>
				<#if productRegisterList??>
					<#list productRegisterList as productRegi>
						<ul class="ul_col2">
						<#if productRegi.productRegiStat??>
						  <#if productRegi.productRegiStat =='2'>
							  <li style="text-align: left;">
								  <h3>缺货补货成功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(productRegi.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</h3>
								    尊敬的${currentname}用户</br>
								   您提交的缺货商品名为<font color='red'>${productRegi.productName}</font>商品编号为<font color='red'>${productRegi.productCode}</font>的商品补货成功，您</br>
								   现在可对商品进行直接购买，感谢您对我们平台的支持！</br>
								   商品购买链接:<a target="_blank" href="${productRegi.productAddress}">${productRegi.productAddress}</a>
								</li>
								<#elseif productRegi.productRegiStat ==1?string>
								<li style="text-align: left;">
							   <h3>缺货登记成功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(productRegi.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</h3>
							     尊敬的${currentname}用户</br>
							    您提交的缺货商品名为<font color='red'>${productRegi.productName}</font>商品编号为<font color='red'>${productRegi.productCode}</font>的商品登记成功，我们</br>
							    会尽快为您提供该缺货商品，感谢您对我们平台的支持！</br>
							  </li>
							  <#else>
							   <li style="text-align: left;">
							  <h3>缺货登记打回&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(productRegi.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</h3>
							    尊敬的${currentname}用户</br>
							   您提交的缺货商品名为<font color='red'>${productRegi.productName}</font>商品编号为<font color='red'>${productRegi.productCode}</font>的商品登记打回</br>
							   打回原因:${productRegi.retroactionReason}</br>感谢您对我们平台的支持！</br>
							  </li>
						  </#if>
						</#if>
						</ul>
					</#list>
				</#if>
				</div>

			</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script type="text/javascript">
      $(function () {
      		//控制左侧菜单选中
			$("#retroaction").addClass("currnet_page");
       });
</script>
<#include "/front/portal/common/footer.ftl" />