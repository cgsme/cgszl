var portalIndex = (function($) {

    // 页码
    var PAGE = 1;

    // 每页记录数
    var LIMIT = 6;

    return {
        // 加载数据
        loadData: function () {
            // 加载最新文章
            portalIndex.loadRecentPost(1, 5);
            // 加载热门标签
            portalIndex.loadHotTags();
            // 加载热门文章（高赞）
            portalIndex.loadHotArticles(1, 5);
            // 加载首页文章（高点击量）
            portalIndex.loadHeightHitsArticle(PAGE, LIMIT);
            // 加载热门分类
            portalIndex.loadHotCategories(1, 5);
        },

        // 加载更多
        loadMoreArticle : function () {
            portalIndex.loadHeightHitsArticle(PAGE, LIMIT);
        },

        // 加载热门分类
        loadHotCategories: function (page, limit) {
            $.post(
                "/portal/listHotCategories.action",
                {page: page, limit: limit},
                function (data) {
                    if (data && data.success) {
                        $.each(data.data, function (index, category) {
                            var $hotCategory = $("<li><a href='#'>" + category.name + "</a></li>");
                            $("#hotCategories").append($hotCategory);
                        })
                    } else {
                        $("#hotCategories").html("数据获取失败……");
                    }
                }
            )
        },

        // 加载高点击量的文章
        loadHeightHitsArticle: function (page, limit) {
            $.post(
                "/portal/listHeightHitsArticles.action",
                {page: page, limit: limit},
                function (data) {
                    if (data.data.length < LIMIT) {
                        $("#loadMoreText").html("没有更多了~")
                        // 禁用点击
                        $("#loadMoreText").removeAttr("href");
                        $("#loadMoreText").removeAttr("click");
                    }
                    $.each(data.data, function (index, article) {
                        var outerBox = ""
                            + "<div class='col-md-6 col-sm-6''>"
                            + "<article class='blog-teaser'>"
                            + "<header>"
                            + "<a title='" + article.title + "' href='/single/" + article.aid + ".html'>"
                            + "<img onmouseover='portalIndex.ooo(this, true);' onmouseleave='portalIndex.ooo(this, false);' "
                            + "src='/portal/img/4.jpg' alt='曹图图'>"
                            + "</a>"
                            + "<h3><a class='atitle' title='" + article.title + "' href='/single/" + article.aid + ".html'>" + article.title + "</a></h3>"
                            + "<span class='meta'>"
                            + "<i title='发布日期' class='fa fa-calendar'></i>" + cgszlUtils.translateTimstampTo(article.created * 1000, "yyyy-MM-dd")
                            + " <i title='作者' class='fa fa-user'></i>" + article.user.screenName
                            + " <i title='阅读量' class='fa fa-eye'></i>" + article.hits
                            + "</span>"
                            //                        + "<hr>"
                            + "</header>"
                            + "<div id='" + article.aid + "' class='body acontent'>"
                            //                                    + article.content
                            + "</div>"
                            + "<div class='clearfix'>"
                            // + " <a href='/single\\" + article.aid + ".html' class='btn btn-clean-one'>阅读更多 >></a>"
                            + "</div>"
                            + "</article>"
                            + "</div>"
                            + "";
                        $("#postRow").append(outerBox);
                    });
                    console.log(page);
                    console.log(limit);
                    PAGE ++;
                    // LIMIT += 6;
                }
            );
        },

        // 当鼠标移到到文章图片上时的动画效果
        ooo: function (img, isMouseOver) {
            if (isMouseOver) {
                $(img).css('box-shadow', '0px 0px 11px #333');
            } else {
                $(img).css("box-shadow", '0');
            }
        },

        // 加载热门文章（高赞）
        loadHotArticles: function (page, limit) {
            $.ajax({
                url: "/portal/listHotArticles.action",
                method: "POST",
                data: {page: page, limit: limit},
                success: function (data) {
                    $.each(data, function (index, hotArticle) {
                        var $hotArticle = $("<li><a href='/single/" + hotArticle.aid + ".html'>" + hotArticle.title + "</a></li>");
                        $("#hotArticles").append($hotArticle);
                    })
                }
            })

        },

        // 加载最新文章
        loadRecentPost: function (page, limit) {
            $.ajax({
                url: "/portal/listArticles.action",
                method: "POST",
                data: {page: page, limit: limit},
                success: function (data) {
                    $.each(data.data, function (index, article) {
                        var $latestArticle = "<li><a href='/single/" + article.aid + ".html'>" + article.title + "</a></li>"
                        $("#latestArticles").append($latestArticle);
                    });
                }
            });
        },

        // 加载热门标签
        loadHotTags: function () {
            $.ajax({
                url: "/portal/listHotTags.action",
                method: "POST",
                success: function (data) {
                    $.each(data, function (index, tag) {
                        var $tagItem = $("<li><a href='#'>" + tag.name + "</a></li>");
                        $("#hotTags").append($tagItem);
                    })
                }
            })
        },
    }
})(jQuery);
