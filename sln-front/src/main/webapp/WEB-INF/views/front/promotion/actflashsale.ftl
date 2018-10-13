<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/list.css">
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/activit.css">
		
	<div class="activit-main">
	
	<#if actFlashBanners??>
      <!-- 轮播图区域 start -->
		<div class="lunbo-container">
		    <div class="hd"><!-- 底部小圆点 -->
		        <ul class="">
		        	<#list actFlashBanners as actFlashBanner>
		            	<li class="bullet"></li>
		            </#list>
		        </ul>
		        <div class="bullet-bg"></div>
		    </div>
		    <ul class="bd"><!-- 轮播图片 -->
		    	<#list actFlashBanners as actFlashBanner>
		        	<li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/${(actFlashBanner.linkUrl)!}" target="_blank"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(actFlashBanner.image)!}" class="img100" width="1210px"/></a></li>
		        </#list>
		    </ul>
		</div>
		<!-- 轮播图区域 end -->
  	</#if>
      
   <#if actFlashSaleStageNow??>
      <div class="sections">
        <div class="section-title">
            <span class="icon-clock"></span>
            <span class="now-time">今日${(actFlashSaleStageNow.startTime)!}:00点开抢</span>
            <span class="low-price">整点开抢，全网最低</span>
            <div class="time-item">距离结束：
              <strong class="hour_show item-h">0时</strong>
              <strong class="minute_show item-m">0分</strong>
              <strong class="second_show item-s">0秒</strong>
            </div>
            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/qianggou-${(actFlashSaleStageNow.id)!}.html" class="a-more">更多></a>
        </div>
        
        <#if actFlashSaleStageNow.productList??>
	        <#list actFlashSaleStageNow.productList as saleProduct>
	        	<#if saleProduct_index < 5>
		        	<#if saleProduct.product??>
				        <div class="item">
				            <div class="item-dbox">
				              <#if saleProduct.stock gt 0>
				              	<span class="state" onclick="location.href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1'">抢购中...</span>
				              <#else>
				              	<span class="state-charterd"></span>
				              </#if>
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="item-box-a">
				              	<!--<#if saleProduct.product.source == 2>
				                <img class="lazy" data-original="${(jdConfig.IMAGE_PATH_160)!}${(saleProduct.product.masterImg)!}" alt="">
				                <#else>
				                <img class="lazy" data-original="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(saleProduct.product.masterImg)!}" alt="">
				                </#if>-->
				                <img class="lazy" data-original="${(getImagePathMethod((saleProduct.product.source)!"",(saleProduct.product.productCode)!""))!''}${(saleProduct.product.masterImg)!}" alt="">
				              </a>
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="disbox" title="${(saleProduct.product.name1)!''}">${(saleProduct.product.name1)!""}</a>
				              <div class="item-price">
				                <span class="new-price">￥${(saleProduct.price)?string("0.00")!""}</span>
				                <em>${(saleProduct.actualSales)!""}件已被秒杀</em>
				                <span class="old-price">￥${(saleProduct.product.marketPrice)?string("0.00")!""}</span>
				              </div>
				            </div>
				        </div>
		        	</#if>
	        	</#if>
	        </#list>
        </#if>
      </div> 
	</#if>

   <#if stageList??>
    <#list stageList as actFlashSaleStages>
      <div class="sections">
        <div class="section-title">
            <span class="icon-clock"></span>
            <span class="now-time">今日${(actFlashSaleStages.startTime)!}:00点开抢（未开始）</span>
            <span class="low-price">整点开抢，全网最低</span>
            <div class="time-item">
            </div>
            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/qianggou-${(actFlashSaleStages.id)!}.html" class="a-more">更多></a>
        </div>
        
        <#if actFlashSaleStages.productList??>
	        <#list actFlashSaleStages.productList as saleProduct>
	        	<#if saleProduct_index < 5>
		        	<#if saleProduct.product??>
				        <div class="item">
				            <div class="item-dbox">
				              <span class="state" onclick="location.href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1'">抢购中...</span>
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="item-box-a">
				              	<!--<#if saleProduct.product.source == 2>
				                <img class="lazy" data-original="${(jdConfig.IMAGE_PATH_160)!}${(saleProduct.product.masterImg)!}" alt="">
				                <#else>
				                <img class="lazy" data-original="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(saleProduct.product.masterImg)!}" alt="">
				                </#if>-->
				                <img class="lazy" data-original="${(getImagePathMethod((saleProduct.product.source)!"",(saleProduct.product.productCode)!""))!''}${(saleProduct.product.masterImg)!}" alt="">
				              </a>
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="disbox" title="${(saleProduct.product.name1)!''}">${(saleProduct.product.name1)!""}</a>
				              <div class="item-price">
				                <span class="new-price">￥${(saleProduct.price)!""}</span>
				                <em>${(saleProduct.actualSales)!""}件已被秒杀</em>
				                <span class="old-price">￥${(saleProduct.product.marketPrice)!""}</span>
				              </div>
				            </div>
				        </div>
		        	</#if>
	        	</#if>
	        </#list>
        </#if>
      </div> 
      </#list>
	</#if>
	
   <#if stageListOver??>
    <#list stageListOver as actFlashSaleStages>
      <div class="sections">
        <div class="section-title">
            <span class="icon-clock"></span>
            <span class="now-time">今日${(actFlashSaleStages.startTime)!}:00点开抢（已结束）</span>
            <span class="low-price">整点开抢，全网最低</span>
            <div class="time-item">
            </div>
            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/qianggou-${(actFlashSaleStages.id)!}.html?type=1" class="a-more">更多></a>
        </div>
        
        <#if actFlashSaleStages.productList??>
	        <#list actFlashSaleStages.productList as saleProduct>
		        <#if saleProduct_index < 5>
		        	<#if saleProduct.product??>
				        <div class="item">
				            <div class="item-dbox">
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="item-box-a">
				              	<!--<#if saleProduct.product.source == 2>
				                <img class="lazy" data-original="${(jdConfig.IMAGE_PATH_160)!}${(saleProduct.product.masterImg)!}" alt="">
				                <#else>
				                <img class="lazy" data-original="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(saleProduct.product.masterImg)!}" alt="">
				                </#if>-->
				                <img class="lazy" data-original="${(getImagePathMethod((saleProduct.product.source)!"",(saleProduct.product.productCode)!""))!''}${(saleProduct.product.masterImg)!}" alt="">
				              </a>
				              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1" class="disbox" title="${(saleProduct.product.name1)!''}">${(saleProduct.product.name1)!""}</a>
				              <div class="item-price">
				                <span class="new-price">￥${(saleProduct.price)!""}</span>
				                <em>${(saleProduct.actualSales)!""}件已被秒杀</em>
				                <span class="old-price">￥${(saleProduct.product.marketPrice)!""}</span>
				              </div>
				            </div>
				        </div>
		        	</#if>
	        	</#if>
	        </#list>
        </#if>
      </div> 
      </#list>
	</#if>
      
    </div>
 
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/list.js'></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.SuperSlide.2.1.1.js"></script>  			
<script type="text/javascript">
    $(function(){
    	// 轮播图
    if( $('.lunbo-container').length>0 ){
        $(".lunbo-container").slide({mainCell:".bd",autoPlay:true,pnLoop:true,effect:"left",delayTime:"400"});
    } 
    
      $('.item').on('mouseenter',function(){
          $(this).find('.state').show();
        })
      $('.item').on('mouseleave',function(){
          $(this).find('.state').hide();
      })

      function timer(time,d,h,m,s){
            window.setInterval(function(){
            var day=0,
                hour=0,
                minute=0,
                second=0;//时间默认值       
            if(time > 0){
                day = Math.floor(time / (60 * 60 * 24));
                hour = Math.floor(time / (60 * 60)) - (day * 24);
                minute = Math.floor(time / 60) - (day * 24 * 60) - (hour * 60);
                second = Math.floor(time) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $(d).html(day+"天");
            $(h).html('<s></s>'+hour+'时');
            $(m).html('<s></s>'+minute+'分');
            $(s).html('<s></s>'+second+'秒');
            time--;
            }, 1000);
        } 
        
        var tim = parseInt('${(countTime)!0}'); //传秒
        timer(tim,'.item-d','.item-h','.item-m','.item-s');
        
    })
</script>			

		</div>
<#include "/front/commons/logindialog.ftl" />		
<#include "/front/commons/_endbig.ftl" />
