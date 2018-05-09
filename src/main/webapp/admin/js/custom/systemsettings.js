/*
 * 系统设置
 */
jQuery(document).ready(function () {

    // 保存旧数据
    var oldData = jQuery("#systemSettingForm").serializeJSON();

    // 在键盘按下并释放及提交后验证提交表单
    jQuery("#systemSettingForm").validate({
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
                            // window.location.reload();
                        } else {
                            top.layer.msg(data.message, {icon: 2});
                        }
                    }
                });
            } else {
                layer.msg("您没有做任何修改", {icon: 1});
            }
        },
        /*rules: {
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

        },*/
    });

    /* input转标签 */
    jQuery('#keyWordTags').tagsInput({
        width: '60%',
        height: '',
        defaultText: '',
        'interactive': true,
        'maxChars': 200,
        'background':'#d8d8d8'
        // placeholderColor : '#666666'
    });

});
