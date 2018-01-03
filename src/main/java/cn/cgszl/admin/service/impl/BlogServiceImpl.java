package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.BlogService;
import cn.cgszl.common.dao.mapper.ArticleMapper;
import cn.cgszl.common.dao.pojo.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客文章管理业务实现类
 * @author cguisheng 2018/1/1 14:50
 */
@Service
public class BlogServiceImpl implements BlogService {

    // 注入博客文章mapper接口
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 获取所有文章
     * @return 文章集合
     */
    @Override
    public List<Article> getBlogList() {
        return articleMapper.getArticleList();
    }
}
