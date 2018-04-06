/* 文章详情 */
var single = (function ($) {

    // 公共方法
    return {

        /**
         * 加载评论
         *
         * @param aid 文章标识
         */
        loadComment: function (aid) {
            $.ajax({
                url: "/portal/article/listArticleComment.action",
                data: {aid: aid},
                method: "POST",
                success: function (data) {
                    if (data && data.success) {
                        $("#comments").empty();
                        var comments = data.data;
                        $.each(comments, function (index, comment) {
                            var $commentItem = "<article class='comment'>" +
                                "<header class='clearfix'>" +
                                "<img src='/portal/img/favicon.ico' alt='大佬' class='avatar'>" +
                                "<div class='meta'>" +
                                "<h3><a>" + comment.author + "</a></h3>" +
                                "<span class='date'>" + cgszlUtils.translateTimstampTo(comment.created * 1000, "yyyy-MM-dd hh:mm:ss") +
                                "</span>" +
                                "<span class='separator'> - </span><a href='#create-comment' onclick='single.setCoid(" + comment.coid + ")' class='reply-link'>回复</a>" +
                                "</div>" +
                                "</header>" +
                                "<div class='body'>" + comment.content + "</div>" +
                                "</article>";
                            $("#comments").append($commentItem);
                            if (comment.commentList && comment.commentList.length > 0) {
                                $.each(comment.commentList, function (index, childrenComment) {
                                    var $commentItem = "<article class='comment reply'>" +
                                        "<header class='clearfix'>" +
                                        "<img src='/portal/img/favicon.ico' alt='大佬' class='avatar'>" +
                                        "<div class='meta'>" +
                                        "<h3><a> 回复： @" + childrenComment.author + "</a></h3>" +
                                        "<span class='date'>" + cgszlUtils.translateTimstampTo(childrenComment.created * 1000, "yyyy-MM-dd hh:mm:ss") +
                                        "</span>" +
                                        // "<span class='separator'> - </span><a href='#create-comment' class='reply-link'>回复</a>" +
                                        "</div>" +
                                        "</header>" +
                                        "<div class='body'>" + childrenComment.content + "</div>" +
                                        "</article>";
                                    $("#comments").append($commentItem);
                                });
                            }
                        });
                    }
                },
                error: function () {
                    $("#comments").append("<div class='alert alert-warning'>评论数据请求异常</div>");
                }
            });
        },

        /**
         * 设置评论标识
         *
         * @param coid
         */
        setCoid: function (coid) {
            $('#parentCommentId').val(coid);
        },

        /**
         * 初始化数据
         *
         * @param created 文章发布时间
         * @param aid     文章标识
         */
        init: function (created, aid) {
            // 渲染文章发布时间
            $("#created").html(cgszlUtils.translateTimstampTo(created * 1000, "yyyy-MM-dd"));
            // 加载评论
            single.loadComment(aid);

        },

        /**
         * / 点赞
         * @param aid 文章标识
         * @param likeNum 点赞数
         */
        like: function (aid, likeNum) {
            // 禁用点赞按钮
            $("#likeBtn").removeAttr("onclick");
            $("#likeBtn").removeAttr("href");
            $("#unlikeBtn").removeAttr("onclick");
            $("#unlikeBtn").removeAttr("href");
            $.post("/portal/likeOrUnlike.action", {aid: aid, isLike: true}, function (data) {
                if (data && data.success) {
                    $("#likeNum").html(likeNum + 1);
                }
            });
        },

        /**
         * / 不喜欢
         *
         * @param aid 文章标识
         * @param unlikeNum 不喜欢数
         */
        unlike: function (aid, unlikeNum) {
            // 禁用点赞按钮
            $("#likeBtn").removeAttr("onclick");
            $("#likeBtn").removeAttr("href");
            $("#unlikeBtn").removeAttr("onclick");
            $("#unlikeBtn").removeAttr("href");
            $.post("/portal/likeOrUnlike.action", {aid: aid, isLike: false}, function (data) {
                if (data && data.success) {
                    $("#unlikeNum").html(unlikeNum + 1);
                }
            });
        },

        /**
         * 发表评论
         */
        saveComment: function () {
            // 提示框
            var msgBox = $("#msgBox");
            // 序列化表单字段数据
            var commentForm = $("#commentForm");
            var commentJson = cgszlUtils.serializeObject(commentForm);
            $.ajax({
                url: "/portal/article/saveComment.action",
                data: commentJson,
                method: "POST",
                success: function (data) {
                    if (data && data.success) {
                        msgBox.addClass("alert alert-success");
                        msgBox.html("<button class='close' data-dismiss='alert'>&times;</button>评论成功");
                        commentForm[0].reset();
                        // 重新加载评论
                        single.loadComment(commentJson.aid);
                    } else {
                        msgBox.addClass("alert alert-warning");
                        msgBox.html("<button class='close' data-dismiss='alert'>&times;</button>" + data.message);
                    }
                },
                error: function () {
                    msgBox.addClass("alert alert-warning");
                    msgBox.html("<button class='close' data-dismiss='alert'>&times;</button>系统异常");
                },
            });
            return false;
        }
    }
})($);