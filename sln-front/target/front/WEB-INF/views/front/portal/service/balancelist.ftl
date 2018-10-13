<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">
<style>
	.myorder{
			width:82%;margin-left:10px
		}
</style>
<!--主体区域-->
<div class="main-container">
      <div class="container">
        	<!--导航目录-->
        	<div class="catalog-map">
          		<a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          		<a href="javascript:;">余额&nbsp;</a>
        	</div>
        	<div class="content-wrap row">
        
          		<!--左文本区域-->
          		<#include "/front/portal/common/left.ftl" />
          
          		<!--右文本区域 start-->
          		<div class='wrapper_main myorder'>
		<h3>收支明细</h3>
		<div style="margin-bottom:10px;">可用余额：<strong class="ftx01 num">￥${(member.balance)!'0.00'}</strong>  
			&nbsp;&nbsp;
			<#if member?? && member.balancePwd?? && member.balancePwd != ''>
				&nbsp;&nbsp;&nbsp;<a class="btn-5" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/editbalancepassword.html">修改支付密码</a>
			<#else>
				&nbsp;&nbsp;&nbsp;<a class="btn-5" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/setbalancepassword.html">设置支付密码</a>
			</#if>
				&nbsp;&nbsp;&nbsp;<a class="btn-5" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance/pay/recharge.html">充值</a>
		</div>
		
		<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
			<tbody>
				<tr>
					<th>序号</th>
					<th>时间</th>
					<th>存入</th>
					<th>支出</th>
					<th>接收入/赠予者</th>
					<th>备注</th>
				</tr>
				<#if infoList??>
					<#list infoList as info>
						<tr>
							<td>
								<a href='' target='_blank' class='ftx-05'>${info_index+1 }</a>
							</td>
							<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							<td>
							<!-- 1、充值；2、退款；3、消费；4、提款；5、系统添加；6、系统减少 -->
								<#if  info.state??>
					  				<#assign state = info.state/>
					  				<#if state==1||state==2||state==5||state==8> ￥${info.money}
					  				</#if>
			  		    		</#if>
							</td>
							<td>
								<#if  info.state??>
					  				<#assign state = info.state/>
					  				<#if state==3||state==4||state==6||state==7> ￥${info.money}
					  				</#if>
			  		    		</#if>
							</td>
							<td>
								${info.memberTwoName!""}
							</td>
							<td>
								${info.remark!""}
							</td>
						</tr>
					</#list>
				</#if>
			</tbody>
		</table>
		
		<!-- 分页 -->
		<div class="pagination-container"></div>
	</div>
          		<!--右文本区域 end-->
        	</div>
      </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      	//控制左侧菜单选中
		$("#balance").addClass("currnet_page");
		
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/balance.html?rows=5&page="+page;
              }
          });
       });
</script>
<#include "/front/portal/common/footer.ftl" />