<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>忘记密码</title>
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/bootstrap.min.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/base.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/hover.css">
  <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/login.css">
  <style type="text/css">
    .main-container .login-wrap{
      margin-top: -275px;
    }
    .main-container .login-wrap .put-wrap .QRcode a{
      padding: 0;
      color: #fff;
      line-height: 50px;
      text-align: center;
      background-color: #ec6c00;
    }
  </style>
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;忘记密码</span>
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
          <b class="fl">忘记密码</b>
          <div class="err-tip fr"></div>
        </div>
        <div class="put-wrap">
          <form id="forgetForm" onSubmit="return false;">
            <div class="form-group telphone">
              <label for="telphone"></label>
              <input id="telphone" name="telphone" type="text" class="form-control" placeholder="请输入手机号码">
            </div>
            <div class="form-group QRcode clearfix">
              <label for="QRcode"></label>
              <input id="QRcode" name="QRcode" type="text" class="form-control col-md-8" placeholder="手机验证码">
              <a class="col-md-4" id="obtainBtn" href="javascript:;" onclick="getCode(this)">获取验证码</a>
            </div>
             <div class="form-group pass">
              <label for="pass"></label>
              <input id="pass" name="password" type="password" class="form-control" placeholder="输入新密码">
            </div>
             <div class="form-group pass">
              <label for="confirmPass"></label>
              <input id="confirmPass" name="confirmPass" type="password" class="form-control" placeholder="确认新密码">
            </div>
            <div class="form-group">
              <button type="submit" class="btn" id="resetBtn">重置密码</button>
            </div>
          </form>
        </div>
        <div class="link clearfix">
          <a class="fr register" href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/login.html"><img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/register.png">&nbsp;&nbsp;立即登录</a>
        </div>
      </div>
    </div>
  </div>
  <div class="footer-container">版权所有 © Copyright 2017 粤ICP备17164358号-1 海核云谷.保留一切权利</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/verification.js"></script>
  <script type="text/javascript">
      var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
    $(function () {
      /*登录*/
      $("#telphone").on('blur',function(){
            var telphone = $.trim($('#telphone').val());
            if (telphone == '') {
                showTips('请输入您的手机号码！');
                $('#telphone').focus().addClass('focus');
                return ;
            }else if (!regular.isPhone(telphone)) {
                showTips('手机号码格式不对！');
                $('#telphone').focus().addClass('focus');
                return ;
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
                            $("#obtainBtn").removeAttr('onclick');
                        } else {
                            $("#obtainBtn").attr("onclick","getCode(this)");
                        }
                    }

                });
            }
        });
      $('#resetBtn').on('click', function () {
        var phone = $.trim($('#telphone').val());
        var QRcode = $.trim($('#QRcode').val());
        var pass = $.trim($('#pass').val());
        var confirmPass = $.trim($('#confirmPass').val());
        if (phone == '') {
          showTips('请输入手机号码！');
          $('#telphone').focus().addClass('focus');
        } else if (!regular.isPhone(phone)) {
          showTips('手机号码格式不对！');
          $('#telphone').focus().addClass('focus');
        } else if (QRcode == '') {
          showTips('请输入手机验证码！');
          $('#QRcode').focus().addClass('focus');
        } else if (QRcode.length !=6) {
            showTips('手机验证码为6位数！');
            $('#QRcode').focus().addClass('focus');
        }else if (pass == '') {
          showTips('请输入密码！');
          $('#pass').focus().addClass('focus');
        } else if (!regular.isPass(pass)) {
          showTips('密码为6-12的数字,字母(大小写均可)！');
          $('#pass').focus().addClass('focus');
        } else if(confirmPass != pass) {
          showTips('两次输入的密码不一致！');
          $('#confirmPass').focus().addClass('focus');
        } else {
          $('.err-tip').html('');
        }
          $("#resetBtn").attr("disabled", "disabled");
          var params = $('#forgetForm').serialize();
          $.ajax({
              type : "POST",
              url : domain + "/portal/doForPassword.html",
              dataType : "json",
              async : false,
              data : params,
              success : function(data) {
                  if (data.success) {
                      alert("重置密码成功!");
                      window.location.href=  domain + data.backUrl;
                  } else {
                      showTips(data.message);
                      refreshCode();
                      $("#resetBtn").removeAttr("disabled");
                  }
              },
              error : function() {
                  $("#resetBtn").removeAttr("disabled");
              }
          });
      });
      $('#telphone').on('keyup', function () {
        verification(this, '请输入手机号码！');
      });
      $('#pass').on('keyup', function () {
        verification(this, '请输入密码！');
      });
      $('#QRcode').on('keyup', function () {
        verification(this, '请输入验证码！');
      });
      $('#confirmPass').on('keyup', function () {
        verification(this, '请输入验证码！');
      });
      isFixed();
    });
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

    function getCode(obj) {
        var timer = null;
        var sendverfiy = false;
        var time = 120;
        var phone = $.trim($('#telphone').val());
        if (phone==null||phone == '') {
            showTips('请输入手机号码！');
            $('#telphone').focus().addClass('focus');
            return;
        }
        if (!regular.isPhone(phone)) {
            showTips('手机号码格式不对！');
            $('#telphone').focus().addClass('focus');
            return;
        }
        $.ajax({
            type : 'post',
            url : domain + '/portal/sendSMS.html?mob=' + phone
            + '&type=forget',
            async:false,
            success : function(data) {
                if (data.success) {
                    sendverfiy = true;
                } else{
                    showTips(data.message);
                    refreshCode();
                    $("#code").html("获取短信验证码");
                }
            }
        });
        if (sendverfiy) {
            $(obj).attr("disabled", true).css('backgroundColor', '#ccc').text(time + "秒后重新获取");
            timer = setInterval(function () {
                $(obj).text(time + "秒后重新获取");
                time--;
                if (time == 0) {
                    clearInterval(timer);
                    $(obj).removeAttr("disabled").css('backgroundColor', '#ec6c00').text("获取验证码");
                }
            }, 1000);
        }
    }

      function refreshCode() {
          jQuery("#code_img").attr("src", domain + "/verify.html?d" + new Date().getTime());
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