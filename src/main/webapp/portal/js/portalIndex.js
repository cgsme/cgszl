/* 前台首页 */
var portalIndex = (function ($) {

    // 页码
    var PAGE = 1;
    // 标签分页 页码
    var CONDITION_TAG_PAGE = 1;
    // 分类分页 页码
    var CONDITION_CATEGORY_PAGE = 1;

    // 每页记录数
    var LIMIT = 6;
    // 标签每页记录数
    var CONDITION_TAG_LIMIT = 6;
    // 分类每页记录数
    var CONDITION_CATEGORY_LIMIT = 6;

    // 公共方法
    return {

        /**
         * 加载初始化数据
         * @param name
         * @param type
         */
        loadData: function (name, type) {
            // 说明是获取热门分类或标签中的文章
            if (type) {
                portalIndex.loadArticleByCondition(name, type);
            } else {
                // 加载首页文章（高点击量）
                portalIndex.loadHeightHitsArticle(PAGE, LIMIT);
            }
            // 加载最新文章
            portalIndex.loadRecentPost(1, 5);
            // 加载热门标签
            portalIndex.loadHotTags();
            // 加载热门文章（高赞）
            portalIndex.loadHotArticles(1, 5);
            // 加载热门分类
            portalIndex.loadHotCategories(1, 5);
            // 加载友情链接
            portalIndex.loadLinksList();
        },

        /**
         * 加载更多
         */
        loadMoreArticle: function () {
            portalIndex.loadHeightHitsArticle(PAGE, LIMIT);
        },

        /**
         * 加载友情链接
         */
        loadLinksList: function () {
            $.ajax({
                url: "/portal/link/listLinksList.action",
                method: "POST",
                success: function (data) {
                    if (data && data.success) {
                        $.each(data.data, function (index, link) {
                            var $hotCategory = $("<li><a href='" + link.slug + "' target='_blank'>" + link.name + "</a></li>");
                            $("#linksList").append($hotCategory);
                        });
                    } else {
                        $("#linksList").html("数据获取失败……");
                    }
                },

            });
        },

        /**
         * 加载热门分类
         * @param page
         * @param limit
         */
        loadHotCategories: function (page, limit) {
            $.post(
                "/portal/listHotCategories.action",
                {page: page, limit: limit},
                function (data) {
                    if (data && data.success) {
                        $.each(data.data, function (index, category) {
                            var $hotCategory = $("<li><a href='/portal/article/category/" + category.name + ".html'>" + category.name + "</a></li>");
                            // $hotCategory.bind("click", function () {
                            //     portalIndex.loadArticleByCondition(category.name, "category");
                            // });
                            $("#hotCategories").append($hotCategory);
                        });
                    } else {
                        $("#hotCategories").html("数据获取失败……");
                    }
                }
            )
        },

        /**
         * / 加载高点击量的文章
         * @param page
         * @param limit
         */
        loadHeightHitsArticle: function (page, limit) {
            $.post(
                "/portal/listHeightHitsArticles.action",
                {page: page, limit: limit},
                function (data) {
                    if (data.data.length < LIMIT) {
                        $("#loadMoreText").html("没有更多了~");
                        // 禁用点击
                        $("#loadMoreText").removeAttr("href");
                        $("#loadMoreText").removeAttr("click");
                    }
                    $.each(data.data, function (index, article) {
                        var outerBox = ""
                            + "<div class='col-md-6 col-sm-6'>"
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
                    // 页码递增
                    PAGE++;
                    // 每页记录数不变
                    // LIMIT += 6;
                }
            );
        },

        /**
         * / 当鼠标移到到文章图片上时的动画效果
         * @param img
         * @param isMouseOver
         */
        ooo: function (img, isMouseOver) {
            if (isMouseOver) {
                $(img).css('box-shadow', '0px 0px 11px #333');
            } else {
                $(img).css("box-shadow", '0');
            }
        },

        /**
         * / 加载热门文章（高赞）
         * @param page
         * @param limit
         */
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

        /**
         * / 加载最新文章
         * @param page
         * @param limit
         */
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

        /**
         * / 加载热门标签
         */
        loadHotTags: function () {
            $.ajax({
                url: "/portal/index/listHotTags.action",
                method: "POST",
                data: {page: 1, limit: 12},
                success: function (data) {
                    $.each(data, function (index, tag) {
                        var $tagItem = $("<li><a href='/portal/article/tag/" + tag.name + ".html'>" + tag.name + "</a></li>");
                        // $tagItem.bind("click", function () {
                        //     portalIndex.toHotPage(tag.name, "tag");
                        // });
                        $("#hotTags").append($tagItem);
                    });
                }
            })
        },

        /**
         * / 根据条件获取文章列表
         * @param searchValue
         * @param type
         */
        loadArticleByCondition: function (searchValue, type) {
            var param;
            // 点击热门标签
            if ("tag" === type) {
                param = {page: CONDITION_TAG_PAGE, limit: CONDITION_TAG_LIMIT};
                param.tag = searchValue;
                param.type = type;

                // 点击热门分类
            } else if ("category" === type) {
                param = {page: CONDITION_CATEGORY_PAGE, limit: CONDITION_CATEGORY_LIMIT};
                param.categories = searchValue;
                param.type = type;
            }
            // 发送请求
            $.ajax({
                url: "/portal/article/loadArticleByCondition.action",
                method: "POST",
                data: param,
                success: function (data) {
                    // 页码先加 1
                    if (type === "tag") {
                        CONDITION_TAG_PAGE++;
                    } else if (type === "category") {
                        CONDITION_CATEGORY_PAGE++;
                    }
                    // 先清空
                    // $("#postRow").empty();
                    if (data.data.length < LIMIT) {
                        $("#loadMoreText").html("没有更多了~");
                        // 禁用点击
                        $("#loadMoreText").removeAttr("href");
                        $("#loadMoreText").removeAttr("click");
                    }
                    $.each(data.data, function (index, article) {
                        var outerBox = ""
                            + "<div class='col-md-6 col-sm-6'>"
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
                    // 页码递增
                    // PAGE++;
                    // 每页记录数不变
                    // LIMIT += 6;
                },
                error: function (result) {

                }

            });
        },
    }
})(jQuery);
