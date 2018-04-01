<%@page pageEncoding="utf-8" %>
<%--头部--%>
<jsp:include page="common/header.jsp" />

<div class="widewrapper main">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 clean-superblock" id="contact">
                <h2>联系我</h2>

                <form action="#" method="get" accept-charset="utf-8" class="contact-form">
                    <input type="text" name="name" id="contact-name" placeholder="姓名" class="form-control input-lg">
                    <input type="email" name="email" id="contact-email" placeholder="邮箱" class="form-control input-lg">
                    <textarea rows="10" name="message" id="contact-body" placeholder="给我留言……" class="form-control input-lg"></textarea>
                    <div class="buttons clearfix">
                        <button type="submit" class="btn btn-xlarge btn-clean-one">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--尾部--%>
<%@ include file="common/footer.jsp"%>