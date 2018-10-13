<#include "/front/commons/_top.ftl" />
<div class='w1 header container'>
	<div id='logo'>
		<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html' target='_blank' class='link1'>
			<img src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/haihetaologo.png'>
		</a>
	</div>
    <div style="padding-top: 45px;float: left;margin-left: -35px;">
        <a style="text-decoration: none" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/cart/detail.html?cartSource=1">
            <span style="font-size: 16px;color:#FF8000;font-weight: 600">切换到普通购物车&nbsp;></span>
        </a>
    </div>
	<div class='stepflex-box fr'>
		<ul>
			<li class="current">
				<span class="fl">1.我的购物车</span><i class="fl"></i>
			</li>
			<li class="">
				<span class="fl">2.确认订单信息</span><i class="fl"></i>
			</li>
			<li class="">
				<span class="fl">3.成功提交订单</span><i class="fl lasti"></i>
			</li>
		</ul>
	</div>
</div>

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/order.css">
<div class='container'>
	<div class='container'>
		<div class='cart-filter-bar'>
			<ul class='switch-cart'>
				<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
					<li class='switch-cart-item curr'>
						<em style='color: #ed6f05;'>全部商品</em>
						<span class='number' id="totalCount">${cartInfoVO.totalNumber!0}</span>
					</li>
				</#if>
			</ul>
			<div class='cart-store'></div>
			<div class='clr'></div>
			<div class='w-line'>
				<div class='floater'></div>
			</div>
		</div>
	</div>
	<div class='cart-warp'>
		<div class='container'>
			<div id="cart-list-ajax">
				<#include "/front/cart/jifencart_list.ftl" />
			</div>
		</div>
	</div>
</div>
		
<script type="text/javascript">
	
	/**
	 * 异步加载货品信息
	*/
	function getNewCartInfo(cartSource){
		$.ajax({
			type : "POST",
			url  : domain+"/cart/getcartinfo.html?cartSource="+cartSource+"&rd=" + Math.random(),
			async:false,
			success : function(data) {
				$("#cart-list-ajax").empty();
				$("#cart-list-ajax").append(data);
				$("#totalCount").html($("#totalNumber").val());
			}
		});
	}

	/**
	* 数量增加
	*/
	function increment(obj){
		//数量增加操作 ,计算 小计
		var buynum = $(obj).siblings(".buy-num");
		buynum.val(parseInt(buynum.val())+1);
		checknum(buynum);
	}
	/**
	* 数量减少操作
	*/
	function decrement(obj){
		var buynum = $(obj).siblings(".buy-num");
		buynum.val(parseInt(buynum.val())-1);
		checknum(buynum);
	}
	
	//更新购物车某某件商品的数量
	function updateSingle(id,count){
		$.ajax({
			type : "POST",
			url :  domain+"/cart/updateCartById.html",
			data : {id:id,count:count},
			dataType : "json",
			async:false,
			success : function(data) {
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	
	//删除 
	function deleteSingle(obj,id){
		if(confirm("是否确定删除")){
			$.ajax({
				type : "GET",
				url :  domain+"/cart/deleteCartById.html",
				data : {id:id},
				dataType : "json",
				success : function(data) {
					if(data.success){
						getNewCartInfo(2);
					}else {
						jAlert("删除失败");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});	
		}
	}
	/**
	 * 输入购买数量后进行校验
	 */
	function checknum(obj){
		var val = $(obj).val();
		var datanow = $(obj).attr("data-now");
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(datanow);
			return false;
		}
		//如果值为1 不能点-
		var decrement = $(obj).parent().find(".decrement");
		if (parseInt(val)==1){
		    $(decrement).attr('disabled',true);
		}else{
		    $(decrement).removeAttr("disabled");
		}
		//判断是否大于库存量
		var productStock = $(obj).parent().parent().find(".productStock");
		if(productStock){
		    var pstock = $(productStock).html();
		    if(parseInt(val)>parseInt(pstock)){
		    	jAlert("库存量不足");
		    	val = pstock;
		    	$(obj).val(pstock);
		    }else{
		    	var purchase = $("#purchase").val();
		    	if(parseInt(val)>parseInt(purchase)){
		    		jAlert("超过限购数量，限购为"+purchase+"件");
		    		val = purchase;
		    		$(obj).val(purchase);
		    	}
		    }
		}else{
			jAlert("库存量为0，不能购买");
		    return false;
		}
		$(obj).attr("data-now",val);
		//更新购物车数据
		//获得购物车id
		var cartId = $(obj).siblings('.cartId').val();
		updateSingle(cartId,val);
		
		//重新加载单品信息
		getNewCartInfo(2);
	}
	
	//选中
	function checkedChange(obj){
		var checked = 0;
		if ($(obj).prop('checked')) {
			checked = 1;
		}
		var id = $(obj).val();
		$.ajax({
			type : "GET",
			url :  domain+"/cart/cartchecked.html",
			data : {id:id,checked:checked},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//重新加载单品信息
					getNewCartInfo(2);
				}else {
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("操作失败！");
			}
		});	
	}
	
	//全部选中
	function checkedChangeAll(obj){
		var checked = 0;
		if ($(obj).prop('checked')) {
			checked = 1;
		}
		$.ajax({
			type : "GET",
			url :  domain+"/cart/cartcheckedall.html",
			data : {checked:checked},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//重新加载单品信息
					getNewCartInfo(2);
				}else {
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("操作失败！");
			}
		});	
	}
	
	//去结算
	function toOrder(){
		var judge = false ; 
		$("[name='checkItem']").each(function(){
		    if($(this).attr("checked")){
		    	judge=true;
		   }
		});
		if(judge){
			window.location.href = domain+"/order/jifenCart.html";
		}else{
			jAlert("请选择需要结算的商品！");
		}
	}
	
</script>		
 <#include "/front/commons/_endbig.ftl" />
