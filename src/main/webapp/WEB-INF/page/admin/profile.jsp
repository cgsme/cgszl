<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>资料展示页面</title>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>
    <link rel="stylesheet" href="/common/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/common/bootstrap/css/bootstrap-theme.min.css"/>
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/plugins/css3-mediaqueries.js"></script>
    <![endif]-->
    <link type="text/css" href="${request.pageContext.contextPath}/admin/css/plugins/jquery.tagsinput.css">
    <script src="${request.pageContext.contextPath}/admin/js/plugins/jquery.tagsinput.min.js"></script>
    <script src="js/custom/profile.js"></script>
</head>
<script type="text/javascript">
    window.onbeforeunload = function () {
        //刷新后页面自动回到顶部
        document.documentElement.scrollTop = 0;  // ie下
        document.body.scrollTop = 0;  // 非ie
    }
</script>
<body>
<div class="pageheader">
    <span class="profilepic"><img src="images/thumbs/avatarbig.png" alt=""/></span>
    <div class="profiletitle">
        <h1 class="pagetitle">${login_user.screenName}</h1>
        <span class="pagedesc" title="技能标签">${fn:replace(userInfo.tecHobby, ",", " / ")}</span>
    </div>
    <ul class="hornav" style="margin-top: 15px;">
        <li class="current"><a style="text-decoration: none;" href="#profile">个人信息</a></li>
        <li><a style="text-decoration: none;" href="#editprofile">编辑个人信息</a></li>
        <li><a style="text-decoration: none;" href="#editpassword">修改密码</a></li>
    </ul>
</div><!--pageheader-->

<div id="contentwrapper" class="contentwrapper">
    <div class="two_third last profile_wrapper">
        <div id="profile" class="subcontent">
            <%--<button id="followbtn" class="stdbtn btn_yellow followbtn">Follow</button>
            <ul class="profile_summary">
                <li><a href="followers.html"><span>15</span> Followers</a></li>
                <li><a href="" id="following"><span>20</span> Following</a></li>
                <li><a href="blog.html"><span>2</span> Blog</a></li>
                <li><a href=""><span>8</span> Project Shots</a></li>
            </ul>--%>

            <c:if test="${userInfo.maxim != null or userInfo.maxim ne ''}">
                <blockquote class="bq2 currentstatus marginbottom0" style="margin: 0px;">
                    <a class="edit_status" title="编辑座右铭"></a>
                        ${userInfo.maxim}
                </blockquote>
            </c:if>
            <div class="contenttitle2">
                <h3>关于我</h3>
            </div><!--contenttitle-->

            <div class="profile_about">
                <p>${userInfo.introduction}</p>
            </div>


            <div class="contenttitle2">
                <h3>基本信息</h3>
            </div><!--contenttitle-->
            <div class="recentblog">
                <table class="stdtable stdtablecb overviewtable2">
                    <colgroup>
                        <col class="con1" width="30%"/>
                        <col class="con0" width="70%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>登录名</td>
                        <td>${login_user.username}</td>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td>${login_user.screenName}</td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td>${login_user.email}</td>
                    </tr>
                    <tr>
                        <td>主页</td>
                        <td><a href="${login_user.homeUrl}" target="_blank">${login_user.homeUrl}</a></td>
                    </tr>
                    </tbody>
                </table>
            </div><!--recentblog-->
            <br clear="all"/>

            <div class="contenttitle2">
                <h3>详细信息</h3>
            </div><!--contenttitle-->
            <table class="stdtable stdtablecb overviewtable2">
                <colgroup>
                    <col class="con1" width="30%"/>
                    <col class="con0" width="70%"/>
                </colgroup>
                <tbody>
                <tr>
                    <td>性别</td>
                    <td>${userInfo.sex}</td>
                </tr>
                <tr>
                    <td>手机号码</td>
                    <td>${userInfo.phone}</td>
                </tr>
                <tr>
                    <td>QQ / 微信</td>
                    <td>${userInfo.qq}</td>
                </tr>
                <tr>
                    <td>技术爱好</td>
                    <td>${userInfo.tecHobby}</td>
                </tr>
                <tr>
                    <td>婚姻状况</td>
                    <td>${userInfo.merry}</td>
                </tr>
                <tr>
                    <td>职位</td>
                    <td>${userInfo.post}</td>
                </tr>
                <tr>
                    <td>GitHub</td>
                    <td>
                        <a href="${userInfo.github}" target="_blank">${userInfo.github}</a>
                    </td>
                </tr>
                <tr>
                    <td>住址</td>
                    <td>${userInfo.address}</td>
                </tr>
                </tbody>
            </table>
            <br clear="all"/>
        </div><!--#profile-->

        <%--编辑个人信息--%>
        <div id="editprofile" class="subcontent" style="display: none">
            <div id="basicform">
                <div class="contenttitle2">
                    <h3>基本信息</h3>
                </div>
                <form id="userInfoForm" class="stdform"
                      action="/admin/user/saveUserInfo.action" method="post">
                    <input type="hidden" name="uid" value="${login_user.uid}"/>
                    <input type="hidden" name="id" value="${userInfo.id}"/>
                    <p>
                        <label>登录名</label>
                        <span class="field">
                                <input type="text" value="${login_user.username}" name="username" disabled="disabled"
                                       class="mediuminput"/>
                            </span>
                        <small class="desc">登录名用于登录系统，不可修改.</small>
                    </p>
                    <p>
                        <label>用户名</label>
                        <span class="field">
                                <input type="text" value="${login_user.screenName}" name="screenName"
                                       class="mediuminput"/>
                            </span>
                    </p>
                    <p>
                        <label>邮箱</label>
                        <span class="field">
                                <input type="text" id="" value="${login_user.email}" name="email" class="mediuminput"/>
                            </span>
                    </p>
                    <p>
                        <label>主页</label>
                        <span class="field">
                                <input type="text" name="homeUrl" value="${login_user.homeUrl}"
                                       class="longinput" placeholder="……"/>
                            </span>
                    </p>
                    <div id="detail-panel" style="display: block;">
                        <div class="contenttitle2">
                            <h3>详细信息</h3>
                        </div>
                        <p>
                            <label>性别</label>
                            <span class="field">
                                <select name="sex" class="uniformselect">
                                    <c:choose>
                                        <c:when test="${userInfo.sex eq '男'}">
                                            <option value="未知">未知</option>
                                            <option value="男" selected>男</option>
                                            <option value="女">女</option>
                                        </c:when>
                                        <c:when test="${userInfo.sex eq '女'}">
                                            <option value="未知">未知</option>
                                            <option value="男">男</option>
                                            <option value="女" selected>女</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="未知" selected>未知</option>
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                </span>
                        </p>
                        <p>
                            <label>住址</label>
                            <span class="field">
                                    <input type="text" value="${userInfo.address}" name="address" class="longinput"/>
                                </span>
                        </p>
                        <p>
                            <label>婚姻状况</label>
                            <span class="field">
                                    <select name="merry" class="uniformselect">
                                        <c:choose>
                                            <c:when test="${userInfo.merry eq '已婚'}">
                                                <option value="未知">未知</option>
                                                <option value="已婚" selected>已婚</option>
                                                <option value="未婚">未婚</option>
                                                <option value="单身狗">单身狗</option>
                                            </c:when>
                                            <c:when test="${userInfo.merry eq '未婚'}">
                                                <option value="未知">未知</option>
                                                <option value="已婚">已婚</option>
                                                <option value="未婚" selected>未婚</option>
                                                <option value="单身狗">单身狗</option>
                                            </c:when>
                                            <c:when test="${userInfo.merry eq '单身狗'}">
                                                <option value="未知">未知</option>
                                                <option value="已婚">已婚</option>
                                                <option value="未婚">未婚</option>
                                                <option value="单身狗" selected>单身狗</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="未知" selected>未知</option>
                                                <option value="已婚">已婚</option>
                                                <option value="未婚">未婚</option>
                                                <option value="单身狗">单身狗</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </span>
                        </p>
                        <p>
                            <label>职位</label>
                            <span class="field">
                                    <input type="text" value="${userInfo.post}" name="post" class="longinput"/>
                                </span>
                        </p>
                        <p>
                            <label>Github</label>
                            <span class="field">
                                    <input type="text" value="${userInfo.github}" name="github" class="longinput"/>
                                </span>
                        </p>
                        <p>
                            <label>技术爱好</label>
                            <span class="field">
                                    <input id="tecHobby" type="text" value="${userInfo.tecHobby}" name="tecHobby"
                                           placeholder="感兴趣的技术……" class="longinput"/>
                                </span>
                        </p>
                        <p>
                            <label>座右铭</label>
                            <span class="field">
                                    <input type="text" value="${userInfo.maxim}" name="maxim" class="longinput"/>
                                </span>
                        </p>
                        <p>
                            <label>手机号码</label>
                            <span class="field"><input type="text" value="${userInfo.phone}" name="phone"
                                                       class="longinput"/></span>
                        </p>
                        <p>
                            <label>QQ / 微信</label>
                            <span class="field"><input type="text" name="qq" value="${userInfo.qq}" class="longinput"/></span>
                        </p>
                        <p>
                            <label>个人介绍</label>
                            <span class="field">
                                    <textarea cols="80" rows="8" id="textarea2" name="introduction"
                                              class="longinput">${userInfo.introduction}</textarea>
                                </span>
                        </p>
                    </div>
                    <p class="stdformbutton">
                        <button class="submit radius2">保存</button>
                        <input type="reset" class="reset radius2" value="重置"/>
                    </p>
                </form>
                <br/>
            </div><!--subcontent-->
        </div><!--#editprofile-->
        <%-- editpassword --%>
        <div id="editpassword" class="subcontent" style="display: none;">
            <form id="editPasswordForm" class="stdform" method="post" action="/admin/user/changePassword.action">
                <p>
                    <label>旧密码</label>
                    <span class="field">
                        <input type="password" name="oldPassword" id="oldpassword"
                               placeholder="请输入旧密码..." class="longinput" style="width: 300px"/>
                    </span>
                </p>
                <p>
                    <label>新密码</label>
                    <span class="field">
                        <input type="password" name="newPassword" id="newpassword"
                               placeholder="请输入新密码..." class="longinput" style="width: 300px"/>
                    </span>
                </p>
                <p>
                    <label>确认密码</label>
                    <span class="field">
                        <input type="password" name="confirmPassword" id="confirmpassword"
                               placeholder="请再次输入旧密码..." class="longinput" style="width: 300px"/>
                    </span>
                </p>
                <p class="stdformbutton">
                    <button class="submit radius2">确认修改</button>
                </p>
            </form>
        </div>
        <%--editpassword--%>
        <br/><br/>
    </div><!--two_third-->
    <br/><br/>
</div><!--bodywrapper-->
</body>
</html>
