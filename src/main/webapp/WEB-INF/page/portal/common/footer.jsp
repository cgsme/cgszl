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
                    <h3><i class="fa fa-link"></i>友情链接</h3>

                    <ul id="linksList" class="clean-list">
                        <%--<li><a href="">Clean - Responsive HTML5 Template</a></li>--%>
                    </ul>
                    <%--<p>您可以通过以下方式与我取得联系。</p>--%>
                    <%--<p></p>--%>
                    <%--<div class="footer-widget-icon">--%>
                        <%--<i class="fa fa-weibo"></i><i class="fa fa-wechat"></i><i class="fa fa-qq"></i>--%>
                    <%--</div>--%>
                </div>

            </div>
        </div>
    </div>
    <div class="widewrapper copyright">
        <%--Copyright--%>&copy;2018 曹图图，<a href="/" title="曹图图">博客</a>
    </div>
</footer>
<script src="/portal/js/jquery.min.js"></script>
<script src="/portal/js/bootstrap.min.js"></script>
<script src="/portal/js/jquery.scrollUp.min.js"></script>
<script src="/portal/js/modernizr.js"></script>
<script src="/common/js/cgszl.utils.js"></script>
<script src="/common/js/jquery.form.js"></script>
<script src="/portal/js/portalIndex.js"></script>
<script type="text/javascript">
    // 文档加载完成后执行
    $(function () {
        // 初始化返回顶部按钮
        $.scrollUp({
            animation: 'slide',
            scrollText: '返回顶部'
//            activeOverlay: '#00FFFF'
        });
        portalIndex.loadData('${name}', '${type}');
    });
</script>
</body>
</html>
