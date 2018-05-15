<%--
  Created by IntelliJ IDEA.
  User: cguisheng
  Date: 2018/5/15
  Time: 22:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>500错误提示页面</title>
    <link rel="stylesheet" href="css/style.default.css" type="text/css" />
    <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/custom/general.js"></script>
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

<body>
<div class="bodywrapper">

    <div class="contentwrapper padding10">
        <div class="errorwrapper error403">
            <div class="errorcontent">
                <h1>500 Internal Server Error</h1>
                <h3>The server encountered an internal error and was unable to complete your request.</h3>

                <p>Please contact the server administrator <strong>webmaster@yourdomain.com</strong> and informed them of the time the error occurred.<br /> More information about this error may be available in the server error log.</p>
                <br />
                <button class="stdbtn btn_black" onclick="history.back()">Go Back to Previous Page</button> &nbsp;
                <button onclick="location.href='dashboard.html'" class="stdbtn btn_orange">Go Back to Dashboard</button>
            </div><!--errorcontent-->
        </div><!--errorwrapper-->
    </div>


</div><!--bodywrapper-->

</body>
</html>

