<%@ page import="cn.cgszl.common.constant.WebConst" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>登录页面</title>

    <link rel="shortcut icon" href="/admin/images/favicon.ico" />

    <link rel="stylesheet" href="css/style.default.css" type="text/css" />
    <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="js/custom/general.js"></script>
    <script type="text/javascript" src="js/custom/index.js"></script>
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/plugins/css3-mediaqueries.js"></script>
    <![endif]-->
<style type="text/css">
    #world{
        z-index: -1;
        position: absolute;
    }
</style>
</head>

<body class="loginpage">

<div class="loginbox">
    <div class="loginboxinner">

        <div class="logo">
            <h1 class="logo">博客.<span>登录</span></h1>
            <span class="slogan">曹图图</span>
        </div><!--logo-->

        <br clear="all" /><br />

        <div class="nousername">
            <div class="loginmsg">用户名不能为空.</div>
        </div><!--nousername-->

        <div class="errormessage">
            <div id="errormessage" class="loginmsg">${msg}</div>
        </div><!--nousername-->

        <div class="nopassword">
            <div class="loginmsg">密码不能为空.</div>
            <div class="loginf">
                <div class="thumb"><img alt="" src="images/thumbs/avatar1.png" /></div>
                <div class="userlogged">
                    <h4></h4>
                    <a href="index.html">不是 <span></span>?</a>
                </div>
            </div><!--loginf-->
        </div><!--nopassword-->

        <form id="login" action="/admin/login.html" method="post">

            <div class="username">
                <div class="usernameinner">
                    <input type="text" name="username" id="username" value="${username}" />
                </div>
            </div>

            <div class="password">
                <div class="passwordinner">
                    <input type="password" name="password" id="password" />
                </div>
            </div>

            <button>登录</button>

            <div class="keep"><input type="checkbox" /> 记住密码</div>

        </form>

    </div><!--loginboxinner-->
</div><!--loginbox-->
<%--<canvas id='world' style="position:absolute;z-index:-2;filter: alpha(opacity:50);opacity: 0.5"></canvas>--%>
<%--<script type="text/javascript" src="/admin/canvas/canvas.js"></script>--%>
</body>
</html>
