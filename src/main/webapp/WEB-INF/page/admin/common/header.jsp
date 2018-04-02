<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="topheader">
    <div class="left">
        <h1 class="logo">曹.<span>图图</span></h1>
        <span class="slogan">后台管理系统</span>

        <div class="search">
            <form action="" method="post">
                <input type="text" name="keyword" id="keyword" placeholder="请输入关键字..."/>
                <button class="submitbutton"></button>
            </form>
        </div><!--search-->

        <br clear="all"/>

    </div><!--left-->

    <div class="right">
        <!--<div class="notification">
            <a class="count" href="ajax/notifications.html"><span>9</span></a>
        </div>-->
        <div class="userinfo">
            <img src="images/thumbs/avatar.png" alt=""/>
            <span>${login_user.username}</span>
        </div><!--userinfo-->

        <div class="userinfodrop">
            <div class="avatar">
                <a href=""><img src="images/thumbs/avatarbig.png" alt=""/></a>
                <div class="changetheme">
                    切换主题: <br/>
                    <a class="default"></a>
                    <%--<a class="blueline"></a>--%>
                    <%--<a class="greenline"></a>--%>
                    <%--<a class="contrast"></a>--%>
                    <%--<a class="custombg"></a>--%>
                </div>
            </div><!--avatar-->
            <div class="userdata">
                <h4>${login_user.username}</h4>
                <span class="email">${login_user.email}</span>
                <ul>
                    <li id="editprofile"><a href="javascript:void(0)">编辑个人资料</a></li>
                    <li id="accountsettings"><a href="javascript:void(0)">账号设置</a></li>
                    <li id="help"><a href="javascript:void(0)">帮助</a></li>
                    <li><a href="/admin/logout.html">注销</a></li>
                </ul>
            </div><!--userdata-->
        </div><!--userinfodrop-->
    </div><!--right-->
</div>
<!--topheader-->
<script type="text/javascript">
    // 文档加载完执行
    jQuery(function () {
        // 编辑资料
        jQuery("#editprofile").click(function () {
            var body = null;
            // 多窗口模式，层叠置顶
            var editprofileWinIndex = layer.open({
                type: 2   // 此处是iframe
                , title: '编辑资料'
                , area: ['820px', '620px']
                , shade: 0.3
                , maxmin: true
                , content: '/admin/editProfile.html'     // 需要加载的页面地址
                , loading: true          // 显示正在加载...
                , end: function () {   // 窗口销毁时触发,无参数
                    // 重新加载列表数据
//                            table.reload('articleGrid');
                }
            });
        });

        // 账号设置
        jQuery("#accountsettings").click(function () {
            var body = null;
            // 多窗口模式，层叠置顶
            var editprofileWinIndex = layer.open({
                type: 2   // 此处是iframe
                , title: '账号设置'
                , area: ['420px', '320px']
                , shade: 0.3
                , maxmin: true
                , content: '/admin/editProfile.html'     // 需要加载的页面地址
                , loading: true          // 显示正在加载...
                , end: function () {   // 窗口销毁时触发,无参数
                    // 重新加载列表数据
//                            table.reload('articleGrid');
                }
            });
        });

        // 查看帮助
        jQuery("#help").click(function () {
            var body = null;
            // 多窗口模式，层叠置顶
            var editprofileWinIndex = layer.open({
                type: 1   // 此处是iframe
                , title: '帮助'
                , area: ['320px', '100px']
                , shade: 0.3
                , maxmin: true
                , content: '无法帮助'     // 需要加载的页面地址
                , loading: true          // 显示正在加载...
                , end: function () {   // 窗口销毁时触发,无参数
                    // 重新加载列表数据
//                            table.reload('articleGrid');
                }
            });
        });
    })
</script>
<%--<div class="header">
    <ul class="headermenu">
        <li class="current"><a href="dashboard.html"><span class="icon icon-flatscreen"></span>首页</a></li>
        <li><a href="manageblog.html"><span class="icon icon-pencil"></span>博客管理</a></li>
        <li><a href="messages.html"><span class="icon icon-message"></span>查看消息</a></li>
        <li><a href="reports.html"><span class="icon icon-chart"></span>统计报表</a></li>
    </ul>

    <div class="headerwidget">
        <div class="earnings">
            <div class="one_half">
                <h4>今日访问</h4>
                <h2>666</h2>
            </div><!--one_half-->
            <div class="one_half last alignright">
                <h4>历史访问</h4>
                <h2>999</h2>
            </div><!--one_half last-->
        </div><!--earnings-->
    </div><!--headerwidget-->

</div>--%><!--header-->

