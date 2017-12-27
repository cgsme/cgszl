package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.UserService;
import cn.cgszl.common.dao.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台首页控制器
 *
 * @author cguisheng 2017/10/30 21:35
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 进入后台登录页
     *
     * @return
     */
    @RequestMapping("/admin/index")
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/admin/login")
    public String login(String username, String password, Model model) {
        // 服务端非空校验（用户名、密码）
        if (StringUtils.isBlank(username)) {
            model.addAttribute("msg", "用户名不能为空...");
            return "admin/login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("msg", "密码不能为空...");
            return "admin/login";
        }
        boolean result = userService.validateLogin(username, password);
        if (result) {
            // 重定向
            return "redirect:/admin/dashboard.html";
        }
        return "admin/login";
    }

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @RequestMapping("/admin/dashboard")
    public String toIndex() {
        return "admin/dashboard";
    }
}
