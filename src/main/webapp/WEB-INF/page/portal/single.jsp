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
                        <h2>${article.title}</h2>
                        <div class="meta">
                            <i title="作者" class="fa fa-user"></i>${article.user.screenName}
                            <i title="发布日期" class="fa fa-calendar"></i><span id="created"> </span>
                            <i title="阅读量" class="fa fa-eye"></i><span class="data">${article.hits}</span>
                            <i title="评论数" class="fa fa-comments"></i><span class="data"><a href="#comments">${article.commentsNum} 评论</a></span>
                        </div>
                        <div class="acontent">
                            ${article.content}
                        </div>
                    </div>
                </article>

                <div class="meta" style="text-align: center;">
                    <a id="likeBtn" href="javascript:void(0)"
                       onclick="like(${article.aid}, ${article.likeNum})" title="喜欢">
                        <i class="fa fa-thumbs-o-up"></i></a> <span id="likeNum">${article.likeNum}</span> &nbsp;&nbsp;
                    <a id="unlikeBtn" href="javascript:void(0)"
                       onclick="unlike(${article.aid}, ${article.unlikeNum})" title="不喜欢">
                        <i class="fa fa-thumbs-o-down"></i></a> <span id="unlikeNum">${article.unlikeNum}</span>
                </div>

                <aside class="social-icons clearfix">
                    <h3>分享到 </h3>
                    <a href="#" title="微博"><i class="fa fa-weibo"></i></a>
                    <a href="#" title="微信"><i class="fa fa-wechat"></i></a>
                    <a href="#" title="QQ"><i class="fa fa-qq"></i></a>
                </aside>

                <aside class="comments" id="comments">
                    <hr>

                    <h2><i class="fa fa-comments"></i> ${article.commentsNum} 评论</h2>

                    <article class="comment">
                        <header class="clearfix">
                            <img src="/portal/img/favicon.ico" alt="也是大佬" class="avatar">
                            <div class="meta">
                                <h3><a href="#">John Doe</a></h3>
                                <span class="date">
                                        24 August 2015
                                    </span>
                                <span class="separator">
                                        -
                                    </span>

                                <a href="#create-comment" class="reply-link">回复</a>
                            </div>
                        </header>
                        <div class="body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere sit perspiciatis debitis,
                            vel ducimus praesentium expedita, assumenda ipsum cum corrupti dolorum modi. Rem ipsam
                            similique sapiente obcaecati tenetur beatae voluptatibus.
                        </div>
                    </article>

                    <article class="comment reply">
                        <header class="clearfix">
                            <img src="/portal/img/avatar.png" alt="A Smart Guy" class="avatar">
                            <div class="meta">
                                <h3><a href="#">John Doe</a></h3>
                                <span class="date">
                                        24 August 2015
                                    </span>
                                <span class="separator">
                                        -
                                    </span>

                                <a href="#create-comment" class="reply-link">Reply</a>
                            </div>
                        </header>
                        <div class="body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere sit perspiciatis debitis,
                            vel ducimus praesentium expedita, assumenda ipsum cum corrupti dolorum modi. Rem ipsam
                            similique sapiente obcaecati tenetur beatae voluptatibus.
                        </div>
                    </article>

                    <article class="comment ">
                        <header class="clearfix">
                            <img src="/portal/img/avatar.png" alt="A Smart Guy" class="avatar">
                            <div class="meta">
                                <h3><a href="#">John Doe</a></h3>
                                <span class="date">
                                        24 August 2015
                                    </span>
                                <span class="separator">
                                        -
                                    </span>

                                <a href="#create-comment" class="reply-link">Reply</a>
                            </div>
                        </header>
                        <div class="body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere sit perspiciatis debitis,
                            vel ducimus praesentium expedita, assumenda ipsum cum corrupti dolorum modi. Rem ipsam
                            similique sapiente obcaecati tenetur beatae voluptatibus.
                        </div>
                    </article>

                    <article class="comment">
                        <header class="clearfix">
                            <img src="/portal/img/avatar.png" alt="A Smart Guy" class="avatar">
                            <div class="meta">
                                <h3><a href="#">John Doe</a></h3>
                                <span class="date">
                                        24 August 2015
                                    </span>
                                <span class="separator">
                                        -
                                    </span>

                                <a href="#create-comment" class="reply-link">Reply</a>
                            </div>
                        </header>
                        <div class="body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere sit perspiciatis debitis,
                            vel ducimus praesentium expedita, assumenda ipsum cum corrupti dolorum modi. Rem ipsam
                            similique sapiente obcaecati tenetur beatae voluptatibus.
                        </div>
                    </article>
                </aside>

                <aside class="create-comment" id="create-comment">
                    <hr>

                    <h2><i class="fa fa-pencil"></i> 发表评论</h2>

                    <form action="#" method="get" accept-charset="utf-8">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="name" id="comment-name" placeholder="您的姓名"
                                       class="form-control input-lg">
                            </div>
                            <div class="col-md-6">
                                <input type="email" name="email" id="comment-email" placeholder="您的邮箱"
                                       class="form-control input-lg">
                            </div>
                        </div>

                        <input type="url" name="name" id="comment-url" placeholder="您的主页" class="form-control input-lg">

                        <textarea rows="10" name="message" id="comment-body" placeholder="您的留言"
                                  class="form-control input-lg"></textarea>

                        <div class="buttons clearfix">
                            <button type="submit" class="btn btn-xlarge btn-clean-one">发表评论</button>
                        </div>
                    </form>
                </aside>
            </div>
            <%--右边列表--%>
            <jsp:include page="common/rightside.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
<script type="text/javascript">

    $(function () {
        // 渲染文章发布时间
        var created = ${article.created};
        $("#created").html(cgszlUtils.translateTimstampTo(created * 1000, "yyyy-MM-dd"));
    });

    // 喜欢
    function like(aid, likeNum) {
        // 禁用点赞按钮
        $("#likeBtn").removeAttr("onclick");
        $("#likeBtn").removeAttr("href");
        $("#unlikeBtn").removeAttr("onclick");
        $("#unlikeBtn").removeAttr("href");
        $.post("/portal/likeOrUnlike.action", {aid:aid, isLike:true}, function (data) {
            if (data && data.success) {
//                alert("+ 1", {icon: 1});
                $("#likeNum").html(likeNum + 1);
            }
        });
    }

    // 不喜欢
    function unlike(aid, unlikeNum) {
        // 禁用点赞按钮
        $("#likeBtn").removeAttr("onclick");
        $("#likeBtn").removeAttr("href");
        $("#unlikeBtn").removeAttr("onclick");
        $("#unlikeBtn").removeAttr("href");
        $.post("/portal/likeOrUnlike.action", {aid:aid, isLike:false}, function (data) {
            if (data && data.success) {
//                alert("+ 1", {icon: 1});
                $("#unlikeNum").html(unlikeNum + 1);
            }
        });
    }
</script>
<%--尾部--%>
