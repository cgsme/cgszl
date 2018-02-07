<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>
    <title>发布文章页面</title>
    <link type="text/css" href="${request.pageContext.contextPath}/admin/css/plugins/jquery.tagsinput.css">
    <script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript"
            src="${request.pageContext.contextPath}/admin/js/plugins/tinymce/tinymce.min.js"></script>
    <%--<script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/custom/forms.js"></script>--%>

    <%--<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>--%>
    <script type="text/javascript">
        ////// 保存(发布)文章 //////
        function saveArticle1(actionType) {
            var url = "";
            if (actionType === "publish") {
                url = "/admin/savePost.action?actionType=publish";
            }
            if (actionType === "draft") {
                url = "/admin/savePost.action?actionType=deaft";
            }
            // 序列化表单数据为字符串
            var formData = jQuery("#articleForm").serializeJSON();
            // 获取富文本编辑器中的内容,包括html标签
            var content = tinyMCE.activeEditor.getContent();
            // 封装content属性
            formData.content = content;
            jQuery.ajax({
                url : url,
                data: formData,
                type: 'POST',
                dataType:'JSON',
                success: function (data) {
                    if (data.success) {
                        // 刷新父页面的列表
                        parent.table.reload('articleGrid');
                        parent.layer.closeAll();
                        top.layer.msg('操作成功', {icon: 1, title: "系统提示" });
                    } else {
                        parent.top.layer.msg(data.message, {icon: 2, title: "系统提示" });
                    }
                },
            });
        }

        jQuery(document).ready(function () {
            ////// 初始化富文本编辑器 //////
            tinymce.init({
                mode: "textareas",
                language: "zh_CN",
                theme: 'modern',
                plugins: 'print preview fullpage searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern help',
                toolbar1: "undo redo | cut copy paste | bold italic underline strikethrough | " +
                "alignleft aligncenter alignright alignjustify | " +
                "searchreplace | " +
                "bullist numlist | outdent indent blockquote | link unlink anchor image media code | " +
                "inserttime preview | forecolor backcolor | table | hr removeformat | " +
                "subscript superscript | charmap emoticons | print fullscreen | ltr rtl | " +
                "spellchecker | visualchars visualblocks nonbreaking template pagebreak restoredraft | " +
                "styleselect formatselect fontselect fontsizeselect",

            });

            ////// 表单元素转换 //////
            jQuery('input:checkbox, input:radio, select.uniformselect, input:file').uniform();

            /*jQuery('.editornav a').click(function () {
                jQuery('.editornav li.current').removeClass('current');
                jQuery(this).parent().addClass('current');
                if (jQuery(this).hasClass('visual'))
                    jQuery('#elm1').tinymce().show();
                else
                    jQuery('#elm1').tinymce().hide();
                return false;
            });*/

            ////// 将文本框转换为特殊标签样式 //////
            jQuery('#tags').tagsInput({
                width : '',
                height: '',
                defaultText: '请输入标签...'
            });
        });

    </script>
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

<body class="" style="background-color: white">
<%--<body class="withvernav">--%>
<%--<div class="bodywrapper">--%>
    <%--<jsp:include page="common/header.jsp"/>--%>

    <%--<jsp:include page="common/blogmanagerleftmenu.jsp"/>--%>

    <%--<div class="centercontent">--%>
    <%--<div class="">--%>

        <div class="contentwrapper">

            <form id="articleForm" class="stdform stdform2" method="post">
                <input type="hidden" name="aid" id="aid" value="${article.aid}" />
                <p>
                    <label>文章标题</label>
                    <span class="field">
                        <input type="text" name="title" id="title" class="longinput"
                                               value="${article.title}" placeholder="请输入文章标题（必须）" required />
                    </span>
                </p>

                <p>
                    <label>自定义访问路径</label>
                    <span class="field">
                        <input type="text" name="slug" id="sulg" class="longinput"  value="${article.slug}"
                               placeholder="自定义访问路径，如：my-first-artiicle" />
                    </span>
                </p>

                <p>
                    <label>选择分类</label>
                    <span class="field">
                        <select name="categories" id="categories" required>
                            <option value="">请选择分类...</option>
                            <c:forEach var="metas" items="${metasList}">
                                <option
                                        <c:if test="${article.categories eq metas.name}">
                                            selected
                                        </c:if>
                                        value="${metas.name}">${metas.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </p>

                <p>
                    <label>文章标签</label>
                    <span class="field">
                        <input name="tags" id="tags" type="text"  value="${article.tags}"  class="form-control" />
                    </span>
                </p>

                <p>
                    <label>开启评论</label>
                    <span class="field">
                        <c:choose>
                            <c:when test="${article.allowComment}">
                                <input type="radio" name="allowComment" value="1" checked required/> 开启 &nbsp; &nbsp;
                                <input type="radio" name="allowComment" value="0" required/> 关闭 &nbsp; &nbsp;
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="allowComment" value="1" required/> 开启 &nbsp; &nbsp;
                                <input type="radio" name="allowComment" value="0" checked required/> 关闭 &nbsp; &nbsp;
                            </c:otherwise>
                        </c:choose>
                        <%--<input type="radio" name="radiofield" disabled="disabled"/> Disabled Radio  &nbsp; &nbsp;--%>
                        <%--<input type="radio" name="radiofield" checked="checked" disabled="disabled"/> Disabled Radio--%>
                    </span>
                </p>

                <br/>
                <textarea cols="80" rows="5" name="content" id="content" style="width: auto; height: 300px" class="tinymce">${article.content}</textarea>

                <br />
                <div align="right">
                <%--<input type="submit" value="发布"/>--%>
                <button type="button" id="saveArticle"  onclick="saveArticle1('publish');" class="btn" >发布</button>
                <button type="button" id="saveDraft" onclick="saveArticle1('draft');" class="btn">存为草稿</button>
                <button type="reset" id="resetBtn" class="reset radius2">重置</button>&nbsp;&nbsp;
                <%--<button class="submit radius2">发布</button>--%>
                </div>
            </form>

        </div><!--subcontent-->
    <%--</div><!--contentwrapper-->--%>

<%--</div><!--centercontent-->--%>


<%--</div><!--bodywrapper-->--%>

</body>
</html>
