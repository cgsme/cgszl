package cn.cgszl.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 关于
 *
 * @author cguisheng 2018/3/31 16:08
 */
@Controller
public class AboutController {

    /**
     * 关于页面
     *
     * @return
     */
    @RequestMapping(value = "/about")
    public String aboutPage() {
        return "portal/about";
    }

    /**
     * 联系我
     *
     * @return
     */
    @RequestMapping(value = "/contact")
    public String contactPage() {
        return "portal/contact";
    }
}
