/* 登录 */
jQuery(document).ready(function () {

    // 获取cookie中用户名密码的值
    var isRememberPassword = jQuery.cookie("rememberPassword");
    // 判断cookie中是否保存了复选框的状态，若没有则不再取username和password的值
    if (isRememberPassword && "false" !== isRememberPassword) {
        var username = jQuery.cookie('username');
        var password = jQuery.cookie('password');
        //将获取的值填充入输入框中
        jQuery('#username').val(username);
        jQuery('#password').val(password);
        jQuery("#rememberPassword").prop('checked', true);
    }

    ///// 转换checkbox /////
    jQuery('input:checkbox').uniform();

    if (jQuery("#errormessage").html() != '' && jQuery("#errormessage").html() != null) {
        jQuery('.errormessage').fadeIn();
        jQuery('.nousername').hide();
        jQuery('.nopassword').hide();
    }

    ///// 提交登录表单 /////
    jQuery('#login').submit(function () {
        var username = jQuery('#username').val();
        var password = jQuery('#password').val();
        if (jQuery('#username').val() == '' && jQuery('#password').val() == '') {
            jQuery('.nousername').fadeIn();
            jQuery('.nopassword').hide();
            return false;
        }
        if (jQuery('#username').val() != '' && jQuery('#password').val() == '') {
            jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(jQuery('#username').val());
            jQuery('.nousername,.username').hide();
            return false;
        }
        // 获取复选框状态（是否记住密码）
        var isRememberPassword = jQuery("#rememberPassword").prop("checked");
        // 如果勾选了记住密码，保存到cookie
        if (isRememberPassword) {
            // 记住复选框勾选状态
            jQuery.cookie("rememberPassword", true, {expires: 7})
            // 记住用户名
            jQuery.cookie("username", username, {expires: 7});
            // 记住密码
            jQuery.cookie("password", password, {expires: 7});
        } else {
            jQuery.cookie("rememberPassword", false, {expire: -1});
            jQuery.cookie("username", "", {expires: -1});
            jQuery.cookie("password", "", {expires: -1});
        }
    });

    ///// 添加 PLACEHOLDER /////
    jQuery('#username').attr('placeholder', '用户名');
    jQuery('#password').attr('placeholder', '密码');
});
