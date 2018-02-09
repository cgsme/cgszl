package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 博客文章管理业务接口
 *
 * @author cguisheng 2018/1/1 14:49
 */
public interface BlogService {

    /**
     * 获取所有文章，按创建实践降序
     *
     * @return 文章集合
     */
    List<Article> getBlogList() throws CgszlException;

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    boolean savePost(Article article) throws CgszlException;

    /**
     * 根据文章标识获取文章信息
     *
     * @param aid 文章标识
     * @return 文章
     */
    Article findArticleById(String aid) throws CgszlException;

    /**
     * 更新文章
     *
     * @param article 文章对象
     * @return 结果
     */
    boolean updatePost(Article article) throws CgszlException;

    /**
     * 根据文章标识删除文章(逻辑删,回收站展示)
     *
     * @param aid 文章标识
     * @return 删除结果
     */
    boolean deleteByAid(String aid) throws CgszlException;

    /**
     * 获取草稿箱列表
     *
     * @return 草稿集合
     * @throws CgszlException 全局异常
     */
    List<Article> findAllDraftList() throws CgszlException;

    /**
     * 根据文章标识发布文章
     *
     * @param aid 文章标识
     * @return 操作结果
     */
    boolean publishById(String aid) throws CgszlException;

    /**
     * 获取回收站数据列表
     *
     * @return 回收站文章列表
     */
    List<Article> getAllTrashList() throws CgszlException;

    /**
     * 还原回收站中的文章
     *
     * @param aid 文章标识
     * @return 通用结果对象
     */
    boolean revertByAid(String aid) throws CgszlException;

    /**
     * 根据文章标识删除文章（物理删）
     *
     * @param aid 文章标识
     * @return 操作结果
     * @throws CgszlException 系统异常
     */
    boolean deleteByAidPhy(String aid) throws CgszlException;
}
