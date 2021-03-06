<%@page pageEncoding="utf-8" %>
<%--头部--%>
<jsp:include page="common/header.jsp"/>
<style>
    .atitle {
        display: block;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: center;
        padding-left: 20px;
        padding-right: 20px;
    }
    .acontent {
        /*overflow: hidden;*/
        display: -webkit-box;
        text-overflow: ellipsis;
        word-break: break-all;
        -webkit-line-clamp: 4;
        -webkit-box-orient: vertical;
    }
</style>
<div class="widewrapper main">
    <div class="container">
        <div class="row">
            <div class="col-md-12 blog-main">
                <div id="postRow" class="row">
                </div>

                <div class="paging" style="text-align: center">
                    <a id="loadMoreText" href="javascript:void(0)" onclick="header.loadMoreArticle();"
                       class="older"><i class="fa fa-hand-o-down"></i><span>加载更多</span></a>
                </div>
            </div>
        </div>
    </div>
</div>
<%--尾部--%>
<%--<%@ include file="common/footer.jsp" %>--%>
<script src="/portal/js/jquery.min.js"></script>
<script src="/common/js/cgszl.utils.js"></script>
<script src="/portal/js/header.js"></script>
<script type="text/javascript">
    $("#pageTitle").html("文章详情");
    var keyWord = '<%=request.getAttribute("keyWord")%>';
    var articleList = '<%=request.getAttribute("articleList")%>';
    header.renderArticleList(articleList, keyWord);
</script>