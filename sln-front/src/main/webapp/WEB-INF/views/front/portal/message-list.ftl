<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/message-list.css">

<script type="text/javascript">
	$(function(){
		
	});
</script>

    <!--主体区域-->
    <div class="main-container">
      <div class="container">
        <!--导航目录-->
        <div class="catalog-map">
          <a href="javascript:;" class="old-catalog">首页&nbsp;</a>&gt;
          <a href="javascript:;">消息列表&nbsp;</a>
        </div>
        <div class="content-wrap row">
          <!--左文本区域-->
          <div class="message-nav col-md-2">
            <ul id="typeList">
              <#if messageTypeList??>
              	<li <#if messageTypeId == 0>class="active"</#if>  typeId="">全部消息(${(unreadNum)?string(0)})</li>
              	<#list messageTypeList as type>
              		<li <#if messageTypeId == type.id>class="active"</#if> typeId="${type.id}">${type.typeName}(${type.unreadNum})</li>
              	</#list>
              </#if>
            </ul>
          </div>
          <!--右文本区域-->
          <div class="message-list col-md-10">
            <ul>
              <#if messageList??>
              <#list messageList as message>
              		<li messageId="${message.id}">
                		<h4>&nbsp;&nbsp;${message.typeName} &nbsp;&nbsp;
                			<#if message.isRead == 0>
                				<b class="unread">未读</b>
                			</#if>
                		</h4>
                		<h6 class="clearfix">
                  			<span class="fl">${message.title}</span>
                  			<span class="fr col_666">${message.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                		</h6>
                		<p>${message.content}</p>
             		</li>
              
              </#list>
              </#if>
            </ul>
          </div>
          <!-- 分页 -->

		  <div class="pagination-container"></div>
        </div>
        
      </div>
    </div>
    
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function () {
      
      	var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/message/messageList.html?id=${(messageTypeId)!''}" +
                  "&rows=8&page="+page;
              }
          });
          
        /*显示消息详情*/
        $('.message-list ul li').on('click', function () {
          var h4Txt = $(this).children('h4').html();
          var h6TxtFl = $(this).children('h6').children('.fl').text();
          var h6TxtFr = $(this).children('h6').children('.fr').text();
          var pTxtFr = $(this).children('p').html();
          var html = '';
              html += '<div class="massage-detail">';
              html += '<ul><li>';
              html += '<h4>'+ h4Txt +'</h4>';
              html += '<h6 class="clearfix">';
              html += '<span class="fl">'+ h6TxtFl +'</span>';
              html += '<span class="fr col_666">'+ h6TxtFr +'</span>';
              html += '</h6>';
              html += '<p>'+ pTxtFr +'</p>';
              html += '<div class="btn">';
              html += '<a class="confirm" href="javascript:;">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;';
              html += '<a href="javascript:;">查看反馈</a>';
              html += '</div>';
              html += '</li></ul>';
              html += '</div>';
              $('body').append(html);
               /*居中定位*/
              var detailW = $('.massage-detail ul').width();
              var detailH = $('.massage-detail ul').height();
              $('.massage-detail ul').css({
                marginLeft: -detailW/2,
                marginTop: -detailH/2
            });
            setRead($(this).attr("messageId"),$(this).find("h4 .unread"));
        });  
        
        
        

        /*确认*/
        $(document).on('click', '.confirm', function () {
          $(this).parents('.massage-detail').remove();
        });
        
        $("#typeList li").on('click',function(){
        	var id = $(this).attr("typeId");
        	if(id == '' || id == '0'){
        		window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/message/messageList.html";
        		return;
        	}
        	window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/message/messageList.html?id="+id;
        });
        
      });
      
      function setRead(id,obj){
		$.ajax({
			type:"GET",
			url:"${(domainUrlUtil.SLN_URL_RESOURCES)!}/message/setRead.html",
			data:{id:id},
			success:function(data){
				if(data.data){
					$(obj).remove();
				}else{
					jAlert("异常，请重试！");
				}
			}
		});
	  }
</script>
<#include "/front/portal/common/footer.ftl" />
