<#import "/front/commons/_macro_controller.ftl" as cont/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <title>海核云谷智慧园区综合服务平台</title>
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/user.css"/>
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/bootstrap.min.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/daterangepicker-bs3.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/hover.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/base.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/index.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/yungu-service.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/advantage.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/details-actives.css">
    <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/jquery-1.9.1.min.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/bootstrap.min.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/moment.min.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/jquery.SuperSlide.2.1.2.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/jquery.placeholder.min.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.lazyload.js"></script>
	<script src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
	<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/checkvalue.js"></script>
	
	
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 头部 -->
<div class="header">
    <div class="login">
        <div class="w">
            <div class="login-top clearfix">
                <div class="login-left fl">
                    <i></i>
                    <p>当前园区：<#if park??>${park.parkName}</#if>&nbsp;&nbsp;<span><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/parkMap.html">【切换】</a></span></p>
                </div>
                <div class="login-right fr">
                    <ul>
                        <li><a href="javascript:;">海核云谷欢迎您！</a></li>
                        <li><a class="login-line" href="javascript:;">客服热线：400—533—026&nbsp;&nbsp;</a></li>

                        <li class="client-li"><a class="login-line" href="javascript:;"><i class="client"></i>手机客户端 &nbsp;&nbsp;</a>
                            <div class="code" style="display:none;">
                                <div class="code-top">
                                    <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u1057.png" alt="">
                                </div>
                                <div class="code-bottom">
                                    <p>手机客户端</p>
                                </div>
                            </div>
                        </li>
                        <li class="client2-li"><a class="login-line" href="javascript:;"><b class="client2"></b>微信公众号&nbsp;&nbsp;</a>
                            <div class="code-weiXin " style="display:none;">
                                <div class="codew-top">
                                    <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u1057.png" alt="">
                                </div>
                                <div class="codew-bottom">
                                    <p>微信客户端</p>
                                </div>
                            </div>
                        </li>
                    <#if user??>
                        <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/userCenter.html">${(user.name)!''}会员&nbsp;<img class="person" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/person.png"></a>
                        <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/logout.html">退出</a>
                        </li>
                    <#else>
                        <li>
                            <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/login.html'>&nbsp;你好，请登录</a>
                            <a href='${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/register.html' class='regist'>免费注册</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <div class="logo clearfix">
        <div class="w">
            <div class="logo-left fl">
                <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/home/u2329.png" alt="海核云谷">
            </div>
            <div class="logo-right fl clearfix">
                <div class="row fl">
                    <div class="col-lg-11">
                        <div class="groups clearfix">
                            <div class="search-wrap fl">
                                <input type="text" class="form-control" aria-label="..." placeholder="|请输入您需要搜索的服务/产品内容" style="height:36px;padding:0 12px;">
                            </div>
                            <div class="input-group-btn fl" style="width:94px;">
                                <div class="btn btn-default dropdown-toggle last-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">全部分类 <span class="caret"></span></div>
                                <ul class="dropdown-menu dropdown-menu-right">
                                <#if pid?? && pid?size &gt; 0>
                                    <#list pid as service>
                                        <li><a href="javascript:void(0);">${service.serviceName}</a></li>
                                    </#list>
                                </#if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <p>大家都在搜索：
                    <#if recommendList??>
                        <#list recommendList as item>
                            <span>${item.fwx}</span>
                        </#list>
                    </#if>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!--导航条  -->
<div class="nav">
    <div class="head-v3" style="background-color:#fff;">
        <div class="w">
            <div class="navigation-up">
                <div class="navigation-inner clearfix">
                    <div class="navigation-v3">
                        <ul class="clearfix">
                            <li class="nav-up-selected-inpage" _t_nav="home" style="width:136px; height:43px;">
                                <h2>
                                    <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html" style="color:#fff;">首页</a>
                                </h2>
                            </li>
                        <#if menus??>
                            <#list menus as item>
                                <li class="num${item.count}"  <#if item.count!=0>_t_nav="${item.abbreviation}"</#if>>
                                    <h2>
                                        <a <#if item.state=='1'> href="${item.url}" </#if> >${item.name}
                                           <#if item.count!=0><i></i></#if>
                                        </a>
                                    </h2>
                                </li>
                            </#list>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navigation-down">
        <#if menus??>
            <#list menus as item>
                <div id="${item.abbreviation}" class="nav-down-menu menu-1 hvr-box-shadow-outset" style="display: none;" _t_nav="${item.abbreviation}">
                    <div class="navigation-down-inner">
                        <ul>
                            <#if map?? && keys?? >
                                <#list map?keys as key>
                                    <#if key==item.id?string>
                                        <#list map[key] as service>
                                            <li><a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/construction.html">${service.serviceName}</a></li>
                                            <#if service_index+1!=map[key]?size>
                                                <li>|</li>
                                            </#if>
                                        </#list>
                                    </#if>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                </div>
            </#list>
        </#if>
        </div>
    </div>
</div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/daterangepicker.js "></script>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/common.js "></script>
<script type="text/javascript">
		var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
</script>
