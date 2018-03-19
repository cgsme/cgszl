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

    <fieldset class="layui-elem-field layui-field-title">
        <legend>友情链接</legend>
    </fieldset>

    <%--文章列表--%>
    <table id="linkGrid" lay-filter="linkGrid" ></table>

    <blockquote class="layui-elem-quote layui-quote-nm" style="font-style: normal">
        <form id="linkForm" class="layui-form layui-form-pane">
            <input id="mid" type="hidden" name="mid">
            <div class="layui-form-item" style="margin-bottom: 0px;">
                <div class="layui-inline">
                    <%--<label class="layui-form-label">分类名称</label>--%>
                    <div class="layui-input-inline">
                        <input id="linkName" type="text" name="name" style="width: 100%"
                               lay-verify="checkNme" placeholder="链接标题" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input id="linkAddr" type="text" name="slug" style="width: 100%"
                               lay-verify="url" placeholder="链接地址" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input id="logoAddr" type="text" name="description" style="width: 100%"
                               placeholder="LOGO地址" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input id="sort" type="text" name="sort" style="width: 100%"
                               placeholder="排序" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit="" lay-filter="addLink">保存链接</button>
                    </div>
                </div>
            </div>
        </form>
    </blockquote>

    <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>

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
                elem: '#linkGrid'     // 表格元素id
                , url: '/admin/getAllLinkList.action' //数据接口
                , page: true //开启分页
//                , size: 'sm'
                , limit: 5
                , skin: ''
                , height: '400'
                , limits:[5,10,20]
                , cols: [[    // 表头
//                    {type: 'checkbox'}
                    {type: 'numbers', title: '序号'}
                    , {field: 'name', title: '链接标题', width: 300}
                    , {field: 'slug', title: '链接地址', width: 300}
                    , {field: 'description', title: 'LOGO链接', width: 200}
                    , {field: 'sort', title: '排序', width: 80}
                    , {fixed: 'right', width: 178, align: 'center', title: "操作", toolbar: '#toolbar1', }
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
            table.on('tool(linkGrid)', function (obj) {
                var data = obj.data;
                ////// 操作 //////
                ////// 查看详情 //////
                if (obj.event === 'detail') {
                    layer.msg('ID：' + data.aid + ' 的查看操作');

                ////// 删除分类 //////
                } else if (obj.event === 'del') {
                    layer.confirm('确认删除该链接？', {icon: 3, title:'温馨提示'}, function (index) {
                        // 移除列表中选中的数据
                        jQuery.ajax({
                            url:"/admin/deleteByMid.action"
                            , type : "POST"
                            , data : {mid: data.mid}
                            , dataType : "JSON"
                            , async : true
                            , success : function (resule) {
                                if (resule && resule.success) {
                                    obj.del();
                                    top.layer.msg('删除成功', {icon: 1});
                                    table.reload('linkGrid');
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
                    jQuery('#linkName').val(data.name);
                    jQuery('#linkAddr').val(data.slug);
                    jQuery('#logoAddr').val(data.description);
                    jQuery('#sort').val(data.sort);
                    // 记录旧数据
                    oldName = data.name;
                    oldLinkAddr = data.slug;
                    oldLogoAddr = data.description;
                    oldSort = data.sort;
                }
            });

        });

        // 表单操作
        layui.use('form', function(){
            var form = layui.form;
            form.on('submit(addLink)', function(data){
//                console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
//                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                // 修改时先判断值是否有改变，未改变则不提交
                if (jQuery('input[name=mid]').val() != '') {
                    if (jQuery('input[name=name]').val() === oldName && jQuery('input[name=slug]').val() === oldLinkAddr
                        && jQuery('input[name=description]').val() === oldLogoAddr
                        && jQuery('input[name=sort]').val() == oldSort) { // 0会被转成"0"，所以只能两个等于
                        layer.msg('未做任何修改', {icon: 2})
                        return false;
                    }
                }
                ////// 判断是否已经存在同名分类 //////
                jQuery.ajax({
                   url: '/admin/checkLinkName.action'
                   , type: 'POST'
                   , async: false
                   , data: data.field
                   , dataType: 'JSON'
                   , success: function (result) {
                        if (result && result.success) {
                            ////// 保存新增分类 //////
                            jQuery.post('/admin/saveLink.action', data.field,
                                function (result) {
                                    if (result && result.success) {
                                        table.reload('linkGrid');
                                        // 重置表单
                                        jQuery("#linkForm")[0].reset();
                                        jQuery("input[name=mid]").val('');
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
                checkNme: function(value, item) {
                    if(value.length == 0) {
                        return '链接标题不能为空！';
                    }
                }
            });
        });
    </script>
</body>
</html>
