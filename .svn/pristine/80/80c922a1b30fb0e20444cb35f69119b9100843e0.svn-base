<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">我的ejs</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"><span class="fa fa-bars"></span></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
	
	<div class="s-containerr cl70">
	   
	   <div class="user-perinfo">
	   	  <div class="perinfor-1 flex flex-pack-center flex-align-center">
	   	  	 <div class="text-center"><i class="fa fa-user"></i><br>
	   	  	 <div class="text-center"><@cont.codetext value="${(member.grade)!0}" codeDiv="MEMBER_GRADE"/></div>
	   	  	 <span>${(member.name)!""}&nbsp;(积分:${(member.integral)!""})</span>
	   	  	 </div>
	   	  </div>
	   	  <div class="flex userbar">
	   	  	 <div class="flex-1">
	   	  	 	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/order.html?orderState=1" class="block">
	   	  	 		<span>${(toBepaidOrders)!0}</span><br>待付款
	   	  	 	</a>
	   	  	 </div>
	   	  	 <div class="flex-1">
	   	  	 	<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/order.html?orderState=4" class="block">
	   	  	 		<span>${(toBeReceivedOrders)!0}</span><br>待收货
	   	  	 	</a>
	   	  	 </div>
	   	  </div>
	   </div>

	   <div class="user-menu mar_top bgff">
	   	  <dl class="menubox" >
	   	  	   <dt class="flex flex-pack-justify pad10">
	   	  	   	   <div><i class="fa fa-file-image-o"></i>&nbsp;订单中心</div>
	   	  	   	   <div><span class="fa fa-angle-down"></span></div>
	   	  	   </dt>
	   	  	   <dd>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/order.html" class="block">
				   	  <div class="user-menu-2">我的订单</div>
			   	  </a>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/question.html" class="block">
				   	  <div class="user-menu-2">我的咨询</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/comment.html" class="block">
				   	  <div class="user-menu-2">我的评价</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!''}/member/coupon/list.html" class="block">
				   	  <div class="user-menu-2">我的优惠券</div>
			   	  </a>
	   	  	   </dd>
	   	  </dl>
	   	  <dl class="menubox" >
	   	  	   <dt class="flex flex-pack-justify pad10">
	   	  	   	   <div><i class="fa fa-heart"></i>&nbsp;我的收藏</div>
	   	  	   	   <div><span class="fa fa-angle-down"></span></div>
	   	  	   </dt>
	   	  	   <dd>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/collect.html" class="block">
				   	  <div class="user-menu-2">收藏的商品</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/collect.html?type=seller" class="block">
				   	  <div class="user-menu-2">收藏的店铺</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/viewed.html" class="block">
				   	  <div class="user-menu-2">我的浏览记录</div>
			   	  </a>
	   	  	   </dd>
	   	  </dl>

	   	  <dl class="menubox" >
	   	  	   <dt class="flex flex-pack-justify pad10">
	   	  	   	   <div><i  class="fa fa-fax"></i>&nbsp;客户服务</div>
	   	  	   	   <div><span class="fa fa-angle-down"></span></div>
	   	  	   </dt>
	   	  	   <dd>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/back.html" class="block">
				   	 <div class="user-menu-2">我的退货</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/exchange.html" class="block">
				   	 <div class="user-menu-2">我的换货</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/applydrawing.html" class="block">
				   	 <div class="user-menu-2">申请提现</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance.html" class="block">
				   	 <div class="user-menu-2">我的余额</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/complaint.html" class="block">
				   	 <div class="user-menu-2">我的申诉</div>
			   	  </a>
	   	  	   </dd>
	   	  </dl>

	   	  <dl class="menubox" >
	   	  	   <dt class="flex flex-pack-justify pad10">
	   	  	   	   <div><i class="fa fa-file-video-o"></i>&nbsp;会员中心</div>
	   	  	   	   <div><span class="fa fa-angle-down"></span></div>
	   	  	   </dt>
	   	  	   <dd>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/integral.html" class="block">
				   	 <div class="user-menu-2">我的积分</div>
			   	  </a>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/grade.html" class="block">
				   	 <div class="user-menu-2">我的经验值</div>
			   	  </a>
	   	  	   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/info.html" class="block">
				   	 <div class="user-menu-2">我的个人资料</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/address.html" class="block">
				   	 <div class="user-menu-2">我的收货地址</div>
			   	  </a>
			   	  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/editpassword.html" class="block">
				   	 <div class="user-menu-2">修改密码</div>
			   	  </a>
	   	  	   </dd>
	   	  </dl>

	   </div>

    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

</body>
</html>