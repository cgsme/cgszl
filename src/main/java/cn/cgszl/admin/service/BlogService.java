package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Article;

import java.util.List;

/**
 * 博客文章管理业务接口
 * @author cguisheng 2018/1/1 14:49
 */
public interface BlogService {

    /**
     * 获取所有文章，按创建实践降序
     * @return 文章集合
     */
    List<Article> getBlogList();

    /**
     * 保存文章
     * @param article
     * @return
     */
    boolean savePost(Article article);
}
