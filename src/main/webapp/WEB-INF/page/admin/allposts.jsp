<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>
    <script type="text/javascript" src="<%=sSystemPath %>common/js/cgszl.utils.js"></script>
    <%--<script type="text/javascript" src="<%=sSystemPath %>admin/js/custom/tables.js"></script>--%>
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

<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>

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
            , size: ''
            , limit: 20
            , height: 'full-240'
            , limits:[10,20,40]
            , cols: [[    // 表头
                {type: 'checkbox'}
                , {field: 'title', title: '文章标题', width: 350}
                , {field: 'user', title: '作者', width: 80, align: 'center',
                    templet: function (d) {
                        return d.user.username;
                    }
                }
                , {field: 'status', title: '状态', width: 80, align: 'center',
                    templet: function (d) {
                        if (d.status === 'publish') {
                            return "<font color='green'>已发布</font>";
                        }
                    }
                }
                , {field: 'created', title: '发布时间', width: 180, align: 'center',
                    templet: function (d) {
                        return cgszlUtils.translateTimstampTo(d.created * 1000, 'yyyy-MM-dd hh:mm:ss');
                    }
                }
                , {field: 'hits', title: '访问量', width: 100, sort: true, align: 'right',}
                , {field: 'categories', title: '所属分类', width: 90, align: 'center',}
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
            ////// 操作 //////
            if (obj.event === 'detail') {    ////// 查看详情 //////


                layer.msg('ID：' + data.aid + ' 的查看操作');



            } else if (obj.event === 'del') {  ////// 删除文章 //////
                layer.confirm('真的删除行么', function (index) {



                    obj.del();
                    layer.close(index);
                });




            } else if (obj.event === 'edit') {    ////// 编辑文章 //////
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
