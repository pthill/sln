<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册</title>
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/bootstrap.min.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/base.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/hover.css">
    <link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/register.css">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--头部区域-->
    <div class="header-container">
        <div class="container clearfix">
            <div class="logo fl">
              <img src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/images/login/haihetaologo2.png" alt="">
              <span>&nbsp;&nbsp;&nbsp;&nbsp;欢迎登陆</span>
            </div>
            <div class="go-login fr">
              <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/login.html">已有账号？<span>请登陆</span></a>
            </div>
        </div>
    </div>
    <!--主体区域-->
    <div class="main-container">  
      <div class="container">
        <div class="login-wrap">
            <div class="put-wrap">
              <form onSubmit="return false;" id="formRegister">
                <div class="form-group">
                    <label for="userName" style="letter-spacing: 10px;">手机号</label>
                    <input type="text" id="userName" name="userName" class="form-control"  placeholder="请输入手机号">
                </div>
                <div class="form-group">
                    <label for="pass">设置密码</label>
                    <input type="password" id="pass" name="password" class="form-control"  placeholder="建议使用数字与字母组合,长度为6-12位">
                </div>
                <div class="form-group">
                    <label for="comfirmPass">确认密码</label>
                    <input type="password" id="comfirmPass" name="comfirmPass"  class="form-control"  placeholder="请再次输入密码">
                </div>
                <div class="form-group">
                    <label for="QRcode" style="letter-spacing: 10px;">验证码</label>
                    <input type="text" id="QRcode" name="QRcode" class="form-control" placeholder="图片验证码">
                    <div class="code-img">
                      <img id="code_img" src="${(domainUrlUtil.SLN_URL_RESOURCES)!}/verify.html" onclick="refreshCode();" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="phoneQRcode" style="letter-spacing: 0;">验证码</label>
                    <input type="text" id="phoneQRcode" name="phoneQRcode" class="form-control" placeholder="请输入验证码">
                    <div class="code-btn">
                       <button type="button" id="code" onclick="getCode()" class="block">获取验证码</button>
                    </div>
                </div>
                <div class="form-group protocol">
                  <input type="checkbox" id="check" checked="checked">
                  <label for="check">我已阅读并同意</label>
                  <a href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/portal/protocol.html">《海核云谷用户协议》</a>
                </div>  
                <div class="form-group">
                    <button type="submit" id="registerBtn" class="btn">立即注册</button>
                </div>
                <div class="err-tip"></div>             
              </form>
            </div>
        </div>
     </div>
    </div>
    <div class="footer-container">版权所有 © Copyright 2017 粤ICP备17164358号-1 海核云谷.保留一切权利</div>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/verification.js"></script>
    <script type="text/javascript">
        //刷新验证码
        var domain = '${(domainUrlUtil.SLN_URL_RESOURCES)!}';
        function refreshCode(){
            jQuery("#code_img").attr("src",domain+"/verify.html?d"+new Date().getTime());
        }
        $(function () {
            $("#userName").on('blur',function(){
                var userName = $.trim($('#userName').val());
                if (userName == '') {
                    showTips('请输入您的登陆账号！');
                    $('#userName').focus().addClass('focus');
                    return ;
                }else if (!regular.isPhone(userName)) {
                    showTips('手机号码格式不对！');
                    $('#userName').focus().addClass('focus');
                    return ;
                }else{
                    $.ajax({
                        type : "get",
                        url : domain + "/nameIsExist.html?name="+userName,
                        dataType : "json",
                        async : false,
                        success : function(data) {
                            if (data.success) {
                            } else {
                                showTips(data.message);
                                $('#userName').focus().addClass('focus');
                            }
                        }

                    });
                }
            });
            /*登录*/
            $('#registerBtn').on('click', function () {
                var userName = $.trim($('#userName').val());
                var pass = $.trim($('#pass').val());
                var comfirmPass = $.trim($('#comfirmPass').val());
                var QRcode = $.trim($('#QRcode').val());
                var phoneQRcode = $.trim($('#phoneQRcode').val());
                var isCheck = $('#check').prop('checked');
                if (userName == '') {
                    showTips('请输入您的手机号！');
                    $('#userName').focus().addClass('focus');
                }else if (!regular.isPhone(userName)) {
                    showTips('手机号码格式不对！');
                    $('#userName').focus().addClass('focus');
                    return ;
                } else if (pass == '') {
                    showTips('请输入密码！');
                    $('#pass').focus().addClass('focus');
                    return;
                } else if (!regular.isPass(pass)) {
                    showTips('密码为6-12的数字或字母！');
                    $('#pass').focus().addClass('focus');
                    return;
                }else if (comfirmPass == '') {
                    showTips('请再次输入密码！');
                    $('#comfirmPass').focus().addClass('focus');
                    return;
                } else if (comfirmPass != pass) {
                    showTips('您两次输入的密码不一致！');
                    $('#comfirmPass').focus().addClass('focus');
                    return;
                } else if (QRcode == '') {
                    showTips('请输入验证码！');
                    $('#QRcode').focus().addClass('focus');
                    return
                } else if (QRcode.length != 4) {
                    showTips('验证码为4位！');
                    $('#QRcode').focus().addClass('focus');
                    return;
                } else if (phoneQRcode == '') {
                    showTips('请输入手机验证码！');
                    $('#phoneQRcode').focus().addClass('focus');
                    return;
                } else if (phoneQRcode.length != 6) {
                    showTips('手机验证码为6位！');
                    $('#phoneQRcode').focus().addClass('focus');
                    return;
                } else if (!isCheck) {
                    showTips('请您确认用户协议！');
                    return;
                } else {
                    $('.err-tip').html('');
                }
                $("#registerBtn").attr("disabled", "disabled");
                var params = $('#formRegister').serialize();
                $.ajax({
                    type : "POST",
                    url : domain + "/portal/doRegister.html",
                    dataType : "json",
                    async : false,
                    data : params,
                    success : function(data) {
                        if (data.success) {
                            window.location.href=domain + data.backUrl;
                        } else {
                            showTips(data.message);
                            refreshCode();
                            $("#registerBtn").removeAttr("disabled");
                        }
                    },
                    error : function() {
                        $("#registerBtn").removeAttr("disabled");
                    }
                });
            });
            $('#userName').on('keyup', function () {
                verification(this, '请输入手机号！');
            });
            $('#pass').on('keyup', function () {
                verification(this, '请输入密码！');
            });
            $('#comfirmPass').on('keyup', function () {
                verification(this, '请输入确认密码！');
            });
            $('#QRcode').on('keyup', function () {
                verification(this, '请输入验证码！');
            });
            $('#phoneQRcode').on('keyup', function () {
                verification(this, '请输入手机验证码！');
            });
            $('.dropdown-menu li').on('click', function () {
                var text = $(this).text();
                $('.input-group-btn button').html(text + ' <span class="caret"></span>');
            });
            isFixed();
        });
        //回车时间提示框
        function verification(el, text) {
            var value = $.trim($(el).val());
            if (value == '') {
                showTips(text)
                return false;
            } else {
                $(el).removeClass('focus');
                $('.err-tip').html('');
            }
        }
        //错误提示
        function showTips(text) {
            var html = '<em></em> <span>' + text + '</span>';
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
        function getCode() {
            var sendverfiy = false;
            var telphone = $.trim($("#userName").val());
            if(telphone==null||telphone==""){
                $('#userName').focus().addClass('focus');
                showTips('请输入手机号');
                return;
            }
            if (!regular.isPhone(telphone)) {
                showTips('手机号码格式不对！');
                $('#userName').focus().addClass('focus');
                return;
            }
            $.ajax({
                type : 'post',
                url : domain + '/portal/sendSMS.html?mob=' + telphone
                + '&type=reg',
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
            if(sendverfiy){
                var time = 120;
                $("#code").attr("disabled", "disabled");
                $("#code").html(time+"秒后重新获取");
                time--;
                intervalId = setInterval(function() {
                    $("#code").html(time+"秒后重新获取");
                    time--;
                    if (time == 0) {
                        clearInterval(intervalId);
                        $("#code").removeAttr("disabled");
                        $("#code").html("获取验证码");
                    }
                }, 1000);
            }
        }
  </script>
  </body>
</html>