<%@page pageEncoding="utf-8" %>
<%--头部--%>
<jsp:include page="common/header.jsp"/>

<div class="widewrapper main">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 clean-superblock" id="contact">
                <h2>留言 / 反馈</h2>
                <%-- 消息提示框 --%>
                <div id="msgBox"></div>
                <form id="messageForm" action="/portal/comment/leaveMessage.action"
                      method="get" accept-charset="utf-8" class="contact-form">
                    <input type="text" name="name" id="contact-name" placeholder="姓名"
                           maxlength="50" class="form-control input-lg">
                    <input type="email" name="email" id="contact-email"
                           maxlength="50" placeholder="邮箱" class="form-control input-lg">
                    <textarea rows="10" name="content" id="contact-body"
                              placeholder="给我留言……（500字以内）" maxlength="500" required
                              class="form-control input-lg"></textarea>
                    <div class="buttons clearfix">
                        <button type="submit" class="btn btn-xlarge btn-clean-one">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--尾部--%>
<%@ include file="common/footer.jsp" %>
<script type="text/javascript" src="/portal/js/contcat.js"></script>

