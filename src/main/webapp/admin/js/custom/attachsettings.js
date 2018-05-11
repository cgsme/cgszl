/*
 * 系统设置
 */
jQuery(document).ready(function () {

    // 保存旧数据
    var oldData = jQuery("#attachSettingForm").serializeJSON();

    // 在键盘按下并释放及提交后验证提交表单
    jQuery("#attachSettingForm").validate({
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
            attach_upload_path: {
                required: true,
                maxlength: 10
            },
            attach_backup_path: {
                required: true,
                maxlength: 10
            },

        },*/
    });

    // 数据备份
    /* 异步提交表单 */
    jQuery("#backUpForm").ajaxForm({
        url: "/admin/setting/attachBackUp.action",
        beforeSubmit: function (arr, $form, options) {

            var checkValue = new Array();
            // 获取用户选中的要备份的文件类型
            jQuery("input:checkbox[checked]").each(function(i){
                checkValue[i] = jQuery(this).val();
            });
            // 判断是否有选择
            if (checkValue.length < 1) {
                top.layer.msg("请勾选要备份的文件", {icon: 2});
                return false;
            }
            // TODO 提示备份中
            layer.load(3, {
                shade: [0.5, '#fff']
            });
        },
        success: function (data) {
            // 关闭加载提示
            layer.closeAll();
            if (data && data.success) {
                top.layer.msg('操作成功', {icon: 1});
            } else {
                top.layer.msg(data.message, {icon: 2});
            }
        }
    });
});
