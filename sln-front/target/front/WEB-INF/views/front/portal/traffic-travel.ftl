<#include "/front/portal/common/header.ftl" />
<div class="bgc">
    <!--主体区域-->
    <div class="main-container">
      <div class="container">
        <!--导航目录-->
        <div class="catalog-map">
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html" class="old-catalog">首页&nbsp;</a>&gt;
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/service/traffic.html">交通出行&nbsp;</a>
        </div>
        <!--图片-->
        <div class="banner-wrap">
          <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u1607.png" alt="交通出行">
        </div>
        <!--自助服务列表-->
        <div class="server-list">
            <h3>自助服务</h3>
            <ul class="row">
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block" href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u1162.png" alt="班车预定">
                  <span class="block">班车预定</span>
                </a>
              </li>
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block " href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u1163.png" alt="公务用车">
                  <span class="block">公务用车</span>
                </a>
              </li>
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block" href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u2410.png" alt="车辆租凭">
                  <span class="block">车辆租凭</span>
                </a>
              </li>
              <li class="col-md-3 text-center noborder_bot hvr-shadow-radial">
                <a class="block" href="javascript:;">
                  <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-service/u1164.png" alt="园区滴滴">
                  <span class="block">园区滴滴</span>
                </a>
              </li>
              
            </ul>
        </div>
      </div>
    </div>
  </div>
<#include "/front/portal/common/footer.ftl" />
