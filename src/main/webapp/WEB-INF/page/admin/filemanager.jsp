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
</style>
<body>
<div class="dropzone">
    <div class="dz-message">
        将文件拖至此处或点击上传.
    </div>
</div>
<div class="row">
    <div class="col-md-4 text-center">
        <div class="dropzone-previews">
            目前还没有一个附件呢，你可以上传试试!
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    jQuery("div.dropzone").dropzone({
        url: "/admin/fileUpload.html",  // 上传文件的地址，
        maxFiles: 5,                    // 最多上传几个文件
        maxFilesize: 100,               // 文件的大小，单位是M
        addRemoveLinks: true,           // 是否有删除文件的功能
        dictRemoveFile: "删除",          // 删除文件的文字
        previewsContainer:".dropzone-previews",
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

</script>
</html>

