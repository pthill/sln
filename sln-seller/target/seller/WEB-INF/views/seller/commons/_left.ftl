<div class="page-sidebar" id="sidebar">
    <div class="sidebar-header-wrapper">
        <i class="searchicon glyphicon glyphicon-home homeicon">系统菜单</i>
    </div>
    <ul class="nav sidebar-menu ejava-sidebar-menu">
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-sitemap"></i>
                <span class="menu-text"> 交易管理 </span>

                <i class="menu-expand"></i>
            </a> 
            <ul class="submenu ejava-submenu navtree">
           		<@shiro.hasPermission name="/seller/order/orders">
                <li class="active">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders">
                        <span class="menu-text">全部订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state1">
                <li>
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state1">
                        <span class="menu-text">未付款订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state2">
                <li>
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state2">
                        <span class="menu-text">待确认订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state3">
                <li>
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state3">
                        <span class="menu-text">待发货订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state4">
                <li menu="ordersstate4">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state4">
                        <span class="menu-text">已发货订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state5">
                <li>
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state5">
                        <span class="menu-text">已完成订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/orders/state6">
                <li>
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/orders/state6">
                        <span class="menu-text">已取消订单</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/productBack">
                <li menu="productBack">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/productBack">
                        <span class="menu-text">退货管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/productExchange">
                <li menu="productExchange">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/productExchange">
                        <span class="menu-text">换货管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/order/complaint">
                <li menu="complaint">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/order/complaint">
                        <span class="menu-text">投诉管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-shopping-cart"></i>
                <span class="menu-text"> 商品管理 </span>
                <i class="menu-expand"></i>
            </a>
            <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/product/sellerCate">
                <li menu="sellercate">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/sellerCate">
                        <span class="menu-text">店铺分类</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/cate">
                <li menu="cate">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/cate">
                        <span class="menu-text">商品分类申请</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/manager">
                <li menu="catemanager">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/manager">
                        <span class="menu-text">已申请分类</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/chooseCate">
                <li menu="choosecate">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/chooseCate">
                        <span class="menu-text">发布商品</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/saleAll">
                <li menu="saveall">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/saleAll">
                        <span class="menu-text">所有商品</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/waitSale">
                <li menu="waitsale">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/waitSale">
                        <span class="menu-text">待售商品</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/onSale">
                <li menu="onsale">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/onSale">
                        <span class="menu-text">在售商品</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/delSale">
                <li menu="delsale">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/delSale">
                        <span class="menu-text">已删除商品</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/product/brand">
                <li menu="brand">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/brand">
                        <span class="menu-text">品牌管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                
                <@shiro.hasPermission name="/seller/product/stock">
                <li menu="brand">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/product/stock">
                        <span class="menu-text">库存管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-laptop"></i>
                <span class="menu-text"> 促销管理 </span>
                <i class="menu-expand"></i>
            </a>
            <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/promotion/coupon">
                <li menu="coupon">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/coupon">
                        <span class="menu-text">优惠券管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/full">
                <li menu="full">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/full">
                        <span class="menu-text">订单满减</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/single">
                <li menu="single">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/single">
                        <span class="menu-text">单品立减</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/flash">
                <li menu="flash">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/flash">
                        <span class="menu-text">限时抢购</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/actgroup">
                <li menu="actgroup">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actgroup">
                        <span class="menu-text">团购管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/actbidding">
                <li menu="actbidding">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actbidding">
                        <span class="menu-text">集合竞价管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/promotion/actintegral">
                <li menu="actintegral">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/promotion/actintegral">
                        <span class="menu-text">积分商城管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            </ul>
        </li>

        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-group"></i>
                <span class="menu-text"> 会员管理 </span>
                <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/member/productask">
                <li menu="productask">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/member/productask">
                        <span class="menu-text">会员咨询管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/member/productcomments">
                <li menu="productcomments">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/member/productcomments">
                        <span class="menu-text">会员评价管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-mobile"></i>
                <span class="menu-text"> 移动端管理 </span>
                 <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/mindex/banner">
                <li menu="mbanner">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/banner">
                        <span class="menu-text">首页轮播图</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/mindex/floor">
                <li menu="floor">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/floor">
                        <span class="menu-text">首页楼层</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/mindex/floordata">
                <li menu="floordata">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/mindex/floordata">
                        <span class="menu-text">楼层数据</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-desktop"></i>
                <span class="menu-text"> PC端首页管理 </span>
                 <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/pcindex/sellerinfo">
                <li menu="sellerinfo">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/pcindex/sellerinfo">
                        <span class="menu-text">首页信息</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/pcindex/banner">
                <li menu="pbanner">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/pcindex/banner">
                        <span class="menu-text">首页轮播图</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/pcindex/recommend">
                <li menu="recommend">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/pcindex/recommend">
                        <span class="menu-text">推荐类型</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/pcindex/recommenddata">
                <li menu="recommenddata">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/pcindex/recommenddata">
                        <span class="menu-text">推荐类型数据</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-gamepad"></i>
                <span class="menu-text"> 运营管理 </span>
                 <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/operate/sellerqq">
                <li menu="sellerqq">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/operate/sellerqq">
                        <span class="menu-text">客服QQ设置</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/operate/sellerTransport">
                <li menu="sellerTransport">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/operate/sellerTransport">
                        <span class="menu-text">运费设置</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/systemNotice">
                <li menu="systemNotice">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/systemNotice">
                        <span class="menu-text">商城公告</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/message">
                <li menu="systemNotice">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/message">
                        <span class="menu-text">商家消息</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon fa fa-gears"></i>
                <span class="menu-text"> 系统管理 </span>
                 <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/system/role">
                <li menu="role">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/role">
                        <span class="menu-text">角色管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/system/sellerUser">
                <li menu="sellerUser">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/sellerUser">
                        <span class="menu-text">管理员管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/system/sellerUser/editpwd">
                <li menu="editpwd">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/sellerUser/editpwd">
                        <span class="menu-text">修改密码</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/system/supplier">
                <li menu="supplier">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/system/supplier">
                        <span class="menu-text">供应商管理</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        <li>
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon glyphicon glyphicon-stats"></i>
                <span class="menu-text"> 统计结算 </span>
                 <i class="menu-expand"></i>
            </a>
           <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/settlement">
                <li menu="settlement">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/settlement">
                        <span class="menu-text">平台结算</span>
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/supplier/settlement">
                <li menu="settlement">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/supplier/settlement">
                        <span class="menu-text">供应商结算</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/orderday">
                <li menu="orderday">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/orderday">
                        <span class="menu-text">每日订单统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/productday">
                <li menu="productday">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/productday">
                        <span class="menu-text">每日商品统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/orders/orderOverview">
                <li menu="orderOverview">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/orders/orderOverview">
                        <span class="menu-text">订单概况</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/product/productSale">
                <li menu="productSale">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/product/productSale">
                        <span class="menu-text">商品销量统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/orders/saleOverview">
                <li menu="saleOverview">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/orders/saleOverview">
                        <span class="menu-text">订单销量统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/product/phurchaseRate">
                <li menu="phurchaseRate">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/product/phurchaseRate">
                        <span class="menu-text">购买率统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/orders/goodsReturnRate">
                <li menu="goodsReturnRate">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/orders/goodsReturnRate">
                        <span class="menu-text">退货率统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
            	<@shiro.hasPermission name="/seller/report/orders/CPIstatistics">
                <li menu="CPIstatistics">
                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/report/orders/CPIstatistics">
                        <span class="menu-text">人均消费统计</span>
                    </a>
                </li>
                </@shiro.hasPermission>
           </ul>
        </li>
        <li menu="changethtme" >
            <a class="menu-dropdown" href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/changethtme">
                <i class="menu-icon fa fa-pagelines"></i>
	            <span class="menu-text"> 主题设置 </span>
            </a>
        </li> 	
        
        <li menu="changethtme" >
            <a href="javascript:;" class="menu-dropdown">
                <i class="menu-icon glyphicon glyphicon-stats"></i>
                <span class="menu-text"> 供应商管理 </span>
                 <i class="menu-expand"></i>
            </a>
            <ul class="submenu ejava-submenu navtree" >
            	<@shiro.hasPermission name="/seller/supplier/orderdeliverycontroller">
	                <li menu="choosecate">
	                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/orderdeliverycontroller">
	                        <span class="menu-text">待发货单</span>
	                    </a>
	                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/supplier/orderdeliverycontroller/alreadydevlivery">
	                <li menu="choosecate">
	                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/orderdeliverycontroller/alreadydevlivery">
	                        <span class="menu-text">已发货单</span>
	                    </a>
	                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/supplier/supplierreturn">
	                <li menu="choosecate">
	                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/supplierreturn">
	                        <span class="menu-text">退货单</span>
	                    </a>
	                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/seller/supplier/supplierexchange">
	                <li menu="choosecate">
	                    <a href="${domainUrlUtil.SLN_URL_RESOURCES}/seller/supplier/supplierexchange">
	                        <span class="menu-text">换货单</span>
	                    </a>
	                </li>
                </@shiro.hasPermission>
            </ul>
        </li> 	
    </ul>

</div>
