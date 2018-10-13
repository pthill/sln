<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userCenter.css">
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/userindex.css">

    <!--   ****************以上是公共部分  -->
    <!-- 用户中心 -->
    <section class="w">
        <div class="title">
            <p><a href="#">首页</a>&nbsp;&nbsp; > &nbsp;&nbsp;<span><a href="#">用户中心</a></span></p>
        </div>
        <div class="row center">
            <div class="col-md-2">
                <!-- 左侧内容 -->
                <#include "/front/portal/common/left.ftl" />
                
            </div>
            <div class="col-md-10 right-content">
                <!-- 右侧内容 -->
                <div class='wrapper_main myorder wrapper_main-wd'>
                    <div class="user_info_top">
                        <div class="user_info_l fl">
                            <div class="user_avatar fl">
                                <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/user/u60.png" width="90" height="90">
                            </div>
                            <div class="user_avatar_r fl">
                                <b>${member.name}</b>
                                <br>
                                <div class="h20"><span>会员等级: </span>
                                    <span class="red">
                                    <#if member.grade == 1>
                                        注册会员
                                    <#elseif member.grade == 2>
                                        铜牌会员
                                    <#elseif member.grade == 3>
                                        银牌会员
                                    <#elseif member.grade == 4>
                                        金牌会员
                                    <#elseif member.grade == 5>
                                        钻石会员
                                    </#if>
                                    </span>
                                </div>
                                <div class="h20">
                                    <span>距离下次升级还差经验值：</span>
                                    <span>${(gradeValue)!}</span>
                                </div>
                            </div>
                        </div>
                        <div class="info-rcol">
                            <ul class="acco-info fr">
                                <li class="acco-item fl">
                                    <a href="#">
                                        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/user/u7913.png" alt="">
                                        <br>
                                        <p>一卡通&nbsp;></p>
                                    </a>
                                </li>
                                <li class="acco-item fl">
                                    <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/message/messageList.html">
                                        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/user/u74.png" alt="">
                                        <p>消息&nbsp; ></p>
                                        <span>(${(unreadNum)!''})</span>
                                    </a>
                                </li>
                                <li class="acco-item fl">
                                    <a href="#">
                                        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/user/u7914.png" alt="">
                                        <p>员工绑定&nbsp; ></p>
                                        <span>未绑定</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="info-integral">
                        <ul class="integral">
                            <li>
                                <p>白鹭E积分：<span>0(通用)</span></p>
                            </li>
                            <li>
                                <p>白鹭E积分：<span>0(专项)</span></p>
                            </li>
                            <li>
                                <p>平台积分：<span>${(member.integral)!} >(通用)</span></p>
                            </li>
                            <li>
                                <p>余额：<span>${(member.balance)!}  ></span></p>
                            </li>
                            <li class="info-end">
                                <p>优惠券：<span>${(couponNum)!0} &nbsp;&nbsp;> &nbsp; <button type="button" class="btn btn-warning btn-xs" >领券</button></span></p>

                            </li>
                        </ul>

                    </div>
                </div>
                <!-- 服务单 -->
                <div class="recent-orders">
                    <div class="order-tit">
                        <span class="t">服务单</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="zero black">全部</a>
                        <span class="dividingLine">|</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="zero">待确认</a>
                        <span class="dividingLine">|</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="zero">通过</a>
                        <span class="dividingLine">|</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="zero">打回</a>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="r">查看更多订单>></a>
                    </div>
                    <dl class="serve">
                        <dt>
                            <span>服务类型</span>
                            <span>服务项目</span>
                            <span>服务细则</span>
                            <span>人数</span>
                            <span>申请时间</span>
                            <span>开始时间</span>
                            <span>状态</span>
                            <span>操作</span>
                        </dt>
                        <dd>
                            <span>物业服务</span>
                            <span>文体设施预订</span>
                            <span>篮球场使用预定</span>
                            <span>6人</span>
                            <span>2017-12-12 15：30</span>
                            <span>2017-12-12 15：30</span>
                            <span>待确认</span>
                            <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">详情</a></span>
                        </dd>
                        <dd>
                            <span>交通出行</span>
                            <span>公务用车</span>
                            <span>奥迪A6使用预定</span>
                            <span>3人</span>
                            <span>2017-12-12 15：30</span>
                            <span>2017-12-12 15：30</span>
                            <span>通过</span>
                            <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">详情</a></span>
                        </dd>
                        <dd>
                            <span>商旅服务</span>
                            <span>票务预定</span>
                            <span>预定20号深圳飞广州机票</span>
                            <span>2人</span>
                            <span>2017-12-12 15：30</span>
                            <span>2017-12-12 15：30</span>
                            <span>打回</span>
                            <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">详情</a></span>
                        </dd>
                        <dd class="repast">
                            <span>餐饮服务</span>
                            <span>外卖预定</span>
                            <span>预定13号中午午餐</span>
                            <span>30人</span>
                            <span>2017-12-12 15：30</span>
                            <span>2017-12-12 15：30</span>
                            <span>完成</span>
                            <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">详情</a></span>
                        </dd>
                    </dl>
                </div>

                <!-- S 最近的订单 -->
                <div class="recent-orders">
                    <div class="order-tit">
                        <span class="t">商品订单</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/order.html" class="zero">待付款<span class="num">1</span></a>
                        <span class="dividingLine">|</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/order.html" class="zero">待收货<span class="num">1</span></a>
                        <span class="dividingLine">|</span>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/order.html" class="zero">待评价<span class="num">2</span></a>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/member/order.html" class="r">查看更多订单>></a>
                    </div>
                    <ul class="pro">
                        <#if ordersList??>
                             <#list ordersList as order>
                               <#if (order.orderProductList)??>
                                   <#list (order.orderProductList) as product>
                                       <li>
                                <a target="_blank">
                                    <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${product.productLeadLittle}" alt="" >
                                 </a>
                                <div class="text">
                                    <p>订单号：<span class="num">${(order.orderSn)!''}</span></p>
                                    <p>共<span class="b">${(product.number)!''}</span>件商品</p>
                                    <p class="total">总金额：<span>
                                        <#if order.orderType == 6>
                                         ${(order.integral)!''}分<br>
                                           积分兑换
                                    <#else>
                                        ￥${(order.moneyOrder)?string("0.00")!''}
                                    </#if></span></p>
                                </div>
                                <div class="bar fr">
                                    <div class="box gn">
                                        <i class="spt"></i>
                                        <p></p>
                                        <span>已提交</span>
                                    </div>
                                    <div class="box <#if order.orderState==1>gn<#else>no-gn</#if>">
                                        <i class="spt"></i>
                                        <p></p>
                                        <span>未付款</span>
                                    </div>
                                    <div class="box <#if order.orderState==3>gn<#else>no-gn</#if>">
                                        <i class="spt"></i>
                                        <p></p>
                                        <span>未出库</span>
                                    </div>
                                    <div class="box <#if order.orderState==4>gn<#else>no-gn</#if>">
                                        <i class="spt"></i>
                                        <p></p>
                                        <span>未收货</span>
                                    </div>
                                    <div class="box <#if order.orderState==5>gn<#else>no-gn</#if>">
                                        <i class="spt"></i>
                                        <span>未完成</span>
                                    </div>
                                </div>
                            </li>
                                   </#list>
                               </#if>
                             </#list>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </section>
<script type="text/javascript">
      $(function () {
      	//控制左侧菜单选中
		$("#memberIndex").addClass("currnet_page");
      });
</script>
<#include "/front/portal/common/footer.ftl" />