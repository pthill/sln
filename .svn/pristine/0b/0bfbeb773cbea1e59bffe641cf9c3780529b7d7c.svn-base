<#include "/front/commons/_jifenheadbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/integral.css">
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/happymall.css"/>
<!-- S 主体 -->
<div class="vip-slide">
    <!-- 轮播图区域 start -->
    <div class="lunbo-container">
    	<div class="hd">
    	<!-- 底部小圆点 -->
        <ul class="">
            <#if actIntegralBanners??>
	        	<#list actIntegralBanners as actIntegralBanner>
                   <li class="bullet"></li>
                 </#list>
            </#if>
        </ul>
        <div class="bullet-bg"></div>
    	</div>
    	<ul class="bd">
    	<!-- 轮播图片 -->
    	 	<#if actIntegralBanners??>
	        	<#list actIntegralBanners as actIntegralBanner>
	        	 <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/${(actIntegralBanner.linkUrl)!}" target="_blank">
	        	 <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(actIntegralBanner.image)!}" class="img100" style="height:458px;"/></a></li>
                 </#list>
	       </#if>
      </ul>
    </div>
    <!-- 轮播图区域 end -->
    <!-- 用户昵称 -->
    <div class="pd6-box user-massage">
      	<div class="container">
					<div class="vip-countlf">
	          <div class="vip-info">
	              <div class="bradiu" >
	                  <a href="javascript:void(0);">
                            <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/user-icon.png" alt="" width="110" height="110">
                        </a>
	              </div>
	              <div class="vip-lvdetail">
	              		<#if member??>
	                        <div>
	                            <div class="vip-name" title="${(member.name)!}">${(member.name)!}</div>
	                            <div class="yellow">
	                            	<@cont.codetext value="${(member.grade)!0}" codeDiv="MEMBER_GRADE"/>
	                            </div>
	                            <div class="yellow">
	                            	<#if gradeValue?? && gradeValue != 0>
		                                距离下次升级还差&nbsp;<b>${gradeValue!}</b>&nbsp;个经验值
	                            	<#else>
	                            		您现在已经是最高等级
	                                </#if>
	                            </div> 
	                        </div>
	                    <#else>
                        	<a href="javascript:;" onclick="login();" class="vip_loginbtn">登　录</a>
                        </#if>
	              
	              		<!-- <h4>用户昵称</h4>
	                  <a href="javascript:;" onclick="login();" class="vip_loginbtn">登　录</a> -->
	              </div>
	          </div>
	          <div class="vip-pointbox">
	              <span class="mrg15 yellow">可用积分：<#if member??>${(member.integral)!0}<#else>0</#if></span>
	              <div class="mrg15">
	                  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/integral.html" target="_blank" class="a_blue a-jl">积分中心 > </a>
                      <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/2.html" target="_blank" class="a_blue">如何获取 ？</a>
	              </div>
	          </div>
		      </div>
      	</div>
    </div>
</div>
    <!-- S 主体 -->
<div class="main-bg">   
    <div class="container">
        <!-- 筛选 -->
        <div class="sea-box">
            <div class="tabsContent">
                <div class="choose-box-top">
                    <div class="choose-libox clearfix">
                        <div class="choose-label ellipsis fl">商品分类　<em>></em></div>
                        <div class="choose_tagbox fl">
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=0&grade=${(grade)!1}&sort=${(sort)!0}" class="line_cell floatleft"><span  <#if type=0>class="chsall"</#if>>全部</span></a>
                            <#if actIntegralTypes??>
        						<#list actIntegralTypes as actIntegralType>
                            		<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(actIntegralType.id)!0}&grade=${(grade)!1}&sort=${(sort)!0}" class="line_cell floatleft"><span <#if actIntegralType.id=type>class="chsall"</#if>>${(actIntegralType.name)!""}</span></a>
                            	</#list>
                			</#if>
                        </div>
                    </div>
                    <div class="choose-libox clearfix">
                        <div class="choose-label ellipsis fl">会员等级　<em>></em></div>
                        <div class="choose_tagbox fl">
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=1&sort=${(sort)!0}" class="line_cell floatleft"><span <#if grade=1>class="chsall"</#if>>普通会员</span></a>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=2&sort=${(sort)!0}" class="line_cell floatleft"><span <#if grade=2>class="chsall"</#if>>铜牌会员</span></a>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=3&sort=${(sort)!0}" class="line_cell floatleft"><span <#if grade=3>class="chsall"</#if>>银牌会员</span></a>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=4&sort=${(sort)!0}" class="line_cell floatleft"><span <#if grade=4>class="chsall"</#if>>金牌会员</span></a>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=5&sort=${(sort)!0}" class="line_cell floatleft"><span <#if grade=5>class="chsall"</#if>>钻石会员</span></a>
                        </div>
                    </div>
                </div>
                <div class="choose_box_bottom clearfix">
                    <div class="fl fl-bop">
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=0" class="choose_b_btn fl <#if sort=0>myselect</#if>">人　气</a>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=1" class="choose_b_btn fl <#if sort=1>myselect</#if>">最　新</a>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=2" class="choose_b_btn fl <#if sort=2>myselect</#if>">销　量</a>
                        <#if sort=3>
                        	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=4" class="choose_b_btn fl myselect btn-price">价　格
                        	<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/jt.png" width="12" height="16">
                            </a>
                        <#elseif sort = 4>
                        	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=3" class="choose_b_btn fl myselect btn-price">价　格
                        	<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/jtx.png" width="12" height="16">
                        	</a>
                        <#else>
                        	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html?type=${(type)!0}&grade=${(grade)!1}&sort=3" class="choose_b_btn fl">价　格</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <!-- 商品列表 -->
        <div class="good_box vip_goodbox">
            <ul class="clearfix">
            	<#if actIntegrals??>
      			<#list actIntegrals as actIntegral>
                <li class="list_pergood">
                    <div class="list_item_border">
                    	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${(actIntegral.id)!0}.html" target="_blank">
	                        <div class="list_img"><img class="lazy" data-original="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(actIntegral.image)!}" width="260" height="260">
	                        </div>
                        </a>
                        <div class="list_acinfo ellipsis">
	                        <span class="em-sty"><@cont.codetext value="${(actIntegral.gradeValue)!0}" codeDiv="MEMBER_GRADE"/></span>
	                       <#-- <#if actIntegral.sellerId==1><em class="em-sty-pd fr">平台自营</em></#if>-->
                        </div>
                        <div class="list_atit ellipsis">
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${(actIntegral.id)!0}.html" target="_blank"  title="${(actIntegral.productName)!''}">
                            	${(actIntegral.productName)!}
                            </a>
                        </div>
                        <div class="list_price">
                            <span>${(actIntegral.price)!}分</span>
                        </div>
                        <div class='look-goods limit'>
                            <#if seller?? && seller.isSelf == 1>
                                <span style="color: #ffffff;background-color: #E23A3A;display: block;border-radius: 2px;padding:0px 2px" class='fr fl'>自营</span>
                            </#if>
                        </div>
                        <div>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(actIntegral.sellerId)!0}.html" style="float: left;padding-top: 2px;"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/dp.png" ></a>
                            <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(actIntegral.sellerId)!0}.html" style="font-size: 12px;color: #999;float: left;padding-top: 1px;padding-left: 5px;">${(actIntegral.sellerName)!''}</a></span>
                        </div>
                    </div>
                </li>
                </#list>
      		</#if> 
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
function login() {
	showid('ui-dialog');
}
	
$(function(){
   // 轮播图
    if( $('.vip-slide .lunbo-container').length>0 ){
        $(".vip-slide .lunbo-container").slide({
        	mainCell:".bd",
        	autoPlay: true,
        	pnLoop: true,
        	effect:"left",
        	delayTime:"1000"
        });
    }  
    
    // 商品列表
    $(".list_item_border").on("mouseenter",function(){
        $(this).css({borderColor:"#ed6f05"});
    });
    $(".list_item_border").on("mouseleave",function(){
        $(this).css({borderColor:"#fff"});
    });
    
    // 关注海核云谷显示微信码
    $(".shortcut-right li").on("mouseover",function(){
		$(this).find(".imgwx-ej").show().parent(".menubox").addClass("show-wx").find(".mu-line").css({"color":"#fc8207","border-left":"1px solid #e0e0e0","border-right":"1px solid #e0e0e0"});
		$(this).find(".ci-t").css({"background-position":"-30px -17px"})
	});
	$(".shortcut-right li").on("mouseleave",function(){
		$(this).find(".imgwx-ej").hide().parent(".menubox").removeClass("show-wx");
		$(".mu-line").css({"color":"#fff","border-left":"0","border-right":"0"});
		$(".ci-t").css({"background-position":"-21px -17px"})
	});

    // 筛选
    $(".choose_tagbox a span").on("click",function(){
        $(this).addClass("chsall").parent().siblings().find("span").removeClass("chsall");
    });

    $(".choose_box_bottom .fl-bop a").on("click",function(){
        $(this).addClass("myselect").siblings().removeClass("myselect");
    });

 });
</script>
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />