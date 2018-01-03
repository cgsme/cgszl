<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="common/common.jsp"%>
    <%@include file="common/pageResource.jsp"%>
    <title>发布文章页面</title>
    <%--<script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/plugins/tinymce2/jquery.tinymce.js"></script>--%>
    <script type="text/javascript" src="${request.pageContext.contextPath}/admin/js/plugins/tinymce/tinymce.min.js"></script>

    <%--<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>--%>
    <script type="text/javascript">

        jQuery(document).ready(function() {
/*
            ///// TINYMCE EDITOR /////
            jQuery('textarea.tinymce').tinymce({
                // Location of TinyMCE script
                script_url : 'js/plugins/tinymce/tiny_mce.js',

                // General options
                theme : "advanced",
                skin : "themepixels",
                plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",
                inlinepopups_skin: "themepixels",
                // Theme options
                theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,outdent,indent,blockquote,formatselect,fontselect,fontsizeselect",
                theme_advanced_buttons2 : "pastetext,pasteword,|,bullist,numlist,|,undo,redo,|,link,unlink,image,help,code,|,preview,|,forecolor,backcolor,removeformat,|,charmap,media,|,fullscreen",
                theme_advanced_buttons3 : "",
                theme_advanced_toolbar_location : "top",
                theme_advanced_toolbar_align : "left",
                theme_advanced_statusbar_location : "bottom",
                theme_advanced_resizing : true,

                // Example content CSS (should be your site CSS)
                content_css : "css/plugins/tinymce.css",

                // Drop lists for link/image/media/template dialogs
                template_external_list_url : "lists/template_list.js",
                external_link_list_url : "lists/link_list.js",
                external_image_list_url : "lists/image_list.js",
                media_external_list_url : "lists/media_list.js",

                // Replace values for the template plugin
                template_replace_values : {
                    username : "Some User",
                    staffid : "991234"
                }
            });
*/
            tinymce.init({
                mode : "textareas",
                language : "zh_CN",
                toolbar1: "undo redo | cut copy paste | bold italic underline strikethrough | " +
                            "alignleft aligncenter alignright alignjustify | " +
                            "searchreplace | " +
                            "bullist numlist | outdent indent blockquote | link unlink anchor image media code | " +
                            "inserttime preview | forecolor backcolor | table | hr removeformat | " +
                            "subscript superscript | charmap emoticons | print fullscreen | ltr rtl | " +
                            "spellchecker | visualchars visualblocks nonbreaking template pagebreak restoredraft | " +
                            "styleselect formatselect fontselect fontsizeselect",

            });

            jQuery('.editornav a').click(function(){
                jQuery('.editornav li.current').removeClass('current');
                jQuery(this).parent().addClass('current');
                if(jQuery(this).hasClass('visual'))
                    jQuery('#elm1').tinymce().show();
                else
                    jQuery('#elm1').tinymce().hide();
                return false;
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

<body class="withvernav">
<div class="bodywrapper">
    <jsp:include page="common/header.jsp"/>

    <jsp:include page="common/blogmanagerleftmenu.jsp"/>

    <div class="centercontent">

        <div class="pageheader notab">
            <h1 class="pagetitle">创建新文章</h1>
            <span class="pagedesc">可在此处创建新的文章</span>

        </div><!--pageheader-->

        <div class="contentwrapper">
            <div class="two_third">
                <form action="21312.html" method="post">
                    <input type="text" name="title" class="fullwidth" placeholder="请输入文章标题（必须）" required />

                    <br /><br />

                    <!-- 取代TinyMCE，记住文本区域中的HTML应该被编码 -->
                    <div>
                        <textarea id="content" name="content" rows="15" cols="80" style="width: auto" class="tinymce">

                        </textarea>
                    </div>

                    <br />
                    <input type="submit" name="save" value="创建" />
                    <input type="reset" name="reset" value="重置" />
                    <br /><br />
                </form>
            </div><!--two_third-->
        </div><!--contentwrapper-->

    </div><!--centercontent-->


</div><!--bodywrapper-->

</body>
</html>
