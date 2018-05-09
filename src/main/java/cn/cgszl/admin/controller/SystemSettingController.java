package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.SiteOptionsService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.pojo.SiteOptions;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public String systemSetting(Model model) {
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
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return "admin/systemsetting";
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
