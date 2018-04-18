/*
 * 个人资料
 */
jQuery(document).ready(function () {

    // 保存旧数据
    var oldData = jQuery("#userInfoForm").serializeJSON();
    // 在键盘按下并释放及提交后验证提交表单
    jQuery("#userInfoForm").validate({
        submitHandler: function (form) {
            // 新数据
            var newData = jQuery(form).serializeJSON();
            // 比较新旧数据是否相等
            var result = cgszlUtils.compare(oldData, newData);
            if (!result) {
                /* 异步提交表单 */
                jQuery(form).ajaxSubmit({
                    success: function (data) {
                        if (data && data.success) {
                            top.layer.msg('操作成功', {icon: 1});
                            window.location.reload();
                        } else {
                            top.layer.msg(data.message, {icon: 2});
                        }
                    }
                });
            } else {
                layer.msg("您没有做任何修改", {icon: 1});
            }
        },
        rules: {
            screenName: {
                required: true,
                maxlength: 10
            },
            username: {
                required: true,
                maxlength: 10
            },
            email: {
                required: true,
                email: true,
                maxlength: 50
            },
            homeUrl: {
                maxlength: 100,
                url: true
            },
            address: {
                maxlength: 100,
            },
            post: {
                maxlength: 50,
            },
            github: {
                maxlength: 250,
                url: true
            },
            tecHobby: {
                maxlength: 150,
            },
            phone: {
                maxlength: 11,
                digits: true
            },
            maxim: {
                maxlength: 250,
            },
            qq: {
                maxlength: 20,
            },
            introduction: {
                maxlength: 150,
            },
        },
        /*messages: {
            screenName: {
                required: "用户名不能为空",
                maxlength: "不能超过10个字符"
            },
            username: {
                required: "登录名不能为空",
                maxLength: "登录名不能超过10个字符"
            },
            email: {
                email: true
            },
            homeUrl: {
                minlength: 100,
                url: true
            },
            address: {
                maxlength: 100,
            },
            post: {
                maxlength: 50,
            },
            github: {
                maxlength: 250,
                url: true
            },
            tecHobby: {
                maxlength: 150,
            },
            phone: {
                maxlength: 11,
                digits: true
            },
            maxim: {
                maxlength: 250,
            },
            qq: {
                maxlength: 20,
            },
            introduction: {
                maxlength: 150,
            },
        }*/
    });

    /* input转标签 */
    jQuery('#tecHobby').tagsInput({
        width: '461',
        height: '',
        defaultText: '',
        'interactive':true,
        'maxChars' : 200,
        // placeholderColor : '#666666'
    });

    /* Follow按钮 */
    jQuery('#followbtn').click(function () {
        if (jQuery(this).text() == 'Follow') {
            jQuery(this).text('Following')
                .removeClass('btn_yellow')
                .addClass('btn_lime');

            //this is an example of updating number
            //of following when clicking follow button
            jQuery('#following span').text('21');

            //use the line of code below to implement it to the server using ajax
            //uncomment the code to use it
            //var action = 'Follow';
            //var url = 'enter your url here'
            //jQuery.post(url,{action: action},function(data) {
            //the server response should be the updated number of following
            //});

        } else {
            jQuery(this).text('Follow')
                .removeClass('btn_lime')
                .addClass('btn_yellow');

            //this is an example of updating number
            //of following when clicking following button
            jQuery('#following span').text('20');

            //use the line of code below to implement it to the server using ajax
            //uncomment the code to use it
            //var action = 'Unfollow';
            //var url = 'enter your url here'
            //jQuery.post(url,{action: action},function(data) {
            //the server response should be the updated number of following
            //});
        }
    });

    ///// ACTIVE STATUS ON HOVER /////
    jQuery('.bq2').hover(function () {
        jQuery(this).find('.edit_status').show();
    }, function () {
        jQuery(this).find('.edit_status').hide();
    });

    ///// CONTENT SLIDER /////
    jQuery('#slidercontent').bxSlider({
        prevText: '',
        nextText: ''
    });

    ///// AUTOGROW TEXTAREA /////
    jQuery('#comment').autogrow();


});
