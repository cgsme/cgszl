<%@page pageEncoding="utf-8" %>
<!doctype html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>
    <!-- META TAGS -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Knowledge Base Theme</title>

    <link rel="shortcut icon" href="../../images/favicon.png" />

    <!-- Style Sheet-->
    <link rel="stylesheet" href="../../style.css"/>
    <link rel='stylesheet' id='bootstrap-css-css' href='../../css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css' href='../../css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css' href='../../js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css' href='../../css/main5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='custom-css-css' href='../../css/custom5152.html?ver=1.0' type='text/css' media='all' />


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="js/html5.js"></script>
    <![endif]-->

</head>

<body>

<!-- Start of Header -->
<div class="header-wrapper">
    <header>
        <div class="container">


            <div class="logo-container">
                <!-- Website Logo -->
                <a href="portal/index-2.html" title="Knowledge Base Theme">
                    <%--<img src="../../images/logo.png" alt="Knowledge Base Theme">--%>
                    <span class="tag-line">图图</span>
                </a>
                <span class="tag-line">图图 blog</span>
            </div>


            <!-- Start of Main Navigation -->
            <nav class="main-nav">
                <div class="menu-top-menu-container">
                    <ul id="menu-top-menu" class="clearfix">
                        <li class="current-menu-item"><a href="portal/index-2.html">主页</a></li>
                        <%--<li><a href="home-categories-description.html">Home 2</a></li>--%>
                        <%--<li><a href="home-categories-articles.html">Home 3</a></li>--%>
                        <li><a href="portal/articles-list.html">文章列表</a></li>
                        <li><a href="portal/faq.html">常见问题</a></li>
                        <li><a href="#">换肤</a>
                            <ul class="sub-menu">
                                <li><a href="portal/blue-skin.html">蓝色皮肤</a></li>
                                <li><a href="portal/green-skin.html">绿色皮肤</a></li>
                                <li><a href="portal/red-skin.html">红色皮肤</a></li>
                                <li><a href="portal/index-2.html">默认皮肤</a></li>
                            </ul>
                        </li>
                        <li><a href="#">更多</a>
                            <ul class="sub-menu">
                                <li><a href="portal/full-width.html">Full Width</a></li>
                                <li><a href="portal/elements.html">Elements</a></li>
                                <li><a href="portal/page.html">Sample Page</a></li>
                            </ul>
                        </li>
                        <li><a href="portal/contact.html">联系我</a></li>
                    </ul>
                </div>
            </nav>
            <!-- End of Main Navigation -->

        </div>
    </header>
</div>
<!-- End of Header -->

<!-- Start of Search Wrapper -->
<div class="search-area-wrapper">
    <div class="search-area container">
        <h3 class="search-header">有什么要找的？</h3>
        <p class="search-tag-line">如果您有任何问题，可以在下面输入您要查找的内容！</p>

        <form id="search-form" class="search-form clearfix" method="get" action="#" autocomplete="off">
            <input class="search-term required" type="text" id="s" name="s" placeholder="输入您的搜索条件..." title="* 输入您的搜索条件!" />
            <input class="search-btn" type="submit" value="搜索" />
            <div id="search-error-container"></div>
        </form>
    </div>
</div>
<!-- End of Search Wrapper -->
<%--<div class="copyrights">Collect from <a href="#" title="cgszl博客">cgszl博客</a></div>--%>

<!-- Start of Page Container -->
<div class="page-container">
    <div class="container">
        <div class="row">

            <!-- start of page content -->
            <div class="span8 page-content">

                <!-- Basic Home Page Template -->
                <div class="row separator">
                    <section class="span4 articles-list">
                        <h3>推荐文章</h3>
                        <ul class="articles">
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">Integrating WordPress with Your Website</a></h4>
                                <span class="article-meta">2013-02-25 发布于 <a href="#" title="View all posts in Server &amp; Database">服务器 &amp; 数据库</a></span>
                                <span class="like-count">66</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">WordPress Site Maintenance</a></h4>
                                <span class="article-meta">24 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                <span class="like-count">15</span>
                            </li>
                            <li class="article-entry video">
                                <h4><a href="portal/single.html">Meta Tags in WordPress</a></h4>
                                <span class="article-meta">23 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                <span class="like-count">8</span>
                            </li>
                            <li class="article-entry image">
                                <h4><a href="portal/single.html">WordPress in Your Language</a></h4>
                                <span class="article-meta">22 Feb, 2013 in <a href="#" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                <span class="like-count">6</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">Know Your Sources</a></h4>
                                <span class="article-meta">22 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                <span class="like-count">2</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">Validating a Website</a></h4>
                                <span class="article-meta">21 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                <span class="like-count">3</span>
                            </li>
                        </ul>
                    </section>


                    <section class="span4 articles-list">
                        <h3>最新文章</h3>
                        <ul class="articles">
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">Integrating WordPress with Your Website</a></h4>
                                <span class="article-meta">2013-02-25 in <a href="#" title="View all posts in Server &amp; Database">服务器 &amp; 数据库</a></span>
                                <span class="like-count">66</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">Using Javascript</a></h4>
                                <span class="article-meta">25 Feb, 2013 in <a href="#" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                <span class="like-count">18</span>
                            </li>
                            <li class="article-entry image">
                                <h4><a href="portal/single.html">Using Images</a></h4>
                                <span class="article-meta">25 Feb, 2013 in <a href="#" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                <span class="like-count">7</span>
                            </li>
                            <li class="article-entry video">
                                <h4><a href="portal/single.html">Using Video</a></h4>
                                <span class="article-meta">24 Feb, 2013 in <a href="#" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                <span class="like-count">7</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">WordPress Site Maintenance</a></h4>
                                <span class="article-meta">24 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                <span class="like-count">15</span>
                            </li>
                            <li class="article-entry standard">
                                <h4><a href="portal/single.html">WordPress CSS Information and Techniques</a></h4>
                                <span class="article-meta">24 Feb, 2013 in <a href="#" title="View all posts in Theme Development">Theme Development</a></span>
                                <span class="like-count">1</span>
                            </li>
                        </ul>
                    </section>
                </div>
            </div>
            <!-- end of page content -->


            <!-- start of sidebar -->
            <aside class="span4 page-sidebar">

                <section class="widget">
                    <div class="support-widget">
                        <h3 class="title">支持</h3>
                        <p class="intro">需要更多的支持？如果您没有找到想要的，请联系我获取进一步的帮助。</p>
                    </div>
                </section>

                <section class="widget">
                    <div class="quick-links-widget">
                        <h3 class="title">快速链接</h3>
                        <ul id="menu-quick-links" class="menu clearfix">
                            <li><a href="portal/index-2.html">主页</a></li>
                            <li><a href="portal/articles-list.html">文章列表</a></li>
                            <li><a href="portal/faq.html">常见问题解答</a></li>
                            <li><a href="portal/contact.html">联系我</a></li>
                        </ul>
                    </div>
                </section>

                <section class="widget">
                    <h3 class="title">标签</h3>
                    <div class="tagcloud">
                        <a href="#" class="btn btn-mini">basic</a>
                        <a href="#" class="btn btn-mini">beginner</a>
                        <a href="#" class="btn btn-mini">blogging</a>
                        <a href="#" class="btn btn-mini">colour</a>
                        <a href="#" class="btn btn-mini">css</a>
                        <a href="#" class="btn btn-mini">date</a>
                        <a href="#" class="btn btn-mini">design</a>
                        <a href="#" class="btn btn-mini">files</a>
                        <a href="#" class="btn btn-mini">format</a>
                        <a href="#" class="btn btn-mini">header</a>
                        <a href="#" class="btn btn-mini">images</a>
                        <a href="#" class="btn btn-mini">plugins</a>
                        <a href="#" class="btn btn-mini">setting</a>
                        <a href="#" class="btn btn-mini">templates</a>
                        <a href="#" class="btn btn-mini">theme</a>
                        <a href="#" class="btn btn-mini">time</a>
                        <a href="#" class="btn btn-mini">videos</a>
                        <a href="#" class="btn btn-mini">website</a>
                        <a href="#" class="btn btn-mini">wordpress</a>
                    </div>
                </section>

            </aside>
            <!-- end of sidebar -->
        </div>
    </div>
</div>
<!-- End of Page Container -->

<!-- Start of Footer -->
<footer id="footer-wrapper">
    <div id="footer" class="container">
        <div class="row">

            <div class="span3">
                <section class="widget">
                    <h3 class="title">如何运行</h3>
                    <div class="textwidget">
                        <p>不知道。 </p>
                        <p>不需要知道。 </p>
                    </div>
                </section>
            </div>

            <div class="span3">
                <section class="widget"><h3 class="title">分类</h3>
                    <ul>
                        <li><a href="#" title="Lorem ipsum dolor sit amet,">最新技术</a> </li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet,">WordPress设计</a></li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet,">服务器 &amp; 数据库</a></li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet, ">主题发展</a></li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet,">网站开发</a></li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet,">WordPress初学者</a></li>
                        <li><a href="#" title="Lorem ipsum dolor sit amet, ">WordPress插件</a></li>
                    </ul>
                </section>
            </div>

            <div class="span3">
                <section class="widget">
                    <h3 class="title">最新推文</h3>
                    <div id="twitter_update_list">
                        <ul>
                            <li>暂无内容!</li>
                        </ul>
                    </div>

                </section>
            </div>

            <div class="span3">
                <section class="widget">
                    <h3 class="title">Flickr照片</h3>
                    <%--<div class="flickr-photos" id="basicuse">--%>
                    <%--</div>--%>
                </section>
            </div>

        </div>
    </div>
    <!-- end of #footer -->

    <!-- Footer Bottom -->
    <div id="footer-bottom-wrapper">
        <div id="footer-bottom" class="container">
            <div class="row">
                <div class="span6">
                    <p class="copyright">
                        Copyright © 2017. tutu保留所有权利<%--Collect from <a href="#" title="图图blog" target="_blank">图图blog</a>--%>
                    </p>
                </div>
                <div class="span6">
                    <!-- Social Navigation -->
                    <ul class="social-nav clearfix">
                        <li class="linkedin"><a target="_blank" href="#"></a></li>
                        <li class="stumble"><a target="_blank" href="#"></a></li>
                        <li class="google"><a target="_blank" href="#"></a></li>
                        <li class="deviantart"><a target="_blank" href="#"></a></li>
                        <li class="flickr"><a target="_blank" href="#"></a></li>
                        <li class="skype"><a target="_blank" href="skype:#?call"></a></li>
                        <li class="rss"><a target="_blank" href="#"></a></li>
                        <li class="twitter"><a target="_blank" href="#"></a></li>
                        <li class="facebook"><a target="_blank" href="#"></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- End of Footer Bottom -->

</footer>
<!-- End of Footer -->

<a href="#top" id="scroll-top"></a>

<!-- script -->
<script type='text/javascript' src='../../js/jquery-1.8.3.min.js'></script>
<script type='text/javascript' src='../../js/jquery.easing.1.3.js'></script>
<script type='text/javascript' src='../../js/prettyphoto/jquery.prettyPhoto.js'></script>
<script type='text/javascript' src='../../js/jflickrfeed.js'></script>
<script type='text/javascript' src='../../js/jquery.liveSearch.js'></script>
<script type='text/javascript' src='../../js/jquery.form.js'></script>
<script type='text/javascript' src='../../js/jquery.validate.min.js'></script>
<script type='text/javascript' src='../../js/custom.js'></script>

</body>
</html>