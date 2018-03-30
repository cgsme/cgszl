package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.admin.service.MetasService;
import cn.cgszl.common.CommonResult;
import cn.cgszl.common.GridData;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.CgszlUtils;
import cn.cgszl.common.utils.DateKit;
import cn.cgszl.common.utils.GsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private MetasService metasService;

    /**
     * 博客管理页
     *
     * @return 博客管理页面地址
     */
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
    public String newpost(HttpServletRequest request, Model model) {
        try {
            String aid = request.getParameter("aid");
            String actionType = request.getParameter("actionType");
            // 如果操作时查看详情
            if (StringUtils.isNotBlank(actionType) && (actionType.equals("viewDetail")
                    || actionType.equals("edit"))) {
                // 根据id获取文章信息
                Article article = blogService.findArticleById(aid);
                if (null != article) {
                    model.addAttribute("article", article);
                }
            }
            // 获取所有分类
            List<Metas> metasList = metasService.getAllCatList();
            // 保存到request中
            model.addAttribute("metasList", metasList);
            // 获取标签
            return "admin/newpost";
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            // 创建mybatis分页对象
            PageHelper.startPage(page, limit);
            // 调用service获取文章数据
            List<Article> articleList = blogService.getBlogList();
            // 使用pageInfo包装itemList，可以获得对应的总记录数、没有条数...等等
            PageInfo<Article> articlePageInfo = new PageInfo<Article>(articleList);
            return GridData.build(articleList, articlePageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建新文章
     *
     * @return
     */
    @RequestMapping(value = "/admin/savePost", method = RequestMethod.POST)
    @ResponseBody   // 标记返回json对象!
    public CommonResult savePost(HttpServletRequest request, Article article) {
        User user = CgszlUtils.getLoginUser(request);
        if (user != null) {
            // 封装作者标识
            article.setAuthorId(user.getUid());
        }
        try {
            // 判断是发布还是存为草稿
            String actionType = request.getParameter("actionType");
            // 发布
            if (StringUtils.isNotBlank(actionType) && actionType.equals("publish")) {
                // 默认为发布
                article.setStatus("publish");
                article.setDraft(false);
            } else {
                // 草稿不发布
                article.setStatus("unpublish");
                // 存为草稿
                article.setDraft(true);
            }
            boolean result = false;
            result = blogService.savePost(article);
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "操作失败");
            }
        } catch (CgszlException e) {
            return CommonResult.fail(false, e.getMessage());
        }
    }

    /**
     * 根据文章标识删除文章(逻辑删)
     *
     * @param aid 文章标识
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/blog/deleteByAid", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteByAid(@RequestParam(value = "aid") String aid) {
        boolean result = false;
        try {
            result = blogService.deleteByAid(aid);
        } catch (CgszlException e) {
            e.printStackTrace();
            result = false;
        }
        if (result) {
            return CommonResult.ok();
        }
        return CommonResult.fail(false, "删除失败");
    }

    @RequestMapping(value = "/admin/blog/deleteByAidPhy", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteByAidPhy(@RequestParam(name = "aid") String aid) {
        boolean result = false;
        try {
            result = blogService.deleteByAidPhy(aid);
        } catch (CgszlException e) {
            e.printStackTrace();
            result = false;
        }
        if (result) {
            return CommonResult.ok();
        } else {
            return CommonResult.fail(false, "删除失败");
        }
    }

    /**
     * 跳转到草稿箱页面
     *
     * @return 页面地址
     */
    @RequestMapping(value = "/admin/draft")
    public String allDraft() {
        // 返回草稿箱页面
        return "admin/draft";
    }

    /**
     * 跳转到回收站页面
     *
     * @return 页面地址
     */
    @RequestMapping(value = "/admin/trash")
    public String allTrash() {
        // 返回回收站页面
        return "admin/trash";
    }

    /**
     * 分页获取草稿箱列表
     *
     * @param page  当前页
     * @param limit 每页显示记录数
     * @return layui数据表格通用结果对象
     */
    @RequestMapping(value = "/admin/blog/getAllDraftList")
    @ResponseBody
    public GridData getAllDraftList(int page, int limit) {
        try {
            // 创建mybatis分页对象
            PageHelper pageHelper = new PageHelper();
            pageHelper.startPage(page, limit);
            // 调用service获取文章数据
            List<Article> articleList = blogService.findAllDraftList();
            // 使用pageInfo包装itemList，可以获得对应的总记录数、没有条数...等等
            PageInfo<Article> articlePageInfo = new PageInfo<Article>(articleList);
            return GridData.build(articleList, articleList.size());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 草稿箱中发布文章
     *
     * @param aid 文章标识
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/blog/publishByAid", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult publishByAid(@RequestParam(value = "aid") String aid) {
        try {
            boolean resule = blogService.publishById(aid);
            if (resule) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "发布失败");
            }
        } catch (CgszlException e) {
            return CommonResult.fail(false, "系统异常,请稍后再试……");
        }
    }

    /**
     * 获取回收站文章列表
     *
     * @param page  当前页
     * @param limit 每页记录数
     * @return layui通用结果对象
     */
    @RequestMapping(value = "/admin/blog/getAllTrashList")
    @ResponseBody
    public GridData getAllTrashList(int page, int limit) {
        try {
            // 创建mybatis分页对象
            PageHelper pageHelper = new PageHelper();
            pageHelper.startPage(page, limit);
            // 调用service获取文章数据
            List<Article> articleList = blogService.getAllTrashList();
            // 使用pageInfo包装itemList，可以获得对应的总记录数、没有条数...等等
            PageInfo<Article> articlePageInfo = new PageInfo<Article>(articleList);
            return GridData.build(articleList, articleList.size());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 还原回收站中的文章
     *
     * @param aid 文字标识
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/blog/revertByAid", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult revertByAid(@RequestParam(name = "aid") String aid) {
        boolean result = false;
        try {
            result = blogService.revertByAid(aid);
        } catch (CgszlException e) {
            e.printStackTrace();
            result = false;
        }
        if (result) {
            return CommonResult.ok();
        } else {
            return CommonResult.fail(false, "操作失败");
        }
    }
}
