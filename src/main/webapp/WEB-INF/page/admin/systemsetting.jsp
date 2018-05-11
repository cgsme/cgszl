<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>系统设置基本信息页面</title>
    <%--<%@include file="common/common.jsp" %>--%>
    <%--<%@include file="common/pageResource.jsp" %>--%>
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/plugins/css3-mediaqueries.js"></script>
    <![endif]-->
    <link type="text/css" href="${request.pageContext.contextPath}/admin/css/plugins/jquery.tagsinput.css">
    <script src="${request.pageContext.contextPath}/admin/js/plugins/jquery.tagsinput.min.js"></script>
    <script src="js/custom/systemsettings.js"></script>
</head>
<body>
<blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">系统设置 / 基本信息</blockquote>
<div id="contentwrapper" class="contentwrapper">
    <form id="systemSettingForm" class="stdform"
          action="/admin/setting/saveSiteOptions.action" method="post">
        <p>
            <label>站点名称</label>
            <span class="field">
                <input type="text" value="${siteOptionsMap["site_title"]}"
                       maxlength="20" name="site_title" placeholder="站点的名称……"
                       style="width: 30%"/>
            </span>
            <small class="desc">用于展示.</small>
        </p>
        <p>
            <label>站点描述</label>
            <span class="field">
                <textarea type="text"
                          name="site_description"
                          rows="5"
                          placeholder="对站点进行描述……（100字以内）"
                          maxlength="200"
                          class="mediuminput">${siteOptionsMap["site_description"]}</textarea>
            </span>
            <small class="desc">站点简介.</small>
        </p>
        <p>
            <label>站点关键词</label>
            <span class="field">
                <input id="keyWordTags" type="text" value="${siteOptionsMap["site_keywords"]}" name="site_keywords"
                       maxlength="200" class="mediuminput"/>
            </span>
        </p>
        <p class="stdformbutton">
            <button class="submit radius2">保存</button>
            <input type="reset" class="reset radius2" value="重置"/>
        </p>
    </form>
</div>
</body>
</html>
