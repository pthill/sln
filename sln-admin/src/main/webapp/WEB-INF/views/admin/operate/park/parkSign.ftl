<#include "/admin/commons/_detailheader.ftl" /> 
<#assign
currentBaseUrl="${domainUrlUtil.SLN_URL_RESOURCES}/admin/operate/park"/>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script language="javascript" type="text/javascript" 
	src="http://api.map.baidu.com/api?v=2.0&ak=VZAcXOGAzHx3QinWCXPFE482IFPBq1GR">
</script>
<style>
.dl-group p img {
	max-width: 120px;
	float: left;
}

.formbox-a .lab-item {
	float: left;
	width: 120px;
	text-align: right;
	margin-right: 3px;
	display: inline;
	padding-top: 5px;
}

.panel-fit body.panel-noscroll {
	overflow-y: scroll;
}
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
</style>

<div class="wrapper">
	<div class="formbox-a" style="height:300px;">
		<h2 class="h2-title">
			园区标记
				<a class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doSign"> 
			<input type="hidden" id="id" name="name" value="${(id)!''}">
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">位置:
							</label><input type="text" id="suggestId" size="20" style="width:150px;" />
								<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
								<label class="lab-item">
							</label> 
								
						</p>
					</div>
							<div class="dd-group" id="allmap" style="height:400px;"></div>
					
				</dd>
			</dl>
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>
<script type="text/javascript">


	var lng ='';
	var lat ='';
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	map.addEventListener("click",function(e){
	 	 lat = e.point.lat;
	 	 lng =e.point.lng;
	 	 map.clearOverlays(); 
			var new_point = new BMap.Point(lng,lat);
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(new_point);     
	});
	
		
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			lat = pp.lat;
	 	 	lng =pp.lng;
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	function G(id) {
		return document.getElementById(id);
	}
	
	$(function() {
		
		//绑定提交事件
		$('#add').click(function(){
			//首先判断是否已经选择了点
			if(lng == '' || lat == ''){
				$.messager.alert('提示', '请标记地点');
					return;
			}
			var id = $("#id").val();
			$.ajax({
									type:"GET",
								    url: "${currentBaseUrl}/signPark?id="+id+"&lng="+lng+"&lat="+lat,
									dataType: "json",
								    cache:false,
									success:function(data, textStatus){
										if (data.success) {
											$.messager.alert('提示','操作成功',"info",function(){
											location.href = '${currentBaseUrl}'
											});
									    } else {
									    	$.messager.alert('提示',data.message);
									    }
									}
								});
		});
		
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});
	});
	
</script>
<#include "/admin/commons/_detailfooter.ftl" />
