<li style="color: #fff;">商家消息： <a class="wave in dropdown-toggle"
	data-toggle="dropdown" title="Help" href="javascript:;"> <i
		class="icon fa fa-envelope"></i> <span class="badge"><#if count??>${count}<#else>0</#if></span>
</a>
	<ul class="pull-right dropdown-menu dropdown-arrow dropdown-messages">
		<#if messagelist?? && messagelist?size &gt; 0>
		<#list messagelist as message>
		<li>
			<a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/message">
					<div class="message">
						<span class="message-sender" style="max-width: 79.5%;">${message.title}</span> 
						<span class="message-time">${(message.createTime)?string('yyyy-MM-dd')}</span> 
						<span class="message-body">${message.content}</span>
					</div>
			</a>
		</li>
		</#list>
		<#if messagelist?? && count &gt; 5>
		<li>
			<a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/message">
				<div class="message">
					<span class="message-time" style="position: relative;float: right;">更多&gt;&gt;</span>
				</div>
			</a>
		</li>
		</#if>
		<#else>
		<li>
			<a href="javascript:;">
				<div class="message" style="text-align: center;">
					没有未读消息
				</div>
			</a>
		</li>
		</#if>
	</ul>
</li>
