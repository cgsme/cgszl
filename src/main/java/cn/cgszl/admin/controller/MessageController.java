package cn.cgszl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 留言管理控制器
 *
 * @author cguisheng 2018/5/12 1:47
 */
@Controller
public class MessageController {

    /**
     * 留言管理
     *
     * @return
     */
    @RequestMapping("/admin/messagemanager")
    public String messageManager() {
        return "admin/messagemanager";
    }

}
