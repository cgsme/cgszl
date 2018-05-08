/* 留言 */
var contcat = (function ($) {

    // 设置页面标题
    $("#pageTitle").html("联系我");

    /* 提示框 */
    var msgBox = $("#msgBox");
    /*  */
    $("#messageForm").ajaxForm({

        success: function (data) {
            if (data && data.success) {
                // 清空提示框
                msgBox.empty();
                var tip  = "<div class='alert alert-success'>" +
                    "<button class='close' data-dismiss='alert'>&times;</button>留言成功</div>"
                msgBox.append(tip);
                // 重置表单
                $("#messageForm").resetForm();
            } else {
                msgBox.empty();
                var tip  = "<div class='alert alert-warning'>" +
                    "<button class='close' data-dismiss='alert'>&times;</button>"+ data.message +"</div>"
                msgBox.append(tip);
            }
        },
        error: function () {
            msgBox.empty();
            var tip  = "<div class='alert alert-warning'>" +
                "<button class='close' data-dismiss='alert'>&times;</button>系统异常</div>"
            msgBox.append(tip);
        },
    });

    // 公共方法
    return {
    };

})($);