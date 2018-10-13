<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>登录</title>
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/bootstrap.min.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/base.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/hover.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/login.css">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
  <!--头部区域-->
  <div class="header-container">
      <div class="container">
          <div class="logo">
            <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/haihetaologo2.png" alt="">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;欢迎登陆</span>
          </div>
      </div>
  </div>
  <!--主体区域-->
  <div class="main-container">
    <div class="bg-img"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/bg-login.jpg" alt=""></div>
    <div class="container">
      <div class="login-wrap">
        <ul>
          <li><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/notice.png" alt="">&nbsp;请勿设置与其他网站相同的登录及支付密码，谨防诈骗！</li>
        </ul>
        <div class="staff-login clearfix">
          <b class="fl">用户登录</b>
          <div class="err-tip fr"></div>
        </div>
        <div class="put-wrap">
          <form id="formLogin" onSubmit="return false">
            <div class="form-group telphone">
              <label for="telphone"></label>
              <input id="telphone" name="telphone" type="text"  class="form-control" placeholder="手机号或员工号">
              <span style="color: #ec6c00;font-size: 12px">提示：原海核淘用户可直接使用员工号登录</span>
            </div>
            <div class="form-group pass">
              <label for="pass"></label>
              <input id="pass" name="pass" type="password" class="form-control" placeholder="请输入密码">
            </div>
            <div class="form-group QRcode clearfix">
              <label for="QRcode"></label>
              <input id="QRcode" name="QRcode" type="text" class="form-control col-md-4" placeholder="验证码">
                <a class="col-md-8" onclick="refreshCode();" >
                <img  id="code_img" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html"  >
                &nbsp;看不清，换一张
                </a>
            </div>
            <div class="form-group forget">
              <div class="checkbox clearfix">
                <a class="fr" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/forPassword.html">忘记密码？</a>
              </div>
            </div>
            <div class="form-group">
              <button type="submit"  class="btn" id="login">登录</button>
            </div>
          </form>
        </div>
        <div class="link clearfix">
          <a class="fl QQ" href="javascript:"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/QQ.png">&nbsp;&nbsp;QQ</a>
          <a class="fl wechat" href="javascript:;"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/wechat.png">&nbsp;&nbsp;微信</a>
          <a class="fr register" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/register.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/register.png">&nbsp;&nbsp;立即注册</a>
        </div>
      </div>
    </div>
  </div>
  <div class="footer-container">版权所有 © Copyright 2017 粤ICP备17164358号-1 海核云谷.保留一切权利</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/verification.js"></script>
  <script type="text/javascript">
      //刷新验证码
      var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
      $(function () {
          $("#telphone").on('blur',function(){
              var telphone = $.trim($('#telphone').val());
              if (telphone == '') {
                  showTips('请输入您的手机号或手机号！');
                  $('#telphone').focus().addClass('focus');
                  return ;
              }
              if (telphone.length==11) {
                  if (!regular.isPhone(telphone)) {
                      showTips('手机号码格式不对！');
                      $('#telphone').focus().addClass('focus');
                      return ;
                  }
              }else{
                  $.ajax({
                      type : "get",
                      url : domain + "/nameIsExist.html?name="+telphone,
                      dataType : "json",
                      async : false,
                      success : function(data) {
                          if (data.success) {
                              showTips("对不起，你尚未注册过海核云谷的账号");
                              $('#telphone').focus().addClass('focus');
                              $("#login").removeAttr('disabled');
                              //$("#login").attr("disabled","disabled");
                          } else {
                              $("#login").removeAttr('disabled');
                          }
                      }

                  });
              }
          });
          $("#login").click(function () {
              var phone = $.trim($('#telphone').val());
              var pass = $.trim($('#pass').val());
              var QRcode = $.trim($('#QRcode').val());
              if (phone == '') {
                  showTips('请输入手机号或员工号！');
                  $('#telphone').focus().addClass('focus');
                  return ;
              } else if (phone.length==11) {
                  if (!regular.isPhone(phone)) {
                      showTips('手机号码格式不对！');
                      $('#telphone').focus().addClass('focus');
                      return ;
                  }
              } else if (pass == '') {
                  showTips('请输入密码！');
                  $('#pass').focus().addClass('focus');
                  return ;
              } else if (!regular.isPass(pass)) {
                  showTips('密码为6-12的数字、字母！');
                  $('#pass').focus().addClass('focus');
                  return ;
              } else if (QRcode == '') {
                  showTips('请输入验证码！');
                  $('#QRcode').focus().addClass('focus');
                  return ;
              } else if(QRcode.length != 4) {
                  showTips('验证码为4位！');
                  $('#QRcode').focus().addClass('focus');
                  return ;
              } else {
                  $('.err-tip').html('');
              }
              $("#login").attr("disabled", "disabled");
              var params = $('#formLogin').serialize();
              $.ajax({
                  type : "POST",
                  url : domain + "/portal/doLogin.html",
                  dataType : "json",
                  async : false,
                  data : params,
                  success : function(data) {
                      if (data.success) {
                          window.location = domain + data.backUrl;
                      } else {
                          showTips(data.message);
                          refreshCode();
                          $("#login").removeAttr("disabled");
                      }
                  },
                  error : function() {
                      $("#login").removeAttr("disabled");
                  }
              });
          })
          $('#telphone').on('keyup', function () {
              verification(this, '请输入手机号或员工号！');
          });
          $('#pass').on('keyup', function () {
              verification(this, '请输入密码！');
          });
          $('#QRcode').on('keyup', function () {
              verification(this, '请输入验证码！');
          });
          isFixed();
      });

      function refreshCode(){
          jQuery("#code_img").attr("src",domain+"/verify.html?d"+new Date().getTime());
      }
      function verification(el, text) {
          var value = $.trim($(el).val());
          if (value == '') {
              showTips(text);
              return false;
          } else {
              $(el).removeClass('focus');
              $('.err-tip').html('');
          }
      }
      function showTips(text) {
          var html = '<em></em> <span>'+ text +'</span>';
          $('.err-tip').html(html);
      }
      function isFixed() {
          var winH = $(window).height();
          var bodyH = $('body').height();
          if (bodyH < winH) {
              $('.footer-container').addClass('footer-fixed');
          } else {
              $('.footer-container').removeClass('footer-fixed');
          }
      }

  </script>
</body>

</html>