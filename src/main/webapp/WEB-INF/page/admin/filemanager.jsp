<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>博客管理页面</title>
    <script src="/common/js/dropzone.js"></script>
    <link rel="stylesheet" href="/css/dropzone.css"/>
    <%--<link href="//cdn.bootcss.com/dropzone/4.3.0/min/dropzone.min.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="/css/basic.css"/>
</head>
<style>
    #dropzone {
        margin-bottom: 3rem;
        position: relative;
    }

    .dropzone {
        border: 2px dashed #8D8D8D;
        border-radius: 5px;
        background: white;
        margin: 10px;
    }

    .dropzone .dz-message {
        font-weight: 400;
        font-size: 30px;
        color: #8D8D8D;
    }

    .dropzone .dz-message .note {
        font-size: 0.8em;
        font-weight: 200;
        display: block;
        margin-top: 1.4rem;
    }

    .attach-img {
        width: 100px;
        height: 100px;
        border-radius: 10px;
        box-shadow: 0px 0px 8px #333;
    }

    .attach-text {
        font-size: 12px;
        font-weight: 300;
    }

    .attach-img:hover {
        background-color: #f9f9f9;
    }

    .attach-img {
        width: 100px;
        height: 100px;
        border-radius: 10px;
        box-shadow: 0px 0px 8px #333;
    }

    .attach-text {
        font-size: 12px;
        font-weight: 300;
    }

    .attach-img:hover {
        background-color: #f9f9f9;
    }

    .div-center {
        text-align: center;
        padding: 10px;
    }
</style>
<body>
    <blockquote class="layui-elem-quote layui-quote-nm" style="font-style: inherit;">文件管理</blockquote>
    <div class="dropzone">
        <div class="dz-message">
            将照片拖至此处或点击上传.
        </div>
    </div>
    <div id="tipBox" class="layui-row div-center" style="display: none">
        <div class="layui-col-md12">
            ~ ~ 空空如也 ~ ~
        </div>
    </div>
    <div id="dataBox">
        <div class="layui-row site-demo-flow" id="LAY_demo3">
        </div>
        <div id="loadMore" class="userlistwidget">
            <a class="more" href="javascript:void(0)">加载更多……</a>
        </div>
    </div>
</body>
<script type="text/javascript">

    // 初始化dropzone上传区域
    jQuery("div.dropzone").dropzone({
        url: "/admin/fileUpload.action",  // 上传文件的地址，
        maxFiles: 5,                    // 最多上传几个文件
        maxFilesize: 100,               // 文件的大小，单位是M
        addRemoveLinks: true,           // 是否有删除文件的功能
        dictRemoveFile: "删除",          // 删除文件的文字
//        previewsContainer:".dz-preview",
        dictInvalidFileType: "不支持的文件类型",
        dictFileTooBig: "文件大小{{filesize}}M，超过最大限制{{maxFilesize}}M",
        acceptedFiles: ".jpg,.jpeg,.png,.gif,",         // 支持的文件格式
        paramName: 'files',                                 // 上传的文件名称，即服务端可以通过此来获取上传的文件，如$_FILES['dropimage']
        init: function (file) {                             // 初始化的事件

            // 文件已经成功上传，获得服务器返回信息作为第二个参数(这个时间又被称作finished)
            this.on("success", function (file, result) {
                // 移除框中文件
                this.removeFile(file);
                if (result && result.success) {
                    var attachList = result.data;
                    jQuery.each(attachList, function (index, attach) {
                        var imgPath;
                        if (attach.ftype === 'image') {
                            imgPath = attach.fkey;
                        } else {
                            imgPath = "/upload/attach.png";
                        }
                        var $img = "<div id='" + attach.id + "' class='layui-col-md2 div-center'>" +
                            "<div class='layui-row grid-demo'>" +
                            "<div class='layui-col-md12 div-center'>" +
                            "<a href='javascript:void(0)'>" +
                            "<img class='attach-img' onclick='viewPhoto();' src='" + imgPath + "'" +
                            "title='" + attach.fname + "'/>" +
                            "</a>" +
                            "</div>" +
                            "<div class='layui-col-md12 layui-elip' title='" + attach.fname + "'>" +
                            attach.fname +
                            "</div>" +
                            "<div class='layui-col-md12'>" +
                            "<%--删除按钮--%>" +
                            "<div class='layui-btn-group'>" +
                            "<a href='"+ attach.fkey +"' download='"+ attach.fname +"'>" +
                            "<button class='layui-btn layui-btn-primary layui-btn-sm'>" +
                            "<i class='layui-icon'>&#xe601;</i>" +
                            "</button>" +
                            "</a>" +
                            "<button onclick='deleteAttachById(" + attach.id + ")'" +
                            "class='layui-btn layui-btn-primary layui-btn-sm'>" +
                            "<i class='layui-icon'>&#xe640;</i>" +
                            "</button>" +
                            "</div>" +
                            "</div>" +
                            "</div>" +
                            "</div>";
                        jQuery("#LAY_demo3").prepend($img);
                        layer.msg(result.message, {icon: 1});
                    });
                } else {
                    layer.msg(result.message, {icon: 2});
                }
                var queueFiles = this.getQueuedFiles();
                console.log(queueFiles.length);
                console.log("File " + file.name + "uploaded");
            });

            // 当上传队列中的所有文件上传完成时调用.
            this.on("queuecomplete", function (file) {
//                setTimeout(function () {
//                    // 触发菜单点击事件，刷新页面
//                    jQuery("a[href=filemanager\\.html]").trigger("click");
//                }, 3000);
            });

            // 移除文件时
            this.on("removedfile", function (file) {
                console.log("File " + file.name + "removed");
            });

            // 出错时. 接受 errorMessage 作为第二个参数，并且如果错误是 XMLHttpRequest对象， 那就作为第三个参数.
            this.on("error", function (file, errorMessage) {
                layer.msg(errorMessage, {icon: 2});
            });

            // 由于文件数量达到 maxFiles 限制数量被拒绝时调用.
            this.on("maxfilesexceeded", function (file) {
                layer.msg(errorMessage);
            });
        }
    });

    // 图片懒加载
    layui.use('flow', function () {
        var flow = layui.flow;
        // 按屏加载图片
        flow.lazyimg({
            elem: '#LAY_demo3 img'
        });
    });

    layui.use(['util', 'laydate', 'layer'], function () {
        var util = layui.util
            , layer = layui.layer;
        // 固定块
        util.fixbar({
//            bar1: true
//            ,bar2: true
            css: {right: 10, bottom: 20}
            , bgcolor: '#a7a6a8'
            , click: function (type) {
                if (type === 'bar1') {
                    layer.msg('敬请期待')
                } else if (type === 'bar2') {
                    layer.msg('敬请期待')
                }
            }
        });
    });

    /**
     * 根据附件标识删除附件
     * @param attachId
     */
    function deleteAttachById(attachId) {
        layer.confirm('确认删除该文件?', {icon: 3, title: '温馨提示'}, function (index) {
            // 移除列表中选中的数据
            jQuery.ajax({
                url: "/admin/attach/deleteById.action"
                , type: "POST"
                , data: {id: attachId}
                , dataType: "JSON"
                , async: false
                , success: function (result) {
                    if (result && result.success) {
                        // 移除页面上的元素
                        jQuery("#" + attachId).remove();
                        top.layer.msg('删除成功', {icon: 1});
                    } else {
                        top.layer.msg(result.message, {icon: 2});
                    }
                }
            });
            // 关闭确认框
            layer.close(index);
        });
    }

    /*  页码 */
    var PAGE = 1;
    /* 每页记录数 */
    var LIMIT = 18;

    /**
     * 浏览照片
     */
    function viewPhoto() {
        layer.photos({
//            area: ['900px', '550px'],
            photos: '#LAY_demo3'
            ,anim: 5 // 0-6的选择，指定弹出图片动画类型，默认随机（3.0之前的版本用shift参数）
        });
    }

    /**
     * 加载更多
     *
     * @param page  页码
     * @param limit 每页记录数
     */
    function loadMore(page, limit) {
        console.log("page:" + page + ", limit:" + limit);
        /* 加载数据 */
        loadData(page, limit);
    }

    /**
     * 加载数据
     *
     * @param PAGE
     * @param LIMIT
     */
    function loadData(PAGE, LIMIT) {
        jQuery.post(
            "/admin/attach/listAttachs.action",
            {page:PAGE, limit:LIMIT},
            function (result) {
                if (result && result.success) {
                    jQuery("#tipBox").css("display", "none");
                    jQuery("#dataBox").css("display", "block");
                    var attachList = result.data;
                    jQuery.each(attachList, function (index, attach) {
                        var imgPath;
                        if (attach.ftype === 'image') {
                            imgPath = attach.fkey;
                        } else {
                            imgPath = "/upload/attach.png";
                        }
                        var $img = "<div id='" + attach.id + "' class='layui-col-md2 div-center'>" +
                            "<div class='layui-row grid-demo'>" +
                            "<div class='layui-col-md12 div-center'>" +
                            "<a href='javascript:void(0)'>" +
                            "<img class='attach-img' onclick='viewPhoto();'" +
                            "src='" + imgPath + "'" +
                            "title='" + attach.fname + "'/>" +
                            "</a>" +
                            "</div>" +
                            "<div class='layui-col-md12 layui-elip' title='" + attach.fname + "'>" +
                            attach.fname +
                            "</div>" +
                            "<div class='layui-col-md12'>" +
                            "<%--删除按钮--%>" +
                            "<div class='layui-btn-group'>" +
                            "<a href='"+ attach.fkey +"' download='"+ attach.fname +"'>" +
                            "<button class='layui-btn layui-btn-primary layui-btn-sm'>" +
                            "<i class='layui-icon'>&#xe601;</i>" +
                            "</button>" +
                            "</a>" +
                            "<button onclick='deleteAttachById(" + attach.id + ")'" +
                            "class='layui-btn layui-btn-primary layui-btn-sm'>" +
                            "<i class='layui-icon'>&#xe640;</i>" +
                            "</button>" +
                            "</div>" +
                            "</div>" +
                            "</div>" +
                            "</div>";
                        jQuery("#LAY_demo3").append($img);
                    });
                    if (attachList.length < LIMIT) {
                        jQuery("#loadMore").unbind("click");
                        jQuery(".more").html('没有更多了~');
                    } else {
                        PAGE ++;
                        /* 为【加载更多】绑定点击事件 */
                        jQuery("#loadMore").unbind("click").bind("click", function () {
                            // 加载更多
                            loadMore(PAGE, LIMIT);
                        });
                    }
                } else {
                    jQuery("#dataBox").css("display", "none");
                    jQuery("#tipBox").css("display", "block");
                }
            });
    }

    /* 文档加载完成后执行 */
    jQuery(function () {
        /* 加载数据 */
        loadData(PAGE, LIMIT);

    });

</script>
</html>

