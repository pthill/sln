<!--
<footer class="text-center">
	<#if memberSession?? && memberSession.member??>
		<div class="ft_top mar-bt flex">
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html" class="block">${(memberSession.member.name)!''}</a></div>
			<div class="flex-1"><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">返回首页</a></div>
			<div class="flex-1"><a href="#" class="block">返回顶部</a></div>
		</div>
	<#else>
		<div class="ft_top mar-bt flex">
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
-->
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/index.js"></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js"></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/wallet.js"></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/echo.min.js"></script>
<script>
	echo.init();
</script>