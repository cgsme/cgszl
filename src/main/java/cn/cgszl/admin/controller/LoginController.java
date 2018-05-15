package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.ConsoleService;
import cn.cgszl.common.dao.dto.Statistics;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.log.ModuleOperation;
import cn.cgszl.common.log.SystemLog;
import cn.cgszl.common.service.UserService;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 后台首页控制器
 *
 * @author cguisheng 2017/10/30 21:35
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Resource
    private ConsoleService consoleService;

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
            return "admin/internal";
        }
        return "admin/login";
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/admin/login")
//    @SystemLog(module = "用户模块", methods = "用户登录")
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
        try {
            User user = userService.validateLogin(username, password);
            if (null != user) {
                // 保存用户登录信息
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
                // 重定向
                return "redirect:/admin/dashboard.html";
            }
        } catch (CgszlException e) {
            e.printStackTrace();
            return "admin/internal";
        }
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("msg", "用户名或密码错误.");
//        model.addAttribute("password", "password");
        return "redirect:/admin/index.html";
    }

    /**
     * 获取控制台数据
     *
     * @param request 请求对象
     */
    private void getPreData(HttpServletRequest request) throws CgszlException {
        // 获取最新文章
        List<Article> recentArticleList = consoleService.getRecentArticle();
        // 获取最新评论
        List<Comment> recentCommentList = consoleService.getrecentComment(Types.COMMENT.getType());
        // 获取最新留言
        List<Comment> recentMessageList = consoleService.getrecentComment(Types.MESSAGE.getType());
        // 获取统计数据
        Statistics statistics = consoleService.getStatistics();
        request.setAttribute("recentArticleList", recentArticleList);
        request.setAttribute("recentCommentList", recentCommentList);
        request.setAttribute("recentMessageList", recentMessageList);
        request.setAttribute("statistics", statistics);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/admin/logout")
    public String logOut(HttpServletRequest request) {
        // 销毁session
        request.getSession().invalidate();
        // 返回到登录页
        return "redirect:/admin/index.html";
    }

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @RequestMapping("/admin/dashboard")
    @SystemLog(module = "控制台", methods = "访问控制台")
    public String toIndex(HttpServletRequest request) {
        try {
            // 获取控制台中需要的数据
            this.getPreData(request);
            int i = 1 / 0;
        } catch (CgszlException e) {
            e.printStackTrace();
            return "admin/internal";
        }
        return "admin/dashboard";
    }
}
