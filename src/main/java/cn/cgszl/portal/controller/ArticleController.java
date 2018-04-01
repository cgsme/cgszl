package cn.cgszl.portal.controller;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.common.CommonResult;
import cn.cgszl.common.GridData;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 前台文章控制器
 *
 * @author cguisheng 2018/3/31 15:18
 */
@Controller
public class ArticleController {

    @Resource
    private BlogService blogService;

    /**
     * 根据文章标识获取文章详情
     *
     * @param aid 文章标识
     * @return
     */
    @RequestMapping(value = "/single/{aid}.html")
    public String getArticleDetailById(Model model, @PathVariable Integer aid) {
        Article article = null;
        try {
            article = blogService.getArticleDetailById(aid);
        } catch (CgszlException e) {
            // TODO 异常处理
            e.printStackTrace();
        }
        model.addAttribute("article", article);
        return "portal/single";
    }

    /**
     * 获取文章列表
     *
     * @return
     */
    @RequestMapping(value = "/portal/listArticles")
    @ResponseBody
    public GridData listArticles(Integer page, Integer limit) {
        try {
            // 调用service获取文章数据
            PageInfo<Article> articlePageInfo = blogService.getBlogList(page, limit);
            return GridData.build(articlePageInfo.getList(), articlePageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文章列表
     *
     * @return
     */
    @RequestMapping(value = "/portal/listHeightHitsArticles")
    @ResponseBody
    public CommonResult listHeightHitsArticles(Integer page, Integer limit) {
        try {
            // 调用service获取文章数据
            List<Article> articleList = blogService.getBlogListBySql(page, limit, "hits desc");
            return CommonResult.ok(true, articleList);
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取热门文章
     *
     * @param page  页码
     * @param limit 每页记录数
     * @return
     */
    @RequestMapping(value = "/portal/listHotArticles")
    @ResponseBody
    public List<Article> listHotArticles(Integer page, Integer limit) {
        try {
            return blogService.listHotArticles(page, limit);
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 推荐或不推荐
     *
     * @param aid 文章标识
     * @return
     */
    @RequestMapping(value = "/portal/likeOrUnlike")
    @ResponseBody
    public CommonResult like(Integer aid, boolean isLike) {
        try {
            boolean result = blogService.like(aid, isLike);
            if (result) {
                return CommonResult.ok();
            }
            return CommonResult.fail(false, "操作失败");
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

}
