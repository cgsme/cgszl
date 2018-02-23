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

<body>

    <%--<div class="layui-btn-group articleTable" style="padding-top: 10px">--%>
        <%--<button class="layui-btn layui-btn-primary" data-type="createNewArticle">111创建新文章</button>--%>
        <%--<button class="layui-btn layui-btn-primary" data-type="getCheckData">获取选中行数据</button>--%>
        <%--<button class="layui-btn layui-btn-primary" data-type="getCheckLength">获取选中数目</button>--%>
        <%--<button class="layui-btn layui-btn-primary" data-type="isAll">验证是否全选</button>--%>
    <%--</div>--%>
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
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="publish">
            <i class="layui-icon">&#xe609;</i>发布
        </a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>编辑
        </a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
            <i class="layui-icon">&#xe640;</i>删除
        </a>
    </script>

    <script>
        // 表格对象
        var table;
        layui.use('table', function () {
            table = layui.table;
            // 文章列表
            table.render({
                elem: '#articleGrid'     // 表格元素id
                , url: '/admin/blog/getAllDraftList.action' //数据接口
                , page: true //开启分页
                , style: [{"background-color": "red"}]
                , size: ''
                , limit: 10
//                , skin: 'nob'
//                , height: 'full-240'
                , limits:[10,20,40]
                , cols: [[    // 表头
                    {type: 'checkbox'}
                    , {field: 'title', title: '文章标题', width: 370}
                    , {field: 'user', title: '作者', width: 100, align: 'center',
                        templet: function (d) {
                            return d.user.username;
                        }
                    }
                    , {field: 'status', title: '状态', width: 80, align: 'center',
                        templet: function (d) {
                            if (d.status === 'publish') {
                                return "<font color='green'>已发布</font>";
                            } else {
                                return "<font color='#ff4500'>未发布</font>";
                            }
                        }
                    }
                    , {field: 'created', title: '发布时间', width: 180, align: 'center',
                        templet: function (d) {
                            return cgszlUtils.translateTimstampTo(d.created * 1000, 'yyyy-MM-dd hh:mm:ss');
                        }
                    }
//                    , {field: 'hits', title: '访问量', width: 100, sort: true, align: 'right',}
                    , {field: 'categories', title: '所属分类', width: 90, align: 'center',}
                    , {fixed: 'right', width: 220, align: 'center', toolbar: '#toolbar', title: "操作"}
                ]],
                done: function (res, curr, count) {
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    // 展示当前已发布文章总数
                    jQuery("#totalDraft").html(count);

                    console.log(res);
                    //得到当前页码
                    console.log(curr);
                    //得到数据总量
                    console.log(count);
                }
            });

            var $ = layui.$, active = {
                ////// 创建新文章 //////
                createNewArticle: function () {
                    var body = null;
                    // 多窗口模式，层叠置顶
                    var articleWinIndex = layer.open({
                        type: 2   // 此处是iframe
                        , title: '创建新文章'
                        , area: ['1120px', '620px']
                        , shade: 0.3
                        , maxmin: true
                        , content: '/admin/newpost.html'     // 需要加载的页面地址
                        , loading: true          // 显示正在加载...
                        , end: function () {   // 窗口销毁时触发,无参数
                            // 重新加载列表数据
                            table.reload('articleGrid');
                        }
//                      , btn: ['发布', '存草稿', '取消']
                        /*,success: function(layero, index){
                            body = layer.getChildFrame('body', index);
                            layer.setTop(layero);
                        }
                        ,yes: function() {  // 点击第一个按钮,即发布按钮触发
                            var data = body.find("#articleForm").serialize();
                            debugger;
                            form.submit();
                            var url = "";
                            if (actionType === "publish") {
                                url = "/admin/savePost.html?actionType=publish"
                            }
                            if (actionType === "draft") {
                                url = "/admin/savePost.html?actionType=deaft"
                            }
                            jQuery.post({
                                url : url,
                                data: data,
                                success: function (result) {
                                    if (result.success) {
                                        layer.closeAll(articleWinIndex);
                                        layer.alert("保存成功");
                                    } else {
                                        layer.alert(data.message);
                                    }
                                }
                            });
                        }
                        ,btn2: function() {   // 点击第二个按钮触发
                            alert('存草稿')
                        }
                        ,btn3: function() {   // 点击第三个按钮的触发
                            layer.closeAll();
                        }*/
                        ,zIndex: layer.zIndex // 重点1
                    });
                }
                ////// 获取选中数据 //////
                , getCheckData: function(){ //获取选中数据
                    var checkStatus = table.checkStatus('articleGrid')
                        ,data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                }
                ////// 选中数量 //////
                , getCheckLength: function(){ //获取选中数目
                    var checkStatus = table.checkStatus('articleGrid')
                        ,data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                }
                ////// 是否全选 //////
                , isAll: function(){ //验证是否全选
                    var checkStatus = table.checkStatus('articleGrid');
                    layer.msg(checkStatus.isAll ? '全选': '未全选')
                }
            };

            // 监听表格复选框选择
            $('.articleTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });

            //监听工具条
            table.on('tool(articleGrid)', function (obj) {
                var data = obj.data;
                ////// 操作 //////
                ////// 查看详情 //////
                if (obj.event === 'publish') {
//                    layer.msg('ID：' + data.aid + ' 的发布操作');
                    layer.confirm('确认发布该文章?', {icon: 3, title:'温馨提示'}, function (index) {
                        // 移除列表中选中的数据
                        jQuery.ajax({
                            url: "/admin/blog/publishByAid.action"
                            , type: "POST"
                            , data: {aid: data.aid}
                            , dataType: "JSON"
                            , async: true
                            , success: function (resule) {
                                if (resule && resule.success) {
                                    obj.del();
                                    top.layer.msg('发布成功', {icon: 1, title: "系统提示"});
                                } else {
                                    top.layer.msg(resule.message, {icon: 2, title: "系统提示"});
                                }
                            }
                        });
                        // 关闭确认框
                        layer.close(index);
                    });
                ////// 删除文章 //////
                } else if (obj.event === 'del') {
                    layer.confirm('删除后可在回收站找回. 确认删除该文章?', {icon: 3, title:'温馨提示'}, function (index) {
                        // 移除列表中选中的数据
                        jQuery.ajax({
                            url:"/admin/blog/deleteByAid.action"
                            , type : "POST"
                            , data : {aid: data.aid}
                            , dataType : "JSON"
                            , async : true
                            , success : function (resule) {
                                if (resule && resule.success) {
                                    obj.del();
                                    top.layer.msg('删除成功', {icon: 1, title: "系统提示" });
                                } else {
                                    top.layer.msg(resule.message, {icon: 2, title: "系统提示" });
                                }
                            }
                        });
                        // 关闭确认框
                        layer.close(index);
                    });
                ////// 编辑文章 //////
                } else if (obj.event === 'edit') {
//                    layer.alert('编辑行：<br>' + JSON.stringify(data))
                    layer.open({
                        type: 2   // 此处是iframe
                        , title: '编辑文章'
                        , area: ['1120px', '620px']
                        , shade: 0.3
                        , maxmin: true
                        , content: '/admin/newpost.html?aid=' + data.aid + "&actionType=edit"    // 需要加载的页面地址
                        , loading: true          // 显示正在加载...
                        , end: function (e) {   // 窗口销毁时触发,无参数
                            // 重新加载列表数据
//                            table.reload('articleGrid');
//                            obj.update();
                        }
                    });
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
