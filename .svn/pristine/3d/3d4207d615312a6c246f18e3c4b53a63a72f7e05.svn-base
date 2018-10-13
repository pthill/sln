<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>海核云谷智慧园区综合服务平台</title>
		<meta name="Keywords" content="海核云谷智慧园区综合服务平台">
		<meta name="Description" content="海核云谷智慧园区综合服务平台">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"></link>
		<link  rel="stylesheet" type="text/css" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/bootstrap.min.css'></link>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/index.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/func.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/checkvalue.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/slider.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.SuperSlide.2.1.1.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.lazyload.js"></script>
		<style type='text/css' rel="stylesheet">
		</style>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
			var resource_path_ = '${(domainUrlUtil.SLN_STATIC_RESOURCES)!}';
		</script>
	</head>
	<#assign getImagePathMethod="com.sln.web.util.freemarker.ProductImagePathModel"?new()/>
	<body class='wp1200'>
			<div class='wrapper'>
			<div class='container'>
				<ul class='collect lh'>
					<li class='fore1'>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}' >海核云谷</a>
					</li>
				</ul>
				<ul class='shortcut-right lh'>
					<#if Session.memberSession??>
				   		<#assign user = Session.memberSession.member>
				   </#if>
				   <#if user??>
				   		<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html' target="_blank" class='login'>${(user.name)!''}</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/logout.html'  onclick="logout()" class='regist'>退出</a>
						</li>
						<li class='fore2 ld'>
							<span></span>
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html" target="_blank">我的订单</a>
						</li>
				   	 
				   	<#else>
						<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/login.html' class='login'>你好，请登录</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/register.html' class='regist'>免费注册</a>
						</li>
				   </#if>
					
					<li class='fore2-1 ld ff-vip'stle='padding-left:12px;'>
						<span></span>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html' target="_blank">会员中心</a>
					</li>
					
					<li class='fore3 ld app-ff menu' style="text-align:center;width: 126px;padding: 0 5px;">
						<div class="menubox">
							<div class="mu-line">
								<span></span>
								关注海核云谷<i class="ci-t"></i>
							</div>
							<div class="imgwx-ej" style="display: none;border:1px solid #e0e0e0;border-top:0;">
								<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/haiheyunguwx.jpg" width="80" height="80">
								<div style="padding: 0 6px; line-height: 20px;">海核云谷公众号</div>
							</div>
						</div>
					</li>
					
					<li class='fore3 ld app-ff menu' style="text-align:center;width: 78px;padding: 0 5px;">
						<div class="menubox">
							<div class="mu-line">
								<span></span>
								客户服务<i class="ci-t"></i>
							</div>
							<div class="imgwx-ej khfw clearfix " style="display: none">
								<div class="" style="text-align: left;">客户</div>
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_1.html">帮助中心</a>
								</div>
								<!--
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_2.html">店主之家</a>
								</div>
								-->
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_3.html">支付方式</a>
								</div>
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_4.html">售后服务</a>
								</div>
								<!--
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_5.html">客服中心</a>
								</div>
								-->
								<div class="item">
									<a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/type_6.html">关于我们</a>
								</div>
							</div>
						</div>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a target="_blank" href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/step1.html'>商家入驻</a>
					</li>
					<li class='fore2-1 ld ff-vip'stle='padding-left:12px;'>
						<span></span>
						<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/stock.html' target="_blank">京东缺货商品登记</a>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a target="_blank" href='http://www.hhyungu.com/'>联系我们</a>
					</li>
				</ul>
			</div>
		</div>
		
	<#if pcIndexImageTop??>
		<div class="top-banner" style="background: #F65382">
			<div class="container" style="position: relative;">
				<a href="javascript:;" class="a-close"></a>
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/${(pcIndexImageTop.linkUrl)!}">
					<img width="100%" src="${domainUrlUtil.SLN_IMAGE_RESOURCES!}/${(pcIndexImageTop.image)!}" alt="" />
				</a>
			</div>
		</div>
	</#if>
		
		<div >
			<div class='container' id='HeardTop'>
				<div class='ld' id='logo-img'>
					<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
						<img alt="" src="${domainUrlUtil.SLN_STATIC_RESOURCES!}/img/haihetaologo.png" >
					</a>
				</div>
				<div class='seach-box index-saeach-box'>
					<div class='i-search ld'>
						<ul class="hide-box" style="display: none">
						</ul>
						<div class='form'>
							<form action="${(domainUrlUtil.SLN_URL_RESOURCES)!}/search.html" method="get">
								<input type='text' id='keyword' name="keyword" value="${(keyword)!''}" class='text' autocomplete="off" style='color:rgb(153,153,153);'>
								<input type="hidden" id="source" name="source" value="1">
								<input type='submit' value='搜索' class='button'>
							</form>
						</div>
					</div>
					<div id='Hotwords'>
						<strong>热门搜索：</strong>
						<div id="keywordIDs"></div>
					</div>
				</div>
				
				
				<div class='settleup'>
					<dl class>
						<!-- 如果没有商品这里显示0 -->
						<div class='addcart-goods-num'>0</div>
						<!--  -->
						<dt class='ld first-dt'>
							<s></s> <a style="color: #ffffff" href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/cart/detail.html?cartSource=1'
								target="_blank">我的购物车</a> <b></b>
						</dt>
						<dd  id="priviewMycart">
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<!--商品分类 -->
		<div id='NavSort'>
			<div class='container'>
				<div class='all-category'>
					<div class='dts'>
						<a href='' target='_blank'>全部商品分类</a>
					</div>
					<div class='sec_attr'>
						<ul class='dd-inner '>
						<#if cateList??> 
							<#list cateList as cate1>
								<li class='odd' data-index='${cate1_index+1}'>
									<h3>
										<a href='' target='_blank'>${cate1.name!''}</a>
									</h3>
									<i>&gt;</i>
								</li>
							</#list> 
						</#if>
						</ul>
						<div class='dorpdown-layer'>
							<!-- 一级分类 -->
							<#if cateList??>
							<#list cateList as cate1>
							<div class='item-sub' id='index${cate1_index+1}'>
								<div class='subitems'>
									<!-- 二级分类  -->
									<#list cate1.childs as cate2>
									<dl class='fore-dl'>
										<dt>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/list/${cate2.id!0}.html' 
												target='_blank'>${cate2.name!'' }</a>
											<i>&gt;</i>
										</dt>
										<dd>
											<!-- 三级分类 -->
											<#list cate2.childs as cate3> 
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/cate/${cate3.id!0}.html' 
												target='_blank'>${cate3.name!'' }</a>
											</#list>
											<!-- 三级分类 end -->
										</dd>
									</dl>
									</#list>
									<!-- 二级分类  end -->
								</div>
							</div>
							</#list>
							</#if>
							<!-- 一级分类 end -->
							
						</div>
					</div>
				</div>
				<ul class='site-menu'>
					<li class='fore1'>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html'>首页</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/recommend.html#item'>优惠部落</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/qianggou.html'>整点抢购<i class="nav-icon hot-icon"></i></a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/tuan.html'>团购</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/bidding-sale.html'>阶梯竞价<i class="nav-icon new-icon"></i></a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/brand.html'>品牌馆</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/coupon.html'>优券集市</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html'>福利商城</a>
						</li>
				</ul>
				<!--轮播图上面的右侧小图 -->
				<#if pcIndexImageFloat??>
					<div class="banner-smimg">
						<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/${(pcIndexImageFloat.linkUrl)!}">
						<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}/${(pcIndexImageFloat.image)!}" alt="" /></a>
					</div>
				</#if>
			</div>
		</div>
 
	<!-- end -->
	<!-- 轮播图 
	<div id="banner_tabs" class="i-flexslider">
        <ul class="slides">
	        <#if bannerList?? && bannerList?size &gt; 0 >
				<#list bannerList as banner>
				<li>
	                <a title="" target="_blank" href="${(banner.linkUrl)!''}">
	                    <img width="1920" height="457" alt="" style="background: url(${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(banner.image)!''}) no-repeat center;">
	                </a>
	            </li>
				</#list>
			</#if>
        </ul>
        <ul class="flex-direction-nav">
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
        <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
	        <#if bannerList?? && bannerList?size &gt; 0 >
				<#list bannerList as banner>
					<li><a>${(banner_index + 1)!1}</a></li>
				</#list>
			</#if>
        </ol>
    </div>-->
    <#if bannerList?? && bannerList?size &gt; 0 >
    <div class="main-lbbox">
		<div class="index-lunbo-container clearfix">
	    <div class="hd">
	        <ul class="">
	        	<#list bannerList as banner>
	            	<li class="bullet">${banner_index+1}</li>
	            </#list>
	        </ul>
	        <div class="bullet-bg"></div>
	    </div>
	    <ul class="bd">
	    	<#list bannerList as banner>
		        <li>
		        	<a target="_blank" href="${(banner.linkUrl)!''}">
		        		<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(banner.image)!''}" alt="" width="100%" height="457"/>
		        	</a>
		        </li>
	        </#list>
	    </ul>
		</div>
	</div>
	</#if>
	<!-- end -->
	
	<!-- S add -->
	<#if pcIndexImageDowns ??>
		<div class="container">
			<div class="index15_ad">
				<ul>
				<#list pcIndexImageDowns as pcIndexImageDown>
					<#if pcIndexImageDown_index < 4>
						<li class="mg5">
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/${(pcIndexImageDown.linkUrl)!}">
								<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}/${(pcIndexImageDown.image)!}" alt="" width="100%" height="100%" />
							</a>
						</li>
					</#if>
				</#list>
				</ul>
			</div>
		</div>
	</#if>
	<!-- E add-->
	
	<!-- S 限时抢购 -->
	<div id="priviewMyQiangou"></div>
	<!-- E 限时抢购 -->
	
	<!-- 优惠部落 -->
	<#if hotList?? && hotList?size &gt; 0 >
		<div class='container'>
			<div class='gusess-like'>
				<div id='guessyou'>
					<div class='guess-title'>
						<h2>优惠部落</h2>
						<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/recommend.html" style="float:right; line-height:36px;">更多>></a>
					</div>
					<div class='guess-box guess-box-berd'>
						<div class='guess-spacer'></div>
						<ul>
							<#list hotList as recommend>
							
							<#if recommend?? && recommend.product??>
								<li>
									<div class='p-img' style="position:relative;">
										<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html' target='_blank' >
											<!--<#if recommend.product.source == 2>
											<img class="lazy" data-original='${(getImagePathMethod($(recommend.product.source),$(recommend.product.productCode)))!}${(saleProduct.product.masterImg)!}' title='${(recommend.product.name1)!""}'>
											<#else>
											<img class="lazy" data-original='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(recommend.product.masterImg)!""}' title='${(recommend.product.name1)!""}'>
											</#if>-->
											<img class="lazy" data-original='${(getImagePathMethod((recommend.product.source)!"",(recommend.product.productCode)!""))!''}${(recommend.product.masterImg)!""}' title='${(recommend.product.name1)!""}'>
											<div class="item-price-spread">
												<img width="60" height="60" class="img-icon" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/pricespread.png" alt="" />
												<span class="item-spread">省<span class="item-spread-money">${(recommend.discount)!""}</span></span>
											</div>
										</a>
									</div>
									<div class='p-info'>
										<div class='p-name'>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html'
												 target='_blank' title='${(recommend.product.name1)!""}'>${(recommend.product.name1)!""}</a>
										</div>
										<div class='p-price'>
											<i>¥</i>
											${(recommend.product.mallPcPrice)!"0.00"}
										</div>
									</div>
								</li>
								</#if>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</#if>
	<!-- end -->

	<!-- 楼层 -->
	<!-- 广告 -->
	<#if floorList?? && floorList?size &gt; 0>
	<#list floorList as floor>
		<#if floor?? && floor.advImage?? && floor.advImage != "">
			<div class='container'>
				<div class='floor-banner'>
				  <a href='${(floor.advLinkUrl)!"#"}' target='_blank'>
						<img src='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(floor.advImage)!""}' width='1210' height='100'/>
				  </a>
				</div>
			</div>
			<br />
		</#if>
		<div class='container'>
			<div class='lazy-fn'>
				<div class='floor-box guess-box-berd'>
					<div class='lazy-clothes'>
						<h2>
							<span class='floor-title'>${floor_index+1}F</span>
							${floor.name!""}
						</h2>
						<ul class='tab'>
							<#if floor.classList?? && floor.classList?size &gt; 0>
								<#list floor.classList as fc>
									<li class='tab-items <#if fc_index==0>tab-selected</#if>'>
										<a href='javascript:void(0);'>${fc.name!""}</a>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
					<div class='lazy-mc'>
						<div class='lazy-left'>
							<div class='lazy-inner'>
								<div class='left-box'>
									<a href='${(floor.masterLinkUrl)!""}' target='blank'>
										<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(floor.masterImage)!""}" width="329" height="400"/>
									</a>
								</div>
								<div class='brand-logo'>
								   <div class="brand-logo-in">
									<ul>
										<#if floor.patchList?? && floor.patchList?size &gt; 0>
										<#list floor.patchList as patch>
											<li>
												<a href='${(patch.linkUrl)!""}' target="_blank">
													${(patch.title)!""}
												</a>
											</li>
										</#list>
										</#if>
									</ul>
									</div>
								</div>
							</div>
						</div>
						
						<#if floor.classList?? && floor.classList?size &gt; 0>
						<#list floor.classList as fc>
							<div class='lazy-main <#if fc_index &gt; 0>hide</#if>'>
								<ul class='p-list'>
									<#if fc.dataList?? && fc.dataList?size &gt; 0>
									<#list fc.dataList as data >
										<#if data.dataType == 1 >
										   <#if data.product??>
											<li>
												<div class='p-img'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(data.product.id)!0}.html' 
														target='_blank' title='${(data.product.name1)!""}'>
														<!--<#if data.product.source??>
															<#if data.product.source == 2>
															<img class="lazy" data-original='${(jdConfig.IMAGE_PATH_160)!}${(data.product.masterImg)!""}' width="185" height="185">
															<#else>
															<img class="lazy" data-original='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(data.product.masterImg)!""}' width="185" height="185">
															</#if>
														</#if>-->
														<img class="lazy" data-original='${(getImagePathMethod((data.product.source)!"",(data.product.productCode)!""))!''}${(data.product.masterImg)!""}' title='${(data.product.name1)!""}'>
													</a>
												</div>
												<div class='p-name'>
													<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(data.product.id)!0}.html' 
														target='_blank'  title='${(data.product.name1)!""}'>
														${(data.product.name1)!""}
													</a>
												</div>
												<div class='p-price'>
													<span>￥</span><span>${(data.product.mallPcPrice)!''}</span>
												</div>
											</li>
										   </#if>
										<#elseif data.dataType == 2 >
											<li>
												<div class='add-p-img'>
													<a href='${(data.linkUrl)!""}' target='_blank' title='${(data.title)!""}'>
														<img class="lazy" data-original='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(data.image)!""}' >
													</a>
												</div>
											</li>
										</#if>
									</#list>
									</#if>
								</ul>
							</div>
						</#list>
						</#if>
					</div>
				</div>
				<!--  end-->
			</div>
		</div>
		<br />
		<br />
	</#list>
	</#if>
	<!-- end -->

	<!-- footer -->
		<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/index.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js"></script>
		
		<script>
			$(function(){
				// 初始化登录状态
				loginInfoInit();
				// 刷新购物车
				refreshMycart(1);
				// 限时抢购
				refreshMyQiangou();
			});
			
			// 异步加载用户登录信息
			function loginInfoInit() {
				$.ajax({
					type:"POST",
					url:domain+"/getloginuser.html",
					success:function(data){
						if(data.success){
							if (data.data != null && data.data.name != null) {
								// 移除未登录时显示的链接
								$("#loginbar").remove();
								// 构造登录信息
								var loginInfoHtml = "";
								loginInfoHtml += ("<li class='fore1' id='loginbar'>");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html' target='_blank' class='login'>" + data.data.name + "</a>&nbsp;&nbsp;");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/logout.html'  onclick='logout()' class='regist'>退出</a>");
								loginInfoHtml += ("</li>");
								loginInfoHtml += ("<li class='fore2 ld'>");
								loginInfoHtml += ("	<span></span>");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html' target='_blank'>我的订单</a>");
								loginInfoHtml += ("</li>");
								// 显示登录信息
								$(".shortcut-right").prepend(loginInfoHtml);
							} else {
							}
						}else{
						}
					},
					error:function(){
					}
				});
			}
		</script>
		<#include "/front/commons/_endbig.ftl" />
