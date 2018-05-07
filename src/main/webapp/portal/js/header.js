/* 头部 */
var header = (function ($) {

    // 查询关键字
    var KEYWORD = null;
    // 页码
    var PAGE = 2;
    // 每页记录数
    var LIMIT = 6;

    return {

        /**
         * 搜索文章
         * @param keyWord 搜索内容
         */
        search: function (keyWord) {
            if (!$.trim(keyWord)) {
                return;
            }
            KEYWORD = keyWord;
            window.location.href = "/portal/search.html?keyWord=" + encodeURI(encodeURI(keyWord));
        },

        /**
         * 渲染文章列表
         * @param articleList 文章列表数据
         */
        renderArticleList: function (articleList, keyWord) {
            KEYWORD = keyWord;
            articleList = JSON.parse(articleList);
            $.each(articleList, function (index, article) {
                var outerBox = ""
                    + "<div class='col-md-4 col-sm-4'>"
                    + "<article class='blog-teaser'>"
                    + "<header>"
                    + "<a title='" + article.title + "' href='/single/" + article.aid + ".html'>"
                    + "<img src='/portal/img/4.jpg' alt='曹图图'>"
                    + "</a>"
                    + "<h4><a class='atitle' title='" + article.title + "' href='/single/" + article.aid + ".html'>" + article.title + "</a></h4>"
                    + "<span class='meta' style='font-size: 14px;'>"
                    + "<i title='发布日期' class='fa fa-calendar'></i>" + cgszlUtils.translateTimstampTo(article.created * 1000, "yyyy-MM-dd")
                    + " <i title='作者' class='fa fa-user'></i>" + article.user.screenName
                    + " <i title='阅读量' class='fa fa-eye'></i>" + article.hits
                    + "</span>"
                    + "</header>"
                    + "<div id='" + article.aid + "' class='body acontent'>"
                    + "</div>"
                    + "<div class='clearfix'>"
                    + "</div>"
                    + "</article>"
                    + "</div>";
                $("#postRow").append(outerBox);
            });
        },

        /**
         * 加载更多
         */
        loadMoreArticle: function () {
            // 发送请求
            $.ajax({
                url: "/portal/search.html",
                method: "GET",
                data: {page: PAGE, limit: LIMIT, keyWord: KEYWORD},
                success: function (data) {
                    var articleList = JSON.parse(data);
                    if (articleList.length < LIMIT) {
                        $("#loadMoreText").html("没有更多了~");
                        // 禁用点击
                        $("#loadMoreText").removeAttr("href");
                        $("#loadMoreText").removeAttr("click");
                    }
                    if (articleList.length < LIMIT) {
                        $("#loadMoreText").html("没有更多了~");
                        // 禁用点击
                        $("#loadMoreText").removeAttr("href");
                        $("#loadMoreText").removeAttr("click");
                    }
                    header.renderArticleList(data);
                    // 页码递增
                    PAGE++;
                },
                error: function (result) {

                }
            });
        }

    }
})(jQuery);