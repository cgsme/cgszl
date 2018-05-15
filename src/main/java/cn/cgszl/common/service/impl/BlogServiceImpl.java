package cn.cgszl.common.service.impl;

import cn.cgszl.common.ArticleOperThread;
import cn.cgszl.common.dao.dto.States;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.ArticleExample;
import cn.cgszl.common.service.BlogService;
import cn.cgszl.common.dao.mapper.ArticleMapper;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public PageInfo<Article> getBlogList(Integer page, Integer limit) {
        // 创建mybatis分页对象
        PageHelper.startPage(page, limit);
        List<Article> articleList = articleMapper.getArticleList();
        // 使用pageInfo包装itemList，可以获得对应的总记录数、没有条数...等等
        return new PageInfo<Article>(articleList);
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
            article.setUnlikeNum(0);
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
     *
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

    /**
     * 获取回收站文章列表
     *
     * @return 文章集合
     * @throws CgszlException 系统异常
     */
    @Override
    public List<Article> getAllTrashList() throws CgszlException {
        return articleMapper.getAllTrashList();
    }

    /**
     * 还原回收站中的文章
     *
     * @param aid 文章标识
     * @return 通用结果对象
     * @throws CgszlException 系统异常
     */
    @Override
    public boolean revertByAid(String aid) throws CgszlException {
        Article article = findArticleById(aid);
        article.setDeleteFlag(false);
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    /**
     * 根据文章标识删除文章（物理删）
     *
     * @param aid 文章标识
     * @return 操作结果
     * @throws CgszlException 系统异常
     */
    @Override
    public boolean deleteByAidPhy(String aid) throws CgszlException {
        return articleMapper.deleteByPrimaryKey(Integer.parseInt(aid)) > 0;
    }

    /**
     * 根据文章标识获取文章信息
     *
     * @param aid 文章标识
     * @return
     * @throws CgszlException
     */
    @Override
    public Article getArticleDetailById(Integer aid) throws CgszlException {
        return articleMapper.selectArticleDetailById(aid);
    }

    /**
     * 获取热门文章
     *
     * @param page  页码
     * @param limit 每页记录数
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Article> listHotArticles(Integer page, Integer limit) throws CgszlException {
        PageHelper.startPage(page, limit);
        return articleMapper.listHotArticles();
    }

    /**
     * 点赞
     *
     * @param aid    文章标识
     * @param isLike
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean like(Integer aid, boolean isLike) throws CgszlException {
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (isLike) {
            article.setLikeNum(article.getLikeNum() + 1);
        } else {
            article.setUnlikeNum(article.getUnlikeNum() + 1);
        }
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    /**
     * 根据sql获取文章列表
     *
     * @param page     当前页
     * @param limit    每页记录数
     * @param paramMap 查询条件
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Article> getBlogListBySql(Integer page, Integer limit, Map<String, Object> paramMap) throws CgszlException {
        PageHelper.startPage(page, limit);
        return articleMapper.getArticleListBySql(paramMap);
    }

    /**
     * 更新文章点击量
     *
     * @param article
     * @return
     * @throws CgszlException
     */
    @Override
    public void updatePostHits(Article article) throws CgszlException {
        new ArticleOperThread(articleMapper, article).start();
    }

    /**
     * 根据条件查询文章
     *
     * @param searchValue 条件值
     * @param type
     * @param page
     * @param limit       @return
     * @throws CgszlException
     */
    @Override
    public List<Article> loadArticleByCondition(String searchValue, String type, Integer page, Integer limit) throws CgszlException {
        if (StringUtils.isNotBlank(type)) {
            PageHelper.startPage(page, limit);
            ArticleExample articleExample = new ArticleExample();
            if (Types.CATEGORY.getType().equalsIgnoreCase(type)) {
                articleExample.createCriteria().andCategoriesEqualTo(searchValue);
            } else if (Types.TAG.getType().equalsIgnoreCase(searchValue)) {
                articleExample.createCriteria().andTagsLike(searchValue);
            }
            return articleMapper.selectByExample(articleExample);
        }
        return null;
    }

    /**
     * 根据文章标识获取文章（无大文本）
     *
     * @param aid
     * @return
     * @throws CgszlException
     */
    @Override
    public Article getArticleById(Integer aid) throws CgszlException {
        return articleMapper.selectByPrimaryKeyWithoutBlob(aid);
    }

    /**
     * 根据关键字查询文章
     *
     * @param page    页码
     * @param limit   每页记录数
     * @param keyWord 关键字  @throws CgszlException
     */
    @Override
    public List<Article> listArticleByKeyWord(Integer page, Integer limit, String keyWord) throws CgszlException {
        if (null == page) {
            page = 1;
        }
        if (org.springframework.util.StringUtils.isEmpty(limit)) {
            limit = 6;
        }
        PageHelper.startPage(page, limit);
        keyWord = "%" + keyWord + "%";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyWord", keyWord);
        paramMap.put("orderCause", "created desc");
        return articleMapper.getArticleListBySql(paramMap);
    }

    /**
     * 根据状态统计文章数量
     *
     * @param state 文章状态
     * @return
     * @throws CgszlException
     */
    @Override
    public Long countArticle(String state) throws CgszlException {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andStatusEqualTo(state);
        return articleMapper.countByExample(articleExample);
    }
}
