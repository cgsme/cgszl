<%@page contentType="text/html;charset=UTF-8"%>
<%--<%@page import="org.apache.commons.lang.StringUtils" %>--%>

<%
    String appId = "ModelConfigDB";
    //WEB 应用路径
    String sSystemPath = request.getContextPath() + "/";

    String longTime = "";

    //final String TITLE = "平台";

    //获取退出地址与登录地址
    //ServletContext sc = this.getServletContext();
    //String casLogoutUrl = sc.getInitParameter("casLogoutUrl");
    //String casLoginUrl = sc.getInitParameter("casLoginUrl");

    //LoginInfo loginInfo = AppSessionUtils.getLoginInfo(request);

    // 应用的主路径
//    String sSystemPath = sSystemPath;
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<script type="text/javascript" src="<%=sSystemPath %>js/eventResite.js"></script>

<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery-1.8.2.min.js"></script>
<%--<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="<%=sSystemPath %>common/js/jquery.form.js"></script>

<link href="<%=sSystemPath %>js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    //应用的主目录
    var WEB_APP_PATH = "<%= sSystemPath%>";

    // 模块的主路径
//    var MODULE_PATH = WEB_APP_PATH + "module" + "/";
</script>

