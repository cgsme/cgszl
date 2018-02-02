package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.common.GridData;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.utils.DateKit;
import cn.cgszl.common.utils.GsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 博客文章管理控制器
 *
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
     *
     * @return 创建文章页面
     */
    @RequestMapping("/admin/newpost")
    public String newpost() {
        return "admin/newpost";
    }

    /**
     * 管理文章
     *
     * @return 文章管理页面
     */
    @RequestMapping(value = "/admin/allposts")
    public String allPost(Model model) {
        return "admin/allposts";
    }

    /**
     * 获取所有文章列表
     *
     * @return layui 通用结果对象
     */
    @RequestMapping("/admin/getAllArticleList")
    @ResponseBody
    public GridData getAllArticleList(int page, int limit) {
        // 创建mybatis分页对象
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, limit);
        // 调用service获取文章数据
        List<Article> articleList = blogService.getBlogList();
        // 使用pageInfo包装itemList，可以获得对应的总记录数、没有条数...等等
        PageInfo<Article> articlePageInfo = new PageInfo<Article>(articleList);
        return GridData.build(articleList, articlePageInfo.getTotal());
    }

    /**
     * 创建新文章
     *
     * @return
     */
    @RequestMapping("/admin/savePost")
    public String newPost(Article article) {
        boolean result = blogService.savePost(article);
        return "redirect:/admin/allposts.html";
    }
}
