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

                <div class="copyrights">Collect from <a href="http://www.smallseashell.com/">手机网站模板</a></div>

                <div class="paging">
                    <a href="#" class="older">更早 >></i></a>
                </div>
            </div>
            <jsp:include page="common/rightside.jsp"/>
        </div>
    </div>
</div>
<%--尾部--%>
<%@ include file="common/footer.jsp" %>

