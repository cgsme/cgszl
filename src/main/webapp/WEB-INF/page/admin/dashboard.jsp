<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="shortcut icon" href="/admin/images/favicon.ico"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>曹图图</title>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>

    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/jquery.flot.min.js"></script>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/jquery.flot.time.js"></script>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/jquery.flot.resize.min.js"></script>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/jquery.slimscroll.js"></script>

    <script type="text/javascript" src="<%=sSystemPath %>admin/js/custom/dashboard.js"></script>
    <script type="text/javascript" src="js/custom/tables.js"></script>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/tinymce/tinymce.min.js"></script>

    <!--[if lte IE 8]>
    <!--<script language="javascript" type="text/javascript" src="<%=sSystemPath %>admin/js/plugins/excanvas.min.js"></script>-->
    <![endif]-->
    <!--[if IE 9]>
    <!--<link rel="stylesheet" media="screen" href="css/style.ie9.css"/>-->
    <![endif]-->
    <!--[if IE 8]>
    <!--<link rel="stylesheet" media="screen" href="css/style.ie8.css"/>-->
    <![endif]-->
    <!--[if lt IE 9]>
    <!--<script src="js/plugins/css3-mediaqueries.js"></script>-->
    <![endif]-->
</head>

<body class="withvernav">
<div class="bodywrapper">

    <%--引入网页头部--%>
    <jsp:include page="common/header.jsp"/>
    <%--引入左边菜单--%>
    <jsp:include page="common/leftmenu.jsp"/>

    <div id="bolgmanagerbox" class="centercontent">

        <%--<div class="pageheader">
            &lt;%&ndash;<h1 class="pagetitle">控制台</h1>&ndash;%&gt;
            &lt;%&ndash;<span class="pagedesc">系统概览</span>&ndash;%&gt;

            <ul class="hornav">
                <li class="current"><a href="#updates">最新更新</a></li>
                <li><a href="#activities">最近活动</a></li>
            </ul>
        </div>--%><!--pageheader-->

        <div id="contentwrapper" class="contentwrapper">

            <div id="updates" class="subcontent">
                <%--<div class="notibar announcement">--%>
                <%--<a class="close"></a>--%>
                <%--<h3>公告</h3>--%>
                <%--<p>今天下午三点，系统维护。</p>--%>
                <%--</div><!-- notification announcement -->--%>

                <div class="two_third dashboard_left">

                    <%--<ul class="shortcuts">--%>
                    <%--<li><a href="" class="settings"><span>设置</span></a></li>--%>
                    <%--<li><a href="" class="users"><span>用户</span></a></li>--%>
                    <%--<li><a href="" class="gallery"><span>相册</span></a></li>--%>
                    <%--<li><a href="" class="events"><span>事件</span></a></li>--%>
                    <%--<li><a href="" class="analytics"><span>分析</span></a></li>--%>
                    <%--</ul>--%>

                    <%--<br clear="all"/>--%>

                    <%--<div class="contenttitle2 nomargintop">
                        <h3>操作日志</h3>
                    </div>--%><!--contenttitle-->

                    <%--<div class="overviewhead">
                        <div class="overviewselect">
                            <select id="overviewselect" name="select">
                                <option value="">Last 1 hour ago</option>
                                <option value="">Last 5 hours ago</option>
                                <option value="">Today</option>
                                <option value="">Yesterday</option>
                                <option value="">This Week</option>
                                <option value="">Last Week</option>
                                <option value="">This Month</option>
                                <option value="">Last Month</option>
                            </select>
                        </div><!--floatright-->
                        From: &nbsp;<input type="text" id="datepickfrom"/> &nbsp; &nbsp; To: &nbsp;<input type="text"
                                                                                                          id="datepickto"/>
                    </div><!--overviewhead-->

                    <br clear="all"/>

                    <table cellpadding="0" cellspacing="0" border="0" class="stdtable overviewtable">
                        <colgroup>
                            <col class="con0" width="20%"/>
                            <col class="con1" width="20%"/>
                            <col class="con0" width="20%"/>
                            <col class="con1" width="20%"/>
                            <col class="con0" width="20%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="head0">Metric</th>
                            <th class="head1">Rates</th>
                            <th class="head0">Impressions</th>
                            <th class="head1">Unique Visits</th>
                            <th class="head0">平均时间 (min)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="progress progress150">
                                    <div class="bar">
                                        <div style="width: 60%;" class="value bluebar"></div>
                                    </div>
                                </div>
                            </td>
                            <td>67.3%</td>
                            <td>856, 220</td>
                            <td class="center">32, 012</td>
                            <td class="center">20.5</td>
                        </tr>
                        </tbody>
                    </table>

                    <br clear="all"/>

                    <div id="chartplace" style="height:300px;"></div>

                    <br clear="all"/>

                    <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb overviewtable2">
                        <colgroup>
                            <col class="con0" style="width: 4%"/>
                            <col class="con1"/>
                            <col class="con0"/>
                            <col class="con1"/>
                            <col class="con0"/>
                            <col class="con1"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="head0"><input type="checkbox" class="checkall"/></th>
                            <th class="head1">Rendering engine</th>
                            <th class="head0">Browser</th>
                            <th class="head1">Platform(s)</th>
                            <th class="head0">Engine version</th>
                            <th class="head1">CSS grade</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th class="head0"><input type="checkbox" class="checkall"/></th>
                            <th class="head1">Rendering engine</th>
                            <th class="head0">Browser</th>
                            <th class="head1">Platform(s)</th>
                            <th class="head0">Engine version</th>
                            <th class="head1">CSS grade</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr>
                            <td align="center"><input type="checkbox"/></td>
                            <td>Trident</td>
                            <td>Internet Explorer 5.5</td>
                            <td>Win 95+</td>
                            <td class="center">5.5</td>
                            <td class="center">A</td>
                        </tr>
                        <tr>
                            <td align="center"><input type="checkbox"/></td>
                            <td>Trident</td>
                            <td>Internet Explorer 6</td>
                            <td>Win 98+</td>
                            <td class="center">6</td>
                            <td class="center">A</td>
                        </tr>
                        <tr>
                            <td align="center"><input type="checkbox"/></td>
                            <td>Trident</td>
                            <td>Internet Explorer 7</td>
                            <td>Win XP SP2+</td>
                            <td class="center">7</td>
                            <td class="center">A</td>
                        </tr>
                        </tbody>
                    </table>

                    <br/>--%>

                    <div class="widgetbox">
                        <div class="title"><h3>最新文章</h3></div>
                        <div class="widgetcontent">
                            <div id="" class="mousescroll">
                                <ul class="entrylist">
                                    <c:forEach items="${recentArticleList}" var="recentArticle">
                                        <li>
                                            <div class="entry_wrap">
                                                    <%--<div class="entry_img"><img src="images/thumbs/image2.png" alt=""/></div>--%>
                                                <div class="entry_content" style="margin-left: 0px;">
                                                    <h4>
                                                        <a href="<%=sSystemPath %>single/${recentArticle.aid}.html"
                                                           target="_blank">
                                                                ${recentArticle.title}
                                                        </a>
                                                    </h4>
                                                    <p>
                                                        作者: <a><strong>${recentArticle.user.screenName}</strong></a>
                                                        - ${recentArticle.created}
                                                    </p>
                                                        <%--<p>Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet,--%>
                                                        <%--consectetur, adipisci velit, sed quia non...</p>--%>
                                                        <%--<p>--%>
                                                        <%--<button class="stdbtn btn_lime">Approve</button>--%>
                                                        <%--<button class="stdbtn">Decline</button>--%>
                                                        <%--</p>--%>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div><!--#scroll1-->
                        </div><!--widgetcontent-->
                    </div><!-- widgetbox -->

                    <div class="contenttitle2 nomargintop">
                        <h3>操作日志</h3>
                    </div><!--contenttitle-->

                    <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="logTable">
                        <colgroup>
                            <col class="con0" style="width: 8%"/>
                            <col class="con1"/>
                            <col class="con0"/>
                            <col class="con1"/>
                            <col class="con0"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>操作</th>
                            <th>操作模块</th>
                            <th>操作人员</th>
                            <th>客户端ip</th>
                            <th>操作时间</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <br/>
                </div><!--two_third dashboard_left -->

                <div class="one_third last dashboard_right">

                    <div class="contenttitle2 nomargintop">
                        <h3>统计概况</h3>
                    </div><!--contenttitle-->

                    <ul class="toplist">
                        <li>
                            <div>
                                <span class="three_fourth">
                                    <span class="left">
                                        <span class="title"><a>发布文章数量</a></span>
                                        <span class="desc">已发布的文章</span>
                                    </span><!--left-->
                                </span><!--three_fourth-->
                                <span class="one_fourth last">
                                    	<span class="right">
                                        	<span class="h3">${statistics.articleNum}</span>
                                        </span><!--right-->
                                    </span><!--one_fourth-->
                                <br clear="all"/>
                            </div>
                        </li>
                        <li>
                            <div>
                                <span class="three_fourth">
                                    <span class="left">
                                        <span class="title"><a>收到评论数量</a></span>
                                        <span class="desc">访客评论</span>
                                    </span><!--left-->
                                </span><!--three_fourth-->
                                <span class="one_fourth last">
                                    	<span class="right">
                                        	<span class="h3">${statistics.commentNum}</span>
                                        </span><!--right-->
                                    </span><!--one_fourth-->
                                <br clear="all"/>
                            </div>
                        </li>
                        <li>
                            <div>
                                	<span class="three_fourth">
                                    	<span class="left">
                                    		<span class="title"><a>收到留言数量</a></span>
                                        	<span class="desc">访客留言</span>
                                    	</span><!--left-->
                                    </span><!--three_fourth-->
                                <span class="one_fourth last">
                                    	<span class="right">
                                        	<span class="h3">${statistics.messageNum}</span>
                                        </span><!--right-->
                                    </span><!--one_fourth-->
                                <br clear="all"/>
                            </div>
                        </li>
                        <li>
                            <div>
                                	<span class="three_fourth">
                                    	<span class="left">
                                    		<span class="title"><a>友链的好友数量</a></span>
                                        	<span class="desc">友情链接</span>
                                    	</span><!--left-->
                                    </span><!--three_fourth-->
                                <span class="one_fourth last">
                                    	<span class="right">
                                        	<span class="h3">${statistics.linkNum}</span>
                                        </span><!--right-->
                                    </span><!--one_fourth-->
                                <br clear="all"/>
                            </div>
                        </li>
                    </ul>

                    <div class="widgetbox">
                        <div class="title"><h3>最新留言</h3></div>
                        <%--<div class="widgetoptions">
                            <div class="right"><a href="">View All Users</a></div>
                            <a href="">Add User</a>
                        </div>--%>
                        <div class="widgetcontent userlistwidget nopadding">
                            <ul>
                                <c:forEach items="${recentMessageList}" var="recentMessage">
                                    <li>
                                        <div class="avatar"><img alt="" src="images/thumbs/avatar1.png"/></div>
                                        <div class="info">
                                            <a>${recentMessage.author}</a>
                                            <br/>
                                                ${recentMessage.content}
                                            <br/>
                                                ${recentMessage.created}
                                        </div><!--info-->
                                    </li>
                                </c:forEach>
                            </ul>
                            <%--<a class="more" href="">View More Users</a>--%>
                        </div><!--widgetcontent-->
                    </div>

                    <div class="widgetbox">
                        <div class="title"><h3>最新评论</h3></div>
                        <div class="widgetcontent">
                            <div id="accordion" class="accordion">
                                <c:forEach var="recentComment" items="${recentCommentList}">
                                    <h3><a href="#">${recentComment.author} - ${recentComment.created} <fmt:formatDate value="" pattern="yyyy-MM-dd"/></a></h3>
                                    <div style="word-wrap: break-word;">
                                        <p>
                                            ${recentComment.content}
                                        </p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div> <!--widgetcontent-->
                    </div><!--widgetbox-->
                </div><!--one_third last-->
            </div><!-- #updates -->

            <%-- 最近活动 --%>
            <%--<div id="activities" class="subcontent" style="display: none;">

                <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="logTable">
                    <colgroup>
                        <col class="con0" style="width: 4%"/>
                        <col class="con0" style="width: 5%"/>
                        <col class="con1"/>
                        <col class="con0"/>
                        <col class="con1"/>
                        <col class="con0"/>
                        <col class="con1"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th><input type="checkbox" class="checkall"/></th>
                        <th>序号</th>
                        <th>操作</th>
                        <th>操作模块</th>
                        <th>操作人员</th>
                        <th>客户端ip</th>
                        <th>操作数据</th>
                        <th>操作时间</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>

            </div>--%><!-- #activities -->

        </div><!--contentwrapper-->

        <br clear="all"/>

    </div><!-- centercontent -->


</div><!--bodywrapper-->

</body>
</html>

