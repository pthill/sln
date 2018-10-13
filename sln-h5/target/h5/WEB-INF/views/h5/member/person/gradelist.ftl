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
	   	  	 <span>${(member.name)!""}&nbsp;(经验值:${(member.gradeValue)!""})</span>
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


	  <#if memberGradeIntegralLogss??>
		<#list memberGradeIntegralLogss as memberGradeIntegralLogs>
	    <div class="oder-list sev-list">
	    	<h2 class="flex flex-pack-justify sev_regoods">
	    		<div style="flex:3">
	    		  <p>时间：${(memberGradeIntegralLogs.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
	    		  <p class="pad-top">描述：${(memberGradeIntegralLogs.optDes)!''}</p>
	    		   <p class="pad-top">积分：${(memberGradeIntegralLogs.value)!0}</p>
	    		</div>
	    		<div class="text-right" style="flex:1">
	    		   <p class="mar-bt"><font class="clr53">状态：
		  				<#if memberGradeIntegralLogs.optType == 6 || memberGradeIntegralLogs.optType == 7 || memberGradeIntegralLogs.optType == 9 || memberGradeIntegralLogs.optType == 12>
						减少
						<#else>
						增加
						</#if>
	    		   </font></p>
	    		</div>
	    	</h2>
	    </div>
		</#list>
	</#if>
	
	
	<div id="grade_more"></div>
			
	<#if memberGradeIntegralLogss?? && memberGradeIntegralLogss?size == pagesize>
		<div id="grade_more_json" class="text-center font14 pad-top2">查看更多<i class="fa fa-angle-double-down"></i></div>
		<div id="grade_more_json_no" class="text-center font14 pad-top2" style="display:none;">已展示全部记录</div>
	<#else>
		<div id="grade_more_json_no" class="text-center font14 pad-top2">已展示全部记录</div>
	</#if>
	<input type="hidden"  name="list_page" id="list_page" value="1" />
    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
$(function(){
	$("#grade_more_json").click(function(){
		var list_page = $("#list_page").val();
		list_page++;
		$("#list_page").val(list_page);
		
		var urljson = "${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/gradeJson.html?pageNum=" + list_page;
		
		var listJsonHtml = "";
		$.ajax({
            type:"get",
            url: urljson,
            dataType: "json",
            cache:false,
            success:function(data){
                if (data.success) {
                    $.each(data.data, function(i, info){
                    	listJsonHtml += "<div class='oder-list sev-list'>";
                    	listJsonHtml += "<h2 class='flex flex-pack-justify sev_regoods'>";
                    	listJsonHtml += "<div style='flex:3'>";
                    	listJsonHtml += "<p>时间：" + info.createTime + "</p>";
                    	listJsonHtml += "<p class='pad-top'>描述：" + info.optDes + "</p>";
                    	listJsonHtml += "<p class='pad-top'>积分：" + info.value + "</p>";
                    	listJsonHtml += "</div>";
                    	listJsonHtml += "<div class='text-right' style='flex:1'>";
                    	listJsonHtml += " <p class='mar-bt'><font class='clr53'>状态：";
                    	if(info.state == 6 || info.state == 7 || info.state == 9 || info.state == 12) {
                    		listJsonHtml += "增加";
                    	} else {
                    		listJsonHtml += "减少";
                    	}
                		listJsonHtml += " </font></p></div></h2></div>";
                    })
                    $("#grade_more").append(listJsonHtml);
                    if ((data.total) == ${(pagesize)!}) {
                        $("#grade_more_json").show();
                        $("#grade_more_json_no").hide();
                    } else {
                        $("#grade_more_json").hide();
                        $("#grade_more_json_no").show();
                    }
                }
            }
        });
	});
})
</script>

</body>
</html>