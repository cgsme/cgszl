package cn.cgszl.common;

import cn.cgszl.common.dao.mapper.ArticleMapper;
import cn.cgszl.common.dao.pojo.Article;

import java.util.Date;

/**
 * 文章操作线程
 *
 * @author cguisheng 2018/4/2 23:35
 */
public class ArticleOperThread extends Thread {

    private ArticleMapper articleMapper;

    private Article article;

    public ArticleOperThread(ArticleMapper articleMapper, Article article) {
        this.articleMapper = articleMapper;
        this.article = article;
    }

    @Override
    public void run() {
        article.setHits(article.getHits() + 1);
        System.out.println(new Date() + " 正在更新点击量，当前点击量：" + article.getHits());
        articleMapper.updateByPrimaryKey(article);
    }
}
