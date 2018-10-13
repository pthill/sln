<#include "/front/commons/_headbig.ftl" />                              
<script type="text/javascript" src="${domainUrlUtil.SLN_STATIC_RESOURCES}/resources/front/js/My97DatePicker/WdatePicker.js"></script>
		<div class='container'>
			<div class='breadcrumb'>
				
				<span>
					<a href='javascript:void(0)'>个人中心</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>客户服务</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>缺货商品登记反馈</a>
				</span>
			</div>
		</div>
		<div class='container'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
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
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
			</div>
		</div>
<#include "/front/commons/_endbig.ftl" />