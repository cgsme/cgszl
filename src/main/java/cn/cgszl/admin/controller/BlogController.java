package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.common.GridData;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.utils.GsonUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客文章管理控制器
 * @author cguisheng 2017/12/30 13:39
 */
@Controller
public class BlogController {

    @Resource
    private BlogService blogService;

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

    /**
     * 管理文章
     * @return
     */
    @RequestMapping(value = "/admin/allposts", method = RequestMethod.POST)
    public String allPost(Model model) {
        List<Article> articleList = blogService.getBlogList();
        model.addAttribute("articleList", articleList);
        return "admin/allposts";
    }

    @RequestMapping("/admin/getAllArticleList")
    @ResponseBody
    public GridData getAllArticleList() {
        GridData gridData = new GridData();
        List<Article> articleList = blogService.getBlogList();
        gridData.setCode("0");
        gridData.setMsg("ok");
        gridData.setCount(articleList.size());
        gridData.setData(articleList);
        return gridData;
    }
}
