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
            <div class="col-md-8 blog-main">
                <div id="postRow" class="row">
                </div>

                <div class="paging" style="text-align: center">
                    <a id="loadMoreText" href="javascript:void(0)" onclick="portalIndex.loadArticleByCondition('${name}', 'tag');"
                       class="older"><i class="fa fa-hand-o-down"></i><span >加载更多</span></a>
                </div>
            </div>
            <jsp:include page="common/rightside.jsp"/>
        </div>
    </div>
</div>
<%--尾部--%>
<%@ include file="common/footer.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#pageTitle").html("热门标签 / 文章列表");
    });
</script>
