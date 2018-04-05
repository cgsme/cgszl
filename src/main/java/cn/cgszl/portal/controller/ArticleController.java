package cn.cgszl.portal.controller;

import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.service.BlogService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.dto.GridData;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 前台文章控制器
 *
 * @author cguisheng 2018/3/31 15:18
 */
@Controller
@Transactional
public class ArticleController {

    @Resource
    private BlogService blogService;

    /**
     * 根据文章标识获取文章详情
     *
     * @param aid 文章标识
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)   // 事务隔离级别为 读已提交，防止更新点击量时数据不正确
    @RequestMapping(value = "/single/{aid}.html")
    public String getArticleDetailById(Model model, @PathVariable Integer aid) {
        Article article = null;
        try {
            article = blogService.getArticleDetailById(aid);
            // 传回前台展示
            model.addAttribute("article", article);
            // 更新点击量
            blogService.updatePostHits(article);
        } catch (CgszlException e) {
            // TODO 异常处理
            e.printStackTrace();
        }
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
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("orderCause", "hits desc");
            List<Article> articleList = blogService.getBlogListBySql(page, limit, paramMap);
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

    /**
     * 根据类型跳转到页面
     *
     * @param request 请求对象
     * @param name    查询条件
     * @param type    类型
     * @return 视图
     */
    @RequestMapping(value = "/portal/article/{type}/{name}.html")
    public String toHotPage(HttpServletRequest request, @PathVariable String name, @PathVariable String type) {
        name = new String(name.getBytes(Charset.forName("ISO8859-1")), Charset.forName("utf-8"));
        request.setAttribute("name", name);
        request.setAttribute("type", type);
        if (Types.TAG.getType().equalsIgnoreCase(type)) {
            return "portal/hotTagArticle";
        } else if (Types.CATEGORY.getType().equalsIgnoreCase(type)){
           return  "portal/hotCategoryArticle";
        }
        return null;
    }

    /**
     * 根据条件获取文章
     *
     * @return
     */
    @RequestMapping(value = "/portal/article/loadArticleByCondition")
    @ResponseBody
    public CommonResult loadArticleByCondition(HttpServletRequest request, String type, Integer page, Integer limit) {
        try {
            if (StringUtils.isNotBlank(type)) {
                List<Article> articleList = null;
                String searchKey = null;
                String searchValue = null;
                // 标签
                if (Types.TAG.getType().equalsIgnoreCase(type)) {
                    searchKey = "tag";
                    searchValue = request.getParameter("tag");
                } else if (Types.CATEGORY.getType().equalsIgnoreCase(type)) {
                    searchKey = "categories";
                    searchValue = request.getParameter("categories");
                }
                searchValue = new String(searchValue.getBytes(Charset.forName("ISO8859-1")), Charset.forName("UTF-8"));
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(searchKey, searchValue);
                articleList = blogService.getBlogListBySql(page, limit, paramMap);
                return CommonResult.ok(articleList);
            }
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
        return CommonResult.fail(false, "请求出错");
    }
}
