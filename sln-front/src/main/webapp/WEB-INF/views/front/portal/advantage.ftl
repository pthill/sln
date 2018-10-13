<#include "/front/portal/common/header.ftl" />
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
          <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/advantage.html">云谷优势&nbsp;</a>
        </div>
        <div class="content-wrap row">
          <!--左文本区域-->
          <div class="text-wrap col-md-9">
               <#if advantage?? && advantage?size gt 0>
                 <#list advantage as item>
                   <#noescape>
                   ${(item.remark)!''}
                   </#noescape>
                 </#list>
               </#if>
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
<#include "/front/portal/common/footer.ftl" />