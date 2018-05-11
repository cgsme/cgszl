package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.SiteOptionsService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.SiteOptions;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.CgszlUtils;
import cn.cgszl.common.utils.DateKit;
import cn.cgszl.common.utils.ZipUtlis;
import cn.cgszl.common.utils.ZipUtlis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统设置控制器
 *
 * @author cguisheng 2018/5/8 22:11
 */
@Controller
public class SystemSettingController {

    @Resource
    private SiteOptionsService siteOptionsService;

    /**
     * 系统设置页
     *
     * @param model mvc视图对象
     * @return
     */
    @RequestMapping("/admin/systemsetting.html")
    public String systemSetting(Model model, String operType) {
        try {
            Map<String, String> siteOptionsMap = null;
            List<SiteOptions> siteOptionsList = siteOptionsService.listSiteOptions();
            if (!CollectionUtils.isEmpty(siteOptionsList)) {
                siteOptionsMap = new HashMap<String, String>();
                for (SiteOptions siteOption : siteOptionsList) {
                    siteOptionsMap.put(siteOption.getName(), siteOption.getValue());
                }
                model.addAttribute("siteOptionsMap", siteOptionsMap);
            }
            if (StringUtils.isNotBlank(operType)) {
                if ("attachSet".equals(operType)) {
                    // 跳转到附件存储设置页面
                    return "admin/attachsetting";
                }
            }
            return "admin/systemsetting";
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存系统设置信息
     *
     * @param request 请求对象
     * @return
     */
    @RequestMapping("/admin/setting/saveSiteOptions")
    @ResponseBody
    public CommonResult saveSiteOptions(HttpServletRequest request) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> valueMap = new HashMap<>();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                valueMap.put(entry.getKey(), translate(entry.getValue()));
            }
            siteOptionsService.saveSiteOptions(valueMap);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 附件存储
     *
     * @return
     */
    @RequestMapping("/admin/attachsetting")
    public String attachSetting(Model model) {
        return this.systemSetting(model, "attachSet");
    }

    /**
     * 数据备份
     *
     * @param backUpFileType 需要备份的文件类型 相册、附件、数据库脚本文件
     * @param backUpPath     文件保存路径
     * @return
     */
    @RequestMapping("/admin/setting/attachBackUp")
    @ResponseBody
    public CommonResult attachBack(HttpServletRequest request, String backUpFileType, String backUpPath) {
        if (StringUtils.isBlank(backUpFileType)) {
            return CommonResult.fail(false, "请勾选要备份的文件");
        }
        if (StringUtils.isBlank(backUpPath)) {
            return CommonResult.fail(false, "请输入文件备份路径");
        }
        // 压缩包文件名
        String fname = null;
        File backUpDir = new File(backUpPath);
        // 判断输入的目录是否存在，不存在则创建
        if (!backUpDir.exists()) {
            backUpDir.mkdirs();
        }
        if (backUpFileType.contains(Types.PHOTOFILE.getType())) {   // 相册
            // 上传的相册文件保存的路径
            String uploadPhotoFilePath = CgszlUtils.getWebappPath(request) + "/upload/";
            fname = Types.PHOTOFILE.getType() + "_" + DateKit.getCurrentUnixTime() + "_backup.zip";
            backUpPath = backUpPath + File.separator + fname;
            try {
                ZipUtlis.zipFolder(uploadPhotoFilePath, backUpPath);
            } catch (Exception e) {
                e.printStackTrace();
                // TODO 记录错误信息，提示用户
            }
        }
        if (backUpFileType.contains(Types.ATTACHFILE.getType())) {     // 附件
            // 上传的附件文件保存的路径
            String uploadAttachFilePath = CgszlUtils.getWebappPath(request) + "/upload/attach/";
            fname = Types.ATTACHFILE.getType() + "_" + DateKit.getCurrentUnixTime() + "_backup.zip";
            backUpPath = backUpPath + File.separator + fname;
            try {
                ZipUtlis.zipFolder(uploadAttachFilePath, backUpPath);
            } catch (Exception e) {
                e.printStackTrace();
                // TODO 记录错误信息，提示用户
            }
        }
        if (backUpFileType.contains(Types.SQLFILE.getType())) {     // 数据库脚本文件

        }
        return CommonResult.ok();
    }

    /**
     * 数组转换为字符串
     *
     * @param value 数组值
     * @return 转换后的字符串
     */
    private String translate(String[] value) {
        String tmpValue[] = value;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tmpValue.length; i++) {
            stringBuilder.append(",");
            stringBuilder.append(tmpValue[i]);
        }
        return stringBuilder.length() > 0 ? stringBuilder.substring(1) : stringBuilder.toString();
    }

}
