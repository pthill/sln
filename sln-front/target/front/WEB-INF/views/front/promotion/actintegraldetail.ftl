<#include "/front/commons/_jifenheadbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/integral.css">
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/happymall.css"/><style>
	.choosenorms .disabled{
		background-color: #f0ad4e;
		border-color: #eea236;
		cursor: not-allowed;
		filter: alpha(opacity=25);
		-webkit-box-shadow: none;
		box-shadow: none;
		opacity: .25;
		cursor: wait;
	}
	
	.choosenorms .disabled a{
		cursor: not-allowed;
	}
	
	.choosenorms .disabled a:hover{
		color: #ccc;
		border: 1px solid #ccc;
	}
	.subheader a{
		color:#666;
	}
</style>
		<div id='root-nav'>
			<div class='container'>
				<div class='subheader'>
					<strong>
						<#if productCatePP?? >
							${(productCatePP.name)!''}
						</#if>
					</strong>
					<span>
						&nbsp;>&nbsp;<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/list/${(productCateP.id)!0}.html'>${(productCateP.name)!''}</a>
						&nbsp;>&nbsp;<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/cate/${(productCate.id)!0}.html'>${(productCate.name)!''}</a>
						&nbsp;>&nbsp;
					</span>
					<span>
					<!-- 品牌的链接 -->
						<a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/brand/${(productBrand.id)!0}.html">${(productBrand.name)!''}</a>
						&nbsp;>&nbsp;
						${(product.name1)!''}
					</span>
				</div>
			</div>
		</div>
		
		<!-- S 商品主图介绍 -->
		<form action="" method="GET"  name="cartForm" id="cartForm" autocomplete="off">
		<!-- 产品ID -->
		<input  type="hidden" name="productId" value="${(product.id)!''}">
		<input  type="hidden" name="sellerId" value="${(seller.id)!''}">
		<input  type="hidden" name="productGoodsId" id="productGoodsId" value="${(goods.id)!''}">
		<input  type="hidden"  id='goodsNormAttrId' value="${(goods.normAttrId)!''}">
		<div id='p-box'>
			<div class='container'>
			
				<div class="clearfix container-xg">
				<div class="left-box">
					<div class="mainImg">
						<a href="javascript:;">
							<!-- <img src="img/mainimg.jpg" alt=""> -->
							<img class="lazy" style="width:360px;height:360px" data-original="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(actIntegral.image)!''}" />
						</a>
					</div>
				</div>
				<div class="right-box">
					<div class="right-box-pad">
						<h1 class="shop-name" style="color:#000">${(product.name1)!''}</h1>
						<div class="exchange_profile clearfix">
							<div class="span-profile">兑换简介：</div>
							<div class="span-pos">${(actIntegral.name)!''}</div>
						</div>
						<div class="timeinfo">
	          				<#if stageType?? && stageType == 1 >
								<!-- 即将开始 -->
								<div class="time-item">即将开始：
									<strong class="day_show">0天</strong>
									<strong class="hour_show">0时</strong>
									<strong class="minute_show">0分</strong>
									<strong class="second_show">0秒</strong>
		          				</div>
							<#elseif stageType?? && stageType == 2 >
								<!-- 正在进行 -->
								<div class="time-item">距离结束：
									<strong class="day_show">0天</strong>
									<strong class="hour_show">0时</strong>
									<strong class="minute_show">0分</strong>
									<strong class="second_show">0秒</strong>
		          				</div>
							<#else>
								<!-- 已经结束 -->
								<div class="time-item">兑换结束
		          				</div>
							</#if>
	          				<div class="number-item">
								<span class="num">${(actIntegral.virtualSaleNum + actIntegral.saleNum)!0}</span>件已被抢<#if stageType!=3>，剩余库存：${(actIntegral.stock)!0}</#if>
							</div>
						</div>
						<div class='summary-service'>
						<div class='dt'>商品编码：</div>
								<div id="productCode" >${(goods.sku)!''}</div>
						</div>
						<div id="Choose" class='p-choose-wrap tuan-choose-wrap'>
							<#if norms?? && norms?size &gt; 0>
							<#list norms as norm>
								<div id='ChooseNorm${norm_index}' class='li choosenorms'>
									<div class='dt'>${norm.name}：</div>
									<div class='dd norms' >
										<#list norm.attrList as normattr>
											<div class='item' 
												data-pic-url="${(normattr.url)!''}"
												val="${normattr.id}">
												<b></b>
												<a href='javascript:void(0);' class='norm-min' title='${normattr.name!''}' style="color: #333;">
												<#if (normattr.url)??>
												<img
													width="25" height="25"
													src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}/${(normattr.url)!}" 
													onerror="this.src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/nopic.png'" />
												</#if>
												${normattr.name}</a>
											</div>
										</#list>
										<!-- 规格值ID  -->
										<input  type="hidden" name="specId" class="attrid" >
										<!-- 规格详情， 用逗号分隔 ，例如颜色：黑色 -->
										<input  type="hidden" name="specInfo" class="attrname" >
									</div>
								</div>
							</#list>
							</#if>
						</div>
						
						
						<!-- <div class="club_info">
							库存：<span>36件</span>
						</div> -->
						<div class="club_info">
							积分：<span>${(actIntegral.price)!'99999999'}</span>
											
						</div>
						
						<div class="exchange-btn">
							<#if stageType?? && stageType == 1 >
								<a href="javascript:;" class="a-first btn-gray">即将开始</a>
							<#elseif stageType?? && stageType == 2 >
								<a href="javascript:;" class="a-first click-btn <#if actIntegral.stock lt 1>btn-gray</#if>">立即兑换</a>
								<button type="button" class="btn btn-danger addcart">加入购物车</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<#else>
								<a href="javascript:;" class="a-first btn-gray">兑换结束</a>
							</#if>
						</div>
					</div>
					<!--商家信息-->
					<div class="m-item-ext">
						<div class="extInfo" id="extInfo">
							<div class="seller-infor">
                                <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank' class="infor-name">
								${(seller.sellerName)!''}
								</a>
							</div>
							<div class="seller-pop-box">
								<div class="z-pop-desc-show">
									<dl class="pop-score-detail">
										<dt class="score-title">
											<span class="rating-name">商家满意度</span>
										</dt>
										<dd class="score-infor">
											<div class="score-part">
												<span class="score-desc">商品描述：<em class="score">${(seller.scoreDescription)!'0'}</em>分</span>
											</div>
											<div class="score-part">
												<span class="score-desc">服务态度：<em class="score">${(seller.scoreService)!'0'}</em>分</span>
											</div>
											<div class="score-part">
												<span class="score-desc">发货速度：<em class="score">${(seller.scoreDeliverGoods)!'0'}</em>分</span>
											</div>
										</dd>
									</dl>
									<div class="pop-shop-detail">
										<div class="item">
											<span class="label">店铺名称：</span>
											<span class="text"><a target="_blank" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.id)!0}.html">${(seller.sellerName)!''}</a></span>
										</div>
										<div class="item">
											<span class="label">所&nbsp;在&nbsp;地&nbsp;：</span>
											<span class="text"> ${(sellerLocation)!''}</span>
										</div>
									</div>
								</div>
							</div>
							<dl class="customer-service clearfix">
								<dd class="service">
									<span class="item">
										<b style="font-weight:700">联 系 客 服 </b>
									</span>
									<br>
                                    <div class='custom-service'>
									<#if sellerQqList?? && sellerQqList?size &gt; 0>
										<#list sellerQqList as qq>
                                            <span class='item'>
													<b>${(qq.name)!''}：</b>
													<a href='http://wpa.qq.com/msgrd?v=3&uin=${(qq.qq)!''}&Site=${(qq.qq)!''}&Menu=yes' target='_blank'><img src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/qq.jpg' width='25' height='25'></a>
												</span>
										</#list>
									</#if>
                                    </div>
								</dd>
							</dl>
							<div class="pop-shop-enter">
								<a href="../store/1.html" target="_blank" class="btn btn-default">进入店铺</a>
								&nbsp;&nbsp;
								<#if statisticsVO??>
									<#if statisticsVO.collectedShop=true>
										<a id="collectShop" href='javascript:void(0)' onclick="disCollectShop('${(seller.id)!''}')" class='btn btn-default'>取消收藏</a>
									<#else>
										<a id="collectShop" href='javascript:void(0)' onclick="collectShop('${(seller.id)!''}')" class='btn btn-default'>收藏店铺</a>
									</#if>
								</#if>
							</div>
						</div>
					</div>
					
				</div>
				</div>
			</div>
		</div>
		</form>
		<!-- E 商品主图介绍 -->
		<!--S 商品主体 -->
		<div class='container'>
			<div class='left'>
				<div id='browse-browse-pop' class='m m2 related-buy'>
					<div class='mt'>
						<h2>相关积分活动</h2>
					</div>
					<div class='mc'>
						<ul>
							<#if actIntegralTop?? && actIntegralTop?size &gt; 0>
								<#list actIntegralTop as top>
									<li class='fore1'>
										<div class='p-img'>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${top.id!0}.html' title='${(top.name)!""}'>
												<img width='160' height='160' alt='${(top.name)!""}' 
												class="lazy"
												data-original='${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(top.image)!""}'>
											</a>
										</div>
										<div class='p-name lift-font-line'>
											<a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/jifen/${top.id!0}.html' target='_blank' title=''>${(top.name)!""}</a>
										</div>
										<div class='p-price'>
											<strong>${(top.price)!0}(积分)</strong>
										</div>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
				</div>
			</div>
			<div class='right'>
				<div id='product-detail' class='m m1'>
					<div class='mt' id='pro-detail-hd'>
						<div class='mt-inner tab-trigger-wrap clearfix'>
							<ul class='m-tab-trigger'>
								<li class='li-curr curr trig-item' data-table='1'>
									<a href='javascript:void(0);' style="black;">商品详情</a>
								</li>
								<li class='li-curr trig-item' data-table='2'>
									<a href='javascript:void(0);'>规格参数</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- 商品介绍 -->
					<div class='b-table bcent-table' id='table1'>
						<div class='mc'>
							<div class='p-parameter'>
								<ul id='parameter2' class='p-parameter-list'>
									<li title=''>商品名称：${(product.name1)!'' }</li>
									<li title=''>店铺： <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank'>${(seller.sellerName)!''}</a></li>
									<#if productAttr?? && productAttr?size &gt; 0>
										<#list productAttr as attr>
											<li title=''>${(attr.name)!''}：${(attr.value)!''}</li>
										</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class='detail-content clearfix'>
							<div class='detail-content-wrap'>
								<div class='detail-content-item'>
									<p align='center'>
										 <#noescape> ${(actIntegral.descinfo)!''}</#noescape>
									</p>
								</div>
							</div>
						</div>
					</div>
					<!-- 规格参数-->
					<div class="b-table" id="table2">
					<#if product.source?? && product.source == 2>
						<#noescape> ${(product.jdparam)!''}</#noescape>
					<#else>
						<table cellpadding="0" cellspacing="1" width="100%" border="0" class="Ptable">
							<tbody>
								<tr>
									<th class="tdTitle" colspan="2">主体</th>
								</tr>
									
								<tr class="tdTitleContent">
									<td class="tdTitle">商品名称：</td>
									<td>${(product.name1)!'' }</td>
								</tr>
								<tr class="tdTitleContent">
									<td class="tdTitle">店铺：</td>
									<td>${(seller.sellerName)!''}</td>
								</tr>
								<tr class="tdTitleContent">
									<td class="tdTitle">上架时间：</td>
									<td>${(product.upTime?string("yyyy-MM-dd HH:mm:ss"))!'' }</td>
								</tr>
									<#if productAttr?? && productAttr?size &gt; 0>
										<#list productAttr as attr>
                                        <tr class="tdTitleContent">
                                            <td class="tdTitle">${(attr.name)!''}：</td>
                                            <td>${(attr.value)!''}</td>
                                        </tr>
										</#list>
									</#if>
							</tbody>
						</table>
					</#if>
					</div>
					
				</div>
			</div>
		</div>
		<!--E 商品主体 -->

		<!-- 彈出框 -->
		<div class="groupon-box ">
			<div class="groupon-Popup">
				<div class="attribute_buy_num">
		      <span class="attribute_buy_num_tit">购买数量：</span>
		      <div class="attribute_buy_num_val">
		        <span class="un_minus_icon" id="minus_num"></span>
		        <input type="text" value="1" id="buyNum" onkeyup="checknum(this)" autocomplete="off">
		        <span class="add_icon" id="add_num"></span>
		        <div class="limit_num">
		          单次限购
		          <span>${(actIntegral.purchase)!1}</span>
		          件
		        </div>
		        <button class="statement" id="buynowBtn">去结算</button>
		      </div>
		      <p class="tuan-num-check" id="checkError"></p>
		      <span class="groupon-box-close">×</span>
		    </div>
			</div>
		</div>

<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/list.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/details.js'></script>
<script>
//启用的属性
var effectAttr = new Array();
<#noescape>
<#if effectAttr??>
effectAttr = eval('${effectAttr}');
</#if>
</#noescape>
var normsNum = Number("${(normsNum)!'0'}");
</script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/normplugin.js'></script>
<script type="text/javascript">
	function timer(intDiff){
		window.setInterval(function(){
		var day=0,
		    hour=0,
		    minute=0,
		    second=0;//时间默认值
		if(intDiff > 0){
		    day = Math.floor(intDiff / (60 * 60 * 24));
		    hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		    minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		    second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		}
		if (minute <= 9) minute = '0' + minute;
		if (second <= 9) second = '0' + second;
		$('.day_show').html(day+"天");
		$('.hour_show').html('<s id="h"></s>'+hour+'时');
		$('.minute_show').html('<s></s>'+minute+'分');
		$('.second_show').html('<s></s>'+second+'秒');
		intDiff--;
		}, 1000);
	}
	
	$(function(){
		var intDiff = parseInt(${countTime!0});//倒计时总秒数量
        timer(intDiff);
		// 点击立即兑换弹出框
		$(".click-btn").on("click",function(){
			// 未登录不能团购
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var grade = "${(user.grade)!''}";
			
			if(grade != '' && Number(grade) < Number("${actIntegral.gradeValue}")){
				jAlert("对不起，您的会员等级不能换购该商品，谢谢。");		
				return;		
			}
			
			$(".groupon-box").addClass("show-groupon-box");
		});
		// 关闭弹出框
		$(".groupon-box-close").on("click",function(){
			$(".groupon-box").removeClass("show-groupon-box");
		});

		//默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(!isEmpty(norms)){
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				 $("#ChooseNorm"+i).find(".item").each(function(){
						var attrid = $(this).attr("val");
						if(attrid==strs[i]){
							 //规格详情
							var norminfo = $(this).parent().siblings(".dt").html();
							var attrinfo = $(this).find("a").attr("title");
							$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
							$(this).siblings(".attrid").val("").val($(this).attr("val"));
							$(this).addClass("selected").siblings().removeClass("selected");
							return;
						}
					});
			}
		}
		
		// 只有规格有多个时需要初始化规格显示
		if (normsNum > 1) {
			NormChecker.init();
		}
		
		//选择规格
		$(".choosenorms .item").click(function(){
			if($(this).hasClass("disabled")){
				return;
			}
			//加载图片
			var pic_ = $(this).data('pic-url');
			var url_ = ""
			if(pic_ != null && pic_ != ""){
				url_ = "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}"+pic_;
				$(".mainImg img").attr("src",url_);
			}
			
			//为隐藏域赋值
			$(this).siblings(".attrid").val("").val($(this).attr("val"));
			//规格详情
			var norminfo = $(this).parent().siblings(".dt").html();
			var attrinfo = $(this).find("a").attr("title");
			$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
			$(this).addClass("selected").siblings().removeClass("selected");
			
			// 只有规格有多个时需要修改规格显示
			if (normsNum > 1) {
				NormChecker.init();
			}
			//异步加载价格及库存信息
			queryPrice();
		});
		
		// 购买数量减少
		$("#minus_num").click(function() {
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) <= 1) {
				return;
			} else {
				$("#buyNum").val(parseInt(buyNum) - 1);
				$("#checkError").html("");
				$("#checkError").hide();
			}
		});
		
		// 购买数量增加
		$("#add_num").click(function() {
			var limitNum = ${(actIntegral.purchase)!0};
			var stock = ${(actIntegral.stock)!0};
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) >= parseInt(limitNum) || parseInt(buyNum) >= parseInt(stock)) {
				return;
			} else {
				$("#buyNum").val(parseInt(buyNum) + 1);
				$("#checkError").html("");
				$("#checkError").hide();
			}
		});
		
		//添加购物车  立即购买点击事件
		$(".buynow,.addcart").click(function(){
			var isAddcart = $(this).hasClass("addcart");
			
			//未登录不能添加购物车
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var grade = "${(user.grade)!''}";
			
			if(grade != '' && Number(grade) < Number("${actIntegral.gradeValue}")){
				jAlert("对不起，您的会员等级不能换购该商品，谢谢。");		
				return;		
			}
			
			//如果有规格，判断是否选择了规格，如果没有规格则判断是否有货品ID
			//默认只有两个规格
			var Selectcolor = $(this).parents("#itemInfo").find("#ChooseNorm0 .item");
			var SelectVersion = $(this).parents("#itemInfo").find("#ChooseNorm1 .item");
			if(Selectcolor.hasClass("selected") && SelectVersion.hasClass("selected")){
				$(this).parents("#itemInfo").find(".tzm-border").css("display",'none');
			}else{
				// 如果是没有规格的商品，则判断隐藏的货品ID，如果有则通过，没有则报错
				var goodId = $("#productGoodsId").val();
				if (goodId == null || goodId == "") {
					$(this).parents("#itemInfo").find(".tzm-border").css("display",'block');
					return false;
				}
			}
			
			var params = $("#cartForm").serialize();
			params = params+"&source=2&actIntegralId="+${(actIntegral.id)!''}
			if($("#cartForm").valid()){
				 $.ajax({
					type : "POST",
					url :  domain+"/cart/addtocart.html",
					data : params,
					dataType : "json",
					success : function(data) {
						if(data.success){
							if(isAddcart){
								//跳转到添加购物车成功页面
								window.location.href=domain+"/cart/add.html?id=" + data.data+"&cartSource=2&actIntegralId="+${(actIntegral.id)!''};
							}else{
								//跳转到提交订单页面
								window.location.href=domain+"/order/info.html";
							}
						}else{
							jAlert(data.message);
						}
					},
					error : function() {
						jAlert("数据加载失败！");
					}
				});
			}
		});
		
		// 立即兑换
		$("#buynowBtn").click(function(){
			// 未登录不能团购
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var stock = ${(actIntegral.stock)!0};
			if (parseInt(stock) < 1) {
				$("#checkError").html("已经被抢光了，下次要赶早哦！");
				$("#checkError").show();
				return false;
			}
			
			var buyNum = $("#buyNum").val();
			var limitNum = ${(actIntegral.purchase)!0};
			var stock = ${(actIntegral.stock)!0};
			// 判断是否为正整数
			if(!isIntege1(buyNum)){
				$("#buyNum").val(1);
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				return false;
			}
			if (parseInt(buyNum) < 1) {
				$("#buyNum").val(1);
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				return false;
			} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				if (parseInt(limitNum) < parseInt(stock)) {
					$("#buyNum").val(parseInt(limitNum));
				} else {
					$("#buyNum").val(parseInt(stock));
				}
				return false;
			}
			
			var goodsId = $("#productGoodsId").val();
			if (goodsId == null || goodsId == "" || goodsId == 0) {
				$("#checkError").html("请选择购买的商品。");
				$("#checkError").show();
				return false;
			}
			window.location.href=domain+"/order/jifen-" + ${(product.id)!''}+ "-" + goodsId + "-" + ${(seller.id)!''} + "-" + ${(actIntegral.id)!''} + "-" + buyNum + ".html";
		});
		
	});

	/**
     * 输入购买数量后进行校验
	 */
	function checknum(obj){
		var buyNum = $(obj).val();
		var limitNum = ${(actIntegral.purchase)!0};
		var stock = ${(actIntegral.stock)!0};
		//判断是否为正整数
		if(!isIntege1(buyNum)){
			$(obj).val(1);
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			return false;
		}
		if (parseInt(buyNum) < 1) {
			$(obj).val(1);
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			return false;
		} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			if (parseInt(limitNum) < parseInt(stock)) {
				$(obj).val(parseInt(limitNum));
			} else {
				$(obj).val(parseInt(stock));
			}
			return false;
		}
		$("#checkError").html("");
		$("#checkError").hide();
	}

	//点击规格信息查询
	function queryPrice(){
		var flag = true;
		$("input[name='specId']").each(function(){
				if($(this).val().length<1){
					flag = false;
					return;
				}
			}
		);
		
		var params = $("#cartForm").serialize();
		if(flag){
			$.ajax({
				type : "POST",
				url :  domain+"/getGoodsInfo.html",
				data : params,
				dataType : "json",
				success : function(data) {
					var goods = data.data;
					if(goods.id!=null){
						/* //商城价格
						$("#mallPcPrice").html("￥"+goods.mallPcPrice);
						//库存
						$("#productStock").html(goods.productStock); */
						//货品ID
						$("#productGoodsId").val(goods.id);
						//商品编码
						$("#productCode").html(goods.sku);
					}else{
						//无货品信息 则不能添加购物车和购买
						jAlert("货品信息为空，请与管理员联系");
						$(".buynow").attr("disabled","disabled");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}

</script>
