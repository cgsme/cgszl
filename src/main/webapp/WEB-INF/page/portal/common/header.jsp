<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>曹图图</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="shortcut icon" href="/portal/img/favicon.ico" />
    <link rel="bookmark" href="/portal/img/favicon.ico" type="image/x-icon"　/>
    <!-- Bootstrap样式 -->
    <link rel="stylesheet" href="/portal/css/bootstrap.min.css">
    <%-- 回到顶部样式 --%>
    <link rel="stylesheet" href="/portal/css/tab.css">
    <!-- 炫酷字体 -->
    <link rel="stylesheet" href="/portal/css/font-awesome/css/font-awesome.min.css">
    <!-- 谷歌字体 -->
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600|PT+Serif:400,400italic' rel='stylesheet' type='text/css'>--%>
    <!-- 样式 -->
    <link rel="stylesheet" href="/portal/css/style.css" id="theme-styles">
    <!--[如果小于 IE 9]>
    <script src="/portal/js/vendor/google/html5-3.6-respond-1.1.0.min.js"></script>
    <![endif]-->

</head>
<body>
<header>
    <div class="widewrapper masthead">
        <div class="container">
            <a href="/index.html" id="logo">
                <img src="/portal/img/logo2.png" alt="clean Blog">
            </a>

            <div id="mobile-nav-toggle" class="pull-right">
                <a href="#" data-toggle="collapse" data-target=".clean-nav .navbar-collapse">
                    <i class="fa fa-bars"></i>
                </a>
            </div>

            <nav class="pull-right clean-nav">
                <div class="collapse navbar-collapse">
                    <ul class="nav nav-pills navbar-nav">

                        <li>
                            <a href="/index.html">首页</a>
                        </li>
                        <li>
                            <a href="/about.html">关于</a>
                        </li>
                        <li>
                            <a href="/contact.html">联系我</a>
                        </li>
                    </ul>
                </div>
            </nav>

        </div>
    </div>

    <div class="widewrapper subheader">
        <div class="container">
            <div class="clean-breadcrumb">
                <a id="pageTitle">曹图图</a>
            </div>

            <div class="clean-searchbox">
                <%--<form action="/portal/search.html" method="get" accept-charset="utf-8">--%>
                    <input name="keyWord" class="searchfield" id="searchbox" maxlength="20"
                           required type="text" placeholder="搜索" value="${keyWord}">
                    <button class="searchbutton" onclick="header.search($('#searchbox').val())">
                        <i class="fa fa-search"></i>
                    </button>
                <%--</form>--%>
            </div>
        </div>
    </div>
</header>

