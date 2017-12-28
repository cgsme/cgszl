package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.UserService;
import cn.cgszl.common.dao.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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
    public String loginPage(String username, String msg, Model model) {
        try {
            if (StringUtils.isNotBlank(username)) {
                // 用于登录表单回显
                model.addAttribute("username", new String(username.getBytes("ISO-8859-1"), "UTF-8"));
            }
            if (StringUtils.isNotBlank(msg)) {
                // 返回错误信息
                model.addAttribute("msg", new String(msg.getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "admin/login";
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/admin/login")
    public String login(HttpServletRequest request, String username, String password, RedirectAttributes redirectAttributes) {
        // 服务端非空校验（用户名、密码）
        if (StringUtils.isBlank(username)) {
            redirectAttributes.addAttribute("msg", "用户名不能为空.");
            return "redirect:/admin/index.html";
        }
        if (StringUtils.isBlank(password)) {
            redirectAttributes.addAttribute("msg", "密码不能为空.");
            return "redirect:/admin/index.html";
        }
        User user = userService.validateLogin(username, password);
        if (null != user) {
            // 保存用户登录信息
            request.getSession().setAttribute("user", user);
            // 重定向
            return "redirect:/admin/dashboard.html";
        }
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("msg", "用户名或密码错误.");
//        model.addAttribute("password", "password");
        return "redirect:/admin/index.html";
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
