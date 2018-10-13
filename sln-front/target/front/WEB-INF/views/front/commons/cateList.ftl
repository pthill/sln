
<div class=''>
	<#if cateList??> 
		<#list cateList as cate1>
			<div class='list-cashcade forel'>
				<span>
					<h3>
						${cate1.name}
					</h3> <s></s>
				</span>
				<div class='i-mc'>
					<div class='subitem'>
						<#list cate1.childs as cate2>
						<dl class='fore1'>
							<dt>
								<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/list/${cate2.id!0}.html' 
									target="_blank">${cate2.name }</a>
							</dt>
							<dd>
								<#list cate2.childs as cate3> 
									<em>
										<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/cate/${cate3.id!0}.html' 
											target="_blank">${cate3.name }</a>
									</em>
								</#list>
							</dd>
						</dl>
						</#list>
					</div>
				</div>
			</div>
		</#list> 
	</#if>
</div>


<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/list.js'></script>

