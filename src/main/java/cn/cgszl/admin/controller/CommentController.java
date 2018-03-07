package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评论管理控制器
 *
 * @author cguisheng 2018/2/23 21:17
 */
@Controller
public class CommentController {

    @RequestMapping(value = "/admin/commentmanager")
    public String commentManager() {

        return "admin/commentmanager";
    }

}
