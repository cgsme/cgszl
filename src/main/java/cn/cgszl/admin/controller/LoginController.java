package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台首页控制器
 * @author cguisheng 2017/10/30 21:35
 */
@Controller
public class LoginController {

    /**
     * 进入后台登录页
     * @return
     */
    @RequestMapping("/admin/index")
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/admin/login")
    public String login() {
        // TODO 用户登录校验
        // 重定向
        return "redirect:/admin/dashboard.html";
    }

    /**
     * 跳转到后台首页
     * @return
     */
    @RequestMapping("/admin/dashboard")
    public String toIndex() {
        return "admin/dashboard";
    }
}
