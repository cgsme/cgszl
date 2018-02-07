package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.common.dao.mapper.ArticleMapper;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.ArticleExample;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.DateUtils;
import cn.cgszl.common.utils.UNIDGenerate;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 博客文章管理业务实现类
 *
 * @author cguisheng 2018/1/1 14:50
 */
@Service
public class BlogServiceImpl implements BlogService {

    // 注入博客文章mapper接口
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 获取所有文章
     *
     * @return 文章集合
     */
    @Override
    public List<Article> getBlogList() {
        return articleMapper.getArticleList();
    }

    /**
     * 保存文章
     *
     * @param article 文章对象
     * @return 执行是否成功
     */
    @Override
    public boolean savePost(Article article) throws CgszlException {
        // 验证用户是否登录
        if (null == article || article.getAuthorId() == null) {
            throw new CgszlException("请先登录...");
        }
        // 验证文章标题
        if (StringUtils.isBlank(article.getTitle())) {
            throw new CgszlException("文章标题不能为空");
        }
        // 若没有自定义访问路径，则默认为文章id
        if (StringUtils.isBlank(article.getSlug())) {
            article.setSlug(null);
        }
        int time = (int) (new Date().getTime() / 1000L);
        // 若没有选择分类，则为默认分类
        if (StringUtils.isBlank(article.getType())) {
            article.setType("default");
        }
        boolean result;
        // 新增
        if (null == article.getAid()) {
            // 文章类型
            article.setType("post");
            // 初始点击量为0
            article.setHits(0);
            // 初始评论数为0
            article.setCommentsNum(0);
            // 初始点赞为0
            article.setLikeNum(0);
            article.setCreated(time);
            result = articleMapper.insertSelective(article) > 0;
        } else {   // 编辑
            article.setModified(time);
            result = articleMapper.updateByPrimaryKeySelective(article) > 0;
        }
        return result;
    }

    /**
     * 根据文章标识获取文章列表
     *
     * @param aid 文章标识
     * @return 文章对象
     * @throws CgszlException 全局异常
     */
    @Override
    public Article findArticleById(String aid) throws CgszlException {
        return articleMapper.selectByPrimaryKey(Integer.parseInt(aid));
    }

    /**
     * 更新文章
     *
     * @param article 文章对象
     * @return 操作结果
     * @throws CgszlException 全局异常
     */
    @Override
    public boolean updatePost(Article article) throws CgszlException {
        return articleMapper.updateByPrimaryKeyWithBLOBs(article) > 0;
    }

    /**
     * 根据文章标识删除文章
     *
     * @param aid 文章标识
     * @return 删除结果
     * @throws CgszlException 全局异常
     */
    @Override
    public boolean deleteByAid(String aid) throws CgszlException {
        Article article = findArticleById(aid);
        if (null != article) {
            // 修改删除标记
            article.setDeleteFlag(true);
            return articleMapper.updateByPrimaryKeySelective(article) > 0;
        } else {
            return false;
        }
    }

    /**
     * 获取所有草稿列表
     *
     * @return 草稿集合
     * @throws CgszlException 全局异常
     */
    @Override
    public List<Article> findAllDraftList() throws CgszlException {
        return articleMapper.getArticleDraftList();
    }

    /**
     * 根据文章标识发布文章
     * @param aid 文章标识
     * @return 操作结果
     * @throws CgszlException 系统异常
     */
    @Override
    public boolean publishById(String aid) throws CgszlException {
        Article article = findArticleById(aid);
        article.setStatus("publish");
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }
}
