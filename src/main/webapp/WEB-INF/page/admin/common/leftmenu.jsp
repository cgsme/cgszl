<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="vernav2 iconmenu">
    <ul>
        <li><a href="/admin/dashboard.html" class="tables">控制台</a></li>
        <li><a href="#formsub" class="editor">博客管理</a>
            <span class="arrow"></span>
            <ul id="formsub">
                <li><a href="manageblog.html">文章管理</a></li>
            </ul>
        </li>
        <li><a href="#messagesub" class="addons">消息管理</a>
            <span class="arrow"></span>
            <ul id="messagesub">
                <li><a href="commentmanager.html">评论</a></li>
                <li><a href="messagemanager.html">留言 / 反馈</a></li>
            </ul>
        </li>
        <li><a href="categorymanager.html" class="typo">分类/标签</a></li>
        <li><a href="#filesub" class="gallery">文件管理</a>
            <span class="arrow"></span>
            <ul id="filesub">
                <li><a href="filemanager.html">相册</a></li>
                <li><a href="attachmanager.html">附件</a></li>
            </ul>
        </li>
        <li><a href="linkmanager.html" class="support">友链管理</a></li>
        <%--<li><a href="pagemanager.html" class="widgets">页面管理</a></li>--%>
        <li><a href="#settingsub" class="elements">系统设置</a>
            <span class="arrow"></span>
            <ul id="settingsub">
                <li><a href="systemsetting.html">基本设置</a></li>
                <li><a href="attachsetting.html">附件存储</a></li>
            </ul>
        </li>

    </ul>
    <a class="togglemenu"></a>
    <br /><br />
</div><!--leftmenu-->

