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
<%--<div class="pageheader" style="padding-bottom: 10px">
        <h1 class="pagetitle">分类/标签</h1>
    </div>--%><!--pageheader-->
<div class="layui-container" style="width: 1135px;margin: 0px;padding: 0px;">
    <div class="layui-row">
        <div class="layui-col-xs6">
            <blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">分类列表</blockquote>
            <%--文章列表--%>
            <table id="categoryGrid" lay-filter="categoryGrid"></table>
            <blockquote class="layui-elem-quote layui-quote-nm" style="font-style: normal">
                <%--<div class="layui-btn-group categoryTable">
                    <button class="layui-btn layui-btn-sm" data-type="addCategory">
                        <i class="layui-icon">&#xe654;</i>新增分类
                    </button>
                </div>--%>
                <form class="layui-form layui-form-pane">
                    <input id="mid" type="hidden" name="mid">
                    <div class="layui-form-item" style="margin-bottom: 0px;">
                        <div class="layui-inline">
                            <label class="layui-form-label">分类名称</label>
                            <div class="layui-input-inline">
                                <input id="catName" type="text" name="name" style="width: 95%" lay-verify="required"
                                       placeholder="请输入分类名称……" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-input-inline" style="width: 120px;">
                                <button class="layui-btn" lay-submit="" lay-filter="addCategory">保存分类</button>
                            </div>
                        </div>
                    </div>
                </form>
            </blockquote>
        </div>

        <div class="layui-col-xs6">
            <blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">标签列表</blockquote>
            <%--文章列表--%>
            <table id="tagsGrid" lay-filter="tagsGrid"></table>
        </div>
    </div>
</div>
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862"
     data-ad-slot="3820120620"></ins>

<script type="text/html" id="toolbar1">
    <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">
        <i class="layui-icon">&#xe642;</i>编辑
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
        <i class="layui-icon">&#xe640;</i>删除
    </a>
</script>

<script type="text/html" id="toolbar2">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
        <i class="layui-icon">&#xe640;</i>删除
    </a>
</script>
<script>
    // 表格对象
    var table;
    layui.use('table', function () {
        table = layui.table;
        // 分类列表
        table.render({
            elem: '#categoryGrid'     // 表格元素id
            , url: '/admin/getAllCategoryList.action' //数据接口
            , page: true //开启分页
//                , size: 'sm'
            , limit: 5
            , skin: ''
//                , width: 560
            , height: '400'
            , limits: [5, 10, 20]
            , cols: [[    // 表头
//                    {type: 'checkbox'}
                {type: 'numbers', title: '序号'}
                , {field: 'name', title: '分类名称', width: 200}
                , {field: 'count', title: '文章数量', width: 100}
                , {fixed: 'right', width: 178, align: 'center', title: "操作", toolbar: '#toolbar1',}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                // 展示当前已发布文章总数
                jQuery("#totalPub").html(count);
                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
            }
        });

        // 标签列表
        table.render({
            elem: '#tagsGrid'     // 表格元素id
            , url: '/admin/getAllTagsList.action' //数据接口
            , page: true //开启分页
            , size: ''
            , limit: 5
            , skin: ''
//                , width: 460
            , height: '400'
            , limits: [5, 10, 20]
            , cols: [[    // 表头
//                    {type: 'checkbox'}
                {type: 'numbers', title: '序号'}
                , {field: 'name', title: '标签名称', width: 200}
                , {field: 'count', title: '文章数量', width: 100}
                , {fixed: 'right', width: 100, align: 'center', toolbar: '#toolbar2', title: "操作"}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                // 展示当前已发布文章总数
                jQuery("#totalPub").html(count);

                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
            }
        });

        // 监听分类列表工具条
        table.on('tool(categoryGrid)', function (obj) {
            var data = obj.data;
            ////// 操作 //////
            ////// 查看详情 //////
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.aid + ' 的查看操作');

                ////// 删除分类 //////
            } else if (obj.event === 'del') {
                layer.confirm('删除分类将重置对应文章的分类类型，确认删除该分类?', {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/category/deleteByMid.action"
                        , type: "POST"
                        , data: {mid: data.mid}
                        , dataType: "JSON"
                        , async: true
                        , success: function (resule) {
                            if (resule && resule.success) {
                                obj.del();
                                top.layer.msg('删除成功', {icon: 1});
                            } else {
                                top.layer.msg(resule.message, {icon: 2});
                            }
                        }
                    });
                    // 关闭确认框
                    layer.close(index);
                });
                ////// 编辑分类 //////
            } else if (obj.event === 'edit') {
//                    layer.alert('编辑行：<br>' + JSON.stringify(data))
                // 设置隐藏域的值
                jQuery('#mid').val(data.mid);
                // 回显分类名
                jQuery('#catName').val(data.name);
                // 旧数据
                oldName = data.name;
            }
        });

        table.on('tool(tagsGrid)', function (obj) {
            var data = obj.data;
            ////// 操作 //////
            ////// 查看详情 //////
            if (obj.event === 'del') {
                layer.confirm('确认删除该标签?', {icon: 3, title: '温馨提示'}, function (index) {
                    // 移除列表中选中的数据
                    jQuery.ajax({
                        url: "/admin/category/deleteByMid.action"
                        , type: "POST"
                        , data: {mid: data.mid}
                        , dataType: "JSON"
                        , async: true
                        , success: function (resule) {
                            if (resule && resule.success) {
                                obj.del();
                                top.layer.msg('删除成功', {icon: 1});
                            } else {
                                top.layer.msg(resule.message, {icon: 2});
                            }
                        }
                    });
                    // 关闭确认框
                    layer.close(index);
                });
            }
            /*else if (obj.event === 'edit') {
           //                    layer.alert('编辑行：<br>' + JSON.stringify(data))
                               layer.open({
                                   type: 2   // 此处是iframe
                                   , title: '编辑文章'
                                   , area: ['1120px', '620px']
                                   , shade: 0.3  // 默认0.3
                                   , maxmin: true
                                   , content: '/admin/newpost.html?aid=' + data.aid + "&actionType=edit"    // 需要加载的页面地址
                                   , loading: true          // 显示正在加载...
                                   , end: function (e) {   // 窗口销毁时触发,无参数
                                       // 重新加载列表数据
           //                            table.reload('articleGrid');
           //                            obj.update();
                                   }
                               });
                           }*/
        });
    });

    // 表单操作
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(addCategory)', function (data) {
//                console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
//                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            // 判断值是否有改变，未改变则不提交
            if (jQuery('input[name=name]').val() === oldName) {
                layer.msg('未做任何修改', {icon: 2})
                return false;
            }
            ////// 判断是否已经存在同名分类 //////
            jQuery.ajax({
                url: '/admin/checkCatName.action'
                , type: 'POST'
                , async: false
                , data: data.field
                , dataType: 'JSON'
                , success: function (result) {
                    if (result && result.success) {
                        ////// 保存新增分类 //////
                        jQuery.post('/admin/addCategory.action', data.field,
                            function (result) {
                                if (result && result.success) {
                                    table.reload('categoryGrid');
                                    jQuery('input[name=mid]').val('');
                                    jQuery('input[name=name]').val('');
                                    layer.msg('操作成功', {icon: 1});
                                } else {
                                    layer.msg(result.message, {icon: 2});
                                }
                            }
                        );
                        return false;
                    } else {
                        layer.msg(result.message, {icon: 2});
                        return false;
                    }
                }
            });
            // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            return false;
        });

        // 输入框非空校验
        form.verify({
            required: function (value) {
                if (value.length == 0) {
                    return '分类名称不能为空！';
                }
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
