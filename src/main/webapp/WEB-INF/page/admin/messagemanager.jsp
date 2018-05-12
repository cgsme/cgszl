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
    <title>留言管理</title>
</head>
<style>
    .layui-table-view {
        margin: 0px 0
    }
</style>
<body>
<blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">
    <div class="layui-btn-group messageTable">
        <button class="layui-btn layui-btn-sm" data-type="alreadyRead">
            <i class="layui-icon">&#xe605;</i>已读
        </button>
        <button class="layui-btn layui-btn-danger layui-btn-sm" data-type="batchDelete">
            <i class="layui-icon layui-icon-delete"></i>批量删除
        </button>
    </div>
</blockquote>

<table id="messageGrid" lay-filter="messageGrid">
</table>

<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862"
     data-ad-slot="3820120620"></ins>

<script type="text/html" id="toolbar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="alreadyRead">已读</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    // 表格对象
    var table;
    layui.use('table', function () {
        table = layui.table;
        // 文章列表
        table.render({
            elem: '#messageGrid'     // 表格元素id
            , url: '/admin/message/listMessages.action' //数据接口
            , page: true //开启分页
            , style: [{"background-color": "red"}]
            , size: ''
            , limit: 10
            , skin: ''
//                , height: 'full-235'
            , limits: [10, 20, 40]
            , cols: [[    // 表头
                {type: 'checkbox'},
                {type: 'numbers', title: '序号'}
                , {field: 'content', title: '留言内容', width: 270}
                , {
                    field: 'author', title: '留言者', width: 90, align: 'left',
                    templet: function (d) {
                        if (!d.author) {
                            return "-";
                        } else {
                            return d.author;
                        }
                    }
                }
                , {
                    field: 'mail', title: '留言邮箱', width: 120, align: 'left',
                    templet: function (d) {
                        if (!d.mail) {
                            return "-";
                        } else {
                            return d.mail;
                        }
                    }
                }
                , {
                    field: 'url', title: '留言者主页', width: 150, align: 'left',
                    templet: function (d) {
                        if (!d.url) {
                            return "-";
                        } else {
                            return d.url;
                        }
                    }
                }
                , {
                    field: 'created', title: '留言时间', width: 160, align: 'left',
                    templet: function (d) {
                        return cgszlUtils.translateTimstampTo(d.created * 1000, 'yyyy-MM-dd hh:mm:ss');
                    }
                }
                , {
                    field: 'status', title: '状态', width: 75, align: 'center',
                    templet: function (d) {
                        if (d.status === 'approved') {
                            return "<font color='green'>已读</font>";
                        } else if (d.status === 'not_audit') {
                            return "<font color='#ff4500'>未读</font>";
                        }
                    }
                }
                , {
                    fixed: 'right', width: 160, align: 'center', toolbar: '#toolbar', title: "操作",
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
            ////// 已读 //////
            alreadyRead: function () {
                var checkStatus = table.checkStatus('messageGrid');
                if (checkStatus.data.length < 1) {
                    layer.msg("请至少选择一条记录", {icon: 2});
                    return;
                }
                $.ajax({
                    url: "/admin/message/batchRead.action",
                    type: "POST",
                    data: JSON.stringify(checkStatus.data),
                    contentType: 'application/json;charset=utf-8', // 指定请求的数据格式为json,这样后台才能用@RequestBody接受java bean
                    success: function (result) {
                        if (result && result.success) {
                            layer.msg("操作成功", {icon: 1});
                            // 刷新列表
                            table.reload("messageGrid");
                        } else {
                            layer.msg(result.message, {icon: 2});
                        }
                    }
                });
            },
            ////// 批量删除 //////
            batchDelete: function () {
                var checkStatus = table.checkStatus('messageGrid');
                var checkIds = new Array();
                for (var i = 0; i < checkStatus.data.length; i++) {
                    checkIds.push(checkStatus.data[i].coid);
                }
                if (checkIds.length < 1) {
                    layer.msg("请至少选择一条记录", {icon: 2});
                    return;
                }
                $.ajax({
                    url: "/admin/message/batchDelete.action",
                    type: "POST",
                    data: {'checkIds': checkIds},
                    success: function (result) {
                        if (result && result.success) {
                            layer.msg("操作成功", {icon: 1});
                            table.reload("messageGrid");
                        } else {
                            layer.msg(resule.message, {icon: 2});
                        }
                    }
                });
            }
        };

        // 绑定点击事件
        $('.messageTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        // 监听工具条
        table.on('tool(messageGrid)', function (obj) {
            var data = obj.data;
            ////// 操作 //////
            ////// 查看详情 //////
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.aid + ' 的查看操作');
                ////// 删除 //////
            } else if (obj.event === 'del') {
                layer.confirm('确认删除该留言?', {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/message/deleteById.action"
                        , type: "POST"
                        , data: {coid: data.coid}
                        , dataType: "JSON"
                        , async: true
                        , success: function (result) {
                            if (result && result.success) {
                                top.layer.msg('删除成功', {icon: 1, title: "系统提示"});
                                obj.del();
                            } else {
                                top.layer.msg(result.message, {icon: 2, title: "系统提示"});
                            }
                        }
                    });
                    // 关闭确认框
                    layer.close(index);
                });
                ////// 已读 //////
            } else if (obj.event === 'alreadyRead') {
                layer.confirm('是否标记为已读?', {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/message/alreadyRead.action"
                        , type: "POST"
                        , data: JSON.stringify(data)
                        , contentType: 'application/json;charset=utf-8'
//                        , dataType: "JSON"
                        , success: function (result) {
                            if (result && result.success) {
                                top.layer.msg('操作成功', {icon: 1, title: "系统提示"});
                                table.reload('messageGrid');
                            } else {
                                top.layer.msg(result.message, {icon: 2, title: "系统提示"});
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
</html>
