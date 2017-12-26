jQuery(document).ready(function(){
								
	///// 转换checkbox /////
	jQuery('input:checkbox').uniform();
	
	///// 提交登录表单 /////
	jQuery('#login').submit(function(){
	
		if(jQuery('#username').val() == '' && jQuery('#password').val() == '') {
			jQuery('.nousername').fadeIn();
			jQuery('.nopassword').hide();
			return false;	
		}
		if(jQuery('#username').val() != '' && jQuery('#password').val() == '') {
			jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(jQuery('#username').val());
			jQuery('.nousername,.username').hide();
			return false;
		}
	});
	
	///// 添加 PLACEHOLDER /////
	jQuery('#username').attr('placeholder','用户名');
	jQuery('#password').attr('placeholder','密码');
});
