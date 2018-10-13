<#include "/front/portal/common/header.ftl" />
<div class="bgc">
    <!--主体区域-->
    <div class="main-container">
      <div class="container">
        <!--导航目录-->
        <div class="catalog-map">
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html" class="old-catalog">首页&nbsp;</a>&gt;
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/health.html">健康服务&nbsp;</a>
        </div>
        <!--图片-->
        <div class="banner-wrap">
          <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u1919.png" alt="交通出行">
        </div>
        <!--自助服务列表-->
        <div class="server-list">
            <h3>自助服务</h3>
            <ul class="row">
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block" href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u4087.png" alt="健康体验">
                  <span class="block">健康体验</span>
                </a>
              </li>
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block " href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u4088.png" alt="医疗服务">
                  <span class="block">医疗服务</span>
                </a>
              </li>
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block" href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u2402.png" alt="健康小屋">
                  <span class="block">健康小屋</span>
                </a>
              </li>             
            </ul>
        </div>
      </div>
    </div>
</div>
<#include "/front/portal/common/footer.ftl" />
