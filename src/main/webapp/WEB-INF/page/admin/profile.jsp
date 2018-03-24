<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>资料展示页面</title>
    <%@include file="common/common.jsp" %>
    <%@include file="common/pageResource.jsp" %>
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/plugins/css3-mediaqueries.js"></script>
    <![endif]-->
</head>

<body class="">
<div class="">

    <div class="pageheader">
        <span class="profilepic"><img src="images/thumbs/avatarbig.png" alt=""/></span>
        <div class="profiletitle">
            <h1 class="pagetitle">Juan Dela Cruz</h1>
            <span class="pagedesc">Front-End Engineer / UI Designer</span>
        </div>
        <ul class="hornav">
            <li class="current"><a href="#profile">Profile</a></li>
            <li><a href="#editprofile">Edit Profile</a></li>
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

            <div id="editprofile" class="subcontent" style="display: none">
                Edit profile form goes here...
            </div><!--#editprofile-->

            <br/><br/>
        </div><!--two_third-->

        <br/><br/>

    </div><!--contentwrapper-->


</div><!--bodywrapper-->

</body>
</html>
