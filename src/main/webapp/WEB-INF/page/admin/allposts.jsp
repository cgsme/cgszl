<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>
    <script type="text/javascript" src="<%=sSystemPath %>admin/js/custom/tables.js"></script>
    <title>所有文章页面</title>
</head>
<body>
<%--文章列表--%>
<table id="articleGrid" lay-filter="articleGrid">
    <%--<thead>
        <tr>
            <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
            <th lay-data="{field:'aid', sort: true, fixed: true}">ID</th>
            <th lay-data="{field:'title'}">标题</th>
            <th lay-data="{field:'authorId', sort: true}">作者</th>
            <th lay-data="{field:'status'}">状态</th>
            <th lay-data="{field:'created'}">发布时间</th>
            <th lay-data="{field:'hits', sort: true}">点击量</th>
            <th lay-data="{field:'categories'}">分类</th>
            <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}"></th>
        </tr>
    </thead>--%>
</table>

<script type="text/html" id="toolbar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function () {
        var table = layui.table;
        // 文章列表
        table.render({
            elem: '#articleGrid'     // 表格元素id
            , url: '/admin/getAllArticleList.action' //数据接口
            , page: true //开启分页
            , style: [{"background-color": "red"}]
            , size: 'sm'
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'title', title: '文章标题', width: 350}
                , {field: 'authorId', title: '作者', width: 80}
                , {field: 'status', title: '状态', width: 80}
                , {field: 'created', title: '发布时间', width: 120}
                , {field: 'hits', title: '访问量', width: 100, sort: true}
                , {field: 'categories', title: '所属分类', width: 90}
                , {fixed: 'right', width: 178, align: 'center', toolbar: '#toolbar', title: "操作"}
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

        //监听工具条
        table.on('tool(articleGrid)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.aid + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.alert('编辑行：<br>' + JSON.stringify(data))
            }
        });
    });
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
