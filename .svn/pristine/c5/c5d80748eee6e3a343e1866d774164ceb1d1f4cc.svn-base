  <#include "/front/commons/_headbig.ftl" />
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/hover.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/message-list.css">
  <script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
 
<style type='text/css' rel="stylesheet">

		.setRead{
			font-size:14px;float:right;
		}
		.delete{
			font-size:14px;float:right;margin-left:10px
		}
</style>

		<div class='container'>
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
					<a href='javascript:void(0)'>消息中心</a>
				</span>
			</div>
		</div>
		<div class='container'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>消息中心</h3>
				<div class='mc'>
					<!--右文本区域-->
					  <#if messageList?? && messageList?size &gt; 0>
			          <div class="message-list col-md-10">
			            <ul>
			              	<#list	messageList as message>
			              		<li>
			              			<h4>&nbsp;&nbsp;${message.typeName} &nbsp;&nbsp;<#if message.isRead == 0><b class="unread">未读</b></#if>
			              			<span>
			              				<button type="button" class="delete" onclick="deleteMessage(${(message.id)!''},${(messageTypeId)!''})" >删除</button>
			              				<#if message.isRead == 0>
			              					<button type="button"  class="setRead" onclick="setRead(${(message.id)!''},this)">标为已读</button>
			              				</#if>
			              			</span>
			              			</h4>
					                <h6 class="clearfix">
					                  <span class="fl">${message.title}</span>
					                  <span class="fr col_666">${message.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
					                </h6>
					                <p>${message.content}</p>
			              		</li>
			              	</#list>
			            </ul>
			          </div>
			          </#if>
				</div>
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
			</div>
		</div>


<#include "/front/commons/_endbig.ftl" /> 
<script>
	function setRead(id,obj){
		$.ajax({
			type:"GET",
			url:domain+"/message/setRead.html",
			data:{id:id},
			success:function(data){
				if(data.data){
					$(obj).parents('li').find('.unread').remove();
					$(obj).remove();
				}else{
					jAlert("异常，请重试！");
				}
			}
		});
	}
	
	function deleteMessage(id,messageTypeId){
		if(confirm("是否确认删除")){
			if(messageTypeId == '' || messageTypeId == null){
				window.location.href=domain+"/message/del.html?id="+id;
				return;
			}
			window.location.href=domain+"/message/del.html?id="+id+"&messageTypeId="+messageTypeId;
		}
	}
</script>