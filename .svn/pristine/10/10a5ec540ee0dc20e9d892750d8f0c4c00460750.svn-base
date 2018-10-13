<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/park-active.css">
<!--主体区域-->
<div class="main-container">
      <!--图片-->
      <div class="banner-wrap">
        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/park-active/u5724.png" alt="活动列表">
        <div class="img-text">
          <a class="block" href="javascript:;">
            <h1>海核云谷振翅高飞</h1>
            <h4>KNOW MORE ABOUT US</h4>
          </a>
        </div>
      </div>
      <div class="container">
        <!--导航目录-->
        <div class="catalog-map">
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html" class="old-catalog">首页&nbsp;</a>&gt;
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeList.html">活动列表&nbsp;</a>
        </div>
        <!--活动列表-->
        <div class="active-list">
          <ul class="row">
            <#if activeList?? && (activeList?size>0)>
                <#list activeList as active>
                  <#if .now?date gt active.endTime?date><#--已结束的活动-->
                      <li class="col-md-3 end">
                          <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeDetail/${active.id}.html">
                              <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${active.img}" alt="园区活动">
                              <h3>${active.title}</h3>
                              <div class="btn-wrap">
                                  <button class="bg_666">
                                      已结束
                                  </button>
                                  <button class="bg_999">
                                      <#if active.parkId==park.id>
                                          本园区
                                      <#else>
                                          其他园区
                                      </#if>
                                  </button>
                              </div>
                              <p>${(active.description)!''}</p>
                              <div class="time-address">
                                  <h5>
                                      <span>${active.address}</span>
                                  </h5>
                                  <h6 style="margin-top: 10px">
                                      <span>${active.startTime?string('yyyy.MM.dd HH:mm')}至${active.endTime?string('yyyy.MM.dd HH:mm')}</span>
                                  </h6>
                              </div>
                          </a>
                      </li>
                      <#else >
                      <li class="col-md-3" ><#--未结束的活动-->
                              <a class="block" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeDetail/${active.id}.html">
                                  <img src="${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}${active.img}" alt="园区活动">
                                  <h3>${active.title}</h3>
                                  <div class="btn-wrap">
                                    <#if active.startTime?date gt .now?date>
                                        <button class="bg_red">即将开始</button>
                                    <#else>
                                        <button class="bg_red">进行中</button>
                                    </#if>
                                      <#if active.parkId==park.id>
                                      <button class="bg_orange">
                                          本园区
                                      </button>
                                      <#else>
                                          <button class="bg_pink">
                                              其他园区
                                          </button>
                                      </#if>
                                  </div>
                                  <p>${(active.description)!''}</p>
                                  <div class="time-address">
                                      <h5>
                                          <span>${active.address}</span>
                                      </h5>
                                      <h6 style="margin-top: 10px">
                                          <span>${active.startTime?string('yyyy.MM.dd HH:mm')}至${active.endTime?string('yyyy.MM.dd HH:mm')}</span>
                                      </h6>
                                  </div>
                              </a>
                          </li>
                  </#if>
                </#list>
            </#if>
          </ul>      
        </div>
        <!--页码-->
          <div class="pagination-container"></div>

      </div>
    </div>
<script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/pagination.js"></script>
<script type="text/javascript">
      $(function(){
          var pageCount= Math.ceil(${page.rowsCount}/${page.pageSize});
          $(".pagination-container").pagination({
              pageCount: pageCount,  //总页数
              current: ${page.pageIndex},  //当前页码
              backFn:function(page){  //回调函数
                  //page当前页码
                  window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeList.html" +
                  "?rows=8&page="+page;
              }
          });
      });
  </script>
<#include "/front/portal/common/footer.ftl" />

