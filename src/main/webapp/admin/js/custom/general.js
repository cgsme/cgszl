/*
 * 	Additional function for this template
 *	Written by ThemePixels	
 *	http://themepixels.com/
 *
 *	Copyright (c) 2012 ThemePixels (http://themepixels.com)
 *	
 *	Built for Amanda Premium Responsive Admin Template
 *  http://themeforest.net/category/site-templates/admin-templates
 */

jQuery.noConflict();

jQuery(document).ready(function () {


    ///// 当USERINFO被点击时显示/隐藏用户数据 /////

    jQuery('.userinfo').click(function () {
        if (!jQuery(this).hasClass('active')) {
            jQuery('.userinfodrop').show();
            jQuery(this).addClass('active');
        } else {
            jQuery('.userinfodrop').hide();
            jQuery(this).removeClass('active');
        }
        //remove notification box if visible
        jQuery('.notification').removeClass('active');
        jQuery('.noticontent').remove();

        return false;
    });


    ///// 显示/隐藏通知 /////

    jQuery('.notification a').click(function () {
        var t = jQuery(this);
        var url = t.attr('href');
        if (!jQuery('.noticontent').is(':visible')) {
            jQuery.post(url, function (data) {
                t.parent().append('<div class="noticontent">' + data + '</div>');
            });
            //this will hide user info drop down when visible
            jQuery('.userinfo').removeClass('active');
            jQuery('.userinfodrop').hide();
        } else {
            t.parent().removeClass('active');
            jQuery('.noticontent').hide();
        }
        return false;
    });


    ///// 在此元素外部单击时显示/隐藏通知和用户信息 /////
    jQuery(document).click(function (event) {
        var ud = jQuery('.userinfodrop');
        var nb = jQuery('.noticontent');

        //hide user drop menu when clicked outside of this element
        if (!jQuery(event.target).is('.userinfodrop')
            && !jQuery(event.target).is('.userdata')
            && ud.is(':visible')) {
            ud.hide();
            jQuery('.userinfo').removeClass('active');
        }

        //hide notification box when clicked outside of this element
        if (!jQuery(event.target).is('.noticontent') && nb.is(':visible')) {
            nb.remove();
            jQuery('.notification').removeClass('active');
        }
    });


    ///// 通知内容 /////
    jQuery('.notitab a').live('click', function () {
        var id = jQuery(this).attr('href');
        jQuery('.notitab li').removeClass('current'); //reset current
        jQuery(this).parent().addClass('current');
        if (id == '#messages')
            jQuery('#activities').hide();
        else
            jQuery('#messages').hide();

        jQuery(id).show();
        return false;
    });


    ///// 显示/隐藏垂直子菜单 /////
    jQuery('.vernav > ul li a, .vernav2 > ul li a').each(function () {
        var url = jQuery(this).attr('href');
        jQuery(this).click(function () {
            if (jQuery(url).length > 0) {
                if (jQuery(url).is(':visible')) {
                    if (!jQuery(this).parents('div').hasClass('menucoll') &&
                        !jQuery(this).parents('div').hasClass('menucoll2')) {

                        jQuery(url).slideUp();
                    }
                } else {
                    jQuery('.vernav ul ul, .vernav2 ul ul').each(function () {
                        jQuery(this).slideUp();
                    });
                    if (!jQuery(this).parents('div').hasClass('menucoll') &&
                        !jQuery(this).parents('div').hasClass('menucoll2')) {

                        jQuery(url).slideDown();
                    }
                }
            } else {
                //loading层
                var index = layer.load(1, {
                    shade: [0.5,'#fff'] //0.1透明度的白色背景
                });
                // 先清空内容 add on 2018-03-19 00:07
                jQuery('#bolgmanagerbox').html('');
                ////// 加载页面 //////
                jQuery.ajax({
                    url: url,
                    success: function (data) {
                        // TODO 此处判断加载的是否是登录页面，若是则
                        if (data && data.indexOf("<title>登录页面</title>") != -1) {
                            layer.close(index);
                            window.location.reload();
                            return
                        }
                        // 关闭加载提示
                        layer.close(index);
                        // 将页面内容放入div中
                        jQuery('#bolgmanagerbox').html(data);

                    },
                    error: function () {
                        // 关闭加载提示
                        layer.close(index);
                    }
                });
            }
            return false;
        });
    });


    ///// 当菜单折叠时显示/隐藏子菜单 /////
    jQuery('.menucoll > ul > li, .menucoll2 > ul > li').live('mouseenter mouseleave', function (e) {
        if (e.type == 'mouseenter') {
            jQuery(this).addClass('hover');
            jQuery(this).find('ul').show();
        } else {
            jQuery(this).removeClass('hover').find('ul').hide();
        }
    });


    ///// 水平导航（ajax / 内联数据） /////
    jQuery('.hornav a').click(function () {

        // 这仅适用于窗口大小低于450像素的情况
        if (jQuery(this).parents('.more').length == 0)
            jQuery('.hornav li.more ul').hide();

        // 删除当前菜单
        jQuery('.hornav li').each(function () {
            jQuery(this).removeClass('current');
        });

        jQuery(this).parent().addClass('current');	// 设置为当前菜单

        var url = jQuery(this).attr('href');
        if (jQuery(url).length > 0) {
            jQuery('.contentwrapper .subcontent').hide();
            jQuery(url).show();
        } else {
            jQuery('#contentwrapper').html('');
            jQuery.ajax({
                url: url,
                success: function (data) {
                    jQuery('#contentwrapper').html(data);
                    jQuery('.stdtable input:checkbox').uniform();	// 重新设定复选框
                }
            });
        }
        return false;
    });


    ///// 搜索框与自动完成 /////
    var availableTags = [
        "ActionScript",
        "AppleScript",
        "Asp",
        "BASIC",
        "C",
        "C++",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
    ];
    jQuery('#keyword').autocomplete({
        source: availableTags
    });


    ///// 搜索框上的焦点 /////
    jQuery('#keyword').bind('focusin focusout', function (e) {
        var t = jQuery(this);
        if (e.type == 'focusin' && t.val() == '请输入关键字...') {
            t.val('');
        } else if (e.type == 'focusout' && t.val() == '') {
            t.val('请输入关键字...');
        }
    });


    ///// 通知关闭按钮 /////
    jQuery('.notibar .close').click(function () {
        jQuery(this).parent().fadeOut(function () {
            jQuery(this).remove();
        });
    });


    ///// 折叠/展开左侧菜单 /////
    jQuery('.togglemenu').click(function () {
        if (!jQuery(this).hasClass('togglemenu_collapsed')) {

            //if(jQuery('.iconmenu').hasClass('vernav')) {
            if (jQuery('.vernav').length > 0) {
                if (jQuery('.vernav').hasClass('iconmenu')) {
                    jQuery('body').addClass('withmenucoll');
                    jQuery('.iconmenu').addClass('menucoll');
                } else {
                    jQuery('body').addClass('withmenucoll');
                    jQuery('.vernav').addClass('menucoll').find('ul').hide();
                }
            } else if (jQuery('.vernav2').length > 0) {
                //} else {
                jQuery('body').addClass('withmenucoll2');
                jQuery('.iconmenu').addClass('menucoll2');
            }

            jQuery(this).addClass('togglemenu_collapsed');

            jQuery('.iconmenu > ul > li > a').each(function () {
                var label = jQuery(this).text();
                jQuery('<li><span>' + label + '</span></li>')
                    .insertBefore(jQuery(this).parent().find('ul li:first-child'));
            });
        } else {

            //if(jQuery('.iconmenu').hasClass('vernav')) {
            if (jQuery('.vernav').length > 0) {
                if (jQuery('.vernav').hasClass('iconmenu')) {
                    jQuery('body').removeClass('withmenucoll');
                    jQuery('.iconmenu').removeClass('menucoll');
                } else {
                    jQuery('body').removeClass('withmenucoll');
                    jQuery('.vernav').removeClass('menucoll').find('ul').show();
                }
            } else if (jQuery('.vernav2').length > 0) {
                //} else {
                jQuery('body').removeClass('withmenucoll2');
                jQuery('.iconmenu').removeClass('menucoll2');
            }
            jQuery(this).removeClass('togglemenu_collapsed');

            jQuery('.iconmenu ul ul li:first-child').remove();
        }
    });


    ///// 响应 /////
    if (jQuery(document).width() < 640) {
        jQuery('.togglemenu').addClass('togglemenu_collapsed');
        if (jQuery('.vernav').length > 0) {

            jQuery('.iconmenu').addClass('menucoll');
            jQuery('body').addClass('withmenucoll');
            jQuery('.centercontent').css({marginLeft: '56px'});
            if (jQuery('.iconmenu').length == 0) {
                jQuery('.togglemenu').removeClass('togglemenu_collapsed');
            } else {
                jQuery('.iconmenu > ul > li > a').each(function () {
                    // vernav2
                    var label = jQuery(this).text();
                    jQuery('<li><span>' + label + '</span></li>')
                        .insertBefore(jQuery(this).parent().find('ul li:first-child'));
                });
            }

        } else {

            jQuery('.iconmenu').addClass('menucoll2');
            jQuery('body').addClass('withmenucoll2');
            jQuery('.centercontent').css({marginLeft: '36px'});

            jQuery('.iconmenu > ul > li > a').each(function () {
                var label = jQuery(this).text();
                jQuery('<li><span>' + label + '</span></li>')
                    .insertBefore(jQuery(this).parent().find('ul li:first-child'));
            });
        }
    }


    jQuery('.searchicon').live('click', function () {
        jQuery('.searchinner').show();
    });

    jQuery('.searchcancel').live('click', function () {
        jQuery('.searchinner').hide();
    });


    ///// 在加载窗口 /////
    function reposSearch() {
        if (jQuery(window).width() < 520) {
            if (jQuery('.searchinner').length == 0) {
                jQuery('.search').wrapInner('<div class="searchinner"></div>');
                jQuery('<a class="searchicon"></a>').insertBefore(jQuery('.searchinner'));
                jQuery('<a class="searchcancel"></a>').insertAfter(jQuery('.searchinner button'));
            }
        } else {
            if (jQuery('.searchinner').length > 0) {
                jQuery('.search form').unwrap();
                jQuery('.searchicon, .searchcancel').remove();
            }
        }
    }

    reposSearch();

    ///// 调整窗口 /////
    jQuery(window).resize(function () {

        if (jQuery(window).width() > 640)
            jQuery('.centercontent').removeAttr('style');

        reposSearch();

    });


    ///// 改变主题 /////
    jQuery('.changetheme a').click(function () {
        var c = jQuery(this).attr('class');
        if (jQuery('#addonstyle').length == 0) {
            if (c != 'default') {
                jQuery('head').append('<link id="addonstyle" rel="stylesheet" href="css/style.' + c + '.css" type="text/css" />');
                jQuery.cookie("addonstyle", c, {path: '/'});
            }
        } else {
            if (c != 'default') {
                jQuery('#addonstyle').attr('href', 'css/style.' + c + '.css');
                jQuery.cookie("addonstyle", c, {path: '/'});
            } else {
                jQuery('#addonstyle').remove();
                jQuery.cookie("addonstyle", null);
            }
        }
    });

    ///// 当它已经设置时加载插件样式 /////
    if (jQuery.cookie('addonstyle')) {
        var c = jQuery.cookie('addonstyle');
        if (c != '') {
            jQuery('head').append('<link id="addonstyle" rel="stylesheet" href="css/style.' + c + '.css" type="text/css" />');
            jQuery.cookie("addonstyle", c, {path: '/'});
        }
    }


});