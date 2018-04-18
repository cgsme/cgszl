<%@page contentType="text/html;charset=UTF-8"%>
<%--<%@page import="org.apache.commons.lang.StringUtils" %>--%>

<%
    //WEB 应用路径
    String sSystemPath = request.getContextPath() + "/";
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="../../../images/favicon.png" />
<link rel="stylesheet" href="<%=sSystemPath %>common/layui/css/layui.css" media="all">
<%--<link rel="stylesheet" href="<%=sSystemPath %>common/bootstrap/css/bootstrap.css" media="all">--%>
<%--<link rel="stylesheet" href="<%=sSystemPath %>common/bootstrap/css/bootstrap-theme.css" media="all">--%>

<%--<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript" src="<%=sSystemPath %>common/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/localization/messages_zh.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/cgszl.utils.js"></script>
<%--<script type="text/javascript" src="<%=sSystemPath %>common/bootstrap/js/bootstrap.js"></script>--%>


<!--[if IE 9]>
<link rel="stylesheet" media="screen" href="<%=sSystemPath %>admin/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" media="screen" href="<%=sSystemPath %>admin/css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
<script src="<%=sSystemPath %>admin/js/plugins/css3-mediaqueries.js"></script>
<![endif]-->

<script type="text/javascript">
    //应用的主目录
    var WEB_APP_PATH = "<%= sSystemPath%>";

    // 模块的主路径
//    var MODULE_PATH = WEB_APP_PATH + "module" + "/";
</script>

