<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<footer>
    <div class="widewrapper footer">
        <div class="container">
            <div class="row">
                <div class="col-md-4 footer-widget">
                    <h3><i class="fa fa-user"></i>关于</h3>

                    <p>
                        曹操（155年－220年3月15日），字孟德，一名吉利，小字阿瞒，沛国谯县（今安徽亳州）人。东汉末年杰出的政治家、军事家、文学家、书法家，三国中曹魏政权的奠基人。
                    </p>
                    <p>
                        曹操曾担任东汉丞相，后加封魏王，奠定了曹魏立国的基础。去世后谥号为武王。其子曹丕称帝后，追尊为武皇帝，庙号太祖。
                    </p>

                </div>

                <div class="col-md-4 footer-widget">
                    <h3><i class="fa fa-pencil"></i> 最新文章</h3>
                    <ul id="latestArticles" class="clean-list">
                        <%--<li><a href="">Clean - Responsive HTML5 Template</a></li>--%>
                    </ul>
                </div>

                <div class="col-md-4 footer-widget">
                    <h3><i class="fa fa-envelope"></i>联系我</h3>

                    <p>您可以通过以下方式与我取得联系。</p>
                    <p></p>
                    <div class="footer-widget-icon">
                        <i class="fa fa-weibo"></i><i class="fa fa-wechat"></i><i class="fa fa-qq"></i>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="widewrapper copyright">
        Copyright 2018 曹图图 <a href="#" target="_blank" title="图图超酷">博客</a>
    </div>
</footer>
<script src="/portal/js/jquery.min.js"></script>
<script src="/portal/js/bootstrap.min.js"></script>
<script src="/portal/js/modernizr.js"></script>

<script type="text/javascript">

    // 文档加载完成后执行
    $(function () {
        loadData();
    });

    // 加载数据
    function loadData() {
        // 加载最新文章
        loadRecentPost(1, 5);
        // 加载热门标签
        loadHotTags();
        // 加载热门文章（高赞）
        loadHotArticles(1, 5);
        // 加载首页文章（高点击量）
        loadHeightHitsArticle(1, 5);
        // 加载热门分类
        loadHotCategories(1, 5);

    }

    // 加载热门分类
    var loadHotCategories = function (page, limit) {
        $.post(
            "/portal/listHotCategories.action",
            {page:page, limit:limit},
            function (data) {
                if (data && data.success) {
                    $.each(data.data, function (index, category) {
                        var $hotCategory = $("<li><a href='#'>"+ category.name +"</a></li>");
                        $("#hotCategories").append($hotCategory);
                    })
                } else {
                    $("#hotCategories").html("数据获取失败……");
                }
            }

        )
    };

    // 加载高点击量的文章
    var loadHeightHitsArticle = function (page, limit) {
        $.post(
            "/portal/listHeightHitsArticles.action",
            {page: page, limit: limit},
            function (data) {
                $.each(data.data, function (index, article) {
                    var outerBox = ""
                        + "<div class='col-md-6 col-sm-6'>"
                        + "<article class='blog-teaser'>"
                        + "<header>"
                        + "<img src='/portal/img/2.jpg' alt='曹图图'>"
                        + "<h3><a class='atitle' title='" + article.title + "' href='/single\\" + article.aid + ".html'>" + article.title + "</a></h3>"
                        + "<span class='meta'>" + new Date(article.created * 1000).toLocaleString() + "，" + article.user.screenName + "</span>"
                        + "<hr>"
                        + "</header>"
                        + "<div id='" + article.aid + "' class='body acontent'>"
                        //                                    + article.content
                        + "</div>"
                        + "<div class='clearfix'>"
                        + " <a href='/single\\" + article.aid + ".html' class='btn btn-clean-one'>阅读更多 >></a>"
                        + "</div>"
                        + "</article>"
                        + "</div>"
                        + "";
                    $("#postRow").append(outerBox);
                })
            }
        );
    };

    // 加载热门文章（高赞）
    function loadHotArticles(page, limit) {
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
    }

    // 加载最新文章
    function loadRecentPost(page, limit) {
        $.ajax({
            url: "/portal/listArticles.action",
            method: "POST",
            data: {page: page, limit: limit},
            success: function (data) {
                $.each(data.data, function (index, article) {
                    var $latestArticle = "<li><a href='/single/"+ article.aid +".html'>" + article.title + "</a></li>"
                    $("#latestArticles").append($latestArticle);
                });
            }
        });
    }

    // 加载热门标签
    function loadHotTags() {
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
    }

</script>
</body>
</html>
