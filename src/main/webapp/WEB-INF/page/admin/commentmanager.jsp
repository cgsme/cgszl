<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--<%@include file="common/common.jsp" %>--%><%--防止重复加载资源文件导致卡顿--%>
    <%--<%@include file="common/pageResource.jsp" %>--%>

    <script type="text/javascript" src="/common/js/cgszl.utils.js"></script>
    <%--<script type="text/javascript" src="<%=sSystemPath %>admin/js/custom/tables.js"></script>--%>
    <title>所有文章页面</title>
</head>
<style>
    .layui-table-view {
        margin: 0px 0
    }
</style>
<body>
<blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">评论管理</blockquote>
<%--<div class="layui-btn-group articleTable" style="padding-top: 10px">--%>
<%--<button class="layui-btn layui-btn-primary" data-type="createNewArticle">--%>
<%--<i class="layui-icon">&#xe654;</i>创建新文章--%>
<%--</button>--%>
<%--<button class="layui-btn layui-btn-primary" data-type="getCheckData">获取选中行数据</button>--%>
<%--<button class="layui-btn layui-btn-primary" data-type="getCheckLength">获取选中数目</button>--%>
<%--<button class="layui-btn layui-btn-primary" data-type="isAll">验证是否全选</button>--%>
<%--</div>--%>
<%--文章列表--%>
<table id="commentGrid" lay-filter="commentGrid">
</table>
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862"
     data-ad-slot="3820120620"></ins>

<script type="text/html" id="toolbar">
    <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="pass">
        <i class="layui-icon">&#xe605;</i>通过
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unpass">
        <i class="layui-icon">&#x1006;</i>不通过
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
        <i class="layui-icon">&#xe640;</i>删除
    </a>
</script>

<script>
    jQuery(function () {
        ///// 水平导航（ajax / 内联数据） /////
        jQuery('.hornav a').click(function () {

            // 这仅适用于窗口大小低于450像素的情况
            if (jQuery(this).parents('.more').length == 0)
                jQuery('.hornav li.more ul').hide();

            // 删除当前菜单
            jQuery('.hornav li').each(function () {
                jQuery(this).removeClass('current');
            });

            jQuery(this).parent().addClass('current');	// 设置为当前菜单

            var url = jQuery(this).attr('href');
            if (jQuery(url).length > 0) {
                jQuery('.contentwrapper .subcontent').hide();
                jQuery(url).show();
            } else {
                jQuery.ajax({
                    url: url,
                    success: function (data) {
                        jQuery('#contentwrapper').html(data);
                        jQuery('.stdtable input:checkbox').uniform();	// 重新设定复选框
                    }
                });
            }
            return false;
        });
    });
    // 表格对象
    var table;
    layui.use('table', function () {
        table = layui.table;
        // 文章列表
        table.render({
            elem: '#commentGrid'     // 表格元素id
            , url: '/admin/comment/listComments.action' //数据接口
            , page: true //开启分页
            , style: [{"background-color": "red"}]
            , size: ''
            , limit: 10
            , skin: ''
//                , height: 'full-235'
            , limits: [10, 20, 40]
            , cols: [[    // 表头
//                    {type: 'checkbox'},
                {type: 'numbers', title: '序号'}
                , {field: 'content', title: '评论内容', width: 270}
                , {
                    field: 'author', title: '评论者', width: 90, align: 'left',
                    templet: function (d) {
                        if (!d.author) {
                            return "-";
                        } else {
                            return d.author;
                        }
                    }
                }
                , {
                    field: 'created', title: '评论时间', width: 160, align: 'left',
                    templet: function (d) {
                        return cgszlUtils.translateTimstampTo(d.created * 1000, 'yyyy-MM-dd hh:mm:ss');
                    }
                }
                , {
                    field: 'mail', title: '评论人邮箱', width: 120, align: 'left',
                    templet: function (d) {
                        if (!d.mail) {
                            return "-";
                        } else {
                            return d.mail;
                        }
                    }
                }
                , {
                    field: 'url', title: '评论人网址', width: 150, align: 'left',
                    templet: function (d) {
                        if (!d.url) {
                            return "-";
                        } else {
                            return d.url;
                        }
                    }
                }
                , {
                    field: 'status', title: '状态', width: 75, align: 'center',
                    templet: function (d) {
                        if (d.status === 'approved') {
                            return "<font color='green'>已通过</font>";
                        } else if (d.status === 'not_audit') {
                            return "<font color='#ff4500'>待审核</font>";
                        } else {
                            return "<font color='red'>未通过</font>";
                        }
                    }
                }
                , {
                    fixed: 'right', width: 220, align: 'center', toolbar: '#toolbar', title: "操作",
                    templet: function (d) {
                        if (d.status === "approved") {
                            debugger;
                        }
                    }

                }
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
            }
        });

        var $ = layui.$, active = {

            ////// 获取选中数据 //////
            getCheckData: function () { //获取选中数据
                var checkStatus = table.checkStatus('commentGrid')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ////// 选中数量 //////
            , getCheckLength: function () { //获取选中数目
                var checkStatus = table.checkStatus('commentGrid')
                    , data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            }
            ////// 是否全选 //////
            , isAll: function () { //验证是否全选
                var checkStatus = table.checkStatus('commentGrid');
                layer.msg(checkStatus.isAll ? '全选' : '未全选')
            }
        };

        // 监听工具条
        table.on('tool(commentGrid)', function (obj) {
            var data = obj.data;
            ////// 操作 //////
            ////// 查看详情 //////
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.aid + ' 的查看操作');

                ////// 删除 //////
            } else if (obj.event === 'del') {
                layer.confirm('确认删除该评论?', {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/comment/deleteById.action"
                        , type: "POST"
                        , data: {coid: data.coid}
                        , dataType: "JSON"
                        , async: true
                        , success: function (resule) {
                            if (resule && resule.success) {
                                obj.del();
                                top.layer.msg('删除成功', {icon: 1, title: "系统提示"});
                            } else {
                                top.layer.msg(resule.message, {icon: 2, title: "系统提示"});
                            }
                        }
                    });
                    // 关闭确认框
                    layer.close(index);
                });
                ////// 通过 //////
            } else if (obj.event === 'pass' || obj.event === 'unpass') {
                var param;
                var msg;
                if (obj.event === 'pass') {
                    if (data.status === "approved") {
                        top.layer.msg("该评论已通过，无法再次操作", {icon: 2});
                        return;
                    }
                    msg = "确认通过该评论？";
                    param = {coid: data.coid, status: 'approved'}
                } else if (obj.event === 'unpass') {
                    if (data.status === "unpass") {
                        top.layer.msg("该评论已不通过，无法再次操作", {icon: 2});
                        return;
                    }
                    msg = "不通过该评论？";
                    param = {coid: data.coid, status: 'unpass'}
                }
                layer.confirm(msg, {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/comment/updateCommentStatus.action"
                        , type: "POST"
                        , data: param
                        , dataType: "JSON"
                        , async: true
                        , success: function (resule) {
                            if (resule && resule.success) {
                                table.reload('commentGrid');
                                top.layer.msg('操作成功', {icon: 1});
                            } else {
                                top.layer.msg(resule.message, {icon: 2});
                            }
                        }
                    });
                    // 关闭确认框
                    layer.close(index);
                });
            }
        });
    })
</script>
</body>
<%--<body class="withvernav">
    &lt;%&ndash;<div class="centercontent tables">&ndash;%&gt;
    <table id="allpoststable" cellpadding="0" cellspacing="0" border="0" class="stdtable">
        <colgroup>
            &lt;%&ndash;<col class="con0" style="width: 4%"/>&ndash;%&gt;
            <col class="con1"/>
            <col class="con0"/>
            <col class="con1"/>
            <col class="con0"/>
        </colgroup>
        <thead>
            <tr>
                &lt;%&ndash;<th class="head0 nosort"><input type="checkbox"  class="checkall"/></th>&ndash;%&gt;
                <th class="head0">文章标题</th>
                <th class="head1">作者</th>
                <th class="head0">状态</th>
                <th class="head0">发布时间</th>
                <th class="head1">点击量</th>
                <th class="head0">所属分类</th>
            </tr>
        </thead>
        &lt;%&ndash;<tfoot>
            <tr>
                <th class="head0">
                    <span class="center"><input type="checkbox"/></span>
                </th>
                <th class="head0">Rendering engine</th>
                <th class="head1">Browser</th>
                <th class="head0">Platform(s)</th>
                <th class="head1">Engine version</th>
                <th class="head0">CSS grade</th>
            </tr>
        </tfoot>&ndash;%&gt;
        &lt;%&ndash;<tbody>
            <c:forEach items="${articleList}" var="article">
                <tr class="gradeX">
                    <td align="center">
                        <span class="center">
                            <input type="checkbox"/>
                        </span>
                    </td>
                    <td>${article.title}</td>
                    <td>${article.authorId}</td>
                    <td class="center">${article.status}</td>
                    <td class="center">${article.created}</td>
                    <td class="center">${article.hits}</td>
                    <td class="center">${article.categories}</td>
                </tr>
            </c:forEach>
        </tbody>&ndash;%&gt;
    </table>

</div><!--contentwrapper-->

</body>--%>
</html>
