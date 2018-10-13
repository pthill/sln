<#include "/front/portal/common/header.ftl" />
  <div class="bgc">
    <!--主体区域-->
    <div class="main-container">
      <!--图片-->
      <div class="banner-wrap">
        <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/yungu-advantage/u4883.png" alt="云谷优势">
        <div class="img-text">
          <a class="block" href="javascript:;">
            <h1>了解海核云谷</h1>
            <h4>KNOW MORE ABOUT US</h4>
          </a>
        </div>
      </div>
      <div class="container">
        <!--导航目录-->
        <div class="catalog-map">
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/index.html" class="old-catalog">首页&nbsp;</a>&gt;
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/activeList.html">活动列表&nbsp;</a>&gt;
          <a href="javascript:;">活动详情&nbsp;</a>
        </div>
        <div class="content-wrap row">
          <!--左文本区域-->
          <div class="text-wrap col-md-9">
            <div class="text-tit">
              <h3>${active.title}</h3>
            </div>
            <div class="author-msg">
              <span>发布时间：${active.createTime?string('yyyy-MM-dd')}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <span>作者：${active.author}</span>
            </div>
            <div class="btn-wrap">
            <#if active.startTime?date gt .now?date>
                <button class="bg_red">
                    即将开始
                </button>
            <#elseif active.endTime?date gt .now?date && .now?date gt active.startTime?date>
                <button class="bg_red">
                    进行中
                </button>
            <#else>
                <button class="bg_666">
                    已结束
                </button>
            </#if>
                <button class="bg_orange">
                <#if active.parkId==parkId>
                    本园区
                <#else >
                    其他园区
                </#if>
                </button>
            </div>
            <div class="time-address">
              <h5>&nbsp;&nbsp;${active.address}</h5>
              <h6>&nbsp;&nbsp;${active.startTime?string('yyyy.MM.dd HH:mm')}至${active.endTime?string('yyyy.MM.dd HH:mm')}</h6>
            </div>  
            <div class="text-paragraph">
                <#if active.remark??>
                    <#noescape>
                    ${(active.remark)!''}
                    </#noescape>
                </#if>
            </div>
          </div>
          <!--右文本区域-->
          <div class="recommend-wrap col-md-3">
            <dl>
              <dd class="recommend-servive">&nbsp;&nbsp;推荐服务</dd>
              <dt>
              <#if recommendList??>
                <#list recommendList as item>
                    <a class="block" href="javascript:;">${item.fwx}</a>
                </#list>
              </#if>
              </dt>
              <dd class="contact-us">&nbsp;&nbsp;联系我们</dd>
            </dl>
            <div class="address-wrap">
              <h5>0755-123456789</h5>
              <h5>深圳市福田区深南路核电大厦1409海核云谷招商中心</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<#include "/front/portal/common/footer.ftl" />
