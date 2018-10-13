<#include "/front/portal/common/header.ftl" />
<#assign getImagePathMethod="com.sln.web.util.freemarker.ProductImagePathModel"?new()/>

<div class="w">
    <div class="banner">
        <div class="row">
            <div class="col-md-5">
                <!-- tab栏 -->
                <div class="tabs-vertical">
                    <ul>
                        <li class="pic1 tab-active">
                            <a data-index="0" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html"><i></i>&nbsp;<span>电商分类</span></a>
                        </li>
                        <li class="pic2">
                            <a data-index="1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/property.html"><i></i>&nbsp;<span>物业服务</span></a>
                        </li>
                        <li class="pic3">
                            <a data-index="2" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/restaurant.html"><i></i>&nbsp;<span>餐饮服务</span></a>
                        </li>
                        <li class="pic4">
                            <a data-index="3" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/traffic.html"><i></i>&nbsp;<span>交通出行</span></a>
                        </li>
                        <li class="pic5">
                            <a data-index="4" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/conference.html"><i></i>&nbsp;<span>会务服务</span></a>
                        </li>
                        <li class="pic6">
                            <a data-index="5" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/health.html"><i></i>&nbsp;<span>健康服务</span></a>
                        </li>
                        <li class="pic7">
                            <a data-index="6" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/business.html"><i></i>&nbsp;<span>商旅服务</span></a>
                        </li>
                    </ul>
                    <div class="tabs-content-placeholder">

                        <div class="tab-content-active">
                                <span class="block clearfix">
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">手机</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">数码</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">配件电脑</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">硬件</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">外设家电</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">汽配</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">健身食品</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">饮料</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">保健母婴</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">个护</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">清洁手表</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">家纺</a>
                                    <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">厨具</a>
                                </span>

                        </div>

                        <div class="visitor">
                            <h4>访客登记</h4>
                            <input type="text" class="form-control " placeholder="|输入访客姓名">
                            <input type="text" class="form-control" placeholder="|输入访客手机">
                            <h4>到访时间</h4>
                            <div class="time">
                                <select name="year1"></select>
                                <select name="month1"></select>
                                <select name="day1"></select>
                            </div>
                            <div class="registration">
                                <button type="button" class="btn btn-warning btn-md">申请登记</button>
                            </div>
                            <div class="garden-list">
                                    <span class="block clearfix">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block a1">园区入驻</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">装修申请</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">电子屏预定</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">物品放行</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block a2">停车缴费</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block a3">文体设施预定</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block a4">车位预定</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">访客登记</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">园林绿化</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block a5">卡证办理</a>
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html" class="block">物业报修</a>
                                    </span>
                            </div>
                        </div>

                        <div class="catering">
                            <h4>外卖预订</h4>
                            <input type="text" class="form-control " placeholder="|输入餐饮名称">
                            <p>餐类</p>
                            <div class="classify">
                                <button class="btn btn-default">全部</button>
                                <button class="btn btn-default">炒菜</button>
                                <button class="btn btn-default">日韩</button>
                                <button class="btn btn-default">西餐</button>
                                <button class="btn btn-default">湘菜</button>
                            </div>
                            <div class="search-cai">
                                <button type="search" class="btn btn-warning btn-md"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/search1.png" alt="">&nbsp;搜索</button>
                            </div>
                            <div class="garden-list">
                                <span><a class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">私家厨房</a><a class="a1" href="javascript:void(0);">餐位预订</a><a href="javascript:void(0);">食材预定</a></span>
                                <span><a class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">智能餐盘</a><a href="javascript:void(0);">接待用餐</a><a  class="a1" href="javascript:void(0);">会议小食</a></span>
                                <span><a href="javascript:void(0);">团餐</a><a  class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">餐卖预定</a><a href="javascript:void(0);"></a></span>
                            </div>
                        </div>
                        <!-- 交通出行 -->
                        <div class="transportation">
                            <h4>班车预定</h4>
                            <input type="text" class="form-control " placeholder="出发地点">&nbsp;至
                            <input type="text" class="form-control" placeholder="目的地">
                            <br>
                            <br>
                            <form class="form-horizontal">
                                <fieldset>
                                    <div class="control-group" style="display:block;">
                                        <div class="controls" style="display:block;">
                                            <div class="input-prepend input-group" style="display:block;">
                                                <input type="text" readonly name="reservation" id="reservation" class="form-control" value="选择开始时间    至    结束时间" style="width:100%;"/>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#reservation').daterangepicker(null, function (start, end, label) {
                                        console.log(start.toISOString(), end.toISOString(),
                                                label);
                                    });

                                });
                            </script>
                            <div class="people">
                                <i>人数：<input type="text" class="form-control num" placeholder="输入人数">人</i>
                                <i>是否往返：&nbsp;<input name="Fruit" type="radio" value="">是&nbsp;<input name="Fruit" type="radio" value="">否</i>
                            </div>
                            <div class="search-people">
                                <button type="search" class="btn btn-warning btn-md"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/search1.png" alt="">&nbsp;查询</button>
                            </div>
                            <div class="garden-list">
                                <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">班车预定</a><a  class="a1" href="javascript:void(0);">公务用车</a><a href="javascript:void(0);">园区滴滴</a></span>
                                <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">车辆租凭</a><a href="javascript:void(0);"></a><a href="javascript:void(0);"></a></span>
                            </div>
                        </div>
                        <!-- 会务服务 -->
                        <div class="conference-service">
                            <h4>会议室预定</h4>
                            <div class="form-group">
                                <select name="" id="" class="form-control">
                                    <option value="00">选择园区</option>
                                    <option value="11">阳江园区</option>
                                    <option value="22">台山园区</option>
                                    <option value="33">惠州园区</option>
                                </select>
                                <select name="" id="" class="form-control">
                                    <option value="00">选择楼室</option>
                                    <option value="11">A座509</option>
                                    <option value="22">B座509</option>
                                    <option value="33">C座509</option>
                                </select>
                            </div>
                            <div class="form-horizontal">
                                <div class="control-group">
                                    <div class="controls">
                                        <div class="input-prepend input-group clearfix" style="width:100%;">
                                            <input type="text" readonly name="reservation" id="reservation1" class="form-control" value="选择开始时间    至    结束时间" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#reservation1').daterangepicker(null, function (start,
                                                                                       end, label) {
                                        console.log(start.toISOString(), end.toISOString(),
                                                label);
                                    });

                                });
                            </script>

                            <div class="people">
                                <p>人数：<input type="text" class="form-control num" placeholder="|输入人数">&nbsp;人</p>
                            </div>
                            <div class="search-people">
                                <button type="search" class="btn btn-warning btn-md"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/search1.png" alt="">&nbsp;查询</button>
                            </div>
                            <div class="garden-list">
                                <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">会务策划</a><a class="a1" href="javascript:void(0);">展厅服务</a><a href="javascript:void(0);">会议室预定</a></span>
                                <span><a class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">来访餐馆</a><a href="javascript:void(0);"></a><a href="javascript:void(0);"></a></span>
                            </div>
                        </div>
                        <!-- 健康服务 -->
                        <div class="health-service">
                            <h4>健康体检</h4>
                            <div class="form-group" style="width:100%;">
                                <select name="" id="" class="form-control" style="width:100%;">
                                    <option value="00">选择园区</option>
                                    <option value="11">阳江园区</option>
                                    <option value="22">台山园区</option>
                                    <option value="33">惠州园区</option>
                                </select>
                            </div>
                            <div class="form-horizontal">
                                <div class="control-group" style="display:block;">
                                    <div class="controls" style="display:block;">
                                        <div class="input-prepend input-group" style="display:block;">
                                            <input type="text" readonly name="reservation" id="reservation2" class="form-control" value="选择开始时间    至    结束时间" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#reservation2').daterangepicker(null, function (start,
                                                                                       end, label) {
                                        console.log(start.toISOString(), end.toISOString(),
                                                label);
                                    });
                                });
                            </script>
                            <div class="search-people">
                                <button type="search" class="btn btn-warning btn-md"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/search1.png" alt="">&nbsp;查询</button>
                            </div>
                            <div class="garden-list">
                                <span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">健康体检</a><a class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">医疗服务</a><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">健康小屋</a></span>
                            </div>
                        </div>
                        <!--商旅服务  -->
                        <div class=" concierge-services">
                            <h4>客房预定</h4>
                            <div class="form-group" style="width:100%;">
                                <select name="" id="" class="form-control" style="width:100%;">
                                    <option value="00">选择园区</option>
                                    <option value="11">阳江园区</option>
                                    <option value="22">台山园区</option>
                                    <option value="33">惠州园区</option>
                                </select>
                            </div>
                            <div class="form-horizontal">
                                <fieldset>
                                    <div class="control-group" style="display:block;">
                                        <div class="controls" style="display:block;">
                                            <div class="input-prepend input-group" style="display:block;">
                                                <input type="text" readonly name="reservation" id="reservation3" class="form-control" value="选择开始时间    至    结束时间" />
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#reservation3').daterangepicker(null, function (start,
                                                                                       end, label) {
                                        console.log(start.toISOString(), end.toISOString(),
                                                label);
                                    });
                                });
                            </script>

                            <div class="people">
                                <i>人数：<input type="text" class="form-control num" placeholder="|输入人数">&nbsp;人</i>

                            </div>
                            <div class="search-people">
                                <button type="search" class="btn btn-warning btn-md"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/search1.png" alt="">&nbsp;查询</button>
                            </div>
                            <div class="garden-list">
                                <span><a  class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">客户预订</a><a href="javascript:void(0);">休闲旅游</a><a href="javascript:void(0);">差旅出行</a></span>
                                <span><a  class="a1" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">票务预订</a><a href="javascript:void(0);"></a><a href="javascript:void(0);"></a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-5 banner-center">
                <!-- 轮播图 -->
                <div class="center-top">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <#if topBanner?? && (topBanner?size) gt 0>
                                <#if (topBanner?size) gte 3>
                                    <#list topBanner as top>
                                        <#if top_index<3>
                                            <#--取出最先开始轮播 如何开始时间大于系统当前时间则开始轮播-->
                                            <#if top_index==0>
                                                <#if (top.endTime)?datetime gt .now?datetime>
                                                    <div class="item active">
                                                        <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                    </div>
                                                 <#else >
                                                     <div class="item active">
                                                         <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/3.jpg"></a>
                                                     </div>
                                                </#if>
                                            </#if>
                                            <#if top_index==1>
                                                <#if (top.endTime)?datetime gt .now?datetime>
                                                    <div class="item">
                                                        <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                    </div>
                                                <#else>
                                                    <div class="item">
                                                        <a hhref="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/ban2.jpg"></a>
                                                    </div>
                                                </#if>
                                            </#if>
                                            <#if top_index==2>
                                                <#if (top.endTime)?datetime gt .now?datetime>
                                                    <div class="item">
                                                        <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                    </div>
                                                <#else>
                                                     <div class="item">
                                                         <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/2.jpg"></a>
                                                     </div>
                                                </#if>
                                            </#if>
                                        </#if>
                                    </#list>
                                <#elseif (topBanner?size) gte 2>
                                    <#list topBanner as top>
                                        <#if top_index<2>
                                            <#if top_index==0>
                                                <#if (top.endTime)?datetime gt .now?datetime>
                                                    <div class="item active">
                                                        <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                    </div>
                                                   <#else>
                                                    <div class="item active">
                                                           <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/3.jpg"></a>
                                                    </div>
                                                </#if>
                                            </#if>
                                            <#if top_index==1>
                                                <#if (top.endTime)?datetime gt .now?datetime>
                                                    <div class="item">
                                                        <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                    </div>
                                                <#else>
                                                    <div class="item">
                                                        <a hhref="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/ban2.jpg"></a>
                                                    </div>
                                                </#if>
                                            </#if>
                                        </#if>
                                    </#list>
                                    <div class="item">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/ban2.jpg"></a>
                                    </div>
                                    <#else>
                                    <#list topBanner as top>
                                        <#if top_index==0>
                                            <#if (top.endTime)?datetime gt .now?datetime>
                                                <div class="item active">
                                                    <a href="${top.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${top.img}"></a>
                                                </div>
                                            <#else>
                                                <div class="item active">
                                                    <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/3.jpg"></a>
                                                </div>
                                            </#if>
                                        </#if>
                                    </#list>
                                    <div class="item">
                                       <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/ban2.jpg"></a>
                                    </div>
                                    <div class="item">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/2.jpg"></a>
                                    </div>
                                </#if>
                                <#else >
                                    <div class="item active">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/3.jpg"></a>
                                    </div>
                                    <div class="item">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/ban2.jpg"></a>
                                    </div>
                                    <div class="item">
                                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/banner/2.jpg"></a>
                                    </div>
                            </#if>
                        </div>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="left-arrows"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u501.png" alt=""></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="right-arrows"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/you.png" alt=""></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                </div>
                <div class="center-bottom row">
                <#if leftBanner?? && leftBanner?size gt 0>
                    <#list leftBanner as left>
                        <#if left_index==0>
                        <#if (left.endTime)?datetime gt .now?datetime>
                            <a class="col-md-6 no_pad_l no_pad_r" href="${left.url}">
                                <img class="hvr-grow" src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${left.img}" alt="">
                            </a>
                            <#else>
                            <a class="col-md-6 no_pad_l no_pad_r" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html">
                                <img class="hvr-grow" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u471.png" alt="">
                            </a>
                        </#if>
                        </#if>
                    </#list>
                 <#else>
                    <a class="col-md-6 no_pad_l no_pad_r" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html">
                        <img class="hvr-grow" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u471.png" alt="">
                    </a>
                </#if>
                <#if rightBanner?? && rightBanner?size gt 0>
                    <#list rightBanner as right>
                        <#if right_index==0>
                            <#if (right.endTime)?datetime gt .now?datetime>
                                <a class="col-md-6 no_pad_r" href="${right.url}">
                                    <img class="hvr-grow" src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${right.img}" alt="">
                                </a>
                            <#else>
                                <a class="col-md-6 no_pad_r" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html">
                                    <img class="hvr-grow" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u475.png" alt="">
                                </a>
                            </#if>
                        </#if>
                    </#list>
                <#else >
                   <a class="col-md-6 no_pad_r" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html">
                    <img class="hvr-grow" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u475.png" alt="">
                   </a>
                </#if>
                </div>
            </div>
            <div class="col-md-2">
                <div class="right2">
                    <div class="right-top">
                        <p><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u1211-2.png" alt="">&nbsp;热门服务</p>
                    </div>
                    <div class="right-bottom">
                        <ul class="clearfix">
                            <li class="li1">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>餐位预订</span>
                                </a>
                            </li>
                            <li class="li2">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>公务用车</span>
                                </a>
                            </li>
                            <li class="li3">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>班车预订</span>
                                </a>
                            </li>
                            <li class="li4">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>物品放行</span>
                                </a>
                            </li>
                            <li class="li5">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>客房预订</span>
                                </a>
                            </li>
                            <li class="li6">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>差旅出行</span>
                                </a>
                            </li>
                            <li class="li7">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>园区入驻</span>
                                </a>
                            </li>
                            <li class="li8">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/index.html">
                                    <i></i>
                                    <span>接待用餐</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--秒杀倒计时部分  -->
<#if actFlashSaleStageNow??>
<div class="w">
    <div class="seckill">
        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/seckill_03.png" alt="">
        <span>距离结束还有
                <i style="display:none;" id="_d"></i>
                <i id="_h">00</i>
                <em>:</em>
                <i id="_m">08</i>
                <em>:</em>
                <i id="_s">43</i>
            </span>
    </div>
    <div class="seckill-list">
        <ul class="clearfix">
            <#if actFlashSaleStageNow.productList??>
                <#list actFlashSaleStageNow.productList as saleProduct>
                    <#if saleProduct_index < 5>
                        <#if saleProduct.product??>
                            <li class="hvr-glow">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1">
                                    <!--<#if saleProduct.product.source == 2>
                                        <img src="${(jdConfig.IMAGE_PATH_160)!}${(saleProduct.product.masterImg)!}" alt="">
                                    <#else>
                                        <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(saleProduct.product.masterImg)!}" alt="">
                                    </#if>-->
                                    <img class="lazy" data-original='${(getImagePathMethod((saleProduct.product.source)!"",(saleProduct.product.productCode)!""))!''}${(saleProduct.product.masterImg)!""}'>
                                    <p>${(saleProduct.product.name1)!""}</p>
                                    <div class="price clearfix">
                                        <span class="fl">￥${(saleProduct.price)?string("0.00")!""} </span>
                                        <em class="rt">￥${(saleProduct.product.marketPrice)?string("0.00")!""}</em>
                                    </div>
                                </a>
                            </li>
                        </#if>
                    </#if>
                </#list>
            </#if>
        </ul>
    </div>
</div>
</#if>
<!--爆品推荐、团购  -->
<div class="w">
    <div class="discounts">
        <div class="row">
            <#--爆款商品-->
            <#if bkProduct??>
                <#list bkProduct as bk>
                   <#if bk_index==0>
                     <div class="col-md-4 recommend">
                        <div class="title-top clearfix">
                            <b class="fl">爆品推荐</b>
                            <span class="fr"><a class="block" href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/recommend.html#item'>更多精彩在这里</a></span>
                        </div>
                        <ul class="recommend-left">
                            <li>
                                <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/recommend.html#item'>
                                    <!-- <span>海欧三历显示机械表</span> -->
                                   <#if bk.img??>
                                     <#if bk.img?split(",")?size &gt; 0>
                                        <#list bk.img?split(",") as item>
                                        <#if item_index==0>
                                         <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${item}" alt="">
                                        </#if>
                                        </#list>
                                     </#if>
                                   </#if>
                                </a>
                            </li>
                        </ul>
                    </div>
                   </#if>
            </#list>
            </#if>
            <#--阶梯竞价-->
            <#if mjProduct??>
                <#list mjProduct as shop>
                    <#if shop_index==0>
                        <div class="col-md-4 recommend">
                            <div class="title-top clearfix">
                                <b class="fl">阶梯竞价</b>
                                <span class="fr"><a class="block" href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/bidding-sale.html'>更多精彩在这里</a></span>
                            </div>
                            <div class="slide-wrap">
                                <div class="bd">
                                    <ul>
                                        <#assign a=3>
                                        <#if shop.img??>
                                            <#if shop.img?split(",")?size==3>
                                                <#list shop.img?split(",") as item>
                                                    <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/bidding-sale.html"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${item}" alt=""></a></li>
                                                </#list>
                                                <#elseif shop.img?split(",")?size==2>
                                                    <#list shop.img?split(",") as item>
                                                        <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/bidding-sale.html"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${item}" alt=""></a></li>
                                                    </#list>
                                                    <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/small2.jpg" alt="">
                                                 <#else >
                                                 <#list 1..a as i>
                                                     <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/bidding-sale.html"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${shop.img}" alt=""></a></li>
                                                 </#list>
                                            </#if>
                                        </#if>
                                    </ul>
                                </div>
                                <!-- 底部小圆点 -->
                                <div class="hd">
                                    <ul></ul>
                                </div>
                                <!-- 左箭头 -->
                                <a class="arrowbtn prev" style="display:none;" href="javascript:void(0)"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/zuo.png"></a>
                                <!-- 右箭头 -->
                                <a class="arrowbtn next" style="display:none;" href="javascript:void(0)"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/you2.png"></a>
                            </div>
                        </div>
                    </#if>
                </#list>
            </#if>
            <#--团购商品-->
            <div class="col-md-4 recommend">
                    <div class="title-top clearfix">
                        <b class="fl">超值团购</b>
                        <span class="fr"><a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/tuan.html">更多精彩在这里</a></span>
                    </div>
                <#--4个团购数据-->
                    <ul class="list-wrap">
                    <#if actGroups?? && (actGroups?size>0)>
                        <#list actGroups as actGroup>
                            <li class="col-md-6">
                                <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/tuan/${(actGroup.id)!}.html">
                                    <img style="width:150px;height: 125px" src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${(actGroup.image)!}" alt="">
                                    <p>${(actGroup.productName)!''}</p>
                                </a>
                            </li>
                        </#list>
                    </#if>
                    </ul>
                </div>
        </div>
    </div>
</div>
<!--活动部分  -->
<div class="w">
    <div class="activity clearfix">
        <div class="activity-font">
            <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/activity_06.png" alt="">
        </div>
        <div class="row">
            <div class="activity-left col-md-3">
                <div class="activity-pic1 hvr-hang">
                    <#if activeBanner?? && (activeBanner?size) gt 0>
                            <#list activeBanner as active>
                                <#if active_index==0>
                                    <a href="${active.url}"><img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${active.img}" alt=""></a>
                                </#if>
                            </#list>
                        <#else>
                            <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeList.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/pic1.png" alt=""></a>
                    </#if>
                </div>
            </div>
        <#if actives?? && actives?size &gt;0>
            <#list actives as active>
                <#if active_index<3 >
                <div class="col-md-3">
                    <div class="activity-pic3">
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeDetail/${active.id}.html"><img  class="pic-top" src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${active.img}" alt=""></a>
                        <div class="icon-1">
                            <#if active.startTime?date gt .now?date>
                                <b>即将开始</b>
                            <#elseif active.endTime?date gt .now?date && .now?date gt active.startTime?date>
                                <b>进行中</b>
                            <#else>
                                <b>结束</b>
                            </#if>
                        </div>
                        <div class="icon-2">
                            <#if park.parkName==active.parkName>
                                <b>本园区</b>
                            <#else >
                                <b>${active.parkName}</b>
                            </#if>
                        </div>
                        <div class="activity-content">
                            <h3>${active.title}</h3>
                            <p>${(active.description)!''}</p>
                        </div>
                        <div class="activity-bottom">
                            <div class="media">
                                <div class="media-left">
                                    <img class="media-object" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u579.png">
                                </div>
                                <div class="media-body">
                                    <span>${active.address}</span>
                                </div>
                            </div>
                        </div>
                        <div class="activity-time">
                            <div class="activity-img ">
                                <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u580.png " alt=" ">&nbsp;
                                <span>${active.startTime?string('yyyy.MM.dd HH:mm')}至${active.endTime?string('yyyy.MM.dd HH:mm')}</span>
                            </div>
                        </div>
                    </div>
                </div>
                </#if>
            </#list>
        </#if>
        </div>
    </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/daterangepicker.js "></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/common.js "></script>
<script>
    $(function(){
        function timer(time,d,h,m,s){
            window.setInterval(function(){
                var day=0,
                        hour=0,
                        minute=0,
                        second=0;//时间默认值
                if(time > 0){
                    day = Math.floor(time / (60 * 60 * 24));
                    hour = Math.floor(time / (60 * 60)) - (day * 24);
                    minute = Math.floor(time / 60) - (day * 24 * 60) - (hour * 60);
                    second = Math.floor(time) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                }
                if (minute <= 9) minute = '0' + minute;
                if (second <= 9) second = '0' + second;
                $(d).html(day+"天");
                $(h).html('<s></s>'+hour);
                $(m).html('<s></s>'+minute);
                $(s).html('<s></s>'+second);
                time--;
            }, 1000);
        }
        var tim = parseInt('${(countTime)!0}'); //传秒
        timer(tim,'#_d','#_h','#_m','#_s');

    });
</script>
<#include "/front/portal/common/footer.ftl" />
<script type="text/javascript">
	// 轮播图
	$(".slide-wrap").slide({
	    titCell: ".hd ul",
	    mainCell: ".bd ul",
	    effect: "leftLoop",
	    vis: "auto",
	    autoPlay: true,
	    autoPage: true,
	    trigger: "click"
	});
	new YMDselect('year1', 'month1', 'day1');
</script>
