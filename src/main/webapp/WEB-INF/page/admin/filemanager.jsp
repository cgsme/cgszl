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
        将文件拖至此处或点击上传.
    </div>
</div>
<c:choose>
    <c:when test="${attachs == null or attachs.size() eq 0}">
        <div class="layui-row div-center">
            <div class="layui-col-md12">
                ~ ~ 空空如也 ~ ~
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="layui-row">
            <c:forEach items="${attachs}" var="attach">
                <div id="${attach.id}" class="layui-col-md2 div-center">
                    <div class="layui-row grid-demo">
                        <div class="layui-col-md12 div-center">
                            <a href="${attach.fkey}" target="_blank">
                                <img class="attach-img"
                                     src="${attach.ftype eq 'image' ? attach.fkey : '/upload/attach.png'}"
                                     title="${attach.fname}"/>
                            </a>
                        </div>
                        <div class="layui-col-md12">
                                ${attach.fname}
                        </div>
                        <div class="layui-col-md12">
                            <div class="layui-btn-group">
                                <button onclick="deleteAttachById(${attach.id})" class="layui-btn-radius layui-btn-primary layui-btn-sm">
                                    <i class="layui-icon">&#xe640;</i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div id="demo2-1"></div>
        <ul id="biuuu_city_list"></ul>
        </c:otherwise>
</c:choose>
</body>
<script type="text/javascript">

    <%--var data = ${attachs};--%>

    jQuery("div.dropzone").dropzone({
        url: "/admin/fileUpload.html",  // 上传文件的地址，
        maxFiles: 5,                    // 最多上传几个文件
        maxFilesize: 100,               // 文件的大小，单位是M
        addRemoveLinks: true,           // 是否有删除文件的功能
        dictRemoveFile: "删除",          // 删除文件的文字
//        previewsContainer:".dz-preview",
        acceptedFiles: ".jpg,.jpeg,.png,.gif,.zip",         // 支持的文件格式
        paramName: 'files',                                 // 上传的文件名称，即服务端可以通过此来获取上传的文件，如$_FILES['dropimage']
        init: function (file) {                             // 初始化的事件
            this.on("success", function (file) {
                console.log("File " + file.name + "uploaded");
            });
            this.on("removedfile", function (file) {
                console.log("File " + file.name + "removed");
            });
        }
    });

    /**
     * 根据附件标识删除附件
     * @param attachId
     */
    function deleteAttachById(attachId) {
        layer.confirm('确认删除该文件?', {icon: 3, title:'温馨提示'}, function (index) {
            // 移除列表中选中的数据
            jQuery.ajax({
                url:"/admin/attach/deleteById.action"
                , type : "POST"
                , data : {id : attachId}
                , dataType : "JSON"
                , async : false
                , success : function (result) {
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

//    layui.use(['laypage', 'layer'], function(){
//        var laypage = layui.laypage
//            ,layer = layui.layer;
//
//        laypage.render({
//            elem: 'demo2-1'
//            ,count: 100
//            ,theme: '#b0b0b0'
//        });
//
//        //调用分页
//        laypage.render({
//            elem: 'demo2-1'
//            ,count: data.length
//            ,jump: function(obj){
//                //模拟渲染
//                document.getElementById('biuuu_city_list').innerHTML = function(){
//                    var arr = []
//                        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
//                    layui.each(thisData, function(index, item){
//                        arr.push('<li>'+ item +'</li>');
//                    });
//                    return arr.join('');
//                }();
//            }
//        });
//
//    });
</script>
</html>

