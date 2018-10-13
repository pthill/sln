
<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
<div class='incart-goods-box ps-container'>
	<div class='incart-goods'>
		<div class='sub-title'>
			<h4>最新加入的商品</h4>
		</div>
		<#assign getImagePathMethod="com.sln.web.util.freemarker.ProductImagePathModel"?new()/>
		<#list cartInfoVO.cartListVOs as cartListVO>
			<#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
               <#list cartListVO.cartList as cart>
               	<#if cartSource ==2>
				<dl>
					<dt class='shop-googs-name'>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${cart.actIntegral.id!0}.html' target="_blank" style="word-break: break-all;text-overflow: ellipsis;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;">${(cart.actIntegral.name)!'' }</a>
						${(cart.specInfo)!'' }
					</dt>
					
					<dd class='mcart-mj' style="display:block;">
						<a href='' title="${(cart.actIntegral.name)!'' }">
						<img width='80' height='80'  alt="${cart.actIntegral.name!''}" 
                        	src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${cart.productGoods.images!''}"/>
                        
                        </a>
					</dd>
					<dd class='mcart-price' style="display:block;">
						<em>¥${cart.actIntegral.price!0}×${(cart.count)!0}</em>
					</dd>
				</dl>
               	<#else>
               	<#assign product = cart.product />
                   <#assign productGoods = cart.productGoods />
				<dl>
					<dt class='shop-googs-name'>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${cart.productId!0}.html' target="_blank" style="word-break: break-all;text-overflow: ellipsis;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;">${(product.name1)!'' }</a>
						${(cart.specInfo)!'' }
					</dt>
					
					<dd class='mcart-mj' style="display:block;">
						<a href='' title="${(product.name1)!'' }">
						<!--<#if product.source == 2>
						<img width='80' height='80'  alt="${product.name1!''}" 
                        	src="${(jdConfig.IMAGE_PATH_160)!}${product.masterImg!''}"/>
						<#else>
						<img width='80' height='80'  alt="${product.name1!''}" 
                        	src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${productGoods.images!''}"/>\
						</#if>-->
                        <img width='80' height='80'  alt="${product.name1!''}" src="${getImagePathMethod(product.source,product.productCode)}${(productGoods.images)!""}"/>
                        </a>
					</dd>
					<dd class='mcart-price' style="display:block;">
						<em>¥${productGoods.mallPcPrice!0}×${(cart.count)!0}</em>
					</dd>
				</dl>
               	</#if>
                   
			</#list>
			</#if>
		</#list>
	</div>
</div>
<div class='checkout'>
	<#if cartSource ==2>
		
		<span class='checkout-price'> 共<i>${(cartInfoVO.totalNumber)!0}</i>种商品&nbsp;&nbsp;总计积分： <em>¥${cartInfoVO.checkedDiscountedCartAmount!'0'}</em>
	</span>
		<#else>
		<span class='checkout-price'> 共<i>${(cartInfoVO.totalNumber)!0}</i>种商品&nbsp;&nbsp;总计金额： <em>¥${cartInfoVO.cartAmount!'0.00'}</em>
		</span>
	</#if>
	
	<span>
		<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/cart/detail.html?cartSource=${cartSource}' class='btn btn-danger' target='_blank' style='color: #fff; padding: 4px 9px; margin-top: 10px;'>去购物车</a>
	</span>
</div>
<input type="hidden" id="totalNumber" name="totalNumber" value="${cartInfoVO.totalNumber!0}"/>
<#else>
	<!-- 如果没有商品的话显示这个 -->
	<div class='no-order'>
		<div class="emptycart">
	      <div class="emptycart_line"></div>
	      <div class="emptycart_txt"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/settleup-nogoods.png" alt="">购物车中还没有商品，赶紧选购吧</div>
	   </div>
	</div>
	<input type="hidden" id="totalNumber" name="totalNumber" value="0"/>
</#if>

