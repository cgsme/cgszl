<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>博客管理页面</title>
    <link rel="stylesheet" href="css/style.default.css" type="text/css" />
    <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.alerts.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="js/custom/general.js"></script>
    <script type="text/javascript" src="js/custom/blog.js"></script>
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/plugins/css3-mediaqueries.js"></script>
    <![endif]-->
</head>
<body class="withvernav">
<div class="bodywrapper">

    <jsp:include page="common/header.jsp"/>

    <jsp:include page="common/blogmanagerleftmenu.jsp"/>

    <div class="centercontent">

        <div class="pageheader">
            <h1 class="pagetitle">管理文章</h1>
            <span class="pagedesc">以下内容由ajax加载</span>

            <ul class="hornav blogmenu">
                <li class="current"><a href="allposts.html">所有文章 (5)</a></li>
                <li><a href="published.html">已发布 (5)</a></li>
                <li><a href="draft.html">草稿 (3)</a></li>
                <li><a href="trash.html">垃圾 (1)</a></li>
            </ul>
        </div><!--pageheader-->

        <div id="contentwrapper" class="contentwrapper">

        </div><!--contentwrapper-->

    </div><!--centercontent-->


</div><!--bodywrapper-->

</body>
</html>

