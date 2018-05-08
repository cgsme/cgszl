<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<%--头部--%>
<jsp:include page="common/header.jsp"/>
<style>
    .acontent {
        /*overflow: hidden;*/
        display: -webkit-box;
        text-overflow: ellipsis;
        word-break: break-all;
        margin: 10px;
        -webkit-box-orient: vertical;
    }
</style>
<div class="widewrapper main">
    <div class="container">
        <div class="row">
            <div class="col-md-8 blog-main">
                <article class="blog-post">
                    <header>
                        <div class="lead-image">
                            <%--<img src="/portal/img/single-post.jpg" alt="" class="img-responsive">--%>

                        </div>
                    </header>
                    <div class="body">
                        <h3><i class="fa fa-book"></i> ${article.title}</h3>
                        <div class="meta">
                            <i title="作者" class="fa fa-user"></i>${article.user.screenName}
                            <i title="发布日期" class="fa fa-calendar"></i><span id="created"> </span>
                            <i title="分类" class="fa fa-list"></i><span id="created">${article.categories}</span>
                            <i title="阅读量" class="fa fa-eye"></i><span class="data">${article.hits}</span>
                            <i title="评论数" class="fa fa-comments"></i><span class="data"><a
                                href="#comments">${article.commentsNum} 评论</a></span>
                        </div>
                        <div class="acontent">
                            ${article.content}
                        </div>
                    </div>
                </article>

                <div class="meta" style="text-align: center;">
                    <a id="likeBtn" href="javascript:void(0)"
                       onclick="single.like(${article.aid}, ${article.likeNum})" title="喜欢">
                        <i class="fa fa-thumbs-o-up"></i></a> <span id="likeNum">${article.likeNum}</span> &nbsp;&nbsp;
                    <a id="unlikeBtn" href="javascript:void(0)"
                       onclick="single.unlike(${article.aid}, ${article.unlikeNum})" title="不喜欢">
                        <i class="fa fa-thumbs-o-down"></i></a> <span id="unlikeNum">${article.unlikeNum}</span>
                </div>

                <aside class="social-icons clearfix">
                    <h3>分享到 </h3>
                    <a href="#" title="微博"><i class="fa fa-weibo"></i></a>
                    <a href="#" title="微信"><i class="fa fa-wechat"></i></a>
                    <a href="#" title="QQ"><i class="fa fa-qq"></i></a>
                </aside>

                <c:choose>
                    <c:when test="${article.allowComment}">
                        <%--评论展示区--%>
                        <aside class="comments" style="margin-bottom: 0px">
                            <hr>
                            <h4><i class="fa fa-comments"></i> ${article.commentsNum} 评论</h4>
                        </aside>
                        <aside class="comments" id="comments">
                        </aside>

                        <%--发表评论区--%>
                        <aside class="create-comment" id="create-comment">
                            <hr>
                            <h4><i class="fa fa-pencil"></i> 发表评论</h4>
                            <%-- 消息提示框 --%>
                            <div id="msgBox"></div>
                            <form id="commentForm" method="post" onsubmit="return single.saveComment();" accept-charset="utf-8">
                                <%-- 文章标识 --%>
                                <input type="hidden" name="parent" id="parentCommentId">
                                <input type="hidden" name="aid" value="${article.aid}">
                                <div class="row">
                                    <div class="col-md-6">
                                        <input type="text" name="author" id="comment-name" placeholder="您的姓名"
                                               class="form-control input-lg" maxlength="20">
                                    </div>
                                    <div class="col-md-6">
                                        <input type="email" name="mail" id="comment-email" placeholder="您的邮箱"
                                               class="form-control input-lg">
                                    </div>
                                </div>

                                <input type="url" name="url" id="comment-url" maxlength="100" placeholder="您的主页（URL地址）"
                                       class="form-control input-lg">

                                <textarea rows="10" name="content" id="comment-body" placeholder="请输入留言……（500字以内）" required
                                          class="form-control input-lg" maxlength="500"></textarea>

                                <div class="buttons clearfix">
                                    <button type="submit" class="btn btn-xlarge btn-clean-one">发表评论</button>
                                </div>
                            </form>
                        </aside>
                    </c:when>
                    <c:otherwise>
                        <%--评论展示区--%>
                        <aside class="comments" style="margin-bottom: 0px">
                            <hr>
                            <h5>很抱歉，该文章暂未开启评论</h5>
                        </aside>
                    </c:otherwise>
                </c:choose>
            </div>
            <%--右边列表--%>
            <jsp:include page="common/rightside.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
<script src="/portal/js/single.js"></script>
<script type="text/javascript">
    $(function () {
        $("#pageTitle").html("文章详情");
        single.init(${article.created}, ${article.aid});
    });
</script>
<%--尾部--%>
