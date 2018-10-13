<#include "/h5/commons/_head.ftl" />
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<body class="bgf2">

    <!-- 搜索框 -->
	<div class="fixedtop">
		<div class="search-cover" id="search-cover"></div>
		<div class="search-box flex">
			<div class="s-b-i">
				<!--<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/logox.png" width="50">-->
			</div>
	   	  	 <div class="flex-2 pos_relative">
	   	  	    <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/search-index.html" class="block">
	   	  	 	<span class="form-control"></span>
	   	  	 	<i class="fa fa-search"></i>
	   	  	 	</a>
	   	  	 </div>
	   	  	 <#if memberSession?? && memberSession.member??>
   	  	     <div class="s-b-t" id="loginTopDiv">
   	  	     	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/logout.html" class="block">
   	  	     	退出
   	  	     	</a>
   	  	     </div>
   	  	     <#else>
   	  	     <div class="s-b-t" id="loginTopDiv">
   	  	     	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/login.html" class="block">
   	  	     	登录
   	  	     	</a>
   	  	     </div>
   	  	     </#if>
		</div>
	</div>

	<!-- lunbo -->
	<div>
		<#if banners?? && banners?size &gt; 0 >
		<div class="swiper-container" id="i-swiper-container">
		    <div class="swiper-wrapper">
		    	<#list banners as banner>
		    	<div class="swiper-slide">
		    		<a href="${(banner.linkUrl)!''}" class="block">
		    			<img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!''}${(banner.image)!''}">
		    		</a>
		    	</div>
		        </#list>
		    </div>
		    <div class="swiper-pagination"></div>
		</div>
		</#if>
	</div>

	<!-- 导航菜单 -->
	<div class="i-menu-box padt_b10 mar-bt">
		<ul class="flex">
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/catelist.html" class="block">
					<i class="fa fa-th-large bg1"></i><br>	
					<span>分类查询</span>
				</a>
			</li>
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/cart/detail.html" class="block">
					<i class="fa fa-shopping-cart bg2"></i><br>
					<span>购物车</span>
				</a>
			</li>
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/index.html" class="block">
					<i class="fa fa-user bg3"></i><br>
					<span>会员中心</span>
				</a>
			</li>
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/order.html" class="block">
					<i class="fa fa-file-picture-o bg1"></i><br>
					<span>我的订单</span>
				</a>
			</li>
		</ul>
		<ul class="flex">
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/coupon.html" class="block">
					<i class="fa fa-cny bg4"></i><br>	
					<span>优券集市</span>
				</a>
			</li>
			<li class="flex-1 flex-25p">
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/bidding-sale.html" class="block">
					<i class="fa fa-sort-amount-desc bg5"></i><br>
					<span>阶梯竞价</span>
				</a>
			</li>
			<li class="flex-1 flex-25p" >
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/jifen.html" class="block">
					<i class="fa fa-hourglass-start bg6"></i><br>
					<span>积分商城</span>
				</a>
			</li>
			<li class="flex-1 flex-25p" >
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/tuan.html" class="block">
					<i class="fa fa-shopping-bag bg7"></i><br>
					<span>团购</span>
				</a>
			</li>
		</ul>
	</div>
    
    
    <!-- 掌上秒杀 -->
	<div id="priviewMyQiangou"></div>

	<!-- 多惠专区 -->
	<#if hotList?? && hotList?size &gt; 0 >
	<div>
		<div class="floor-container bestie-floor bdr-bottom">
			<div class="title-wrap clear">
				<h2 class="seckill-title bestie-tit"><i class="fa fa-gift"></i>多惠部落</h2>
				<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/recommend.html" class="seckill-more-link">更多优惠 ></a>
			</div>
			<div class="bestie-new-container">
				<ul class="bestie-new-list clear">
					<#list hotList as recommend>
					<#if recommend.product ??>
					<li class="bestie-new-item">
						<div class="bestie-item-img bdr-r">
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html" class="bestie-new-link">
								<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/loading.gif" data-echo="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(recommend.product.masterImg)!''}" alt="" border="0" >
								<div class="bestie-tip">
									<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/pricespread.png" alt="">
									<span class="item-spread">省<span class="item-spread-money">${(recommend.discount)!""}</span></span>
								</div>
							</a>
						</div>
						<div class="bestie-item-titl">
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html">${(recommend.product.name1)!""}</a>
						</div>
						<div class="bestie-item-price">
							<span class="bestie-new-price"><#if recommend.product.malMobilePrice??>¥${(recommend.product.malMobilePrice)?string('0.00')}</#if></span>
						</div>
					</li>
					</#if>
					</#list>
				</ul>
			</div>
		</div>
	</div>
    </#if>
    
    <!-- 列表 -->
	<div  style="padding:0 5px;" >
		<#if floors?? && floors?size &gt; 0 >
		<#list floors as floor>
			<#if floor.advImage??>
				<div class="container-col01">
					<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/${(floor.advLinkUrl)!}">
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/loading.gif" data-echo="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!''}/${(floor.advImage)!}" alt="">
					</a>
				</div>
			</#if>
		
			<div class="i-list-box mar-bt">
				<h2>${(floor.name)}</h2>
				<ul class="i-list-ul clear">
					<#if floor.datas?? && floor.datas?size &gt; 0 >
					<#list floor.datas as data >
						<#if data_index &lt; 4 >
						<!-- 如果是商品 -->
						<#if data.dataType?? && data.dataType == 1 >
						<#if data.product ??>
							<li>
								<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/product/${(data.product.id)!0}.html" class="block">
									<div class="i-list-img"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/loading.gif" data-echo="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!''}${(data.product.masterImg)!''}" width="144" height="144"></div>
									<div class="product_name">${(data.product.name1)!""}</div>
								</a>
								<div class="i-list-price">￥${(data.product.malMobilePrice)?string("0.00")!"0.00"}</div>
							</li>
						<#elseif data.dataType?? && data.dataType == 2>
							<li>
								<a href="${(data.linkUrl)!''}" class="block">
									<div class="i-list-img"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!''}/img/loading.gif" data-echo="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!''}${(data.image)!''}" width="144" height="144"></div>
									<div class="product_name">${(data.title)!""}</div>
								</a>
							</li>
						</#if>
						</#if>
						</#if>
					</#list>
					</#if>
				</ul>
			</div>
		</#list>
		</#if>
		<!-- <div class="text-center font14">点击继续加载 <i class="fa fa-angle-double-down"></i></div> -->
	</div>
	
<footer class="text-center" style="margin-bottom: 50px;">
	<#if memberSession?? && memberSession.member??>
		<div class="ft_top mar-bt flex" id="loginFootDiv">
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html" class="block">${(memberSession.member.name)!''}</a></div>
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">返回首页</a></div>
			<div class="flex-1"><a href="#" class="block">返回顶部</a></div>
		</div>
	<#else>
		<div class="ft_top mar-bt flex" id="loginFootDiv">
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/login.html" class="block">登录</a></div>
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/register.html" class="block">注册</a></div>
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">返回首页</a></div>
			<div class="flex-1"><a href="#" class="block">返回顶部</a></div>
		</div>
	</#if>
	<div>
	    Copyright©2017 海核云谷
	</div>
</footer>
	
<div class="" style="position: fixed;bottom:0px;left:0px;width:100%;">
	 <nav class="nav addnav" id="nav">
  	   <div class="flex flex-align-center navbar">
  	   	   <div class="flex-1" ><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/index.html" class="block"><span class="fa fa-home"></span>首页</a></div>
	   	   <div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/catelist.html" class="block"><span class="fa fa-glass"></span>分类</a></div>
	   	   <div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/cart/detail.html" class="block"><span class="fa fa-cart-plus"></span>购物车</a></div>
	   	   <div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/index.html" class="block"><span class="fa fa-user"></span>我的</a></div>
  	   </div>
  </nav>
</div>

<!-- footer -->
<#include "/h5/commons/_footer.ftl" />
<#include "/h5/commons/_statistic.ftl" />
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script type="text/javascript">
 	// 封装滑动方法
    /*左侧滑动*/
    function leftSwipe(parentbox){
		    /*父盒子*/
		    var parentBox = document.getElementsByClassName(parentbox)[0];
		    /*子盒子*/
		    var childBox = parentBox.getElementsByTagName('ul')[0];

		    /*父容器的高度*/
		    var parentW = parentBox.offsetWidth;
		    /*子容器的高度*/
		    var childW = childBox.offsetWidth;

		    /*定位区间 缓冲区间*/
		    var maxPosition = 0;
		    var minPosition = -(childW - parentW);

		    // var distance = 0;

		    /*滑动区间*/
		    // var maxSwipe = maxPosition + distance;
		    // var minSwipe = minPosition - distance;

		    /*公用方法*/
		    /*定位*/
		    var setTranslateX = function(translateX){
		        /*效率更高*/
		        childBox.style.transform = 'translateX('+translateX+'px)';
		        childBox.style.webkitTransform = 'translateX('+translateX+'px)';
		    }
		    /*加过渡*/
		    var addTransition = function(){
		        childBox.style.transition = 'all .2s ease';
		        childBox.style.webkitTransition = 'all .2s ease';
		    }
		    /*清楚过渡*/
		    var removeTransition = function(){
		        childBox.style.transition = 'none';
		        childBox.style.webkitTransition = 'none';
		    }

		    /*滑动*/
		    var startX = 0;/*开始Y坐标*/
		    var moveX = 0;/*滑动时候的Y坐标*/
		    var distanceX = 0;/*滑动的距离*/
		    /*记录当前的定位*/
		    var currX = 0;

		    childBox.addEventListener('touchstart',function(e){
		        startX = e.touches[0].clientX;
		    });
		    childBox.addEventListener('touchmove',function(e){
		        moveX = e.touches[0].clientX;
		        distanceX = moveX - startX;
		        removeTransition();
		        setTranslateX(currX + distanceX);

		    });
		    window.addEventListener('touchend',function(){
		        /*计算 当前滑动结束之后的位置*/
		        currX = currX + distanceX;

		        if(currX > maxPosition){
		            currX = maxPosition;
		            addTransition();
		            setTranslateX(currX);
		        }else if(currX < minPosition){
		            currX = minPosition;
		            addTransition();
		            setTranslateX(currX);
		        }

		        /*重置记录的参数*/
		        startX = 0;
		        moveX = 0;
		        distanceX = 0;
		    });
		}
		/*初始化滑块内每个li的宽度，并以此计算li父容器的总款
		* param showliNum 要展示的li个数
		*/
		function initLiWidth(liWdt,childli,clienbox,childul){
		    var currentLiNum=$(childli).length;
		    if(currentLiNum < 3){
		        $(childli).css("width","33.333%");
		    }else{
		        var enalbeViewWidth = $(clienbox).width();//获取可视化区域的宽度
		        var liwidth = liWdt;//计算li初始化宽度
		        var ulWidth = liwidth*currentLiNum;//计算出容器需要容纳的宽度
		        $(childli).css("width",liwidth);//初始化li的宽度
		        $(childul).css("width",ulWidth);//初始化父容器的宽度
		    }
		}

	<#if hotList?? && hotList?size &gt; 0 >
	    initLiWidth(170,".bestie-new-container ul li",".bestie-new-container",".bestie-new-container ul");
	    leftSwipe('bestie-new-container');
    </#if>
</script>   
<script>
$(document).ready(function () {
	var mySwiper = new Swiper ('.swiper-container', {
		autoplay: 4000,
		loop: true,
		// 如果需要分页器
		pagination: '.swiper-pagination',
	});
  
	//初始化登录状态
	loginInfoInit();
	
	refreshMyQiangou();

});

function refreshMyQiangou(){
 	 $.ajax({
		type:"GET",
		url:domain+"/indexqianggou.html",
		dataType:"html",
		async : true,
		success:function(data){
			//加载数据
			$("#priviewMyQiangou").html(data);
		},
		error:function(){
			jAlert("异常，请重试！");
		}
	});
}
	
//异步加载用户登录信息
function loginInfoInit() {
	$.ajax({
		type:"POST",
		url:domain+"/getloginuser.html",
		success:function(data){
			if(data.success){
				if (data.data != null && data.data.name != null) {
					// 移除未登录时显示的链接
					$("#loginTopDiv").empty();
					// 构造顶部登录信息
					var loginInfoTopHtml = "";
					loginInfoTopHtml += ("<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!''}/logout.html' class='block'>退出</a>");
					$("#loginTopDiv").html(loginInfoTopHtml);
					
					// 移除未登录时显示的链接
					$("#loginFootDiv").empty();
					// 构造底部登录信息
					var loginInfoFootHtml = "";
					loginInfoFootHtml += ("<div class='flex-1'><a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html' class='block'>" + data.data.name + "</a></div>");
					loginInfoFootHtml += ("<div class='flex-1'><a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html' class='block'>返回首页</a></div>");
					loginInfoFootHtml += ("<div class='flex-1'><a href='#' class='block'>返回顶部</a></div>");
					$("#loginFootDiv").html(loginInfoFootHtml);
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
</body>
</html>