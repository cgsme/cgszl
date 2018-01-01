package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客管理控制器
 * @author cguisheng 2017/12/30 13:39
 */
@Controller
public class BlogController {

    @RequestMapping("/admin/manageblog")
    public String toBlogMnanagr() {
        return "admin/manageblog";
    }

    /**
     * 创建新文章
     * @return
     */
    @RequestMapping("/admin/newpost")
    public String newpost() {
        return "admin/newpost";
    }
}
