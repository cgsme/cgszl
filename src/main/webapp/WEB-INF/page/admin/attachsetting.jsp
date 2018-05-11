<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>系统设置附件存储页面</title>
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
    <script src="js/custom/attachsettings.js"></script>
</head>
<body>
<blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">系统设置 / 附件存储</blockquote>
<div id="contentwrapper" class="contentwrapper">
    <div class="contenttitle2">
        <h3>存储配置</h3>
    </div><!--contenttitle-->
    <form id="attachSettingForm" class="stdform"
          action="/admin/setting/saveSiteOptions.action" method="post">
        <p>
            <label>相册文件存储路径</label>
            <span class="field">
                <input type="text" value="${siteOptionsMap["photo_upload_path"]}"
                       maxlength="200" name="photo_upload_path" placeholder="请输入合法路径……"
                       style="width: 60%"/>
            </span>
            <small class="desc">上传的相册文件将保存至该位置.</small>
        </p>
        <p>
            <label>附件文件存储路径</label>
            <span class="field">
                <input type="text" value="${siteOptionsMap["attach_upload_path"]}"
                       maxlength="200" name="attach_upload_path" placeholder="请输入合法路径……"
                       style="width: 60%"/>
            </span>
            <small class="desc">上传的附件文件将保存至该位置.</small>
        </p>
        <p>
            <label>备份文件存储路径</label>
            <span class="field">
                <input type="text" name="attach_backup_path" placeholder="请输入合法路径……"
                       value="${siteOptionsMap["attach_backup_path"]}" maxlength="200" class="mediuminput"/>
            </span>
            <small class="desc">备份的文件将保存至该位置.</small>
        </p>
        <p class="stdformbutton">
            <button class="submit radius2">保存</button>
            <input type="reset" class="reset radius2" value="重置"/>
        </p>
    </form>
    <div class="contenttitle2">
        <h3>数据备份</h3>
    </div><!--contenttitle-->
    <form id="backUpForm" class="stdform" method="post">
        <p>
            <label>备份文件</label>
            <span class="formwrapper">
                <input type="checkbox" name="backUpFileType" checked value="photoFile"/> 相册<br/>
                <input type="checkbox" name="backUpFileType" value="attachFile"/> 附件<br/>
                <%--<input type="checkbox" name="backUpFileType" value="sqlFile"/> 数据库文件<br/>--%>
            </span>
            <small class="desc">选择需要备份的数据.</small>
        </p>
        <p>
            <label>文件备份路径</label>
            <span class="field">
                <input type="text" name="backUpPath" placeholder="请输入合法路径……"
                       required oninvalid="setCustomValidity('请输入文件备份路径')"  oninput="setCustomValidity('')"
                       value="${siteOptionsMap["attach_backup_path"]}" maxlength="200" class="mediuminput"/>
            </span>
            <small class="desc">
                备份的文件将保存至该位置，目录不存在时将自动创建该目录。
                默认目录为系统配置目录.
            </small>
        </p>
        <p class="stdformbutton">
            <button class="submit radius2">开始备份</button>
        </p>
    </form>
</div>
</body>
</html>
