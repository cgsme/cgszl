package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页控制器
 * @author cguisheng 2017/10/30 21:35
 */
@Controller
public class AdminIndexController {

    @RequestMapping("/admin/index")
    public String index() {
        return "admin/index";
    }
}
