<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>博客管理页面</title>
    <%@include file="common/common.jsp"%>
    <%@include file="common/pageResource.jsp"%>
    <script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/plugins/tinymce/tinymce.min.js"></script>
    <script type="text/javascript" src="js/custom/blog.js"></script>

</head>
<body class="withvernav">
<div class="bodywrapper">

    <jsp:include page="common/header.jsp"/>

    <jsp:include page="common/blogmanagerleftmenu.jsp"/>

    <div class="centercontent">

        <%--<div class="pageheader">--%>
        <div>
            <%--<h1 class="pagetitle">管理文章</h1>--%>
            <%--<span class="pagedesc">以下内容由ajax加载</span>--%>

            <ul class="hornav blogmenu">
                <li><a href="newpost.html">新文章</a></li>
                <li class="current"><a id="publishedTab" href="allposts.html">已发布 (<span id="totalPub">-</span>)</a></li>
                <li><a id="draftTab" href="draft.html">草稿箱 (<span id="totalDraft">-</span>)</a></li>
                <li><a href="trash.html">回收站 (<span id="totalTrash">-</span>)</a></li>
            </ul>
        </div><!--pageheader-->

        <div id="contentwrapper"><%-- class="contentwrapper"--%>

        </div><!--contentwrapper-->

    </div><!--centercontent-->


</div><!--bodywrapper-->

</body>
</html>

