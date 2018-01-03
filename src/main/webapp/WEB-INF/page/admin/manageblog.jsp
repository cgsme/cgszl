<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>博客管理页面</title>
    <%@include file="common/common.jsp"%>
    <%@include file="common/pageResource.jsp"%>
    <script type="text/javascript" src="js/custom/blog.js"></script>

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

        <div id="contentwrapper" class="">

        </div><!--contentwrapper-->

    </div><!--centercontent-->


</div><!--bodywrapper-->

</body>
</html>

