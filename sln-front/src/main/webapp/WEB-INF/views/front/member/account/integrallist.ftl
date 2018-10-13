<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/userindex.css"/>
	<div class='container w1200'>
		<div class='breadcrumb'>
			<strong class='business-strong'>
				<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html'>首页</a>
			</strong>
			<span>
				&nbsp;>&nbsp;
				<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/index.html'>我的海核云谷</a>
			</span>
			<span>
				&nbsp;>&nbsp;
				<a href='javascript:void(0)'>积分账户</a>
			</span>
		</div>
	</div>
	
	<div class='container'>
		<#include "/front/commons/_left.ftl" />
	
	<!-- 右侧主要内容 -->
	<div class='wrapper_main myorder wrapper_main-wd'>
		<div class="user_info_top">
			<div class="user_info_l fl">
				<div class="user_avatar fl" >
					<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/user-icon.png" class="head_img" width="110" height="110">
				</div >
				<div class="user_avatar_r fl">
					<b>${commUtil.hideMiddleStr(member.name,2,2)}</b>
					<br>
					<#if member.grade == 1>
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/level1.jpg" alt="注册会员" title="注册会员" width="30" height="30">
					<#elseif member.grade == 2>
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/level2.jpg" alt="铜牌会员" title="铜牌会员" width="30" height="30">
					<#elseif member.grade == 3>
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/level3.jpg" alt="银牌会员" title="银牌会员" width="30" height="30">
					<#elseif member.grade == 4>
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/level4.jpg" alt="金牌会员" title="金牌会员" width="30" height="30">
					<#elseif member.grade == 5>
						<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/level5.jpg" alt="钻石会员" title="钻石会员" width="30" height="30">
					</#if>
					
					<div class="h20"><span>经验值：</span><span class="red">${(member.gradeValue)!}</span></div>
					<#if gradeValue!=0>
						<div class="h20"><span>距离下次升级还差经验值：</span><span class="red">${(gradeValue)!}</span></div>
					</#if>
				</div>
			</div>
			<div class="info-rcol">
				<ul class="acco-info fl">
					<li class="acco-item">
						<div>
							<em>余额：</em>
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance.html">${(member.balance)!}</a>
						</div>
						<div>
							<em>通用积分：</em>
							<a class="bold" href="">${(member.integral)!}</a>
						</div>
                        <div>
                            <em>专项积分：</em>
                            <a class="bold" href="">${(specialIntegral)!"0"}</a>
                        </div>
						<div>
							<em>优惠券：</em>
							<a class="bold" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/coupon-use.html">${(couponNum)!0}</a>
							<a class="pa10 a-col" target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/coupon.html">领券</a>
						</div>
					</li>
				</ul>
				<div class="verification-box fl">
					<#if member.isEmailVerify == 0>
						<div class="telphone-vf fl">
							<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/tel.jpg" alt="" width="50" height="72">
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/info.html" class="red">去验证</a>
							<p>
								<#if member.mobile??>
									${commUtil.hideMiddleStr(member.mobile,3,4)}
								</#if>
							</p>
						</div>
					<#else>
						<div class="telphone-vf fl">
							<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/telyes.jpg" alt="" width="50" height="72">
							<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/info.html" class="red">已验证</a>
							<p>
								<#if member.mobile??>
									${commUtil.hideMiddleStr(member.mobile,3,4)}
								</#if>
							</p>
						</div>
					</#if>
					<#if member.isSmsVerify == 0>
						<div class="telphone-vf fl">
							<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/yx.jpg" alt="" width="68" height="70">
							<a href="" class="colgreen">去验证</a>
							<p>
								<#if member.email??>
									${commUtil.hideMiddleStr(member.email,2,5)}
								</#if>
							</p>
						</div>
					<#else>
						<div class="telphone-vf fl">
							<img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/yxyes.jpg" alt="" width="68" height="70">
							<a class="colgreen">已验证</a>
							<p>
								<#if member.email??>
									${commUtil.hideMiddleStr(member.email,2,5)}
								</#if>
							</p>
						</div>
					</#if>
				</div>
			</div>
		</div>

		<div class="main-fram">
			<div class="jifen_top">
				<div class="fl itg">
					<span>可用积分：</span>
					<span class="colr">${(member.integral)!}</span>
					<span>分</span>
				</div>
				<div class="fr">
					<span style="font-size: 14px;">小积分大用处：</span>
					<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen.html" target="_blank" class="jf-btn">兑换商品</a>
				</div>
			</div>
			<div class="content_tit">
				<ul class="tabsTitle fl">
					<li class="current">积分明细</li>
				</ul>
				<div class="fr coupon_fr">
					<!--<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/news/2.html" target="_blank" class="colblue">积分规则说明</a>
					-->
				</div>
			</div>
			<div class="integral_list">
				<table class="integral_table">
					<tbody>
						<tr class="coupon_tabeltitle">
							<th style="width:30%">日期</th>
							<th style="width:30%">收入/支出</th>
							<th style="width:40%">详细说明</th>
						</tr>
						
						<#if memberGradeIntegralLogss??>
							<#list memberGradeIntegralLogss as memberGradeIntegralLogs>
								<tr>
									<td>${(memberGradeIntegralLogs.createTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
									<td><span class="colr">
									<#if memberGradeIntegralLogs.optType == 6 || memberGradeIntegralLogs.optType == 7 || memberGradeIntegralLogs.optType == 9 || memberGradeIntegralLogs.optType == 12 || memberGradeIntegralLogs.optType == 13>
									-
									<#elseif memberGradeIntegralLogs.optType==15>
									<#else>
									+
									</#if>
									${(memberGradeIntegralLogs.value)!0}</span></td>
									<td>${(memberGradeIntegralLogs.optDes)!''}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
					
			<#include "/front/commons/_pagination.ftl" />
		</div>

	</div>
	<script type="text/javascript">
	$(function(){
		//控制左侧菜单选中
		$("#integrallist").addClass("currnet_page");
	});
	</script>	
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/list.js'></script>
<#include "/front/commons/_endbig.ftl" />
