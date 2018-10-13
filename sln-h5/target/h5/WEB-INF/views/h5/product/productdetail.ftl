<#include "/h5/commons/_head.ftl" />
<style>
	.sel-btn .disabled{
		color: #ccc;
	    border: 1px solid #ccc;
	    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0);
	}
</style>
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">详细介绍</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"><span class="fa fa-bars"></span></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
	<div class="s-container" id="container" style="padding-bottom:60px;">
		<!-- 轮播 -->
		<div style="width:100%; overflow:hidden; padding:10px 0; border-bottom:solid 1px #dedede; text-align:center;">
			<div class="swiper-container bgff" >
			    <div class="swiper-wrapper">
			    	<#if productLeadPicList?? && productLeadPicList?size &gt; 0 >
			    		<#list productLeadPicList as pic >
					        <div class="swiper-slide"><img src="${pic!''}" height="320" class="productImage"></div>
					    </#list>
			        </#if>
			    </div>
			    <div class="swiper-pagination"></div>
			</div>
		</div>

		<!-- 详情列表 -->
		<#if product?? && product.state?? && product.state == 6>
			<div class="goods-infor">
				<div class="bor-btom mar-bt">
					<div class="flex">
						<div style="padding-right:10px;">${(product.name1)!''}</div>
						<!-- <div><span class="fa fa-angle-right"></span></div> -->
					</div>
					<div class="salesty  pad-bt">${(product.name2)!''}</div>
					<div class="pad-bt prod-price" id="productPrice">¥${(goods.mallMobilePrice)?string("0.00")!'999999'}</div>
				</div>
				
				  <!-- S 领券 促销 -->
			      <div class="coupon-redemption" style="display:none" id="couponActDiv">
			        <!-- 领券 -->
			        <div id="couponListDiv"></div>
			        <!-- 促销 -->
			        <div id="actInfoDiv"></div>
			      </div>
			      <div class="coupon-redemption" style="display:none" id=flashSaleInfoDiv>
			        <!-- 限时抢购 -->
			      </div>
			      <!-- E 领券 促销 -->
	
				<div class="bor-btom mar-bt">
					
					<#if norms?? && norms?size &gt; 0>
						<div class="flex pad-bt">
					    	<div class="pad-r">已选：</div>
					    	<div class="flex-2" id="normAttr0"></div>
					    	<div class="flex-2" id="normAttr1"></div>
					    	<div class="flex-2" id="amount"></div>
					    </div>
						<#list norms as norm>
							<div class="flex pad-bt choosenorms">
								<div class="pad-r">${norm.name}：</div>
								<div class="flex-2 sel-btn" id="normsDiv${norm_index}">
									<#list norm.attrList as normattr>
										<a class="btn btn-default norm-min" id="${(normattr.id)!0}" 
										val="${normattr.id}" img-data="${(normattr.url)!''}"
										onclick="chooseNorm(this, ${norm_index}, ${(normattr.id)!0}, '${(normattr.name)!}')">${(normattr.name)!""}</a>
									</#list>
								</div>
							</div>
						</#list>
					</#if>
					<!-- 隐藏域 -->
					<!-- 规格值ID  -->
					<input  type="hidden" id="specId0" name="specId0" >
					<input  type="hidden" id="specId1" name="specId1" >
					<!-- 产品ID -->
					<input  type="hidden" id="productId" name="productId" value="${productId!''}">
					<input  type="hidden" id="productGoodsId" name="productGoodsId" value="${(goods.id)!''}">
					<input  type="hidden" id="goodsNormAttrId" name="goodsNormAttrId" value="${(goods.normAttrId)!''}">
					<input  type="hidden" id="goodsStock" name="goodsStock" value="${(goods.productStock)!0}">

					<div class="flex pad-bt">
						<div class="pad-r">数量：</div>
						<div class="flex-2">
	                        <a class="quantity-decrease" id="productMinus">
	                            <i class="fa fa-minus-square"></i>
	                        </a>
	                        <input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" class="quantity" size="4" value="1" id="number" onblur="modify();">
	                        <a class="quantity-increase"  id="productPlus"">
	                            <i class="fa fa-plus-square"></i>
	                        </a>
	                        <#--(库存<span id="productStockSpan">${(goods.productStock)!0}</span>件)
	                         <span style='line-height:46px;'>(库存<em id="productStock">${(goods.productStock)!'0'}</em>件)</span> -->
											<#if goods.productStock?? && goods.productStock &gt; 0>
			                                	 <span style='line-height:25px;' id="productStock">有货</span>
			                                <#else>
			                                	 <span style='line-height:25px;' id="productStock">无货</span>
			                                </#if>
						</div>
					</div>
				</div>

				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/product/info/${(product.id)!0}.html" class="block">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >详细介绍</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a>
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/product/spec/${(product.id)!0}.html" class="block">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >规格参数</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a>
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/product/comment/${(product.id)!0}.html" class="block">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >商品评价&nbsp;<font class="clr53">${(statisticsVO.productCommentsHighProportion)!"100"}%</font>好评</div>
						<div >${(statisticsVO.productCommentsAllCount)!"0"}人评价&nbsp;<span class="fa fa-angle-right"></span></div>
					</div>
				</a>
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/product/ask/${(product.id)!0}.html" class="block">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >商品咨询&nbsp;<font class="clr53">${(statisticsVO.productAskCount)!"0"}</font>条</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a>
	
			</div>
	        <!--  -详情列表end-->
	        
	        <!-- 店铺详情 -->
	        <div class="pad10 bgff" >
		    	<h2>
		    		<div><i class="fa fa-institution"></i>${(seller.sellerName)!''}</div>
		    	</h2>
		    	<div class="flex flex-pack-justify s-score">
		    		<div>商品评分：<font class="clr53">${(seller.scoreDescription)!'0'}</font></div>
		    		<div>服务态度：<font class="clr53">${(seller.scoreService)!'0'}</font></div>
		    	</div>
		    	<div class="flex flex-pack-justify s-score">
		    		<div>物流速度：<font class="clr53">${(seller.scoreDeliverGoods)!'0'}</font></div>
		    		<div>关注：<font class="clr53">${(seller.collectionNumber)!'0'}</font></div>
		    	</div>
		    	<div class="text-center pad-top">
		    	   <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.id)!0}.html" class="btn btn-default s-btn" role="button">进入店铺</a>
		    	   <#if statisticsVO??> 
						<#if statisticsVO.collectedShop=true>
							<a id="collectShop" href="javascript:;" class="btn btn-default s-btn" role="button" onclick="disCollectShop('${(seller.id)!''}')">取消收藏</a>
						<#else>
							<a id="collectShop" href="javascript:;" class="btn btn-default s-btn" role="button" onclick="collectShop('${(seller.id)!''}')">收藏店铺</a>
						</#if>
				   </#if> 
		    	</div>
		    </div>
	        <!-- 底部固定菜单 -->
	        <div class="fixed-nav">
	        	<div class="flex">
		        	<div class="flex-1">
		        		<a href="javascript:;" onclick="collectProduct()" class="block">
		        			<#if statisticsVO?? && statisticsVO.collectedProduct>
		        				<span class="fa fa-heart current"></span><font id="collectSpan">取消</font>
		        			<#else>
		        				<span class="fa fa-heart"></span><font id="collectSpan">关注</font>
		        			</#if>
		        		</a>
		        	</div>
	        		
		        	<div class="flex-1">
		        		<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/cart/detail.html" class="block pos_relative">
		        			<span class="fa fa-cart-plus"></span>购物车
		        			<font class="cart-num">${(cartNumber)!0}</font>
		        		</a>
		        	</div>
		        	<div class="flex-2 yellow-cart">
		        		<a id="addToCart" class="block">
		        			加入购物车
		        		</a>
		        	</div>
	        		<div class="flex-2 red-cart" >
	        			<a id="buyNow" class="block" href="javascript:;">
	        				立即购买
	        			</a>
	        		</div>
	        		
	        	</div>
	        </div>
        <#else>
			<div class=" mar-bt">
		        <h3 style="padding:20px 0; background:#fdf5f5; text-align:center;"><strong>该商品已下架，非常抱歉！</strong></h3>
		    </div>
		</#if>
		<!-- footer -->
	    <#include "/h5/commons/_footer.ftl" />
    </div>
    <input type="hidden" id="isCollectProduct" value="${(statisticsVO.collectedProduct)?string('true','false')!'false'}"/>
	<!-- 主体结束 -->

	
	<#include "/h5/commons/_statistic.ftl" />
    
	
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>
<!-- 轮播 -->
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script>
// 启用的属性
var effectAttr = new Array();
<#noescape>
<#if effectAttr??>
effectAttr = eval('${effectAttr}');
</#if>
</#noescape>
var normsNum = Number("${(normsNum)!'0'}");
</script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/normplugin.js'></script>
<script>
	$(document).ready(function () {
		var mySwiper = new Swiper ('.swiper-container', {
			autoplay: 4000,
			loop: true,
			// 如果需要分页器
			pagination: '.swiper-pagination',
		});
		
		// 加载活动信息
		showProductActInfo('${productId!0}','${(seller.id)!0}');
		showProductFlashSaleInfo('${productId!0}');
		
		$("#productMinus").click(function() {
			var number = parseInt($("#number").val(), 10);
			if (number <= 1) {
				number = 1;
			} else {
				number--;
			}
			$("#number").val(number);
			$("#amount").html(number)
		});

		$("#productPlus").click(function() {
			var number = parseInt($("#number").val(), 10);
			var goodsStock = parseInt($("#goodsStock").val(), 10);
			if (number >= goodsStock) {
				number = goodsStock;
			} else {
				number++;
			}
			$("#number").val(number);
			$("#amount").html(number)
		});
		
		// 默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(norms != null && norms != ""){
			$("#amount").html($("#number").val());
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				$("#normsDiv"+i).find("a").each(function(){
					var attrid = $(this).attr("id");
					if(attrid==strs[i]){
						$("#normAttr"+i).html($(this).html());
						$("#specId"+i).val(attrid);
						$(this).addClass("active").siblings().removeClass("active");
						return;
					}
				});
			}
		}
		
		// 只有规格有多个时需要初始化规格显示
		if (normsNum > 1) {
			NormChecker.init();
		}
		
		$("#addToCart").click(function(){
			if($(this).attr("disabled")){
				return;
			}
			//未登录不能添加购物车
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			
			var norms_ = $(".choosenorms");
			if(norms_.length > 1){
				//多规格，都选才能提交
				if(norms_.find("a.active").length < 2){
					$.dialog('alert','提示','请选择规格',2000);
					return;
				}
			} else if(norms_.length == 1){
				if(norms_.find("a.active").length < 1){
					$.dialog('alert','提示','请选择规格',2000);
					return;
				}
			}
			
			$.ajax({
				type : "POST",
				url :  domain+"/cart/addtocart.html",
				data : {productId:$("#productId").val(), productGoodId:$("#productGoodsId").val(),number:$("#number").val(),source:1},
				dataType : "json",
				success : function(data) {
					if(data.success){
						//跳转到添加购物车成功页面
						// alert("添加成功");
						$.dialog('alert','提示','添加成功',2000);
						var cartNum = parseInt($(".cart-num").html());
						cartNum = cartNum + parseInt($("#number").val());
						$(".cart-num").html(cartNum);
					}else{
						// alert(data.message);
						$.dialog('alert','提示',data.message,2000);
					}
				},
				error : function() {
					// alert("数据加载失败！");
					$.dialog('alert','提示','数据加载失败！',2000);
				}
			});
		});
		
		$("#buyNow").click(function(){
			if($(this).attr("disabled")){
				return;
			}
			//未登录不能立即购买
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			
			var norms_ = $(".choosenorms");
			if(norms_.length > 1){
				//多规格，都选才能提交
				if(norms_.find("a.active").length < 2){
					$.dialog('alert','提示','请选择规格',2000);
					return;
				}
			} else if(norms_.length == 1){
				if(norms_.find("a.active").length < 1){
					$.dialog('alert','提示','请选择规格',2000);
					return;
				}
			}
			
			$.ajax({
				type : "POST",
				url :  domain+"/cart/addtocart.html",
				data : {productId:$("#productId").val(), productGoodId:$("#productGoodsId").val(),number:$("#number").val(),source:1},
				dataType : "json",
				success : function(data) {
					if(data.success){
						// 跳转到订单结算页
						window.location.href=domain+"/order/info.html";
					}else{
						// alert(data.message);
						$.dialog('alert','提示',data.message,2000);
					}
				},
				error : function() {
					// alert("数据加载失败！");
					$.dialog('alert','提示','数据加载失败！',2000);
				}
			});
		});
		
	});
	
	// 数量输入框失去焦点
	function modify() {
		var number = $("#number").val();
		var goodsStock = parseInt($("#goodsStock").val(), 10);
		if (number == null || parseInt(number) < 1) {
			number = 1;
			$("#number").val(number);
		} else if (number != null && parseInt(number) > goodsStock) {
			number = goodsStock;
			$("#number").val(number);
		}
		$("#amount").html(number);
	}
	
	function chooseNorm(obj, normIndex, normAttrId, normAttrName) {
		if($(this).hasClass("disabled")){
			return;
		}
		var imageUrl = $(obj).attr('img-data');
		if(imageUrl != '' && imageUrl != null){
			imageUrl = "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}" + imageUrl;
			$(".swiper-wrapper").empty();
			$(".swiper-wrapper").append("<div class='swiper-slide'><img src='"+imageUrl+"' height='320' width='320' ></div>");
			$(".swiper-wrapper").css("transform","translate3d(0px, 0px, 0px)");
		}
		$("#normAttr"+normIndex).html(normAttrName);
		$("#specId"+normIndex).val(normAttrId);
		$(obj).addClass("active").siblings().removeClass("active");
		
		var normAttrId0 = $("#specId0").val();
		var normAttrId1 = $("#specId1").val();
		
		var normAttrIds = "";
		if (normAttrId0 != null && normAttrId0 != "") {
			normAttrIds = normAttrId0;
		}
		if (normAttrId1 != null && normAttrId1 != "") {
			normAttrIds = normAttrIds + "," + normAttrId1;
		}
		
		// 只有规格有多个时需要修改规格显示
		if (normsNum > 1) {
			NormChecker.init();
		}
		$.ajax({
			type : "POST",
			url :  domain+"/getGoodsInfo.html",
			data : {productId:$("#productId").val(), normAttrId:normAttrIds},
			dataType : "json",
			success : function(data) {
				var productGood = data.data;
				if(productGood.id!=null){
// 					if(productGood.state == 0){
// 						$.dialog('alert','提示','该货品暂时无法购买，请联系商家',2000);
// 						$("#buyNow").attr("disabled","disabled");
// 						$("#addToCart").attr("disabled","disabled");
// 						return;
// 					}
					
					//商城价格
					$("#productPrice").html("¥"+parseFloat(productGood.mallMobilePrice).toFixed(2));
					//库存
					$("#productStockSpan").html(productGood.productStock);
					//货品ID
					$("#productGoodsId").val(productGood.id);
					$("#goodsNormAttrId").val(productGood.normAttrId);
					if(productGood.productStock >0){
						$("#productStock").html("有货")
					}else{
						$("#productStock").html("无货")
					}
					// 货品库存
					$("#goodsStock").val(productGood.productStock);
					
					$("#buyNow").removeAttr("disabled");
					$("#addToCart").removeAttr("disabled");
				}else{
					//无货品信息 则不能添加购物车和购买
					// alert("货品信息为空，请与管理员联系");
					$.dialog('alert','提示','货品信息为空，请与管理员联系',2000);
					$("#buyNow").attr("disabled","disabled");
					$("#addToCart").attr("disabled","disabled");
				}
			},
			error : function() {
				// alert("数据加载失败！");
				$.dialog('alert','提示','数据加载失败！',2000);
			}
		});
	}
	
	function collectProduct() {
		//未登录不能添加收藏
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		var isCollectProduct = $("#isCollectProduct").val();
		if (isCollectProduct == "true") {
			$.ajax({
				type:'GET',
				dataType:'json',
				async:false,
				data:{productId:${(productId)!0}},
				url:domain+'/member/cancelcollectproduct.html',
				success:function(data){
					if(data.success){
						$("#collectSpan").html("关注");
						$("#isCollectProduct").val("false");
						$("#collectSpan").siblings("span.fa-heart").removeClass("current");
					}else{
						$.dialog('alert','提示',data.message,2000);
					}
				}
			});
		} else {
			$.ajax({
				type:'GET',
				dataType:'json',
				async:false,
				data:{productId:${(productId)!0}},
				url:domain+'/member/docollectproduct.html',
				success:function(data){
					if(data.success){
						$("#collectSpan").html("取消");
						$("#isCollectProduct").val("true");
						$("#collectSpan").siblings("span.fa-heart").addClass("current");
					}else{
						$.dialog('alert','提示',data.message,2000);
					}
				}
			});
		}
	}
	
	// 关注店铺
	function collectShop(id){
		// 未登录不能关注店铺
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{sellerId:id},
			url:domain+'/member/docollectshop.html',
			success:function(data){
				if(data.success){
					$.dialog('alert','提示','收藏成功',2000);
					$("#collectShop").html("取消收藏");
					$("#collectShop").attr("onclick","disCollectShop(" + id + ")");
				}else{
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
	
	// 取消关注店铺
	function disCollectShop(id){
		// 未登录不能取消关注店铺
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{sellerId:id},
			url:domain+'/member/cancelcollectshop.html',
			success:function(data){
				if(data.success){
					$.dialog('alert','提示','取消收藏成功',2000);
					$("#collectShop").html("收藏店铺");
					$("#collectShop").attr("onclick","collectShop(" + id + ")");
				}else{
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
	
	// 异步加载商品促销信息
	function showProductActInfo(productId, sellerId){
		var sellerName = "${(seller.sellerName)!''}";
		$.ajax({
			type : "POST",
			url :  domain+"/getproductactinfo.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载单品立减和满减
					if (productActVO.actSingle == null && productActVO.actFull == null) {
						// 都是空去掉下边线
						$(".coupon-redemption").css({"border-bottom":"0"});
					} else {
						var actHtml = '<div class="sales-box clearfix">';
						//actHtml += '	<span class="sales-tit">促销：</span>';
						actHtml += '	<div class="sales-content">';
						
						// 加载立减
						var actSingle = productActVO.actSingle;
						if (actSingle != null) {
							actHtml += '		<div class="sales-txt-price">';
							actHtml += '			<span class="sales-txt">立减</span>';
							if (actSingle.type == 1) {
								actHtml += '			<span>下单即享' + actSingle.discount + '元优惠</span>';
							} else if (actSingle.type == 2) {
								var dis = parseInt(parseFloat(actSingle.discount)*10);
								actHtml += '			<span>下单即享' + dis + '折优惠</span>';
							}
							actHtml += '		</div>';
						}
						
						// 加载满减
						var actFull = productActVO.actFull;
						if (actFull != null) {
							actHtml += '		<div class="sales-txt-price">';
							actHtml += '			<span class="sales-txt">满减</span>';
							// 满999.00减10.00，满4999.00减100.00，满12999.00减300.00
							var fullInfo = "";
							if (actFull.firstFull != null && actFull.firstFull > 0) {
								fullInfo += '满' + actFull.firstFull + '减' + actFull.firstDiscount;
							}
							if (actFull.secondFull != null && actFull.secondFull > 0) {
								fullInfo += '，满' + actFull.secondFull + '减' + actFull.secondDiscount;
							}
							if (actFull.thirdFull != null && actFull.thirdFull > 0) {
								fullInfo += '，满' + actFull.thirdFull + '减' + actFull.thirdDiscount;
							}
							actHtml += '			<span>' + fullInfo + '</span>';
							actHtml += '		</div>';
						}
						actHtml += '	</div>';
						actHtml += '</div>';
						
						$("#actInfoDiv").html(actHtml);
						$("#couponActDiv").show();
					}
					
					// 加载优惠券信息
					var couponList = productActVO.couponList;
					if (couponList != null && couponList.length > 0) {
						var couponListHtml = '<div class="coupon-redemption-box">';
						couponListHtml += '	<div class="coupon-box">';
						couponListHtml += '		<span class="part-note-msg">领券</span>';
						couponListHtml += '		<span class="coupon-total-nums">共' +couponList.length + '张</span>';
						couponListHtml += '	</div>';
						couponListHtml += '	<div class="vouchers-box">';
						couponListHtml += '		<ul class="slider-container">';
						
						for (var i=0; i < couponList.length; i++) {
							var coupon = couponList[i];
							couponListHtml += '			<a onclick=receiveCoupon(' + coupon.id + ')>';
							couponListHtml += '			<li class="slider-item">';
							couponListHtml += '				<div class="slider-item-box">';
							couponListHtml += '					<div class="item-left">';
							couponListHtml += '						<div class="expeNum">';
							couponListHtml += '							<span class="rmb">￥</span>';
							couponListHtml += '							<span class="actual-number">'+ coupon.couponValue +'</span>';
							couponListHtml += '						</div>';
							couponListHtml += '						<div class="condi_msg">满'+coupon.minAmount+'元可用</div>';
							couponListHtml += '					</div>';
							couponListHtml += '					<div class="item-right">';
							couponListHtml += '						<div class="item-right-up">领</div>';
							couponListHtml += '						<div class="item-right-down">取</div>';
							couponListHtml += '					</div>';
							couponListHtml += '				</div>';
							couponListHtml += '			</li>';
							couponListHtml += '			</a>';
						}
						couponListHtml += '		</ul>';
						couponListHtml += '	</div>';
						couponListHtml += '</div>';
						
						$("#couponListDiv").html(couponListHtml);
						$("#couponActDiv").show();
						initLiWidth(3);
					    leftSwipe();
					}
				} else {
					
				}
			}
		});
	}
	
	// 领取优惠券
	function receiveCoupon(couponId) {
		// 未登录不能领取
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
	 	$.ajax({
			type:"POST",
			url:domain+"/member/coupon/reveivecoupon.html",
			dataType:"json",
			data:{couponId:couponId},
			success:function(data){
				if (data.success) {
					$.dialog('alert','提示',"领取成功，您可在用户中心查看您的优惠券！",2000);
				} else {
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error:function(){
				$.dialog('alert','提示',"领取失败，请稍后再试！",2000);
			}
		});
	}
	
	
	// 异步加载限时抢购活动信息
	function showProductFlashSaleInfo(productId) {
		$.ajax({
			type : "POST",
			url :  domain+"/getproductflashinfo.html",
			data : {productId:productId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载限时抢购信息
					if (productActVO.actFlashSaleProduct != null) {
						var actFlashSaleProduct = productActVO.actFlashSaleProduct;
						
						var flashHtml = '<div class="sales-box clearfix">';
						flashHtml += '  <div class="sales-content">';
						flashHtml += '    <div class="sales-txt-price">';
						flashHtml += '      <span class="sales-txt">秒杀</span>';
						var stageType = productActVO.stageType;
						// 如果是正在进行
						if (stageType == 2) {
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀正在进行中&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">我要秒杀>></a></span>';
						} else if (stageType == 3) {
							// 如果是即将开始
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀即将开始&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">去看看>></a></span>';
						} else if (stageType == 1) {
							// 如果是已经结束
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀结束了~~~&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">去看看>></a></span>';
						}
						flashHtml += '    </div>';
						flashHtml += '  </div>';
						flashHtml += '</div>';
						$("#flashSaleInfoDiv").html(flashHtml);
						$("#flashSaleInfoDiv").show();
					}
				}
			}
		});
	}
	
	// 跳转到限时抢购页面
	function gotoFlashSale() {
		window.location.href=domain+"/product/${(productId)!0}.html?type=1";  
	}
</script>
</body>
</html>

<script type="text/javascript">
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>