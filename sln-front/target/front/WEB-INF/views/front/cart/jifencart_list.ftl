<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
<div class='yy-cart'>
	<div class='cart-main'>
		<div class='cart-thead'>
			<div class='column t-checkbox'>
				<div class='cart-checkbox'>
					<input type='checkbox' id="checkAllHead" class='yycheckbox' onchange="checkedChangeAll(this)" autocomplete="off" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if>>
				</div>
				全选
			</div>
			<!-- <div class='column t-checkbox'>&nbsp;</div> -->
			<div class='column t-goods'>商品</div>
			<div class='column t-props'></div>
			<div class='column t-price'>单价(积分)</div>
			<div class='column t-quantity'>数量</div>
			<div class='column t-sum'>小计(积分)</div>
			<div class='column t-action'>操作</div>
		</div>
		<#assign getImagePathMethod="com.sln.web.util.freemarker.ProductImagePathModel"?new()/>
		<div id='cartList'>
			<#assign isCheckAll = 1 />
			<#list cartInfoVO.cartListVOs as cartListVO>
			    <#assign seller = (cartListVO.seller) />
			    <div class='cart-item-list'>
			        <div class='cart-tbody'>
			            <div class='shop'>
			                <a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.id)!0}.html" class='shop-name self-shop-name'>${seller.sellerName!''}</a>
			            </div>
			            <div class='item-list'> 
			                <div class='item-full'> 
			                    <#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
			                    <#list cartListVO.cartList as cart>
			                    	<#if cart?? && cart.checked?? && cart.checked == 0>
			                    		<#assign isCheckAll = 0 />
			                    	</#if>
			                        <#assign product = cart.product />
			                        <#assign productGoods = cart.productGoods />
			                        <div class='item-last item-item item-selected'>
			                            <div class='item-form'>
			                            	<div class='cell p-checkbox'>
			                            		<input type='checkbox' name="checkItem" id="${(cart.id)!''}" value="${(cart.id)!''}" onchange="checkedChange(this)" class='yycheckbox' autocomplete="off" <#if cart?? && cart.checked?? && cart.checked == 1>checked="checked"</#if>>
			                            	</div>
			                                <div class='cell p-goods'>
			                                    <div class='goods-item'> 
			                                        <div class='p-img'>
			                                            <a target='_blank' href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${cart.actIntegral.id!0}.html">
				                                            	<img width="80" height="80" alt="${cart.actIntegral.name!''}" src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(productGoods.images)!}" >
			                                            </a> 
			                                        </div> 
			                                        <div class='item-msg'>
			                                            <div class='p-name'> 
			                                                <a target='_blank' href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${cart.actIntegral.id!0}.html">${product.name1!''}</a> 
			                                            </div> 
			                                        </div>
			                                    </div> 
			                                </div> 
			                                <div class='cell p-props'>${cart.specInfo!''}</div> 
			                                <div class='cell p-price'> 
			                                    <strong class='unit-price'>${(cart.actIntegral.price)!0}</strong> 
			                                </div> 
			                                <div class='cell p-quantity'> 
			                                    <div class='quantity-form'>
			                                        <input type=button  class='btn-reduce decrement' value='-' onclick='decrement(this)'>
			                                        <input type='text' class='itxt buy-num' data-now="${cart.count!''}" value="${cart.count!''}" onkeyup='checknum(this)'>
			                                        <a href='javascript:void(0);' class='increment'  onclick='increment(this)'>+</a>
			                                         <!-- 购物车id -->
			                                        <input class='cartId' type='hidden' value="${cart.id!0}" name='id' > 
			                                    </div>
			                                    <div class='ac ftx-03 quantity-txt'>
			                                        <#if cart.actIntegral?? && cart.actIntegral.stock &gt; 0>
			                                            	<#-- 库存量<span class='productStock'>${cart.actIntegral.stock!0}</span> -->
			                                            	<span class='productStock'>有货</span>
			                                            	<input type="hidden" id="purchase" name="purchase" value="${cart.actIntegral.purchase}">
			                                        <#else>
			                                            <span style='color:red'>无货</span>
			                                        </#if>
			                                    </div>
			                                </div> 
			                                <div class='cell p-sum'>
			                                    <strong class='subtotal'>
			                                        ${(cart.currDiscountedAmount)!0}
			                                    </strong>
			                                    <br>
			                                </div>
			                                <div class='cell p-ops'>
			                                    <a href='javascript:void(0);' class='cart-remove' onclick="deleteSingle(this,'${(cart.id)!0}')">删除</a>
			                                    <!-- <a href='javascript:void(0);'>收藏</a> -->
			                                </div>
			                            </div> 
			                        </div>
			                    </#list>
			                    </#if>
			                </div>
			            </div>
			        </div>
			    </div>
			</#list>
			
		</div>
	</div>
</div>
<div id='cart-floatbar'>
	<div class='clearing'>
		<div class='cart-toolbar'>
			<div class='toolbar-wrap'>
				<div class='options-box'>
					<div class='select-all'>
						
						<div class='cart-checkbox'>
							<input type='checkbox' id="checkAllFoot" class='yycheckbox' onchange="checkedChangeAll(this)" autocomplete="off" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if>>
						</div>
							全选 
						
					</div>
					<div class='operation'>
						<!--
						<a href='' class='remove-batch'>删除选中的商品</a>
						<a href='' class='follow-batch'>收藏</a>
						-->
					</div>
					<div class='toolbar-right'>
						<div class='normal'>
							<div class='comm-right'>
								<div class='btn-area'>
									<a href='javascript:;' class='submit-btn' onclick="toOrder()">
										去结算
										<b></b>
									</a>
								</div>
								<div class='price-sum'>
									<span class='txt'>总价（不含运费）：</span>
									<span class='price sumPrice'>
										<em id="sumPrice">￥${(cartInfoVO.checkedDiscountedCartAmount)!'0'}</em>
									</span>
									<br>
								</div>
								<div class='amount-sum'>
									已选择
									<em id="selectedCount">${cartInfoVO.totalCheckedNumber!0}</em>
									件商品
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<#else>
	<div class="cart-empty">
		<div class="message">
			<ul>
				<li class="txt">购物车空空的哦~，去看看心仪的商品吧~</li>
				<li>
					<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="ftx-05"> 去购物&gt;</a>
				</li>
			</ul>
		</div>
	</div>
</#if>
<input type="hidden" id="totalNumber" name="totalNumber" value="${(cartInfoVO.totalNumber)!0}"/>