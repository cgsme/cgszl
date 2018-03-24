package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 *
 * @author cguisheng 2018/3/24 17:05
 */
@Controller
public class UserController {

    /**
     * 显示个人资料页
     * @return
     */
    @RequestMapping("/admin/editProfile")
    public String profile() {
        return "admin/profile";
    }
}
