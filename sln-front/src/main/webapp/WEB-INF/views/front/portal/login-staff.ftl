<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>企业员工登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/hover.css">
    <link rel="stylesheet" href="css/login.css">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="bgc">
    <div class="bg-img"><img src="images/login/u430.jpg" alt=""></div>
    <!--主体区域-->
    <div class="main-container">
      <div class="container">
        <div class="return-btn">
          <a href="javascript:;">返回上一页</a>
        </div>
        <div class="logo text-center"><img src="images/login/u892.png" alt=""></div>       
        <div class="login-wrap">
            <ul class="row">
              <li class="col-md-4 active"><a class="block" href="javascript:;">登录</a></li>
              <li class="col-md-4"><a class="block" href="javascript:;">注册</a></li>
              <li class="col-md-4"><a class="block" href="javascript:;">忘记密码</a></li>
            </ul>
            <div class="staff-login clearfix">
              <span class="staff fl">企业员工登录</span>
              <a class="fr" href="javascript:;">普通登录&nbsp;&gt;</a>
            </div>
            <div class="put-wrap">
              <form onSubmit="return false;">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="手机号码...">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码...">
                </div>
                <div class="form-group">
                    <div class="checkbox">
                      <label for="remember">
                        <input id="remember" type="checkbox"> 记住账号
                      </label>
                      <a class="fr" href="javascript:;">忘记密码？</a>
                    </div>
                </div>  
                <div class="form-group">
                    <button type="submit" class="btn">立即登录</button>
                </div>         
              </form>
            </div>
        </div>
        <footer>Copyright 2016 haiheyungu Inc.,All rights reserved.
                <br/>Powered by haiheyungu</footer>       
      </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>
        $(function () {
        });
    </script>
  </body>
</html>