<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="js/custom/profile.js"></script>
</head>

<body>
    <div class="pageheader">
        <span class="profilepic"><img src="images/thumbs/avatarbig.png" alt=""/></span>
        <div class="profiletitle">
            <h1 class="pagetitle">${login_user.screenName}</h1>
            <span class="pagedesc">java / java</span>
        </div>
        <ul class="hornav">
            <li class="current"><a href="#profile">个人信息</a></li>
            <li><a href="#editprofile">编辑个人信息</a></li>
        </ul>
    </div><!--pageheader-->

    <div id="contentwrapper" class="contentwrapper">
        <div class="two_third last profile_wrapper">
            <div id="profile" class="subcontent">
                <button id="followbtn" class="stdbtn btn_yellow followbtn">Follow</button>
                <ul class="profile_summary">
                    <li><a href="followers.html"><span>15</span> Followers</a></li>
                    <li><a href="" id="following"><span>20</span> Following</a></li>
                    <li><a href="blog.html"><span>2</span> Blog</a></li>
                    <li><a href=""><span>8</span> Project Shots</a></li>
                </ul>

                <blockquote class="bq2 currentstatus marginbottom0">
                    <a class="edit_status" title="Edit Status"></a>
                    This is an example of my current status. When clicking Follow button above, watch how the number of
                    Following change. This is ajax implementation ready, just read the documentation on how. :)
                </blockquote>

                <div class="contenttitle2">
                    <h3>About Juan</h3>
                </div><!--contenttitle-->

                <div class="profile_about">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi auctor, sem sit amet eleifend
                        feugiat, nulla mauris molestie lectus, id rutrum sem nunc ac dolor. Cras condimentum tincidunt
                        semper. Proin a justo vitae massa adipiscing convallis eu in nunc. Nam fringilla ante cursus
                        enim gravida sodales. Nulla sit amet felis erat. Praesent vel augue ac lacus lobortis pulvinar.
                        Praesent elit quam, porta id sagittis sit amet, pretium a augue.</p>
                    <p>Aliquam molestie rutrum tincidunt. Nullam non augue a libero interdum hendrerit. Sed vestibulum
                        mi quis odio vestibulum dapibus. Curabitur porttitor, libero eget lobortis ultrices, elit sem
                        dignissim enim, in vestibulum magna tortor vel dui. </p>
                </div>


                <div class="contenttitle2">
                    <h3>Recent Blog</h3>
                </div><!--contenttitle-->

                <div class="recentblog">
                    <div class="blogthumb">
                        <a href="blogview.html"><img src="images/preview/blog1.png" alt=""/></a>
                    </div><!--blogthumb-->
                    <div class="blogsummary">
                        <h3><a href="blogview.html">Some Tutorials (an in-house blog)</a></h3>
                        <small>June 10, 2012 3:30pm &nbsp;/&nbsp; 0 Comment</small>
                        <p>This is where you can discuss or give some tips/tutorials as a guide for your fellow
                            teammates. Vivamus vitae lacus dui, in vestibulum augue. Vestibulum ante ipsum primis. Lorem
                            ipsum dolor sit amet, consectetur adipiscing elit. Ut eget nibh urna. Vivamus vitae lacus
                            dui.</p>
                        <p><a href="blogview.html" class="orangeboldlink">Read More &raquo;</a></p>
                    </div><!--blogsummary-->
                </div><!--recentblog-->

                <br clear="all"/>

                <div class="contenttitle2">
                    <h3>Project Shots</h3>
                </div><!--contenttitle-->

                <ul class="recentshots">
                    <li>
                        <a href="" class="th"><img src="images/preview/portfolio1.png" alt=""/></a>
                        <h4><a href="">Admin Template</a></h4>
                        <small>2 Comments</small>
                    </li>
                    <li>
                        <a href="" class="th"><img src="images/preview/portfolio2.png" alt=""/></a>
                        <h4><a href="">File Manager</a></h4>
                        <small>0 Comment</small>
                    </li>
                </ul>


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
                                <input type="text" value="${login_user.username}" name="username" disabled="disabled" class="mediuminput"/>
                            </span>
                            <small class="desc">登录名用于登录系统，不可修改.</small>
                        </p>
                        <p>
                            <label>用户名</label>
                            <span class="field">
                                <input type="text" value="${login_user.screenName}" name="screenName" class="mediuminput"/>
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
                                    <select name="sex" class="uniformselect">
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
                                    <input id="tags" type="text" value="${userInfo.tecHobby}" name="tecHobby" class="longinput"/>
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
                                <span class="field"><input type="text" value="${userInfo.phone}" name="phone" class="longinput"/></span>
                            </p>
                            <p>
                                <label>QQ/微信</label>
                                <span class="field"><input type="text" name="qq" class="longinput"/></span>
                            </p>
                            <p>
                                <label>个人介绍</label>
                                <span class="field">
                                    <textarea cols="80" rows="8" id="textarea2" name="introduction" class="longinput">${userInfo.introduction}</textarea>
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

                <div id="validation" class="subcontent" style="display: none">

                    <form id="form1" class="stdform" method="post" action="">
                        <p>
                            <label>First Name</label>
                            <span class="field"><input type="text" name="firstname" id="firstname"
                                                       class="longinput"/></span>
                        </p>

                        <p>
                            <label>Last Name</label>
                            <span class="field"><input type="text" name="lastname" id="lastname"
                                                       class="longinput"/></span>
                        </p>

                        <p>
                            <label>Email</label>
                            <span class="field"><input type="text" name="email" id="email" class="longinput"/></span>
                        </p>

                        <p>
                            <label>Location</label>
                            <span class="field"><textarea cols="80" rows="5" name="location" class="mediuminput"
                                                          id="location"></textarea></span>
                        </p>

                        <p>
                            <label>Select</label>
                            <span class="field">
                                <select name="selection" id="selection">
                                    <option value="">Choose One</option>
                                    <option value="1">Selection One</option>
                                    <option value="2">Selection Two</option>
                                    <option value="3">Selection Three</option>
                                    <option value="4">Selection Four</option>
                                </select>
                                </span>
                        </p>

                        <br/>

                        <p class="stdformbutton">
                            <button class="submit radius2">Submit Button</button>
                        </p>
                    </form>

                </div><!--subcontent-->
            </div><!--#editprofile-->
            <br/><br/>
        </div><!--two_third-->
        <br/><br/>
    </div><!--bodywrapper-->
</body>
</html>
